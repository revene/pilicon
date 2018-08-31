package com.pilicon.demo.demo;

import org.springframework.stereotype.Component;

@Component
public class JayCd implements Cd {
    @Override
    public void play() {
        System.out.println("this is Jay's Cd");
    }
}
