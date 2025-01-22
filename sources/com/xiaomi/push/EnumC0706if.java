package com.xiaomi.push;

/* renamed from: com.xiaomi.push.if */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/if.class */
public enum EnumC0706if {
    Baidu(0),
    Tencent(1),
    AutoNavi(2),
    Google(3),
    GPS(4);


    /* renamed from: a */
    private final int f1632a;

    EnumC0706if(int i) {
        this.f1632a = i;
    }

    /* renamed from: a */
    public static EnumC0706if m1673a(int i) {
        if (i == 0) {
            return Baidu;
        }
        if (i == 1) {
            return Tencent;
        }
        if (i == 2) {
            return AutoNavi;
        }
        if (i == 3) {
            return Google;
        }
        if (i != 4) {
            return null;
        }
        return GPS;
    }

    /* renamed from: a */
    public int m1674a() {
        return this.f1632a;
    }
}
