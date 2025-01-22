package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/FunctionParameterContext.class */
public class FunctionParameterContext extends ToNativeContext {
    private Object[] args;
    private Function function;
    private int index;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FunctionParameterContext(Function function, Object[] objArr, int i) {
        this.function = function;
        this.args = objArr;
        this.index = i;
    }

    public Function getFunction() {
        return this.function;
    }

    public int getParameterIndex() {
        return this.index;
    }

    public Object[] getParameters() {
        return this.args;
    }
}
