package com.pilicon.demo.demo;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Proxy;

@Component
public class TaylorCd implements Cd {

    public static void main(String[] args) {
        TaylorCd taylorCd = new TaylorCd();
        taylorCd.play();
    }

    @Override
    public void play() {
        System.out.println("this is Taylor's Cd");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("嘻嘻");
        stopWatch.stop();
    }
}
