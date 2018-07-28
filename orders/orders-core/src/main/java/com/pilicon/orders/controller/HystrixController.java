package com.pilicon.orders.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
//配置这个类里面的全局服务降级配置
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    //定义单个的服务降价
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(value = "getProductInfoList",method = RequestMethod.POST)
    public String getProductInfoList(){
        RestTemplate restTemplate = new RestTemplate();
        String object = restTemplate.postForObject("http://localhost:8700/product/listForOrder", Arrays.asList("157875196366160022"), String.class);
        return object;
    }

    private String fallback(){
        return "太拥挤了,嘻嘻嘻嘻";
    }


    private String defaultFallback(){
        return "默认提示,太拥挤了";
    }

}
