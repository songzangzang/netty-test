package com.test.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 零拷贝服务端
 *
 * @author songxibo
 * @date 2018/1/24下午8:57
 */
public class NewIOServer {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = socketChannel.socket();
        serverSocket.bind(new InetSocketAddress(9999));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {

            SocketChannel channel = socketChannel.accept();
            // 因为不使用selector所以是否阻塞不会产生影响
            channel.configureBlocking(true);

            int readCount = 0;
            while (-1 != readCount) {

                readCount = channel.read(byteBuffer);
                byteBuffer.clear();

            }


        }

    }

}
