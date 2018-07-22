package com.pilicon.concurrency.singleton;

import sun.jvm.hotspot.oops.Instance;

public class SingletonExample5 {

    private SingletonExample5(){

    }

    private static SingletonExample5 intance = null;


    static {
        intance = new SingletonExample5();
    }

    public static SingletonExample5 getInstance(){
        return intance;
    }

    public static void main(String[] args) {

    }
}
