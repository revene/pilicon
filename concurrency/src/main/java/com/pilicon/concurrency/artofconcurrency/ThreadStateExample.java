package com.pilicon.concurrency.artofconcurrency;

import java.util.concurrent.TimeUnit;

public class ThreadStateExample {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingthread").start();
        new Thread(new Waiting(),"Waitingthread").start();
        //使用两个Block线程,一个获取锁成功,另一个因为获取不到而进入阻塞状态
        new Thread(new Blocked(),"BlockThread-1").start();
        new Thread(new Blocked(),"BlockThread-2").start();
    }


    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //调用wait方法进入了WAIT状态,只有等待另外线程的通知或被终端才返回,且wait操作释放锁
    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
           synchronized (Blocked.class){
               while (true){
                   try {
                       TimeUnit.SECONDS.sleep(5);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
        }
    }
}
