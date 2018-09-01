package com.pilicon.concurrency.example.commonUnsafe;

import com.pilicon.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@Slf4j
@ThreadSafe
public class JodaTimeExample {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

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
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        log.info("执行完毕,嘻嘻");
        executorService.shutdown();

    }

    public static void update(){
        DateTime.parse("20180808",dateTimeFormatter);
    }
}
