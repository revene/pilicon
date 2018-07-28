package com.pilicon.netty.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public final class Server {
    public static void main(String[] args) {

        //两大线程,一个服务端,一个客户端
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

    }
}