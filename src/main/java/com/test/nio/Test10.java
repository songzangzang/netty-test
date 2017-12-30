package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Buffer的Scatting和Gathering
 *
 * @author songxibo
 * @date 2017/12/20下午9:18
 */
public class Test10 {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel channel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(9999);
        channel.socket().bind(address);

        int responseSize = 3 + 3 + 5;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(3);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(5);

        while (true) {

            SocketChannel socketChannel = channel.accept();

            Thread thread = new Thread(() -> {
                // 读取数据
                int readByteSize = 0;
                while (readByteSize < responseSize) {

                    long read = 0;
                    try {
                        read = socketChannel.read(buffers);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    readByteSize += read;

                    Arrays.asList(buffers).forEach(System.out::println);

                }

                Arrays.asList(buffers).forEach(ByteBuffer::flip);

                // 写回响应
                int writeByteSize = 0;
                while (writeByteSize < responseSize) {

                    long write = 0;
                    try {
                        write = socketChannel.write(buffers);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    writeByteSize += write;

                }

                Arrays.asList(buffers).stream().forEach(buffer -> {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.print((char) buffer.get());
                    }
                    buffer.clear();

                });
            });

            thread.run();

        }

    }

}