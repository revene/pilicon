package com.pilicon.concurrency.example.syncontainer;

import java.util.Iterator;
import java.util.Vector;

public class UnsafeExample {

    //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1){ //foreach
        for (Integer i : v1){
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1){ //iterator
        Iterator<Integer> integerIterator = v1.iterator();
        while (integerIterator.hasNext()){
            Integer i = integerIterator.next();
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //成功的 不抛出上面的两个异常
    private static void test3(Vector<Integer> v1){ //for
        for (int i = 0 ; i < v1.size() ; i++){
            if (v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        //test1(vector);
        //test2(vector);
        test3(vector);
    }
}
