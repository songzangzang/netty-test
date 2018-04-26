package com.test.netty.action.sayhi.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * nio SayHi 服务端
 *
 * @author songxibo
 * @date 2018/4/20下午5:25
 */
public class SayHiNioServerSocket {

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            selectionKeys.forEach(key -> {
                if (key.isAcceptable()) {
                    System.out.println("收到客户端连接请求");
                    ServerSocketChannel acceptSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel accept = null;
                    try {
                        accept = acceptSocketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    try {
                        socketChannel.read(buffer);
                        buffer.flip();

                        while (buffer.hasRemaining()) {
                            System.out.print((char) buffer.get());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                key.cancel();

            });

        }

    }

}
