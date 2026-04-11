#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <queue>
#include <atomic>
#include <chrono>
#include <cassert>

// 有锁队列 (Lock-Based Queue)
template <typename T>
class LockBasedQueue {
private:
    std::queue<T> q;
    std::mutex mtx;

public:
    void push(const T& value) {
        std::lock_guard<std::mutex> lock(mtx);
        q.push(value);
    }

    bool pop(T& value) {
        std::lock_guard<std::mutex> lock(mtx);
        if (q.empty()) {
            return false;
        }
        value = q.front();
        q.pop();
        return true;
    }
};

// 无锁队列 (Lock-Free Queue - SPSC Ring Buffer)
// 适用于单生产者单消费者模式，作为最小可执行单元进行学习
template <typename T, size_t Size>
class LockFreeQueue {
private:
    T buffer[Size];
    std::atomic<size_t> head{0};
    std::atomic<size_t> tail{0};

public:
    bool push(const T& value) {
        size_t current_tail = tail.load(std::memory_order_relaxed);
        size_t next_tail = (current_tail + 1) % Size;
        
        // 如果 next_tail 追上 head，说明队列已满
        if (next_tail == head.load(std::memory_order_acquire)) {
            return false;
        }
        buffer[current_tail] = value;
        tail.store(next_tail, std::memory_order_release);
        return true;
    }

    bool pop(T& value) {
        size_t current_head = head.load(std::memory_order_relaxed);
        
        // 如果 head 等于 tail，说明队列为空
        if (current_head == tail.load(std::memory_order_acquire)) {
            return false;
        }
        value = buffer[current_head];
        head.store((current_head + 1) % Size, std::memory_order_release);
        return true;
    }
};

// --- 单元测试 ---
void test_lock_based() {
    LockBasedQueue<int> q;
    q.push(1);
    q.push(2);
    int v;
    assert(q.pop(v) && v == 1);
    assert(q.pop(v) && v == 2);
    assert(!q.pop(v));
    std::cout << "[单元测试] 有锁队列测试通过。\n";
}

void test_lock_free() {
    LockFreeQueue<int, 10> q;
    q.push(1);
    q.push(2);
    int v;
    assert(q.pop(v) && v == 1);
    assert(q.pop(v) && v == 2);
    assert(!q.pop(v));
    std::cout << "[单元测试] 无锁队列测试通过。\n";
}

// --- 性能对比测试 ---
void benchmark() {
    const int NUM_ITEMS = 5000000;
    
    // 1. 有锁队列测试
    {
        LockBasedQueue<int> q;
        auto start = std::chrono::high_resolution_clock::now();
        
        std::thread producer([&]() {
            for (int i = 0; i < NUM_ITEMS; ++i) {
                q.push(i);
            }
        });
        
        std::thread consumer([&]() {
            int v;
            for (int i = 0; i < NUM_ITEMS; ) {
                if (q.pop(v)) {
                    i++;
                }
            }
        });
        
        producer.join();
        consumer.join();
        auto end = std::chrono::high_resolution_clock::now();
        auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count();
        std::cout << "[性能测试] 有锁队列耗时: " << duration << " ms\n";
    }

    // 2. 无锁队列测试
    {
        // 确保队列容量足够大以容纳数据 (+1 是因为环形缓冲区的特性)
        LockFreeQueue<int, NUM_ITEMS + 1> q;
        auto start = std::chrono::high_resolution_clock::now();
        
        std::thread producer([&]() {
            for (int i = 0; i < NUM_ITEMS; ) {
                // 如果队列满了会返回false，我们需要重试直到放入
                if (q.push(i)) {
                    i++;
                }
            }
        });
        
        std::thread consumer([&]() {
            int v;
            for (int i = 0; i < NUM_ITEMS; ) {
                // 如果队列空了会返回false，我们需要重试直到取出
                if (q.pop(v)) {
                    i++;
                }
            }
        });
        
        producer.join();
        consumer.join();
        auto end = std::chrono::high_resolution_clock::now();
        auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count();
        std::cout << "[性能测试] 无锁队列耗时: " << duration << " ms\n";
    }
}

int main() {
    std::cout << "--- 开始运行无锁数据结构学习示例 (C++) ---\n";
    test_lock_based();
    test_lock_free();
    
    std::cout << "\n--- 开始性能对比测试 ---\n";
    benchmark();
    return 0;
}
