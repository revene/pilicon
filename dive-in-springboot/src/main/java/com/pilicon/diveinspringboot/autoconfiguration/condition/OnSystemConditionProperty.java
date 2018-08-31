package com.pilicon.diveinspringboot.autoconfiguration.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 系统属性条件判断
 */
public class OnSystemConditionProperty implements Condition {


    @Override
    //annotatedTypeMetadata主要是元信息,即注解上标注的信息,和字段
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> attributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnSystemProperties.class.getName());

        String propertyName = String.valueOf(attributes.get("name"));

        String propertValue = String.valueOf(attributes.get("value"));

        String javaPropertyValue = System.getProperty(propertyName);

        return javaPropertyValue.equals(propertValue);
    }
}
