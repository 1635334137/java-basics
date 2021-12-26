package com.lqj.day02.filetest;

import java.io.File;
import java.io.IOException;

public class FileDemo {

    //使用File.separator 名为分隔符Char的常量，它是系统相关的名称分隔符。
    public static final String separator = File.separator;

    public static void main(String[] args) {
        //获取JVM当前工作目录
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);

        //替换：File newFile = new File("F:\\abc\\test.txt");
        File newFile = new File("F:"+separator+"abc"+separator+"test2.txt");
        if (!newFile.exists()){//如果该文件不存在
            //用于创建此抽象路径名指定的多级目录。创建成功返回true，创建失败返回false；
            //仅仅是创建目录，不创建文件；
            newFile.getParentFile().mkdirs();//一定要使用getParentFile()，否则test.txt也作为文件夹来创建
            try {
                newFile.createNewFile();//创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(newFile.getAbsolutePath());//获取文件的绝对路径
    }
}
