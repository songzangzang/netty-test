package com.test.netty.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * webSocket 处理器
 *
 * @author songxibo
 * @date 2018/3/27下午1:48
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

        String text = textWebSocketFrame.text();
        GroupContext.CONCURRENT_MAP.put(text, channelHandlerContext.channel());
        System.out.println(GroupContext.CONCURRENT_MAP);

        GroupContext.send("宋喜博", "有新消息");

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {


    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        GroupContext.CONCURRENT_MAP.forEach((k, v) -> {

            if (v.equals(ctx.channel())) {

                GroupContext.CONCURRENT_MAP.remove(k);

            }

        });

        System.out.println(GroupContext.CONCURRENT_MAP);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.channel().close();

    }

}
