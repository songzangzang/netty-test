package com.test.netty.test2.test;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author kaeraier
 * @date 2018/1/18上午12:11
 */
public class TestClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpCodec", new HttpServerCodec());
        pipeline.addLast("myhandler", new TestClientHandler());

    }

}
