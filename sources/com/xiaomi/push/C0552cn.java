package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.xiaomi.push.cn */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cn.class */
public class C0552cn {

    /* renamed from: a */
    private static C0552cn f594a;

    /* renamed from: a */
    private static String f595a;

    /* renamed from: a */
    private Context f597a;

    /* renamed from: a */
    private C0545cg f600a;

    /* renamed from: a */
    private C0546ch f601a;

    /* renamed from: a */
    private AsyncTaskC0549ck f602a;

    /* renamed from: a */
    private Object f603a = new Object();

    /* renamed from: a */
    private int f596a = 0;

    /* renamed from: a */
    private C0542cd f599a = new C0542cd();

    /* renamed from: a */
    private Handler f598a = new HandlerC0540cb(this);

    /* renamed from: a */
    public static C0552cn m788a() {
        if (f594a == null) {
            f594a = new C0552cn();
        }
        return f594a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m791a(HashMap hashMap) {
        if (hashMap == null) {
            return;
        }
        String m773b = C0548cj.m773b(this.f597a);
        C0542cd c0542cd = this.f599a;
        if (c0542cd != null && m773b != null) {
            c0542cd.m742g(m773b);
            if (hashMap.containsKey(m773b)) {
                this.f599a.m743h((String) hashMap.get(m773b));
            }
        }
        Object m767a = C0548cj.m767a(this.f597a);
        if (m767a != null && hashMap.containsKey(m767a)) {
            hashMap.remove(m767a);
        }
        if (this.f599a == null || hashMap.size() <= 0) {
            return;
        }
        this.f599a.m734a(new ArrayList(hashMap.values()));
        m799b();
    }

    /* renamed from: d */
    private void m792d() {
        C0551cm.m781a(this.f597a, this.f598a, 0);
    }

    /* renamed from: e */
    private void m793e() {
        C0542cd c0542cd;
        String m774c = C0548cj.m774c(this.f597a);
        String m768a = C0548cj.m768a(this.f597a, 2);
        String m768a2 = C0548cj.m768a(this.f597a, 1);
        if (m774c == null || m768a == null || m768a2 == null || (c0542cd = this.f599a) == null) {
            return;
        }
        c0542cd.m733a(Build.MODEL).m737b(C0527bp.m627b()).m738c(m774c).m741f(m768a).m740e(m768a2).m732a(this.f601a.m763a()).m736b(this.f601a.m765b());
    }

    /* renamed from: f */
    private void m794f() {
        m799b();
    }

    /* renamed from: g */
    private void m795g() {
        C0542cd c0542cd;
        if (this.f596a != 4 || (c0542cd = this.f599a) == null) {
            return;
        }
        ((C0522bk) this.f597a).m600a(c0542cd.m735a().m745a().toString());
    }

    /* renamed from: h */
    private void m796h() {
        this.f602a = new AsyncTaskC0549ck(this, null);
        f595a = C0523bl.m603a().m607a();
        StringBuffer stringBuffer = new StringBuffer(f595a);
        stringBuffer.append("/api/v2/realip");
        this.f602a.execute(stringBuffer.toString());
    }

    /* renamed from: a */
    public void m797a() {
        C0545cg c0545cg = this.f600a;
        if (c0545cg != null) {
            c0545cg.m761c();
            this.f600a.m762d();
            this.f600a = null;
        }
        this.f601a = null;
    }

    /* renamed from: a */
    public void m798a(Context context) {
        this.f597a = context;
        this.f601a = new C0546ch(this, null);
        this.f600a = new C0545cg(context);
        this.f600a.m760b();
        this.f600a.m759a(this.f601a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m799b() {
        if (C0523bl.m603a().m614b()) {
            int i = this.f596a;
            if (i == 0) {
                this.f596a = 1;
                m793e();
                if (this.f599a != null) {
                    m792d();
                    return;
                }
                return;
            }
            if (i == 1) {
                this.f596a = 2;
                m794f();
            } else if (i == 2) {
                this.f596a = 3;
                m796h();
            } else {
                if (i != 3) {
                    return;
                }
                this.f596a = 4;
                this.f602a.cancel(true);
                this.f602a = null;
                m795g();
            }
        }
    }

    /* renamed from: c */
    public void m800c() {
        C0545cg c0545cg = this.f600a;
        if (c0545cg != null) {
            c0545cg.m758a();
        }
    }
}
