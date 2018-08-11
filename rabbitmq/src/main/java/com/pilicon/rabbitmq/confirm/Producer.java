package com.pilicon.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1创建connectionfactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2拿到Connection
        Connection connection = connectionFactory.newConnection();

        //3获取最关键的channel
        Channel channel = connection.createChannel();

        //4指定消息的投递模式为消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.save";

        //发送一条消息
        String message = "Hello RabbitMQ Send confirm message";
        channel.basicPublish(exchangeName,routingKey,null,message.getBytes());


        //添加一个确认消息
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                //l是返回消息的唯一标签
                System.out.println("no ----------- ack");
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("--------ack!---------");
            }
        });
    }
}
