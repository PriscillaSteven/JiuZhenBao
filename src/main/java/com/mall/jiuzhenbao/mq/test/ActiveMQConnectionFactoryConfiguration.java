//package com.shengsiyuan.cabinetprotection.mq;
//
//
//import javax.jms.ConnectionFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jms.connection.CachingConnectionFactory;
//
///**
// * 定义带有session缓冲的连接工厂
// * @author song
// *
// */
//@Configuration
//@EnableConfigurationProperties({ ActiveMQProperties.class })
//public class ActiveMQConnectionFactoryConfiguration {
//    /**
//     * 注入MQ连接工厂
//     */
//    @Autowired
//    @Qualifier(value="jmsConnectionFactory")
//    private ConnectionFactory connectionFactory;
//
//    /**
//     * 创建带有缓冲session的连接工厂
//     * @return
//     */
//    @Bean(name="cachingConnectionFactory")
//    @Primary
//    public CachingConnectionFactory getConnectionFactory(){
//        CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory();
//        //目标ConnectionFactory对应真实的可以产生JMS Connection的ConnectionFactory
//        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory);
//        //Session缓存数量,这里属性也可以直接在这里配置
//        cachingConnectionFactory.setSessionCacheSize(10);
//        return cachingConnectionFactory;
//    }
//}
