package com.rpc.thrift.services.impl;

import com.rpc.thrift.services.DataException;
import com.rpc.thrift.services.Person;
import com.rpc.thrift.services.PersonService;
import org.apache.thrift.TException;

public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Got client Param:" + username);

        Person person = new Person();
        person.setUsername(username);
        person.setAge(32);
        person.setMarried(true);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got Client Param: ");

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
