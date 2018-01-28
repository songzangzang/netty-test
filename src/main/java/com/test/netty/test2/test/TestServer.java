package com.test.netty.test2.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author kaeraier
 * @date 2018/1/17下午11:04
 */
public class TestServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boos, work).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new TestServerInitializer()).childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {

            boos.shutdownGracefully();
            work.shutdownGracefully();

        }

    }

}
