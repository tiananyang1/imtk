package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/Extension.class */
public abstract class Extension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/Extension$ExtensionType.class */
    protected enum ExtensionType {
        IMMUTABLE,
        MUTABLE,
        PROTO1
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/Extension$MessageType.class */
    public enum MessageType {
        PROTO1,
        PROTO2
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object fromReflectionType(Object obj);

    public abstract Descriptors.FieldDescriptor getDescriptor();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ExtensionType getExtensionType();

    public MessageType getMessageType() {
        return MessageType.PROTO2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.protobuf.ExtensionLite
    public final boolean isLite() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object singularFromReflectionType(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object singularToReflectionType(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object toReflectionType(Object obj);
}
