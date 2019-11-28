//package com.shengsiyuan.cabinetprotection.mq;
//
//
//import javax.jms.DeliveryMode;
//
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jms.connection.CachingConnectionFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.support.converter.MessageConverter;
//import org.springframework.jms.support.destination.DestinationResolver;
//
//
///**
// * 自定义JmsTemplate，支持事务
// */
//@Configuration
////@DependsOn(value="cachingConnectionFactory")
//public class JmsTemplateConfiguration {
//
//    private final ObjectProvider<DestinationResolver> destinationResolver;
//    private final ObjectProvider<MessageConverter> messageConverter;
////    @Autowired
////    private CachingConnectionFactory cachingConnectionFactory;
//
//    public JmsTemplateConfiguration(ObjectProvider<DestinationResolver> destinationResolver,
//                                    ObjectProvider<MessageConverter> messageConverter) {
//        this.destinationResolver = destinationResolver;
//        this.messageConverter = messageConverter;
//    }
//
//    /**
//     * 配置队列生产者的JmsTemplate
//     * @return
//     */
//    @Bean(name="jmsQueueTemplate")
//    @Primary
//    public JmsTemplate jmsQueueTemplate() {
//        //设置创建连接的工厂
////		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        //优化连接工厂，这里应用缓存池 连接工厂就即可
//        JmsTemplate jmsTemplate = new JmsTemplate(/*cachingConnectionFactory*/);
//
//        //设置P2P队列消息类型
//        jmsTemplate.setPubSubDomain(false);
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
//        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
//        //默认不开启事务
//        System.out.println("默认是否开启事务："+jmsTemplate.isSessionTransacted());
//        //如果不启用事务，则会导致XA事务失效；
//        //作为生产者如果需要支持事务，则需要配置SessionTransacted为true
//        jmsTemplate.setSessionTransacted(true);
//
//        return jmsTemplate;
//    }
//
//    /**
//     * 配置发布订阅生产者的JmsTemplate
//     * @return
//     */
//    @Bean(name="jmsTopicTemplate")
//    public JmsTemplate jmsTopicTemplate() {
//        //设置创建连接的工厂
////		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        //优化连接工厂，这里应用缓存池 连接工厂就即可
//        JmsTemplate jmsTemplate = new JmsTemplate(/*cachingConnectionFactory*/);
//
//        //设置发布订阅消息类型
//        jmsTemplate.setPubSubDomain(true);
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
//        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
//        //默认不开启事务
//        System.out.println("默认是否开启事务："+jmsTemplate.isSessionTransacted());
//        //如果不启用事务，则会导致XA事务失效；
//        //作为生产者如果需要支持事务，则需要配置SessionTransacted为true
//        jmsTemplate.setSessionTransacted(true);
//
//        return jmsTemplate;
//    }
//}