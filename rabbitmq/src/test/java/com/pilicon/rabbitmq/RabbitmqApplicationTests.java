package com.pilicon.rabbitmq;

import com.pilicon.rabbitmq.springboot.producer.RabbitSender;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitSender rabbitSender;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Test
    public void testAdmin() throws Exception{
        //三个参数分别为 交换机的名字,是否持久化,是否自动删除
        rabbitAdmin.declareExchange(new DirectExchange("test.direct",false,false));
        rabbitAdmin.declareExchange(new TopicExchange("test.topic",false,false));
        rabbitAdmin.declareExchange(new FanoutExchange("test.fanout",false,false));

        //第一个参数队列的名字,第二个队列是否持久化
        rabbitAdmin.declareQueue(new Queue("test.direct.queue",false));
        rabbitAdmin.declareQueue(new Queue("test.topic.queue",false));
        rabbitAdmin.declareQueue(new Queue("test.fanout.queue",false));

        //声明绑定,五个入参分别为 队列的名字,绑定的类型是Queue,交换机名字,routingkey,和arguments参数
        rabbitAdmin.declareBinding(new Binding("test.direct.queue",Binding.DestinationType.QUEUE,"test.direct","direct",new HashMap<>()));

        //第二中声明绑定的方式,链式编程,在声明绑定的时候再创建交换机和队列
        rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue("test.topic.queue",false)).to(new TopicExchange("test.topic",true,false)).with("user.#"));
        //fanout 类型的交换机不走路由键,所以不需要with指定routingKey
        rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue("test.fanout.queue",false)).to(new FanoutExchange("test.fanout",true,false)));


        //清空指定的队列
        rabbitAdmin.purgeQueue("test.direct.queue",false);
    }

    @Test
    public void testSendMessage() throws Exception{

        //创建一个消息,包括消息属性和消息内容
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc","信息描述....");
        messageProperties.getHeaders().put("type","自定义消息类型....");

        Message message = new Message("Hello RabbitMQ".getBytes(),messageProperties);

        //发送消息
        rabbitTemplate.convertAndSend("topic001", "spring.amqp", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println("-------添加额外的设置-------");
                messageProperties.getHeaders().put("desc","额外修改的信息描述");
                messageProperties.getHeaders().put("attr","额外新加的属性");
                return message;
            }
        });

    }


    @Test
    public void testSendMessage2()throws Exception{

        //可以随便传一句话发送 第三个参数不必一定是一个message,因为会帮我们先转化convert再发送
        rabbitTemplate.convertAndSend("topic001","spring.amqp","这是我要发送的消息,嘻嘻");



        //创建一个消息,包括消息属性和消息内容
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc","信息描述....");
        messageProperties.getHeaders().put("type","自定义消息类型....");
        Message message = new Message("Hello RabbitMQ".getBytes(),messageProperties);
        //send方法则必须要发送message
        rabbitTemplate.send("topic001","spring.abc",message);
    }


    @Test
    public void testSender1 () throws Exception{
        Map<String,Object> properties = new HashMap<>();
        properties.put("number","12345");
        properties.put("send_time",simpleDateFormat.format(new Date()));
        rabbitSender.send("Hello RabbitMQ for SpringBoot",properties);

    }
}
