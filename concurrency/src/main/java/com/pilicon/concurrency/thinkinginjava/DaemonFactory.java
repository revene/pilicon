package com.pilicon.concurrency.thinkinginjava;

import java.util.concurrent.ThreadFactory;

/**
 * 线程工厂,与普通的唯一区别就是设置了生成的线程都是后台线程
 */
public class DaemonFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
