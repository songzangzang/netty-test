package com.test.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Nio客户端进行消息通信
 *
 * @author songxibo
 * @date 2018/1/4下午3:44
 */
public class NioClient {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost", 9999));

        while (true) {

            int select = selector.select();
            if (0 == select) {

                continue;

            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {

                final SelectionKey key = iterator.next();

                if (key.isConnectable()) {

                    SocketChannel channel = (SocketChannel) key.channel();

                    if (channel.isConnectionPending()) {

                        try {

                            channel.finishConnect();
                            System.out.println("连接成功");

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put(("NioClient send : " + UUID.randomUUID().toString()).getBytes());
                            writeBuffer.flip();
                            channel.write(writeBuffer);

                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(() -> {
                                while (true) {
                                    try {
                                        writeBuffer.clear();
                                        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                                        String sendMessage = bufferedReader.readLine();

                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        channel.write(writeBuffer);

                                    } catch (Exception e) {

                                        e.printStackTrace();

                                    }

                                }
                            });

                        } catch (IOException e) {

                            e.printStackTrace();

                        }

                    }

                    channel.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {

                    SocketChannel serverRequest = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                    try {

                        int read = serverRequest.read(readBuffer);
                        if (read > 0) {
//                            String receivedMessage = new String(readBuffer.array(), 0, read);
                            readBuffer.flip();
                            Charset charset = Charset.forName("utf-8");
                            char[] array = charset.decode(readBuffer).array();
                            System.out.println(String.valueOf(array));
                        }

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                }

                selectionKeys.clear();

            }

        }

    }

}
