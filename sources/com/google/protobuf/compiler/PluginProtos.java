package com.google.protobuf.compiler;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos.class */
public final class PluginProtos {
    private static Descriptors.FileDescriptor descriptor;

    /* renamed from: internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor */
    private static final Descriptors.Descriptor f2x36d642;

    /* renamed from: internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f3xfc149ac0;

    /* renamed from: internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor */
    private static final Descriptors.Descriptor f4xcaba06d7;

    /* renamed from: internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f5xb43c6655;

    /* renamed from: internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor */
    private static final Descriptors.Descriptor f6x140ff76a;

    /* renamed from: internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f7x23ee13e8;
    private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_Version_descriptor;

    /* renamed from: internal_static_google_protobuf_compiler_Version_fieldAccessorTable */
    private static final GeneratedMessageV3.FieldAccessorTable f8xc9007e77;

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorRequest.class */
    public static final class CodeGeneratorRequest extends GeneratedMessageV3 implements CodeGeneratorRequestOrBuilder {
        public static final int COMPILER_VERSION_FIELD_NUMBER = 3;
        public static final int FILE_TO_GENERATE_FIELD_NUMBER = 1;
        public static final int PARAMETER_FIELD_NUMBER = 2;
        public static final int PROTO_FILE_FIELD_NUMBER = 15;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Version compilerVersion_;
        private LazyStringList fileToGenerate_;
        private byte memoizedIsInitialized;
        private volatile Object parameter_;
        private List<DescriptorProtos.FileDescriptorProto> protoFile_;
        private static final CodeGeneratorRequest DEFAULT_INSTANCE = new CodeGeneratorRequest();

        @Deprecated
        public static final Parser<CodeGeneratorRequest> PARSER = new AbstractParser<CodeGeneratorRequest>() { // from class: com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CodeGeneratorRequest m3411parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CodeGeneratorRequest(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorRequest$Builder.class */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CodeGeneratorRequestOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> compilerVersionBuilder_;
            private Version compilerVersion_;
            private LazyStringList fileToGenerate_;
            private Object parameter_;
            private RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> protoFileBuilder_;
            private List<DescriptorProtos.FileDescriptorProto> protoFile_;

            private Builder() {
                this.fileToGenerate_ = LazyStringArrayList.EMPTY;
                this.parameter_ = "";
                this.protoFile_ = Collections.emptyList();
                this.compilerVersion_ = null;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.fileToGenerate_ = LazyStringArrayList.EMPTY;
                this.parameter_ = "";
                this.protoFile_ = Collections.emptyList();
                this.compilerVersion_ = null;
                maybeForceBuilderInitialization();
            }

            private void ensureFileToGenerateIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.fileToGenerate_ = new LazyStringArrayList(this.fileToGenerate_);
                    this.bitField0_ |= 1;
                }
            }

            private void ensureProtoFileIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.protoFile_ = new ArrayList(this.protoFile_);
                    this.bitField0_ |= 4;
                }
            }

            private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> getCompilerVersionFieldBuilder() {
                if (this.compilerVersionBuilder_ == null) {
                    this.compilerVersionBuilder_ = new SingleFieldBuilderV3<>(getCompilerVersion(), getParentForChildren(), isClean());
                    this.compilerVersion_ = null;
                }
                return this.compilerVersionBuilder_;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return PluginProtos.f2x36d642;
            }

            private RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileFieldBuilder() {
                if (this.protoFileBuilder_ == null) {
                    this.protoFileBuilder_ = new RepeatedFieldBuilderV3<>(this.protoFile_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.protoFile_ = null;
                }
                return this.protoFileBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (CodeGeneratorRequest.alwaysUseFieldBuilders) {
                    getProtoFileFieldBuilder();
                    getCompilerVersionFieldBuilder();
                }
            }

            public Builder addAllFileToGenerate(Iterable<String> iterable) {
                ensureFileToGenerateIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.fileToGenerate_);
                onChanged();
                return this;
            }

            public Builder addAllProtoFile(Iterable<? extends DescriptorProtos.FileDescriptorProto> iterable) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                    return this;
                }
                ensureProtoFileIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.protoFile_);
                onChanged();
                return this;
            }

            public Builder addFileToGenerate(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureFileToGenerateIsMutable();
                this.fileToGenerate_.add(str);
                onChanged();
                return this;
            }

            public Builder addFileToGenerateBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                ensureFileToGenerateIsMutable();
                this.fileToGenerate_.add(byteString);
                onChanged();
                return this;
            }

            public Builder addProtoFile(int i, DescriptorProtos.FileDescriptorProto.Builder builder) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                    return this;
                }
                ensureProtoFileIsMutable();
                this.protoFile_.add(i, builder.build());
                onChanged();
                return this;
            }

            public Builder addProtoFile(int i, DescriptorProtos.FileDescriptorProto fileDescriptorProto) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, fileDescriptorProto);
                    return this;
                }
                if (fileDescriptorProto == null) {
                    throw new NullPointerException();
                }
                ensureProtoFileIsMutable();
                this.protoFile_.add(i, fileDescriptorProto);
                onChanged();
                return this;
            }

            public Builder addProtoFile(DescriptorProtos.FileDescriptorProto.Builder builder) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                    return this;
                }
                ensureProtoFileIsMutable();
                this.protoFile_.add(builder.build());
                onChanged();
                return this;
            }

            public Builder addProtoFile(DescriptorProtos.FileDescriptorProto fileDescriptorProto) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(fileDescriptorProto);
                    return this;
                }
                if (fileDescriptorProto == null) {
                    throw new NullPointerException();
                }
                ensureProtoFileIsMutable();
                this.protoFile_.add(fileDescriptorProto);
                onChanged();
                return this;
            }

            public DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder() {
                return getProtoFileFieldBuilder().addBuilder(DescriptorProtos.FileDescriptorProto.getDefaultInstance());
            }

            public DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder(int i) {
                return getProtoFileFieldBuilder().addBuilder(i, DescriptorProtos.FileDescriptorProto.getDefaultInstance());
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: addRepeatedField */
            public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest, com.google.protobuf.Message] */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CodeGeneratorRequest m3412build() {
                ?? m3414buildPartial = m3414buildPartial();
                if (m3414buildPartial.isInitialized()) {
                    return m3414buildPartial;
                }
                throw newUninitializedMessageException(m3414buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CodeGeneratorRequest m3414buildPartial() {
                CodeGeneratorRequest codeGeneratorRequest = new CodeGeneratorRequest(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) == 1) {
                    this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                codeGeneratorRequest.fileToGenerate_ = this.fileToGenerate_;
                if ((i & 2) != 2) {
                    i2 = 0;
                }
                codeGeneratorRequest.parameter_ = this.parameter_;
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
                        this.bitField0_ &= -5;
                    }
                    codeGeneratorRequest.protoFile_ = this.protoFile_;
                } else {
                    codeGeneratorRequest.protoFile_ = repeatedFieldBuilderV3.build();
                }
                int i3 = i2;
                if ((i & 8) == 8) {
                    i3 = i2 | 2;
                }
                SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    codeGeneratorRequest.compilerVersion_ = this.compilerVersion_;
                } else {
                    codeGeneratorRequest.compilerVersion_ = singleFieldBuilderV3.build();
                }
                codeGeneratorRequest.bitField0_ = i3;
                onBuilt();
                return codeGeneratorRequest;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clear */
            public Builder mo2896clear() {
                super.mo2896clear();
                this.fileToGenerate_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                this.parameter_ = "";
                this.bitField0_ &= -3;
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protoFile_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.compilerVersion_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clearCompilerVersion() {
                SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.compilerVersion_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clearField */
            public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo2897clearField(fieldDescriptor);
            }

            public Builder clearFileToGenerate() {
                this.fileToGenerate_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clearOneof */
            public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo2899clearOneof(oneofDescriptor);
            }

            public Builder clearParameter() {
                this.bitField0_ &= -3;
                this.parameter_ = CodeGeneratorRequest.getDefaultInstance().getParameter();
                onChanged();
                return this;
            }

            public Builder clearProtoFile() {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.clear();
                    return this;
                }
                this.protoFile_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clone */
            public Builder mo2904clone() {
                return (Builder) super.mo2904clone();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public Version getCompilerVersion() {
                SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Version version = this.compilerVersion_;
                Version version2 = version;
                if (version == null) {
                    version2 = Version.getDefaultInstance();
                }
                return version2;
            }

            public Version.Builder getCompilerVersionBuilder() {
                this.bitField0_ |= 8;
                onChanged();
                return getCompilerVersionFieldBuilder().getBuilder();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public VersionOrBuilder getCompilerVersionOrBuilder() {
                SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Version version = this.compilerVersion_;
                Version version2 = version;
                if (version == null) {
                    version2 = Version.getDefaultInstance();
                }
                return version2;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder
            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
            public CodeGeneratorRequest mo3095getDefaultInstanceForType() {
                return CodeGeneratorRequest.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return PluginProtos.f2x36d642;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public String getFileToGenerate(int i) {
                return (String) this.fileToGenerate_.get(i);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public ByteString getFileToGenerateBytes(int i) {
                return this.fileToGenerate_.getByteString(i);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public int getFileToGenerateCount() {
                return this.fileToGenerate_.size();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public ProtocolStringList getFileToGenerateList() {
                return this.fileToGenerate_.getUnmodifiableView();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public String getParameter() {
                Object obj = this.parameter_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.parameter_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public ByteString getParameterBytes() {
                Object obj = this.parameter_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.parameter_ = copyFromUtf8;
                return copyFromUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public DescriptorProtos.FileDescriptorProto getProtoFile(int i) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                return repeatedFieldBuilderV3 == null ? this.protoFile_.get(i) : repeatedFieldBuilderV3.getMessage(i);
            }

            public DescriptorProtos.FileDescriptorProto.Builder getProtoFileBuilder(int i) {
                return getProtoFileFieldBuilder().getBuilder(i);
            }

            public List<DescriptorProtos.FileDescriptorProto.Builder> getProtoFileBuilderList() {
                return getProtoFileFieldBuilder().getBuilderList();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public int getProtoFileCount() {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                return repeatedFieldBuilderV3 == null ? this.protoFile_.size() : repeatedFieldBuilderV3.getCount();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public List<DescriptorProtos.FileDescriptorProto> getProtoFileList() {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.protoFile_) : repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int i) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                return repeatedFieldBuilderV3 == null ? this.protoFile_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.protoFile_);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public boolean hasCompilerVersion() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
            public boolean hasParameter() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PluginProtos.f3xfc149ac0.ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= getProtoFileCount()) {
                        return true;
                    }
                    if (!getProtoFile(i2).isInitialized()) {
                        return false;
                    }
                    i = i2 + 1;
                }
            }

            public Builder mergeCompilerVersion(Version version) {
                Version version2;
                SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) != 8 || (version2 = this.compilerVersion_) == null || version2 == Version.getDefaultInstance()) {
                        this.compilerVersion_ = version;
                    } else {
                        this.compilerVersion_ = Version.newBuilder(this.compilerVersion_).mergeFrom(version).m3462buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(version);
                }
                this.bitField0_ |= 8;
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x0035  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest.Builder m3419mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
                /*
                    r4 = this;
                    r0 = 0
                    r7 = r0
                    com.google.protobuf.Parser<com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest> r0 = com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest.PARSER     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    r1 = r5
                    r2 = r6
                    java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest r0 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    r5 = r0
                    r0 = r5
                    if (r0 == 0) goto L1a
                    r0 = r4
                    r1 = r5
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest$Builder r0 = r0.mergeFrom(r1)
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
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest r0 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest) r0     // Catch: java.lang.Throwable -> L1c
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
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest$Builder r0 = r0.mergeFrom(r1)
                L3b:
                    r0 = r6
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequest.Builder.m3419mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest$Builder");
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3418mergeFrom(Message message) {
                if (message instanceof CodeGeneratorRequest) {
                    return mergeFrom((CodeGeneratorRequest) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CodeGeneratorRequest codeGeneratorRequest) {
                if (codeGeneratorRequest == CodeGeneratorRequest.getDefaultInstance()) {
                    return this;
                }
                if (!codeGeneratorRequest.fileToGenerate_.isEmpty()) {
                    if (this.fileToGenerate_.isEmpty()) {
                        this.fileToGenerate_ = codeGeneratorRequest.fileToGenerate_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFileToGenerateIsMutable();
                        this.fileToGenerate_.addAll(codeGeneratorRequest.fileToGenerate_);
                    }
                    onChanged();
                }
                if (codeGeneratorRequest.hasParameter()) {
                    this.bitField0_ |= 2;
                    this.parameter_ = codeGeneratorRequest.parameter_;
                    onChanged();
                }
                if (this.protoFileBuilder_ == null) {
                    if (!codeGeneratorRequest.protoFile_.isEmpty()) {
                        if (this.protoFile_.isEmpty()) {
                            this.protoFile_ = codeGeneratorRequest.protoFile_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureProtoFileIsMutable();
                            this.protoFile_.addAll(codeGeneratorRequest.protoFile_);
                        }
                        onChanged();
                    }
                } else if (!codeGeneratorRequest.protoFile_.isEmpty()) {
                    if (this.protoFileBuilder_.isEmpty()) {
                        this.protoFileBuilder_.dispose();
                        RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = null;
                        this.protoFileBuilder_ = null;
                        this.protoFile_ = codeGeneratorRequest.protoFile_;
                        this.bitField0_ &= -5;
                        if (CodeGeneratorRequest.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getProtoFileFieldBuilder();
                        }
                        this.protoFileBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.protoFileBuilder_.addAllMessages(codeGeneratorRequest.protoFile_);
                    }
                }
                if (codeGeneratorRequest.hasCompilerVersion()) {
                    mergeCompilerVersion(codeGeneratorRequest.getCompilerVersion());
                }
                mo2912mergeUnknownFields(codeGeneratorRequest.unknownFields);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
            }

            public Builder removeProtoFile(int i) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.remove(i);
                    return this;
                }
                ensureProtoFileIsMutable();
                this.protoFile_.remove(i);
                onChanged();
                return this;
            }

            public Builder setCompilerVersion(Version.Builder builder) {
                SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.compilerVersion_ = builder.m3460build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m3460build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setCompilerVersion(Version version) {
                SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.compilerVersionBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(version);
                } else {
                    if (version == null) {
                        throw new NullPointerException();
                    }
                    this.compilerVersion_ = version;
                    onChanged();
                }
                this.bitField0_ |= 8;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: setField */
            public Builder mo2913setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo2913setField(fieldDescriptor, obj);
            }

            public Builder setFileToGenerate(int i, String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureFileToGenerateIsMutable();
                this.fileToGenerate_.set(i, str);
                onChanged();
                return this;
            }

            public Builder setParameter(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.parameter_ = str;
                onChanged();
                return this;
            }

            public Builder setParameterBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.parameter_ = byteString;
                onChanged();
                return this;
            }

            public Builder setProtoFile(int i, DescriptorProtos.FileDescriptorProto.Builder builder) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                    return this;
                }
                ensureProtoFileIsMutable();
                this.protoFile_.set(i, builder.build());
                onChanged();
                return this;
            }

            public Builder setProtoFile(int i, DescriptorProtos.FileDescriptorProto fileDescriptorProto) {
                RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> repeatedFieldBuilderV3 = this.protoFileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, fileDescriptorProto);
                    return this;
                }
                if (fileDescriptorProto == null) {
                    throw new NullPointerException();
                }
                ensureProtoFileIsMutable();
                this.protoFile_.set(i, fileDescriptorProto);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: setRepeatedField */
            public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo2915setUnknownFields(unknownFieldSet);
            }
        }

        private CodeGeneratorRequest() {
            this.memoizedIsInitialized = (byte) -1;
            this.fileToGenerate_ = LazyStringArrayList.EMPTY;
            this.parameter_ = "";
            this.protoFile_ = Collections.emptyList();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v100 */
        /* JADX WARN: Type inference failed for: r0v102 */
        /* JADX WARN: Type inference failed for: r0v103 */
        /* JADX WARN: Type inference failed for: r0v104 */
        /* JADX WARN: Type inference failed for: r0v106 */
        /* JADX WARN: Type inference failed for: r0v107 */
        /* JADX WARN: Type inference failed for: r0v108 */
        /* JADX WARN: Type inference failed for: r0v111 */
        /* JADX WARN: Type inference failed for: r0v112 */
        /* JADX WARN: Type inference failed for: r0v113 */
        /* JADX WARN: Type inference failed for: r0v114 */
        /* JADX WARN: Type inference failed for: r0v115 */
        /* JADX WARN: Type inference failed for: r0v116 */
        /* JADX WARN: Type inference failed for: r0v119 */
        /* JADX WARN: Type inference failed for: r0v120 */
        /* JADX WARN: Type inference failed for: r0v121 */
        /* JADX WARN: Type inference failed for: r0v122 */
        /* JADX WARN: Type inference failed for: r0v124 */
        /* JADX WARN: Type inference failed for: r0v125 */
        /* JADX WARN: Type inference failed for: r0v126 */
        /* JADX WARN: Type inference failed for: r0v127 */
        /* JADX WARN: Type inference failed for: r0v128 */
        /* JADX WARN: Type inference failed for: r0v129 */
        /* JADX WARN: Type inference failed for: r0v130 */
        /* JADX WARN: Type inference failed for: r0v131 */
        /* JADX WARN: Type inference failed for: r0v15 */
        /* JADX WARN: Type inference failed for: r0v16 */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v25 */
        /* JADX WARN: Type inference failed for: r0v27 */
        /* JADX WARN: Type inference failed for: r0v41 */
        /* JADX WARN: Type inference failed for: r0v42 */
        /* JADX WARN: Type inference failed for: r0v43 */
        /* JADX WARN: Type inference failed for: r0v46 */
        /* JADX WARN: Type inference failed for: r0v47 */
        /* JADX WARN: Type inference failed for: r0v48 */
        /* JADX WARN: Type inference failed for: r0v49 */
        /* JADX WARN: Type inference failed for: r0v50 */
        /* JADX WARN: Type inference failed for: r0v51 */
        /* JADX WARN: Type inference failed for: r0v55 */
        /* JADX WARN: Type inference failed for: r0v56 */
        /* JADX WARN: Type inference failed for: r0v57 */
        /* JADX WARN: Type inference failed for: r0v58 */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v60 */
        /* JADX WARN: Type inference failed for: r0v61 */
        /* JADX WARN: Type inference failed for: r0v63 */
        /* JADX WARN: Type inference failed for: r0v64 */
        /* JADX WARN: Type inference failed for: r0v65 */
        /* JADX WARN: Type inference failed for: r0v69 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v70 */
        /* JADX WARN: Type inference failed for: r0v71 */
        /* JADX WARN: Type inference failed for: r0v74 */
        /* JADX WARN: Type inference failed for: r0v75 */
        /* JADX WARN: Type inference failed for: r0v76 */
        /* JADX WARN: Type inference failed for: r0v78 */
        /* JADX WARN: Type inference failed for: r0v79 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v80 */
        /* JADX WARN: Type inference failed for: r0v83 */
        /* JADX WARN: Type inference failed for: r0v84 */
        /* JADX WARN: Type inference failed for: r0v85 */
        /* JADX WARN: Type inference failed for: r0v87 */
        /* JADX WARN: Type inference failed for: r0v88 */
        /* JADX WARN: Type inference failed for: r0v89 */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference failed for: r0v93 */
        /* JADX WARN: Type inference failed for: r0v94 */
        /* JADX WARN: Type inference failed for: r0v95 */
        /* JADX WARN: Type inference failed for: r0v98 */
        /* JADX WARN: Type inference failed for: r0v99 */
        /* JADX WARN: Type inference failed for: r11v1 */
        /* JADX WARN: Type inference failed for: r11v2 */
        /* JADX WARN: Type inference failed for: r11v3 */
        /* JADX WARN: Type inference failed for: r11v4 */
        /* JADX WARN: Type inference failed for: r11v5 */
        /* JADX WARN: Type inference failed for: r11v6 */
        /* JADX WARN: Type inference failed for: r14v0 */
        /* JADX WARN: Type inference failed for: r14v1 */
        /* JADX WARN: Type inference failed for: r14v2 */
        /* JADX WARN: Type inference failed for: r14v3 */
        /* JADX WARN: Type inference failed for: r1v42, types: [com.google.protobuf.MessageLite] */
        /* JADX WARN: Type inference failed for: r6v0, types: [com.google.protobuf.compiler.PluginProtos$CodeGeneratorRequest, com.google.protobuf.MessageLite] */
        /* JADX WARN: Type inference failed for: r9v0 */
        /* JADX WARN: Type inference failed for: r9v1 */
        /* JADX WARN: Type inference failed for: r9v2 */
        /* JADX WARN: Type inference failed for: r9v3 */
        /* JADX WARN: Type inference failed for: r9v4 */
        private CodeGeneratorRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw new NullPointerException();
            }
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            ?? r14 = false;
            ?? r9 = false;
            while (r14 == false) {
                ?? r0 = r9;
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                ByteString readBytes = codedInputStream.readBytes();
                                ?? r11 = r9;
                                if ((r9 & true) != true) {
                                    this.fileToGenerate_ = new LazyStringArrayList();
                                    r11 = r9 | true;
                                }
                                this.fileToGenerate_.add(readBytes);
                                r9 = r11;
                            } else if (readTag == 18) {
                                ByteString readBytes2 = codedInputStream.readBytes();
                                ?? r02 = r9;
                                this.bitField0_ |= 1;
                                ?? r03 = r9;
                                this.parameter_ = readBytes2;
                            } else if (readTag == 26) {
                                Version.Builder builder = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    ?? r04 = r9;
                                    builder = this.compilerVersion_.m3457toBuilder();
                                }
                                ?? r05 = r9;
                                this.compilerVersion_ = (Version) codedInputStream.readMessage(Version.PARSER, extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.compilerVersion_);
                                    ?? r06 = r9;
                                    this.compilerVersion_ = builder.m3462buildPartial();
                                }
                                ?? r07 = r9;
                                this.bitField0_ |= 2;
                            } else if (readTag == 122) {
                                ?? r112 = r9;
                                if (((r9 == true ? 1 : 0) & 4) != 4) {
                                    this.protoFile_ = new ArrayList();
                                    r112 = ((r9 == true ? 1 : 0) | 4) == true ? 1 : 0;
                                }
                                this.protoFile_.add(codedInputStream.readMessage(DescriptorProtos.FileDescriptorProto.PARSER, extensionRegistryLite));
                                r9 = r112;
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        r14 = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } catch (Throwable th) {
                    if (((r0 == true ? 1 : 0) & 1) == 1) {
                        this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();
                    }
                    if (((r0 == true ? 1 : 0) & 4) == 4) {
                        this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if ((r9 & true) == true) {
                this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();
            }
            if (((r9 == true ? 1 : 0) & 4) == 4) {
                this.protoFile_ = Collections.unmodifiableList(this.protoFile_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private CodeGeneratorRequest(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static CodeGeneratorRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return PluginProtos.f2x36d642;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m3409toBuilder();
        }

        public static Builder newBuilder(CodeGeneratorRequest codeGeneratorRequest) {
            return DEFAULT_INSTANCE.m3409toBuilder().mergeFrom(codeGeneratorRequest);
        }

        public static CodeGeneratorRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CodeGeneratorRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CodeGeneratorRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CodeGeneratorRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(InputStream inputStream) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CodeGeneratorRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CodeGeneratorRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CodeGeneratorRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CodeGeneratorRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Parser<CodeGeneratorRequest> parser() {
            return PARSER;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CodeGeneratorRequest)) {
                return super.equals(obj);
            }
            CodeGeneratorRequest codeGeneratorRequest = (CodeGeneratorRequest) obj;
            boolean z = (getFileToGenerateList().equals(codeGeneratorRequest.getFileToGenerateList())) && hasParameter() == codeGeneratorRequest.hasParameter();
            boolean z2 = z;
            if (hasParameter()) {
                z2 = z && getParameter().equals(codeGeneratorRequest.getParameter());
            }
            boolean z3 = (z2 && getProtoFileList().equals(codeGeneratorRequest.getProtoFileList())) && hasCompilerVersion() == codeGeneratorRequest.hasCompilerVersion();
            boolean z4 = z3;
            if (hasCompilerVersion()) {
                z4 = z3 && getCompilerVersion().equals(codeGeneratorRequest.getCompilerVersion());
            }
            return z4 && this.unknownFields.equals(codeGeneratorRequest.unknownFields);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public Version getCompilerVersion() {
            Version version = this.compilerVersion_;
            Version version2 = version;
            if (version == null) {
                version2 = Version.getDefaultInstance();
            }
            return version2;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public VersionOrBuilder getCompilerVersionOrBuilder() {
            Version version = this.compilerVersion_;
            Version version2 = version;
            if (version == null) {
                version2 = Version.getDefaultInstance();
            }
            return version2;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
        public CodeGeneratorRequest mo3095getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public String getFileToGenerate(int i) {
            return (String) this.fileToGenerate_.get(i);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public ByteString getFileToGenerateBytes(int i) {
            return this.fileToGenerate_.getByteString(i);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public int getFileToGenerateCount() {
            return this.fileToGenerate_.size();
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public ProtocolStringList getFileToGenerateList() {
            return this.fileToGenerate_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public String getParameter() {
            Object obj = this.parameter_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.parameter_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public ByteString getParameterBytes() {
            Object obj = this.parameter_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.parameter_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Parser<CodeGeneratorRequest> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public DescriptorProtos.FileDescriptorProto getProtoFile(int i) {
            return this.protoFile_.get(i);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public int getProtoFileCount() {
            return this.protoFile_.size();
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public List<DescriptorProtos.FileDescriptorProto> getProtoFileList() {
            return this.protoFile_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int i) {
            return this.protoFile_.get(i);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
            return this.protoFile_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.fileToGenerate_.size(); i3++) {
                i2 += computeStringSizeNoTag(this.fileToGenerate_.getRaw(i3));
            }
            int size = i2 + 0 + (getFileToGenerateList().size() * 1);
            int i4 = size;
            if ((this.bitField0_ & 1) == 1) {
                i4 = size + GeneratedMessageV3.computeStringSize(2, this.parameter_);
            }
            int i5 = 0;
            int i6 = i4;
            if ((this.bitField0_ & 2) == 2) {
                i6 = i4 + CodedOutputStream.computeMessageSize(3, getCompilerVersion());
                i5 = 0;
            }
            while (i5 < this.protoFile_.size()) {
                i6 += CodedOutputStream.computeMessageSize(15, this.protoFile_.get(i5));
                i5++;
            }
            int serializedSize = i6 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public boolean hasCompilerVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorRequestOrBuilder
        public boolean hasParameter() {
            return (this.bitField0_ & 1) == 1;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            int i = hashCode;
            if (getFileToGenerateCount() > 0) {
                i = (((hashCode * 37) + 1) * 53) + getFileToGenerateList().hashCode();
            }
            int i2 = i;
            if (hasParameter()) {
                i2 = (((i * 37) + 2) * 53) + getParameter().hashCode();
            }
            int i3 = i2;
            if (getProtoFileCount() > 0) {
                i3 = (((i2 * 37) + 15) * 53) + getProtoFileList().hashCode();
            }
            int i4 = i3;
            if (hasCompilerVersion()) {
                i4 = (((i3 * 37) + 3) * 53) + getCompilerVersion().hashCode();
            }
            int hashCode2 = (i4 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.f3xfc149ac0.ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
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
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= getProtoFileCount()) {
                    this.memoizedIsInitialized = (byte) 1;
                    return true;
                }
                if (!getProtoFile(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                i = i2 + 1;
            }
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3407newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3409toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.fileToGenerate_.size()) {
                    break;
                }
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.fileToGenerate_.getRaw(i2));
                i = i2 + 1;
            }
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.parameter_);
            }
            int i3 = 0;
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(3, getCompilerVersion());
                i3 = 0;
            }
            while (i3 < this.protoFile_.size()) {
                codedOutputStream.writeMessage(15, this.protoFile_.get(i3));
                i3++;
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorRequestOrBuilder.class */
    public interface CodeGeneratorRequestOrBuilder extends MessageOrBuilder {
        Version getCompilerVersion();

        VersionOrBuilder getCompilerVersionOrBuilder();

        String getFileToGenerate(int i);

        ByteString getFileToGenerateBytes(int i);

        int getFileToGenerateCount();

        List<String> getFileToGenerateList();

        String getParameter();

        ByteString getParameterBytes();

        DescriptorProtos.FileDescriptorProto getProtoFile(int i);

        int getProtoFileCount();

        List<DescriptorProtos.FileDescriptorProto> getProtoFileList();

        DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int i);

        List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList();

        boolean hasCompilerVersion();

        boolean hasParameter();
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorResponse.class */
    public static final class CodeGeneratorResponse extends GeneratedMessageV3 implements CodeGeneratorResponseOrBuilder {
        public static final int ERROR_FIELD_NUMBER = 1;
        public static final int FILE_FIELD_NUMBER = 15;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private volatile Object error_;
        private List<File> file_;
        private byte memoizedIsInitialized;
        private static final CodeGeneratorResponse DEFAULT_INSTANCE = new CodeGeneratorResponse();

        @Deprecated
        public static final Parser<CodeGeneratorResponse> PARSER = new AbstractParser<CodeGeneratorResponse>() { // from class: com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public CodeGeneratorResponse m3427parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CodeGeneratorResponse(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorResponse$Builder.class */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CodeGeneratorResponseOrBuilder {
            private int bitField0_;
            private Object error_;
            private RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> fileBuilder_;
            private List<File> file_;

            private Builder() {
                this.error_ = "";
                this.file_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.error_ = "";
                this.file_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void ensureFileIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.file_ = new ArrayList(this.file_);
                    this.bitField0_ |= 2;
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return PluginProtos.f6x140ff76a;
            }

            private RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> getFileFieldBuilder() {
                if (this.fileBuilder_ == null) {
                    this.fileBuilder_ = new RepeatedFieldBuilderV3<>(this.file_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.file_ = null;
                }
                return this.fileBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (CodeGeneratorResponse.alwaysUseFieldBuilders) {
                    getFileFieldBuilder();
                }
            }

            public Builder addAllFile(Iterable<? extends File> iterable) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                    return this;
                }
                ensureFileIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.file_);
                onChanged();
                return this;
            }

            public Builder addFile(int i, File.Builder builder) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, builder.m3444build());
                    return this;
                }
                ensureFileIsMutable();
                this.file_.add(i, builder.m3444build());
                onChanged();
                return this;
            }

            public Builder addFile(int i, File file) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, file);
                    return this;
                }
                if (file == null) {
                    throw new NullPointerException();
                }
                ensureFileIsMutable();
                this.file_.add(i, file);
                onChanged();
                return this;
            }

            public Builder addFile(File.Builder builder) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(builder.m3444build());
                    return this;
                }
                ensureFileIsMutable();
                this.file_.add(builder.m3444build());
                onChanged();
                return this;
            }

            public Builder addFile(File file) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(file);
                    return this;
                }
                if (file == null) {
                    throw new NullPointerException();
                }
                ensureFileIsMutable();
                this.file_.add(file);
                onChanged();
                return this;
            }

            public File.Builder addFileBuilder() {
                return getFileFieldBuilder().addBuilder(File.getDefaultInstance());
            }

            public File.Builder addFileBuilder(int i) {
                return getFileFieldBuilder().addBuilder(i, File.getDefaultInstance());
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: addRepeatedField */
            public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.Message, com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse] */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CodeGeneratorResponse m3428build() {
                ?? m3430buildPartial = m3430buildPartial();
                if (m3430buildPartial.isInitialized()) {
                    return m3430buildPartial;
                }
                throw newUninitializedMessageException(m3430buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CodeGeneratorResponse m3430buildPartial() {
                CodeGeneratorResponse codeGeneratorResponse = new CodeGeneratorResponse(this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                codeGeneratorResponse.error_ = this.error_;
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.file_ = Collections.unmodifiableList(this.file_);
                        this.bitField0_ &= -3;
                    }
                    codeGeneratorResponse.file_ = this.file_;
                } else {
                    codeGeneratorResponse.file_ = repeatedFieldBuilderV3.build();
                }
                codeGeneratorResponse.bitField0_ = i;
                onBuilt();
                return codeGeneratorResponse;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clear */
            public Builder mo2896clear() {
                super.mo2896clear();
                this.error_ = "";
                this.bitField0_ &= -2;
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.clear();
                    return this;
                }
                this.file_ = Collections.emptyList();
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clearError() {
                this.bitField0_ &= -2;
                this.error_ = CodeGeneratorResponse.getDefaultInstance().getError();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clearField */
            public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo2897clearField(fieldDescriptor);
            }

            public Builder clearFile() {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.clear();
                    return this;
                }
                this.file_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clearOneof */
            public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo2899clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clone */
            public Builder mo2904clone() {
                return (Builder) super.mo2904clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder
            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
            public CodeGeneratorResponse mo3095getDefaultInstanceForType() {
                return CodeGeneratorResponse.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return PluginProtos.f6x140ff76a;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public String getError() {
                Object obj = this.error_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.error_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public ByteString getErrorBytes() {
                Object obj = this.error_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.error_ = copyFromUtf8;
                return copyFromUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public File getFile(int i) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                return repeatedFieldBuilderV3 == null ? this.file_.get(i) : repeatedFieldBuilderV3.getMessage(i);
            }

            public File.Builder getFileBuilder(int i) {
                return getFileFieldBuilder().getBuilder(i);
            }

            public List<File.Builder> getFileBuilderList() {
                return getFileFieldBuilder().getBuilderList();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public int getFileCount() {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                return repeatedFieldBuilderV3 == null ? this.file_.size() : repeatedFieldBuilderV3.getCount();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public List<File> getFileList() {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.file_) : repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public FileOrBuilder getFileOrBuilder(int i) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                return repeatedFieldBuilderV3 == null ? this.file_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public List<? extends FileOrBuilder> getFileOrBuilderList() {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.file_);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
            public boolean hasError() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PluginProtos.f7x23ee13e8.ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
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
            public com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.Builder m3435mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
                /*
                    r4 = this;
                    r0 = 0
                    r7 = r0
                    com.google.protobuf.Parser<com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse> r0 = com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.PARSER     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    r1 = r5
                    r2 = r6
                    java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse r0 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    r5 = r0
                    r0 = r5
                    if (r0 == 0) goto L1a
                    r0 = r4
                    r1 = r5
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$Builder r0 = r0.mergeFrom(r1)
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
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse r0 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse) r0     // Catch: java.lang.Throwable -> L1c
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
                    com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$Builder r0 = r0.mergeFrom(r1)
                L3b:
                    r0 = r6
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.Builder.m3435mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$Builder");
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3434mergeFrom(Message message) {
                if (message instanceof CodeGeneratorResponse) {
                    return mergeFrom((CodeGeneratorResponse) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CodeGeneratorResponse codeGeneratorResponse) {
                if (codeGeneratorResponse == CodeGeneratorResponse.getDefaultInstance()) {
                    return this;
                }
                if (codeGeneratorResponse.hasError()) {
                    this.bitField0_ |= 1;
                    this.error_ = codeGeneratorResponse.error_;
                    onChanged();
                }
                if (this.fileBuilder_ == null) {
                    if (!codeGeneratorResponse.file_.isEmpty()) {
                        if (this.file_.isEmpty()) {
                            this.file_ = codeGeneratorResponse.file_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureFileIsMutable();
                            this.file_.addAll(codeGeneratorResponse.file_);
                        }
                        onChanged();
                    }
                } else if (!codeGeneratorResponse.file_.isEmpty()) {
                    if (this.fileBuilder_.isEmpty()) {
                        this.fileBuilder_.dispose();
                        RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = null;
                        this.fileBuilder_ = null;
                        this.file_ = codeGeneratorResponse.file_;
                        this.bitField0_ &= -3;
                        if (CodeGeneratorResponse.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getFileFieldBuilder();
                        }
                        this.fileBuilder_ = repeatedFieldBuilderV3;
                    } else {
                        this.fileBuilder_.addAllMessages(codeGeneratorResponse.file_);
                    }
                }
                mo2912mergeUnknownFields(codeGeneratorResponse.unknownFields);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: mergeUnknownFields */
            public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
            }

            public Builder removeFile(int i) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.remove(i);
                    return this;
                }
                ensureFileIsMutable();
                this.file_.remove(i);
                onChanged();
                return this;
            }

            public Builder setError(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.error_ = str;
                onChanged();
                return this;
            }

            public Builder setErrorBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.error_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: setField */
            public Builder mo2913setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo2913setField(fieldDescriptor, obj);
            }

            public Builder setFile(int i, File.Builder builder) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, builder.m3444build());
                    return this;
                }
                ensureFileIsMutable();
                this.file_.set(i, builder.m3444build());
                onChanged();
                return this;
            }

            public Builder setFile(int i, File file) {
                RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> repeatedFieldBuilderV3 = this.fileBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, file);
                    return this;
                }
                if (file == null) {
                    throw new NullPointerException();
                }
                ensureFileIsMutable();
                this.file_.set(i, file);
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: setRepeatedField */
            public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo2915setUnknownFields(unknownFieldSet);
            }
        }

        /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorResponse$File.class */
        public static final class File extends GeneratedMessageV3 implements FileOrBuilder {
            public static final int CONTENT_FIELD_NUMBER = 15;
            public static final int INSERTION_POINT_FIELD_NUMBER = 2;
            public static final int NAME_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private volatile Object content_;
            private volatile Object insertionPoint_;
            private byte memoizedIsInitialized;
            private volatile Object name_;
            private static final File DEFAULT_INSTANCE = new File();

            @Deprecated
            public static final Parser<File> PARSER = new AbstractParser<File>() { // from class: com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public File m3443parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new File(codedInputStream, extensionRegistryLite);
                }
            };

            /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorResponse$File$Builder.class */
            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FileOrBuilder {
                private int bitField0_;
                private Object content_;
                private Object insertionPoint_;
                private Object name_;

                private Builder() {
                    this.name_ = "";
                    this.insertionPoint_ = "";
                    this.content_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.name_ = "";
                    this.insertionPoint_ = "";
                    this.content_ = "";
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return PluginProtos.f4xcaba06d7;
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = File.alwaysUseFieldBuilders;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: addRepeatedField */
                public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
                }

                /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File, com.google.protobuf.Message] */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public File m3444build() {
                    ?? m3446buildPartial = m3446buildPartial();
                    if (m3446buildPartial.isInitialized()) {
                        return m3446buildPartial;
                    }
                    throw newUninitializedMessageException(m3446buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public File m3446buildPartial() {
                    File file = new File(this);
                    int i = this.bitField0_;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    file.name_ = this.name_;
                    int i3 = i2;
                    if ((i & 2) == 2) {
                        i3 = i2 | 2;
                    }
                    file.insertionPoint_ = this.insertionPoint_;
                    int i4 = i3;
                    if ((i & 4) == 4) {
                        i4 = i3 | 4;
                    }
                    file.content_ = this.content_;
                    file.bitField0_ = i4;
                    onBuilt();
                    return file;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: clear */
                public Builder mo2896clear() {
                    super.mo2896clear();
                    this.name_ = "";
                    this.bitField0_ &= -2;
                    this.insertionPoint_ = "";
                    this.bitField0_ &= -3;
                    this.content_ = "";
                    this.bitField0_ &= -5;
                    return this;
                }

                public Builder clearContent() {
                    this.bitField0_ &= -5;
                    this.content_ = File.getDefaultInstance().getContent();
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: clearField */
                public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.mo2897clearField(fieldDescriptor);
                }

                public Builder clearInsertionPoint() {
                    this.bitField0_ &= -3;
                    this.insertionPoint_ = File.getDefaultInstance().getInsertionPoint();
                    onChanged();
                    return this;
                }

                public Builder clearName() {
                    this.bitField0_ &= -2;
                    this.name_ = File.getDefaultInstance().getName();
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: clearOneof */
                public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.mo2899clearOneof(oneofDescriptor);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: clone */
                public Builder mo2904clone() {
                    return (Builder) super.mo2904clone();
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public String getContent() {
                    Object obj = this.content_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.content_ = stringUtf8;
                    }
                    return stringUtf8;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public ByteString getContentBytes() {
                    Object obj = this.content_;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.content_ = copyFromUtf8;
                    return copyFromUtf8;
                }

                @Override // com.google.protobuf.MessageLiteOrBuilder
                /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
                public File mo3095getDefaultInstanceForType() {
                    return File.getDefaultInstance();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
                public Descriptors.Descriptor getDescriptorForType() {
                    return PluginProtos.f4xcaba06d7;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public String getInsertionPoint() {
                    Object obj = this.insertionPoint_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.insertionPoint_ = stringUtf8;
                    }
                    return stringUtf8;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public ByteString getInsertionPointBytes() {
                    Object obj = this.insertionPoint_;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.insertionPoint_ = copyFromUtf8;
                    return copyFromUtf8;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public String getName() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.name_ = stringUtf8;
                    }
                    return stringUtf8;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public ByteString getNameBytes() {
                    Object obj = this.name_;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = copyFromUtf8;
                    return copyFromUtf8;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public boolean hasContent() {
                    return (this.bitField0_ & 4) == 4;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public boolean hasInsertionPoint() {
                    return (this.bitField0_ & 2) == 2;
                }

                @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
                public boolean hasName() {
                    return (this.bitField0_ & 1) == 1;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return PluginProtos.f5xb43c6655.ensureFieldAccessorsInitialized(File.class, Builder.class);
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
                public com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File.Builder m3451mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
                    /*
                        r4 = this;
                        r0 = 0
                        r7 = r0
                        com.google.protobuf.Parser<com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File> r0 = com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File.PARSER     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                        r1 = r5
                        r2 = r6
                        java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                        com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File r0 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                        r5 = r0
                        r0 = r5
                        if (r0 == 0) goto L1a
                        r0 = r4
                        r1 = r5
                        com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File$Builder r0 = r0.mergeFrom(r1)
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
                        com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File r0 = (com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File) r0     // Catch: java.lang.Throwable -> L1c
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
                        com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File$Builder r0 = r0.mergeFrom(r1)
                    L3b:
                        r0 = r6
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File.Builder.m3451mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.compiler.PluginProtos$CodeGeneratorResponse$File$Builder");
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m3450mergeFrom(Message message) {
                    if (message instanceof File) {
                        return mergeFrom((File) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(File file) {
                    if (file == File.getDefaultInstance()) {
                        return this;
                    }
                    if (file.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = file.name_;
                        onChanged();
                    }
                    if (file.hasInsertionPoint()) {
                        this.bitField0_ |= 2;
                        this.insertionPoint_ = file.insertionPoint_;
                        onChanged();
                    }
                    if (file.hasContent()) {
                        this.bitField0_ |= 4;
                        this.content_ = file.content_;
                        onChanged();
                    }
                    mo2912mergeUnknownFields(file.unknownFields);
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: mergeUnknownFields */
                public final Builder mo2912mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mo2912mergeUnknownFields(unknownFieldSet);
                }

                public Builder setContent(String str) {
                    if (str == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 4;
                    this.content_ = str;
                    onChanged();
                    return this;
                }

                public Builder setContentBytes(ByteString byteString) {
                    if (byteString == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 4;
                    this.content_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: setField */
                public Builder mo2913setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.mo2913setField(fieldDescriptor, obj);
                }

                public Builder setInsertionPoint(String str) {
                    if (str == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 2;
                    this.insertionPoint_ = str;
                    onChanged();
                    return this;
                }

                public Builder setInsertionPointBytes(ByteString byteString) {
                    if (byteString == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 2;
                    this.insertionPoint_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder setName(String str) {
                    if (str == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.name_ = str;
                    onChanged();
                    return this;
                }

                public Builder setNameBytes(ByteString byteString) {
                    if (byteString == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.name_ = byteString;
                    onChanged();
                    return this;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: setRepeatedField */
                public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.Builder
                /* renamed from: setUnknownFields */
                public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mo2915setUnknownFields(unknownFieldSet);
                }
            }

            private File() {
                this.memoizedIsInitialized = (byte) -1;
                this.name_ = "";
                this.insertionPoint_ = "";
                this.content_ = "";
            }

            /* JADX WARN: Multi-variable type inference failed */
            private File(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 10) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.name_ = readBytes;
                                } else if (readTag == 18) {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ |= 2;
                                    this.insertionPoint_ = readBytes2;
                                } else if (readTag == 122) {
                                    ByteString readBytes3 = codedInputStream.readBytes();
                                    this.bitField0_ |= 4;
                                    this.content_ = readBytes3;
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
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

            private File(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            public static File getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return PluginProtos.f4xcaba06d7;
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m3441toBuilder();
            }

            public static Builder newBuilder(File file) {
                return DEFAULT_INSTANCE.m3441toBuilder().mergeFrom(file);
            }

            public static File parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (File) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static File parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (File) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static File parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString);
            }

            public static File parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static File parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (File) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static File parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (File) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static File parseFrom(InputStream inputStream) throws IOException {
                return (File) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static File parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (File) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static File parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer);
            }

            public static File parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static File parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr);
            }

            public static File parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Parser<File> parser() {
                return PARSER;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof File)) {
                    return super.equals(obj);
                }
                File file = (File) obj;
                boolean z = hasName() == file.hasName();
                boolean z2 = z;
                if (hasName()) {
                    z2 = z && getName().equals(file.getName());
                }
                boolean z3 = z2 && hasInsertionPoint() == file.hasInsertionPoint();
                boolean z4 = z3;
                if (hasInsertionPoint()) {
                    z4 = z3 && getInsertionPoint().equals(file.getInsertionPoint());
                }
                boolean z5 = z4 && hasContent() == file.hasContent();
                boolean z6 = z5;
                if (hasContent()) {
                    z6 = z5 && getContent().equals(file.getContent());
                }
                return z6 && this.unknownFields.equals(file.unknownFields);
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public String getContent() {
                Object obj = this.content_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.content_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public ByteString getContentBytes() {
                Object obj = this.content_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.content_ = copyFromUtf8;
                return copyFromUtf8;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder
            /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
            public File mo3095getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public String getInsertionPoint() {
                Object obj = this.insertionPoint_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.insertionPoint_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public ByteString getInsertionPointBytes() {
                Object obj = this.insertionPoint_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.insertionPoint_ = copyFromUtf8;
                return copyFromUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
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
            public Parser<File> getParserForType() {
                return PARSER;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
                }
                int i3 = i2;
                if ((this.bitField0_ & 2) == 2) {
                    i3 = i2 + GeneratedMessageV3.computeStringSize(2, this.insertionPoint_);
                }
                int i4 = i3;
                if ((this.bitField0_ & 4) == 4) {
                    i4 = i3 + GeneratedMessageV3.computeStringSize(15, this.content_);
                }
                int serializedSize = i4 + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public boolean hasContent() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public boolean hasInsertionPoint() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.FileOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int hashCode = 779 + getDescriptor().hashCode();
                int i = hashCode;
                if (hasName()) {
                    i = (((hashCode * 37) + 1) * 53) + getName().hashCode();
                }
                int i2 = i;
                if (hasInsertionPoint()) {
                    i2 = (((i * 37) + 2) * 53) + getInsertionPoint().hashCode();
                }
                int i3 = i2;
                if (hasContent()) {
                    i3 = (((i2 * 37) + 15) * 53) + getContent().hashCode();
                }
                int hashCode2 = (i3 * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PluginProtos.f5xb43c6655.ensureFieldAccessorsInitialized(File.class, Builder.class);
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
            public Builder m3439newBuilderForType() {
                return newBuilder();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.protobuf.GeneratedMessageV3
            /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
            public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3441toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            @Override // com.google.protobuf.GeneratedMessageV3
            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    GeneratedMessageV3.writeString(codedOutputStream, 2, this.insertionPoint_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    GeneratedMessageV3.writeString(codedOutputStream, 15, this.content_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }
        }

        /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorResponse$FileOrBuilder.class */
        public interface FileOrBuilder extends MessageOrBuilder {
            String getContent();

            ByteString getContentBytes();

            String getInsertionPoint();

            ByteString getInsertionPointBytes();

            String getName();

            ByteString getNameBytes();

            boolean hasContent();

            boolean hasInsertionPoint();

            boolean hasName();
        }

        private CodeGeneratorResponse() {
            this.memoizedIsInitialized = (byte) -1;
            this.error_ = "";
            this.file_ = Collections.emptyList();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private CodeGeneratorResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                ByteString readBytes = codedInputStream.readBytes();
                                byte b4 = b2;
                                this.bitField0_ = 1 | this.bitField0_;
                                byte b5 = b2;
                                this.error_ = readBytes;
                            } else if (readTag == 122) {
                                byte b6 = b2;
                                if (((b2 == true ? 1 : 0) & 2) != 2) {
                                    this.file_ = new ArrayList();
                                    b6 = ((b2 == true ? 1 : 0) | 2) == true ? 1 : 0;
                                }
                                this.file_.add(codedInputStream.readMessage(File.PARSER, extensionRegistryLite));
                                b2 = b6;
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        b = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } catch (Throwable th) {
                    if (((b3 == true ? 1 : 0) & 2) == 2) {
                        this.file_ = Collections.unmodifiableList(this.file_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (((b2 == true ? 1 : 0) & 2) == 2) {
                this.file_ = Collections.unmodifiableList(this.file_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private CodeGeneratorResponse(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static CodeGeneratorResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return PluginProtos.f6x140ff76a;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m3425toBuilder();
        }

        public static Builder newBuilder(CodeGeneratorResponse codeGeneratorResponse) {
            return DEFAULT_INSTANCE.m3425toBuilder().mergeFrom(codeGeneratorResponse);
        }

        public static CodeGeneratorResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CodeGeneratorResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CodeGeneratorResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CodeGeneratorResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(InputStream inputStream) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CodeGeneratorResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CodeGeneratorResponse) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CodeGeneratorResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CodeGeneratorResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CodeGeneratorResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Parser<CodeGeneratorResponse> parser() {
            return PARSER;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CodeGeneratorResponse)) {
                return super.equals(obj);
            }
            CodeGeneratorResponse codeGeneratorResponse = (CodeGeneratorResponse) obj;
            boolean z = hasError() == codeGeneratorResponse.hasError();
            boolean z2 = z;
            if (hasError()) {
                z2 = z && getError().equals(codeGeneratorResponse.getError());
            }
            return (z2 && getFileList().equals(codeGeneratorResponse.getFileList())) && this.unknownFields.equals(codeGeneratorResponse.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
        public CodeGeneratorResponse mo3095getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public String getError() {
            Object obj = this.error_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.error_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public ByteString getErrorBytes() {
            Object obj = this.error_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.error_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public File getFile(int i) {
            return this.file_.get(i);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public int getFileCount() {
            return this.file_.size();
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public List<File> getFileList() {
            return this.file_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public FileOrBuilder getFileOrBuilder(int i) {
            return this.file_.get(i);
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public List<? extends FileOrBuilder> getFileOrBuilderList() {
            return this.file_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Parser<CodeGeneratorResponse> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 1) == 1 ? GeneratedMessageV3.computeStringSize(1, this.error_) + 0 : 0;
            for (int i2 = 0; i2 < this.file_.size(); i2++) {
                computeStringSize += CodedOutputStream.computeMessageSize(15, (MessageLite) this.file_.get(i2));
            }
            int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponseOrBuilder
        public boolean hasError() {
            return (this.bitField0_ & 1) == 1;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            int i = hashCode;
            if (hasError()) {
                i = (((hashCode * 37) + 1) * 53) + getError().hashCode();
            }
            int i2 = i;
            if (getFileCount() > 0) {
                i2 = (((i * 37) + 15) * 53) + getFileList().hashCode();
            }
            int hashCode2 = (i2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.f7x23ee13e8.ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
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
        public Builder m3423newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3425toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.error_);
            }
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.file_.size()) {
                    this.unknownFields.writeTo(codedOutputStream);
                    return;
                } else {
                    codedOutputStream.writeMessage(15, (MessageLite) this.file_.get(i2));
                    i = i2 + 1;
                }
            }
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$CodeGeneratorResponseOrBuilder.class */
    public interface CodeGeneratorResponseOrBuilder extends MessageOrBuilder {
        String getError();

        ByteString getErrorBytes();

        CodeGeneratorResponse.File getFile(int i);

        int getFileCount();

        List<CodeGeneratorResponse.File> getFileList();

        CodeGeneratorResponse.FileOrBuilder getFileOrBuilder(int i);

        List<? extends CodeGeneratorResponse.FileOrBuilder> getFileOrBuilderList();

        boolean hasError();
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$Version.class */
    public static final class Version extends GeneratedMessageV3 implements VersionOrBuilder {
        public static final int MAJOR_FIELD_NUMBER = 1;
        public static final int MINOR_FIELD_NUMBER = 2;
        public static final int PATCH_FIELD_NUMBER = 3;
        public static final int SUFFIX_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int major_;
        private byte memoizedIsInitialized;
        private int minor_;
        private int patch_;
        private volatile Object suffix_;
        private static final Version DEFAULT_INSTANCE = new Version();

        @Deprecated
        public static final Parser<Version> PARSER = new AbstractParser<Version>() { // from class: com.google.protobuf.compiler.PluginProtos.Version.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Version m3459parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Version(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$Version$Builder.class */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VersionOrBuilder {
            private int bitField0_;
            private int major_;
            private int minor_;
            private int patch_;
            private Object suffix_;

            private Builder() {
                this.suffix_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.suffix_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Version.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: addRepeatedField */
            public Builder mo2889addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo2889addRepeatedField(fieldDescriptor, obj);
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.Message, com.google.protobuf.compiler.PluginProtos$Version] */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Version m3460build() {
                ?? m3462buildPartial = m3462buildPartial();
                if (m3462buildPartial.isInitialized()) {
                    return m3462buildPartial;
                }
                throw newUninitializedMessageException(m3462buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Version m3462buildPartial() {
                Version version = new Version(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                version.major_ = this.major_;
                int i3 = i2;
                if ((i & 2) == 2) {
                    i3 = i2 | 2;
                }
                version.minor_ = this.minor_;
                int i4 = i3;
                if ((i & 4) == 4) {
                    i4 = i3 | 4;
                }
                version.patch_ = this.patch_;
                int i5 = i4;
                if ((i & 8) == 8) {
                    i5 = i4 | 8;
                }
                version.suffix_ = this.suffix_;
                version.bitField0_ = i5;
                onBuilt();
                return version;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clear */
            public Builder mo2896clear() {
                super.mo2896clear();
                this.major_ = 0;
                this.bitField0_ &= -2;
                this.minor_ = 0;
                this.bitField0_ &= -3;
                this.patch_ = 0;
                this.bitField0_ &= -5;
                this.suffix_ = "";
                this.bitField0_ &= -9;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clearField */
            public Builder mo2897clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo2897clearField(fieldDescriptor);
            }

            public Builder clearMajor() {
                this.bitField0_ &= -2;
                this.major_ = 0;
                onChanged();
                return this;
            }

            public Builder clearMinor() {
                this.bitField0_ &= -3;
                this.minor_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: clearOneof */
            public Builder mo2899clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.mo2899clearOneof(oneofDescriptor);
            }

            public Builder clearPatch() {
                this.bitField0_ &= -5;
                this.patch_ = 0;
                onChanged();
                return this;
            }

            public Builder clearSuffix() {
                this.bitField0_ &= -9;
                this.suffix_ = Version.getDefaultInstance().getSuffix();
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
            public Version mo3095getDefaultInstanceForType() {
                return Version.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public int getMajor() {
                return this.major_;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public int getMinor() {
                return this.minor_;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public int getPatch() {
                return this.patch_;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public String getSuffix() {
                Object obj = this.suffix_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.suffix_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public ByteString getSuffixBytes() {
                Object obj = this.suffix_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.suffix_ = copyFromUtf8;
                return copyFromUtf8;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public boolean hasMajor() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public boolean hasMinor() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public boolean hasPatch() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
            public boolean hasSuffix() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return PluginProtos.f8xc9007e77.ensureFieldAccessorsInitialized(Version.class, Builder.class);
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
            public com.google.protobuf.compiler.PluginProtos.Version.Builder m3467mergeFrom(com.google.protobuf.CodedInputStream r5, com.google.protobuf.ExtensionRegistryLite r6) throws java.io.IOException {
                /*
                    r4 = this;
                    r0 = 0
                    r7 = r0
                    com.google.protobuf.Parser<com.google.protobuf.compiler.PluginProtos$Version> r0 = com.google.protobuf.compiler.PluginProtos.Version.PARSER     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    r1 = r5
                    r2 = r6
                    java.lang.Object r0 = r0.parsePartialFrom(r1, r2)     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    com.google.protobuf.compiler.PluginProtos$Version r0 = (com.google.protobuf.compiler.PluginProtos.Version) r0     // Catch: java.lang.Throwable -> L1c com.google.protobuf.InvalidProtocolBufferException -> L22
                    r5 = r0
                    r0 = r5
                    if (r0 == 0) goto L1a
                    r0 = r4
                    r1 = r5
                    com.google.protobuf.compiler.PluginProtos$Version$Builder r0 = r0.mergeFrom(r1)
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
                    com.google.protobuf.compiler.PluginProtos$Version r0 = (com.google.protobuf.compiler.PluginProtos.Version) r0     // Catch: java.lang.Throwable -> L1c
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
                    com.google.protobuf.compiler.PluginProtos$Version$Builder r0 = r0.mergeFrom(r1)
                L3b:
                    r0 = r6
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.compiler.PluginProtos.Version.Builder.m3467mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.compiler.PluginProtos$Version$Builder");
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m3466mergeFrom(Message message) {
                if (message instanceof Version) {
                    return mergeFrom((Version) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Version version) {
                if (version == Version.getDefaultInstance()) {
                    return this;
                }
                if (version.hasMajor()) {
                    setMajor(version.getMajor());
                }
                if (version.hasMinor()) {
                    setMinor(version.getMinor());
                }
                if (version.hasPatch()) {
                    setPatch(version.getPatch());
                }
                if (version.hasSuffix()) {
                    this.bitField0_ |= 8;
                    this.suffix_ = version.suffix_;
                    onChanged();
                }
                mo2912mergeUnknownFields(version.unknownFields);
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

            public Builder setMajor(int i) {
                this.bitField0_ |= 1;
                this.major_ = i;
                onChanged();
                return this;
            }

            public Builder setMinor(int i) {
                this.bitField0_ |= 2;
                this.minor_ = i;
                onChanged();
                return this;
            }

            public Builder setPatch(int i) {
                this.bitField0_ |= 4;
                this.patch_ = i;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: setRepeatedField */
            public Builder mo2914setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.mo2914setRepeatedField(fieldDescriptor, i, obj);
            }

            public Builder setSuffix(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.suffix_ = str;
                onChanged();
                return this;
            }

            public Builder setSuffixBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.suffix_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            /* renamed from: setUnknownFields */
            public final Builder mo2915setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mo2915setUnknownFields(unknownFieldSet);
            }
        }

        private Version() {
            this.memoizedIsInitialized = (byte) -1;
            this.major_ = 0;
            this.minor_ = 0;
            this.patch_ = 0;
            this.suffix_ = "";
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Version(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.bitField0_ |= 1;
                                    this.major_ = codedInputStream.readInt32();
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 2;
                                    this.minor_ = codedInputStream.readInt32();
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 4;
                                    this.patch_ = codedInputStream.readInt32();
                                } else if (readTag == 34) {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ |= 8;
                                    this.suffix_ = readBytes;
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
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

        private Version(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static Version getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m3457toBuilder();
        }

        public static Builder newBuilder(Version version) {
            return DEFAULT_INSTANCE.m3457toBuilder().mergeFrom(version);
        }

        public static Version parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Version parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Version parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Version parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Version parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Version parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Version parseFrom(InputStream inputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Version parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Version parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Version parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Version parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Version parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Parser<Version> parser() {
            return PARSER;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Version)) {
                return super.equals(obj);
            }
            Version version = (Version) obj;
            boolean z = hasMajor() == version.hasMajor();
            boolean z2 = z;
            if (hasMajor()) {
                z2 = z && getMajor() == version.getMajor();
            }
            boolean z3 = z2 && hasMinor() == version.hasMinor();
            boolean z4 = z3;
            if (hasMinor()) {
                z4 = z3 && getMinor() == version.getMinor();
            }
            boolean z5 = z4 && hasPatch() == version.hasPatch();
            boolean z6 = z5;
            if (hasPatch()) {
                z6 = z5 && getPatch() == version.getPatch();
            }
            boolean z7 = z6 && hasSuffix() == version.hasSuffix();
            boolean z8 = z7;
            if (hasSuffix()) {
                z8 = z7 && getSuffix().equals(version.getSuffix());
            }
            return z8 && this.unknownFields.equals(version.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
        public Version mo3095getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public int getMajor() {
            return this.major_;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public int getMinor() {
            return this.minor_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Parser<Version> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public int getPatch() {
            return this.patch_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.major_);
            }
            int i3 = i2;
            if ((this.bitField0_ & 2) == 2) {
                i3 = i2 + CodedOutputStream.computeInt32Size(2, this.minor_);
            }
            int i4 = i3;
            if ((this.bitField0_ & 4) == 4) {
                i4 = i3 + CodedOutputStream.computeInt32Size(3, this.patch_);
            }
            int i5 = i4;
            if ((this.bitField0_ & 8) == 8) {
                i5 = i4 + GeneratedMessageV3.computeStringSize(4, this.suffix_);
            }
            int serializedSize = i5 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public String getSuffix() {
            Object obj = this.suffix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.suffix_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public ByteString getSuffixBytes() {
            Object obj = this.suffix_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.suffix_ = copyFromUtf8;
            return copyFromUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public boolean hasMajor() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public boolean hasMinor() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public boolean hasPatch() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.protobuf.compiler.PluginProtos.VersionOrBuilder
        public boolean hasSuffix() {
            return (this.bitField0_ & 8) == 8;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = 779 + getDescriptor().hashCode();
            int i = hashCode;
            if (hasMajor()) {
                i = (((hashCode * 37) + 1) * 53) + getMajor();
            }
            int i2 = i;
            if (hasMinor()) {
                i2 = (((i * 37) + 2) * 53) + getMinor();
            }
            int i3 = i2;
            if (hasPatch()) {
                i3 = (((i2 * 37) + 3) * 53) + getPatch();
            }
            int i4 = i3;
            if (hasSuffix()) {
                i4 = (((i3 * 37) + 4) * 53) + getSuffix().hashCode();
            }
            int hashCode2 = (i4 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PluginProtos.f8xc9007e77.ensureFieldAccessorsInitialized(Version.class, Builder.class);
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
        public Builder m3455newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
        public Builder mo2884newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m3457toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.major_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.minor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.patch_);
            }
            if ((this.bitField0_ & 8) == 8) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.suffix_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/compiler/PluginProtos$VersionOrBuilder.class */
    public interface VersionOrBuilder extends MessageOrBuilder {
        int getMajor();

        int getMinor();

        int getPatch();

        String getSuffix();

        ByteString getSuffixBytes();

        boolean hasMajor();

        boolean hasMinor();

        boolean hasPatch();

        boolean hasSuffix();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%google/protobuf/compiler/plugin.proto\u0012\u0018google.protobuf.compiler\u001a google/protobuf/descriptor.proto\"F\n\u0007Version\u0012\r\n\u0005major\u0018\u0001 \u0001(\u0005\u0012\r\n\u0005minor\u0018\u0002 \u0001(\u0005\u0012\r\n\u0005patch\u0018\u0003 \u0001(\u0005\u0012\u000e\n\u0006suffix\u0018\u0004 \u0001(\t\"º\u0001\n\u0014CodeGeneratorRequest\u0012\u0018\n\u0010file_to_generate\u0018\u0001 \u0003(\t\u0012\u0011\n\tparameter\u0018\u0002 \u0001(\t\u00128\n\nproto_file\u0018\u000f \u0003(\u000b2$.google.protobuf.FileDescriptorProto\u0012;\n\u0010compiler_version\u0018\u0003 \u0001(\u000b2!.google.protobuf.compiler.Version\"ª\u0001\n\u0015CodeGeneratorResponse\u0012\r\n\u0005error\u0018\u0001 \u0001(\t\u0012B\n\u0004file\u0018\u000f \u0003(\u000b24.google.protobuf.compiler.CodeGeneratorResponse.File\u001a>\n\u0004File\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0017\n\u000finsertion_point\u0018\u0002 \u0001(\t\u0012\u000f\n\u0007content\u0018\u000f \u0001(\tBg\n\u001ccom.google.protobuf.compilerB\fPluginProtosZ9github.com/golang/protobuf/protoc-gen-go/plugin;plugin_go"}, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.google.protobuf.compiler.PluginProtos.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = PluginProtos.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_google_protobuf_compiler_Version_descriptor = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        f8xc9007e77 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_Version_descriptor, new String[]{"Major", "Minor", "Patch", "Suffix"});
        f2x36d642 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        f3xfc149ac0 = new GeneratedMessageV3.FieldAccessorTable(f2x36d642, new String[]{"FileToGenerate", "Parameter", "ProtoFile", "CompilerVersion"});
        f6x140ff76a = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        f7x23ee13e8 = new GeneratedMessageV3.FieldAccessorTable(f6x140ff76a, new String[]{"Error", "File"});
        f4xcaba06d7 = (Descriptors.Descriptor) f6x140ff76a.getNestedTypes().get(0);
        f5xb43c6655 = new GeneratedMessageV3.FieldAccessorTable(f4xcaba06d7, new String[]{"Name", "InsertionPoint", "Content"});
        DescriptorProtos.getDescriptor();
    }

    private PluginProtos() {
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
