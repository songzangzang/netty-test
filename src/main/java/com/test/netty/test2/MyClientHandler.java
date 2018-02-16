package com.test.netty.test2;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.UUID;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println(msg);
        ctx.writeAndFlush("client " + UUID.randomUUID());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush("我所在的时间为" + LocalDateTime.now());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }
}
