package com.pilicon.concurrency.example.publish;

import com.pilicon.concurrency.annotations.NotRecommend;
import com.pilicon.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape (){
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass(){
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
