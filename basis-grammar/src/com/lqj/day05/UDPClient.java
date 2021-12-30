package com.lqj.day05;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * UDP面向无连接，不提供可靠传输
 * 核心类：DatagramSocket、DatagramPacket
 */
public class UDPClient {

    public static void main(String[] args) throws IOException {
        //1.创建发送端
        DatagramSocket client = new DatagramSocket(8888);
        //2.准备数据
        String data = "让你心跳慢半拍";
        //转为字节数组
        byte[] datas = data.getBytes();
        //3.封装成datagramPacket 入参：字节数组 长度 接收端信息
        DatagramPacket packet = new DatagramPacket(datas, datas.length,new InetSocketAddress("127.0.0.1",8889));
        //4.发送数据
        client.send(packet);
        //5.释放资源
        client.close();
    }
}
