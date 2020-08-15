package com.rpc.grpc.helloworld;

import io.grpc.stub.StreamObserver;


public class GreeterGrpcImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        //asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);

        System.out.println(String.format("接收到客户端信息:%s" ,request.getName()));

        //onNext,onError，onCompletedB表示方法都是回调方法
        responseObserver.onNext(HelloReply.newBuilder().setMessage("王超").build());

        //onCompletedB表示方法方法执行完毕
        responseObserver.onCompleted();
    }

}
