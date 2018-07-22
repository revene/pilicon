package com.pilicon.concurrency.immutable;

import com.google.common.collect.Maps;
import com.pilicon.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExample {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer,Integer> map = Maps.newHashMap() ;

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        map.put(1,4);
        map.put(5,10);

        //map = Maps.newHashMap();  不可 final修饰不可指向别的对象
    }

    private void test(final int a){
//        a=1
    }
}
