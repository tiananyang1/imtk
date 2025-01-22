package com.sun.jna.ptr;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ptr/FloatByReference.class */
public class FloatByReference extends ByReference {
    public FloatByReference() {
        this(0.0f);
    }

    public FloatByReference(float f) {
        super(4);
        setValue(f);
    }

    public float getValue() {
        return getPointer().getFloat(0L);
    }

    public void setValue(float f) {
        getPointer().setFloat(0L, f);
    }
}
