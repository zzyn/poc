package com.rpc;

import com.rpc.thrift.services.Person;
import com.rpc.thrift.services.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class RpcClient {

    public final static int port = 8888;

    public static void main(String[] args) {

        TTransport tTransport = new TFastFramedTransport(new TSocket("localhost",port),600);
        TProtocol tProtocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(tProtocol);

        try{
            tTransport.open();

            Person person = client.getPersonByUsername("张三");

            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("............");

            Person person2 = new Person();

            person2.setUsername("李四");
            person2.setAge(30);
            person2.setMarried(true);

            client.savePerson(person2);
        }catch (Exception ex){
            throw new  RuntimeException(ex.getMessage(),ex);
        }finally {
            tTransport.close();
        }
    }
}
