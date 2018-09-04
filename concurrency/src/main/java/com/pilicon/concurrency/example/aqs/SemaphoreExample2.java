package com.pilicon.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample2 {


    private final static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);


        for (int i = 0 ; i < threadTotal ; i++){
            final int threadNum = i ;
            executorService.execute(()->{
                try {
                    //尝试获取许可
                    if (semaphore.tryAcquire()) {
                        test(threadNum);
                        semaphore.release();  //释放一个许可
                    }
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }finally {

                }
            });
        }

        log.info("嘻嘻,正在用semaphorr抢资源");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
