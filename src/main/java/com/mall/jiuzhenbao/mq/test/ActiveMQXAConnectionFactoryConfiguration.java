//package com.shengsiyuan.cabinetprotection.mq;
//
//import javax.jms.ConnectionFactory;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 配置注册连接工厂
// * @author song
// *
// */
//@Configuration
//public class ActiveMQXAConnectionFactoryConfiguration {
//    /**
//     * 创建普通连接工厂
//     * @param properties
//     * @return
//     */
//    @Bean(name = "jmsConnectionFactory")
//    public ActiveMQConnectionFactory nonXaJmsConnectionFactory(ActiveMQProperties properties) {
//        return new ActiveMQConnectionFactoryFactory(properties)
//                .createConnectionFactory(ActiveMQConnectionFactory.class);
//    }
//
//}
