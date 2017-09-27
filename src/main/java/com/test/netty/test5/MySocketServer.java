package com.test.netty.test5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * 使用webSocket进行通信
 */
public class MySocketServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boos, worker).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MySocketInitializer());

            ChannelFuture future = bootstrap.bind(new InetSocketAddress(9999)).sync();
            future.channel().closeFuture().sync();

        } finally {

            boos.shutdownGracefully();
            worker.shutdownGracefully();

        }

    }

}
