package com.pilicon.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicExample5 {

    //原子性的去更新某一个类的实例的某个字段
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;


    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5,100,120)){
            log.info("update success,{}",example5.getCount());
        }

        if (updater.compareAndSet(example5,100,120)){
            log.info("update success,{}",example5.getCount());
        }else {
            log.info("update fail:{}",example5.getCount());
        }
    }
}
