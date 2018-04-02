package com.test.netty.message;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 维护连接通道
 *
 * @author songxibo
 * @date 2018/3/27下午5:09
 */
public class GroupContext {

    /**
     * 维护当前上下文的通道
     */
    public static ConcurrentHashMap<String, Channel> CONCURRENT_MAP = new ConcurrentHashMap();

    public static void send (String name, String content) {

        Channel channel = CONCURRENT_MAP.get(name);

        if (channel != null) {

            channel.writeAndFlush(new TextWebSocketFrame(name + content));

        } else {

            System.out.println("没有对应的通道");

        }


    }

}
