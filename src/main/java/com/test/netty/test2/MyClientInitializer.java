package com.test.netty.test2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class MyClientInitializer extends ChannelInitializer<SocketChannel>{


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("stringEncode", new StringEncoder());
        pipeline.addLast("stringDecode", new StringDecoder());
        pipeline.addLast("myHandler", new MyClientHandler());

    }
}
