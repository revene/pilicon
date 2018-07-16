package com.pilicon.consumer.web.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/")
public class ConsumerController {




    @RequestMapping("consumer")
    private String consumer()throws Exception{
        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("http://CLIENT/hello",String.class);
        String result=responseEntity.getBody();
        return result;
    }
}
