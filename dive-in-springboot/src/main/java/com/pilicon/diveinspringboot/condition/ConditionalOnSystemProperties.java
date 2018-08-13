package com.pilicon.diveinspringboot.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * java 系统属性条件判断
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSystemConditionProperty.class)
public @interface ConditionalOnSystemProperties {

    //java 系统属性的名称
    String name();

    //java 系统属性值
    String value();
}
