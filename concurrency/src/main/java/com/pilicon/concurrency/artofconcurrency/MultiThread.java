package com.pilicon.concurrency.artofconcurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 打印启动main方法所用到的所有线程
 */
public class MultiThread {
    public static void main(String[] args) {
        System.out.println("Hello World");
        //获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //获取线程和线程堆栈信息,不需要获取monitor和synchronizer信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        //遍历线程信息,打印出线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos){
            System.out.println("线程id是: " + threadInfo.getThreadId() + "  线程名称是: " + threadInfo.getThreadName());
        }
    }
}
