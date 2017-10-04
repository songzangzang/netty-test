package com.test.netty.test6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

public class TestServerHandler extends SimpleChannelInboundHandler<TestDataInfo.Teacher> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TestDataInfo.Teacher msg) throws Exception {

        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }
}
