package com.pilicon.diveinspringboot.autoconfiguration.configuration;

import com.pilicon.diveinspringboot.autoconfiguration.annotations.EnableHelloWorld;
import com.pilicon.diveinspringboot.autoconfiguration.condition.ConditionalOnSystemProperties;
import org.springframework.context.annotation.Configuration;

@Configuration //Spring 模式注解装配
@EnableHelloWorld //@Enable 模块装配
@ConditionalOnSystemProperties(name = "user.name",value = "wangbaoliang") //条件装配
public class HelloWorldAutoConfiguration {

}
