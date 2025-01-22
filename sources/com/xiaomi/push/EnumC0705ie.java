package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ie */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ie.class */
public enum EnumC0705ie {
    INT(1),
    LONG(2),
    STRING(3),
    BOOLEAN(4);


    /* renamed from: a */
    private final int f1625a;

    EnumC0705ie(int i) {
        this.f1625a = i;
    }

    /* renamed from: a */
    public static EnumC0705ie m1672a(int i) {
        if (i == 1) {
            return INT;
        }
        if (i == 2) {
            return LONG;
        }
        if (i == 3) {
            return STRING;
        }
        if (i != 4) {
            return null;
        }
        return BOOLEAN;
    }
}
