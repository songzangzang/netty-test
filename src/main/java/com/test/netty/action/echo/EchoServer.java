package com.test.netty.action.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author songxibo
 * @date 2018/4/17上午11:04
 */
public class EchoServer {

    private String host;

    private int port;

    public EchoServer(String host, int port) {

        this.host = host;
        this.port = port;

    }

    /**
     * 启动服务
     */
    public void start() {

        EventLoopGroup group = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {

            ChannelFuture future = bootstrap.group(group).channel(NioServerSocketChannel.class).localAddress(port).childHandler(new EchoServerChannelInitializer()).bind().sync();

            future.channel().closeFuture().sync();

            group.shutdownGracefully().sync();

        } catch (InterruptedException e) {

            System.out.println("echo server failure :" + e);

        }

    }

    public static void main(String[] args) {

        new EchoServer("localhost", 9999).start();

    }

}
