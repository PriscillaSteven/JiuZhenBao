package com.mall.jiuzhenbao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * This is the class of Redis Configuration.
 * This class creating a bean of JedisConnectionFactory and RedisTemplate.
 * Created by Steven
 *
 * @version 0.0.1
 */
//@Configuration
public class RedisConfig {

    //Read redis host from .properties file
//    @Value("${redis.hostname}")
//    private String redisHostName;

    //Read redis port from .properties file
//    @Value("${redis.port}")
//    private int redisPort;


    /**
     * Create bean of JedisConnectionFactory
     *
     * @return
     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName(redisHostName);
//        factory.setPort(redisPort);
//        factory.setUsePool(true);
//        return factory;
//    }
}


