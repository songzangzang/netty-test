package com.test.nio.reactor;


import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable{

    private Reactor reactor;

    public Acceptor(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public void run() {

        try {

            SocketChannel socketChannel = reactor.serverSocketChannel.accept();
            if (socketChannel != null) {

                new SocketReadHandler(reactor.selector, socketChannel);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
