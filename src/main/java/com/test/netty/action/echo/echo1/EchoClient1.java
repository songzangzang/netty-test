package com.test.netty.action.echo.echo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author songxibo
 * @date 2018/4/17下午6:56
 */
public class EchoClient1 {

    private final String host;

    private final int port;

    public EchoClient1 (String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start () throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port)).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new EchoClientHandler1());
                }
            });

            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();

        } finally {

            group.shutdownGracefully().sync();

        }

    }

    public static void main(String[] args) throws Exception{

        new EchoClient1("localhost", 9999).start();

    }

}
