package com.sun.jna.ptr;

import com.sun.jna.NativeLong;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ptr/NativeLongByReference.class */
public class NativeLongByReference extends ByReference {
    public NativeLongByReference() {
        this(new NativeLong(0L));
    }

    public NativeLongByReference(NativeLong nativeLong) {
        super(NativeLong.SIZE);
        setValue(nativeLong);
    }

    public NativeLong getValue() {
        return getPointer().getNativeLong(0L);
    }

    public void setValue(NativeLong nativeLong) {
        getPointer().setNativeLong(0L, nativeLong);
    }
}
