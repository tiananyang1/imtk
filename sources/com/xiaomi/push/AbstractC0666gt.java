package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.xiaomi.push.gt */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gt.class */
public abstract class AbstractC0666gt {

    /* renamed from: a */
    private static long f1137a;

    /* renamed from: c */
    private static String f1141c;

    /* renamed from: a */
    private C0670gx f1142a;

    /* renamed from: a */
    private List<C0663gq> f1143a;

    /* renamed from: a */
    private final Map<String, Object> f1144a;

    /* renamed from: d */
    private String f1145d;

    /* renamed from: e */
    private String f1146e;

    /* renamed from: f */
    private String f1147f;

    /* renamed from: g */
    private String f1148g;

    /* renamed from: h */
    private String f1149h;

    /* renamed from: i */
    private String f1150i;

    /* renamed from: a */
    protected static final String f1138a = Locale.getDefault().getLanguage().toLowerCase();

    /* renamed from: b */
    private static String f1140b = null;

    /* renamed from: a */
    public static final DateFormat f1139a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    static {
        f1139a.setTimeZone(TimeZone.getTimeZone("UTC"));
        f1141c = C0678he.m1527a(5) + Constants.ACCEPT_TIME_SEPARATOR_SERVER;
        f1137a = 0L;
    }

    public AbstractC0666gt() {
        this.f1145d = f1140b;
        this.f1146e = null;
        this.f1147f = null;
        this.f1148g = null;
        this.f1149h = null;
        this.f1150i = null;
        this.f1143a = new CopyOnWriteArrayList();
        this.f1144a = new HashMap();
        this.f1142a = null;
    }

    public AbstractC0666gt(Bundle bundle) {
        this.f1145d = f1140b;
        this.f1146e = null;
        this.f1147f = null;
        this.f1148g = null;
        this.f1149h = null;
        this.f1150i = null;
        this.f1143a = new CopyOnWriteArrayList();
        this.f1144a = new HashMap();
        this.f1142a = null;
        this.f1147f = bundle.getString("ext_to");
        this.f1148g = bundle.getString("ext_from");
        this.f1149h = bundle.getString("ext_chid");
        this.f1146e = bundle.getString("ext_pkt_id");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f1143a = new ArrayList(parcelableArray.length);
            int length = parcelableArray.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                C0663gq m1443a = C0663gq.m1443a((Bundle) parcelableArray[i2]);
                if (m1443a != null) {
                    this.f1143a.add(m1443a);
                }
                i = i2 + 1;
            }
        }
        Bundle bundle2 = bundle.getBundle("ext_ERROR");
        if (bundle2 != null) {
            this.f1142a = new C0670gx(bundle2);
        }
    }

    /* renamed from: i */
    public static String m1481i() {
        String sb;
        synchronized (AbstractC0666gt.class) {
            try {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(f1141c);
                long j = f1137a;
                f1137a = 1 + j;
                sb2.append(Long.toString(j));
                sb = sb2.toString();
            } catch (Throwable th) {
                throw th;
            }
        }
        return sb;
    }

    /* renamed from: q */
    public static String m1482q() {
        return f1138a;
    }

    /* renamed from: a */
    public Bundle mo1454a() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.f1145d)) {
            bundle.putString("ext_ns", this.f1145d);
        }
        if (!TextUtils.isEmpty(this.f1148g)) {
            bundle.putString("ext_from", this.f1148g);
        }
        if (!TextUtils.isEmpty(this.f1147f)) {
            bundle.putString("ext_to", this.f1147f);
        }
        if (!TextUtils.isEmpty(this.f1146e)) {
            bundle.putString("ext_pkt_id", this.f1146e);
        }
        if (!TextUtils.isEmpty(this.f1149h)) {
            bundle.putString("ext_chid", this.f1149h);
        }
        C0670gx c0670gx = this.f1142a;
        if (c0670gx != null) {
            bundle.putBundle("ext_ERROR", c0670gx.m1508a());
        }
        List<C0663gq> list = this.f1143a;
        if (list != null) {
            Bundle[] bundleArr = new Bundle[list.size()];
            int i = 0;
            Iterator<C0663gq> it = this.f1143a.iterator();
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
    public C0663gq m1483a(String str) {
        return m1484a(str, null);
    }

    /* renamed from: a */
    public C0663gq m1484a(String str, String str2) {
        for (C0663gq c0663gq : this.f1143a) {
            if (str2 == null || str2.equals(c0663gq.m1451b())) {
                if (str.equals(c0663gq.m1448a())) {
                    return c0663gq;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public C0670gx m1485a() {
        return this.f1142a;
    }

    /* renamed from: a */
    public Object m1486a(String str) {
        synchronized (this) {
            if (this.f1144a == null) {
                return null;
            }
            return this.f1144a.get(str);
        }
    }

    /* renamed from: a */
    public abstract String mo1456a();

    /* renamed from: a */
    public Collection<C0663gq> m1487a() {
        synchronized (this) {
            if (this.f1143a == null) {
                return Collections.emptyList();
            }
            return Collections.unmodifiableList(new ArrayList(this.f1143a));
        }
    }

    /* renamed from: a */
    public void m1488a(C0663gq c0663gq) {
        this.f1143a.add(c0663gq);
    }

    /* renamed from: a */
    public void m1489a(C0670gx c0670gx) {
        this.f1142a = c0670gx;
    }

    /* renamed from: b */
    public Collection<String> m1490b() {
        synchronized (this) {
            if (this.f1144a == null) {
                return Collections.emptySet();
            }
            return Collections.unmodifiableSet(new HashSet(this.f1144a.keySet()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00fe, code lost:            if (r0.equals(r0.f1145d) == false) goto L85;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r4) {
        /*
            Method dump skipped, instructions count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.AbstractC0666gt.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.f1145d;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        String str2 = this.f1146e;
        int hashCode2 = str2 != null ? str2.hashCode() : 0;
        String str3 = this.f1147f;
        int hashCode3 = str3 != null ? str3.hashCode() : 0;
        String str4 = this.f1148g;
        int hashCode4 = str4 != null ? str4.hashCode() : 0;
        String str5 = this.f1149h;
        int hashCode5 = str5 != null ? str5.hashCode() : 0;
        int hashCode6 = this.f1143a.hashCode();
        int hashCode7 = this.f1144a.hashCode();
        C0670gx c0670gx = this.f1142a;
        if (c0670gx != null) {
            i = c0670gx.hashCode();
        }
        return (((((((((((((hashCode * 31) + hashCode2) * 31) + hashCode3) * 31) + hashCode4) * 31) + hashCode5) * 31) + hashCode6) * 31) + hashCode7) * 31) + i;
    }

    /* renamed from: j */
    public String m1491j() {
        if ("ID_NOT_AVAILABLE".equals(this.f1146e)) {
            return null;
        }
        if (this.f1146e == null) {
            this.f1146e = m1481i();
        }
        return this.f1146e;
    }

    /* renamed from: k */
    public String m1492k() {
        return this.f1149h;
    }

    /* renamed from: k */
    public void m1493k(String str) {
        this.f1146e = str;
    }

    /* renamed from: l */
    public String m1494l() {
        return this.f1147f;
    }

    /* renamed from: l */
    public void m1495l(String str) {
        this.f1149h = str;
    }

    /* renamed from: m */
    public String m1496m() {
        return this.f1148g;
    }

    /* renamed from: m */
    public void m1497m(String str) {
        this.f1147f = str;
    }

    /* renamed from: n */
    public String m1498n() {
        return this.f1150i;
    }

    /* renamed from: n */
    public void m1499n(String str) {
        this.f1148g = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01f3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m1500o() {
        /*
            Method dump skipped, instructions count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.AbstractC0666gt.m1500o():java.lang.String");
    }

    /* renamed from: o */
    public void m1501o(String str) {
        this.f1150i = str;
    }

    /* renamed from: p */
    public String m1502p() {
        return this.f1145d;
    }
}
