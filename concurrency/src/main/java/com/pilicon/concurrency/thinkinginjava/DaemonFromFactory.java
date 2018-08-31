package com.pilicon.concurrency.thinkinginjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable{


    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread()+ "" + this);
            } catch (InterruptedException e) {
                System.out.println("嘻嘻,出错了");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonFactory());
        for (int i = 0 ; i< 5;i++){
            executorService.execute(new DaemonFromFactory());
        }
        System.out.println("all Daemon started");
        //主线程休息500ms
        TimeUnit.MILLISECONDS.sleep(111111111);
    }
}
