package com.sun.jna.ptr;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ptr/ShortByReference.class */
public class ShortByReference extends ByReference {
    public ShortByReference() {
        this((short) 0);
    }

    public ShortByReference(short s) {
        super(2);
        setValue(s);
    }

    public short getValue() {
        return getPointer().getShort(0L);
    }

    public void setValue(short s) {
        getPointer().setShort(0L, s);
    }
}
