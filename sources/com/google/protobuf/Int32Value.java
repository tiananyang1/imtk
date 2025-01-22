package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/Int32Value.class */
public final class Int32Value extends GeneratedMessageV3 implements Int32ValueOrBuilder {
    private static final Int32Value DEFAULT_INSTANCE = new Int32Value();
    private static final Parser<Int32Value> PARSER = new AbstractParser<Int32Value>() { // from class: com.google.protobuf.Int32Value.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Int32Value m3118parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Int32Value(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VALUE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int value_;

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/Int32Value$Builder.class */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements Int32ValueOrBuilder {
        private int value_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return WrappersProto.internal_static_google_protobuf_Int32Value_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean z = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: addRepeatedField, reason: avoid collision after fix types in other method */
        public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.Int32Value, com.google.protobuf.Message] */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Int32Value m3120build() {
            ?? m3122buildPartial = m3122buildPartial();
            if (m3122buildPartial.isInitialized()) {
                return m3122buildPartial;
            }
            throw newUninitializedMessageException(m3122buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Int32Value m3122buildPartial() {
            Int32Value int32Value = new Int32Value(this);
            int32Value.value_ = this.value_;
            onBuilt();
            return int32Value;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clear */
        public Builder mo2896clear() {
            super.mo2896clear();
            this.value_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearField, reason: avoid collision after fix types in other method */
        public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo2897clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearOneof */
        public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo2899clearOneof(oneofDescriptor);
        }

        public Builder clearValue() {
            this.value_ = 0;
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
        public Int32Value mo3095getDefaultInstanceForType() {
            return Int32Value.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return WrappersProto.internal_static_google_protobuf_Int32Value_descriptor;
        }

        @Override // com.google.protobuf.Int32ValueOrBuilder
        public int getValue() {
            return this.value_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return WrappersProto.internal_static_google_protobuf_Int32Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Int32Value.class, Builder.class);
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
        public com.google.protobuf.Int32Value.Builder m3128mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                r7 = r0
                com.google.protobuf.Parser r0 = com.google.protobuf.Int32Value.access$400()     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r1 = r5
                r2 = r6
                java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                com.google.protobuf.Int32Value r0 = (com.google.protobuf.Int32Value) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r5 = r0
                r0 = r5
                if (r0 == 0) goto L1a
                r0 = r4
                r1 = r5
                com.google.protobuf.Int32Value$Builder r0 = r0.mergeFrom(r1)
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
                com.google.protobuf.Int32Value r0 = (com.google.protobuf.Int32Value) r0     // Catch: java.lang.Throwable -> L1c
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
                com.google.protobuf.Int32Value$Builder r0 = r0.mergeFrom(r1)
            L3b:
                r0 = r6
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Int32Value.Builder.m3128mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Int32Value$Builder");
        }

        public Builder mergeFrom(Int32Value int32Value) {
            if (int32Value == Int32Value.getDefaultInstance()) {
                return this;
            }
            if (int32Value.getValue() != 0) {
                setValue(int32Value.getValue());
            }
            mo2912mergeUnknownFields(int32Value.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3127mergeFrom(Message message) {
            if (message instanceof Int32Value) {
                return mergeFrom((Int32Value) message);
            }
            super.mergeFrom(message);
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setField, reason: avoid collision after fix types in other method */
        public Builder mo2913setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2913setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setRepeatedField, reason: avoid collision after fix types in other method */
        public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setUnknownFields, reason: avoid collision after fix types in other method */
        public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public Builder setValue(int i) {
            this.value_ = i;
            onChanged();
            return this;
        }
    }

    private Int32Value() {
        this.memoizedIsInitialized = (byte) -1;
        this.value_ = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Int32Value(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.value_ = codedInputStream.readInt32();
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

    private Int32Value(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static Int32Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return WrappersProto.internal_static_google_protobuf_Int32Value_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3117toBuilder();
    }

    public static Builder newBuilder(Int32Value int32Value) {
        return DEFAULT_INSTANCE.m3117toBuilder().mergeFrom(int32Value);
    }

    /* renamed from: of */
    public static Int32Value m3of(int i) {
        return newBuilder().setValue(i).m3120build();
    }

    public static Int32Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Int32Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Int32Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Int32Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Int32Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Int32Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Int32Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Int32Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Int32Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Int32Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Int32Value parseFrom(InputStream inputStream) throws IOException {
        return (Int32Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Int32Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Int32Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Int32Value parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Int32Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Int32Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Int32Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<Int32Value> parser() {
        return PARSER;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Int32Value)) {
            return super.equals(obj);
        }
        Int32Value int32Value = (Int32Value) obj;
        return (getValue() == int32Value.getValue()) && this.unknownFields.equals(int32Value.unknownFields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public Int32Value mo3095getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public Parser<Int32Value> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int i3 = this.value_;
        if (i3 != 0) {
            i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.Int32ValueOrBuilder
    public int getValue() {
        return this.value_;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getValue()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WrappersProto.internal_static_google_protobuf_Int32Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Int32Value.class, Builder.class);
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

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3115newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3117toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.value_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
