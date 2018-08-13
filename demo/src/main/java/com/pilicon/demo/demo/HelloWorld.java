package com.pilicon.demo.demo;

import com.pilicon.demo.demo.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
public class HelloWorld {
    //通过Value注入属性
    @Value("${user}")
    private String userName;


    @RequestMapping("user")
    public User hello(){
        User user = new User();
        user.setUserName(userName);
        user.setPassword("1111");
        user.setDate(new Date());
//        user.setDesc("嘻嘻嘻嘻嘻嘻");
        return user;
    }

    @RequestMapping(value = "helloWorld",method = RequestMethod.POST)
    public String helloWorld()throws Exception{
        return "helloWorld";
    }
}
