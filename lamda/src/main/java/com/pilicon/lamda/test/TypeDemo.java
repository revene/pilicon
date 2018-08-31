package com.pilicon.lamda.test;


@FunctionalInterface
interface IMath{
    int add(int x , int y);
}
public class TypeDemo {

    public static void main(String[] args) {

        //变量类型定义
        IMath iMath = (x,y)->x+y;
        //数组里
        IMath[] iMaths = {(x,y)->x+y};

        //强转
        Object lambda2 = (IMath)(x,y)->x+y;


        //通过返回类型
        IMath lamdba = createLamdba();
    }

    public static IMath createLamdba(){
        return (x,y)->x+y;
    }
}
