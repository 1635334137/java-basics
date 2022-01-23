package com.example.fastdfs;

import org.apache.commons.io.FileUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.DownloadCallback;
import org.csource.fastdfs.StorageClient;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * FastDFS下载文件
 *
 * @author Mr.Lan
 * @create: 2022-01-23 18:11
 */
public class DownloadFile {
    public static void main(String[] args) {

    }

    /**
     * 从存储服务器上下载文件，返回字节数组，适合下载小文件
     */
    public static void downloadFile1(StorageClient storageClient,String path) throws MyException, IOException {
        byte[] buff = storageClient.download_file("group1","M00/00/00/wKhClmHtTN-AImpNAATjOE0msoA816.png");
        FileUtils.copyInputStreamToFile(new ByteArrayInputStream(buff),new File(path+"xxx.png"));

    }

    /**
     * 下载文件，适合下载大文件
     */
    public static void downloadFile2(StorageClient storageClient,String path) throws MyException, IOException {
        int result = storageClient.download_file("group1", "M00/00/00/wKhClmHtXraATMN-AFRcTtRKgz4058.mp4", new DownloadCallback() {
            long current_bytes = 0;
            FileOutputStream fos = null;

            @Override
            public int recv(long fileSize, byte[] data, int bytes) {
                try {
                    if(fos == null){
                        fos = new FileOutputStream(path+"agc.mp4");
                    }
                    fos.write(data,0,bytes);
                    current_bytes += bytes;
                    System.out.println("current_bytes = "+current_bytes);
                    if(current_bytes == fileSize){
                        fos.close();
                        fos = null;
                        current_bytes = 0;
                    }
                }catch (IOException e){
                    e.printStackTrace();
                    return -1;//下载失败
                }
                return 0;//下载成功
            }
        });
        System.out.println("result = "+result+"[0成功，非0失败]");
    }

    /**
     * 下载文件
     */
    public static void downloadFile3(StorageClient storageClient) throws MyException, IOException {
        int result = storageClient.download_file("group1","MOO/xxxx.png","dfdsjf.png");
        System.out.println("result = "+result+"[0成功，非0失败]");
    }

    /**
     * 使用FileUtils下载Url
     */
    public static void downloadByHttp() throws IOException {
        String url = "http://xxxxx/MOO/xxx.png";
        FileUtils.copyURLToFile(new URL(url),new File("./download/xx.png"));
    }


}
