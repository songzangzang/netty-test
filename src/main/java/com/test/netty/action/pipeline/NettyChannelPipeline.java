package com.test.netty.action.pipeline;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 对ChannelPipeline的基本操作
 *
 * @author songxibo
 * @date 2018/4/26下午1:32
 */
public class NettyChannelPipeline {

    public static void main(String[] args) {

        Channel channel = new NioServerSocketChannel();
        ChannelPipeline pipeline = channel.pipeline();

        ChannelHandler channelHandler1 = new HttpServerCodec();
        pipeline.addLast("handler1", channelHandler1);
        System.out.println(pipeline);

        pipeline.remove("handler1");
        System.out.println(pipeline);

    }

}
