package com.pilicon.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SingleThreadExecutorExample {

    public static void main(String[] args) {
//        使用single线程池,实际上任务是会按照顺序来执行的
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0 ; i < 10 ; i++){
            final int index = i;
            executorService.execute(()->{
                log.info("task{}",index);
            });
        }
        executorService.shutdown();
    }
}

