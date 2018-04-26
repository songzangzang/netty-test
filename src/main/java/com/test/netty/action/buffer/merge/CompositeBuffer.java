package com.test.netty.action.buffer.merge;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Netty操作Buffer合并优化版
 *
 * @author songxibo
 * @date 2018/4/24下午7:31
 */
public class CompositeBuffer {

    public static void main(String[] args) {

        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.copiedBuffer("heap test".getBytes());

        String heapStr = "set heap test";
        ByteBuf heapSetBuf = Unpooled.buffer(heapStr.length());
        heapSetBuf.setBytes(0, heapStr.getBytes());

        String str = "direct test";
        ByteBuf directBuf = Unpooled.directBuffer(str.length());
        directBuf.writeBytes(str.getBytes());

        compositeByteBuf.addComponent(heapBuf);
        compositeByteBuf.addComponent(heapSetBuf);
        compositeByteBuf.addComponent(directBuf);

        compositeByteBuf.forEach(b -> {

            if (b.hasArray()) {

                System.out.println(new String(b.array(), CharsetUtil.UTF_8));

            } else {

                byte[] bytes = new byte[b.readableBytes()];
                b.getBytes(b.readerIndex(), bytes);
                System.out.println(new String(bytes, CharsetUtil.UTF_8));

            }

        });

    }

}
