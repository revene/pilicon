package com.pilicon.diveinspringboot.bootstrap;

import com.pilicon.diveinspringboot.condition.ConditionalOnSystemProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


/**
 * 系统属性条件引导类
 */
public class SystemConditionPropertyBootstrap {

    @Bean
    @ConditionalOnSystemProperties(name = "user.name",value = "wangbaoliang")
    public String helloWorld(){
        return "Hello world";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SystemConditionPropertyBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloWorld = context.getBean("helloWorld",String.class);
        System.out.println(helloWorld);

        context.close();


    }
}
