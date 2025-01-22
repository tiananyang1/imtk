package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/Mixin.class */
public final class Mixin extends GeneratedMessageV3 implements MixinOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int ROOT_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private volatile Object root_;
    private static final Mixin DEFAULT_INSTANCE = new Mixin();
    private static final Parser<Mixin> PARSER = new AbstractParser<Mixin>() { // from class: com.google.protobuf.Mixin.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Mixin m3216parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Mixin(codedInputStream, extensionRegistryLite);
        }
    };

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/Mixin$Builder.class */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MixinOrBuilder {
        private Object name_;
        private Object root_;

        private Builder() {
            this.name_ = "";
            this.root_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.root_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ApiProto.internal_static_google_protobuf_Mixin_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean z = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: addRepeatedField */
        public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.Mixin, com.google.protobuf.Message] */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Mixin m3217build() {
            ?? m3219buildPartial = m3219buildPartial();
            if (m3219buildPartial.isInitialized()) {
                return m3219buildPartial;
            }
            throw newUninitializedMessageException(m3219buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Mixin m3219buildPartial() {
            Mixin mixin = new Mixin(this);
            mixin.name_ = this.name_;
            mixin.root_ = this.root_;
            onBuilt();
            return mixin;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clear */
        public Builder mo2896clear() {
            super.mo2896clear();
            this.name_ = "";
            this.root_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearField */
        public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo2897clearField(fieldDescriptor);
        }

        public Builder clearName() {
            this.name_ = Mixin.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearOneof */
        public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo2899clearOneof(oneofDescriptor);
        }

        public Builder clearRoot() {
            this.root_ = Mixin.getDefaultInstance().getRoot();
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
        public Mixin mo3095getDefaultInstanceForType() {
            return Mixin.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ApiProto.internal_static_google_protobuf_Mixin_descriptor;
        }

        @Override // com.google.protobuf.MixinOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.protobuf.MixinOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override // com.google.protobuf.MixinOrBuilder
        public String getRoot() {
            Object obj = this.root_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.root_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.protobuf.MixinOrBuilder
        public ByteString getRootBytes() {
            Object obj = this.root_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.root_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ApiProto.internal_static_google_protobuf_Mixin_fieldAccessorTable.ensureFieldAccessorsInitialized(Mixin.class, Builder.class);
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
        public com.google.protobuf.Mixin.Builder m3224mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                r7 = r0
                com.google.protobuf.Parser r0 = com.google.protobuf.Mixin.access$500()     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r1 = r5
                r2 = r6
                java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                com.google.protobuf.Mixin r0 = (com.google.protobuf.Mixin) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r5 = r0
                r0 = r5
                if (r0 == 0) goto L1a
                r0 = r4
                r1 = r5
                com.google.protobuf.Mixin$Builder r0 = r0.mergeFrom(r1)
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
                com.google.protobuf.Mixin r0 = (com.google.protobuf.Mixin) r0     // Catch: java.lang.Throwable -> L1c
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
                com.google.protobuf.Mixin$Builder r0 = r0.mergeFrom(r1)
            L3b:
                r0 = r6
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Mixin.Builder.m3224mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Mixin$Builder");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3223mergeFrom(Message message) {
            if (message instanceof Mixin) {
                return mergeFrom((Mixin) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Mixin mixin) {
            if (mixin == Mixin.getDefaultInstance()) {
                return this;
            }
            if (!mixin.getName().isEmpty()) {
                this.name_ = mixin.name_;
                onChanged();
            }
            if (!mixin.getRoot().isEmpty()) {
                this.root_ = mixin.root_;
                onChanged();
            }
            mo2912mergeUnknownFields(mixin.unknownFields);
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

        public Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setRepeatedField */
        public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder setRoot(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.root_ = str;
            onChanged();
            return this;
        }

        public Builder setRootBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.root_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }
    }

    private Mixin() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.root_ = "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Mixin(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.root_ = codedInputStream.readStringRequireUtf8();
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
            } finally {
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    private Mixin(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static Mixin getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ApiProto.internal_static_google_protobuf_Mixin_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3214toBuilder();
    }

    public static Builder newBuilder(Mixin mixin) {
        return DEFAULT_INSTANCE.m3214toBuilder().mergeFrom(mixin);
    }

    public static Mixin parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Mixin) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Mixin parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Mixin) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Mixin parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Mixin parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Mixin parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Mixin) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Mixin parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Mixin) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Mixin parseFrom(InputStream inputStream) throws IOException {
        return (Mixin) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Mixin parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Mixin) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Mixin parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Mixin parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Mixin parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Mixin parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<Mixin> parser() {
        return PARSER;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Mixin)) {
            return super.equals(obj);
        }
        Mixin mixin = (Mixin) obj;
        return ((getName().equals(mixin.getName())) && getRoot().equals(mixin.getRoot())) && this.unknownFields.equals(mixin.unknownFields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public Mixin mo3095getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MixinOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.protobuf.MixinOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public Parser<Mixin> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MixinOrBuilder
    public String getRoot() {
        Object obj = this.root_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.root_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.protobuf.MixinOrBuilder
    public ByteString getRootBytes() {
        Object obj = this.root_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.root_ = copyFromUtf8;
        return copyFromUtf8;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getNameBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        }
        int i3 = i2;
        if (!getRootBytes().isEmpty()) {
            i3 = i2 + GeneratedMessageV3.computeStringSize(2, this.root_);
        }
        int serializedSize = i3 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getRoot().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ApiProto.internal_static_google_protobuf_Mixin_fieldAccessorTable.ensureFieldAccessorsInitialized(Mixin.class, Builder.class);
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
    public Builder m3212newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3214toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getRootBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.root_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
