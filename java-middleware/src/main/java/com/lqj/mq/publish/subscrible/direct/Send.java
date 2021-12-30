package com.lqj.mq.publish.subscrible.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 */
public class Send {
    public static final String EXCHANGE_NAME="exchange_direct";
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
            //4.创建交换机
            //direct方式：队列绑定到交换机，交换机根据路由键将消息路由到对应队列
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            //5.发送消息到指定队列
            String message = "info message";
            channel.basicPublish(EXCHANGE_NAME,"error",null,message.getBytes(StandardCharsets.UTF_8));
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
