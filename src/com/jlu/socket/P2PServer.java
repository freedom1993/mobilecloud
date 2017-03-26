package com.jlu.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dell on 2017/3/25.
 */
public class P2PServer {

    private static final int SERVERPORT = 9999;
    private ServerSocket MyServer = null;
    private List<Socket> Clients = new ArrayList<Socket>();
    public P2PServer() {
        try {
            MyServer = new ServerSocket(SERVERPORT);
            mExecutorService = Executors.newCachedThreadPool();//使用线程池
            System.out.println("start:");
            Socket MySocket = null;
            while (true) {
                MySocket = MyServer.accept();
                Clients.add(MySocket);
                mExecutorService.execute(new LogicServer(MySocket,Clients));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private ExecutorService mExecutorService;
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


