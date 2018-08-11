package com.pilicon.rabbitmq.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();


        /**这就是一个普通的交换机,队列,路由 的声明*/
        String exchangeName = "test_dlx_exchange";
        String queueName = "test_dlx_queue";
        String routingKey = "dlx.#";
        channel.exchangeDeclare(exchangeName,"topic",true,false,null);

        Map<String,Object> arguments = new HashMap<>();
        //指定这个队列里的消息要是变成死信的话,那么发送到哪个死信队列上去
        arguments.put("x-dead-letter-exchange","dlx.exchange");

        //这个arguments属性,要设置声明到队列上
        channel.queueDeclare(queueName,true,false,false,arguments);
        channel.queueBind(queueName,exchangeName,routingKey);



        /**要进行死信队列的声明 */
        channel.exchangeDeclare("dlx.exchange","topic",true,false,null);
        channel.queueDeclare("dlx.queue",true,false,false,null);
        channel.queueBind("dlx.queue","dlx.exchange","#");


        channel.basicConsume(queueName,true,new MyConsumer(channel));

    }
}
