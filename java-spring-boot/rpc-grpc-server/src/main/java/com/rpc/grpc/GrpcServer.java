package com.rpc.grpc;


import com.rpc.grpc.helloworld.GreeterGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

	private Server server;

	private final static int port = 9090;


	//编写服务启动方法
	private void start() throws IOException {
		//增加实际业务代码的实列，如果使用spring的话可以直接将GreeterGrpcImpl对象注入进来
		this.server = ServerBuilder.forPort(port).addService(new GreeterGrpcImpl()).build().start();

		System.out.println("server start!");

		//jvm回调钩子的作用，Runtime.getRuntime()可以获得java一些运行期间的一些信息。
		//不管程序是正常关闭还是异常终端，在jvm关闭的时候做一些清理工作
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("closing jvm");
			GrpcServer.this.stop();
			System.out.println("shutting down service");
		}));

		System.out.println("execution done.");
	}

	//停止服务器
	private void stop(){
		if(null != this.server){
			this.server.shutdown();
		}
	}

	//服务器阻塞
	private void blockUntilShutdown() throws InterruptedException {
		if(null != this.server){
			this.server.awaitTermination();
			//this.server.awaitTermination(3000, TimeUnit.MILLISECONDS);
		}
	}


	public static void main(String[] args) throws IOException,InterruptedException{
		GrpcServer server = new GrpcServer();

		server.start();;
		System.out.println("gprc server is started.");

		server.blockUntilShutdown();

	}
}
