package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ToNativeConverter.class */
public interface ToNativeConverter {
    Class<?> nativeType();

    Object toNative(Object obj, ToNativeContext toNativeContext);
}
