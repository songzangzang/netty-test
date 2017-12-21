package com.test.nio;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket通信 服务端
 *
 * @author songxibo
 * @date 2017/12/21上午11:21
 */
public class Test11 {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress address = new InetSocketAddress(9999);
        serverSocket.bind(address);

        while (true) {

            Socket socket = serverSocket.accept();

            InputStream is = socket.getInputStream();

            byte[] buffer = new byte[256];
            int line = 0;
            while (-1 != (line = is.read(buffer))) {

                for (int i = 0; i < line; i++) {
                    System.out.print((char) buffer[i]);
                }

            }

        }

    }

}
