//package com.pilicon.orders.controller;
//
//import com.pilicon.orders.message.StreamClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//public class SendMessageController {
//    @Autowired
//    private StreamClient streamClient;
//
//    @RequestMapping(value = "sendMessage",method = RequestMethod.GET)
//    public void process(){
//        String message = "now" + new Date();
//        streamClient.output().send(MessageBuilder.withPayload(message).build());
//    }
//
//}
