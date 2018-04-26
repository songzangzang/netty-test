package com.test.netty.action.sayhi.block;

import io.netty.util.CharsetUtil;

import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端阻塞调用SayHi
 *
 * @author songxibo
 * @date 2018/4/20下午5:20
 */
public class SayHiClientSocket {

    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", 9999);
        OutputStream out = socket.getOutputStream();
        out.write("Say Hi".getBytes(CharsetUtil.UTF_8));
        out.flush();

        socket.close();

    }

}
