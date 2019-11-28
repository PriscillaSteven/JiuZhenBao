//package com.shengsiyuan.cabinetprotection.mq;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.activemq.command.ActiveMQTopic;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.jms.connection.CachingConnectionFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.support.converter.MessageConverter;
//import org.springframework.jms.support.destination.DestinationResolver;
//
//import javax.jms.DeliveryMode;
//import javax.jms.Queue;
//import javax.jms.Session;
//import javax.jms.Topic;
//
//@Configuration
//public class JmsTemplateConfig {
//
//    @Value("${wechat.sendmessage.queue}")
//    private String queueName;
//
//    @Value("${wechat.sendmessage.topic}")
//    private String topicName;
//
//    @Value("${spring.jms.pub-sub-domain}")
//    private boolean isPubSubDomain;
//
//    @Bean
//    public Queue queue() {
//        return new ActiveMQQueue(queueName);
//    }
//
//    @Bean
//    public Topic topic() {
//        return new ActiveMQTopic(topicName);
//    }
//
//    private final ObjectProvider<DestinationResolver> destinationResolver;
//    private final ObjectProvider<MessageConverter> messageConverter;
//
//    @Autowired
//    public JmsTemplateConfig(ObjectProvider<DestinationResolver> destinationResolver,
//                                    ObjectProvider<MessageConverter> messageConverter) {
//        this.destinationResolver = destinationResolver;
//        this.messageConverter = messageConverter;
//    }
//
//    /**
//     * 配置队列生产者的JmsTemplate
//     * @return JmsTemplate
//     */
//    @Bean(name="jmsQueueTemplate")
//    public JmsTemplate jmsQueueTemplate() {
//        //设置创建连接的工厂
//        //JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        //优化连接工厂，这里应用缓存池 连接工厂就即可
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        //设置默认消费topic
//        //jmsTemplate.setDefaultDestination(topic());
//        //设置P2P队列消息类型
//        jmsTemplate.setPubSubDomain(isPubSubDomain);
//
//        DestinationResolver destinationResolver = (DestinationResolver) this.destinationResolver.getIfUnique();
//        if (destinationResolver != null) {
//            jmsTemplate.setDestinationResolver(destinationResolver);
//        }
//        MessageConverter messageConverter = (MessageConverter) this.messageConverter.getIfUnique();
//        if (messageConverter != null) {
//            jmsTemplate.setMessageConverter(messageConverter);
//        }
//        //deliveryMode, priority, timeToLive 的开关，要生效，必须配置为true，默认false
//        jmsTemplate.setExplicitQosEnabled(true);
//        //DeliveryMode.NON_PERSISTENT=1:非持久 ; DeliveryMode.PERSISTENT=2:持久
//        //定义持久化后节点挂掉以后，重启可以继续消费.
//        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
//        //默认不开启事务
//        System.out.println("默认是否开启事务："+jmsTemplate.isSessionTransacted());
//        //如果不启用事务，则会导致XA事务失效；
//        //作为生产者如果需要支持事务，则需要配置SessionTransacted为true
//        //jmsTemplate.setSessionTransacted(true);
//        //消息的应答方式，需要手动确认，此时SessionTransacted必须被设置为false，且为Session.CLIENT_ACKNOWLEDGE模式
//        //Session.AUTO_ACKNOWLEDGE  消息自动签收
//        //Session.CLIENT_ACKNOWLEDGE  客户端调用acknowledge方法手动签收
//        //Session.DUPS_OK_ACKNOWLEDGE 不必必须签收，消息可能会重复发送
//        jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
//        return jmsTemplate;
//    }
//
//    /**
//     * 配置发布订阅生产者的JmsTemplate
//     * @return JmsTemplate
//     */
//    @Bean(name="jmsTopicTemplate")
//    public JmsTemplate jmsTopicTemplate() {
//        //设置创建连接的工厂
//        //JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        //优化连接工厂，这里应用缓存池 连接工厂就即可
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        //设置默认消费topic
//        //jmsTemplate.setDefaultDestination(topic());
//        //设置发布订阅消息类型
//        jmsTemplate.setPubSubDomain(isPubSubDomain);
//
//
//        //deliveryMode, priority, timeToLive 的开关，要生效，必须配置为true，默认false
//        jmsTemplate.setExplicitQosEnabled(true);
//        //DeliveryMode.NON_PERSISTENT=1:非持久 ; DeliveryMode.PERSISTENT=2:持久
//        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
//
//        //默认不开启事务
//        System.out.println("是否开启事务"+jmsTemplate.isSessionTransacted());
//        //如果session带有事务，并且事务成功提交，则消息被自动签收。如果事务回滚，则消息会被再次传送。
//        //jmsTemplate.setSessionTransacted(true);
//
//        //不带事务的session的签收方式，取决于session的配置。
//        //默认消息确认方式为1，即AUTO_ACKNOWLEDGE
//        System.out.println("是否消息确认方式"+jmsTemplate.getSessionAcknowledgeMode());
//
//        //消息的应答方式，需要手动确认，此时SessionTransacted必须被设置为false，且为Session.CLIENT_ACKNOWLEDGE模式
//        //Session.AUTO_ACKNOWLEDGE  消息自动签收
//        //Session.CLIENT_ACKNOWLEDGE  客户端调用acknowledge方法手动签收
//        //Session.DUPS_OK_ACKNOWLEDGE 不必必须签收，消息可能会重复发送
//        jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
//
//        return jmsTemplate;
//    }
//}