package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.Message;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/MapEntry.class */
public final class MapEntry<K, V> extends AbstractMessage {
    private volatile int cachedSerializedSize;
    private final K key;
    private final Metadata<K, V> metadata;
    private final V value;

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/MapEntry$Builder.class */
    public static class Builder<K, V> extends AbstractMessage.Builder<Builder<K, V>> {
        private boolean hasKey;
        private boolean hasValue;
        private K key;
        private final Metadata<K, V> metadata;
        private V value;

        private Builder(Metadata<K, V> metadata) {
            this(metadata, metadata.defaultKey, metadata.defaultValue, false, false);
        }

        private Builder(Metadata<K, V> metadata, K k, V v, boolean z, boolean z2) {
            this.metadata = metadata;
            this.key = k;
            this.value = v;
            this.hasKey = z;
            this.hasValue = z2;
        }

        private void checkFieldDescriptor(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() == this.metadata.descriptor) {
                return;
            }
            throw new RuntimeException("Wrong FieldDescriptor \"" + fieldDescriptor.getFullName() + "\" used in message \"" + this.metadata.descriptor.getFullName());
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] */
        public Builder<K, V> m3173addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.google.protobuf.MapEntry<K, V>, com.google.protobuf.MapEntry, com.google.protobuf.Message] */
        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MapEntry<K, V> m3175build() {
            MapEntry<K, V> m3177buildPartial = m3177buildPartial();
            if (m3177buildPartial.isInitialized()) {
                return m3177buildPartial;
            }
            throw newUninitializedMessageException(m3177buildPartial);
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MapEntry<K, V> m3177buildPartial() {
            return new MapEntry<>(this.metadata, this.key, this.value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] */
        public Builder<K, V> m3178clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            checkFieldDescriptor(fieldDescriptor);
            if (fieldDescriptor.getNumber() == 1) {
                clearKey();
                return this;
            }
            clearValue();
            return this;
        }

        public Builder<K, V> clearKey() {
            this.key = this.metadata.defaultKey;
            this.hasKey = false;
            return this;
        }

        public Builder<K, V> clearValue() {
            this.value = this.metadata.defaultValue;
            this.hasValue = false;
            return this;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder<K, V> m3183clone() {
            return new Builder<>(this.metadata, this.key, this.value, this.hasKey, this.hasValue);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            TreeMap treeMap = new TreeMap();
            for (Descriptors.FieldDescriptor fieldDescriptor : this.metadata.descriptor.getFields()) {
                if (hasField(fieldDescriptor)) {
                    treeMap.put(fieldDescriptor, getField(fieldDescriptor));
                }
            }
            return Collections.unmodifiableMap(treeMap);
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MapEntry<K, V> m3185getDefaultInstanceForType() {
            Metadata<K, V> metadata = this.metadata;
            return new MapEntry<>(metadata, metadata.defaultKey, this.metadata.defaultValue);
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return this.metadata.descriptor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v11, types: [com.google.protobuf.Descriptors$EnumValueDescriptor] */
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            checkFieldDescriptor(fieldDescriptor);
            K key = fieldDescriptor.getNumber() == 1 ? getKey() : getValue();
            K k = key;
            if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
                k = fieldDescriptor.getEnumType().findValueByNumberCreatingIfUnknown(((Integer) key).intValue());
            }
            return k;
        }

        public K getKey() {
            return this.key;
        }

        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        public UnknownFieldSet getUnknownFields() {
            return UnknownFieldSet.getDefaultInstance();
        }

        public V getValue() {
            return this.value;
        }

        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            checkFieldDescriptor(fieldDescriptor);
            return fieldDescriptor.getNumber() == 1 ? this.hasKey : this.hasValue;
        }

        public boolean isInitialized() {
            return MapEntry.isInitialized(this.metadata, this.value);
        }

        public Message.Builder newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor) {
            checkFieldDescriptor(fieldDescriptor);
            if (fieldDescriptor.getNumber() == 2 && fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                return ((Message) this.value).newBuilderForType();
            }
            throw new RuntimeException("\"" + fieldDescriptor.getFullName() + "\" is not a message value field.");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v22, types: [com.google.protobuf.Message] */
        /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object] */
        /* renamed from: setField, reason: merged with bridge method [inline-methods] */
        public Builder<K, V> m3186setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            V v;
            checkFieldDescriptor(fieldDescriptor);
            if (fieldDescriptor.getNumber() == 1) {
                setKey(obj);
                return this;
            }
            if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
                v = Integer.valueOf(((Descriptors.EnumValueDescriptor) obj).getNumber());
            } else {
                v = obj;
                if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) {
                    v = obj;
                    if (obj != 0) {
                        v = obj;
                        if (!this.metadata.defaultValue.getClass().isInstance(obj)) {
                            v = ((Message) this.metadata.defaultValue).toBuilder().mergeFrom((Message) obj).build();
                        }
                    }
                }
            }
            setValue(v);
            return this;
        }

        public Builder<K, V> setKey(K k) {
            this.key = k;
            this.hasKey = true;
            return this;
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] */
        public Builder<K, V> m3187setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] */
        public Builder<K, V> m3188setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return this;
        }

        public Builder<K, V> setValue(V v) {
            this.value = v;
            this.hasValue = true;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/MapEntry$Metadata.class */
    public static final class Metadata<K, V> extends MapEntryLite.Metadata<K, V> {
        public final Descriptors.Descriptor descriptor;
        public final Parser<MapEntry<K, V>> parser;

        public Metadata(Descriptors.Descriptor descriptor, MapEntry<K, V> mapEntry, WireFormat.FieldType fieldType, WireFormat.FieldType fieldType2) {
            super(fieldType, ((MapEntry) mapEntry).key, fieldType2, ((MapEntry) mapEntry).value);
            this.descriptor = descriptor;
            this.parser = new AbstractParser<MapEntry<K, V>>() { // from class: com.google.protobuf.MapEntry.Metadata.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public MapEntry<K, V> m3189parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new MapEntry<>(Metadata.this, codedInputStream, extensionRegistryLite);
                }
            };
        }
    }

    private MapEntry(Descriptors.Descriptor descriptor, WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        this.cachedSerializedSize = -1;
        this.key = k;
        this.value = v;
        this.metadata = new Metadata<>(descriptor, this, fieldType, fieldType2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MapEntry(Metadata<K, V> metadata, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.cachedSerializedSize = -1;
        try {
            this.metadata = metadata;
            Map.Entry parseEntry = MapEntryLite.parseEntry(codedInputStream, metadata, extensionRegistryLite);
            this.key = (K) parseEntry.getKey();
            this.value = (V) parseEntry.getValue();
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (IOException e2) {
            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
        }
    }

    private MapEntry(Metadata metadata, K k, V v) {
        this.cachedSerializedSize = -1;
        this.key = k;
        this.value = v;
        this.metadata = metadata;
    }

    private void checkFieldDescriptor(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getContainingType() == this.metadata.descriptor) {
            return;
        }
        throw new RuntimeException("Wrong FieldDescriptor \"" + fieldDescriptor.getFullName() + "\" used in message \"" + this.metadata.descriptor.getFullName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <V> boolean isInitialized(Metadata metadata, V v) {
        if (metadata.valueType.getJavaType() == WireFormat.JavaType.MESSAGE) {
            return ((MessageLite) v).isInitialized();
        }
        return true;
    }

    public static <K, V> MapEntry<K, V> newDefaultInstance(Descriptors.Descriptor descriptor, WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        return new MapEntry<>(descriptor, fieldType, k, fieldType2, v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        TreeMap treeMap = new TreeMap();
        for (Descriptors.FieldDescriptor fieldDescriptor : this.metadata.descriptor.getFields()) {
            if (hasField(fieldDescriptor)) {
                treeMap.put(fieldDescriptor, getField(fieldDescriptor));
            }
        }
        return Collections.unmodifiableMap(treeMap);
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MapEntry<K, V> m3168getDefaultInstanceForType() {
        Metadata<K, V> metadata = this.metadata;
        return new MapEntry<>(metadata, metadata.defaultKey, this.metadata.defaultValue);
    }

    public Descriptors.Descriptor getDescriptorForType() {
        return this.metadata.descriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.google.protobuf.Descriptors$EnumValueDescriptor] */
    public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
        checkFieldDescriptor(fieldDescriptor);
        K key = fieldDescriptor.getNumber() == 1 ? getKey() : getValue();
        K k = key;
        if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
            k = fieldDescriptor.getEnumType().findValueByNumberCreatingIfUnknown(((Integer) key).intValue());
        }
        return k;
    }

    public K getKey() {
        return this.key;
    }

    final Metadata<K, V> getMetadata() {
        return this.metadata;
    }

    public Parser<MapEntry<K, V>> getParserForType() {
        return this.metadata.parser;
    }

    public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
        throw new RuntimeException("There is no repeated field in a map entry message.");
    }

    public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
        throw new RuntimeException("There is no repeated field in a map entry message.");
    }

    public int getSerializedSize() {
        if (this.cachedSerializedSize != -1) {
            return this.cachedSerializedSize;
        }
        int computeSerializedSize = MapEntryLite.computeSerializedSize(this.metadata, this.key, this.value);
        this.cachedSerializedSize = computeSerializedSize;
        return computeSerializedSize;
    }

    public UnknownFieldSet getUnknownFields() {
        return UnknownFieldSet.getDefaultInstance();
    }

    public V getValue() {
        return this.value;
    }

    public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
        checkFieldDescriptor(fieldDescriptor);
        return true;
    }

    public boolean isInitialized() {
        return isInitialized(this.metadata, this.value);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder<K, V> m3170newBuilderForType() {
        return new Builder<>(this.metadata);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder<K, V> m3172toBuilder() {
        return new Builder<>(this.metadata, this.key, this.value, true, true);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        MapEntryLite.writeTo(codedOutputStream, this.metadata, this.key, this.value);
    }
}
