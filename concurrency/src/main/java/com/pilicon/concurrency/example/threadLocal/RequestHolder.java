package com.pilicon.concurrency.example.threadLocal;

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    //请求进入后端服务器但是没有进行实际处理的时候,将用户信息放进去,过滤器的位置放,以后就可以取出来了用
    public static void add(Long id){
        //key 是当前线程 id是value
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        //如果不设置移除的方法,会导致内存泄漏,这个类会一直伴随着项目,除非重启,才会清空
        requestHolder.remove();
    }

    public static void main(String[] args) {

    }
}
