package com.test.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 零拷贝客户端（主要）
 *
 * @author songxibo
 * @date 2018/1/24下午9:51
 */
public class NewIOClient {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        socketChannel.connect(new InetSocketAddress("localhost", 9999));

        // 获取文件通道
        String filePath = "/Users/kaeraier/Downloads/apache-maven-3.5.2-bin.tar.gz";
        FileChannel fileChannel = new FileInputStream(filePath).getChannel();

        long start = System.currentTimeMillis();
        long l = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("传递文件大小: " + l + "所用时间：" + (System.currentTimeMillis() - start));

        fileChannel.close();

    }

}
