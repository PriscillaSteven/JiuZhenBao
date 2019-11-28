package com.mall.jiuzhenbao.mq.test;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import java.util.Random;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MsgTest {
//    @Autowired
//    private Producer producer;
//
//    @Autowired
//    private Consumer consumer;
//
//    @Test
//    public void contextLoads() throws InterruptedException {
//        final Destination destination = new ActiveMQQueue("test.Priscilla");
//
//        Thread pro = new Thread(new Runnable(){
//            int i = 0;
//            @Override
//            public void run(){
//                while(true){
//                    i++;
//                    producer.sendMessage(/*destination, */"my name is Priscilla!!!" + (i++));
//                    try{
//                        Thread.sleep(2000);
//                    }catch (Exception e){
//                        System.out.println(e.getMessage());
//                    }
//                }
//            }
//        });
//
//        pro.start();
//    }
//}
