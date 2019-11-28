package com.mall.jiuzhenbao;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Topic;

@SpringBootApplication
public class JiuZhenBaoApplication /*implements CommandLineRunner*/ {
//    @Autowired
//    private Producer producer;
//
//    @Autowired
//    private Consumer consumer;

//    private Destination destination = new ActiveMQQueue("test.Priscilla");

//    private  int i = 0;

    public static void main(String[] args) throws Exception{
        SpringApplication.run(JiuZhenBaoApplication.class, args);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("test.topic");
    }
//    @Override
//    public void run(String... strings) throws Exception {
//        Thread p1 = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                while(true){
//                    try{
//                        producer.sendMessage(destination, "用户A 操作 部门DD");
//                        Thread.sleep(10000);
//                    }catch(Exception ex){
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });
//        Thread p2 = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                while(true){
//                    try{
//                        producer.sendMessage(destination, "用户B 操作 部门FF");
//                        Thread.sleep(5000);
//                    }catch(Exception ex){
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        p1.start();
//        p2.start();
//    }
}
