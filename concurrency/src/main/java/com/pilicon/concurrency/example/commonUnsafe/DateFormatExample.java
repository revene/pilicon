package com.pilicon.concurrency.example.commonUnsafe;

import com.pilicon.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class DateFormatExample {

    //按照年月日的方式进行日期转换 这样在成员变量中使用是非线程安全的
    //private static SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyyMMdd");

    private static int clientTotal = 5000 ;

    private static int threadTotal = 200 ;

    //使用SimpleDateFormat在多线程的情况下,会出现异常的
    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ;i < 5000; i++){
            try {
                semaphore.acquire();
                update();
                semaphore.release();
            } catch (InterruptedException e) {
                log.error("嘻嘻,出问题了");
            }
            countDownLatch.countDown();
        }

        countDownLatch.await();
        log.info("执行完毕");
        executorService.shutdown();

    }


    public static void update(){
        try {
            //堆栈封闭的写法确保了线程安全
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20180208");
        } catch (ParseException e) {
            log.error("parse exception");
        }
    }
}
