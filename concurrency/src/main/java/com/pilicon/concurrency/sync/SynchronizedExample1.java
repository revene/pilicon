package com.pilicon.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    //修饰代码块 同步代码块 作用的对象是调用的对象
    public void test1(){
        synchronized (this){
            for (int i=0;i<10;i++){
                log.info("test1-{}",i);
            }
        }
    }


    //修饰一个方法 同步方法 作用的对象同样是调用的对象
    public synchronized void test2(){
        for (int i =0;i<10;i++){
            log.info("test1-{}",i);
        }
    }

    //修饰一个类  作用的对象是这个类的所有对象
    public void test3(){
        synchronized (SynchronizedExample1.class){
            for (int i=0;i<10;i++){
                log.info("test1-{}",i);
            }
        }
    }

    //修饰一个静态方法 同步方法 作用的对象是这个类的所有对象
    public static synchronized void test4(){
        for (int i =0;i<10;i++){
            log.info("test1-{}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1();
        });
        executorService.execute(()->{
            example2.test1();
        });
    }

}
