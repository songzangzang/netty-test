package com.test.nio;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Socket通信
 *
 * @author songxibo
 * @date 2017/12/21上午11:32
 */
public class Test12 {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 9999);

        OutputStream os = socket.getOutputStream();

        os.write("hello world".getBytes());
        os.flush();
        socket.shutdownOutput();

    }

}
