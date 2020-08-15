package com.rpc.grpc.helloworld;

import com.google.protobuf.*;

public final class HelloWorldProto {

  private HelloWorldProto() {}

  public static void registerAllExtensions(ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite) registry);
  }

  static final Descriptors.Descriptor internal_static_helloworld_HelloRequest_descriptor;
  static final GeneratedMessageV3.FieldAccessorTable internal_static_helloworld_HelloRequest_fieldAccessorTable;
  static final Descriptors.Descriptor internal_static_helloworld_HelloReply_descriptor;
  static final GeneratedMessageV3.FieldAccessorTable internal_static_helloworld_HelloReply_fieldAccessorTable;

  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  private static Descriptors.FileDescriptor descriptor;

  static {
    String[] descriptorData = {
      "\n\020helloworld.proto\022\nhelloworld\"\034\n\014HelloR" +
      "equest\022\014\n\004name\030\001 \001(\t\"\035\n\nHelloReply\022\017\n\007me" +
      "ssage\030\001 \001(\t2I\n\007Greeter\022>\n\010SayHello\022\030.hel" +
      "loworld.HelloRequest\032\026.helloworld.HelloR" +
      "eply\"\000B2\n\027com.rpc.grpc.helloworldB\017Hello" +
      "WorldProtoP\001\242\002\003HLWb\006proto3"
    };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] {});
    internal_static_helloworld_HelloRequest_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_helloworld_HelloRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_helloworld_HelloRequest_descriptor, new String[] { "Name", });
    internal_static_helloworld_HelloReply_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_helloworld_HelloReply_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_helloworld_HelloReply_descriptor, new String[] { "Message", });
  }
}
