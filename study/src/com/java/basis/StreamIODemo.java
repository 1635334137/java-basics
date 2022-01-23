package com.java.basis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * JavaIO流操作
 *
 * @author Mr.Lan
 * @create: 2022-01-13 15:33
 */
public class StreamIODemo {
    public static void main(String[] args) throws IOException {
        readChar();
    }

    /**
     * 从控制台读取单个字符，遇到字符q后退出程序
     */
    public static void readChar() throws IOException {
        //定义一个变量用于保存输入的字符
        char x = 0;
        //从控制台读取字节并转为字符(这是包装类的设计模式，本质还是输入流)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入字符：");
        do {
            //每次读取一个字符
            x = (char) br.read();
            System.out.println(x);
        } while (x != 'q');
    }
}
