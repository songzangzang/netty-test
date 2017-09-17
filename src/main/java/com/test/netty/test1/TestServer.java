package com.test.netty.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty 可以进行RPC通信的框架
 * 可以用来充当http服务器
 * netty没有对Servlet的规范进行实现
 *
 * 第一个程序是对服务器进行的实现
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {

        /*
            创建两个时间组
            boos用来进行接收
            work用来处理
         */
        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {

        /**
         * 用来启动服务端，对启动进行了基本的封装
         * group 传入的一个线程组进行接收然后转给第二个线程组
         * childHandle 子处理器我们可以对传入的参数进行逻辑处理
         */
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boos, work).channel(NioServerSocketChannel.class)
                .childHandler(new TestServerInitializer());

        // 设置端口号
        ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
        channelFuture.channel().closeFuture().sync();

        } finally {
        // 关闭监听

            boos.shutdownGracefully();
            work.shutdownGracefully();

        }

    }


}
