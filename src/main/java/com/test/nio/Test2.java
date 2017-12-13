package com.test.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 读取文件并进行打印
 *
 * @author songxibo
 * @date 2017/12/11下午10:02
 */
public class Test2 {

    public static void main(String[] args) throws Exception {

        FileInputStream is = new FileInputStream("src/webapp/NioTest2");
        FileChannel channel = is.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        channel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {

            System.out.print((char)byteBuffer.get());

        }

        is.close();

    }

}
