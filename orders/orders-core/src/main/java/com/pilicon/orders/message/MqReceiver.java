package com.pilicon.orders.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收Mq消息
 */
@Slf4j
@Component
public class MqReceiver {

    //1. @RabbitListener(queues = "myQueue")
    //2. 自动创建队列
    //3. 自动创建, Exchange和Queue队列绑定

//   2 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    public void process(String message){
//        log.info("MyReceiver:{}",message);
//    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message){
        log.info("MyReceiver:{}",message);
    }
}
