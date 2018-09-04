package com.pilicon.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample {

    private final static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);

        for (int i = 0 ; i < threadTotal ; i++){
            final int threadNum = i ;
            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }finally {
                    //为了保证countdown方法一定执行,放在finally里面调用的
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        log.info("嘻嘻,全部都执行完了");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("{}",threadNum);
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
