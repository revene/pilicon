package com.pilicon.rabbitmq.springamqp;

public class MessageDelegate {
    public void handleMessage(byte[] messageBody){
        System.out.println("-------默认方法:消息内容是"+new String(messageBody));
    }
}
