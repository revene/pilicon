package com.pilicon.rabbitmq.returnlistener;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchange = "test_return_exchange";
        String routingKey = "return.save";
        String routingKeyError = "abc.save";

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("handler----------------return");
                System.out.println("replyCode-------"+i);
                System.out.println("replyText-------"+s);
                System.out.println("exchange--------"+s1);
                System.out.println("routingKey------"+s2);
                System.out.println("properties------"+basicProperties);
                System.out.println("body------------"+new String(bytes));
            }
        });


        String message = "Hello RabbitMQ Return message";
        //第三个参数最重要 mandatory true和false的情况下说明是否监听路由不可达的消息,默认是false
        //channel.basicPublish(exchange,routingKey,true,null,message.getBytes());

        //channel.basicPublish(exchange,routingKeyError,false,null,message.getBytes());

        channel.basicPublish(exchange,routingKeyError,true,null,message.getBytes());

    }
}
