package com.jlu.socket;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Dell on 2017/3/22.
 */
public class LogicServer {
    private Socket server;

    public LogicServer(Socket server) {
        this.server = server;
    }

    //接收文件
    public String receiveFile() {
        try {
            System.out.println("Logic:go to");
            //接收文件名
            InputStream fileStream = server.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(fileStream);
            BufferedReader br = new BufferedReader(streamReader);
            String fileName = br.readLine();
            //接收文件数据
            String savePath =System.getProperty("user.dir")+"/recfile/"+fileName;//获取文件存入路径
            FileOutputStream file = new FileOutputStream(savePath, false);
            byte[] buffer = new byte[1024];
            int size = -1;
            while ((size = fileStream.read(buffer)) != -1) {
                file.write(buffer, 0, size);
            }
            return fileName + " 接收完成";
        } catch (Exception e) {
            return "接收错误:\n" + e.getMessage();
        }
    }

}
