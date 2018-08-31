package com.pilicon.diveinspringboot.autoconfiguration.service.impl;

import com.pilicon.diveinspringboot.autoconfiguration.service.CalculateService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * java 7 for循环实现
 */
@Profile("java8")
@Service
public class Java8CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("java8 Lambda 表达式实现");
        int sum = Stream.of(values).reduce(0,Integer::sum);

        return sum;
    }


    public static void main(String[] args) {
        CalculateService calculateService = new Java8CalculateServiceImpl();
        System.out.println(calculateService.sum(1,2,3,4,5,6,7,8,9,10));
    }
}
