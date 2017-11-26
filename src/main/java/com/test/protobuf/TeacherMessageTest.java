package com.test.protobuf;


public class TeacherMessageTest {

    public static void main(String[] args) throws Exception{

        DataInfo.Teacher teacher = DataInfo.Teacher.newBuilder().setName("卡尔大魔王").setAge(23)
                .setAddress("qiqihaer").build();
        byte[] teacher2ByteArray = teacher.toByteArray();

        DataInfo.Teacher teacher1 = DataInfo.Teacher.parseFrom(teacher2ByteArray);
        System.out.println(teacher1.getName());
        System.out.println(teacher1.getAge());
        System.out.println(teacher1.getAddress());

    }

}
