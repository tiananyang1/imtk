package com.xiaomi.push;

import java.util.Map;

/* renamed from: com.xiaomi.push.gd */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gd.class */
public class C0650gd implements Cloneable {

    /* renamed from: a */
    public static String f1076a = "wcc-ml-test10.bj";

    /* renamed from: b */
    public static final String f1077b = C0489ae.f412a;

    /* renamed from: c */
    public static String f1078c = null;

    /* renamed from: a */
    private int f1079a;

    /* renamed from: a */
    private InterfaceC0653gg f1080a;

    /* renamed from: a */
    private boolean f1081a = AbstractC0649gc.f1056a;

    /* renamed from: b */
    private boolean f1082b = true;

    /* renamed from: d */
    private String f1083d;

    /* renamed from: e */
    private String f1084e;

    /* renamed from: f */
    private String f1085f;

    public C0650gd(Map<String, Integer> map, int i, String str, InterfaceC0653gg interfaceC0653gg) {
        m1413a(map, i, str, interfaceC0653gg);
    }

    /* renamed from: a */
    public static final String m1411a() {
        String str = f1078c;
        return str != null ? str : C0486ab.m426a() ? "sandbox.xmpush.xiaomi.com" : C0486ab.m427b() ? f1077b : "app.chat.xiaomi.net";
    }

    /* renamed from: a */
    public static final void m1412a(String str) {
        f1078c = str;
    }

    /* renamed from: a */
    private void m1413a(Map<String, Integer> map, int i, String str, InterfaceC0653gg interfaceC0653gg) {
        this.f1079a = i;
        this.f1083d = str;
        this.f1080a = interfaceC0653gg;
    }

    /* renamed from: a */
    public int m1414a() {
        return this.f1079a;
    }

    /* renamed from: a */
    public void m1415a(boolean z) {
        this.f1081a = z;
    }

    /* renamed from: a */
    public boolean m1416a() {
        return this.f1081a;
    }

    /* renamed from: a */
    public byte[] mo1417a() {
        return null;
    }

    /* renamed from: b */
    public String m1418b() {
        return this.f1085f;
    }

    /* renamed from: b */
    public void m1419b(String str) {
        this.f1085f = str;
    }

    /* renamed from: c */
    public String m1420c() {
        if (this.f1084e == null) {
            this.f1084e = m1411a();
        }
        return this.f1084e;
    }

    /* renamed from: c */
    public void m1421c(String str) {
        this.f1084e = str;
    }
}
