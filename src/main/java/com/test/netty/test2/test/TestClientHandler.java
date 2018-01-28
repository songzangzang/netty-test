package com.test.netty.test2.test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.test.netty.test2.test.model.ByteBufToBytes;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.time.LocalDateTime;

/**
 * @author kaeraier
 * @date 2018/1/18上午12:14
 */
public class TestClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    private ByteBufToBytes reader;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            System.out.println("content type" + response.headers().get(HttpHeaderNames.CONTENT_TYPE));
            if (HttpUtil.isContentLengthSet(response)) {
                reader = new ByteBufToBytes((int) HttpUtil.getContentLength(response));
            }
        }
        if (msg instanceof HttpContent) {

            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(CharsetUtil.UTF_8));

        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String msg = "hello server";
        ByteBuf requestMessage = Unpooled.wrappedBuffer(msg.getBytes(CharsetUtil.UTF_8));
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "你好我是客户端");
        request.headers().set(HttpHeaderNames.HOST, "localhost");
        request.headers().set(HttpHeaderNames.CONNECTION, "keep-alive");
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH, requestMessage.readableBytes());

        ctx.writeAndFlush(request);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();

        ctx.channel().close();

    }
}
