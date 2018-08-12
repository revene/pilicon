package com.pilicon.rabbitmq.springboot.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            System.out.println("correlationData: " + correlationData);
            System.out.println("ack: "+ack);
            if (!ack){
                System.out.println("异常处理....");
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("return exchange: "+ exchange + " , routingKey :" + routingKey + " , replyCode : "+replyText+", replyText : "+replyText);
        }
    };

    public void send(Object message, Map<String,Object> properties) throws Exception{
        MessageHeaders mhs = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message,mhs);

        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("1234567890");  //保证这个id全局唯一  id + 时间戳 ,这里写死
        rabbitTemplate.convertAndSend("exchange-1","springboot.hello",msg,correlationData);
    }

}
