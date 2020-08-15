package com.rpc;

import com.rpc.thrift.services.PersonService;
import com.rpc.thrift.services.impl.PersonServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

public class RpcServer {

    public final static int port = 8888;
    public final static int minThreadsCount = 2;
    public final static int maxThreadsCount = 4;

    public static void main(String[] args) throws Exception {

        TNonblockingServerSocket socket = new TNonblockingServerSocket(port);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(minThreadsCount).maxWorkerThreads(maxThreadsCount);
        //范型就是实现的接收类
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        //表示协议层次（压缩协议）
        arg.protocolFactory(new TCompactProtocol.Factory());
        //表示传输层次
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        //半同步半异步的server
        TServer server = new THsHaServer(arg);

        System.out.println("Thrift Server started!");

        //死循环，永远不会退出
        server.serve();

    }
}
