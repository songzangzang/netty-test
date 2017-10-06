package com.test.netty.test6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

public class TestServerHandler extends SimpleChannelInboundHandler<TestDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TestDataInfo.MyMessage msg) throws Exception {

        TestDataInfo.MyMessage.DataType type = msg.getType();

        if (type == TestDataInfo.MyMessage.DataType.TEACHER_TYPE) {

            TestDataInfo.Teacher teacher = msg.getTeacher();

            System.out.println(teacher.getName());
            System.out.println(teacher.getAge());
            System.out.println(teacher.getAddress());


        } else if (type == TestDataInfo.MyMessage.DataType.STUDENT_TYPE) {

            TestDataInfo.Student student = msg.getStudent();

            System.out.println(student.getName());
            System.out.println(student.getAge());
            System.out.println(student.getAddress());

        } else if (type == TestDataInfo.MyMessage.DataType.GOD_TYPE) {

            TestDataInfo.God god = msg.getGod();

            System.out.println(god.getName());
            System.out.println(god.getAge());
            System.out.println(god.getAddress());

        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }
}
