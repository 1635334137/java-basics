package com.example.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;

import java.io.IOException;

/**
 * FastDFS删除文件
 *
 * @author Mr.Lan
 * @create: 2022-01-23 18:25
 */
public class DeleteFile {

    public static void main(String[] args) throws IOException {

    }

    public static void deleteFile(StorageClient storageClient) throws MyException, IOException {
       int result =  storageClient.delete_file("group1","M00/00/00/wKhClmHtVaiAFoCkAATjOE0msoA081.png");
       System.out.println("result = "+result+"[0成功，非0失败]");
    }
}
