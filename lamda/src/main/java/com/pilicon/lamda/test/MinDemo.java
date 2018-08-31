package com.pilicon.lamda.test;

import java.util.stream.IntStream;

public class MinDemo {

    public static void main(String[] args) {

        int[] nums = {33,55,-55,90,-777,778};

        int min = Integer.MAX_VALUE;

        for (int i =0;i<nums.length;i++){
            if (i< min){
                min = i;
            }
        }

        System.out.println(min);


        //jdk 8 函数式编程
        int min2 = IntStream.of(nums).min().getAsInt();
        System.out.println(min2);
    }


}
