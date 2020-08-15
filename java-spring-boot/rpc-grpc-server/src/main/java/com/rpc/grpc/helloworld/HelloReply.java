package com.rpc.grpc.helloworld;

import com.google.protobuf.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * <pre>
 * The response message containing the greetings
 * </pre>
 * <p>
 * Protobuf type {@code helloworld.HelloReply}
 */
public final class HelloReply extends GeneratedMessageV3 implements HelloReplyOrBuilder {

    private static final long serialVersionUID = 0L;

    private HelloReply(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private HelloReply() {
        message_ = "";
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(UnusedPrivateParameter unused) {
        return new HelloReply();
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {

        return this.unknownFields;
    }

    private HelloReply(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {

        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            while (!done) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        done = true;
                        break;
                    case 10: {
                        String s = input.readStringRequireUtf8();

                        message_ = s;
                        break;
                    }
                    default: {
                        if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                            done = true;
                        }
                        break;
                    }
                }
            }
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        } finally {
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor
    getDescriptor() {
        return HelloWorldProto.internal_static_helloworld_HelloReply_descriptor;
    }

    @Override
    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return HelloWorldProto
                .internal_static_helloworld_HelloReply_fieldAccessorTable
                .ensureFieldAccessorsInitialized(HelloReply.class, HelloReply.Builder.class);
    }

    public static final int MESSAGE_FIELD_NUMBER = 1;
    private volatile Object message_;

    /**
     * <code>string message = 1;</code>
     *
     * @return The message.
     */
    public String getMessage() {
        Object ref = message_;
        if (ref instanceof String) {
            return (String) ref;
        } else {
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            message_ = s;
            return s;
        }
    }

    /**
     * <code>string message = 1;</code>
     *
     * @return The bytes for message.
     */
    public ByteString
    getMessageBytes() {
        Object ref = message_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            message_ = b;
            return b;
        } else {
            return (ByteString) ref;
        }
    }

    private byte memoizedIsInitialized = -1;

    @Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) return true;
        if (isInitialized == 0) return false;

        memoizedIsInitialized = 1;
        return true;
    }

    @Override
    public void writeTo(CodedOutputStream output) throws IOException {
        if (!getMessageBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, message_);
        }
        unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) return size;

        size = 0;
        if (!getMessageBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(1, message_);
        }
        size += unknownFields.getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HelloReply)) {
            return super.equals(obj);
        }
        HelloReply other = (HelloReply) obj;

        if (!getMessage().equals(other.getMessage())) return false;
        if (!unknownFields.equals(other.unknownFields)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
        hash = (53 * hash) + getMessage().hashCode();
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static HelloReply parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HelloReply parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HelloReply parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HelloReply parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HelloReply parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static HelloReply parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static HelloReply parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static HelloReply parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static HelloReply parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static HelloReply parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static HelloReply parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static HelloReply parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HelloReply prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /**
     * <pre>
     * The response message containing the greetings
     * </pre>
     * <p>
     * Protobuf type {@code helloworld.HelloReply}
     */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HelloReplyOrBuilder {
        public static final Descriptors.Descriptor getDescriptor() {
            return HelloWorldProto.internal_static_helloworld_HelloReply_descriptor;
        }

        @Override
        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return HelloWorldProto
                    .internal_static_helloworld_HelloReply_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(HelloReply.class, HelloReply.Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            message_ = "";

            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return HelloWorldProto.internal_static_helloworld_HelloReply_descriptor;
        }

        @Override
        public HelloReply getDefaultInstanceForType() {
            return HelloReply.getDefaultInstance();
        }

        @Override
        public HelloReply build() {
            HelloReply result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @Override
        public HelloReply buildPartial() {
            HelloReply result = new HelloReply(this);
            result.message_ = message_;
            onBuilt();
            return result;
        }

        @Override
        public Builder clone() {
            return super.clone();
        }

        @Override
        public Builder setField(Descriptors.FieldDescriptor field, Object value) {
            return super.setField(field, value);
        }

        @Override
        public Builder clearField(Descriptors.FieldDescriptor field) {
            return super.clearField(field);
        }

        @Override
        public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
            return super.clearOneof(oneof);
        }

        @Override
        public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return super.setRepeatedField(field, index, value);
        }

        @Override
        public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return super.addRepeatedField(field, value);
        }

        @Override
        public Builder mergeFrom(Message other) {
            if (other instanceof HelloReply) {
                return mergeFrom((HelloReply) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(HelloReply other) {
            if (other == HelloReply.getDefaultInstance()) return this;
            if (!other.getMessage().isEmpty()) {
                message_ = other.message_;
                onChanged();
            }
            this.mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        @Override
        public final boolean isInitialized() {
            return true;
        }

        @Override
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            HelloReply parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (InvalidProtocolBufferException e) {
                parsedMessage = (HelloReply) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private Object message_ = "";

        /**
         * <code>string message = 1;</code>
         *
         * @return The message.
         */
        public String getMessage() {
            Object ref = message_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                message_ = s;
                return s;
            } else {
                return (String) ref;
            }
        }

        /**
         * <code>string message = 1;</code>
         *
         * @return The bytes for message.
         */
        public ByteString getMessageBytes() {
            Object ref = message_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                message_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        /**
         * <code>string message = 1;</code>
         *
         * @param value The message to set.
         * @return This builder for chaining.
         */
        public Builder setMessage(
                String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            message_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>string message = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMessage() {

            message_ = getDefaultInstance().getMessage();
            onChanged();
            return this;
        }

        /**
         * <code>string message = 1;</code>
         *
         * @param value The bytes for message to set.
         * @return This builder for chaining.
         */
        public Builder setMessageBytes(
                ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);

            message_ = value;
            onChanged();
            return this;
        }

        @Override
        public final Builder setUnknownFields(
                final UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @Override
        public final Builder mergeUnknownFields(
                final UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

    }

    private static final HelloReply DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new HelloReply();
    }

    public static HelloReply getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final Parser<HelloReply> PARSER = new AbstractParser<HelloReply>() {
        @Override
        public HelloReply parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new HelloReply(input, extensionRegistry);
        }
    };

    public static Parser<HelloReply> parser() {
        return PARSER;
    }

    @Override
    public Parser<HelloReply> getParserForType() {
        return PARSER;
    }

    @Override
    public HelloReply getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}

