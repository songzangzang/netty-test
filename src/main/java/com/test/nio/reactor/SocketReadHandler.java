package com.test.nio.reactor;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author songxibo
 * @date 2018/6/6下午5:45
 */
public class SocketReadHandler implements Runnable{

    private SocketChannel socketChannel;

    public SocketReadHandler (Selector selector, SocketChannel socketChannel) throws Exception{

        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);

        SelectionKey selectionKey = socketChannel.register(selector, 0);

        // 将SelectionKey绑定为SocketReadHandler. 接下来有事件触发，将调用run方法
        selectionKey.attach(this);

        // 将SelectionKey标记为可读，以便读取
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();

    }

    @Override
    public void run() {

        ByteBuffer input = ByteBuffer.allocate(1024);
        input.clear();

        // 使用线程池处理
        // requestHandler(new Request(socket, btt))

    }

}
