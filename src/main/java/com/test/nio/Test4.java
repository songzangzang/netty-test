package com.test.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO文件读取和写入
 *
 * @author songxibo
 * @date 2017/12/16下午12:17
 */
public class Test4 {

    public static void main(String[] args) throws Exception{

        FileInputStream is = new FileInputStream("/Users/kaeraier/git-kaer-workspace/netty-test/src/main/java/com/test/nio/InputFile.txt");
        FileOutputStream os = new FileOutputStream("/Users/kaeraier/git-kaer-workspace/netty-test/src/main/java/com/test/nio/OutputFile.txt");

        ByteBuffer buffer = ByteBuffer.allocate(15);

        FileChannel ischannel = is.getChannel();
        FileChannel oschannel = os.getChannel();

        while (true) {

            // 初始化position, 防止buffer满了读不进去
            buffer.clear();

            int read = ischannel.read(buffer);
            if (-1 == read) {
                break;
            }

            buffer.flip();
            oschannel.write(buffer);

        }

        is.close();
        os.close();

    }

}
