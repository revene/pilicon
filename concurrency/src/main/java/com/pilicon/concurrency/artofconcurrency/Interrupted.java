package com.pilicon.concurrency.artofconcurrency;

import java.util.concurrent.TimeUnit;

public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        //sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(),"睡眠线程");
        sleepThread.setDaemon(true);
        //busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(),"BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        //休眠5秒,让busyThread 和 sleepThread 充分运行
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());

        //防止sleepThread和busyThread立刻退出
        TimeUnit.SECONDS.sleep(5);
    }

    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    int i = Integer.parseInt("1000");
                    Integer integer = Integer.valueOf(1000);
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }
}
