package com.test.netty.action.buffer.direct;

import java.nio.ByteBuffer;

/**
 * @author kaeraier
 * @date 2018/4/24下午3:30
 */
public class JavaDirectBuffer {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.put("Say Hi!".getBytes());
        buffer.flip();

        while (buffer.hasRemaining()) {

            System.out.print((char) buffer.get());

        }

    }

}
