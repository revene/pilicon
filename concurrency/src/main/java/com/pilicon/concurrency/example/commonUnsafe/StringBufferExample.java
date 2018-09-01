package com.pilicon.concurrency.example.commonUnsafe;

import com.pilicon.concurrency.annotations.NotThreadSafe;
import com.pilicon.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class StringBufferExample {

    //请求总数
    public static int clientTotal = 5000;

    //同时执行的并发数
    public static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer() ;

    //如果是线程安全的话,打印出来的size应该是5000,但是线程不安全,所以结果是达不到5000的
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ;i < 5000 ; i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("嘻嘻,出错了");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("执行完毕");
        executorService.shutdown();
        log.info("size:{}",stringBuffer.toString().length());

    }

    public static void update(){
        stringBuffer.append("1");
    }

}
