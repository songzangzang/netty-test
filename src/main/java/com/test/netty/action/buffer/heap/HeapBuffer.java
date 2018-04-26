package com.test.netty.action.buffer.heap;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * 常见对堆缓冲区进行操作
 * <p>
 * 使用set赋值时不会改变可读和可写的位置
 *
 * @author songxibo
 * @date 2018/4/24下午2:33
 */
public class HeapBuffer {

    public static void unPooledTest() {

        ByteBuf byteBuf = Unpooled.copiedBuffer("test".getBytes(CharsetUtil.UTF_8));

        System.out.println("初始read index" + byteBuf.readerIndex());
        System.out.println("初始write index" + byteBuf.writerIndex());
        System.out.println(new String(byteBuf.array()));

        while (byteBuf.isReadable()) {

            System.out.println((char) byteBuf.readByte());
            System.out.println("read index " + byteBuf.readerIndex());
            System.out.println("write index " + byteBuf.writerIndex());

        }

    }

    public static void bufferTest() {

        String str = "test";
        ByteBuf buf = Unpooled.buffer(str.length());
        buf.setBytes(buf.readerIndex(), str.getBytes());
        System.out.println(buf.readableBytes());
        System.out.println(buf.writableBytes());
        System.out.println(buf.isReadable());
        System.out.println(new String(buf.array(), CharsetUtil.UTF_8));

    }


    public static void main(String[] args) {

//        unPooledTest();
//        bufferTest();

        ByteBuf byteBuf = Unpooled.buffer(7);
        byteBuf.setBytes(0, "Say Hi!".getBytes());

        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.writableBytes());

        byte[] array = byteBuf.array();
        System.out.println(new String(array, CharsetUtil.UTF_8));

        System.out.println();
        System.out.println(byteBuf.writableBytes());

    }

}
