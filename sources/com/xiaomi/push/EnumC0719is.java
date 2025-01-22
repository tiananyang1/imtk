package com.xiaomi.push;

/* renamed from: com.xiaomi.push.is */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/is.class */
public enum EnumC0719is {
    RegIdExpired(0),
    PackageUnregistered(1),
    Init(2);


    /* renamed from: a */
    private final int f1842a;

    EnumC0719is(int i) {
        this.f1842a = i;
    }

    /* renamed from: a */
    public static EnumC0719is m1867a(int i) {
        if (i == 0) {
            return RegIdExpired;
        }
        if (i == 1) {
            return PackageUnregistered;
        }
        if (i != 2) {
            return null;
        }
        return Init;
    }

    /* renamed from: a */
    public int m1868a() {
        return this.f1842a;
    }
}
