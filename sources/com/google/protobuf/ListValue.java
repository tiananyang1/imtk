package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.Value;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/ListValue.class */
public final class ListValue extends GeneratedMessageV3 implements ListValueOrBuilder {
    private static final ListValue DEFAULT_INSTANCE = new ListValue();
    private static final Parser<ListValue> PARSER = new AbstractParser<ListValue>() { // from class: com.google.protobuf.ListValue.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ListValue m3155parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListValue(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VALUES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Value> values_;

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/ListValue$Builder.class */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListValueOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> valuesBuilder_;
        private List<Value> values_;

        private Builder() {
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void ensureValuesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.values_ = new ArrayList(this.values_);
                this.bitField0_ |= 1;
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StructProto.internal_static_google_protobuf_ListValue_descriptor;
        }

        private RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> getValuesFieldBuilder() {
            if (this.valuesBuilder_ == null) {
                List<Value> list = this.values_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.valuesBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.values_ = null;
            }
            return this.valuesBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getValuesFieldBuilder();
            }
        }

        public Builder addAllValues(Iterable<? extends Value> iterable) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addAllMessages(iterable);
                return this;
            }
            ensureValuesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.values_);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: addRepeatedField, reason: avoid collision after fix types in other method */
        public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
        }

        public Builder addValues(int i, Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, builder.m3388build());
                return this;
            }
            ensureValuesIsMutable();
            this.values_.add(i, builder.m3388build());
            onChanged();
            return this;
        }

        public Builder addValues(int i, Value value) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, value);
                return this;
            }
            if (value == null) {
                throw new NullPointerException();
            }
            ensureValuesIsMutable();
            this.values_.add(i, value);
            onChanged();
            return this;
        }

        public Builder addValues(Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(builder.m3388build());
                return this;
            }
            ensureValuesIsMutable();
            this.values_.add(builder.m3388build());
            onChanged();
            return this;
        }

        public Builder addValues(Value value) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(value);
                return this;
            }
            if (value == null) {
                throw new NullPointerException();
            }
            ensureValuesIsMutable();
            this.values_.add(value);
            onChanged();
            return this;
        }

        public Value.Builder addValuesBuilder() {
            return getValuesFieldBuilder().addBuilder(Value.getDefaultInstance());
        }

        public Value.Builder addValuesBuilder(int i) {
            return getValuesFieldBuilder().addBuilder(i, Value.getDefaultInstance());
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.ListValue, com.google.protobuf.Message] */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListValue m3157build() {
            ?? m3159buildPartial = m3159buildPartial();
            if (m3159buildPartial.isInitialized()) {
                return m3159buildPartial;
            }
            throw newUninitializedMessageException(m3159buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListValue m3159buildPartial() {
            ListValue listValue = new ListValue(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.values_ = Collections.unmodifiableList(this.values_);
                    this.bitField0_ &= -2;
                }
                listValue.values_ = this.values_;
            } else {
                listValue.values_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return listValue;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clear, reason: merged with bridge method [inline-methods] */
        public Builder mo2896clear() {
            super.mo2896clear();
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.clear();
                return this;
            }
            this.values_ = Collections.emptyList();
            this.bitField0_ &= -2;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearField, reason: avoid collision after fix types in other method */
        public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo2897clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo2899clearOneof(oneofDescriptor);
        }

        public Builder clearValues() {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.clear();
                return this;
            }
            this.values_ = Collections.emptyList();
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder mo2904clone() {
            return (Builder) super.mo2904clone();
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
        public ListValue mo3095getDefaultInstanceForType() {
            return ListValue.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return StructProto.internal_static_google_protobuf_ListValue_descriptor;
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public Value getValues(int i) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 == null ? this.values_.get(i) : repeatedFieldBuilderV3.getMessage(i);
        }

        public Value.Builder getValuesBuilder(int i) {
            return getValuesFieldBuilder().getBuilder(i);
        }

        public List<Value.Builder> getValuesBuilderList() {
            return getValuesFieldBuilder().getBuilderList();
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public int getValuesCount() {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 == null ? this.values_.size() : repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public List<Value> getValuesList() {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.values_) : repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public ValueOrBuilder getValuesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 == null ? this.values_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.values_);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ListValue.class, Builder.class);
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
        public com.google.protobuf.ListValue.Builder m3165mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                r7 = r0
                com.google.protobuf.Parser r0 = com.google.protobuf.ListValue.access$400()     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r1 = r5
                r2 = r6
                java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                com.google.protobuf.ListValue r0 = (com.google.protobuf.ListValue) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r5 = r0
                r0 = r5
                if (r0 == 0) goto L1a
                r0 = r4
                r1 = r5
                com.google.protobuf.ListValue$Builder r0 = r0.mergeFrom(r1)
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
                com.google.protobuf.ListValue r0 = (com.google.protobuf.ListValue) r0     // Catch: java.lang.Throwable -> L1c
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
                com.google.protobuf.ListValue$Builder r0 = r0.mergeFrom(r1)
            L3b:
                r0 = r6
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ListValue.Builder.m3165mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.ListValue$Builder");
        }

        public Builder mergeFrom(ListValue listValue) {
            if (listValue == ListValue.getDefaultInstance()) {
                return this;
            }
            if (this.valuesBuilder_ == null) {
                if (!listValue.values_.isEmpty()) {
                    if (this.values_.isEmpty()) {
                        this.values_ = listValue.values_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureValuesIsMutable();
                        this.values_.addAll(listValue.values_);
                    }
                    onChanged();
                }
            } else if (!listValue.values_.isEmpty()) {
                if (this.valuesBuilder_.isEmpty()) {
                    this.valuesBuilder_.dispose();
                    RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = null;
                    this.valuesBuilder_ = null;
                    this.values_ = listValue.values_;
                    this.bitField0_ &= -2;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getValuesFieldBuilder();
                    }
                    this.valuesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.valuesBuilder_.addAllMessages(listValue.values_);
                }
            }
            mo2912mergeUnknownFields(listValue.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3164mergeFrom(Message message) {
            if (message instanceof ListValue) {
                return mergeFrom((ListValue) message);
            }
            super.mergeFrom(message);
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
        }

        public Builder removeValues(int i) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.remove(i);
                return this;
            }
            ensureValuesIsMutable();
            this.values_.remove(i);
            onChanged();
            return this;
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

        public Builder setValues(int i, Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, builder.m3388build());
                return this;
            }
            ensureValuesIsMutable();
            this.values_.set(i, builder.m3388build());
            onChanged();
            return this;
        }

        public Builder setValues(int i, Value value) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, value);
                return this;
            }
            if (value == null) {
                throw new NullPointerException();
            }
            ensureValuesIsMutable();
            this.values_.set(i, value);
            onChanged();
            return this;
        }
    }

    private ListValue() {
        this.memoizedIsInitialized = (byte) -1;
        this.values_ = Collections.emptyList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            boolean z4 = z2;
                            if (!(z2 & true)) {
                                this.values_ = new ArrayList();
                                z4 = z2 | true;
                            }
                            this.values_.add(codedInputStream.readMessage(Value.parser(), extensionRegistryLite));
                            z2 = z4;
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if (z3 & true) {
                    this.values_ = Collections.unmodifiableList(this.values_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                throw th;
            }
        }
        if (z2 & true) {
            this.values_ = Collections.unmodifiableList(this.values_);
        }
        this.unknownFields = newBuilder.build();
        makeExtensionsImmutable();
    }

    private ListValue(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static ListValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StructProto.internal_static_google_protobuf_ListValue_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3154toBuilder();
    }

    public static Builder newBuilder(ListValue listValue) {
        return DEFAULT_INSTANCE.m3154toBuilder().mergeFrom(listValue);
    }

    public static ListValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ListValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static ListValue parseFrom(InputStream inputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ListValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ListValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<ListValue> parser() {
        return PARSER;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListValue)) {
            return super.equals(obj);
        }
        ListValue listValue = (ListValue) obj;
        return (getValuesList().equals(listValue.getValuesList())) && this.unknownFields.equals(listValue.unknownFields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public ListValue mo3095getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public Parser<ListValue> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.values_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.values_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public Value getValues(int i) {
        return this.values_.get(i);
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public int getValuesCount() {
        return this.values_.size();
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public List<Value> getValuesList() {
        return this.values_;
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public ValueOrBuilder getValuesOrBuilder(int i) {
        return this.values_.get(i);
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
        return this.values_;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        int i = hashCode;
        if (getValuesCount() > 0) {
            i = (((hashCode * 37) + 1) * 53) + getValuesList().hashCode();
        }
        int hashCode2 = (i * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ListValue.class, Builder.class);
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
    public Builder m3152newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3154toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.values_.size()) {
                this.unknownFields.writeTo(codedOutputStream);
                return;
            } else {
                codedOutputStream.writeMessage(1, (MessageLite) this.values_.get(i2));
                i = i2 + 1;
            }
        }
    }
}
