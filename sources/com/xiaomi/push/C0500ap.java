package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ap */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ap.class */
public class C0500ap implements InterfaceC0502ar {

    /* renamed from: a */
    private final String f438a;

    /* renamed from: b */
    private final String f439b;

    public C0500ap(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.f438a = str;
        this.f439b = str2;
    }

    @Override // com.xiaomi.push.InterfaceC0502ar
    /* renamed from: a */
    public String mo466a() {
        return this.f438a;
    }

    @Override // com.xiaomi.push.InterfaceC0502ar
    /* renamed from: b */
    public String mo467b() {
        return this.f439b;
    }
}
