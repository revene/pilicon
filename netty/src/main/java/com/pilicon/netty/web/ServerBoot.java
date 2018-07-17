package com.pilicon.netty.web;

import java.io.IOException;

public class ServerBoot {

    private static final int PORT = 20000;

    public static void main(String[] args) throws IOException {
        Server server = new Server(PORT);
        server.start();
    }
}
