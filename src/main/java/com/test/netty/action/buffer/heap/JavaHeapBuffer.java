package com.test.netty.action.buffer.heap;

import java.nio.ByteBuffer;

/**
 * Java Heap Buffer实现
 *
 * @author songxibo
 * @date 2018/4/24下午3:14
 */
public class JavaHeapBuffer {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Say Hi!".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {

            System.out.print((char) buffer.get());

        }

    }

}
