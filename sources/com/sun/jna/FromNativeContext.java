package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/FromNativeContext.class */
public class FromNativeContext {
    private Class<?> type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FromNativeContext(Class<?> cls) {
        this.type = cls;
    }

    public Class<?> getTargetType() {
        return this.type;
    }
}
