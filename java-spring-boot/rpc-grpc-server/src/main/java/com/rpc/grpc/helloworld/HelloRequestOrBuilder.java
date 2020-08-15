package com.rpc.grpc.helloworld;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface HelloRequestOrBuilder extends MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  ByteString getNameBytes();
}
