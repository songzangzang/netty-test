package com.test.netty.test2.test;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import com.test.netty.test2.test.model.ByteBufToBytes;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.time.LocalDate;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

/**
 * @author songxibo
 * @date 2018/1/17下午11:36
 */
public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private ByteBufToBytes reader;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest) {

            HttpRequest request = (HttpRequest) msg;
            System.out.println("请求头信息 -> " + request.headers());

            if (HttpUtil.isContentLengthSet(request)) {

                reader = new ByteBufToBytes((int) HttpUtil.getContentLength(request));

            }

        }
        if (msg instanceof HttpContent) {

            HttpContent httpContent = (HttpContent) msg;
            ByteBuf content = httpContent.content();
            reader.reading(content);
            content.release();
            if (reader.isEnd()) {

                String resultStr = new String(reader.readFull());
                System.out.println("客户端发送信息 -> " + resultStr);

                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK, Unpooled.wrappedBuffer(("成功收到你的请求").getBytes()));
                response.headers().set(CONTENT_TYPE, "text/plain");
                response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
                response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                ctx.writeAndFlush(response);
            }

        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();

        ctx.channel().close();

    }

}
