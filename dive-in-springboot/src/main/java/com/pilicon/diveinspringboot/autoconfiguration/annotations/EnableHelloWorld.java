package com.pilicon.diveinspringboot.autoconfiguration.annotations;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class) 这个是注解方式
@Import(HelloWorldImportSelector.class) //这个是编程式方式
public @interface EnableHelloWorld {
}
