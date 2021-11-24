package com.lqj.mq.work.rr;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者
 */
public class Receiver01 {
    public static final String QUEUE_NAME="work_rr";
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
            //4.指定接收消息的队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //5.接收消息
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,"utf-8");
                    System.out.println("消费方01收到消息--->"+message);
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            //6.监听指定队列
            channel.basicConsume(QUEUE_NAME,true,consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
