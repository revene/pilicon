//package com.pilicon.orders;
//
//import org.junit.Test;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * 发送mq消息测试
// */
//@Component
//public class MySenderTest extends OrdersCoreApplicationTests{
//
//
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    @Test
//    public void send(){
//        amqpTemplate.convertAndSend("myQueue","now"+new Date());
//    }
//}
