package com.lqj.day05;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        //1.指定端口创建服务器
        ServerSocket server = new ServerSocket(8881);
        //2.阻塞式等待连接
        Socket client = server.accept();
        //数据输入流允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型。应用程序可以使用数据输出流写入稍后由数据输入流读取的数据。
        //DataInputStream 对于多线程访问不一定是安全的。 线程安全是可选的，它由此类方法的使用者负责。
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String datas = dis.readUTF();
        System.out.println(datas);

        dis.close();
        client.close();
        server.close();
    }
}
