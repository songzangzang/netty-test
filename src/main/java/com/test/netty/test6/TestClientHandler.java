package com.test.netty.test6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

public class TestClientHandler extends SimpleChannelInboundHandler<TestDataInfo.Teacher> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TestDataInfo.Teacher msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        TestDataInfo.Teacher teacher = TestDataInfo.Teacher.newBuilder().setName("卡尔大魔王")
                .setAge(23).setAddress("齐齐哈尔").build();

        ctx.writeAndFlush(teacher);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }
}
