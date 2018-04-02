package com.test.netty.message;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * webSocket服务器
 *
 * @author songxibo
 * @date 2018/3/27下午1:40
 */
public class WebSocketServer {

    public static void main(String[] args) {

        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootStrap = new ServerBootstrap();
            serverBootStrap.group(boos, work).channel(NioServerSocketChannel.class).childHandler(new WebSocketInitializer());

            ChannelFuture channelFuture = serverBootStrap.bind(9999);
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            boos.shutdownGracefully();
            work.shutdownGracefully();

        }

    }

}
