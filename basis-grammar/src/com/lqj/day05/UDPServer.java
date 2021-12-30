package com.lqj.day05;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
        public static void main(String[] args) throws IOException {
            //1.创建接收端
            DatagramSocket server = new DatagramSocket(8889);
            //2.准备容器，封装成DatagramPacket
            byte[] container = new byte[1024*60];
            DatagramPacket packet = new DatagramPacket(container,0,container.length);
            //3.阻塞式接收
            server.receive(packet);
            //4.分析数据
            byte[] datas = packet.getData();
            //注意这里的长度是packet封装数据的长度
            System.out.println(new String(datas,0, packet.getLength()));
        }
}
