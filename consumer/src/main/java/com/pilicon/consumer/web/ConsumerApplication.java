package com.pilicon.consumer.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumerApplication {

    @LoadBalanced
    private RestTemplate restTemplate;


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
