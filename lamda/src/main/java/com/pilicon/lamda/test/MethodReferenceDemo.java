package com.pilicon.lamda.test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class Dog{
    private String name = "啸天犬";
    private int food = 10;

    public static void bark(Dog dog){
        System.out.println(dog+"叫了");
    }
    public int eat(int num){
        System.out.println("吃了:"+num + "狗粮");
        this.food-=num;
        return this.food;

    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodReferenceDemo {
    public static void main(String[] args) {
        //方法引用
        Consumer<String> consumer =System.out::println;
        consumer.accept("接收的shuju");

        //静态方法的方法引用
        Consumer<Dog> dogConsumer = Dog::bark;
        Dog dog = new Dog();
        dogConsumer.accept(dog);

        //非静态方法,使用对象实例来引用
        Function<Integer,Integer> function = dog::eat;
        Integer apply = function.apply(5);
        System.out.println("还剩"+ apply + "斤");


        //构造函数的方法引用,构造函数可以理解为静态方法
//        Dog::new
        Supplier<Dog> dogSupplier = Dog::new;
        System.out.println("创建了新的对象" + dogSupplier.get());
    }
}
