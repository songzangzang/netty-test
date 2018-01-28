package com.test.nio.test;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * @author songxibo
 * @date 2018/1/27下午11:26
 */
public class NioServer {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置为不阻塞,才可以使用Selector
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            // 是否有可用的通道
            int select = selector.select();

            if (select == 0) {

                continue;

            }

            // 获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.stream().forEach(key -> {
                if (key.isAcceptable()) {

                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();

                    try {

                        SocketChannel socketChannel = channel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } catch (IOException e) {

                        System.out.println("connection register op_read failure");

                    }

                }
                if (key.isReadable()) {

                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
                    ByteBuffer writeBuffer = ByteBuffer.allocate(4096);

                    int readCount = 0;

                    try {

                        readCount = socketChannel.read(byteBuffer);

                        if (readCount > 0) {

                            byteBuffer.flip();

                            Charset charset = Charset.forName("utf-8");
                            String sendStr = String.valueOf(charset.decode(byteBuffer));
                            System.out.println(sendStr);

                            writeBuffer.put("已经收到你的请求了，我是服务端".getBytes());
                            writeBuffer.flip();
                            socketChannel.write(writeBuffer);

                        }

                    } catch (IOException e) {

                        System.out.println("socket channel read failure");
                    }

                }

                key.cancel();

            });

        }

    }

}
