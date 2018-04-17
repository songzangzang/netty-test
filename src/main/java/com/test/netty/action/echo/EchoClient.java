package com.test.netty.action.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author songxibo
 * @date 2018/4/17上午11:04
 */
public class EchoClient {

    private String host;

    private int port;

    public EchoClient(String host, int port) {

        this.host = host;
        this.port = port;

    }

    public void start() throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new EchoClientChannelInitializer());
            ChannelFuture connect = bootstrap.remoteAddress(new InetSocketAddress(host, port)).connect().sync();

            connect.channel().closeFuture().sync();

        } finally {

            group.shutdownGracefully().sync();

        }

    }

    public static void main(String[] args) throws Exception{

        new EchoClient("localhost", 9999).start();

    }

}
