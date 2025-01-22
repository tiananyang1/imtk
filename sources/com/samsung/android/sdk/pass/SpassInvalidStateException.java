package com.samsung.android.sdk.pass;

/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/SpassInvalidStateException.class */
public class SpassInvalidStateException extends IllegalStateException {
    public static final int STATUS_OPERATION_DENIED = 1;

    /* renamed from: a */
    private int f166a;

    public SpassInvalidStateException(String str, int i) {
        super(str);
        this.f166a = 0;
        this.f166a = i;
    }

    public int getType() {
        return this.f166a;
    }
}
