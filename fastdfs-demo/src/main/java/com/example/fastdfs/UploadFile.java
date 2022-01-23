package com.example.fastdfs;

import org.apache.commons.io.FileUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * FastDFS上传文件
 *
 * @author Mr.Lan
 * @create: 2022-01-23 17:42
 */
public class UploadFile {
    public static String originalFileName = "";
    public static String proPathPre;

    static {
        try {
            proPathPre = new ClassPathResource("").getFile().getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String uploadPathPre = proPathPre+"\\upload\\";
    public static String downloadPathPre = proPathPre+"\\download\\";

    public static void main(String[] args) throws IOException, MyException {
        //加载配置文件
        //使用org.springframework.core.io.ClassPathResource，各种环境都能读取。（通用）
        //使用org.springframework.util.ResourceUtils，读取。在linux环境中无法读取。（不通用）
        ClientGlobal.init(new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath());
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);

        //UploadFile.uploadFileByArr(storageClient);
        //UploadFile.uploadFileByCallback(storageClient);
        //DeleteFile.deleteFile(storageClient);
        //DownloadFile.downloadFile1(storageClient,downloadPathPre);
        //DownloadFile.downloadFile2(storageClient,downloadPathPre);
    }

    /**
     * 记录文件名
     */
    public static void recordFileName(String[] upload_file) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (String str : upload_file){
            System.out.println(str);
            sb.append(str).append("#");
            //上传文件后返回一个数组，这个数组[0]是组名，[1]是FastDFS生成的路径+文件名，[2]是文件名，通过uploadfilename.txt存储了下来，因为FastDFS不存储
        }
        sb.append(originalFileName).append("\r\n");
        FileUtils.write(new File(downloadPathPre+"uploadfilename.txt"),sb.toString(), StandardCharsets.UTF_8,true);

    }

    /**
     * 通过字节数组上传文件到存储服务器，适合小文件
     */
    public static void uploadFileByArr(StorageClient storageClient) throws IOException, MyException {
        originalFileName = "01.png";
        byte[] buffer = FileUtils.readFileToByteArray(new File(uploadPathPre+originalFileName));
        String[] uploadFile = storageClient.upload_file(buffer,"png",null);
        recordFileName(uploadFile);
    }

    /**
     * 通过字节数组上传文件到存储服务器并上传到指定组，适合小文件
     */
    public static void uploadFileByArrGroup(StorageClient storageClient) throws IOException, MyException {
        originalFileName = "03.png";
        byte[] buffer = FileUtils.readFileToByteArray(new File(uploadPathPre+originalFileName));
        String[] uploadFile = storageClient.upload_file("group1",buffer,"png",null);
        recordFileName(uploadFile);
    }

    /**
     * 通过文件名上传文件到存储服务器 适合小文件
     */
    public static void uploadFileByName(StorageClient storageClient) throws MyException, IOException {
        originalFileName = "02.png";
        String[] uploadFile = storageClient.upload_file(uploadPathPre+originalFileName,"png",null);
        recordFileName(uploadFile);
    }

    /**
     * 设置上传文件回调上传文件到存储服务器，适合上传大文件
     */
    public static void uploadFileByCallback(StorageClient storageClient) throws MyException, IOException {
        originalFileName = "04.mp4";
        File file = new File(uploadPathPre+originalFileName);
        String[] uploadFile = storageClient.upload_file("group1", file.length(), new UploadCallback() {
            @Override
            public int send(OutputStream out) throws IOException {
                int readBytes = -1;
                byte[] buff = new byte[256*1024];
                FileInputStream fis = new FileInputStream(uploadPathPre+originalFileName);
                try {
                    while ((readBytes = fis.read(buff)) != -1){
                        out.write(buff,0,readBytes);
                    }
                    out.flush();
                }finally {
                    fis.close();
                }
                return 0;
            }
        },"mp4",null);
        recordFileName(uploadFile);
    }


}
