package com.pilicon.diveinspringboot.service.impl;

import com.pilicon.diveinspringboot.service.CalculateService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * java 7 for循环实现
 */
@Profile("java7")
@Service
public class Java7CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("java7 for 循环实现");
        int sum = 0;
        for (int i = 0 ; i < values.length ; i++){
            sum += values[i];

        }
        return sum;
    }


    public static void main(String[] args) {
        CalculateService calculateService = new Java7CalculateServiceImpl();
        System.out.println(calculateService.sum(1,2,3,4,5,6,7,8,9,10));
    }
}
