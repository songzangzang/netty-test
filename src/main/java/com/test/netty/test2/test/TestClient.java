package com.test.netty.test2.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * @author kaeraier
 * @date 2018/1/18上午12:07
 */
public class TestClient {

    public static void main(String[] args) throws Exception {

        EventLoopGroup work = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true).handler(new TestClientInitializer());
            ChannelFuture future = bootstrap.connect("localhost", 9999).sync();
            future.channel().closeFuture().sync();

        } finally {

            work.shutdownGracefully();

        }

    }

}
