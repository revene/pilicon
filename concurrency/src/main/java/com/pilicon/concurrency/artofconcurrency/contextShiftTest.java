package com.pilicon.concurrency.artofconcurrency;

import org.springframework.util.StopWatch;

import java.io.FileNotFoundException;

/**
 * 测试并行和串行执行代码的速度对比
 * 最终执行的结果如下
 * 1万次调用       并行1ms   串行0ms
 * 10万次调用      并行4ms   串行3ms
 * 100万次调用     并行6ms   串行8ms
 * 1000万次调用    并行14ms  串行33ms
 * 1亿次调用       并行88ms  串行199ms
 */
public class contextShiftTest {
    private static final long count = 10001;

    private static void concurrency() throws InterruptedException, FileNotFoundException {
        //使用stopWatch进行计时
        StopWatch stopWatch = new StopWatch("并行计算");
        stopWatch.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0 ;
                for (long i = 0 ; i < count ;i++){
                    a=+5;
                }
            }
        });
        thread.start();

        int b = 0 ;
        for (long i = 0 ; i< count ; i++){
            b--;
        }
        //让出此时正在运行的线程,直到thread完成,变并行为串行
        thread.join();
        stopWatch.stop();
        String s = stopWatch.prettyPrint();
        System.out.println(s);
        int a ;
    }


    private static void serial(){
        StopWatch stopWatch = new StopWatch("串行计算");
        stopWatch.start();
        int a = 0 ;
        for (long i = 0 ; i < count ; i++){
            a += 5 ;
        }
        int b = 0 ;
        for (long i = 0 ; i < count ; i++){
            b--;
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        concurrency();
        serial();
    }
}

