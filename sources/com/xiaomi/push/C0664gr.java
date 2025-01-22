package com.xiaomi.push;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.gr */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gr.class */
public class C0664gr extends AbstractC0666gt {

    /* renamed from: a */
    private a f1116a;

    /* renamed from: a */
    private final Map<String, String> f1117a;

    /* renamed from: com.xiaomi.push.gr$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gr$a.class */
    public static class a {

        /* renamed from: a */
        public static final a f1118a = new a("get");

        /* renamed from: b */
        public static final a f1119b = new a("set");

        /* renamed from: c */
        public static final a f1120c = new a("result");

        /* renamed from: d */
        public static final a f1121d = new a("error");

        /* renamed from: e */
        public static final a f1122e = new a("command");

        /* renamed from: a */
        private String f1123a;

        private a(String str) {
            this.f1123a = str;
        }

        /* renamed from: a */
        public static a m1460a(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            if (f1118a.toString().equals(lowerCase)) {
                return f1118a;
            }
            if (f1119b.toString().equals(lowerCase)) {
                return f1119b;
            }
            if (f1121d.toString().equals(lowerCase)) {
                return f1121d;
            }
            if (f1120c.toString().equals(lowerCase)) {
                return f1120c;
            }
            if (f1122e.toString().equals(lowerCase)) {
                return f1122e;
            }
            return null;
        }

        public String toString() {
            return this.f1123a;
        }
    }

    public C0664gr() {
        this.f1116a = a.f1118a;
        this.f1117a = new HashMap();
    }

    public C0664gr(Bundle bundle) {
        super(bundle);
        this.f1116a = a.f1118a;
        this.f1117a = new HashMap();
        if (bundle.containsKey("ext_iq_type")) {
            this.f1116a = a.m1460a(bundle.getString("ext_iq_type"));
        }
    }

    @Override // com.xiaomi.push.AbstractC0666gt
    /* renamed from: a */
    public Bundle mo1454a() {
        Bundle mo1454a = super.mo1454a();
        a aVar = this.f1116a;
        if (aVar != null) {
            mo1454a.putString("ext_iq_type", aVar.toString());
        }
        return mo1454a;
    }

    /* renamed from: a */
    public a m1455a() {
        return this.f1116a;
    }

    @Override // com.xiaomi.push.AbstractC0666gt
    /* renamed from: a */
    public String mo1456a() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("<iq ");
        if (m1491j() != null) {
            sb.append("id=\"" + m1491j() + "\" ");
        }
        if (m1494l() != null) {
            sb.append("to=\"");
            sb.append(C0678he.m1528a(m1494l()));
            sb.append("\" ");
        }
        if (m1496m() != null) {
            sb.append("from=\"");
            sb.append(C0678he.m1528a(m1496m()));
            sb.append("\" ");
        }
        if (m1492k() != null) {
            sb.append("chid=\"");
            sb.append(C0678he.m1528a(m1492k()));
            sb.append("\" ");
        }
        for (Map.Entry<String, String> entry : this.f1117a.entrySet()) {
            sb.append(C0678he.m1528a(entry.getKey()));
            sb.append("=\"");
            sb.append(C0678he.m1528a(entry.getValue()));
            sb.append("\" ");
        }
        if (this.f1116a == null) {
            str = "type=\"get\">";
        } else {
            sb.append("type=\"");
            sb.append(m1455a());
            str = "\">";
        }
        sb.append(str);
        String mo1459b = mo1459b();
        if (mo1459b != null) {
            sb.append(mo1459b);
        }
        sb.append(m1500o());
        C0670gx a2 = mo1454a();
        if (a2 != null) {
            sb.append(a2.m1509a());
        }
        sb.append("</iq>");
        return sb.toString();
    }

    /* renamed from: a */
    public void m1457a(a aVar) {
        a aVar2 = aVar;
        if (aVar == null) {
            aVar2 = a.f1118a;
        }
        this.f1116a = aVar2;
    }

    /* renamed from: a */
    public void m1458a(Map<String, String> map) {
        synchronized (this) {
            this.f1117a.putAll(map);
        }
    }

    /* renamed from: b */
    public String mo1459b() {
        return null;
    }
}
