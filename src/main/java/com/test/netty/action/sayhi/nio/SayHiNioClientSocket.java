package com.test.netty.action.sayhi.nio;

import io.netty.buffer.ByteBufUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * nio客户端传入SayHi
 *
 * @author songxibo
 * @date 2018/4/20下午5:43
 */
public class SayHiNioClientSocket {

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost", 9999));

        while (true) {

            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(key -> {
                if (key.isConnectable()) {
                    SocketChannel currentChannel = (SocketChannel) key.channel();
                    if (currentChannel.isConnectionPending()) {
                        try {
                            currentChannel.finishConnect();

                            String requestMessage = "Say Hi";
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put(requestMessage.getBytes());
                            writeBuffer.flip();
                            currentChannel.write(writeBuffer);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                key.cancel();
            });

        }

    }

}


