package com.test.nio.reactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 *
 * @author songxibo
 * @date 2018/3/26上午7:20
 */
public class Server implements Runnable {

    @Override
    public void run() {

        try {

            ServerSocket serverSocket = new ServerSocket(8899);
            while (!Thread.interrupted()) {

                // or single-threaded or a thread pool
                new Thread(new Handler(serverSocket.accept())).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author songxibo
     * @date 2018/3/26上午8:51
     */
    static class Handler implements Runnable{

        final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {

                byte[] input = new byte[Integer.MAX_VALUE];
                socket.getInputStream().read(input);

                byte[] outPut = process(input);
                socket.getOutputStream().write(outPut);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private byte[] process (byte[] cmd) {

            for (int i = 0; i < cmd.length; i++) {

                System.out.println((char)cmd[i]);

            }

            return cmd;

        }

    }

}
