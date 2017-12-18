package com.test.nio;

import java.nio.ByteBuffer;

/**
 * byte buffer可以装入出了boolean的所有原生类型
 *
 * @author songxibo
 * @date 2017/12/17下午9:24
 */
public class Test6 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(15);

        buffer.putInt(1);
        buffer.putLong(2L);
        buffer.putChar('卡');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        
    }

}
