package com.pilicon.concurrency.thinkinginjava;

import java.util.concurrent.TimeUnit;

public class SimpleDaemon implements Runnable{

    @Override
    public void run() {
        try {
            while (true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + "" + this);
            }
        } catch (InterruptedException e) {
            System.out.println("出错了,嘻嘻");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0 ; i<10;i++){
            Thread thread = new Thread(new SimpleDaemon());
            //必须在线程启动前设置
            thread.setDaemon(true);
            thread.start();

        }
        System.out.println("all Deamon start");
        //主线程休眠175ms
        TimeUnit.MILLISECONDS.sleep(175);

    }
}
