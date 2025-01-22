package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/FromNativeConverter.class */
public interface FromNativeConverter {
    Object fromNative(Object obj, FromNativeContext fromNativeContext);

    Class<?> nativeType();
}
