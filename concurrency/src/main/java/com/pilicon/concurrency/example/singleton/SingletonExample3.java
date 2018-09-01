package com.pilicon.concurrency.example.singleton;

import com.pilicon.concurrency.annotations.NotRecommend;
import com.pilicon.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@ThreadSafe
@Slf4j
@NotRecommend
//缺点,synchronzied加在了方法上粒度太粗,性能不好
public class SingletonExample3 {

    //私有的构造函数(只有构造函数私有了 外面的对象才不能随便的去new 对象)
    private SingletonExample3(){

    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法获取一个单例对象
    public static synchronized SingletonExample3 getInstance(){
        //注意,此处线程不安全,两个线程都拿到了instance
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
