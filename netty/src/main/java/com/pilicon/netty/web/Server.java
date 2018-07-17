package com.pilicon.netty.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        this.serverSocket=new ServerSocket(port);
        System.out.println("服务端启动成功,端口号为"+port);
    }

    public void start(){
        new Thread(()->{
            try {
                doStart();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void doStart() throws  IOException {
        while (true){
            Socket client=serverSocket.accept();

        }
    }
}
