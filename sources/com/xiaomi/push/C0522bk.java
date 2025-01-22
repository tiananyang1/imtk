package com.xiaomi.push;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.bk */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bk.class */
public class C0522bk extends ContextWrapper {

    /* renamed from: a */
    private static C0522bk f492a;

    /* renamed from: a */
    private int f493a;

    /* renamed from: a */
    private Handler f494a;

    /* renamed from: a */
    private HandlerThread f495a;

    /* renamed from: a */
    private InterfaceC0531bt f496a;

    /* renamed from: a */
    List f497a;

    /* renamed from: a */
    private boolean f498a;

    /* renamed from: b */
    private boolean f499b;

    private C0522bk(Context context) {
        super(context);
        this.f499b = false;
        this.f497a = new ArrayList();
        this.f493a = 0;
        this.f496a = new C0561cw(this);
        this.f498a = false;
        this.f495a = new HandlerThread("metoknlp_app");
        this.f495a.start();
        this.f494a = new HandlerC0557cs(this, this.f495a.getLooper());
        C0527bp.m626a(context);
        this.f494a.sendEmptyMessageDelayed(101, 1000L);
    }

    /* renamed from: a */
    public static C0522bk m590a() {
        C0522bk c0522bk = f492a;
        C0522bk c0522bk2 = c0522bk;
        if (c0522bk == null) {
            c0522bk2 = null;
        }
        return c0522bk2;
    }

    /* renamed from: a */
    public static C0522bk m591a(Context context) {
        if (f492a == null) {
            f492a = new C0522bk(context);
        }
        return f492a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m594c() {
        if (!this.f498a) {
            this.f498a = true;
        }
        C0536by.m650a().m656a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m595d() {
        this.f494a.sendEmptyMessageDelayed(102, 10000L);
    }

    /* renamed from: a */
    public int m596a() {
        return this.f493a;
    }

    /* renamed from: a */
    public Handler m597a() {
        return this.f494a;
    }

    /* renamed from: a */
    public void m598a() {
        C0536by.m650a().m655a();
    }

    /* renamed from: a */
    public void m599a(InterfaceC0539ca interfaceC0539ca, int i) {
        Iterator it = this.f497a.iterator();
        while (it.hasNext()) {
            if (((InterfaceC0539ca) it.next()) == interfaceC0539ca) {
                return;
            }
        }
        this.f493a = i;
        this.f497a.add(interfaceC0539ca);
    }

    /* renamed from: a */
    public void m600a(String str) {
        for (InterfaceC0539ca interfaceC0539ca : this.f497a) {
            if (interfaceC0539ca != null) {
                interfaceC0539ca.mo707a(str);
            }
        }
    }

    /* renamed from: a */
    public boolean m601a() {
        return this.f498a;
    }

    /* renamed from: b */
    public void m602b() {
        C0523bl.m604a(f492a);
        C0533bv.m648a(f492a);
        C0533bv.m646a().m649a(this.f496a);
    }
}
