package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.gx */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gx.class */
public class C0670gx {

    /* renamed from: a */
    private int f1171a;

    /* renamed from: a */
    private String f1172a;

    /* renamed from: a */
    private List<C0663gq> f1173a;

    /* renamed from: b */
    private String f1174b;

    /* renamed from: c */
    private String f1175c;

    /* renamed from: d */
    private String f1176d;

    /* renamed from: com.xiaomi.push.gx$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gx$a.class */
    public static class a {

        /* renamed from: a */
        public static final a f1177a = new a("internal-server-error");

        /* renamed from: b */
        public static final a f1178b = new a("forbidden");

        /* renamed from: c */
        public static final a f1179c = new a("bad-request");

        /* renamed from: d */
        public static final a f1180d = new a("conflict");

        /* renamed from: e */
        public static final a f1181e = new a("feature-not-implemented");

        /* renamed from: f */
        public static final a f1182f = new a("gone");

        /* renamed from: g */
        public static final a f1183g = new a("item-not-found");

        /* renamed from: h */
        public static final a f1184h = new a("jid-malformed");

        /* renamed from: i */
        public static final a f1185i = new a("not-acceptable");

        /* renamed from: j */
        public static final a f1186j = new a("not-allowed");

        /* renamed from: k */
        public static final a f1187k = new a("not-authorized");

        /* renamed from: l */
        public static final a f1188l = new a("payment-required");

        /* renamed from: m */
        public static final a f1189m = new a("recipient-unavailable");

        /* renamed from: n */
        public static final a f1190n = new a("redirect");

        /* renamed from: o */
        public static final a f1191o = new a("registration-required");

        /* renamed from: p */
        public static final a f1192p = new a("remote-server-error");

        /* renamed from: q */
        public static final a f1193q = new a("remote-server-not-found");

        /* renamed from: r */
        public static final a f1194r = new a("remote-server-timeout");

        /* renamed from: s */
        public static final a f1195s = new a("resource-constraint");

        /* renamed from: t */
        public static final a f1196t = new a("service-unavailable");

        /* renamed from: u */
        public static final a f1197u = new a("subscription-required");

        /* renamed from: v */
        public static final a f1198v = new a("undefined-condition");

        /* renamed from: w */
        public static final a f1199w = new a("unexpected-request");

        /* renamed from: x */
        public static final a f1200x = new a("request-timeout");

        /* renamed from: a */
        private String f1201a;

        public a(String str) {
            this.f1201a = str;
        }

        public String toString() {
            return this.f1201a;
        }
    }

    public C0670gx(int i, String str, String str2, String str3, String str4, List<C0663gq> list) {
        this.f1173a = null;
        this.f1171a = i;
        this.f1172a = str;
        this.f1175c = str2;
        this.f1174b = str3;
        this.f1176d = str4;
        this.f1173a = list;
    }

    public C0670gx(Bundle bundle) {
        this.f1173a = null;
        this.f1171a = bundle.getInt("ext_err_code");
        if (bundle.containsKey("ext_err_type")) {
            this.f1172a = bundle.getString("ext_err_type");
        }
        this.f1174b = bundle.getString("ext_err_cond");
        this.f1175c = bundle.getString("ext_err_reason");
        this.f1176d = bundle.getString("ext_err_msg");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray == null) {
            return;
        }
        this.f1173a = new ArrayList(parcelableArray.length);
        int length = parcelableArray.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            C0663gq m1443a = C0663gq.m1443a((Bundle) parcelableArray[i2]);
            if (m1443a != null) {
                this.f1173a.add(m1443a);
            }
            i = i2 + 1;
        }
    }

    public C0670gx(a aVar) {
        this.f1173a = null;
        m1507a(aVar);
        this.f1176d = null;
    }

    /* renamed from: a */
    private void m1507a(a aVar) {
        this.f1174b = aVar.f1201a;
    }

    /* renamed from: a */
    public Bundle m1508a() {
        Bundle bundle = new Bundle();
        String str = this.f1172a;
        if (str != null) {
            bundle.putString("ext_err_type", str);
        }
        bundle.putInt("ext_err_code", this.f1171a);
        String str2 = this.f1175c;
        if (str2 != null) {
            bundle.putString("ext_err_reason", str2);
        }
        String str3 = this.f1174b;
        if (str3 != null) {
            bundle.putString("ext_err_cond", str3);
        }
        String str4 = this.f1176d;
        if (str4 != null) {
            bundle.putString("ext_err_msg", str4);
        }
        List<C0663gq> list = this.f1173a;
        if (list != null) {
            Bundle[] bundleArr = new Bundle[list.size()];
            int i = 0;
            Iterator<C0663gq> it = this.f1173a.iterator();
            while (it.hasNext()) {
                Bundle m1446a = it.next().m1446a();
                if (m1446a != null) {
                    bundleArr[i] = m1446a;
                    i++;
                }
            }
            bundle.putParcelableArray("ext_exts", bundleArr);
        }
        return bundle;
    }

    /* renamed from: a */
    public String m1509a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<error code=\"");
        sb.append(this.f1171a);
        sb.append("\"");
        if (this.f1172a != null) {
            sb.append(" type=\"");
            sb.append(this.f1172a);
            sb.append("\"");
        }
        if (this.f1175c != null) {
            sb.append(" reason=\"");
            sb.append(this.f1175c);
            sb.append("\"");
        }
        sb.append(">");
        if (this.f1174b != null) {
            sb.append("<");
            sb.append(this.f1174b);
            sb.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.f1176d != null) {
            sb.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            sb.append(this.f1176d);
            sb.append("</text>");
        }
        Iterator<C0663gq> it = m1510a().iterator();
        while (it.hasNext()) {
            sb.append(it.next().mo1453d());
        }
        sb.append("</error>");
        return sb.toString();
    }

    /* renamed from: a */
    public List<C0663gq> m1510a() {
        synchronized (this) {
            if (this.f1173a == null) {
                return Collections.emptyList();
            }
            return Collections.unmodifiableList(this.f1173a);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f1174b;
        if (str != null) {
            sb.append(str);
        }
        sb.append("(");
        sb.append(this.f1171a);
        sb.append(")");
        if (this.f1176d != null) {
            sb.append(" ");
            sb.append(this.f1176d);
        }
        return sb.toString();
    }
}
