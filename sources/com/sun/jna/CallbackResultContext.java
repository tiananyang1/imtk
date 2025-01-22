package com.sun.jna;

import java.lang.reflect.Method;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/CallbackResultContext.class */
public class CallbackResultContext extends ToNativeContext {
    private Method method;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallbackResultContext(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return this.method;
    }
}
