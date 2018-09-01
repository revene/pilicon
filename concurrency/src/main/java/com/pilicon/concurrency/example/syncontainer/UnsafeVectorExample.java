package com.pilicon.concurrency.example.syncontainer;

import com.pilicon.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;

@Slf4j
@NotThreadSafe
public class UnsafeVectorExample {

    private static List<Integer> list = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        list.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        list.get(i);
                    }
                }
            };
        thread1.start();
        thread2.start();
        }
    }
}
