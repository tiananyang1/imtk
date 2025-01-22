package com.xiaomi.push;

/* renamed from: com.xiaomi.push.jy */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jy.class */
public class C0752jy {

    /* renamed from: a */
    public final byte f2324a;

    /* renamed from: a */
    public final String f2325a;

    /* renamed from: a */
    public final short f2326a;

    public C0752jy() {
        this("", (byte) 0, (short) 0);
    }

    public C0752jy(String str, byte b, short s) {
        this.f2325a = str;
        this.f2324a = b;
        this.f2326a = s;
    }

    public String toString() {
        return "<TField name:'" + this.f2325a + "' type:" + ((int) this.f2324a) + " field-id:" + ((int) this.f2326a) + ">";
    }
}
