package com.pilicon.concurrency.example.atomic;

import com.pilicon.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
@Slf4j
public class AtomicBooleanExample {

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private static int totalClient = 200;

    private static int totalCount = 5000;

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(totalClient);
        final CountDownLatch countDownLatch = new CountDownLatch(totalCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0 ; i<totalCount ; i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("出错了",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("最终结果");
        System.out.println(atomicBoolean.get());
    }

    private static void test(){
        if (atomicBoolean.compareAndSet(false,true)){
            log.info("execute");
        };
    }
}
