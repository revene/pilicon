package com.pilicon.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {
    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("此线程正在执行任务,花费5s");
            TimeUnit.MILLISECONDS.sleep(5000);
            return "任务完成了";
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("正在执行主线程的任务,预计用时1s");
        TimeUnit.MILLISECONDS.sleep(1000);
        //阻塞知道获取结果
        String s = future.get();
        log.info("新开线程执行的结果是:{}",s);
    }

}
