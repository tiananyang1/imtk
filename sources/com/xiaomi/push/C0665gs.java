package com.xiaomi.push;

import android.os.Bundle;
import android.text.TextUtils;

/* renamed from: com.xiaomi.push.gs */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gs.class */
public class C0665gs extends AbstractC0666gt {

    /* renamed from: a */
    private boolean f1124a;

    /* renamed from: b */
    private String f1125b;

    /* renamed from: b */
    private boolean f1126b;

    /* renamed from: c */
    private String f1127c;

    /* renamed from: d */
    private String f1128d;

    /* renamed from: e */
    private String f1129e;

    /* renamed from: f */
    private String f1130f;

    /* renamed from: g */
    private String f1131g;

    /* renamed from: h */
    private String f1132h;

    /* renamed from: i */
    private String f1133i;

    /* renamed from: j */
    private String f1134j;

    /* renamed from: k */
    private String f1135k;

    /* renamed from: l */
    private String f1136l;

    public C0665gs() {
        this.f1125b = null;
        this.f1127c = null;
        this.f1124a = false;
        this.f1133i = "";
        this.f1134j = "";
        this.f1135k = "";
        this.f1136l = "";
        this.f1126b = false;
    }

    public C0665gs(Bundle bundle) {
        super(bundle);
        this.f1125b = null;
        this.f1127c = null;
        this.f1124a = false;
        this.f1133i = "";
        this.f1134j = "";
        this.f1135k = "";
        this.f1136l = "";
        this.f1126b = false;
        this.f1125b = bundle.getString("ext_msg_type");
        this.f1128d = bundle.getString("ext_msg_lang");
        this.f1127c = bundle.getString("ext_msg_thread");
        this.f1129e = bundle.getString("ext_msg_sub");
        this.f1130f = bundle.getString("ext_msg_body");
        this.f1131g = bundle.getString("ext_body_encode");
        this.f1132h = bundle.getString("ext_msg_appid");
        this.f1124a = bundle.getBoolean("ext_msg_trans", false);
        this.f1126b = bundle.getBoolean("ext_msg_encrypt", false);
        this.f1133i = bundle.getString("ext_msg_seq");
        this.f1134j = bundle.getString("ext_msg_mseq");
        this.f1135k = bundle.getString("ext_msg_fseq");
        this.f1136l = bundle.getString("ext_msg_status");
    }

    @Override // com.xiaomi.push.AbstractC0666gt
    /* renamed from: a */
    public Bundle mo1454a() {
        Bundle mo1454a = super.mo1454a();
        if (!TextUtils.isEmpty(this.f1125b)) {
            mo1454a.putString("ext_msg_type", this.f1125b);
        }
        String str = this.f1128d;
        if (str != null) {
            mo1454a.putString("ext_msg_lang", str);
        }
        String str2 = this.f1129e;
        if (str2 != null) {
            mo1454a.putString("ext_msg_sub", str2);
        }
        String str3 = this.f1130f;
        if (str3 != null) {
            mo1454a.putString("ext_msg_body", str3);
        }
        if (!TextUtils.isEmpty(this.f1131g)) {
            mo1454a.putString("ext_body_encode", this.f1131g);
        }
        String str4 = this.f1127c;
        if (str4 != null) {
            mo1454a.putString("ext_msg_thread", str4);
        }
        String str5 = this.f1132h;
        if (str5 != null) {
            mo1454a.putString("ext_msg_appid", str5);
        }
        if (this.f1124a) {
            mo1454a.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty(this.f1133i)) {
            mo1454a.putString("ext_msg_seq", this.f1133i);
        }
        if (!TextUtils.isEmpty(this.f1134j)) {
            mo1454a.putString("ext_msg_mseq", this.f1134j);
        }
        if (!TextUtils.isEmpty(this.f1135k)) {
            mo1454a.putString("ext_msg_fseq", this.f1135k);
        }
        if (this.f1126b) {
            mo1454a.putBoolean("ext_msg_encrypt", true);
        }
        if (!TextUtils.isEmpty(this.f1136l)) {
            mo1454a.putString("ext_msg_status", this.f1136l);
        }
        return mo1454a;
    }

    @Override // com.xiaomi.push.AbstractC0666gt
    /* renamed from: a */
    public String mo1456a() {
        C0670gx a;
        StringBuilder sb = new StringBuilder();
        sb.append("<message");
        if (m1502p() != null) {
            sb.append(" xmlns=\"");
            sb.append(m1502p());
            sb.append("\"");
        }
        if (this.f1128d != null) {
            sb.append(" xml:lang=\"");
            sb.append(m1477h());
            sb.append("\"");
        }
        if (m1491j() != null) {
            sb.append(" id=\"");
            sb.append(m1491j());
            sb.append("\"");
        }
        if (m1494l() != null) {
            sb.append(" to=\"");
            sb.append(C0678he.m1528a(m1494l()));
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(m1469d())) {
            sb.append(" seq=\"");
            sb.append(m1469d());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(m1471e())) {
            sb.append(" mseq=\"");
            sb.append(m1471e());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(m1473f())) {
            sb.append(" fseq=\"");
            sb.append(m1473f());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(m1475g())) {
            sb.append(" status=\"");
            sb.append(m1475g());
            sb.append("\"");
        }
        if (m1496m() != null) {
            sb.append(" from=\"");
            sb.append(C0678he.m1528a(m1496m()));
            sb.append("\"");
        }
        if (m1492k() != null) {
            sb.append(" chid=\"");
            sb.append(C0678he.m1528a(m1492k()));
            sb.append("\"");
        }
        if (this.f1124a) {
            sb.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.f1132h)) {
            sb.append(" appid=\"");
            sb.append(m1467c());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(this.f1125b)) {
            sb.append(" type=\"");
            sb.append(this.f1125b);
            sb.append("\"");
        }
        if (this.f1126b) {
            sb.append(" s=\"1\"");
        }
        sb.append(">");
        if (this.f1129e != null) {
            sb.append("<subject>");
            sb.append(C0678he.m1528a(this.f1129e));
            sb.append("</subject>");
        }
        if (this.f1130f != null) {
            sb.append("<body");
            if (!TextUtils.isEmpty(this.f1131g)) {
                sb.append(" encode=\"");
                sb.append(this.f1131g);
                sb.append("\"");
            }
            sb.append(">");
            sb.append(C0678he.m1528a(this.f1130f));
            sb.append("</body>");
        }
        if (this.f1127c != null) {
            sb.append("<thread>");
            sb.append(this.f1127c);
            sb.append("</thread>");
        }
        if ("error".equalsIgnoreCase(this.f1125b) && (a = mo1454a()) != null) {
            sb.append(a.m1509a());
        }
        sb.append(m1500o());
        sb.append("</message>");
        return sb.toString();
    }

    /* renamed from: a */
    public void m1461a(String str) {
        this.f1132h = str;
    }

    /* renamed from: a */
    public void m1462a(String str, String str2) {
        this.f1130f = str;
        this.f1131g = str2;
    }

    /* renamed from: a */
    public void m1463a(boolean z) {
        this.f1124a = z;
    }

    /* renamed from: b */
    public String m1464b() {
        return this.f1125b;
    }

    /* renamed from: b */
    public void m1465b(String str) {
        this.f1133i = str;
    }

    /* renamed from: b */
    public void m1466b(boolean z) {
        this.f1126b = z;
    }

    /* renamed from: c */
    public String m1467c() {
        return this.f1132h;
    }

    /* renamed from: c */
    public void m1468c(String str) {
        this.f1134j = str;
    }

    /* renamed from: d */
    public String m1469d() {
        return this.f1133i;
    }

    /* renamed from: d */
    public void m1470d(String str) {
        this.f1135k = str;
    }

    /* renamed from: e */
    public String m1471e() {
        return this.f1134j;
    }

    /* renamed from: e */
    public void m1472e(String str) {
        this.f1136l = str;
    }

    @Override // com.xiaomi.push.AbstractC0666gt
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0665gs c0665gs = (C0665gs) obj;
        if (!super.equals(c0665gs)) {
            return false;
        }
        String str = this.f1130f;
        if (str != null) {
            if (!str.equals(c0665gs.f1130f)) {
                return false;
            }
        } else if (c0665gs.f1130f != null) {
            return false;
        }
        String str2 = this.f1128d;
        if (str2 != null) {
            if (!str2.equals(c0665gs.f1128d)) {
                return false;
            }
        } else if (c0665gs.f1128d != null) {
            return false;
        }
        String str3 = this.f1129e;
        if (str3 != null) {
            if (!str3.equals(c0665gs.f1129e)) {
                return false;
            }
        } else if (c0665gs.f1129e != null) {
            return false;
        }
        String str4 = this.f1127c;
        if (str4 != null) {
            if (!str4.equals(c0665gs.f1127c)) {
                return false;
            }
        } else if (c0665gs.f1127c != null) {
            return false;
        }
        return this.f1125b == c0665gs.f1125b;
    }

    /* renamed from: f */
    public String m1473f() {
        return this.f1135k;
    }

    /* renamed from: f */
    public void m1474f(String str) {
        this.f1125b = str;
    }

    /* renamed from: g */
    public String m1475g() {
        return this.f1136l;
    }

    /* renamed from: g */
    public void m1476g(String str) {
        this.f1129e = str;
    }

    /* renamed from: h */
    public String m1477h() {
        return this.f1128d;
    }

    /* renamed from: h */
    public void m1478h(String str) {
        this.f1130f = str;
    }

    @Override // com.xiaomi.push.AbstractC0666gt
    public int hashCode() {
        String str = this.f1125b;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        String str2 = this.f1130f;
        int hashCode2 = str2 != null ? str2.hashCode() : 0;
        String str3 = this.f1127c;
        int hashCode3 = str3 != null ? str3.hashCode() : 0;
        String str4 = this.f1128d;
        int hashCode4 = str4 != null ? str4.hashCode() : 0;
        String str5 = this.f1129e;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return (((((((hashCode * 31) + hashCode2) * 31) + hashCode3) * 31) + hashCode4) * 31) + i;
    }

    /* renamed from: i */
    public void m1479i(String str) {
        this.f1127c = str;
    }

    /* renamed from: j */
    public void m1480j(String str) {
        this.f1128d = str;
    }
}
