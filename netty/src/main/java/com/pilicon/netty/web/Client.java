package com.pilicon.netty.web;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 20000;
    private static final int SLEEP_TIME = 50000;

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket(HOST,PORT);

        new Thread(()->{
            System.out.println("客户端启动成功");
            while (true){
                String message = "hello world";
                System.out.println("客户端发送数据"+message);
                try {
                    socket.getOutputStream().write(message.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    sleep();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static  void sleep() throws InterruptedException {
        Thread.sleep(SLEEP_TIME);
    }
}
