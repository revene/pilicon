package com.pilicon.concurrency.artofconcurrency;

import java.util.concurrent.TimeUnit;

public class DaemonExample {

    public static void main(String[] args) {
            Thread deamonThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                            System.out.println("这是一个后台线程");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            deamonThread.setDaemon(true);
            deamonThread.start();
            while (true){

            }
    }
}
