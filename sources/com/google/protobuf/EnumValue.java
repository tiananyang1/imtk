package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Option;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/EnumValue.class */
public final class EnumValue extends GeneratedMessageV3 implements EnumValueOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int NUMBER_FIELD_NUMBER = 2;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private int number_;
    private List<Option> options_;
    private static final EnumValue DEFAULT_INSTANCE = new EnumValue();
    private static final Parser<EnumValue> PARSER = new AbstractParser<EnumValue>() { // from class: com.google.protobuf.EnumValue.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public EnumValue m3004parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new EnumValue(codedInputStream, extensionRegistryLite);
        }
    };

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/EnumValue$Builder.class */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnumValueOrBuilder {
        private int bitField0_;
        private Object name_;
        private int number_;
        private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> optionsBuilder_;
        private List<Option> options_;

        private Builder() {
            this.name_ = "";
            this.options_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.options_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void ensureOptionsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.options_ = new ArrayList(this.options_);
                this.bitField0_ |= 4;
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TypeProto.internal_static_google_protobuf_EnumValue_descriptor;
        }

        private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> getOptionsFieldBuilder() {
            if (this.optionsBuilder_ == null) {
                this.optionsBuilder_ = new RepeatedFieldBuilderV3<>(this.options_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.options_ = null;
            }
            return this.optionsBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getOptionsFieldBuilder();
            }
        }

        public Builder addAllOptions(Iterable<? extends Option> iterable) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addAllMessages(iterable);
                return this;
            }
            ensureOptionsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.options_);
            onChanged();
            return this;
        }

        public Builder addOptions(int i, Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, builder.m3235build());
                return this;
            }
            ensureOptionsIsMutable();
            this.options_.add(i, builder.m3235build());
            onChanged();
            return this;
        }

        public Builder addOptions(int i, Option option) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, option);
                return this;
            }
            if (option == null) {
                throw new NullPointerException();
            }
            ensureOptionsIsMutable();
            this.options_.add(i, option);
            onChanged();
            return this;
        }

        public Builder addOptions(Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(builder.m3235build());
                return this;
            }
            ensureOptionsIsMutable();
            this.options_.add(builder.m3235build());
            onChanged();
            return this;
        }

        public Builder addOptions(Option option) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(option);
                return this;
            }
            if (option == null) {
                throw new NullPointerException();
            }
            ensureOptionsIsMutable();
            this.options_.add(option);
            onChanged();
            return this;
        }

        public Option.Builder addOptionsBuilder() {
            return getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance());
        }

        public Option.Builder addOptionsBuilder(int i) {
            return getOptionsFieldBuilder().addBuilder(i, Option.getDefaultInstance());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: addRepeatedField */
        public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.EnumValue, com.google.protobuf.Message] */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public EnumValue m3006build() {
            ?? m3008buildPartial = m3008buildPartial();
            if (m3008buildPartial.isInitialized()) {
                return m3008buildPartial;
            }
            throw newUninitializedMessageException(m3008buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public EnumValue m3008buildPartial() {
            EnumValue enumValue = new EnumValue(this);
            int i = this.bitField0_;
            enumValue.name_ = this.name_;
            enumValue.number_ = this.number_;
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                    this.bitField0_ &= -5;
                }
                enumValue.options_ = this.options_;
            } else {
                enumValue.options_ = repeatedFieldBuilderV3.build();
            }
            enumValue.bitField0_ = 0;
            onBuilt();
            return enumValue;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clear */
        public Builder mo2896clear() {
            super.mo2896clear();
            this.name_ = "";
            this.number_ = 0;
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.clear();
                return this;
            }
            this.options_ = Collections.emptyList();
            this.bitField0_ &= -5;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearField */
        public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo2897clearField(fieldDescriptor);
        }

        public Builder clearName() {
            this.name_ = EnumValue.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder clearNumber() {
            this.number_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearOneof */
        public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo2899clearOneof(oneofDescriptor);
        }

        public Builder clearOptions() {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.clear();
                return this;
            }
            this.options_ = Collections.emptyList();
            this.bitField0_ &= -5;
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
        public EnumValue mo3095getDefaultInstanceForType() {
            return EnumValue.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TypeProto.internal_static_google_protobuf_EnumValue_descriptor;
        }

        @Override // com.google.protobuf.EnumValueOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.protobuf.EnumValueOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override // com.google.protobuf.EnumValueOrBuilder
        public int getNumber() {
            return this.number_;
        }

        @Override // com.google.protobuf.EnumValueOrBuilder
        public Option getOptions(int i) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 == null ? this.options_.get(i) : repeatedFieldBuilderV3.getMessage(i);
        }

        public Option.Builder getOptionsBuilder(int i) {
            return getOptionsFieldBuilder().getBuilder(i);
        }

        public List<Option.Builder> getOptionsBuilderList() {
            return getOptionsFieldBuilder().getBuilderList();
        }

        @Override // com.google.protobuf.EnumValueOrBuilder
        public int getOptionsCount() {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 == null ? this.options_.size() : repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.protobuf.EnumValueOrBuilder
        public List<Option> getOptionsList() {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.options_) : repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.protobuf.EnumValueOrBuilder
        public OptionOrBuilder getOptionsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 == null ? this.options_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.protobuf.EnumValueOrBuilder
        public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.options_);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TypeProto.internal_static_google_protobuf_EnumValue_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValue.class, Builder.class);
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
        public com.google.protobuf.EnumValue.Builder m3014mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                r7 = r0
                com.google.protobuf.Parser r0 = com.google.protobuf.EnumValue.access$700()     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r1 = r5
                r2 = r6
                java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                com.google.protobuf.EnumValue r0 = (com.google.protobuf.EnumValue) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r5 = r0
                r0 = r5
                if (r0 == 0) goto L1a
                r0 = r4
                r1 = r5
                com.google.protobuf.EnumValue$Builder r0 = r0.mergeFrom(r1)
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
                com.google.protobuf.EnumValue r0 = (com.google.protobuf.EnumValue) r0     // Catch: java.lang.Throwable -> L1c
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
                com.google.protobuf.EnumValue$Builder r0 = r0.mergeFrom(r1)
            L3b:
                r0 = r6
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.EnumValue.Builder.m3014mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.EnumValue$Builder");
        }

        public Builder mergeFrom(EnumValue enumValue) {
            if (enumValue == EnumValue.getDefaultInstance()) {
                return this;
            }
            if (!enumValue.getName().isEmpty()) {
                this.name_ = enumValue.name_;
                onChanged();
            }
            if (enumValue.getNumber() != 0) {
                setNumber(enumValue.getNumber());
            }
            if (this.optionsBuilder_ == null) {
                if (!enumValue.options_.isEmpty()) {
                    if (this.options_.isEmpty()) {
                        this.options_ = enumValue.options_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureOptionsIsMutable();
                        this.options_.addAll(enumValue.options_);
                    }
                    onChanged();
                }
            } else if (!enumValue.options_.isEmpty()) {
                if (this.optionsBuilder_.isEmpty()) {
                    this.optionsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = null;
                    this.optionsBuilder_ = null;
                    this.options_ = enumValue.options_;
                    this.bitField0_ &= -5;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getOptionsFieldBuilder();
                    }
                    this.optionsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.optionsBuilder_.addAllMessages(enumValue.options_);
                }
            }
            mo2912mergeUnknownFields(enumValue.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3013mergeFrom(Message message) {
            if (message instanceof EnumValue) {
                return mergeFrom((EnumValue) message);
            }
            super.mergeFrom(message);
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
        }

        public Builder removeOptions(int i) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.remove(i);
                return this;
            }
            ensureOptionsIsMutable();
            this.options_.remove(i);
            onChanged();
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
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

        public Builder setNumber(int i) {
            this.number_ = i;
            onChanged();
            return this;
        }

        public Builder setOptions(int i, Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, builder.m3235build());
                return this;
            }
            ensureOptionsIsMutable();
            this.options_.set(i, builder.m3235build());
            onChanged();
            return this;
        }

        public Builder setOptions(int i, Option option) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, option);
                return this;
            }
            if (option == null) {
                throw new NullPointerException();
            }
            ensureOptionsIsMutable();
            this.options_.set(i, option);
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

    private EnumValue() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.number_ = 0;
        this.options_ = Collections.emptyList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private EnumValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        byte b = false;
        byte b2 = false;
        while (b == false) {
            byte b3 = b2;
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 16) {
                            this.number_ = codedInputStream.readInt32();
                        } else if (readTag == 26) {
                            byte b4 = b2;
                            if (((b2 == true ? 1 : 0) & 4) != 4) {
                                this.options_ = new ArrayList();
                                b4 = ((b2 == true ? 1 : 0) | 4) == true ? 1 : 0;
                            }
                            this.options_.add(codedInputStream.readMessage(Option.parser(), extensionRegistryLite));
                            b2 = b4;
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    b = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if (((b3 == true ? 1 : 0) & 4) == 4) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                throw th;
            }
        }
        if (((b2 == true ? 1 : 0) & 4) == 4) {
            this.options_ = Collections.unmodifiableList(this.options_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    private EnumValue(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static EnumValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TypeProto.internal_static_google_protobuf_EnumValue_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3003toBuilder();
    }

    public static Builder newBuilder(EnumValue enumValue) {
        return DEFAULT_INSTANCE.m3003toBuilder().mergeFrom(enumValue);
    }

    public static EnumValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EnumValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static EnumValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EnumValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EnumValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static EnumValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static EnumValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EnumValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static EnumValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EnumValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static EnumValue parseFrom(InputStream inputStream) throws IOException {
        return (EnumValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static EnumValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EnumValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EnumValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static EnumValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static EnumValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static EnumValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<EnumValue> parser() {
        return PARSER;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EnumValue)) {
            return super.equals(obj);
        }
        EnumValue enumValue = (EnumValue) obj;
        return (((getName().equals(enumValue.getName())) && getNumber() == enumValue.getNumber()) && getOptionsList().equals(enumValue.getOptionsList())) && this.unknownFields.equals(enumValue.unknownFields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public EnumValue mo3095getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.EnumValueOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.protobuf.EnumValueOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    @Override // com.google.protobuf.EnumValueOrBuilder
    public int getNumber() {
        return this.number_;
    }

    @Override // com.google.protobuf.EnumValueOrBuilder
    public Option getOptions(int i) {
        return this.options_.get(i);
    }

    @Override // com.google.protobuf.EnumValueOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override // com.google.protobuf.EnumValueOrBuilder
    public List<Option> getOptionsList() {
        return this.options_;
    }

    @Override // com.google.protobuf.EnumValueOrBuilder
    public OptionOrBuilder getOptionsOrBuilder(int i) {
        return this.options_.get(i);
    }

    @Override // com.google.protobuf.EnumValueOrBuilder
    public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
        return this.options_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public Parser<EnumValue> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        int i2 = this.number_;
        int i3 = computeStringSize;
        int i4 = 0;
        if (i2 != 0) {
            i3 = computeStringSize + CodedOutputStream.computeInt32Size(2, i2);
            i4 = 0;
        }
        while (i4 < this.options_.size()) {
            i3 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.options_.get(i4));
            i4++;
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
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getNumber();
        int i = hashCode;
        if (getOptionsCount() > 0) {
            i = (((hashCode * 37) + 3) * 53) + getOptionsList().hashCode();
        }
        int hashCode2 = (i * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TypeProto.internal_static_google_protobuf_EnumValue_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumValue.class, Builder.class);
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
    public Builder m3001newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3003toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        int i = this.number_;
        if (i != 0) {
            codedOutputStream.writeInt32(2, i);
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.options_.size()) {
                this.unknownFields.writeTo(codedOutputStream);
                return;
            } else {
                codedOutputStream.writeMessage(3, (MessageLite) this.options_.get(i3));
                i2 = i3 + 1;
            }
        }
    }
}
