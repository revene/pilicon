package com.pilicon.concurrency.singleton;

import com.pilicon.concurrency.annotations.NotThreadSafe;
import com.pilicon.concurrency.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

@NotThreadSafe
@Slf4j
@Recommend
public class SingletonExample4 {

    //私有的构造函数(只有构造函数私有了 外面的对象才不能随便的去new 对象)
    private SingletonExample4(){

    }

    // new 命令
//    1.memory = allocate() 分配对象内存空间
//    2.ctorInstance() 初始化对象
//    3.instance = memory 设置instance指向刚分配的内存

//    jvm和cpu指令进行了指令重排 变成了 1 3 2
    //线程1已经走到了synchronsizd中的第3步 而线程2 刚刚进入第一个判断,不符合条件,直接return 但实际上对象还没有完成构造

    //不让他发生指令重拍就行  volatile

    //单例对象
    private volatile  static SingletonExample4 instance = null;

    //静态的工厂方法获取一个单例对象,
    public static SingletonExample4 getInstance(){
        //注意,此处线程不安全,两个线程都拿到了instance
        if (instance == null){
            //加锁,这个类型的所有对象都只能一个进去
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
