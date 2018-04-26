package com.test.netty.action.sayhi.block;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞版本服务端
 *
 * @author songxibo
 * @date 2018/4/20下午5:11
 */
public class SayHiServerSovket {

    public static void main(String[] args) throws Exception {


        ServerSocket serverSocket = new ServerSocket(9999);
        Socket accept = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        String meg;
        while ((meg = in.readLine()) != null) {
            System.out.println(meg);
        }

        accept.close();
        serverSocket.close();

    }


}
