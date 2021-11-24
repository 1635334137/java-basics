package com.lqj.mq.work.fair;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者
 */
public class Receiver01 {
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
            //每次接收的消息的数量<=1
            channel.basicQos(1);
            //4.指定接收消息的队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //5.接收消息
            final Channel finalChannel = channel;
            Consumer consumer = new DefaultConsumer(finalChannel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,"utf-8");
                    System.out.println("消费方01收到消息--->"+message);
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finalChannel.basicAck(envelope.getDeliveryTag(),false);
                }
            };
            //设置手动回执消息
            boolean autoAck = false;
            //6.监听指定队列
            channel.basicConsume(QUEUE_NAME,autoAck,consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
