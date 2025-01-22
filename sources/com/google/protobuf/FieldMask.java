package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/FieldMask.class */
public final class FieldMask extends GeneratedMessageV3 implements FieldMaskOrBuilder {
    private static final FieldMask DEFAULT_INSTANCE = new FieldMask();
    private static final Parser<FieldMask> PARSER = new AbstractParser<FieldMask>() { // from class: com.google.protobuf.FieldMask.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public FieldMask m3044parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FieldMask(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PATHS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private LazyStringList paths_;

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/FieldMask$Builder.class */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FieldMaskOrBuilder {
        private int bitField0_;
        private LazyStringList paths_;

        private Builder() {
            this.paths_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.paths_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void ensurePathsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.paths_ = new LazyStringArrayList(this.paths_);
                this.bitField0_ |= 1;
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean z = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder addAllPaths(Iterable<String> iterable) {
            ensurePathsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.paths_);
            onChanged();
            return this;
        }

        public Builder addPaths(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensurePathsIsMutable();
            this.paths_.add(str);
            onChanged();
            return this;
        }

        public Builder addPathsBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            ensurePathsIsMutable();
            this.paths_.add(byteString);
            onChanged();
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: addRepeatedField */
        public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.FieldMask, com.google.protobuf.Message] */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FieldMask m3046build() {
            ?? m3048buildPartial = m3048buildPartial();
            if (m3048buildPartial.isInitialized()) {
                return m3048buildPartial;
            }
            throw newUninitializedMessageException(m3048buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FieldMask m3048buildPartial() {
            FieldMask fieldMask = new FieldMask(this);
            if ((this.bitField0_ & 1) == 1) {
                this.paths_ = this.paths_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            fieldMask.paths_ = this.paths_;
            onBuilt();
            return fieldMask;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clear */
        public Builder mo2896clear() {
            super.mo2896clear();
            this.paths_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
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

        public Builder clearPaths() {
            this.paths_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
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
        public FieldMask mo3095getDefaultInstanceForType() {
            return FieldMask.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
        }

        @Override // com.google.protobuf.FieldMaskOrBuilder
        public String getPaths(int i) {
            return (String) this.paths_.get(i);
        }

        @Override // com.google.protobuf.FieldMaskOrBuilder
        public ByteString getPathsBytes(int i) {
            return this.paths_.getByteString(i);
        }

        @Override // com.google.protobuf.FieldMaskOrBuilder
        public int getPathsCount() {
            return this.paths_.size();
        }

        @Override // com.google.protobuf.FieldMaskOrBuilder
        public ProtocolStringList getPathsList() {
            return this.paths_.getUnmodifiableView();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return FieldMaskProto.internal_static_google_protobuf_FieldMask_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldMask.class, Builder.class);
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
        public com.google.protobuf.FieldMask.Builder m3054mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                r7 = r0
                com.google.protobuf.Parser r0 = com.google.protobuf.FieldMask.access$400()     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r1 = r5
                r2 = r6
                java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                com.google.protobuf.FieldMask r0 = (com.google.protobuf.FieldMask) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r5 = r0
                r0 = r5
                if (r0 == 0) goto L1a
                r0 = r4
                r1 = r5
                com.google.protobuf.FieldMask$Builder r0 = r0.mergeFrom(r1)
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
                com.google.protobuf.FieldMask r0 = (com.google.protobuf.FieldMask) r0     // Catch: java.lang.Throwable -> L1c
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
                com.google.protobuf.FieldMask$Builder r0 = r0.mergeFrom(r1)
            L3b:
                r0 = r6
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.FieldMask.Builder.m3054mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.FieldMask$Builder");
        }

        public Builder mergeFrom(FieldMask fieldMask) {
            if (fieldMask == FieldMask.getDefaultInstance()) {
                return this;
            }
            if (!fieldMask.paths_.isEmpty()) {
                if (this.paths_.isEmpty()) {
                    this.paths_ = fieldMask.paths_;
                    this.bitField0_ &= -2;
                } else {
                    ensurePathsIsMutable();
                    this.paths_.addAll(fieldMask.paths_);
                }
                onChanged();
            }
            mo2912mergeUnknownFields(fieldMask.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3053mergeFrom(Message message) {
            if (message instanceof FieldMask) {
                return mergeFrom((FieldMask) message);
            }
            super.mergeFrom(message);
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setField */
        public Builder mo2913setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2913setField(fieldDescriptor, obj);
        }

        public Builder setPaths(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensurePathsIsMutable();
            this.paths_.set(i, str);
            onChanged();
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setRepeatedField */
        public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }
    }

    private FieldMask() {
        this.memoizedIsInitialized = (byte) -1;
        this.paths_ = LazyStringArrayList.EMPTY;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FieldMask(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            boolean z3 = z2;
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                boolean z4 = z2;
                                if (!(z2 & true)) {
                                    this.paths_ = new LazyStringArrayList();
                                    z4 = z2 | true;
                                }
                                this.paths_.add(readStringRequireUtf8);
                                z2 = z4;
                            } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if (z3 & true) {
                    this.paths_ = this.paths_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                throw th;
            }
        }
        if (z2 & true) {
            this.paths_ = this.paths_.getUnmodifiableView();
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    private FieldMask(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static FieldMask getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3043toBuilder();
    }

    public static Builder newBuilder(FieldMask fieldMask) {
        return DEFAULT_INSTANCE.m3043toBuilder().mergeFrom(fieldMask);
    }

    public static FieldMask parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FieldMask) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static FieldMask parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FieldMask) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FieldMask parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static FieldMask parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FieldMask parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FieldMask) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static FieldMask parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FieldMask) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static FieldMask parseFrom(InputStream inputStream) throws IOException {
        return (FieldMask) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static FieldMask parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FieldMask) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static FieldMask parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static FieldMask parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static FieldMask parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static FieldMask parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<FieldMask> parser() {
        return PARSER;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldMask)) {
            return super.equals(obj);
        }
        FieldMask fieldMask = (FieldMask) obj;
        return (getPathsList().equals(fieldMask.getPathsList())) && this.unknownFields.equals(fieldMask.unknownFields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public FieldMask mo3095getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public Parser<FieldMask> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.FieldMaskOrBuilder
    public String getPaths(int i) {
        return (String) this.paths_.get(i);
    }

    @Override // com.google.protobuf.FieldMaskOrBuilder
    public ByteString getPathsBytes(int i) {
        return this.paths_.getByteString(i);
    }

    @Override // com.google.protobuf.FieldMaskOrBuilder
    public int getPathsCount() {
        return this.paths_.size();
    }

    @Override // com.google.protobuf.FieldMaskOrBuilder
    public ProtocolStringList getPathsList() {
        return this.paths_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.paths_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.paths_.getRaw(i3));
        }
        int size = 0 + i2 + (getPathsList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSize = size;
        return size;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        int i = hashCode;
        if (getPathsCount() > 0) {
            i = (((hashCode * 37) + 1) * 53) + getPathsList().hashCode();
        }
        int hashCode2 = (i * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return FieldMaskProto.internal_static_google_protobuf_FieldMask_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldMask.class, Builder.class);
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
    public Builder m3041newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3043toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.paths_.size()) {
                this.unknownFields.writeTo(codedOutputStream);
                return;
            } else {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.paths_.getRaw(i2));
                i = i2 + 1;
            }
        }
    }
}
