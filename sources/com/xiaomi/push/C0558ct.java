package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.ct */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ct.class */
public class C0558ct {

    /* renamed from: a */
    private Context f608a;

    /* renamed from: a */
    private Handler f610a;

    /* renamed from: a */
    private InterfaceC0560cv f611a;

    /* renamed from: a */
    private boolean f613a;

    /* renamed from: a */
    private int f607a = 0;

    /* renamed from: a */
    private List<b> f612a = new ArrayList();

    /* renamed from: b */
    private List<b> f614b = new ArrayList();

    /* renamed from: a */
    private final ServiceConnection f609a = new ServiceConnectionC0559cu(this);

    /* renamed from: com.xiaomi.push.ct$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ct$a.class */
    private class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    C0558ct.this.m806a();
                    return;
                } else if (i != 3) {
                    Log.w("GeoFencingServiceWrapper", "unknown message type ");
                    return;
                } else {
                    C0558ct.this.m810b();
                    return;
                }
            }
            C0558ct.m802a(C0558ct.this);
            C0558ct c0558ct = C0558ct.this;
            c0558ct.m812a(c0558ct.f608a);
            Log.w("GeoFencingServiceWrapper", "Try bindService count=" + C0558ct.this.f607a + ",mBinded=" + C0558ct.this.f613a);
            if (C0558ct.this.f613a || C0558ct.this.f610a == null || C0558ct.this.f607a >= 10) {
                return;
            }
            C0558ct.this.f610a.sendEmptyMessageDelayed(1, 10000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.xiaomi.push.ct$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ct$b.class */
    public class b {

        /* renamed from: a */
        public double f616a;

        /* renamed from: a */
        public float f617a;

        /* renamed from: a */
        public long f618a;

        /* renamed from: a */
        public String f620a;

        /* renamed from: b */
        public double f621b;

        /* renamed from: b */
        public String f622b;

        /* renamed from: c */
        public String f623c;

        public b(double d, double d2, float f, long j, String str, String str2, String str3) {
            this.f616a = d;
            this.f621b = d2;
            this.f617a = f;
            this.f618a = j;
            this.f620a = str;
            this.f622b = str2;
            this.f623c = str3;
        }
    }

    public C0558ct(Context context) {
        this.f613a = false;
        this.f608a = context;
        this.f613a = false;
        m812a(context);
        HandlerThread handlerThread = new HandlerThread("GeoFencingServiceWrapper");
        handlerThread.start();
        this.f610a = new a(handlerThread.getLooper());
        if (this.f613a) {
            return;
        }
        this.f610a.sendEmptyMessageDelayed(1, 10000L);
    }

    /* renamed from: a */
    static /* synthetic */ int m802a(C0558ct c0558ct) {
        int i = c0558ct.f607a;
        c0558ct.f607a = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m806a() {
        InterfaceC0560cv interfaceC0560cv;
        List<b> list = this.f612a;
        Log.d("GeoFencingServiceWrapper", "try registerPendingFence size=" + (list == null ? 0 : list.size()));
        for (b bVar : this.f612a) {
            if (bVar != null && (interfaceC0560cv = this.f611a) != null) {
                try {
                    interfaceC0560cv.mo819a(bVar.f616a, bVar.f621b, bVar.f617a, bVar.f618a, bVar.f620a, bVar.f622b, bVar.f623c);
                } catch (RemoteException e) {
                    Log.w("GeoFencingServiceWrapper", "registerPendingFence:" + e);
                }
            }
        }
        List<b> list2 = this.f612a;
        if (list2 != null) {
            list2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m810b() {
        InterfaceC0560cv interfaceC0560cv;
        List<b> list = this.f614b;
        Log.d("GeoFencingServiceWrapper", "try unregisterPendingFence size=" + (list == null ? 0 : list.size()));
        for (b bVar : this.f614b) {
            if (bVar != null && (interfaceC0560cv = this.f611a) != null) {
                try {
                    interfaceC0560cv.mo822a(bVar.f620a, bVar.f622b);
                } catch (RemoteException e) {
                    Log.w("GeoFencingServiceWrapper", "unregisterPendingFence:" + e);
                }
            }
        }
        List<b> list2 = this.f614b;
        if (list2 != null) {
            list2.clear();
        }
    }

    /* renamed from: a */
    public void m812a(Context context) {
        if (this.f613a || context == null) {
            return;
        }
        if (this.f611a != null) {
            Log.d("GeoFencingServiceWrapper", "GeoFencingService already started");
            return;
        }
        Intent intent = new Intent("com.xiaomi.metoknlp.GeoFencingService");
        intent.setPackage("com.xiaomi.metoknlp");
        try {
            if (context.bindService(intent, this.f609a, 1)) {
                Log.d("GeoFencingServiceWrapper", "GeoFencingService started");
                this.f613a = true;
            } else {
                Log.d("GeoFencingServiceWrapper", "Can't bind GeoFencingService");
                this.f613a = false;
            }
        } catch (SecurityException e) {
            Log.e("GeoFencingServiceWrapper", "SecurityException:" + e);
        }
    }

    /* renamed from: a */
    public void m813a(Context context, double d, double d2, float f, long j, String str, String str2, String str3) {
        m812a(context);
        InterfaceC0560cv interfaceC0560cv = this.f611a;
        if (interfaceC0560cv == null) {
            Log.d("GeoFencingServiceWrapper", "registerFenceListener service not ready, add to pending list.");
            this.f612a.add(new b(d, d2, f, j, str, str2, str3));
        } else {
            try {
                interfaceC0560cv.mo819a(d, d2, f, j, str, str2, str3);
                Log.d("GeoFencingServiceWrapper", "calling registerFenceListener success");
            } catch (RemoteException e) {
                throw new RuntimeException("GeoFencingService has died", e);
            }
        }
    }

    /* renamed from: a */
    public void m814a(Context context, String str, String str2) {
        m812a(context);
        InterfaceC0560cv interfaceC0560cv = this.f611a;
        if (interfaceC0560cv == null) {
            Log.d("GeoFencingServiceWrapper", "unregisterFenceListener service not ready, add to pending list.");
            this.f614b.add(new b(0.0d, 0.0d, 0.0f, -1L, str, str2, ""));
        } else {
            try {
                interfaceC0560cv.mo822a(str, str2);
                Log.d("GeoFencingServiceWrapper", "calling unregisterFenceListener success");
            } catch (RemoteException e) {
                throw new RuntimeException("GeoFencingService has died", e);
            }
        }
    }
}
