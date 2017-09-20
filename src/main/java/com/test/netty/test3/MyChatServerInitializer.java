package com.test.netty.test3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.logging.SocketHandler;

public class MyChatServerInitializer extends SimpleChannelInboundHandler<SocketHandler>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketHandler msg) throws Exception {

        ChannelPipeline pipeline = ctx.pipeline();

        pipeline.addLast(new DelimiterBasedFrameDecoder(4));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new MyChatServerHandler());

    }
}
