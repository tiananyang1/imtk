package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: com.xiaomi.push.gq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gq.class */
public class C0663gq implements InterfaceC0667gu {

    /* renamed from: a */
    private String f1110a;

    /* renamed from: a */
    private List<C0663gq> f1111a;

    /* renamed from: a */
    private String[] f1112a;

    /* renamed from: b */
    private String f1113b;

    /* renamed from: b */
    private String[] f1114b;

    /* renamed from: c */
    private String f1115c;

    public C0663gq(String str, String str2, String[] strArr, String[] strArr2) {
        this.f1112a = null;
        this.f1114b = null;
        this.f1111a = null;
        this.f1110a = str;
        this.f1113b = str2;
        this.f1112a = strArr;
        this.f1114b = strArr2;
    }

    public C0663gq(String str, String str2, String[] strArr, String[] strArr2, String str3, List<C0663gq> list) {
        this.f1112a = null;
        this.f1114b = null;
        this.f1111a = null;
        this.f1110a = str;
        this.f1113b = str2;
        this.f1112a = strArr;
        this.f1114b = strArr2;
        this.f1115c = str3;
        this.f1111a = list;
    }

    /* renamed from: a */
    public static C0663gq m1443a(Bundle bundle) {
        ArrayList arrayList;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> keySet = bundle2.keySet();
        String[] strArr = new String[keySet.size()];
        String[] strArr2 = new String[keySet.size()];
        Iterator<String> it = keySet.iterator();
        int i = 0;
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            strArr[i2] = next;
            strArr2[i2] = bundle2.getString(next);
            i = i2 + 1;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            arrayList = new ArrayList(parcelableArray.length);
            int length = parcelableArray.length;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= length) {
                    break;
                }
                arrayList.add(m1443a((Bundle) parcelableArray[i4]));
                i3 = i4 + 1;
            }
        } else {
            arrayList = null;
        }
        return new C0663gq(string, string2, strArr, strArr2, string3, arrayList);
    }

    /* renamed from: a */
    public static Parcelable[] m1444a(List<C0663gq> list) {
        return m1445a((C0663gq[]) list.toArray(new C0663gq[list.size()]));
    }

    /* renamed from: a */
    public static Parcelable[] m1445a(C0663gq[] c0663gqArr) {
        if (c0663gqArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[c0663gqArr.length];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= c0663gqArr.length) {
                return parcelableArr;
            }
            parcelableArr[i2] = c0663gqArr[i2].m1447a();
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public Bundle m1446a() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.f1110a);
        bundle.putString("ext_ns", this.f1113b);
        bundle.putString("ext_text", this.f1115c);
        Bundle bundle2 = new Bundle();
        String[] strArr = this.f1112a;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                String[] strArr2 = this.f1112a;
                if (i2 >= strArr2.length) {
                    break;
                }
                bundle2.putString(strArr2[i2], this.f1114b[i2]);
                i = i2 + 1;
            }
        }
        bundle.putBundle("attributes", bundle2);
        List<C0663gq> list = this.f1111a;
        if (list != null && list.size() > 0) {
            bundle.putParcelableArray("children", m1444a(this.f1111a));
        }
        return bundle;
    }

    /* renamed from: a */
    public Parcelable m1447a() {
        return m1446a();
    }

    /* renamed from: a */
    public String m1448a() {
        return this.f1110a;
    }

    /* renamed from: a */
    public String m1449a(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (this.f1112a == null) {
            return null;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            String[] strArr = this.f1112a;
            if (i2 >= strArr.length) {
                return null;
            }
            if (str.equals(strArr[i2])) {
                return this.f1114b[i2];
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public void m1450a(String str) {
        String str2 = str;
        if (!TextUtils.isEmpty(str)) {
            str2 = C0678he.m1528a(str);
        }
        this.f1115c = str2;
    }

    /* renamed from: b */
    public String m1451b() {
        return this.f1113b;
    }

    /* renamed from: c */
    public String m1452c() {
        return !TextUtils.isEmpty(this.f1115c) ? C0678he.m1531b(this.f1115c) : this.f1115c;
    }

    @Override // com.xiaomi.push.InterfaceC0667gu
    /* renamed from: d */
    public String mo1453d() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.f1110a);
        if (!TextUtils.isEmpty(this.f1113b)) {
            sb.append(" ");
            sb.append("xmlns=");
            sb.append("\"");
            sb.append(this.f1113b);
            sb.append("\"");
        }
        String[] strArr = this.f1112a;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.f1112a.length) {
                    break;
                }
                if (!TextUtils.isEmpty(this.f1114b[i2])) {
                    sb.append(" ");
                    sb.append(this.f1112a[i2]);
                    sb.append("=\"");
                    sb.append(C0678he.m1528a(this.f1114b[i2]));
                    sb.append("\"");
                }
                i = i2 + 1;
            }
        }
        if (TextUtils.isEmpty(this.f1115c)) {
            List<C0663gq> list = this.f1111a;
            if (list == null || list.size() <= 0) {
                sb.append("/>");
                return sb.toString();
            }
            sb.append(">");
            Iterator<C0663gq> it = this.f1111a.iterator();
            while (it.hasNext()) {
                sb.append(it.next().mo1453d());
            }
        } else {
            sb.append(">");
            sb.append(this.f1115c);
        }
        sb.append("</");
        sb.append(this.f1110a);
        sb.append(">");
        return sb.toString();
    }

    public String toString() {
        return mo1453d();
    }
}
