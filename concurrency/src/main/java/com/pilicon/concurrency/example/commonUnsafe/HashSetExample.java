package com.pilicon.concurrency.example.commonUnsafe;

import com.pilicon.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class HashSetExample {

    //请求总数
    private static int clientTotal = 5000;

    //并发控制总数
    private static int threadTotal = 200;

    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(5000);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ; i < clientTotal ; i++){
            final int j = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(j);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("嘻嘻,不好意思出错了");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("嘻嘻,执行完了");
        log.info("执行完成后的size的大小是: {}" , set.size());
    }

    public static void update(int j ){
        set.add(j);
    }
}
