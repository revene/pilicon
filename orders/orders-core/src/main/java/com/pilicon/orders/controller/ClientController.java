package com.pilicon.orders.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @RequestMapping(value = "getProductMsg", method = RequestMethod.GET)
    public String getProductMsg() {
        /**第一种微服务间调用方式 缺点很明显,调用的url是写死的,不能负载均衡,且必须要知道对方的ip*/
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject("http://localhost:8700/msg", String.class);
//        log.info("response={}",result);

        /** 第二种方式 使用负载均衡客户端挑选出一个服务端 比第一种方式是url不是写死的,通过应用名获取url*/
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/msg";
//        String result = restTemplate.getForObject(url,String.class);

        /** 第三种方式 在外面配置一个带@LoadBanlance注解修饰的RestTemmplate*/
//        String result = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("result={}",result);

        /** 第四种方式 使用feign的方式 **/
//        String productMsg = productClientApi.productMsg();
//        return productMsg;

        return null;

    }
}
