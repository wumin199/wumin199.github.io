---
title: 深入浅出无锁数据结构：以无锁队列为例
date: 2026-04-11 10:00:00
tags: 
  - 编程
  - 算法
  - C++
  - Python
toc: true
comment: false
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

在多线程编程中，我们经常使用互斥锁（Mutex）来保证数据的一致性。然而，锁带来的上下文切换、死锁风险以及线程阻塞等问题往往会成为性能瓶颈。本文将为你介绍什么是**无锁数据结构**，并通过设计和实现最常见的**无锁队列（Lock-Free Queue）**，为你提供 C++ 和 Python 的最小可执行代码，帮助你深入理解其核心算法原理。

<!-- more -->

## 1. 什么是无锁（Lock-Free）数据结构？

**无锁（Lock-Free）** 并不是指不使用任何同步机制，而是指一种多线程的非阻塞（Non-blocking）同步方式。它的核心定义是：**在系统的任何步骤中，至少有一个线程能够取得进展**。也就是说，如果某个线程被操作系统挂起，它绝不会阻止其他线程继续执行。

相比之下，有锁（Lock-based）的结构中，如果持有锁的线程被挂起，其他所有等待该锁的线程都会被阻塞（发生死锁或饥饿现象）。

无锁数据结构的核心依赖于硬件级别支持的原子操作，最常见的就是 **CAS (Compare-And-Swap)**：
- **CAS(地址, 期望值, 新值)**：检查内存地址中的值是否等于“期望值”，如果是，则将其原子地替换为“新值”并返回成功；否则什么都不做并返回失败。

---

## 2. 设计文档：Michael-Scott 无锁队列

对于无锁队列，目前最经典、应用最广的算法是 **Michael-Scott Queue**。我们采用该算法进行简化实现。

### 2.1 核心数据结构设计
- **Node（节点）**：包含数据域 `data` 和原子指针 `next`。
- **Queue（队列）**：包含两个原子指针：`head`（头）和 `tail`（尾）。
- **Dummy Node（哨兵节点）**：初始化时，队列中放入一个空数据的 Dummy 节点，`head` 和 `tail` 都指向它。这样可以解耦 enqueue 和 dequeue 操作，避免它们同时争抢同一个空指针。

### 2.2 Enqueue (入队) 流程
1. 创建新节点 `newNode`。
2. 循环（重试）：
   - 读取 `tail`，并读取 `tail->next`。
   - 如果 `tail` 的确指向末尾（即 `tail->next == null`）：
     - 使用 CAS 尝试将 `tail->next` 从 `null` 指向 `newNode`。
     - 如果成功，再使用 CAS 尝试将 `tail` 指向 `newNode`，返回。
     - 如果失败（被其他线程抢先），则重试。
   - 如果 `tail->next != null`，说明有其他线程入队了节点，但还没来得及更新 `tail`。当前线程通过 CAS 帮助其把 `tail` 指向下一个节点。

### 2.3 Dequeue (出队) 流程
1. 循环（重试）：
   - 读取 `head`, `tail`, 以及 `head->next`。
   - 如果 `head == tail`，说明队列为空，或者 `tail` 还没更新：
     - 如果 `head->next == null`，真正为空，返回失败。
     - 否则，帮助推进 `tail`。
   - 如果 `head != tail`，说明有数据：
     - 读取 `head->next` 中的数据。
     - 使用 CAS 尝试将 `head` 向后移动一位（`head = head->next`）。
     - 若成功，返回数据；若失败，重试。

*注意：在生产环境的 C/C++ 实现中，出队后的节点回收会面临 **ABA 问题** 和 **释放-使用 (Use-After-Free)** 的风险，通常需要引入 Hazard Pointers (风险指针) 或 Epoch-Based Reclamation。为保证核心算法的间接性和易读性，本文的 C++ 代码中不执行 `delete` (由系统退出统一回收)，Python 则依靠内置的 GC 处理。*

---

## 3. C++ 实现与对比测试

在 C++ 中，我们可以使用 `std::atomic` 和 `compare_exchange_weak` 轻松构建无锁队列。

### 代码实现 (main.cpp)

```cpp
#include <iostream>
#include <atomic>
#include <thread>
#include <vector>
#include <queue>
#include <mutex>
#include <chrono>
#include <cassert>

// === 1. 无锁队列实现 (Michael-Scott 算法最小可执行单元) ===
template<typename T>
struct Node {
    T data;
    std::atomic<Node*> next;
    Node(T val) : data(val), next(nullptr) {}
};

template<typename T>
class LockFreeQueue {
    std::atomic<Node<T>*> head;
    std::atomic<Node<T>*> tail;
public:
    LockFreeQueue() {
        Node<T>* dummy = new Node<T>(T());
        head.store(dummy);
        tail.store(dummy);
    }

    void enqueue(T val) {
        Node<T>* newNode = new Node<T>(val);
        while (true) {
            Node<T>* t = tail.load();
            Node<T>* next = t->next.load();
            if (t == tail.load()) { // 确保读到的 tail 没有变化
                if (next == nullptr) {
                    // 尝试将新节点接在队尾
                    if (t->next.compare_exchange_weak(next, newNode)) {
                        // 成功后，尝试将 tail 指向新节点 (如果失败说明其他线程帮忙做了)
                        tail.compare_exchange_weak(t, newNode);
                        return;
                    }
                } else {
                    // tail 滞后了，帮忙推进 tail
                    tail.compare_exchange_weak(t, next);
                }
            }
        }
    }

    bool dequeue(T& result) {
        while (true) {
            Node<T>* h = head.load();
            Node<T>* t = tail.load();
            Node<T>* next = h->next.load();
            if (h == head.load()) {
                if (h == t) {
                    if (next == nullptr) return false; // 队列为空
                    // tail 滞后，帮忙推进
                    tail.compare_exchange_weak(t, next);
                } else {
                    result = next->data;
                    // 将 head 指向下一个节点 (逻辑删除 dummy 节点)
                    if (head.compare_exchange_weak(h, next)) {
                        // 注：此处为了学习和避免复杂的ABA问题处理，不主动 delete h
                        return true;
                    }
                }
            }
        }
    }
};

// === 2. 有锁队列实现 (对比基准) ===
template<typename T>
class MutexQueue {
    std::queue<T> q;
    std::mutex mtx;
public:
    void enqueue(T val) {
        std::lock_guard<std::mutex> lock(mtx);
        q.push(val);
    }
    bool dequeue(T& result) {
        std::lock_guard<std::mutex> lock(mtx);
        if (q.empty()) return false;
        result = q.front();
        q.pop();
        return true;
    }
};

// === 3. 单元测试与性能对比 ===
void test_correctness() {
    LockFreeQueue<int> lfq;
    lfq.enqueue(1); lfq.enqueue(2);
    int val;
    assert(lfq.dequeue(val) && val == 1);
    assert(lfq.dequeue(val) && val == 2);
    assert(!lfq.dequeue(val));
    std::cout << "[C++] 单元测试: 无锁队列正确性测试通过!\n";
}

template<typename Queue>
void perf_test(const std::string& name) {
    Queue q;
    const int NUM_THREADS = 4;
    const int NUM_OPS = 100000;
    
    auto start = std::chrono::high_resolution_clock::now();
    std::vector<std::thread> threads;
    for (int i = 0; i < NUM_THREADS; ++i) {
        threads.emplace_back([&]() {
            for (int j = 0; j < NUM_OPS; ++j) {
                q.enqueue(j);
                int val; q.dequeue(val);
            }
        });
    }
    for (auto& t : threads) t.join();
    auto end = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double, std::milli> elapsed = end - start;
    std::cout << "[C++] 性能测试: " << name << " 耗时: " << elapsed.count() << " ms\n";
}

int main() {
    test_correctness();
    perf_test<MutexQueue<int>>("MutexQueue");
    perf_test<LockFreeQueue<int>>("LockFreeQueue");
    return 0;
}
```

**编译与运行:**
```bash
g++ -std=c++11 -pthread main.cpp -o main && ./main
```

---

## 4. Python 实现与对比测试

Python 的 GIL（全局解释器锁）使得线程在任何时刻只有一个在执行，且 Python 标准库并未暴露原生的原子 CAS 指令。
为了**学习无锁算法的逻辑**，我们在 Python 中利用 Lock 构建了一个 `AtomicReference`，仅仅是为了模拟出原子的 `compare_and_set` 方法，这在真实工程中并不高效，但能完美展现算法骨架。得益于 Python 的垃圾回收，我们也不必像 C++ 一样担心手动回收造成的 ABA 问题。

### 代码实现 (main.py)

```python
import threading
import time
import queue

# === 1. 利用锁来模拟底层的原子操作 (仅为学习算法逻辑) ===
class AtomicReference:
    def __init__(self, value=None):
        self.value = value
        # 这里的锁仅用于模拟 CPU 级别的原子 CAS 指令
        self.lock = threading.Lock()
        
    def get(self):
        return self.value
        
    def compare_and_set(self, expect, update):
        with self.lock:
            # 必须使用 is 判断对象同一性，避免值相同的 ABA
            if self.value is expect:
                self.value = update
                return True
            return False

class Node:
    def __init__(self, data):
        self.data = data
        self.next = AtomicReference(None)

# === 2. 无锁队列实现 (Michael-Scott 算法 Python 版) ===
class LockFreeQueue:
    def __init__(self):
        dummy = Node(None)
        self.head = AtomicReference(dummy)
        self.tail = AtomicReference(dummy)
        
    def enqueue(self, val):
        new_node = Node(val)
        while True:
            t = self.tail.get()
            nxt = t.next.get()
            if t is self.tail.get(): # double check
                if nxt is None:
                    # 尝试挂接到队尾
                    if t.next.compare_and_set(nxt, new_node):
                        # 尝试推进 tail
                        self.tail.compare_and_set(t, new_node)
                        return
                else:
                    # 帮别的线程推进 tail
                    self.tail.compare_and_set(t, nxt)
                    
    def dequeue(self):
        while True:
            h = self.head.get()
            t = self.tail.get()
            nxt = h.next.get()
            if h is self.head.get():
                if h is t: # 队列空或 tail 落后
                    if nxt is None:
                        return None, False
                    self.tail.compare_and_set(t, nxt)
                else:
                    result = nxt.data
                    if self.head.compare_and_set(h, nxt):
                        return result, True

# === 3. 有锁队列实现 (对比基准) ===
class MutexQueue:
    def __init__(self):
        self.q = queue.Queue()
    def enqueue(self, val):
        self.q.put(val)
    def dequeue(self):
        try:
            return self.q.get_nowait(), True
        except queue.Empty:
            return None, False

# === 4. 单元测试与性能对比 ===
def test_correctness():
    lfq = LockFreeQueue()
    lfq.enqueue(1)
    lfq.enqueue(2)
    val, ok = lfq.dequeue()
    assert ok and val == 1
    val, ok = lfq.dequeue()
    assert ok and val == 2
    _, ok = lfq.dequeue()
    assert not ok
    print("[Python] 单元测试: 无锁队列正确性测试通过!")

def perf_test(queue_class, name):
    q = queue_class()
    num_threads = 4
    num_ops = 20000

    def worker():
        for _ in range(num_ops):
            q.enqueue(1)
            q.dequeue()

    threads = [threading.Thread(target=worker) for _ in range(num_threads)]
    start = time.time()
    for t in threads: t.start()
    for t in threads: t.join()
    end = time.time()
    print(f"[Python] 性能测试: {name} 耗时: {(end - start) * 1000:.2f} ms")

if __name__ == "__main__":
    test_correctness()
    perf_test(MutexQueue, "MutexQueue")
    perf_test(LockFreeQueue, "LockFreeQueue")
```

**运行:**
```bash
python main.py
```

---

## 5. 总结
- **C++ 性能：** 你会发现在多线程高并发的情况下，纯粹的互斥锁会因为反复的系统级挂起唤醒导致较大的开销；而无锁队列因为其 CAS 操作始终在用户态忙轮询（Spin），其并发吞吐量通常表现优异。
- **Python 表现：** 因为 Python 中我们用 Lock 来模拟底层的 CAS，而且 Python 有全局解释器锁 (GIL) 的限制，无锁队列的模拟实现并不会比直接用原生 `queue.Queue` 快，但这完美展示了其算法内核！
- **适用场景：** 无锁数据结构主要在追求**极高并发吞吐**、**低延迟**（例如高频交易、游戏服务器网络层底层等）的场景下被使用。对于日常业务逻辑，通常推荐直接使用标准库提供的带锁同步原语，因为手写无锁数据结构极易出现难以排查的 Bug。