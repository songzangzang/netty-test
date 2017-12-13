package com.test.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 向文件中写入
 *
 * @author songxibo
 * @date 2017/12/11下午10:11
 */
public class Test3 {

    public static void main(String[] args) throws Exception {

        FileOutputStream os = new FileOutputStream("src/webapp/NioTest3");

        byte[] bytes = "kaeraier".getBytes();

        ByteBuffer bytebuffer = ByteBuffer.allocate(99);
        for (int i = 0; i < bytes.length; i++) {

            bytebuffer.put(bytes[i]);

        }

        FileChannel channel = os.getChannel();

        bytebuffer.flip();

        channel.write(bytebuffer);

        os.close();

    }

}
