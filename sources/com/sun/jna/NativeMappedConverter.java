package com.sun.jna;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/NativeMappedConverter.class */
public class NativeMappedConverter implements TypeConverter {
    private static final Map<Class<?>, Reference<NativeMappedConverter>> converters = new WeakHashMap();
    private final NativeMapped instance;
    private final Class<?> nativeType;
    private final Class<?> type;

    public NativeMappedConverter(Class<?> cls) {
        if (NativeMapped.class.isAssignableFrom(cls)) {
            this.type = cls;
            this.instance = defaultValue();
            this.nativeType = this.instance.nativeType();
        } else {
            throw new IllegalArgumentException("Type must derive from " + NativeMapped.class);
        }
    }

    public static NativeMappedConverter getInstance(Class<?> cls) {
        NativeMappedConverter nativeMappedConverter;
        synchronized (converters) {
            Reference<NativeMappedConverter> reference = converters.get(cls);
            NativeMappedConverter nativeMappedConverter2 = reference != null ? reference.get() : null;
            nativeMappedConverter = nativeMappedConverter2;
            if (nativeMappedConverter2 == null) {
                nativeMappedConverter = new NativeMappedConverter(cls);
                converters.put(cls, new SoftReference(nativeMappedConverter));
            }
        }
        return nativeMappedConverter;
    }

    public NativeMapped defaultValue() {
        return this.type.isEnum() ? (NativeMapped) this.type.getEnumConstants()[0] : (NativeMapped) Klass.newInstance(this.type);
    }

    @Override // com.sun.jna.FromNativeConverter
    public Object fromNative(Object obj, FromNativeContext fromNativeContext) {
        return this.instance.fromNative(obj, fromNativeContext);
    }

    @Override // com.sun.jna.FromNativeConverter, com.sun.jna.ToNativeConverter
    public Class<?> nativeType() {
        return this.nativeType;
    }

    @Override // com.sun.jna.ToNativeConverter
    public Object toNative(Object obj, ToNativeContext toNativeContext) {
        Object obj2 = obj;
        if (obj == null) {
            if (Pointer.class.isAssignableFrom(this.nativeType)) {
                return null;
            }
            obj2 = defaultValue();
        }
        return ((NativeMapped) obj2).toNative();
    }
}
