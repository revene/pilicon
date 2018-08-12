package com.pilicon.rabbitmq.springamqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public class TextMessageConvert implements MessageConverter {
    //java对象转换成message对象
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(o.toString().getBytes(),messageProperties);
    }

    //message对象转换成java对象 比如这个 会将二进制流转换成String类型 这样我们的handleMessage方法即可直接处理
    //String 类型 , 而不是byte[]类型
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String contentType = message.getMessageProperties().getContentType();
        if (null != contentType && contentType.contains("text")){
            return new String(message.getBody());
        }
        return message.getBody();
    }
}
