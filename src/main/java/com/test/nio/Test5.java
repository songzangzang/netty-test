package com.test.nio;

import java.nio.ByteBuffer;

/**
 * slice buffer 和 buffer 之间的关系
 *
 * @author songxibo
 * @date 2017/12/17下午8:12
 */
public class Test5 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(15);

        for (int i = 0; i < buffer.capacity(); i++) {

            buffer.put((byte) i);

        }

        buffer.position(3);
        buffer.limit(6);

        // 根据buffer创建slice buffer
        ByteBuffer slice = buffer.slice();
        for (int i = 0; i < slice.capacity(); i++) {

            slice.put(i, (byte) (slice.get(i) * 2));

        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {

            System.out.println(buffer.get());

        }

    }

}
