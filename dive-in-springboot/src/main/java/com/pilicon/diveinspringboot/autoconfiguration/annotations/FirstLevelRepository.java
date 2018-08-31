package com.pilicon.diveinspringboot.autoconfiguration.annotations;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Repository
public @interface FirstLevelRepository {
    String value() default "";
}
