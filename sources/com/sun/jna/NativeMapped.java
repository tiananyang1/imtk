package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/NativeMapped.class */
public interface NativeMapped {
    Object fromNative(Object obj, FromNativeContext fromNativeContext);

    Class<?> nativeType();

    Object toNative();
}
