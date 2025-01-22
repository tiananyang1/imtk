package com.sun.jna;

import java.lang.reflect.Method;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/MethodParameterContext.class */
public class MethodParameterContext extends FunctionParameterContext {
    private Method method;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodParameterContext(Function function, Object[] objArr, int i, Method method) {
        super(function, objArr, i);
        this.method = method;
    }

    public Method getMethod() {
        return this.method;
    }
}
