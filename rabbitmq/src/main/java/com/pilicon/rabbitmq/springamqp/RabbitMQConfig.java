package com.pilicon.rabbitmq.springamqp;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@ComponentScan("com.pilicon.rabbitmq.*")
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();

        cachingConnectionFactory.setAddresses("localhost:5672");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        cachingConnectionFactory.setVirtualHost("/");

        return cachingConnectionFactory;
    }


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        //spring容器加载的时候,会把这个加载上
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * 针对消费者配置
     * 1.设置交换机类型
     * 2.将消息队列绑定到交换机
     *
     *     fanoutExchange: 将消息分发到所有的绑定队列,无routingKey概念
     *     headersExchange: 通过添加属性key-value匹配
     *     DirectExchange: 按照routingKey分发到指定队列
     *     TopicExchange: 多关键字匹配
     */


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic001",true,false);
    }

    @Bean
    public Queue queue(){
        return new Queue("queue001",true);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with("spring.*");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        //这个方法里可以放很多个queue
        container.setQueues(queue());
        //设置当前的消费者数量
        container.setConcurrentConsumers(1);
        //设置最大的消费者数量
        container.setMaxConcurrentConsumers(5);
        //是否重回队列,false代表不重回队列
        container.setDefaultRequeueRejected(false);
        //签收模式的设置 自动签收
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //消费端的标签设置策略
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String s) {
                return s+"_"+UUID.randomUUID().toString();
            }
        });
//        container.setMessageListener(new ChannelAwareMessageListener() {
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                String mes = new String(message.getBody());
//                System.out.println("------消费者--------"+mes);
//            }
//        });

        /**
         * 第一种适配器的方式
         */
        //适配器模式,将消息的处理委派给我们自己的实现类MessageDelegate
        //打开MessageListenAdapter 最重要的就是默认调用委托类里的handleMessage方法,所以我们要自定义自己的
        //消息处理方法,方法名字必须加handleMessage(用到了反射,所以方法名要指定好);
        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        //当然,也可以自定义要调用的方法,不是handleMessage;
        //这样修改
        //adapter.setDefaultListenerMethod("consumerMessage");
        adapter.setMessageConverter(new TextMessageConvert());

        //消息侦听器,把适配器放进去 适配器的作用
        container.setMessageListener(adapter);


        /**
         * 第二种适配器方式, 我们的队列名称和方法名称也可以进行一一的匹配,用相应的方法去消费队列中的消息
         */
        Map<String,String> map = new HashMap<>();
        //key是队列的名字,value是method的名字
        map.put("queue001","method1");
        adapter.setQueueOrTagToMethodName(map);
        container.setMessageListener(adapter);


        //json格式转换器
//        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
//        adapter.setMessageConverter(jackson2JsonMessageConverter);



        return container;
    }



}
