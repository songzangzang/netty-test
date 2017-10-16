package com.test.thrift.test2;


import com.test.thrift.generated.God;
import com.test.thrift.generated.GodService;
import com.test.thrift.generated.Person;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Arrays;
import java.util.List;

public class MyThriftClient {

    public static void main(String[] args) {

        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 9999));
        TProtocol protocol = new TCompactProtocol(transport);
        GodService.Client client = new GodService.Client(protocol);

        try {

            transport.open();

            God god = client.getGodBySky("泽明");
            System.out.println(god.getName());
            System.out.println(god.getAge());
            System.out.println(god.getJob());
            god.getPersonArray().forEach(p -> System.out.println(p));

            God god2 = new God();
            god2.setName("路稀饭");
            god2.setAge(15);
            god2.setJob("黑暗天使");

            List personArray = Arrays.asList(new Person().setUsername("小乌鸦").setAge(16).setMarried(true));
            god2.setPersonArray(personArray);

            client.createGod(god2);

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            transport.close();

        }

    }

}
