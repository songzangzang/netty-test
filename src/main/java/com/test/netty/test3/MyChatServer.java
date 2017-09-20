package com.test.netty.test3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * 服务端：有一个服务端会有多个客户端和它进行连接
 * 第一个客户端连接不会出发
 * 第二个客户端,第三个会给前面先通信的客户端发送XXX客户端已经上线
 *
 */
public class MyChatServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boos, work).channel(NioServerSocketChannel.class)
                    .childHandler(new MyChatServerInitializer());

            ChannelFuture future = bootstrap.bind(9999).sync();
            future.channel().closeFuture().sync();

        } finally {

            boos.shutdownGracefully();
            work.shutdownGracefully();

        }

    }

}
