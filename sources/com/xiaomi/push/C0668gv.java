package com.xiaomi.push;

import android.os.Bundle;

/* renamed from: com.xiaomi.push.gv */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gv.class */
public class C0668gv extends AbstractC0666gt {

    /* renamed from: a */
    private int f1151a;

    /* renamed from: a */
    private a f1152a;

    /* renamed from: a */
    private b f1153a;

    /* renamed from: b */
    private String f1154b;

    /* renamed from: com.xiaomi.push.gv$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gv$a.class */
    public enum a {
        chat,
        available,
        away,
        xa,
        dnd
    }

    /* renamed from: com.xiaomi.push.gv$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gv$b.class */
    public enum b {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public C0668gv(Bundle bundle) {
        super(bundle);
        this.f1153a = b.available;
        this.f1154b = null;
        this.f1151a = Integer.MIN_VALUE;
        this.f1152a = null;
        if (bundle.containsKey("ext_pres_type")) {
            this.f1153a = b.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.f1154b = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.f1151a = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.f1152a = a.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    public C0668gv(b bVar) {
        this.f1153a = b.available;
        this.f1154b = null;
        this.f1151a = Integer.MIN_VALUE;
        this.f1152a = null;
        m1505a(bVar);
    }

    @Override // com.xiaomi.push.AbstractC0666gt
    /* renamed from: a */
    public Bundle mo1454a() {
        Bundle mo1454a = super.mo1454a();
        b bVar = this.f1153a;
        if (bVar != null) {
            mo1454a.putString("ext_pres_type", bVar.toString());
        }
        String str = this.f1154b;
        if (str != null) {
            mo1454a.putString("ext_pres_status", str);
        }
        int i = this.f1151a;
        if (i != Integer.MIN_VALUE) {
            mo1454a.putInt("ext_pres_prio", i);
        }
        a aVar = this.f1152a;
        if (aVar != null && aVar != a.available) {
            mo1454a.putString("ext_pres_mode", this.f1152a.toString());
        }
        return mo1454a;
    }

    @Override // com.xiaomi.push.AbstractC0666gt
    /* renamed from: a */
    public String mo1456a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<presence");
        if (m1502p() != null) {
            sb.append(" xmlns=\"");
            sb.append(m1502p());
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
        if (this.f1153a != null) {
            sb.append(" type=\"");
            sb.append(this.f1153a);
            sb.append("\"");
        }
        sb.append(">");
        if (this.f1154b != null) {
            sb.append("<status>");
            sb.append(C0678he.m1528a(this.f1154b));
            sb.append("</status>");
        }
        if (this.f1151a != Integer.MIN_VALUE) {
            sb.append("<priority>");
            sb.append(this.f1151a);
            sb.append("</priority>");
        }
        a aVar = this.f1152a;
        if (aVar != null && aVar != a.available) {
            sb.append("<show>");
            sb.append(this.f1152a);
            sb.append("</show>");
        }
        sb.append(m1500o());
        C0670gx a2 = mo1454a();
        if (a2 != null) {
            sb.append(a2.m1509a());
        }
        sb.append("</presence>");
        return sb.toString();
    }

    /* renamed from: a */
    public void m1503a(int i) {
        if (i >= -128 && i <= 128) {
            this.f1151a = i;
            return;
        }
        throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
    }

    /* renamed from: a */
    public void m1504a(a aVar) {
        this.f1152a = aVar;
    }

    /* renamed from: a */
    public void m1505a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.f1153a = bVar;
    }

    /* renamed from: a */
    public void m1506a(String str) {
        this.f1154b = str;
    }
}
