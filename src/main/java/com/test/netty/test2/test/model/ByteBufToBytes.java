package com.test.netty.test2.test.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * ByteBuf工具类
 *
 * @author songxibo
 * @date 2018/1/27下午5:13
 */
public class ByteBufToBytes {

    private ByteBuf temp;

    private boolean end = true;

    public ByteBufToBytes(int length) {

        temp = Unpooled.buffer(length);

    }

    public void reading(ByteBuf datas) {

        datas.readBytes(temp, datas.readableBytes());
        if (temp.writableBytes() != 0) {
            end = false;
        } else {
            end = true;
        }

    }

    public boolean isEnd() {

        return end;

    }

    public byte[] readFull() {

        if (end) {

            byte[] contentByte = new byte[temp.readableBytes()];
            temp.readBytes(contentByte);
            temp.release();

            return contentByte;

        } else {

            return null;

        }

    }

    public byte[] read(ByteBuf datas) {

        byte[] bytes = new byte[datas.readableBytes()];

        datas.readBytes(bytes);

        return bytes;

    }

}
