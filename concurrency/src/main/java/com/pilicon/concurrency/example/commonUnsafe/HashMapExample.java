package com.pilicon.concurrency.example.commonUnsafe;


import com.pilicon.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class HashMapExample {

    //请求总数
    private static int clientTotal = 5000 ;

    //并发线程数
    private static int threadTotal = 200;

    private static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ; i < clientTotal ; i++){
            //从lambda表达式引用的本地变量必须是final变量
            final int j = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(j);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("嘻嘻,出错了");
                }
                countDownLatch.countDown();
                });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("嘻嘻,执行完毕了");
        log.info("执行玩后,map的大小是{}",map.size());
    }

    public static void update(int j ){
        map.put(j,j);
    }
}
