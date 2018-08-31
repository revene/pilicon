package com.pilicon.lamda.test;

import org.springframework.boot.SpringApplication;

import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FunctionDemo {
    public static void main(String[] args) {
        //断言函数
        Predicate<Integer> predicate = i -> i>0;
        System.out.println(predicate.test(-9));

        //带类型的断言,优先使用 而不是使用泛型
        IntPredicate intPredicate = i -> i>0;
        System.out.println(predicate.test(-9));


        //消费函数接口
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输入的数据");

        SpringApplication.run(FunctionDemo.class,args);
    }
}
