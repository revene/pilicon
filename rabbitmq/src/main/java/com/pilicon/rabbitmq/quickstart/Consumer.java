package com.pilicon.rabbitmq.quickstart;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //1创建一个connection factory 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        //3通过connection创建channel
        Channel channel = connection.createChannel();

        //4声明一个真实的队列
        String queueName = "test001";
        channel.queueDeclare(queueName,true,false,false,null);

        //5创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //6设置channel 设置好队列和消费者
        channel.basicConsume(queueName,true,queueingConsumer);

        //7消费消息
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);

        }



    }
}
