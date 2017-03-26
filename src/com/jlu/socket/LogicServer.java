package com.jlu.socket;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by Dell on 2017/3/22.
 */
public class LogicServer implements Runnable{
    private Socket msocket = null;
    private InputStream in = null;
    private PrintWriter out = null;
    private BufferedReader br = null;
    private String mStrMSG = null;
    private List<Socket> clients=null;

    public LogicServer(Socket socket,List<Socket> clients) {
        try {
            this.msocket = socket;
            this.clients=clients;
            in = msocket.getInputStream();
            mStrMSG = "user:" + msocket.getInetAddress() + " come total:"
                    + clients.size();
            //SendMassage();
            //receiveFile();
        } catch (IOException e) {
            System.out.println("erorr");
            System.exit(0);
        }
    }

    /*private void SendMassage() {
        try {
            System.out.println(mStrMSG);
            for (Socket MySocket : clients) {
                out = new PrintWriter(new OutputStreamWriter(
                        MySocket.getOutputStream(), "utf-8"), true);
                out.println(mStrMSG);
            }
        } catch (IOException e) {
            System.out.println("erorr");
            System.exit(0);
        }
    }*/

    //接收文件
    public String receiveFile() {
        try {
            System.out.println(mStrMSG);
            //接收文件名
            br = new BufferedReader(new InputStreamReader(in));
            String fileName = br.readLine();
            //接收文件数据
            String savePath =System.getProperty("user.dir")+"/recfile/"+fileName;//获取文件存入路径
            FileOutputStream file = new FileOutputStream(savePath, false);
            byte[] buffer = new byte[1024];
            int size = -1;
            while ((size = in.read(buffer)) != -1) {
                file.write(buffer, 0, size);
            }
            return fileName + " 接收完成";
        } catch (Exception e) {
            return "接收错误:\n" + e.getMessage();
        }
    }

    @Override
    public void run() {
        try {

            mStrMSG = msocket.getInetAddress() + ":" + mStrMSG;
            receiveFile();
           /* while ((mStrMSG = br.readLine()) != null) {

               //退出代码以后再完善
                if (mStrMSG.trim().equals("exit")) {
                    clients.remove(msocket);
                    in.close();
                    out.close();
                    mStrMSG = "user:" + msocket.getInetAddress()
                            + " exit tatal:" + clients.size();
                    ;
                    msocket.close();
                   // SendMassage();
                    break;
                } else {
                    mStrMSG = msocket.getInetAddress() + ":" + mStrMSG;
                    receiveFile();
                }
            }*/
        } catch (Exception e) {
            System.out.println("erorr");
            System.exit(0);
        }

    }
}
