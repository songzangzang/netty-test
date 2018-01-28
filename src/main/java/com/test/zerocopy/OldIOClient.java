package com.test.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 阻塞客户端
 *
 * @author songxibo
 * @date 2018/1/23下午10:51
 */
public class OldIOClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 9999));

        byte[] bytes = new byte[4096];

        int flag = 0;
        int count = 0;

        // 输出
        InputStream inputStream = new FileInputStream("/Users/kaeraier/Downloads/Parallels_Desktop_13.2.0_43213_xclient.info.dmg");

        long time = System.currentTimeMillis();
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        while ((flag = inputStream.read(bytes)) >= 0) {

            if (flag == -1) {
                break;
            }

            count += flag;
            dataOutputStream.write(bytes);
            dataOutputStream.flush();

        }
        System.out.println("用时 ->" + (System.currentTimeMillis() - time));

        dataOutputStream.close();
        inputStream.close();
        socket.close();

    }

}
