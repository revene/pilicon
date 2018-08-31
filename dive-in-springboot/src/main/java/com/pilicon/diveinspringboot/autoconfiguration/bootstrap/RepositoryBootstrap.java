package com.pilicon.diveinspringboot.autoconfiguration.bootstrap;

import com.pilicon.diveinspringboot.autoconfiguration.repository.MyFirstLevelRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 仓储的引导类
 */
@ComponentScan(basePackages = "com.pilicon.diveinspringboot.autoconfiguration.repository")
public class RepositoryBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(RepositoryBootstrap.class).web(WebApplicationType.NONE).run(args);


        //myFirstLevelRepository 这个Bean是否存在
        MyFirstLevelRepository myFirstLevelRepository = context.getBean("myFirstLevelRepository",MyFirstLevelRepository.class);
        System.out.println("myFirstLevelRepository : " + myFirstLevelRepository);
        //关闭上下文
        context.close();
    }
}
