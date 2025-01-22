package com.sun.jna;

import com.sun.jna.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/StringArray.class */
public class StringArray extends Memory implements Function.PostCallRead {
    private String encoding;
    private List<NativeString> natives;
    private Object[] original;

    public StringArray(WString[] wStringArr) {
        this(wStringArr, "--WIDE-STRING--");
    }

    private StringArray(Object[] objArr, String str) {
        super((objArr.length + 1) * Native.POINTER_SIZE);
        this.natives = new ArrayList();
        this.original = objArr;
        this.encoding = str;
        int i = 0;
        while (true) {
            int i2 = i;
            Pointer pointer = null;
            if (i2 >= objArr.length) {
                setPointer(Native.POINTER_SIZE * objArr.length, null);
                return;
            }
            if (objArr[i2] != null) {
                NativeString nativeString = new NativeString(objArr[i2].toString(), str);
                this.natives.add(nativeString);
                pointer = nativeString.getPointer();
            }
            setPointer(Native.POINTER_SIZE * i2, pointer);
            i = i2 + 1;
        }
    }

    public StringArray(String[] strArr) {
        this(strArr, false);
    }

    public StringArray(String[] strArr, String str) {
        this((Object[]) strArr, str);
    }

    public StringArray(String[] strArr, boolean z) {
        this((Object[]) strArr, z ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }

    @Override // com.sun.jna.Function.PostCallRead
    public void read() {
        boolean z = this.original instanceof WString[];
        boolean equals = "--WIDE-STRING--".equals(this.encoding);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.original.length) {
                return;
            }
            Pointer pointer = getPointer(Native.POINTER_SIZE * i2);
            CharSequence charSequence = null;
            if (pointer != null) {
                CharSequence wideString = equals ? pointer.getWideString(0L) : pointer.getString(0L, this.encoding);
                charSequence = wideString;
                if (z) {
                    charSequence = new WString((String) wideString);
                }
            }
            this.original[i2] = charSequence;
            i = i2 + 1;
        }
    }

    @Override // com.sun.jna.Memory, com.sun.jna.Pointer
    public String toString() {
        return ("--WIDE-STRING--".equals(this.encoding) ? "const wchar_t*[]" : "const char*[]") + Arrays.asList(this.original);
    }
}
