package com.lqj.mq.work.fair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * 工作队列-公平分发
 */
public class Send {
    public static final String QUEUE_NAME="work_fair";
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            //1.创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            factory.setVirtualHost("/lqj");
            factory.setPort(5672);
            factory.setUsername("lqj123");
            factory.setPassword("123");
            //2.创建连接对象
            connection = factory.newConnection();
            //3.创建通道
            channel = connection.createChannel();
            //4.创建队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //5.发送消息到指定队列
            for(int i=0;i<10;i++){
                String message = "hello rabbitmq"+i;
                channel.basicPublish("",QUEUE_NAME,null,message.getBytes("utf-8"));
            }
            System.out.println("消息发生成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            if(null!=channel&&channel.isOpen()){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if(null!=connection&&connection.isOpen()){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
