package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Extension;
import com.google.protobuf.GeneratedMessage;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/ExtensionRegistry.class */
public class ExtensionRegistry extends ExtensionRegistryLite {
    static final ExtensionRegistry EMPTY_REGISTRY = new ExtensionRegistry(true);
    private final Map<String, ExtensionInfo> immutableExtensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> immutableExtensionsByNumber;
    private final Map<String, ExtensionInfo> mutableExtensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> mutableExtensionsByNumber;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.protobuf.ExtensionRegistry$1 */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/ExtensionRegistry$1.class */
    public static /* synthetic */ class C00081 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Extension$ExtensionType = new int[Extension.ExtensionType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:8:0x0020
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.google.protobuf.Extension$ExtensionType[] r0 = com.google.protobuf.Extension.ExtensionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.protobuf.ExtensionRegistry.C00081.$SwitchMap$com$google$protobuf$Extension$ExtensionType = r0
                int[] r0 = com.google.protobuf.ExtensionRegistry.C00081.$SwitchMap$com$google$protobuf$Extension$ExtensionType     // Catch: java.lang.NoSuchFieldError -> L20
                com.google.protobuf.Extension$ExtensionType r1 = com.google.protobuf.Extension.ExtensionType.IMMUTABLE     // Catch: java.lang.NoSuchFieldError -> L20
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L20
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L20
            L14:
                int[] r0 = com.google.protobuf.ExtensionRegistry.C00081.$SwitchMap$com$google$protobuf$Extension$ExtensionType     // Catch: java.lang.NoSuchFieldError -> L20 java.lang.NoSuchFieldError -> L24
                com.google.protobuf.Extension$ExtensionType r1 = com.google.protobuf.Extension.ExtensionType.MUTABLE     // Catch: java.lang.NoSuchFieldError -> L24
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L24
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L24
                return
            L20:
                r4 = move-exception
                goto L14
            L24:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ExtensionRegistry.C00081.m3018clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/ExtensionRegistry$DescriptorIntPair.class */
    public static final class DescriptorIntPair {
        private final Descriptors.Descriptor descriptor;
        private final int number;

        DescriptorIntPair(Descriptors.Descriptor descriptor, int i) {
            this.descriptor = descriptor;
            this.number = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DescriptorIntPair)) {
                return false;
            }
            DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
            boolean z = false;
            if (this.descriptor == descriptorIntPair.descriptor) {
                z = false;
                if (this.number == descriptorIntPair.number) {
                    z = true;
                }
            }
            return z;
        }

        public int hashCode() {
            return (this.descriptor.hashCode() * 65535) + this.number;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/google/protobuf/ExtensionRegistry$ExtensionInfo.class */
    public static final class ExtensionInfo {
        public final Message defaultInstance;
        public final Descriptors.FieldDescriptor descriptor;

        private ExtensionInfo(Descriptors.FieldDescriptor fieldDescriptor) {
            this.descriptor = fieldDescriptor;
            this.defaultInstance = null;
        }

        private ExtensionInfo(Descriptors.FieldDescriptor fieldDescriptor, Message message) {
            this.descriptor = fieldDescriptor;
            this.defaultInstance = message;
        }

        /* synthetic */ ExtensionInfo(Descriptors.FieldDescriptor fieldDescriptor, Message message, C00081 c00081) {
            this(fieldDescriptor, message);
        }
    }

    private ExtensionRegistry() {
        this.immutableExtensionsByName = new HashMap();
        this.mutableExtensionsByName = new HashMap();
        this.immutableExtensionsByNumber = new HashMap();
        this.mutableExtensionsByNumber = new HashMap();
    }

    private ExtensionRegistry(ExtensionRegistry extensionRegistry) {
        super(extensionRegistry);
        this.immutableExtensionsByName = Collections.unmodifiableMap(extensionRegistry.immutableExtensionsByName);
        this.mutableExtensionsByName = Collections.unmodifiableMap(extensionRegistry.mutableExtensionsByName);
        this.immutableExtensionsByNumber = Collections.unmodifiableMap(extensionRegistry.immutableExtensionsByNumber);
        this.mutableExtensionsByNumber = Collections.unmodifiableMap(extensionRegistry.mutableExtensionsByNumber);
    }

    ExtensionRegistry(boolean z) {
        super(EMPTY_REGISTRY_LITE);
        this.immutableExtensionsByName = Collections.emptyMap();
        this.mutableExtensionsByName = Collections.emptyMap();
        this.immutableExtensionsByNumber = Collections.emptyMap();
        this.mutableExtensionsByNumber = Collections.emptyMap();
    }

    private void add(ExtensionInfo extensionInfo, Extension.ExtensionType extensionType) {
        Map<String, ExtensionInfo> map;
        Map<DescriptorIntPair, ExtensionInfo> map2;
        if (!extensionInfo.descriptor.isExtension()) {
            throw new IllegalArgumentException("ExtensionRegistry.add() was given a FieldDescriptor for a regular (non-extension) field.");
        }
        int i = C00081.$SwitchMap$com$google$protobuf$Extension$ExtensionType[extensionType.ordinal()];
        if (i == 1) {
            map = this.immutableExtensionsByName;
            map2 = this.immutableExtensionsByNumber;
        } else {
            if (i != 2) {
                return;
            }
            map = this.mutableExtensionsByName;
            map2 = this.mutableExtensionsByNumber;
        }
        map.put(extensionInfo.descriptor.getFullName(), extensionInfo);
        map2.put(new DescriptorIntPair(extensionInfo.descriptor.getContainingType(), extensionInfo.descriptor.getNumber()), extensionInfo);
        Descriptors.FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
        if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
            map.put(fieldDescriptor.getMessageType().getFullName(), extensionInfo);
        }
    }

    public static ExtensionRegistry getEmptyRegistry() {
        return EMPTY_REGISTRY;
    }

    static ExtensionInfo newExtensionInfo(Extension<?, ?> extension) {
        if (extension.getDescriptor().getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            return new ExtensionInfo(extension.getDescriptor(), null, null);
        }
        if (extension.getMessageDefaultInstance() != null) {
            return new ExtensionInfo(extension.getDescriptor(), (Message) extension.getMessageDefaultInstance(), null);
        }
        throw new IllegalStateException("Registered message-type extension had null default instance: " + extension.getDescriptor().getFullName());
    }

    public static ExtensionRegistry newInstance() {
        return new ExtensionRegistry();
    }

    public void add(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            throw new IllegalArgumentException("ExtensionRegistry.add() must be provided a default instance when adding an embedded message extension.");
        }
        ExtensionInfo extensionInfo = new ExtensionInfo(fieldDescriptor, null, null);
        add(extensionInfo, Extension.ExtensionType.IMMUTABLE);
        add(extensionInfo, Extension.ExtensionType.MUTABLE);
    }

    public void add(Descriptors.FieldDescriptor fieldDescriptor, Message message) {
        if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            throw new IllegalArgumentException("ExtensionRegistry.add() provided a default instance for a non-message extension.");
        }
        add(new ExtensionInfo(fieldDescriptor, message, null), Extension.ExtensionType.IMMUTABLE);
    }

    public void add(Extension<?, ?> extension) {
        if (extension.getExtensionType() == Extension.ExtensionType.IMMUTABLE || extension.getExtensionType() == Extension.ExtensionType.MUTABLE) {
            add(newExtensionInfo(extension), extension.getExtensionType());
        }
    }

    public void add(GeneratedMessage.GeneratedExtension<?, ?> generatedExtension) {
        add((Extension<?, ?>) generatedExtension);
    }

    public ExtensionInfo findExtensionByName(String str) {
        return findImmutableExtensionByName(str);
    }

    public ExtensionInfo findExtensionByNumber(Descriptors.Descriptor descriptor, int i) {
        return findImmutableExtensionByNumber(descriptor, i);
    }

    public ExtensionInfo findImmutableExtensionByName(String str) {
        return this.immutableExtensionsByName.get(str);
    }

    public ExtensionInfo findImmutableExtensionByNumber(Descriptors.Descriptor descriptor, int i) {
        return this.immutableExtensionsByNumber.get(new DescriptorIntPair(descriptor, i));
    }

    public ExtensionInfo findMutableExtensionByName(String str) {
        return this.mutableExtensionsByName.get(str);
    }

    public ExtensionInfo findMutableExtensionByNumber(Descriptors.Descriptor descriptor, int i) {
        return this.mutableExtensionsByNumber.get(new DescriptorIntPair(descriptor, i));
    }

    public Set<ExtensionInfo> getAllImmutableExtensionsByExtendedType(String str) {
        HashSet hashSet = new HashSet();
        for (DescriptorIntPair descriptorIntPair : this.immutableExtensionsByNumber.keySet()) {
            if (descriptorIntPair.descriptor.getFullName().equals(str)) {
                hashSet.add(this.immutableExtensionsByNumber.get(descriptorIntPair));
            }
        }
        return hashSet;
    }

    public Set<ExtensionInfo> getAllMutableExtensionsByExtendedType(String str) {
        HashSet hashSet = new HashSet();
        for (DescriptorIntPair descriptorIntPair : this.mutableExtensionsByNumber.keySet()) {
            if (descriptorIntPair.descriptor.getFullName().equals(str)) {
                hashSet.add(this.mutableExtensionsByNumber.get(descriptorIntPair));
            }
        }
        return hashSet;
    }

    @Override // com.google.protobuf.ExtensionRegistryLite
    public ExtensionRegistry getUnmodifiable() {
        return new ExtensionRegistry(this);
    }
}
