package com.test.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞客户端
 *
 * @author songxibo
 * @date 2018/1/23下午10:31
 */
public class OldIOServer {

    public static void main(String[] args) throws Exception {

        ServerSocket socket = new ServerSocket(9999);

        byte[] bytes = new byte[4096];
        int flag = 0;
        int count = 0;

        while (true) {

            Socket accept = socket.accept();
            DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());

            while ((flag = dataInputStream.read(bytes, 0, bytes.length)) >= 0) {

                count += flag;
                // TODO 把数据保存到硬盘上

            }
            System.out.println(count);
            count = 0;

        }

    }

}
