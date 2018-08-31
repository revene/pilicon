package com.pilicon.lamda.test;

import org.springframework.boot.Banner;

import java.util.List;
import java.util.stream.IntStream;

public class StreamDemo1 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        //外部迭代 串行的 单线程, 如果性能不达标的话 ,还要自己搞个线程池操作
        int sum = 0 ;
        for (int i : nums){
            sum += i;
        }
        System.out.println("求和的结果为sum"+sum);


        //使用stream的内部迭代 不关注怎么做 告诉他做什么就行了 可以用并行流的方式解决性能的问题
        int sum1 = IntStream.of(nums).sum();
        System.out.println("求和的结果是sum"+sum1);

        
    }


}
