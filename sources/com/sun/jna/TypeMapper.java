package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/TypeMapper.class */
public interface TypeMapper {
    FromNativeConverter getFromNativeConverter(Class<?> cls);

    ToNativeConverter getToNativeConverter(Class<?> cls);
}
