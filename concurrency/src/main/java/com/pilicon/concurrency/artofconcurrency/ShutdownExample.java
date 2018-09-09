package com.pilicon.concurrency.artofconcurrency;

import java.util.concurrent.TimeUnit;

public class ShutdownExample {

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThreadOne");
        countThread.start();
        //睡眠一秒,给countThread充分的执行时间,然后main线程再中断countThread,使CountThread能感受到中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two,"CountThreadTwo");
        countThread.start();
        //睡眠一秒,给countThread充分的执行时间,然后main线程再中断countThread,时CountThread能感受on变成了false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel();

    }

    private static class Runner implements Runnable{

        private long i ;

        private volatile boolean on = true;

        @Override
        public void run() {
            //当开关为on 且 当前线程的中断符号为false时 才操作
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i  = " + i );
        }

        public void cancel(){
            on = false;
        }
    }
}
