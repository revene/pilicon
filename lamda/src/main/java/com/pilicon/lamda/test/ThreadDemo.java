package com.pilicon.lamda.test;

public class ThreadDemo {
    public static void main(String[] args) {
        //jdk7
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ok");
            }
        }).start();




        //jdk8 lambda
        new Thread(()-> System.out.println("ok"));
    }
}
