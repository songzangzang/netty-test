package com.test.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁（共享锁,排它锁）
 *
 * @author songxibo
 * @date 2017/12/20下午9:10
 */
public class Test9 {

    public static void main(String[] args) throws Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/kaeraier/git-kaer-workspace/netty-test/src/main/java/com/test/nio/Test9.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        FileLock lock = channel.lock(0, 5, true);
        System.out.println("valiad: " + lock.isValid());
        System.out.println("shared: " + lock.isShared());

        lock.close();
        randomAccessFile.close();

    }

}
