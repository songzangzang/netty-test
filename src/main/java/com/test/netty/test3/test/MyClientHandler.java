package com.test.netty.test3.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author kaeraier
 * @date 2018/1/19下午1:23
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println(msg);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("成功连接上服务端");

    }




}
