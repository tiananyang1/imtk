package com.sun.jna.ptr;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ptr/ByteByReference.class */
public class ByteByReference extends ByReference {
    public ByteByReference() {
        this((byte) 0);
    }

    public ByteByReference(byte b) {
        super(1);
        setValue(b);
    }

    public byte getValue() {
        return getPointer().getByte(0L);
    }

    public void setValue(byte b) {
        getPointer().setByte(0L, b);
    }
}
