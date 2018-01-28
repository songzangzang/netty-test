package com.test.netty.test3.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author kaeraier
 * @date 2018/1/19下午1:12
 */
public class MyServer {

    public static void main(String[] args) throws Exception{

        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boos, work).channel(NioServerSocketChannel.class).childHandler(new MyInitializer());

            ChannelFuture future = bootstrap.bind(9999).sync();

            future.channel().closeFuture().sync();


        } finally {

            boos.shutdownGracefully();
            work.shutdownGracefully();

        }

    }

}
