package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/EmptyProto.class */
public final class EmptyProto {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_google_protobuf_Empty_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_Empty_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001bgoogle/protobuf/empty.proto\u0012\u000fgoogle.protobuf\"\u0007\n\u0005EmptyBv\n\u0013com.google.protobufB\nEmptyProtoP\u0001Z'github.com/golang/protobuf/ptypes/emptyø\u0001\u0001¢\u0002\u0003GPBª\u0002\u001eGoogle.Protobuf.WellKnownTypesb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.google.protobuf.EmptyProto.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = EmptyProto.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_google_protobuf_Empty_descriptor = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_google_protobuf_Empty_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_Empty_descriptor, new String[0]);
    }

    private EmptyProto() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
