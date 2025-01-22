package com.sun.jna.ptr;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ptr/DoubleByReference.class */
public class DoubleByReference extends ByReference {
    public DoubleByReference() {
        this(0.0d);
    }

    public DoubleByReference(double d) {
        super(8);
        setValue(d);
    }

    public double getValue() {
        return getPointer().getDouble(0L);
    }

    public void setValue(double d) {
        getPointer().setDouble(0L, d);
    }
}
