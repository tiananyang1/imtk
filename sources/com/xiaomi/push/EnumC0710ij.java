package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ij */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ij.class */
public enum EnumC0710ij {
    Circle(0),
    Polygon(1);


    /* renamed from: a */
    private final int f1676a;

    EnumC0710ij(int i) {
        this.f1676a = i;
    }

    /* renamed from: a */
    public static EnumC0710ij m1735a(int i) {
        if (i == 0) {
            return Circle;
        }
        if (i != 1) {
            return null;
        }
        return Polygon;
    }

    /* renamed from: a */
    public int m1736a() {
        return this.f1676a;
    }
}
