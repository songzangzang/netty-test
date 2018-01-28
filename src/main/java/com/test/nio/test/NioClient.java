package com.test.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * @author songxibo
 * @date 2018/1/27下午11:59
 */
public class NioClient {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 9999));

        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {

            int select = selector.select();

            if (select == 0) {

                continue;

            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            selectionKeys.forEach(key -> {
                if (key.isConnectable()) {

                    SocketChannel channel = (SocketChannel) key.channel();

                    if (channel.isConnectionPending()) {

                        try {

                            ByteBuffer writeByteBuffer = ByteBuffer.allocate(4096);
                            channel.finishConnect();
                            channel.configureBlocking(false);

                            writeByteBuffer.put("喂喂喂！我是客户端".getBytes());
                            writeByteBuffer.flip();
                            channel.write(writeByteBuffer);
                            channel.register(selector, SelectionKey.OP_READ);

                        } catch (IOException e) {

                            System.out.println("socket channel send message failure");

                        }

                    }

                }
                if (key.isReadable()) {

                    SocketChannel readChannel = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(4096);

                    int readCount = 0;
                    try {

                        readCount = readChannel.read(readBuffer);

                        if (readCount > 0) {

                            readBuffer.flip();

                            Charset charset = Charset.forName("utf-8");
                            String readStr = String.valueOf(charset.decode(readBuffer));
                            System.out.println(readStr);

                        }

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                }

                key.channel();

            });

        }

    }

}
