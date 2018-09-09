package com.pilicon.concurrency.artofconcurrency;

import java.util.concurrent.TimeUnit;

public class WaitNotifyExample {
    private static boolean flag = true;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(),"WaitThread");
        waitThread.start();

        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            //加锁,拥有lock的Monitor
            synchronized (lock){
                //当条件不满足时,继续wait,同事释放了lock的锁
                while (flag){
                    try {
                        System.out.println(Thread.currentThread()+"flag is true , wait");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //条件满足时,完成工作
                System.out.println(Thread.currentThread()+"flag is false .running");
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            //加锁,拥有lock的Monitor
            synchronized (lock){
                //获取lock的锁,然后进行同志,通知时不会释放lock的锁
                //直到当前线程释放了lock之后,WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + "hold lock.notify");
                lock.notifyAll();
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //再次加锁
            synchronized (lock){
                System.out.println(Thread.currentThread() + "hold lock again");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
