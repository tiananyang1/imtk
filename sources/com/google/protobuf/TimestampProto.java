package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/TimestampProto.class */
public final class TimestampProto {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_google_protobuf_Timestamp_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_Timestamp_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001fgoogle/protobuf/timestamp.proto\u0012\u000fgoogle.protobuf\"+\n\tTimestamp\u0012\u000f\n\u0007seconds\u0018\u0001 \u0001(\u0003\u0012\r\n\u0005nanos\u0018\u0002 \u0001(\u0005B~\n\u0013com.google.protobufB\u000eTimestampProtoP\u0001Z+github.com/golang/protobuf/ptypes/timestampø\u0001\u0001¢\u0002\u0003GPBª\u0002\u001eGoogle.Protobuf.WellKnownTypesb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.google.protobuf.TimestampProto.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = TimestampProto.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_google_protobuf_Timestamp_descriptor = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_google_protobuf_Timestamp_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_Timestamp_descriptor, new String[]{"Seconds", "Nanos"});
    }

    private TimestampProto() {
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
