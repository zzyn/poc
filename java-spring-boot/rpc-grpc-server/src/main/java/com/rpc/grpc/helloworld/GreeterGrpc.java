package com.rpc.grpc.helloworld;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Descriptors;
import io.grpc.*;
import io.grpc.protobuf.ProtoFileDescriptorSupplier;
import io.grpc.protobuf.ProtoMethodDescriptorSupplier;
import io.grpc.protobuf.ProtoServiceDescriptorSupplier;
import io.grpc.stub.*;
import io.grpc.stub.annotations.RpcMethod;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
public final class GreeterGrpc {

    private GreeterGrpc() {
    }

    public static final String SERVICE_NAME = "helloworld.Greeter";

    // Static method descriptors that strictly reflect the proto.
    private static volatile MethodDescriptor<HelloRequest, HelloReply> getSayHelloMethod;

    @RpcMethod(fullMethodName = SERVICE_NAME + '/' + "SayHello"
            , requestType = HelloRequest.class
            , responseType = HelloReply.class
            , methodType = MethodDescriptor.MethodType.UNARY)
    public static MethodDescriptor<HelloRequest, HelloReply> getSayHelloMethod() {
        MethodDescriptor<HelloRequest, HelloReply> getSayHelloMethod;
        if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
            synchronized (GreeterGrpc.class) {
                if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
                    GreeterGrpc.getSayHelloMethod = getSayHelloMethod =
                            MethodDescriptor.<HelloRequest, HelloReply>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHello"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(HelloRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(HelloReply.getDefaultInstance()))
                                    .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayHello"))
                                    .build();
                }
            }
        }
        return getSayHelloMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static GreeterStub newStub(io.grpc.Channel channel) {
        AbstractStub.StubFactory<GreeterStub> factory = new AbstractStub.StubFactory<GreeterStub>() {
            @Override
            public GreeterStub newStub(io.grpc.Channel channel, CallOptions callOptions) {
                return new GreeterStub(channel, callOptions);
            }
        };
        return GreeterStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static GreeterBlockingStub newBlockingStub(Channel channel) {
        AbstractStub.StubFactory<GreeterBlockingStub> factory = new AbstractStub.StubFactory<GreeterBlockingStub>() {
            @Override
            public GreeterBlockingStub newStub(Channel channel, CallOptions callOptions) {
                return new GreeterBlockingStub(channel, callOptions);
            }
        };
        return GreeterBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static GreeterFutureStub newFutureStub(Channel channel) {
        AbstractStub.StubFactory<GreeterFutureStub> factory = new AbstractStub.StubFactory<GreeterFutureStub>() {
            @Override
            public GreeterFutureStub newStub(Channel channel, CallOptions callOptions) {
                return new GreeterFutureStub(channel, callOptions);
            }
        };
        return GreeterFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * The greeting service definition.
     * </pre>
     */
    public static abstract class GreeterImplBase implements BindableService {

        /**
         * <pre>
         * Sends a greeting
         * </pre>
         */
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
        }

        @Override
        public final ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(getSayHelloMethod(), asyncUnaryCall(new MethodHandlers<HelloRequest, HelloReply>(this, METHODID_SAY_HELLO)))
                    .build();
        }
    }

    /**
     * <pre>
     * The greeting service definition.
     * </pre>
     */
    public static final class GreeterStub extends AbstractAsyncStub<GreeterStub> {
        private GreeterStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected GreeterStub build(Channel channel, CallOptions callOptions) {
            return new GreeterStub(channel, callOptions);
        }

        /**
         * <pre>
         * Sends a greeting
         * </pre>
         */
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * <pre>
     * The greeting service definition.
     * </pre>
     */
    public static final class GreeterBlockingStub extends io.grpc.stub.AbstractBlockingStub<GreeterBlockingStub> {

        private GreeterBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected GreeterBlockingStub build(Channel channel, CallOptions callOptions) {
            return new GreeterBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * Sends a greeting
         * </pre>
         */
        public HelloReply sayHello(HelloRequest request) {
            return blockingUnaryCall(getChannel(), getSayHelloMethod(), getCallOptions(), request);
        }
    }

    /**
     * <pre>
     * The greeting service definition.
     * </pre>
     */
    public static final class GreeterFutureStub extends AbstractFutureStub<GreeterFutureStub> {
        private GreeterFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected GreeterFutureStub build(Channel channel, CallOptions callOptions) {
            return new GreeterFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * Sends a greeting
         * </pre>
         */
        public ListenableFuture<HelloReply> sayHello(HelloRequest request) {
            return futureUnaryCall(getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_SAY_HELLO = 0;

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final GreeterImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO:
                    serviceImpl.sayHello((HelloRequest) request, (StreamObserver<HelloReply>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public StreamObserver<Req> invoke(StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    private static abstract class GreeterBaseDescriptorSupplier implements ProtoFileDescriptorSupplier, ProtoServiceDescriptorSupplier {
        GreeterBaseDescriptorSupplier() {
        }

        @Override
        public Descriptors.FileDescriptor getFileDescriptor() {
            return HelloWorldProto.getDescriptor();
        }

        @Override
        public Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Greeter");
        }
    }

    private static final class GreeterFileDescriptorSupplier extends GreeterBaseDescriptorSupplier {
        GreeterFileDescriptorSupplier() {
        }
    }

    private static final class GreeterMethodDescriptorSupplier extends GreeterBaseDescriptorSupplier implements ProtoMethodDescriptorSupplier {
        private final String methodName;

        GreeterMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile ServiceDescriptor serviceDescriptor;

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (GreeterGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
                            .addMethod(getSayHelloMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
