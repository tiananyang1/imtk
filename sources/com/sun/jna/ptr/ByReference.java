package com.sun.jna.ptr;

import com.sun.jna.Memory;
import com.sun.jna.PointerType;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ptr/ByReference.class */
public abstract class ByReference extends PointerType {
    /* JADX INFO: Access modifiers changed from: protected */
    public ByReference(int i) {
        setPointer(new Memory(i));
    }
}
