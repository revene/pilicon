package com.pilicon.concurrency.artofconcurrency;

import java.util.concurrent.TimeUnit;


/**
 * 模拟死锁
 * thread1拿到A锁,使用2000ms后去尝试拿B锁
 * thread2拿到B锁,尝试去拿A锁,但是A锁此时正在尝试去拿B锁,而又因为拿不到B锁导致A锁无法释放
 * 现在线程1,2 都处于拿不到希望的锁而互相等待
 */
public class DeadLockExample {

    private static String a = "A";

    private static String b = "B";

    public static void main(String[] args) {
        new DeadLockExample().deadLock();
    }

    public void deadLock(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a){
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b){
                        System.out.println("1");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b){
                    synchronized (a){
                        System.out.println("2");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
