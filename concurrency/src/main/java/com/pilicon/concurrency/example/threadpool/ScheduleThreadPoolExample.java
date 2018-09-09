package com.pilicon.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleThreadPoolExample {
    public static void main(String[] args) {
        //调度线程池的返回结果会有些不同,这里返回ScheduledExecutorService
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        for (int i = 0 ; i < 10 ; i++){
            final int index = i;
            scheduledExecutorService.execute(()->{
                log.info("task{}",index);
            });
        }

        //延期调用的方法,指定延期调用的时间,和单位
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("这是个延期执行的任务");
            }
        },5,TimeUnit.SECONDS);

        //以指定的速率去执行任务
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("这是以一定的时间去执行任务");
            }
        },1,3,TimeUnit.SECONDS);


        scheduledExecutorService.shutdown();
    }
}
