package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 服务端对多个客户端的数据进行分发
 *
 * @author songxibo
 * @date 2018/1/3下午9:47
 */
public class NioServer {

    /**
     * 用来存储用户通道
     */
    private static Map<String, SocketChannel> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        // 创建服务端,并监听9999端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            int select = selector.select();
            if (select == 0) {

                continue;

            }

            // 获取期望获得的监听事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {

                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

                    try {

                        SocketChannel socketChannel = serverChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        map.put(UUID.randomUUID().toString().replaceAll("-", ""), socketChannel);

                        iterator.remove();

                    } catch (IOException e) {

                        System.out.println("注册OP_READ失败");

                    }


                } else if (key.isReadable()) {

                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                    try {

                        int read = channel.read(readBuffer);
                        if (read > 0) {

                            readBuffer.flip();
                            Charset charset = Charset.forName("utf-8");
                            String sendMessage = String.valueOf(charset.decode(readBuffer).array());
                            System.out.println(channel + sendMessage);

                            final String[] senderKey = new String[1];
                            map.forEach((k, v) -> {
                                if (channel == v) {
                                    senderKey[0] = k;
                                }
                            });

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put((senderKey[0] + ":" +sendMessage).getBytes());
                            map.forEach((k, v) -> {
                                writeBuffer.flip();
                                try {
                                    v.write(writeBuffer);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                        }

                        iterator.remove();

                    } catch (IOException e) {

                        System.out.println("读取信息失败");

                    }

                }

            }

        }

    }

}
