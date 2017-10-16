package com.test.thrift.test1;

import com.test.thrift.generated.Person;
import com.test.thrift.generated.PersonService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * 客户端
 */
public class ThriftClient {

    public static void main(String[] args) {

        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 9999), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {

            transport.open();

            Person person = client.getPersonByUsername("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("-----");

            Person person2 = new Person();
            person2.setUsername("魔五");
            person2.setAge(1005);
            person.setMarried(true);

            client.savePerson(person2);

        } catch (Exception ex) {

            throw new RuntimeException(ex);

        } finally {

            transport.close();

        }

    }


}
