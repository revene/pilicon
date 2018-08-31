package com.pilicon.concurrency.thinkinginjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0 ;i < 5 ; i++){
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
