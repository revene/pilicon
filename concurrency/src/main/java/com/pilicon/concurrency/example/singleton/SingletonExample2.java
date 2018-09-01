package com.pilicon.concurrency.example.singleton;

import com.pilicon.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉模式
 * 单例实例在类装载使用时创建
 */
@ThreadSafe
@Slf4j
//因为在类庄装载时创建,所以是线程安全的,但是如果用了饿汉模式,却没有用这个对象,会浪费资源,性能也会
//有些差,再构造函数比较复杂的时候,因为在类装载的时候就加载实例了
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2(){

    }

    //直接类装载时创建
    private static SingletonExample2 instance = new SingletonExample2();

    //给一个返回的方法
    public SingletonExample2 getInstance(){
        return instance;
    }

}
