package com.xforchen.demo.thread;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xforchen
 * @date 2020/11/11 下午10:34
 */
public class Homework03 {
    
    private static ExecutorService executor;

    private static int result = 0;

    private static volatile AtomicInteger semaphore = new AtomicInteger(0);

    public static void main(String[] args) {

        long start=System.currentTimeMillis();

        executor = Executors.newCachedThreadPool();

        try {
            System.out.println("异步计算结果为：" + fun10());
            System.out.println("使用时间："+ (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static int sum() {
        return fib(36);
    }

    private static int fib(int a) {
        if ( a < 2) {
            return 1;
        }
        return fib(a-1) + fib(a-2);
    }

    public static int fun1() {
        return CompletableFuture.supplyAsync(() -> sum()).join();
    }

    public static int fun2() throws ExecutionException, InterruptedException {
        FutureTask<Integer> result = new FutureTask<>(Homework03::sum);
        new Thread(result).start();
        return result.get();
    }

    public static int fun3() throws ExecutionException, InterruptedException {
        FutureTask<Integer> result = new FutureTask<>(() -> sum());
        executor.submit(result);
        return result.get();
    }

    public static int fun4() throws ExecutionException, InterruptedException {
        Future<Integer> result = executor.submit(Homework03::sum);
        return result.get();
    }

    public static int fun5() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        executor.execute(() -> {
            result = sum();
            countDownLatch.countDown();
        });
        countDownLatch.await();
        return result;
    }

    public static int fun6() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        executor.execute(() -> {
            try {
                result = sum();
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        cyclicBarrier.await();
        return result;
    }

    public static int fun7() throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        executor.execute(() -> {
            result = sum();
            semaphore.release();
        });
        semaphore.acquire();
        semaphore.release();
        return result;
    }

    public static int fun8() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue();
        executor.execute(() -> {
            result = sum();
            queue.add(result);
        });
        queue.take();
        return result;
    }

    public static int fun9() {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        executor.execute(() -> {
            result = sum();
            map.put(result, result);
        });
        while (map.size() == 0) {
        }
        return result;
    }

    public static int fun10() {
        executor.execute(() -> {
            result = sum();
            semaphore.addAndGet(1);
        });
        while (semaphore.intValue() == 0) {
        }
        return result;
    }
}

