package com.pilicon.concurrency.thinkinginjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0 ; i < 5 ; i++){
            executorService.execute(new LiftOff());
            System.out.println("嘻嘻");
        }
        executorService.shutdown();
    }
}
