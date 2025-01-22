package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/Message.class */
public interface Message extends MessageLite, MessageOrBuilder {

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/Message$Builder.class */
    public interface Builder extends MessageLite.Builder, MessageOrBuilder {
        Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj);

        @Override // com.google.protobuf.MessageLite.Builder
        Message build();

        @Override // com.google.protobuf.MessageLite.Builder
        Message buildPartial();

        @Override // com.google.protobuf.MessageLite.Builder
        Builder clear();

        Builder clearField(Descriptors.FieldDescriptor fieldDescriptor);

        Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor);

        @Override // com.google.protobuf.MessageLite.Builder
        /* renamed from: clone */
        Builder m3375clone();

        @Override // com.google.protobuf.MessageOrBuilder
        Descriptors.Descriptor getDescriptorForType();

        Builder getFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor);

        Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor, int i);

        @Override // com.google.protobuf.MessageLite.Builder
        boolean mergeDelimitedFrom(InputStream inputStream) throws IOException;

        @Override // com.google.protobuf.MessageLite.Builder
        boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(ByteString byteString) throws InvalidProtocolBufferException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(CodedInputStream codedInputStream) throws IOException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        Builder mergeFrom(Message message);

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(InputStream inputStream) throws IOException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(byte[] bArr) throws InvalidProtocolBufferException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        @Override // com.google.protobuf.MessageLite.Builder
        Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException;

        Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet);

        Builder newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor);

        Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj);

        Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj);

        Builder setUnknownFields(UnknownFieldSet unknownFieldSet);
    }

    boolean equals(Object obj);

    @Override // com.google.protobuf.MessageLite
    Parser<? extends Message> getParserForType();

    int hashCode();

    @Override // com.google.protobuf.MessageLite
    Builder newBuilderForType();

    @Override // com.google.protobuf.MessageLite
    Builder toBuilder();

    String toString();
}
