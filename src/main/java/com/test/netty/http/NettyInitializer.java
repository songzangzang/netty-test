/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */


package com.test.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author songxibo 
 * @date 2018年4月2日 下午5:33:07
 */
public class NettyInitializer extends ChannelInitializer{

    @Override
    protected void initChannel(Channel channel) throws Exception {
        
        ChannelPipeline pipeline = channel.pipeline();
        
    pipeline.addLast(new HttpClientCodec());
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpObjectAggregator(1048576));
        pipeline.addLast(new NettyHandler());
        
    }

}
