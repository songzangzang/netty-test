package com.test.thrift;

import com.test.thrift.generated.DataException;
import com.test.thrift.generated.Person;
import com.test.thrift.generated.PersonService;
import org.apache.thrift.TException;

public class PersonServiceImpl implements PersonService.Iface{

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {

        System.out.println("客户端用户名 ： " + username);

        Person person = new Person();
        person.setUsername(username);
        person.setAge(13);
        person.setMarried(true);

        return person;

    }

    @Override
    public void savePerson(Person person) throws DataException, TException {

        System.out.println("获得客户端数据");

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());

    }
}
