package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.TextFormatParseInfoTree;
import com.google.protobuf.UnknownFieldSet;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat.class */
public final class TextFormat {
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Parser PARSER = Parser.newBuilder().build();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.TextFormat$1 */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$1.class */
    public static /* synthetic */ class C00511 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = new int[Descriptors.FieldDescriptor.Type.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:92:0x010d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                Method dump skipped, instructions count: 291
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.TextFormat.C00511.m3302clinit():void");
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$InvalidEscapeSequenceException.class */
    public static class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$ParseException.class */
    public static class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(int i, int i2, String str) {
            super(Integer.toString(i) + Constants.COLON_SEPARATOR + i2 + ": " + str);
            this.line = i;
            this.column = i2;
        }

        public ParseException(String str) {
            this(-1, -1, str);
        }

        public int getColumn() {
            return this.column;
        }

        public int getLine() {
            return this.line;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$Parser.class */
    public static class Parser {
        private static final int BUFFER_SIZE = 4096;
        private final boolean allowUnknownFields;
        private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
        private final SingularOverwritePolicy singularOverwritePolicy;

        /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$Parser$Builder.class */
        public static class Builder {
            private boolean allowUnknownFields = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
            private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;

            public Parser build() {
                return new Parser(this.allowUnknownFields, this.singularOverwritePolicy, this.parseInfoTreeBuilder, null);
            }

            public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder builder) {
                this.parseInfoTreeBuilder = builder;
                return this;
            }

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy singularOverwritePolicy) {
                this.singularOverwritePolicy = singularOverwritePolicy;
                return this;
            }
        }

        /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$Parser$SingularOverwritePolicy.class */
        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        private Parser(boolean z, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder builder) {
            this.allowUnknownFields = z;
            this.singularOverwritePolicy = singularOverwritePolicy;
            this.parseInfoTreeBuilder = builder;
        }

        /* synthetic */ Parser(boolean z, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder builder, C00511 c00511) {
            this(z, singularOverwritePolicy, builder);
        }

        private void checkUnknownFields(List<String> list) throws ParseException {
            if (list.isEmpty()) {
                return;
            }
            StringBuilder sb = new StringBuilder("Input contains unknown fields and/or extensions:");
            for (String str : list) {
                sb.append('\n');
                sb.append(str);
            }
            if (!this.allowUnknownFields) {
                String[] split = list.get(0).split(Constants.COLON_SEPARATOR);
                throw new ParseException(Integer.valueOf(split[0]).intValue(), Integer.valueOf(split[1]).intValue(), sb.toString());
            }
            TextFormat.logger.warning(sb.toString());
        }

        private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, TextFormatParseInfoTree.Builder builder, List<String> list) throws ParseException {
            Object valueOf;
            String str;
            if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                switch (C00511.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptor.getType().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        valueOf = Integer.valueOf(tokenizer.consumeInt32());
                        break;
                    case 4:
                    case 5:
                    case 6:
                        valueOf = Long.valueOf(tokenizer.consumeInt64());
                        break;
                    case 7:
                        valueOf = Boolean.valueOf(tokenizer.consumeBoolean());
                        break;
                    case 8:
                        valueOf = Float.valueOf(tokenizer.consumeFloat());
                        break;
                    case 9:
                        valueOf = Double.valueOf(tokenizer.consumeDouble());
                        break;
                    case 10:
                    case 11:
                        valueOf = Integer.valueOf(tokenizer.consumeUInt32());
                        break;
                    case 12:
                    case 13:
                        valueOf = Long.valueOf(tokenizer.consumeUInt64());
                        break;
                    case 14:
                        valueOf = tokenizer.consumeString();
                        break;
                    case 15:
                        valueOf = tokenizer.consumeByteString();
                        break;
                    case 16:
                        Descriptors.EnumDescriptor enumType = fieldDescriptor.getEnumType();
                        if (tokenizer.lookingAtInteger()) {
                            int consumeInt32 = tokenizer.consumeInt32();
                            valueOf = enumType.findValueByNumber(consumeInt32);
                            if (valueOf == null) {
                                throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + consumeInt32 + '.');
                            }
                        } else {
                            String consumeIdentifier = tokenizer.consumeIdentifier();
                            valueOf = enumType.findValueByName(consumeIdentifier);
                            if (valueOf == null) {
                                throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value named \"" + consumeIdentifier + "\".");
                            }
                        }
                        break;
                    case 17:
                    case 18:
                        throw new RuntimeException("Can't get here.");
                    default:
                        valueOf = null;
                        break;
                }
            } else {
                if (tokenizer.tryConsume("<")) {
                    str = ">";
                } else {
                    tokenizer.consume("{");
                    str = "}";
                }
                MessageReflection.MergeTarget newMergeTargetForField = mergeTarget.newMergeTargetForField(fieldDescriptor, extensionInfo == null ? null : extensionInfo.defaultInstance);
                while (!tokenizer.tryConsume(str)) {
                    if (tokenizer.atEnd()) {
                        throw tokenizer.parseException("Expected \"" + str + "\".");
                    }
                    mergeField(tokenizer, extensionRegistry, newMergeTargetForField, builder, list);
                }
                valueOf = newMergeTargetForField.finish();
            }
            if (fieldDescriptor.isRepeated()) {
                mergeTarget.addRepeatedField(fieldDescriptor, valueOf);
                return;
            }
            if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && mergeTarget.hasField(fieldDescriptor)) {
                throw tokenizer.parseExceptionPreviousToken("Non-repeated field \"" + fieldDescriptor.getFullName() + "\" cannot be overwritten.");
            }
            if (this.singularOverwritePolicy != SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES || fieldDescriptor.getContainingOneof() == null || !mergeTarget.hasOneof(fieldDescriptor.getContainingOneof())) {
                mergeTarget.setField(fieldDescriptor, valueOf);
                return;
            }
            Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            throw tokenizer.parseExceptionPreviousToken("Field \"" + fieldDescriptor.getFullName() + "\" is specified along with field \"" + mergeTarget.getOneofFieldDescriptor(containingOneof).getFullName() + "\", another member of oneof \"" + containingOneof.getName() + "\".");
        }

        private void consumeFieldValues(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, TextFormatParseInfoTree.Builder builder, List<String> list) throws ParseException {
            if (!fieldDescriptor.isRepeated() || !tokenizer.tryConsume("[")) {
                consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
            } else {
                if (tokenizer.tryConsume("]")) {
                    return;
                }
                while (true) {
                    consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
                    if (tokenizer.tryConsume("]")) {
                        return;
                    } else {
                        tokenizer.consume(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    }
                }
            }
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, TextFormatParseInfoTree.Builder builder, List<String> list) throws ParseException {
            ExtensionRegistry.ExtensionInfo extensionInfo;
            int line = tokenizer.getLine();
            int column = tokenizer.getColumn();
            Descriptors.Descriptor descriptorForType = mergeTarget.getDescriptorForType();
            Descriptors.FieldDescriptor fieldDescriptor = null;
            if (tokenizer.tryConsume("[")) {
                StringBuilder sb = new StringBuilder(tokenizer.consumeIdentifier());
                while (tokenizer.tryConsume(".")) {
                    sb.append('.');
                    sb.append(tokenizer.consumeIdentifier());
                }
                ExtensionRegistry.ExtensionInfo findExtensionByName = mergeTarget.findExtensionByName(extensionRegistry, sb.toString());
                if (findExtensionByName == null) {
                    list.add((tokenizer.getPreviousLine() + 1) + Constants.COLON_SEPARATOR + (tokenizer.getPreviousColumn() + 1) + ":\t" + descriptorForType.getFullName() + ".[" + ((Object) sb) + "]");
                } else {
                    if (findExtensionByName.descriptor.getContainingType() != descriptorForType) {
                        throw tokenizer.parseExceptionPreviousToken("Extension \"" + ((Object) sb) + "\" does not extend message type \"" + descriptorForType.getFullName() + "\".");
                    }
                    fieldDescriptor = findExtensionByName.descriptor;
                }
                tokenizer.consume("]");
                extensionInfo = findExtensionByName;
            } else {
                String consumeIdentifier = tokenizer.consumeIdentifier();
                Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName(consumeIdentifier);
                Descriptors.FieldDescriptor fieldDescriptor2 = findFieldByName;
                if (findFieldByName == null) {
                    Descriptors.FieldDescriptor findFieldByName2 = descriptorForType.findFieldByName(consumeIdentifier.toLowerCase(Locale.US));
                    fieldDescriptor2 = findFieldByName2;
                    if (findFieldByName2 != null) {
                        fieldDescriptor2 = findFieldByName2;
                        if (findFieldByName2.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                            fieldDescriptor2 = null;
                        }
                    }
                }
                Descriptors.FieldDescriptor fieldDescriptor3 = fieldDescriptor2;
                if (fieldDescriptor2 != null) {
                    fieldDescriptor3 = fieldDescriptor2;
                    if (fieldDescriptor2.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                        fieldDescriptor3 = fieldDescriptor2;
                        if (!fieldDescriptor2.getMessageType().getName().equals(consumeIdentifier)) {
                            fieldDescriptor3 = null;
                        }
                    }
                }
                if (fieldDescriptor3 == null) {
                    list.add((tokenizer.getPreviousLine() + 1) + Constants.COLON_SEPARATOR + (tokenizer.getPreviousColumn() + 1) + ":\t" + descriptorForType.getFullName() + "." + consumeIdentifier);
                }
                extensionInfo = null;
                fieldDescriptor = fieldDescriptor3;
            }
            if (fieldDescriptor == null) {
                if (!tokenizer.tryConsume(Constants.COLON_SEPARATOR) || tokenizer.lookingAt("{") || tokenizer.lookingAt("<")) {
                    skipFieldMessage(tokenizer);
                    return;
                } else {
                    skipFieldValue(tokenizer);
                    return;
                }
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                tokenizer.tryConsume(Constants.COLON_SEPARATOR);
                if (builder != null) {
                    consumeFieldValues(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder.getBuilderForSubMessageField(fieldDescriptor), list);
                } else {
                    consumeFieldValues(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
                }
            } else {
                tokenizer.consume(Constants.COLON_SEPARATOR);
                consumeFieldValues(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
            }
            if (builder != null) {
                builder.setLocation(fieldDescriptor, TextFormatParseLocation.create(line, column));
            }
            if (tokenizer.tryConsume(";")) {
                return;
            }
            tokenizer.tryConsume(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, List<String> list) throws ParseException {
            mergeField(tokenizer, extensionRegistry, mergeTarget, this.parseInfoTreeBuilder, list);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void skipField(com.google.protobuf.TextFormat.Tokenizer r4) throws com.google.protobuf.TextFormat.ParseException {
            /*
                r3 = this;
                r0 = r4
                java.lang.String r1 = "["
                boolean r0 = r0.tryConsume(r1)
                if (r0 == 0) goto L23
            La:
                r0 = r4
                java.lang.String r0 = r0.consumeIdentifier()
                r0 = r4
                java.lang.String r1 = "."
                boolean r0 = r0.tryConsume(r1)
                if (r0 != 0) goto La
                r0 = r4
                java.lang.String r1 = "]"
                r0.consume(r1)
                goto L28
            L23:
                r0 = r4
                java.lang.String r0 = r0.consumeIdentifier()
            L28:
                r0 = r4
                java.lang.String r1 = ":"
                boolean r0 = r0.tryConsume(r1)
                if (r0 == 0) goto L4b
                r0 = r4
                java.lang.String r1 = "<"
                boolean r0 = r0.lookingAt(r1)
                if (r0 != 0) goto L4b
                r0 = r4
                java.lang.String r1 = "{"
                boolean r0 = r0.lookingAt(r1)
                if (r0 != 0) goto L4b
                r0 = r3
                r1 = r4
                r0.skipFieldValue(r1)
                goto L50
            L4b:
                r0 = r3
                r1 = r4
                r0.skipFieldMessage(r1)
            L50:
                r0 = r4
                java.lang.String r1 = ";"
                boolean r0 = r0.tryConsume(r1)
                if (r0 != 0) goto L62
                r0 = r4
                java.lang.String r1 = ","
                boolean r0 = r0.tryConsume(r1)
            L62:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.TextFormat.Parser.skipField(com.google.protobuf.TextFormat$Tokenizer):void");
        }

        private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
            String str;
            if (tokenizer.tryConsume("<")) {
                str = ">";
            } else {
                tokenizer.consume("{");
                str = "}";
            }
            while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
                skipField(tokenizer);
            }
            tokenizer.consume(str);
        }

        private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
            if (!tokenizer.tryConsumeString()) {
                if (tokenizer.tryConsumeIdentifier() || tokenizer.tryConsumeInt64() || tokenizer.tryConsumeUInt64() || tokenizer.tryConsumeDouble() || tokenizer.tryConsumeFloat()) {
                    return;
                }
                throw tokenizer.parseException("Invalid field value: " + tokenizer.currentToken);
            }
            do {
            } while (tokenizer.tryConsumeString());
        }

        private static StringBuilder toStringBuilder(Readable readable) throws IOException {
            StringBuilder sb = new StringBuilder();
            CharBuffer allocate = CharBuffer.allocate(BUFFER_SIZE);
            while (true) {
                int read = readable.read(allocate);
                if (read == -1) {
                    return sb;
                }
                allocate.flip();
                sb.append((CharSequence) allocate, 0, read);
            }
        }

        public void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            Tokenizer tokenizer = new Tokenizer(charSequence, null);
            MessageReflection.BuilderAdapter builderAdapter = new MessageReflection.BuilderAdapter(builder);
            ArrayList arrayList = new ArrayList();
            while (!tokenizer.atEnd()) {
                mergeField(tokenizer, extensionRegistry, builderAdapter, arrayList);
            }
            checkUnknownFields(arrayList);
        }

        public void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
            merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
            merge(toStringBuilder(readable), extensionRegistry, builder);
        }

        public void merge(Readable readable, Message.Builder builder) throws IOException {
            merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$Printer.class */
    public static final class Printer {
        static final Printer DEFAULT = new Printer(true);
        static final Printer UNICODE = new Printer(false);
        private final boolean escapeNonAscii;

        private Printer(boolean z) {
            this.escapeNonAscii = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void print(MessageOrBuilder messageOrBuilder, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
                printField(entry.getKey(), entry.getValue(), textGenerator);
            }
            printUnknownFields(messageOrBuilder.getUnknownFields(), textGenerator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (!fieldDescriptor.isRepeated()) {
                printSingleField(fieldDescriptor, obj, textGenerator);
                return;
            }
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                printSingleField(fieldDescriptor, it.next(), textGenerator);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            switch (C00511.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptor.getType().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    textGenerator.print(((Integer) obj).toString());
                    return;
                case 4:
                case 5:
                case 6:
                    textGenerator.print(((Long) obj).toString());
                    return;
                case 7:
                    textGenerator.print(((Boolean) obj).toString());
                    return;
                case 8:
                    textGenerator.print(((Float) obj).toString());
                    return;
                case 9:
                    textGenerator.print(((Double) obj).toString());
                    return;
                case 10:
                case 11:
                    textGenerator.print(TextFormat.unsignedToString(((Integer) obj).intValue()));
                    return;
                case 12:
                case 13:
                    textGenerator.print(TextFormat.unsignedToString(((Long) obj).longValue()));
                    return;
                case 14:
                    textGenerator.print("\"");
                    textGenerator.print(this.escapeNonAscii ? TextFormatEscaper.escapeText((String) obj) : TextFormat.escapeDoubleQuotesAndBackslashes((String) obj).replace("\n", "\\n"));
                    textGenerator.print("\"");
                    return;
                case 15:
                    textGenerator.print("\"");
                    if (obj instanceof ByteString) {
                        textGenerator.print(TextFormat.escapeBytes((ByteString) obj));
                    } else {
                        textGenerator.print(TextFormat.escapeBytes((byte[]) obj));
                    }
                    textGenerator.print("\"");
                    return;
                case 16:
                    textGenerator.print(((Descriptors.EnumValueDescriptor) obj).getName());
                    return;
                case 17:
                case 18:
                    print((Message) obj, textGenerator);
                    return;
                default:
                    return;
            }
        }

        private void printSingleField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (fieldDescriptor.isExtension()) {
                textGenerator.print("[");
                if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
                    textGenerator.print(fieldDescriptor.getMessageType().getFullName());
                } else {
                    textGenerator.print(fieldDescriptor.getFullName());
                }
                textGenerator.print("]");
            } else if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                textGenerator.print(fieldDescriptor.getMessageType().getName());
            } else {
                textGenerator.print(fieldDescriptor.getName());
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                textGenerator.print(" {");
                textGenerator.eol();
                textGenerator.indent();
            } else {
                textGenerator.print(": ");
            }
            printFieldValue(fieldDescriptor, obj, textGenerator);
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                textGenerator.outdent();
                textGenerator.print("}");
            }
            textGenerator.eol();
        }

        private void printUnknownField(int i, int i2, List<?> list, TextGenerator textGenerator) throws IOException {
            for (Object obj : list) {
                textGenerator.print(String.valueOf(i));
                textGenerator.print(": ");
                TextFormat.printUnknownFieldValue(i2, obj, textGenerator);
                textGenerator.eol();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printUnknownFields(UnknownFieldSet unknownFieldSet, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFieldSet.asMap().entrySet()) {
                int intValue = entry.getKey().intValue();
                UnknownFieldSet.Field value = entry.getValue();
                printUnknownField(intValue, 0, value.getVarintList(), textGenerator);
                printUnknownField(intValue, 5, value.getFixed32List(), textGenerator);
                printUnknownField(intValue, 1, value.getFixed64List(), textGenerator);
                printUnknownField(intValue, 2, value.getLengthDelimitedList(), textGenerator);
                for (UnknownFieldSet unknownFieldSet2 : value.getGroupList()) {
                    textGenerator.print(entry.getKey().toString());
                    textGenerator.print(" {");
                    textGenerator.eol();
                    textGenerator.indent();
                    printUnknownFields(unknownFieldSet2, textGenerator);
                    textGenerator.outdent();
                    textGenerator.print("}");
                    textGenerator.eol();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$TextGenerator.class */
    public static final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;
        private final boolean singleLineMode;

        private TextGenerator(Appendable appendable, boolean z) {
            this.indent = new StringBuilder();
            this.atStartOfLine = false;
            this.output = appendable;
            this.singleLineMode = z;
        }

        /* synthetic */ TextGenerator(Appendable appendable, boolean z, C00511 c00511) {
            this(appendable, z);
        }

        public void eol() throws IOException {
            if (!this.singleLineMode) {
                this.output.append("\n");
            }
            this.atStartOfLine = true;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.setLength(length - 2);
        }

        public void print(CharSequence charSequence) throws IOException {
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.singleLineMode ? " " : this.indent);
            }
            this.output.append(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$Tokenizer.class */
    public static final class Tokenizer {
        private int column;
        private String currentToken;
        private int line;
        private final Matcher matcher;
        private int pos;
        private int previousColumn;
        private int previousLine;
        private final CharSequence text;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

        private Tokenizer(CharSequence charSequence) {
            this.pos = 0;
            this.line = 0;
            this.column = 0;
            this.previousLine = 0;
            this.previousColumn = 0;
            this.text = charSequence;
            this.matcher = WHITESPACE.matcher(charSequence);
            skipWhitespace();
            nextToken();
        }

        /* synthetic */ Tokenizer(CharSequence charSequence, C00511 c00511) {
            this(charSequence);
        }

        private void consumeByteString(List<ByteString> list) throws ParseException {
            char c = 0;
            if (this.currentToken.length() > 0) {
                c = this.currentToken.charAt(0);
            }
            if (c != '\"' && c != '\'') {
                throw parseException("Expected string.");
            }
            if (this.currentToken.length() >= 2) {
                String str = this.currentToken;
                if (str.charAt(str.length() - 1) == c) {
                    try {
                        ByteString unescapeBytes = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
                        nextToken();
                        list.add(unescapeBytes);
                        return;
                    } catch (InvalidEscapeSequenceException e) {
                        throw parseException(e.getMessage());
                    }
                }
            }
            throw parseException("String missing ending quote.");
        }

        private ParseException floatParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse number: " + numberFormatException.getMessage());
        }

        private ParseException integerParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse integer: " + numberFormatException.getMessage());
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void consume(String str) throws ParseException {
            if (tryConsume(str)) {
                return;
            }
            throw parseException("Expected \"" + str + "\".");
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals("True") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                nextToken();
                return true;
            }
            if (!this.currentToken.equals("false") && !this.currentToken.equals("False") && !this.currentToken.equals("f") && !this.currentToken.equals("0")) {
                throw parseException("Expected \"true\" or \"false\".");
            }
            nextToken();
            return false;
        }

        public ByteString consumeByteString() throws ParseException {
            ArrayList arrayList = new ArrayList();
            consumeByteString(arrayList);
            while (true) {
                if (!this.currentToken.startsWith("'") && !this.currentToken.startsWith("\"")) {
                    return ByteString.copyFrom(arrayList);
                }
                consumeByteString(arrayList);
            }
        }

        public double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                nextToken();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            }
            if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            }
            try {
                double parseDouble = Double.parseDouble(this.currentToken);
                nextToken();
                return parseDouble;
            } catch (NumberFormatException e) {
                throw floatParseException(e);
            }
        }

        public float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                nextToken();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            }
            if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return Float.NaN;
            }
            try {
                float parseFloat = Float.parseFloat(this.currentToken);
                nextToken();
                return parseFloat;
            } catch (NumberFormatException e) {
                throw floatParseException(e);
            }
        }

        public String consumeIdentifier() throws ParseException {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.currentToken.length()) {
                    String str = this.currentToken;
                    nextToken();
                    return str;
                }
                char charAt = this.currentToken.charAt(i2);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.'))) {
                    throw parseException("Expected identifier. Found '" + this.currentToken + "'");
                }
                i = i2 + 1;
            }
        }

        public int consumeInt32() throws ParseException {
            try {
                int parseInt32 = TextFormat.parseInt32(this.currentToken);
                nextToken();
                return parseInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeInt64() throws ParseException {
            try {
                long parseInt64 = TextFormat.parseInt64(this.currentToken);
                nextToken();
                return parseInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public String consumeString() throws ParseException {
            return consumeByteString().toStringUtf8();
        }

        public int consumeUInt32() throws ParseException {
            try {
                int parseUInt32 = TextFormat.parseUInt32(this.currentToken);
                nextToken();
                return parseUInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeUInt64() throws ParseException {
            try {
                long parseUInt64 = TextFormat.parseUInt64(this.currentToken);
                nextToken();
                return parseUInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        int getColumn() {
            return this.column;
        }

        int getLine() {
            return this.line;
        }

        int getPreviousColumn() {
            return this.previousColumn;
        }

        int getPreviousLine() {
            return this.previousLine;
        }

        public boolean lookingAt(String str) {
            return this.currentToken.equals(str);
        }

        public boolean lookingAtInteger() {
            boolean z = false;
            if (this.currentToken.length() == 0) {
                return false;
            }
            char charAt = this.currentToken.charAt(0);
            if (('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+') {
                z = true;
            }
            return z;
        }

        public void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    this.line++;
                    this.column = 0;
                } else {
                    this.column++;
                }
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                Matcher matcher2 = this.matcher;
                matcher2.region(this.pos + 1, matcher2.regionEnd());
            }
            skipWhitespace();
        }

        public ParseException parseException(String str) {
            return new ParseException(this.line + 1, this.column + 1, str);
        }

        public ParseException parseExceptionPreviousToken(String str) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, str);
        }

        public boolean tryConsume(String str) {
            if (!this.currentToken.equals(str)) {
                return false;
            }
            nextToken();
            return true;
        }

        public boolean tryConsumeDouble() {
            try {
                consumeDouble();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeFloat() {
            try {
                consumeFloat();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeIdentifier() {
            try {
                consumeIdentifier();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeInt64() {
            try {
                consumeInt64();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeString() {
            try {
                consumeString();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                consumeUInt64();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public UnknownFieldParseException unknownFieldParseExceptionPreviousToken(String str, String str2) {
            return new UnknownFieldParseException(this.previousLine + 1, this.previousColumn + 1, str, str2);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/TextFormat$UnknownFieldParseException.class */
    public static class UnknownFieldParseException extends ParseException {
        private final String unknownField;

        public UnknownFieldParseException(int i, int i2, String str, String str2) {
            super(i, i2, str2);
            this.unknownField = str;
        }

        public UnknownFieldParseException(String str) {
            this(-1, -1, "", str);
        }

        public String getUnknownField() {
            return this.unknownField;
        }
    }

    private TextFormat() {
    }

    private static int digitValue(byte b) {
        if (48 > b || b > 57) {
            return ((97 > b || b > 122) ? b - 65 : b - 97) + 10;
        }
        return b - 48;
    }

    public static String escapeBytes(ByteString byteString) {
        return TextFormatEscaper.escapeBytes(byteString);
    }

    public static String escapeBytes(byte[] bArr) {
        return TextFormatEscaper.escapeBytes(bArr);
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(str);
    }

    static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static Parser getParser() {
        return PARSER;
    }

    private static boolean isHex(byte b) {
        if (48 <= b && b <= 57) {
            return true;
        }
        if (97 > b || b > 102) {
            return 65 <= b && b <= 70;
        }
        return true;
    }

    private static boolean isOctal(byte b) {
        return 48 <= b && b <= 55;
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, extensionRegistry, builder);
    }

    public static void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, builder);
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        PARSER.merge(readable, extensionRegistry, builder);
    }

    public static void merge(Readable readable, Message.Builder builder) throws IOException {
        PARSER.merge(readable, builder);
    }

    private static TextGenerator multiLineOutput(Appendable appendable) {
        return new TextGenerator(appendable, false, null);
    }

    static int parseInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, true, false);
    }

    static long parseInt64(String str) throws NumberFormatException {
        return parseInteger(str, true, true);
    }

    private static long parseInteger(String str, boolean z, boolean z2) throws NumberFormatException {
        int i;
        long longValue;
        int i2 = 0;
        boolean z3 = true;
        if (!str.startsWith(Constants.ACCEPT_TIME_SEPARATOR_SERVER, 0)) {
            z3 = false;
        } else {
            if (!z) {
                throw new NumberFormatException("Number must be positive: " + str);
            }
            i2 = 1;
        }
        int i3 = 10;
        if (str.startsWith("0x", i2)) {
            i = i2 + 2;
            i3 = 16;
        } else {
            i = i2;
            if (str.startsWith("0", i2)) {
                i3 = 8;
                i = i2;
            }
        }
        String substring = str.substring(i);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i3);
            long j = parseLong;
            if (z3) {
                j = -parseLong;
            }
            longValue = j;
            if (!z2) {
                if (z) {
                    if (j <= 2147483647L && j >= -2147483648L) {
                        return j;
                    }
                    throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
                }
                if (j < 4294967296L && j >= 0) {
                    return j;
                }
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            }
        } else {
            BigInteger bigInteger = new BigInteger(substring, i3);
            BigInteger bigInteger2 = bigInteger;
            if (z3) {
                bigInteger2 = bigInteger.negate();
            }
            if (z2) {
                if (z) {
                    if (bigInteger2.bitLength() > 63) {
                        throw new NumberFormatException("Number out of range for 64-bit signed integer: " + str);
                    }
                } else if (bigInteger2.bitLength() > 64) {
                    throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + str);
                }
            } else if (z) {
                if (bigInteger2.bitLength() > 31) {
                    throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
                }
            } else if (bigInteger2.bitLength() > 32) {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            }
            longValue = bigInteger2.longValue();
        }
        return longValue;
    }

    static int parseUInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, false, false);
    }

    static long parseUInt64(String str) throws NumberFormatException {
        return parseInteger(str, false, true);
    }

    public static void print(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        Printer.DEFAULT.print(messageOrBuilder, multiLineOutput(appendable));
    }

    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        Printer.DEFAULT.printUnknownFields(unknownFieldSet, multiLineOutput(appendable));
    }

    public static void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        Printer.DEFAULT.printField(fieldDescriptor, obj, multiLineOutput(appendable));
    }

    public static String printFieldToString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            printField(fieldDescriptor, obj, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        Printer.DEFAULT.printFieldValue(fieldDescriptor, obj, multiLineOutput(appendable));
    }

    public static String printToString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            print(messageOrBuilder, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            print(unknownFieldSet, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.UNICODE.print(messageOrBuilder, multiLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.UNICODE.printUnknownFields(unknownFieldSet, multiLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printUnicode(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        Printer.UNICODE.print(messageOrBuilder, multiLineOutput(appendable));
    }

    public static void printUnicode(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        Printer.UNICODE.printUnknownFields(unknownFieldSet, multiLineOutput(appendable));
    }

    public static void printUnicodeFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        Printer.UNICODE.printFieldValue(fieldDescriptor, obj, multiLineOutput(appendable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printUnknownFieldValue(int i, Object obj, TextGenerator textGenerator) throws IOException {
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType == 0) {
            textGenerator.print(unsignedToString(((Long) obj).longValue()));
            return;
        }
        if (tagWireType == 1) {
            textGenerator.print(String.format((Locale) null, "0x%016x", (Long) obj));
            return;
        }
        if (tagWireType != 2) {
            if (tagWireType == 3) {
                Printer.DEFAULT.printUnknownFields((UnknownFieldSet) obj, textGenerator);
                return;
            } else {
                if (tagWireType == 5) {
                    textGenerator.print(String.format((Locale) null, "0x%08x", (Integer) obj));
                    return;
                }
                throw new IllegalArgumentException("Bad tag: " + i);
            }
        }
        try {
            UnknownFieldSet parseFrom = UnknownFieldSet.parseFrom((ByteString) obj);
            textGenerator.print("{");
            textGenerator.eol();
            textGenerator.indent();
            Printer.DEFAULT.printUnknownFields(parseFrom, textGenerator);
            textGenerator.outdent();
            textGenerator.print("}");
        } catch (InvalidProtocolBufferException e) {
            textGenerator.print("\"");
            textGenerator.print(escapeBytes((ByteString) obj));
            textGenerator.print("\"");
        }
    }

    public static void printUnknownFieldValue(int i, Object obj, Appendable appendable) throws IOException {
        printUnknownFieldValue(i, obj, multiLineOutput(appendable));
    }

    public static String shortDebugString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.DEFAULT.printField(fieldDescriptor, obj, singleLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.DEFAULT.print(messageOrBuilder, singleLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            Printer.DEFAULT.printUnknownFields(unknownFieldSet, singleLineOutput(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static TextGenerator singleLineOutput(Appendable appendable) {
        return new TextGenerator(appendable, true, null);
    }

    public static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequenceException {
        int i;
        int i2;
        int i3;
        ByteString copyFromUtf8 = ByteString.copyFromUtf8(charSequence.toString());
        byte[] bArr = new byte[copyFromUtf8.size()];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i4 >= copyFromUtf8.size()) {
                return bArr.length == i6 ? ByteString.wrap(bArr) : ByteString.copyFrom(bArr, 0, i6);
            }
            byte byteAt = copyFromUtf8.byteAt(i4);
            if (byteAt == 92) {
                i2 = i4 + 1;
                if (i2 >= copyFromUtf8.size()) {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
                byte byteAt2 = copyFromUtf8.byteAt(i2);
                if (isOctal(byteAt2)) {
                    int digitValue = digitValue(byteAt2);
                    int i7 = i2 + 1;
                    int i8 = i2;
                    int i9 = digitValue;
                    if (i7 < copyFromUtf8.size()) {
                        i8 = i2;
                        i9 = digitValue;
                        if (isOctal(copyFromUtf8.byteAt(i7))) {
                            i9 = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i7));
                            i8 = i7;
                        }
                    }
                    int i10 = i8 + 1;
                    int i11 = i8;
                    int i12 = i9;
                    if (i10 < copyFromUtf8.size()) {
                        i11 = i8;
                        i12 = i9;
                        if (isOctal(copyFromUtf8.byteAt(i10))) {
                            i12 = (i9 * 8) + digitValue(copyFromUtf8.byteAt(i10));
                            i11 = i10;
                        }
                    }
                    i = i6 + 1;
                    bArr[i6] = (byte) i12;
                    i4 = i11;
                } else {
                    if (byteAt2 == 34) {
                        i3 = i6 + 1;
                        bArr[i6] = 34;
                    } else if (byteAt2 == 39) {
                        i3 = i6 + 1;
                        bArr[i6] = 39;
                    } else if (byteAt2 == 92) {
                        i3 = i6 + 1;
                        bArr[i6] = 92;
                    } else if (byteAt2 == 102) {
                        i3 = i6 + 1;
                        bArr[i6] = 12;
                    } else if (byteAt2 == 110) {
                        i3 = i6 + 1;
                        bArr[i6] = 10;
                    } else if (byteAt2 == 114) {
                        i3 = i6 + 1;
                        bArr[i6] = 13;
                    } else if (byteAt2 == 116) {
                        i3 = i6 + 1;
                        bArr[i6] = 9;
                    } else if (byteAt2 == 118) {
                        i3 = i6 + 1;
                        bArr[i6] = 11;
                    } else if (byteAt2 == 120) {
                        int i13 = i2 + 1;
                        if (i13 >= copyFromUtf8.size() || !isHex(copyFromUtf8.byteAt(i13))) {
                            break;
                        }
                        int digitValue2 = digitValue(copyFromUtf8.byteAt(i13));
                        int i14 = i13 + 1;
                        i4 = i13;
                        int i15 = digitValue2;
                        if (i14 < copyFromUtf8.size()) {
                            i4 = i13;
                            i15 = digitValue2;
                            if (isHex(copyFromUtf8.byteAt(i14))) {
                                i15 = (digitValue2 * 16) + digitValue(copyFromUtf8.byteAt(i14));
                                i4 = i14;
                            }
                        }
                        bArr[i6] = (byte) i15;
                        i = i6 + 1;
                    } else if (byteAt2 == 97) {
                        i3 = i6 + 1;
                        bArr[i6] = 7;
                    } else {
                        if (byteAt2 != 98) {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + ((char) byteAt2) + '\'');
                        }
                        i3 = i6 + 1;
                        bArr[i6] = 8;
                    }
                    i = i3;
                    i4 = i2 + 1;
                    i5 = i;
                }
            } else {
                i = i6 + 1;
                bArr[i6] = byteAt;
            }
            i2 = i4;
            i4 = i2 + 1;
            i5 = i;
        }
        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
    }

    static String unescapeText(String str) throws InvalidEscapeSequenceException {
        return unescapeBytes(str).toStringUtf8();
    }

    public static String unsignedToString(int i) {
        return i >= 0 ? Integer.toString(i) : Long.toString(i & 4294967295L);
    }

    public static String unsignedToString(long j) {
        return j >= 0 ? Long.toString(j) : BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }
}
