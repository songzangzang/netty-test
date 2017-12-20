package com.test.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 操作堆外内存进行文件读写
 *
 * @author songxibo
 * @date 2017/12/20下午8:56
 */
public class Test8 {

    public static void main(String[] args) throws Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/kaeraier/git-kaer-workspace/netty-test/src/main/java/com/test/nio/Test8.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        // 对内存区域5个字节,并且可以进行读写操作
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE,0,5);

        mappedByteBuffer.put(0, (byte)'a');
        mappedByteBuffer.put(3,(byte)'c');

        mappedByteBuffer.clear();
        randomAccessFile.close();

    }

}
