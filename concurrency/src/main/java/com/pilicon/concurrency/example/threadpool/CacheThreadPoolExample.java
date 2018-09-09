package com.pilicon.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CacheThreadPoolExample {
    public static void main(String[] args) {

        //第一种线程池,缓冲线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ; i < 10 ; i++){
            final int index = i;
            executorService.execute(()->{
                log.info("task{}",index);
            });
        }
        executorService.shutdown();
    }
}
