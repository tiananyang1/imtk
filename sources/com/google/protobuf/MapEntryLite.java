package com.google.protobuf;

import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/MapEntryLite.class */
public class MapEntryLite<K, V> {
    private static final int KEY_FIELD_NUMBER = 1;
    private static final int VALUE_FIELD_NUMBER = 2;
    private final K key;
    private final Metadata<K, V> metadata;
    private final V value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.MapEntryLite$1 */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/MapEntryLite$1.class */
    public static /* synthetic */ class C00311 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = new int[WireFormat.FieldType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.google.protobuf.WireFormat$FieldType[] r0 = com.google.protobuf.WireFormat.FieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.protobuf.MapEntryLite.C00311.$SwitchMap$com$google$protobuf$WireFormat$FieldType = r0
                int[] r0 = com.google.protobuf.MapEntryLite.C00311.$SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch: java.lang.NoSuchFieldError -> L2b
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.MESSAGE     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.google.protobuf.MapEntryLite.C00311.$SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.ENUM     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.google.protobuf.MapEntryLite.C00311.$SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.GROUP     // Catch: java.lang.NoSuchFieldError -> L33
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L33
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L33
                return
            L2b:
                r4 = move-exception
                goto L14
            L2f:
                r4 = move-exception
                goto L1f
            L33:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MapEntryLite.C00311.m3190clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/MapEntryLite$Metadata.class */
    public static class Metadata<K, V> {
        public final K defaultKey;
        public final V defaultValue;
        public final WireFormat.FieldType keyType;
        public final WireFormat.FieldType valueType;

        public Metadata(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
            this.keyType = fieldType;
            this.defaultKey = k;
            this.valueType = fieldType2;
            this.defaultValue = v;
        }
    }

    private MapEntryLite(Metadata<K, V> metadata, K k, V v) {
        this.metadata = metadata;
        this.key = k;
        this.value = v;
    }

    private MapEntryLite(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        this.metadata = new Metadata<>(fieldType, k, fieldType2, v);
        this.key = k;
        this.value = v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> int computeSerializedSize(Metadata<K, V> metadata, K k, V v) {
        return FieldSet.computeElementSize(metadata.keyType, 1, k) + FieldSet.computeElementSize(metadata.valueType, 2, v);
    }

    public static <K, V> MapEntryLite<K, V> newDefaultInstance(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        return new MapEntryLite<>(fieldType, k, fieldType2, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Map.Entry<K, V> parseEntry(CodedInputStream codedInputStream, Metadata<K, V> metadata, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == WireFormat.makeTag(1, metadata.keyType.getWireType())) {
                obj = parseField(codedInputStream, extensionRegistryLite, metadata.keyType, obj);
            } else if (readTag == WireFormat.makeTag(2, metadata.valueType.getWireType())) {
                obj2 = parseField(codedInputStream, extensionRegistryLite, metadata.valueType, obj2);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    static <T> T parseField(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, WireFormat.FieldType fieldType, T t) throws IOException {
        int i = C00311.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()];
        if (i == 1) {
            MessageLite.Builder builder = ((MessageLite) t).toBuilder();
            codedInputStream.readMessage(builder, extensionRegistryLite);
            return (T) builder.buildPartial();
        }
        if (i == 2) {
            return (T) Integer.valueOf(codedInputStream.readEnum());
        }
        if (i != 3) {
            return (T) FieldSet.readPrimitiveField(codedInputStream, fieldType, true);
        }
        throw new RuntimeException("Groups are not allowed in maps.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> void writeTo(CodedOutputStream codedOutputStream, Metadata<K, V> metadata, K k, V v) throws IOException {
        FieldSet.writeElement(codedOutputStream, metadata.keyType, 1, k);
        FieldSet.writeElement(codedOutputStream, metadata.valueType, 2, v);
    }

    public int computeMessageSize(int i, K k, V v) {
        return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSerializedSize(this.metadata, k, v));
    }

    public K getKey() {
        return this.key;
    }

    Metadata<K, V> getMetadata() {
        return this.metadata;
    }

    public V getValue() {
        return this.value;
    }

    public Map.Entry<K, V> parseEntry(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return parseEntry(byteString.newCodedInput(), this.metadata, extensionRegistryLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void parseInto(MapFieldLite<K, V> mapFieldLite, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
        K k = this.metadata.defaultKey;
        V v = this.metadata.defaultValue;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == WireFormat.makeTag(1, this.metadata.keyType.getWireType())) {
                k = parseField(codedInputStream, extensionRegistryLite, this.metadata.keyType, k);
            } else if (readTag == WireFormat.makeTag(2, this.metadata.valueType.getWireType())) {
                v = parseField(codedInputStream, extensionRegistryLite, this.metadata.valueType, v);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(0);
        codedInputStream.popLimit(pushLimit);
        mapFieldLite.put(k, v);
    }

    public void serializeTo(CodedOutputStream codedOutputStream, int i, K k, V v) throws IOException {
        codedOutputStream.writeTag(i, 2);
        codedOutputStream.writeUInt32NoTag(computeSerializedSize(this.metadata, k, v));
        writeTo(codedOutputStream, this.metadata, k, v);
    }
}
