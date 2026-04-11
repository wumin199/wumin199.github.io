import threading
import time
import queue
from collections import deque

# 有锁队列 (Lock-Based Queue)
class LockBasedQueue:
    def __init__(self):
        self.q = deque()
        self.lock = threading.Lock()

    def push(self, item):
        with self.lock:
            self.q.append(item)

    def pop(self):
        with self.lock:
            if not self.q:
                return None
            return self.q.popleft()

# 无锁队列 (Lock-Free Queue in Python)
# 注意：在Python中，由于GIL（全局解释器锁）的存在，真正的多核并发受到限制。
# 但是我们可以利用Python内置数据结构如 `collections.deque` 的 append 和 popleft 
# 的原子性（在GIL下）来实现线程安全且不需要显式锁定的无锁队列机制。
class LockFreeQueue:
    def __init__(self):
        self.q = deque()

    def push(self, item):
        # deque.append 在GIL保护下是原子的
        self.q.append(item)

    def pop(self):
        # deque.popleft 在GIL保护下也是原子的
        try:
            return self.q.popleft()
        except IndexError:
            # 队列为空
            return None

# --- 单元测试 ---
def test_lock_based():
    lq = LockBasedQueue()
    lq.push(1)
    lq.push(2)
    assert lq.pop() == 1, "LockBasedQueue pop error"
    assert lq.pop() == 2, "LockBasedQueue pop error"
    assert lq.pop() is None, "LockBasedQueue pop error"
    print("[单元测试] 有锁队列测试通过。")

def test_lock_free():
    lfq = LockFreeQueue()
    lfq.push(1)
    lfq.push(2)
    assert lfq.pop() == 1, "LockFreeQueue pop error"
    assert lfq.pop() == 2, "LockFreeQueue pop error"
    assert lfq.pop() is None, "LockFreeQueue pop error"
    print("[单元测试] 无锁队列测试通过。")

# --- 性能对比测试 ---
def benchmark():
    NUM_ITEMS = 1000000

    print("\n--- 开始性能对比测试 ---")
    # 1. 有锁队列测试
    lq = LockBasedQueue()
    start_time = time.time()
    
    def producer_lq():
        for i in range(NUM_ITEMS):
            lq.push(i)
            
    def consumer_lq():
        count = 0
        while count < NUM_ITEMS:
            if lq.pop() is not None:
                count += 1

    t1 = threading.Thread(target=producer_lq)
    t2 = threading.Thread(target=consumer_lq)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    print(f"[性能测试] 有锁队列耗时: {time.time() - start_time:.4f} 秒")

    # 2. 无锁队列测试
    lfq = LockFreeQueue()
    start_time = time.time()

    def producer_lfq():
        for i in range(NUM_ITEMS):
            lfq.push(i)

    def consumer_lfq():
        count = 0
        while count < NUM_ITEMS:
            if lfq.pop() is not None:
                count += 1

    t1 = threading.Thread(target=producer_lfq)
    t2 = threading.Thread(target=consumer_lfq)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    print(f"[性能测试] 无锁队列耗时: {time.time() - start_time:.4f} 秒")

if __name__ == "__main__":
    print("--- 开始运行无锁数据结构学习示例 (Python) ---")
    test_lock_based()
    test_lock_free()
    benchmark()
