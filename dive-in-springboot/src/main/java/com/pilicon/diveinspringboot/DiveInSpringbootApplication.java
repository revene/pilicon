package com.pilicon.diveinspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com.pilicon.diveinspringboot.autoconfiguration.web.servlet")
@SpringBootApplication
public class DiveInSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiveInSpringbootApplication.class, args);
    }
}
