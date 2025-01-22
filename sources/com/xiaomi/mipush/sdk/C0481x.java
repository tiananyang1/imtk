package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.C0558ct;
import com.xiaomi.push.C0709ii;
import com.xiaomi.push.C0711ik;

/* renamed from: com.xiaomi.mipush.sdk.x */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/x.class */
public class C0481x {

    /* renamed from: a */
    private Context f391a;

    /* renamed from: a */
    private C0558ct f392a;

    /* renamed from: a */
    private final int f390a = -1;

    /* renamed from: a */
    private final double f389a = 0.0d;

    public C0481x(Context context) {
        this.f391a = context;
        m402a();
    }

    /* renamed from: a */
    private void m402a() {
        this.f392a = new C0558ct(this.f391a);
    }

    /* renamed from: a */
    public void m403a(String str) {
        this.f392a.m814a(this.f391a, "com.xiaomi.xmsf", str);
    }

    /* renamed from: a */
    public boolean m404a(C0709ii c0709ii) {
        if (c0709ii == null) {
            return false;
        }
        if (c0709ii.m1711a() == null || c0709ii.m1699a() <= 0.0d) {
            return true;
        }
        C0711ik m1711a = c0709ii.m1711a();
        this.f392a.m813a(this.f391a, m1711a.m1744b(), m1711a.m1737a(), (float) c0709ii.m1699a(), -1L, "com.xiaomi.xmsf", c0709ii.m1712a(), c0709ii.m1702a().name());
        return true;
    }
}
