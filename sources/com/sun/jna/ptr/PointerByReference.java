package com.sun.jna.ptr;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ptr/PointerByReference.class */
public class PointerByReference extends ByReference {
    public PointerByReference() {
        this(null);
    }

    public PointerByReference(Pointer pointer) {
        super(Native.POINTER_SIZE);
        setValue(pointer);
    }

    public Pointer getValue() {
        return getPointer().getPointer(0L);
    }

    public void setValue(Pointer pointer) {
        getPointer().setPointer(0L, pointer);
    }
}
