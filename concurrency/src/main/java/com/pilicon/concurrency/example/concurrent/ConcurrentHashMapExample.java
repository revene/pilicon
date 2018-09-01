package com.pilicon.concurrency.example.concurrent;

import com.pilicon.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static Map<Integer,Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(200);

        final CountDownLatch countDownLatch = new CountDownLatch(5000);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ; i < clientTotal ; i++){
            final int j = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    update(j);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("嘻嘻,出错了");
                }
                countDownLatch.countDown();
            } );
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("嘻嘻,执行完了");
        log.info("最后map的size是{}",map.size());
    }

    public static void update(int j){
        map.put(j,j);
    }
}
