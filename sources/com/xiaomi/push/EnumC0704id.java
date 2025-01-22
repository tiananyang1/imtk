package com.xiaomi.push;

/* renamed from: com.xiaomi.push.id */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/id.class */
public enum EnumC0704id {
    MISC_CONFIG(1),
    PLUGIN_CONFIG(2);


    /* renamed from: a */
    private final int f1619a;

    EnumC0704id(int i) {
        this.f1619a = i;
    }

    /* renamed from: a */
    public static EnumC0704id m1670a(int i) {
        if (i == 1) {
            return MISC_CONFIG;
        }
        if (i != 2) {
            return null;
        }
        return PLUGIN_CONFIG;
    }

    /* renamed from: a */
    public int m1671a() {
        return this.f1619a;
    }
}
