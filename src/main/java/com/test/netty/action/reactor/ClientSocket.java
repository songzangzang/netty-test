package com.test.netty.action.reactor;

import java.io.IOException;
import java.net.Socket;

/**
 * @author songxibo
 * @date 2018/4/27下午4:32
 */
public class ClientSocket {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 9999);

            socket.getOutputStream().write("test".getBytes());

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
