package com.pilicon.concurrency.thinkinginjava;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> arrayList = new ArrayList<>();
        for (int i =0 ;i< 5;i++){
            arrayList.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs : arrayList){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                executorService.shutdown();
            }
        }
    }
}
