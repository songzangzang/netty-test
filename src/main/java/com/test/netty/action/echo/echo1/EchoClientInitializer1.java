package com.test.netty.action.echo.echo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * @author songxibo
 * @date 2018/4/17下午3:37
 */
public class EchoClientInitializer1 extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new EchoClientHandler1());

    }

}

