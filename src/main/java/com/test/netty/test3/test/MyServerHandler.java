package com.test.netty.test3.test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author songxibo
 * @date 2018/1/19下午1:17
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.forEach(channel1 -> {
            if (channel != channel1) {
                channel1.writeAndFlush("【 " + channel.remoteAddress() + " 】- " + msg + "\n");
            } else {
                channel1.writeAndFlush("【自己】- " + msg + "\n");
            }
        });

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        channelGroup.writeAndFlush("【服务端】- " + ctx.channel().remoteAddress() + "已上线 \n");
        channelGroup.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        channelGroup.writeAndFlush("【服务端】- " + ctx.channel().remoteAddress() + "已经下线 \n");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + "已经建立连接");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + "取消建立连接");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }

}
