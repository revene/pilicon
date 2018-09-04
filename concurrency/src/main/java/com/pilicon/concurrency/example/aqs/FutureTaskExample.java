package com.pilicon.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("此线程正在执行任务,花费5s");
                TimeUnit.MILLISECONDS.sleep(5000);
                return "任务完成了";
            }
        });

        new Thread(futureTask).start();
        log.info("正在执行main函数的任务,预计用时1s");
        TimeUnit.MILLISECONDS.sleep(1000);
        String result = futureTask.get();
        log.info("新开线程的执行结果是:{}",result);

        Class<? extends FutureTask> taskClass = futureTask.getClass();
        Method main = taskClass.getDeclaredMethod("main", new Class[]{String[].class});
        main.invoke(new FutureTaskExample(),null);
    }
}
