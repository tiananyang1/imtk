package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/UInt64Value.class */
public final class UInt64Value extends GeneratedMessageV3 implements UInt64ValueOrBuilder {
    private static final UInt64Value DEFAULT_INSTANCE = new UInt64Value();
    private static final Parser<UInt64Value> PARSER = new AbstractParser<UInt64Value>() { // from class: com.google.protobuf.UInt64Value.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public UInt64Value m3362parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new UInt64Value(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VALUE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private long value_;

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/UInt64Value$Builder.class */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UInt64ValueOrBuilder {
        private long value_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return WrappersProto.internal_static_google_protobuf_UInt64Value_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean z = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: addRepeatedField */
        public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.UInt64Value, com.google.protobuf.Message] */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UInt64Value m3363build() {
            ?? m3365buildPartial = m3365buildPartial();
            if (m3365buildPartial.isInitialized()) {
                return m3365buildPartial;
            }
            throw newUninitializedMessageException(m3365buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public UInt64Value m3365buildPartial() {
            UInt64Value uInt64Value = new UInt64Value(this);
            uInt64Value.value_ = this.value_;
            onBuilt();
            return uInt64Value;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clear */
        public Builder mo2896clear() {
            super.mo2896clear();
            this.value_ = UInt64Value.serialVersionUID;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearField */
        public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo2897clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearOneof */
        public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo2899clearOneof(oneofDescriptor);
        }

        public Builder clearValue() {
            this.value_ = UInt64Value.serialVersionUID;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clone */
        public Builder mo2904clone() {
            return (Builder) super.mo2904clone();
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
        public UInt64Value mo3095getDefaultInstanceForType() {
            return UInt64Value.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return WrappersProto.internal_static_google_protobuf_UInt64Value_descriptor;
        }

        @Override // com.google.protobuf.UInt64ValueOrBuilder
        public long getValue() {
            return this.value_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WrappersProto.internal_static_google_protobuf_UInt64Value_fieldAccessorTable.ensureFieldAccessorsInitialized(UInt64Value.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0035  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.protobuf.UInt64Value.Builder m3370mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                r7 = r0
                com.google.protobuf.Parser r0 = com.google.protobuf.UInt64Value.access$400()     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r1 = r5
                r2 = r6
                java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                com.google.protobuf.UInt64Value r0 = (com.google.protobuf.UInt64Value) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r5 = r0
                r0 = r5
                if (r0 == 0) goto L1a
                r0 = r4
                r1 = r5
                com.google.protobuf.UInt64Value$Builder r0 = r0.mergeFrom(r1)
            L1a:
                r0 = r4
                return r0
            L1c:
                r6 = move-exception
                r0 = r7
                r5 = r0
                goto L31
            L22:
                r6 = move-exception
                r0 = r6
                com.google.protobuf.MessageLite r0 = r0.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L1c
                com.google.protobuf.UInt64Value r0 = (com.google.protobuf.UInt64Value) r0     // Catch: java.lang.Throwable -> L1c
                r5 = r0
                r0 = r6
                java.io.IOException r0 = r0.unwrapIOException()     // Catch: java.lang.Throwable -> L30
                throw r0     // Catch: java.lang.Throwable -> L30
            L30:
                r6 = move-exception
            L31:
                r0 = r5
                if (r0 == 0) goto L3b
                r0 = r4
                r1 = r5
                com.google.protobuf.UInt64Value$Builder r0 = r0.mergeFrom(r1)
            L3b:
                r0 = r6
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.UInt64Value.Builder.m3370mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.UInt64Value$Builder");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3369mergeFrom(Message message) {
            if (message instanceof UInt64Value) {
                return mergeFrom((UInt64Value) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(UInt64Value uInt64Value) {
            if (uInt64Value == UInt64Value.getDefaultInstance()) {
                return this;
            }
            if (uInt64Value.getValue() != UInt64Value.serialVersionUID) {
                setValue(uInt64Value.getValue());
            }
            mo2912mergeUnknownFields(uInt64Value.unknownFields);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setField */
        public Builder mo2913setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2913setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setRepeatedField */
        public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public Builder setValue(long j) {
            this.value_ = j;
            onChanged();
            return this;
        }
    }

    private UInt64Value() {
        this.memoizedIsInitialized = (byte) -1;
        this.value_ = serialVersionUID;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private UInt64Value(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 8) {
                            this.value_ = codedInputStream.readUInt64();
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    private UInt64Value(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static UInt64Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return WrappersProto.internal_static_google_protobuf_UInt64Value_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3360toBuilder();
    }

    public static Builder newBuilder(UInt64Value uInt64Value) {
        return DEFAULT_INSTANCE.m3360toBuilder().mergeFrom(uInt64Value);
    }

    /* renamed from: of */
    public static UInt64Value m7of(long j) {
        return newBuilder().setValue(j).m3363build();
    }

    public static UInt64Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UInt64Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static UInt64Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UInt64Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static UInt64Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UInt64Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static UInt64Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UInt64Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(InputStream inputStream) throws IOException {
        return (UInt64Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static UInt64Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UInt64Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static UInt64Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static UInt64Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<UInt64Value> parser() {
        return PARSER;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UInt64Value)) {
            return super.equals(obj);
        }
        UInt64Value uInt64Value = (UInt64Value) obj;
        return ((getValue() > uInt64Value.getValue() ? 1 : (getValue() == uInt64Value.getValue() ? 0 : -1)) == 0) && this.unknownFields.equals(uInt64Value.unknownFields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public UInt64Value mo3095getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public Parser<UInt64Value> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        long j = this.value_;
        if (j != serialVersionUID) {
            i2 = 0 + CodedOutputStream.computeUInt64Size(1, j);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.UInt64ValueOrBuilder
    public long getValue() {
        return this.value_;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getValue())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WrappersProto.internal_static_google_protobuf_UInt64Value_fieldAccessorTable.ensureFieldAccessorsInitialized(UInt64Value.class, Builder.class);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3358newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3360toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        long j = this.value_;
        if (j != serialVersionUID) {
            codedOutputStream.writeUInt64(1, j);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
