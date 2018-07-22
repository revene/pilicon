package com.pilicon.concurrency.singleton;

import com.pilicon.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式,单例的实例在第一次使用的时候被创建
 */
@NotThreadSafe
@Slf4j
public class SingletonExample {

    //私有的构造函数(只有构造函数私有了 外面的对象才不能随便的去new 对象)
    private SingletonExample(){

    }

    //单例对象
    private static SingletonExample instance = null;

    //静态的工厂方法获取一个单例对象,
    public static SingletonExample getInstance(){
        //注意,此处线程不安全,两个线程都拿到了instance
        if (instance == null){
            instance = new SingletonExample();
        }
        return instance;
    }
}
