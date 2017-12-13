package com.test.nio;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * 使用nio存入和取出
 *
 * @author songxibo
 * @date 2017/12/11下午9:51
 */
public class Test1 {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);

        for (int i = 0; i < intBuffer.capacity(); i++) {

            intBuffer.put(new SecureRandom().nextInt(20));

        }

        intBuffer.flip();

        while (intBuffer.hasRemaining()) {

            System.out.println(intBuffer.get());

        }

    }

}
