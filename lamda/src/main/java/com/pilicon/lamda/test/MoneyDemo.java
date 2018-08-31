package com.pilicon.lamda.test;

import java.text.DecimalFormat;
import java.util.function.Function;

//interface ImoneyFormat{
//    String format(int i);
//}

class Mymoney{
    private final int money;

    Mymoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer,String> imoneyFormat){
        System.out.println("我的存款: "+ imoneyFormat.apply(this.money));
    }
}


public class MoneyDemo {
    public static void main(String[] args) {
        Mymoney mymoney = new Mymoney(999999999);

        mymoney.printMoney((i)->new DecimalFormat("#,###").format(i));
    }
}
