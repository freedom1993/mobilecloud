package com.jlu.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dell on 2017/3/25.
 */
public class P2PServer {

    private final int PORT = 9999;
    private ServerSocket server;
    private String result;

    public String beginTask() {
        try {
            server = new ServerSocket(PORT);
            if (server != null) {
                while (true) {
                    Socket s = server.accept();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("P2P:go to");
                            result = new LogicServer(s).receiveFile();
                            System.out.println(result);
                        }
                    }).start();

                }
            } else {
                result = "端口绑定失败";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("final:"+result);
        return result;
    }



       /* Thread listener = new Thread(new Runnable() {
            @Override
            public void run() {
                //绑定端口
                int port = 9999;
                ServerSocket server = null;
                while (port > 9000) {
                    try {
                        server = new ServerSocket(port);
                        break;
                    } catch (Exception e) {
                        port--;
                    }
                }
                if (server != null) {
                    LogicServer logicServer = new LogicServer(server);
                    while (true) {//接收文件
                        String response = logicServer.ReceiveFile();
                        System.out.println(response);
                    }
                } else {
                    System.out.println("端口绑定失败");
                }
            }


        });
        listener.start();*/
}

