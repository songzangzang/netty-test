package com.test.netty.action.buffer.direct;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Netty直接操作内存空间
 * 解决：内容从系统空间拷贝到用户空间，或从用户空间拷贝到用户空间，基于Java Nio进行实现
 * 缺点：直接内存进行操作的是堆外内存对开启和销毁的代价较大
 *
 * @author songxibo
 * @date 2018/4/24下午3:24
 */
public class DirectBuffer {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.directBuffer(7);
        byteBuf.setBytes(0, "Say Hi!".getBytes());

        byte[] bytes = new byte[7];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes);
        System.out.println(new String(bytes, CharsetUtil.UTF_8));

    }

}
