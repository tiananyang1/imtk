package com.google.protobuf;

import com.google.protobuf.Descriptors;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/Service.class */
public interface Service {
    void callMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, RpcCallback<Message> rpcCallback);

    Descriptors.ServiceDescriptor getDescriptorForType();

    Message getRequestPrototype(Descriptors.MethodDescriptor methodDescriptor);

    Message getResponsePrototype(Descriptors.MethodDescriptor methodDescriptor);
}
