package com.pilicon.concurrency.example.commonUnsafe;

import com.pilicon.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@NotThreadSafe
@Slf4j
public class ArrayLIstExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200 ;

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ; i < clientTotal ; i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("嘻嘻,出差错了");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("嘻嘻,执行完了");
        executorService.shutdown();
        log.info("list的size是{}",list.size());
    }

    public static void update(){
        list.add(1);
    }
}
