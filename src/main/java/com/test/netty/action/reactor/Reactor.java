package com.test.netty.action.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Reactor单线程模式
 *
 * @author songxibo
 * @date 2018/4/27下午3:08
 */
public class Reactor implements Runnable {

    /**
     * 选择器
     */
    final Selector selector;

    /**
     * 服务端套接字
     */
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws Exception {

        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);

        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());

    }

    @Override
    public void run() {

        try {

            while (!Thread.interrupted()) {

                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();

                while (it.hasNext()) {
                    dispatch((SelectionKey) (it.next()));
                    selected.clear();
                }

            }

        } catch (IOException e) {

        }

    }

    void dispatch(SelectionKey k) {

        Runnable r = (Runnable) (k.attachment());

        if (r != null) {
            r.run();
        }

    }


    class Acceptor implements Runnable {

        @Override
        public void run() {

            try {

                SocketChannel c = serverSocket.accept();

                if (c != null) {
                    new Handler(selector, c);
                }

            } catch (Exception e) {

            }

        }

    }

}
