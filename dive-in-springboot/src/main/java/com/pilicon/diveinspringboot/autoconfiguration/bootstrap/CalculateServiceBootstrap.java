package com.pilicon.diveinspringboot.autoconfiguration.bootstrap;

import com.pilicon.diveinspringboot.autoconfiguration.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * CalculateService 引导类
 */
@SpringBootApplication(scanBasePackages = "com.pilicon.diveinspringboot.autoconfiguration.service")
public class CalculateServiceBootstrap  {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                //通过profile进行相应的条件装配
                .profiles("java7")
                .run(args);


        //myFirstLevelRepository 这个Bean是否存在
        CalculateService calculateService = context.getBean(CalculateService.class);
        System.out.println("calculateService : " + calculateService.sum(1,2,3,4,5,6,7,8,9,10));
        //关闭上下文
        context.close();

    }
}
