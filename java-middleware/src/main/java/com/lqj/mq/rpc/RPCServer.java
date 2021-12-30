package com.lqj.mq.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class RPCServer {

    //队列名称
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    /**
     * 计算斐波那契数列
     * 后一项=前两项之和
     * 迭代
     */
    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/lqj");
        factory.setPort(5672);
        factory.setUsername("lqj123");
        factory.setPassword("123");
        try {
            //2.创建连接对象
            final Connection connection = factory.newConnection();
            //3.创建通道
            final Channel channel = connection.createChannel();
            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(RPC_QUEUE_NAME);

            int prefetchCount = 1;
            channel.basicQos(prefetchCount);

            Object monitor = new Object();
            //获取消息
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                String response = "";
                try {
                    //接收客户端消息
                    String message = new String(delivery.getBody(), "UTF-8");
                    int n = Integer.parseInt(message);
                    response += fib(n);
                    System.out.println(response);
                } catch (RuntimeException e) {
                    System.out.println(e.toString());
                } finally {
                    //将处理结果发送至replyTo队列同时携带correlationId属性
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps,
                            response.getBytes(StandardCharsets.UTF_8));
                    //手动回执消息
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    //通知其他线程运行，同步过程
                    synchronized (monitor) {
                        monitor.notify();
                    }
                }
            };

            boolean autoAck = false;
            channel.basicConsume(RPC_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
            });

            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

}
