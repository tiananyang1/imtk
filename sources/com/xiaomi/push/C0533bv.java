package com.xiaomi.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.bv */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bv.class */
public class C0533bv {

    /* renamed from: a */
    private static C0533bv f521a;

    /* renamed from: a */
    private Context f523a;

    /* renamed from: a */
    private List f525a = new ArrayList();

    /* renamed from: a */
    private Handler f524a = new HandlerC0534bw(this, C0522bk.m590a().m597a().getLooper());

    /* renamed from: a */
    private BroadcastReceiver f522a = new C0535bx(this);

    private C0533bv(Context context) {
        this.f523a = context;
        this.f523a.registerReceiver(this.f522a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* renamed from: a */
    public static C0533bv m646a() {
        return f521a;
    }

    /* renamed from: a */
    public static void m648a(Context context) {
        if (f521a == null) {
            f521a = new C0533bv(context);
        }
    }

    /* renamed from: a */
    public void m649a(InterfaceC0531bt interfaceC0531bt) {
        synchronized (this.f525a) {
            this.f525a.add(interfaceC0531bt);
        }
    }
}
