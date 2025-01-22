package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/Value.class */
public final class Value extends GeneratedMessageV3 implements ValueOrBuilder {
    public static final int BOOL_VALUE_FIELD_NUMBER = 4;
    public static final int LIST_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 1;
    public static final int NUMBER_VALUE_FIELD_NUMBER = 2;
    public static final int STRING_VALUE_FIELD_NUMBER = 3;
    public static final int STRUCT_VALUE_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private int kindCase_;
    private Object kind_;
    private byte memoizedIsInitialized;
    private static final Value DEFAULT_INSTANCE = new Value();
    private static final Parser<Value> PARSER = new AbstractParser<Value>() { // from class: com.google.protobuf.Value.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Value m3386parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Value(codedInputStream, extensionRegistryLite);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.Value$2 */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/Value$2.class */
    public static /* synthetic */ class C00662 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Value$KindCase = new int[KindCase.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:43:0x006d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.google.protobuf.Value$KindCase[] r0 = com.google.protobuf.Value.KindCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.protobuf.Value.C00662.$SwitchMap$com$google$protobuf$Value$KindCase = r0
                int[] r0 = com.google.protobuf.Value.C00662.$SwitchMap$com$google$protobuf$Value$KindCase     // Catch: java.lang.NoSuchFieldError -> L59
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.NULL_VALUE     // Catch: java.lang.NoSuchFieldError -> L59
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L59
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L59
            L14:
                int[] r0 = com.google.protobuf.Value.C00662.$SwitchMap$com$google$protobuf$Value$KindCase     // Catch: java.lang.NoSuchFieldError -> L59 java.lang.NoSuchFieldError -> L5d
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.NUMBER_VALUE     // Catch: java.lang.NoSuchFieldError -> L5d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L5d
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L5d
            L1f:
                int[] r0 = com.google.protobuf.Value.C00662.$SwitchMap$com$google$protobuf$Value$KindCase     // Catch: java.lang.NoSuchFieldError -> L5d java.lang.NoSuchFieldError -> L61
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.STRING_VALUE     // Catch: java.lang.NoSuchFieldError -> L61
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L61
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L61
            L2a:
                int[] r0 = com.google.protobuf.Value.C00662.$SwitchMap$com$google$protobuf$Value$KindCase     // Catch: java.lang.NoSuchFieldError -> L61 java.lang.NoSuchFieldError -> L65
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.BOOL_VALUE     // Catch: java.lang.NoSuchFieldError -> L65
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L65
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L65
            L35:
                int[] r0 = com.google.protobuf.Value.C00662.$SwitchMap$com$google$protobuf$Value$KindCase     // Catch: java.lang.NoSuchFieldError -> L65 java.lang.NoSuchFieldError -> L69
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.STRUCT_VALUE     // Catch: java.lang.NoSuchFieldError -> L69
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L69
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L69
            L40:
                int[] r0 = com.google.protobuf.Value.C00662.$SwitchMap$com$google$protobuf$Value$KindCase     // Catch: java.lang.NoSuchFieldError -> L69 java.lang.NoSuchFieldError -> L6d
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.LIST_VALUE     // Catch: java.lang.NoSuchFieldError -> L6d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L6d
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L6d
            L4c:
                int[] r0 = com.google.protobuf.Value.C00662.$SwitchMap$com$google$protobuf$Value$KindCase     // Catch: java.lang.NoSuchFieldError -> L6d java.lang.NoSuchFieldError -> L71
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.KIND_NOT_SET     // Catch: java.lang.NoSuchFieldError -> L71
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L71
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L71
                return
            L59:
                r4 = move-exception
                goto L14
            L5d:
                r4 = move-exception
                goto L1f
            L61:
                r4 = move-exception
                goto L2a
            L65:
                r4 = move-exception
                goto L35
            L69:
                r4 = move-exception
                goto L40
            L6d:
                r4 = move-exception
                goto L4c
            L71:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Value.C00662.m3387clinit():void");
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/Value$Builder.class */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValueOrBuilder {
        private int kindCase_;
        private Object kind_;
        private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> listValueBuilder_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> structValueBuilder_;

        private Builder() {
            this.kindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.kindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StructProto.internal_static_google_protobuf_Value_descriptor;
        }

        private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> getListValueFieldBuilder() {
            if (this.listValueBuilder_ == null) {
                if (this.kindCase_ != 6) {
                    this.kind_ = ListValue.getDefaultInstance();
                }
                this.listValueBuilder_ = new SingleFieldBuilderV3<>((ListValue) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 6;
            onChanged();
            return this.listValueBuilder_;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getStructValueFieldBuilder() {
            if (this.structValueBuilder_ == null) {
                if (this.kindCase_ != 5) {
                    this.kind_ = Struct.getDefaultInstance();
                }
                this.structValueBuilder_ = new SingleFieldBuilderV3<>((Struct) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 5;
            onChanged();
            return this.structValueBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            boolean z = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: addRepeatedField */
        public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.Message, com.google.protobuf.Value] */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Value m3388build() {
            ?? m3390buildPartial = m3390buildPartial();
            if (m3390buildPartial.isInitialized()) {
                return m3390buildPartial;
            }
            throw newUninitializedMessageException(m3390buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Value m3390buildPartial() {
            Value value = new Value(this);
            if (this.kindCase_ == 1) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 2) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 3) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 4) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 5) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
                if (singleFieldBuilderV3 == null) {
                    value.kind_ = this.kind_;
                } else {
                    value.kind_ = singleFieldBuilderV3.build();
                }
            }
            if (this.kindCase_ == 6) {
                SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV32 = this.listValueBuilder_;
                if (singleFieldBuilderV32 == null) {
                    value.kind_ = this.kind_;
                } else {
                    value.kind_ = singleFieldBuilderV32.build();
                }
            }
            value.kindCase_ = this.kindCase_;
            onBuilt();
            return value;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clear */
        public Builder mo2896clear() {
            super.mo2896clear();
            this.kindCase_ = 0;
            this.kind_ = null;
            return this;
        }

        public Builder clearBoolValue() {
            if (this.kindCase_ == 4) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearField */
        public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo2897clearField(fieldDescriptor);
        }

        public Builder clearKind() {
            this.kindCase_ = 0;
            this.kind_ = null;
            onChanged();
            return this;
        }

        public Builder clearListValue() {
            if (this.listValueBuilder_ != null) {
                if (this.kindCase_ == 6) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                this.listValueBuilder_.clear();
            } else if (this.kindCase_ == 6) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
                return this;
            }
            return this;
        }

        public Builder clearNullValue() {
            if (this.kindCase_ == 1) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Builder clearNumberValue() {
            if (this.kindCase_ == 2) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clearOneof */
        public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo2899clearOneof(oneofDescriptor);
        }

        public Builder clearStringValue() {
            if (this.kindCase_ == 3) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Builder clearStructValue() {
            if (this.structValueBuilder_ != null) {
                if (this.kindCase_ == 5) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                this.structValueBuilder_.clear();
            } else if (this.kindCase_ == 5) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
                return this;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Builder mo2904clone() {
            return (Builder) super.mo2904clone();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public boolean getBoolValue() {
            if (this.kindCase_ == 4) {
                return ((Boolean) this.kind_).booleanValue();
            }
            return false;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
        public Value mo3095getDefaultInstanceForType() {
            return Value.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return StructProto.internal_static_google_protobuf_Value_descriptor;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public KindCase getKindCase() {
            return KindCase.forNumber(this.kindCase_);
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ListValue getListValue() {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            return singleFieldBuilderV3 == null ? this.kindCase_ == 6 ? (ListValue) this.kind_ : ListValue.getDefaultInstance() : this.kindCase_ == 6 ? singleFieldBuilderV3.getMessage() : ListValue.getDefaultInstance();
        }

        public ListValue.Builder getListValueBuilder() {
            return getListValueFieldBuilder().getBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ListValueOrBuilder getListValueOrBuilder() {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3;
            return (this.kindCase_ != 6 || (singleFieldBuilderV3 = this.listValueBuilder_) == null) ? this.kindCase_ == 6 ? (ListValue) this.kind_ : ListValue.getDefaultInstance() : singleFieldBuilderV3.getMessageOrBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public NullValue getNullValue() {
            if (this.kindCase_ != 1) {
                return NullValue.NULL_VALUE;
            }
            NullValue valueOf = NullValue.valueOf(((Integer) this.kind_).intValue());
            NullValue nullValue = valueOf;
            if (valueOf == null) {
                nullValue = NullValue.UNRECOGNIZED;
            }
            return nullValue;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public int getNullValueValue() {
            if (this.kindCase_ == 1) {
                return ((Integer) this.kind_).intValue();
            }
            return 0;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public double getNumberValue() {
            if (this.kindCase_ == 2) {
                return ((Double) this.kind_).doubleValue();
            }
            return 0.0d;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public String getStringValue() {
            String str = this.kindCase_ == 3 ? this.kind_ : "";
            if (str instanceof String) {
                return (String) str;
            }
            String stringUtf8 = ((ByteString) str).toStringUtf8();
            if (this.kindCase_ == 3) {
                this.kind_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ByteString getStringValueBytes() {
            String str = this.kindCase_ == 3 ? this.kind_ : "";
            if (!(str instanceof String)) {
                return (ByteString) str;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.kindCase_ == 3) {
                this.kind_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public Struct getStructValue() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            return singleFieldBuilderV3 == null ? this.kindCase_ == 5 ? (Struct) this.kind_ : Struct.getDefaultInstance() : this.kindCase_ == 5 ? singleFieldBuilderV3.getMessage() : Struct.getDefaultInstance();
        }

        public Struct.Builder getStructValueBuilder() {
            return getStructValueFieldBuilder().getBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public StructOrBuilder getStructValueOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3;
            return (this.kindCase_ != 5 || (singleFieldBuilderV3 = this.structValueBuilder_) == null) ? this.kindCase_ == 5 ? (Struct) this.kind_ : Struct.getDefaultInstance() : singleFieldBuilderV3.getMessageOrBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public boolean hasListValue() {
            return this.kindCase_ == 6;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public boolean hasStructValue() {
            return this.kindCase_ == 5;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Value.class, Builder.class);
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
        public com.google.protobuf.Value.Builder m3395mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                r7 = r0
                com.google.protobuf.Parser r0 = com.google.protobuf.Value.access$500()     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r1 = r5
                r2 = r6
                java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                com.google.protobuf.Value r0 = (com.google.protobuf.Value) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                r5 = r0
                r0 = r5
                if (r0 == 0) goto L1a
                r0 = r4
                r1 = r5
                com.google.protobuf.Value$Builder r0 = r0.mergeFrom(r1)
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
                com.google.protobuf.Value r0 = (com.google.protobuf.Value) r0     // Catch: java.lang.Throwable -> L1c
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
                com.google.protobuf.Value$Builder r0 = r0.mergeFrom(r1)
            L3b:
                r0 = r6
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Value.Builder.m3395mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Value$Builder");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3394mergeFrom(Message message) {
            if (message instanceof Value) {
                return mergeFrom((Value) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Value value) {
            if (value == Value.getDefaultInstance()) {
                return this;
            }
            switch (C00662.$SwitchMap$com$google$protobuf$Value$KindCase[value.getKindCase().ordinal()]) {
                case 1:
                    setNullValueValue(value.getNullValueValue());
                    break;
                case 2:
                    setNumberValue(value.getNumberValue());
                    break;
                case 3:
                    this.kindCase_ = 3;
                    this.kind_ = value.kind_;
                    onChanged();
                    break;
                case 4:
                    setBoolValue(value.getBoolValue());
                    break;
                case 5:
                    mergeStructValue(value.getStructValue());
                    break;
                case 6:
                    mergeListValue(value.getListValue());
                    break;
            }
            mo2912mergeUnknownFields(value.unknownFields);
            onChanged();
            return this;
        }

        public Builder mergeListValue(ListValue listValue) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ != 6 || this.kind_ == ListValue.getDefaultInstance()) {
                    this.kind_ = listValue;
                } else {
                    this.kind_ = ListValue.newBuilder((ListValue) this.kind_).mergeFrom(listValue).m3159buildPartial();
                }
                onChanged();
            } else {
                if (this.kindCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(listValue);
                }
                this.listValueBuilder_.setMessage(listValue);
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder mergeStructValue(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ != 5 || this.kind_ == Struct.getDefaultInstance()) {
                    this.kind_ = struct;
                } else {
                    this.kind_ = Struct.newBuilder((Struct) this.kind_).mergeFrom(struct).m3290buildPartial();
                }
                onChanged();
            } else {
                if (this.kindCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(struct);
                }
                this.structValueBuilder_.setMessage(struct);
            }
            this.kindCase_ = 5;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
        }

        public Builder setBoolValue(boolean z) {
            this.kindCase_ = 4;
            this.kind_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setField */
        public Builder mo2913setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo2913setField(fieldDescriptor, obj);
        }

        public Builder setListValue(ListValue.Builder builder) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.kind_ = builder.m3157build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m3157build());
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder setListValue(ListValue listValue) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(listValue);
            } else {
                if (listValue == null) {
                    throw new NullPointerException();
                }
                this.kind_ = listValue;
                onChanged();
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder setNullValue(NullValue nullValue) {
            if (nullValue == null) {
                throw new NullPointerException();
            }
            this.kindCase_ = 1;
            this.kind_ = Integer.valueOf(nullValue.getNumber());
            onChanged();
            return this;
        }

        public Builder setNullValueValue(int i) {
            this.kindCase_ = 1;
            this.kind_ = Integer.valueOf(i);
            onChanged();
            return this;
        }

        public Builder setNumberValue(double d) {
            this.kindCase_ = 2;
            this.kind_ = Double.valueOf(d);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setRepeatedField */
        public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder setStringValue(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.kindCase_ = 3;
            this.kind_ = str;
            onChanged();
            return this;
        }

        public Builder setStringValueBytes(ByteString byteString) {
            if (byteString == null) {
                throw new NullPointerException();
            }
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.kindCase_ = 3;
            this.kind_ = byteString;
            onChanged();
            return this;
        }

        public Builder setStructValue(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.kind_ = builder.m3288build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m3288build());
            }
            this.kindCase_ = 5;
            return this;
        }

        public Builder setStructValue(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else {
                if (struct == null) {
                    throw new NullPointerException();
                }
                this.kind_ = struct;
                onChanged();
            }
            this.kindCase_ = 5;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/Value$KindCase.class */
    public enum KindCase implements Internal.EnumLite {
        NULL_VALUE(1),
        NUMBER_VALUE(2),
        STRING_VALUE(3),
        BOOL_VALUE(4),
        STRUCT_VALUE(5),
        LIST_VALUE(6),
        KIND_NOT_SET(0);

        private final int value;

        KindCase(int i) {
            this.value = i;
        }

        public static KindCase forNumber(int i) {
            switch (i) {
                case 0:
                    return KIND_NOT_SET;
                case 1:
                    return NULL_VALUE;
                case 2:
                    return NUMBER_VALUE;
                case 3:
                    return STRING_VALUE;
                case 4:
                    return BOOL_VALUE;
                case 5:
                    return STRUCT_VALUE;
                case 6:
                    return LIST_VALUE;
                default:
                    return null;
            }
        }

        @Deprecated
        public static KindCase valueOf(int i) {
            return forNumber(i);
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    private Value() {
        this.kindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Value(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (readTag == 8) {
                                int readEnum = codedInputStream.readEnum();
                                this.kindCase_ = 1;
                                this.kind_ = Integer.valueOf(readEnum);
                            } else if (readTag == 17) {
                                this.kindCase_ = 2;
                                this.kind_ = Double.valueOf(codedInputStream.readDouble());
                            } else if (readTag == 26) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.kindCase_ = 3;
                                this.kind_ = readStringRequireUtf8;
                            } else if (readTag != 32) {
                                ListValue.Builder builder = null;
                                if (readTag == 42) {
                                    Struct.Builder m3285toBuilder = this.kindCase_ == 5 ? ((Struct) this.kind_).m3285toBuilder() : null;
                                    this.kind_ = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                    if (m3285toBuilder != null) {
                                        m3285toBuilder.mergeFrom((Struct) this.kind_);
                                        this.kind_ = m3285toBuilder.m3290buildPartial();
                                    }
                                    this.kindCase_ = 5;
                                } else if (readTag == 50) {
                                    builder = this.kindCase_ == 6 ? ((ListValue) this.kind_).m3154toBuilder() : builder;
                                    this.kind_ = codedInputStream.readMessage(ListValue.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom((ListValue) this.kind_);
                                        this.kind_ = builder.m3159buildPartial();
                                    }
                                    this.kindCase_ = 6;
                                } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.kindCase_ = 4;
                                this.kind_ = Boolean.valueOf(codedInputStream.readBool());
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

    private Value(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.kindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StructProto.internal_static_google_protobuf_Value_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m3384toBuilder();
    }

    public static Builder newBuilder(Value value) {
        return DEFAULT_INSTANCE.m3384toBuilder().mergeFrom(value);
    }

    public static Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Value parseFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<Value> parser() {
        return PARSER;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0072, code lost:            if (getListValue().equals(r0.getListValue()) != false) goto L25;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0075, code lost:            r7 = true;     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008e, code lost:            if (getStructValue().equals(r0.getStructValue()) != false) goto L25;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a0, code lost:            if (getBoolValue() == r0.getBoolValue()) goto L25;     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b5, code lost:            if (getStringValue().equals(r0.getStringValue()) != false) goto L25;     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ce, code lost:            if (java.lang.Double.doubleToLongBits(getNumberValue()) == java.lang.Double.doubleToLongBits(r0.getNumberValue())) goto L25;     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e0, code lost:            if (getNullValueValue() == r0.getNullValueValue()) goto L25;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x0038. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r6) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Value.equals(java.lang.Object):boolean");
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public boolean getBoolValue() {
        if (this.kindCase_ == 4) {
            return ((Boolean) this.kind_).booleanValue();
        }
        return false;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public Value mo3095getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public KindCase getKindCase() {
        return KindCase.forNumber(this.kindCase_);
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ListValue getListValue() {
        return this.kindCase_ == 6 ? (ListValue) this.kind_ : ListValue.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ListValueOrBuilder getListValueOrBuilder() {
        return this.kindCase_ == 6 ? (ListValue) this.kind_ : ListValue.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public NullValue getNullValue() {
        if (this.kindCase_ != 1) {
            return NullValue.NULL_VALUE;
        }
        NullValue valueOf = NullValue.valueOf(((Integer) this.kind_).intValue());
        NullValue nullValue = valueOf;
        if (valueOf == null) {
            nullValue = NullValue.UNRECOGNIZED;
        }
        return nullValue;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public int getNullValueValue() {
        if (this.kindCase_ == 1) {
            return ((Integer) this.kind_).intValue();
        }
        return 0;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public double getNumberValue() {
        if (this.kindCase_ == 2) {
            return ((Double) this.kind_).doubleValue();
        }
        return 0.0d;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public Parser<Value> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.kindCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, ((Integer) this.kind_).intValue());
        }
        int i3 = i2;
        if (this.kindCase_ == 2) {
            i3 = i2 + CodedOutputStream.computeDoubleSize(2, ((Double) this.kind_).doubleValue());
        }
        int i4 = i3;
        if (this.kindCase_ == 3) {
            i4 = i3 + GeneratedMessageV3.computeStringSize(3, this.kind_);
        }
        int i5 = i4;
        if (this.kindCase_ == 4) {
            i5 = i4 + CodedOutputStream.computeBoolSize(4, ((Boolean) this.kind_).booleanValue());
        }
        int i6 = i5;
        if (this.kindCase_ == 5) {
            i6 = i5 + CodedOutputStream.computeMessageSize(5, (Struct) this.kind_);
        }
        int i7 = i6;
        if (this.kindCase_ == 6) {
            i7 = i6 + CodedOutputStream.computeMessageSize(6, (ListValue) this.kind_);
        }
        int serializedSize = i7 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public String getStringValue() {
        String str = this.kindCase_ == 3 ? this.kind_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.kindCase_ == 3) {
            this.kind_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ByteString getStringValueBytes() {
        String str = this.kindCase_ == 3 ? this.kind_ : "";
        if (!(str instanceof String)) {
            return (ByteString) str;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
        if (this.kindCase_ == 3) {
            this.kind_ = copyFromUtf8;
        }
        return copyFromUtf8;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public Struct getStructValue() {
        return this.kindCase_ == 5 ? (Struct) this.kind_ : Struct.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public StructOrBuilder getStructValueOrBuilder() {
        return this.kindCase_ == 5 ? (Struct) this.kind_ : Struct.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public boolean hasListValue() {
        return this.kindCase_ == 6;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public boolean hasStructValue() {
        return this.kindCase_ == 5;
    }

    public int hashCode() {
        int i;
        int nullValueValue;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        switch (this.kindCase_) {
            case 1:
                i = ((hashCode * 37) + 1) * 53;
                nullValueValue = getNullValueValue();
                break;
            case 2:
                i = ((hashCode * 37) + 2) * 53;
                nullValueValue = Internal.hashLong(Double.doubleToLongBits(getNumberValue()));
                break;
            case 3:
                i = ((hashCode * 37) + 3) * 53;
                nullValueValue = getStringValue().hashCode();
                break;
            case 4:
                i = ((hashCode * 37) + 4) * 53;
                nullValueValue = Internal.hashBoolean(getBoolValue());
                break;
            case 5:
                i = ((hashCode * 37) + 5) * 53;
                nullValueValue = getStructValue().hashCode();
                break;
            case 6:
                i = ((hashCode * 37) + 6) * 53;
                nullValueValue = getListValue().hashCode();
                break;
        }
        hashCode = i + nullValueValue;
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Value.class, Builder.class);
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
    public Builder m3382newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m3384toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.kindCase_ == 1) {
            codedOutputStream.writeEnum(1, ((Integer) this.kind_).intValue());
        }
        if (this.kindCase_ == 2) {
            codedOutputStream.writeDouble(2, ((Double) this.kind_).doubleValue());
        }
        if (this.kindCase_ == 3) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.kind_);
        }
        if (this.kindCase_ == 4) {
            codedOutputStream.writeBool(4, ((Boolean) this.kind_).booleanValue());
        }
        if (this.kindCase_ == 5) {
            codedOutputStream.writeMessage(5, (Struct) this.kind_);
        }
        if (this.kindCase_ == 6) {
            codedOutputStream.writeMessage(6, (ListValue) this.kind_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
