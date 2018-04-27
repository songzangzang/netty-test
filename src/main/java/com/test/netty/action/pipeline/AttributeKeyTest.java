package com.test.netty.action.pipeline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * 测试AttributeKey
 *
 * @author songxibo
 * @date 2018/4/26下午3:29
 */
public class AttributeKeyTest {

    public static void main(String[] args) throws Exception{

        AttributeKeyTest test = new AttributeKeyTest();
        test.testThread();

    }

    public void testThread() throws Exception {

        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();

            ChannelFuture future = bootstrap.group(boos, work).channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer())
                    .childAttr(AttributeKey.newInstance("content"), "test")
                    .localAddress(9999).bind().sync();

            future.channel().closeFuture().sync();

        } finally {

            boos.shutdownGracefully().sync();
            work.shutdownGracefully().sync();

        }

    }

    class MyChannelInitializer extends ChannelInitializer {

        private MyChannelHandler1 myChannelHandler1 = new MyChannelHandler1();

        @Override
        protected void initChannel(Channel ch) throws Exception {

            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast("handler1", myChannelHandler1);
            pipeline.addLast("handler2", myChannelHandler1);

        }

    }

    class MyChannelHandler1 extends SimpleChannelInboundHandler {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

            System.out.println(AttributeKey.valueOf("content"));

        }
    }


}
