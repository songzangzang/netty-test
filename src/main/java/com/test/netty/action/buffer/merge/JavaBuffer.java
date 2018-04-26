package com.test.netty.action.buffer.merge;

import java.nio.ByteBuffer;

/**
 * Scatter Gather
 *
 * @author songxibo
 * @date 2018/4/24下午7:43
 */
public class JavaBuffer {

    public static void main(String[] args) {

        ByteBuffer heap1 = ByteBuffer.allocate(10);
        ByteBuffer heap2 = ByteBuffer.allocate(10);

        heap1.put("Say Hi!".getBytes());
        heap2.put("Say HO!".getBytes());

        heap1.flip();
        heap2.flip();

        ByteBuffer[] byteBuffers = new ByteBuffer[]{heap1, heap2};

        ByteBuffer byteBuffer = ByteBuffer.allocate(heap1.remaining() + heap2.remaining());
        byteBuffer.put(byteBuffers[0]);
        byteBuffer.put(byteBuffers[1]);

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }

    }

}
