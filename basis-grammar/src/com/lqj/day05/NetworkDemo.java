package com.lqj.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class NetworkDemo {
    public static void main(String[] args) throws IOException {
        //创建InetAddress对象 本机（如果连wifi拿不到wifi连接的地址）
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());

        //通过域名获取ip地址 JavaDNS解析
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName.getHostAddress());
        System.out.println(byName.getHostName());

        //socket的实现类之一
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",8085);

        //URL
        URL url = new URL("https://127.0.0.1:8085/webdemo/index.html?name=zhangsan&age=18#a");
        System.out.println("获取使用的协议："+url.getProtocol());
        System.out.println(String.format("获取ip地址：%s", url.getHost()));
        System.out.println("获取URI："+url.getPath());
        System.out.println("获取URL："+url.getFile());
        System.out.println("获取参数："+url.getQuery());
        System.out.println("获取锚点："+url.getRef());

        //模拟HTTP请求
        URL baidu = new URL("https://www.baidu.com");
        //URLConnection urlConnection = baidu.openConnection();
        //urlConnection.setRequestProperty();设置请求头 等其他自定义配置
        InputStream inputStream = baidu.openStream();//默认请求配置
        //字节流到字符流的转换
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        //从字符流读取文本及缓存
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String msg = null;//使用变量保存读取的文本
        //String msg = bufferedReader.readLine();//每次读取一行
        while (null != (msg=bufferedReader.readLine())){
            System.out.println(msg);
        }
        bufferedReader.close();//不知道关没关，反正测试的时候没关。
    }
}
