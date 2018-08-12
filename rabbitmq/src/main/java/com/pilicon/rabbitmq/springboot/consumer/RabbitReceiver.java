package com.pilicon.rabbitmq.springboot.consumer;

import com.pilicon.rabbitmq.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitReceiver {

    //建议把这些写到配置文件里
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",durable = "true"),
            exchange = @Exchange(value = "exchange-1",type = "topic",durable = "true",autoDelete = "false",ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    ))
    @RabbitHandler
    public void onMessage(Message message , Channel channel) throws Exception{
        System.out.println("-----------------");
        System.out.println("消费端收到的消息体内容: "+message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        //手工ack
//        deliveryTag:该消息的index
//        multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
        channel.basicAck(deliveryTag,false);
    }

    //建议把这些写到配置文件里
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",durable = "true"),
            exchange = @Exchange(value = "exchange-1",type = "topic",durable = "true",autoDelete = "false",ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    ))
    @RabbitHandler
    //把message拆成了两部分 payload和headers
    public void OnMessage(@Payload Order order, Channel channel, @Headers Map<String,Object> headers)throws Exception{
        System.out.println("---------------");
        System.out.println("消费端order" +order.getId());

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        //手工ack
//        deliveryTag:该消息的index
//        multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息
        channel.basicAck(deliveryTag,false);


    }
}
