package com.pilicon.lamda.test;


interface Interface1{
    int doubleNum(int i);
}
public class lamdaDemo1 {

    public static void main(String[] args) {
        //lambda表达式的4中写法

        Interface1 interface1 = (i)-> i*2;

        // 这种就是最常见的写法,当入参只有一个的情况下,可以省略掉括号
        Interface1 interface2 = i-> i*2;

        Interface1 interface3 = (int i)-> i*2;

        Interface1 interface4 = (int i)-> {
            System.out.println("-------------");
            return i * 2;
        };
    }
}
