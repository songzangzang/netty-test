/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */


package com.test.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;
import java.net.URI;

/**
 * @author songxibo 
 * @date 2018年4月2日 下午5:35:27
 */
public class NettyHandler extends SimpleChannelInboundHandler<Object>{

    @Override
    protected void channelRead0(ChannelHandlerContext ct, Object arg1) throws Exception {

        System.out.println(arg1);

        if (arg1 instanceof HttpResponse) {

            HttpResponse arg11 = (HttpResponse) arg1;
            System.out.println("type" + arg11.toString());

        }
        if (arg1 instanceof HttpContent) {
            
            HttpContent httpContent = (HttpContent)arg1;
            
            System.out.println("服务端响应信息 " + httpContent.content().toString(CharsetUtil.UTF_8));

        }
        
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("连接 HTTP 服务器");
        URI uri = new URI("http://172.0.0.1:8899");

        String str = "client test message";

        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString(), Unpooled.copiedBuffer(str.getBytes(CharsetUtil.UTF_8)));
        request.headers().set(HttpHeaderNames.HOST, InetAddress.getLocalHost());
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaders.Values.CLOSE);
        request.headers().set(HttpHeaderNames.ACCEPT_LANGUAGE, str.length());
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

        ctx.channel().writeAndFlush(request);
                
    }

}
