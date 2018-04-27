package com.test.netty.action.reactor;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 处理器
 *
 * @author songxibo
 * @date 2018/4/27下午3:30
 */
public class Handler implements Runnable {

    final SocketChannel socket;

    final SelectionKey sk;

    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);

    static final int READING = 0, SENDING = 1;

    int status = READING;

    public Handler(Selector selector, SocketChannel socket) throws Exception {

        this.socket = socket;
        socket.configureBlocking(false);

        // 尝试读取第一个
        sk = socket.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();

    }

    @Override
    public void run() {

        try {

            if (status == READING) {

                System.out.println("READ");
                read();

            } else if (status == SENDING) {

                System.out.println("SEND");
                send();

            }

        } catch (Exception e) {

        }

    }

    void read() throws Exception {

        socket.read(input);

        input.flip();
        System.out.println(new String(input.array()));

        if (true) {

            status = SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);

        }

    }

    void send() throws Exception {

        output.put("send".getBytes());
        socket.write(output);
        sk.cancel();

    }

}
