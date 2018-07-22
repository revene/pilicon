package com.pilicon.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//spingcloud 升级后 原来的jar包是spring-cloud-starter-feign 现在是spring-cloud-starter-openfeign
//完美的解释了为什么用feign的时候还必须要引入版本号
@EnableFeignClients(basePackages = "com.pilicon.product.api")
public class OrdersWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersWebApplication.class, args);
    }
}
