package com.lqj.day05;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * TCP 可靠连接 三握手四挥手
 * 核心类：ServerSocket、Socket
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        //1.建立连接：使用Socket创建客户端+服务器地址和端口
        Socket client = new Socket("127.0.0.1",8881);
        //DataOutputStream，无需转换流（数据输出流允许应用程序以适当方式将基本 Java 数据类型写入输出流中。）
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        String data = "是独立开发的";
        dos.writeUTF(data);
        dos.flush();
    }
}
