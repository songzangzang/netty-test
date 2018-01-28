package com.test.netty.test3.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author songxibo
 * @date 2018/1/19下午1:18
 */
public class MyClient {

    public static void main(String[] args) throws Exception {

        EventLoopGroup work = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work).channel(NioSocketChannel.class).handler(new MyClientInitializer());

            Channel channel = bootstrap.connect("localhost", 9999).sync().channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {

                channel.writeAndFlush(bufferedReader.readLine() + "\n");

            }

        } finally {

            work.shutdownGracefully();

        }

    }

}
