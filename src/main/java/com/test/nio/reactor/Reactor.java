package com.test.nio.reactor;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Optional;
import java.util.Set;

/**
 * 反应器模式
 * 用于解决多用户访问并发问题
 * <p>
 * eg: 餐厅服务问题
 * <p>
 * 传统线程池: 来一个客人(请求)去一个服务员(线程)
 * 反应器模式: 当客人点菜的时候，服务员就可以去招呼其他客人了，等客人点好了菜，直接召唤一声服务员
 *
 * @author songxibo
 * @date 2018/6/6下午5:19
 */
public class Reactor implements Runnable {

    public final Selector selector;

    public final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws Exception {

        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
        serverSocketChannel.configureBlocking(false);

        // 注册
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 利用selectionKey的attache功能绑定Acceptor 如果有事件触发Acceptor
        selectionKey.attach(new Acceptor(this));

    }


    @Override
    public void run() {

        try {

            while (!Thread.interrupted()) {

                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(s -> {
                    dispatcher(s);
                    selectionKeys.clear();
                });

            }

        } catch (Exception e) {

        }

    }

    void dispatcher(SelectionKey key) {

        Runnable run = (Runnable) key.attachment();

        Optional.ofNullable(run).ifPresent(r -> r.run());

    }


}
