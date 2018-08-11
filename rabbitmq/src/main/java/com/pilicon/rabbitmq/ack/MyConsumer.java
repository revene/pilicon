package com.pilicon.rabbitmq.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {

    private Channel channel;

    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }


    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("----------consumerMessage---------");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ((Integer) properties.getHeaders().get("number") == 0){
            //nack表示消费失败,最后一个参数表示是否重新投回队列,false表示不重回队列,如果设置为true,则会投到queue的尾端
            channel.basicNack(envelope.getDeliveryTag(),false,true);
        }else {
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
