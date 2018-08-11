package com.pilicon.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {

        //1创建一个connection factory 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        //3通过connection创建channel
        Channel channel = connection.createChannel();

        //4通过channelf发送数据
        for (int i = 0;i<5;i++) {
            String message = "Hello,rabbitMQ!";
            channel.basicPublish("", "test001", null, message.getBytes());
            System.out.println(message);
        }

        //5关闭相关的连接
        channel.close();
        connection.close();

    }
}
