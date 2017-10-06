package com.test.netty.test6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<TestDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TestDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int randomNum = new Random().nextInt(3);

        TestDataInfo.MyMessage message = null;

        if (0 == randomNum) {

            message = TestDataInfo.MyMessage.newBuilder()
                    .setType(TestDataInfo.MyMessage.DataType.TEACHER_TYPE)
                    .setTeacher(TestDataInfo.Teacher.newBuilder().setName("卡尔大魔王")
                    .setAge(23).setAddress("齐齐哈尔").build())
                    .build();

        } else if (1 == randomNum) {

            message = TestDataInfo.MyMessage.newBuilder()
                    .setType(TestDataInfo.MyMessage.DataType.STUDENT_TYPE)
                    .setStudent(TestDataInfo.Student.newBuilder().setName("船长杰克")
                            .setAge(13).setAddress("特阿拉法").build())
                    .build();

        } else {

            message = TestDataInfo.MyMessage.newBuilder()
                    .setType(TestDataInfo.MyMessage.DataType.GOD_TYPE)
                    .setGod(TestDataInfo.God.newBuilder().setName("雷神")
                            .setAge(133).setAddress("神之结界").build())
                    .build();

        }


        ctx.writeAndFlush(message);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }
}
