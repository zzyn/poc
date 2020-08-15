package com.rpc.grpc.helloworld;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface HelloReplyOrBuilder extends MessageOrBuilder {

    /**
     * <code>string message = 1;</code>
     *
     * @return The message.
     */
    String getMessage();

    /**
     * <code>string message = 1;</code>
     *
     * @return The bytes for message.
     */
    ByteString getMessageBytes();
}
