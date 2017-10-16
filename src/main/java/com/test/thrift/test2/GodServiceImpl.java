package com.test.thrift.test2;

import com.test.thrift.generated.God;
import com.test.thrift.generated.GodService;
import com.test.thrift.generated.Person;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * God实现类
 */
public class GodServiceImpl implements GodService.Iface {


    @Override
    public God getGodBySky(String name) throws TException {

        System.out.println("客户端请求为参数为 ：" + name);

        God god = new God();
        god.setName(name);
        god.setAge(99999);
        god.setJob("吹牛B");
        List<Person> personArray = new ArrayList<>();
        personArray.add(new Person().setUsername("丫鬟").setAge(13).setMarried(false));
        god.setPersonArray(personArray);

        return god;

    }

    @Override
    public void createGod(God god) throws TException {

        System.out.println("收到客户端参数 ：");
        System.out.println(god.getName());
        System.out.println(god.getAge());
        System.out.println(god.getJob());
        god.getPersonArray().forEach(p -> System.out.println(p));

    }

    @Override
    public void createPersonByGod(List<Person> peronArray) throws TException {

        System.out.println("收到客户端参数 : " + peronArray.toArray());

    }
}
