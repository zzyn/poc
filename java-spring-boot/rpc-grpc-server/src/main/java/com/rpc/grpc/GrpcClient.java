package com.rpc.grpc;

import com.rpc.grpc.helloworld.GreeterGrpc;
import com.rpc.grpc.helloworld.HelloReply;
import com.rpc.grpc.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    private static int port = 9090;

    public static void main(String[] args) {

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", port)
                .usePlaintext()
                .build();

        //定义一个stub，用于实际和服务端连接的对象blockingStub
        GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(managedChannel);

        HelloReply response =blockingStub.sayHello(HelloRequest
                .newBuilder()
                .setName("chao.wang")
                .build());

        System.out.println(response.getMessage());
    }
}
