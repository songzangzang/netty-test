package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio网络通信
 *
 * @author songxibo
 * @date 2017/12/30上午8:49
 */
public class Test13 {

    public static void main(String[] args) throws IOException {

        int[] ports = new int[5];

        ports[0] = 7777;
        ports[1] = 7778;
        ports[2] = 7779;
        ports[3] = 7780;
        ports[4] = 7781;

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(ports[i]));

            // 把channel注册到Selector内，SelectionKey可以获取对应的Channel
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("开始监听端口号 ：" + ports[i]);

        }

        while (true) {

            int number = selector.select();
            System.out.println("number : " + number);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys : " + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    // 清空selectionKey内容
                    iterator.remove();

                } else if (key.isReadable()) {


                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    int readNumber = 0;
                    while (true) {

                        ByteBuffer buffer = ByteBuffer.allocate(999);
                        buffer.clear();
                        int read = socketChannel.read(buffer);

                        if (read <= 0) {

                            break;

                        }

                        buffer.flip();

                        socketChannel.write(buffer);

                        readNumber += read;
                        System.out.println("读取长度为：" + readNumber + "读取内容：" + buffer);
                    }

                    iterator.remove();

                }

            }

        }

    }

}
