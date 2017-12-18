package com.test.nio;

import sun.nio.ch.DirectBuffer;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * DirectBuffer的使用
 *
 * @author songxibo
 * @date 2017/12/18下午9:43
 */
public class Test7 {

    public static void main(String[] args) throws Exception {

        FileInputStream inputStream = new FileInputStream("/Users/kaeraier/git-kaer-workspace/netty-test/src/main/java/com/test/nio/InputFile.txt");

        ByteBuffer buffer = ByteBuffer.allocateDirect(15);

        FileChannel channel = inputStream.getChannel();

        while (true) {
            int read = channel.read(buffer);
            break;
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }

        inputStream.close();

    }

}
