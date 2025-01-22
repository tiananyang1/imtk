package com.sun.jna;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/InvocationMapper.class */
public interface InvocationMapper {
    InvocationHandler getInvocationHandler(NativeLibrary nativeLibrary, Method method);
}
