package com.xiaomi.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.HandlerThread;
import android.os.Message;

/* renamed from: com.xiaomi.push.cg */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cg.class */
public class C0545cg {

    /* renamed from: a */
    private static final long f575a;

    /* renamed from: a */
    private static final Object f576a;

    /* renamed from: a */
    private BroadcastReceiver f577a = new C0550cl(this);

    /* renamed from: a */
    private Context f578a;

    /* renamed from: a */
    private ConnectivityManager f579a;

    /* renamed from: a */
    private HandlerThread f580a;

    /* renamed from: a */
    private C0541cc f581a;

    /* renamed from: a */
    private HandlerC0553co f582a;

    /* renamed from: a */
    private InterfaceC0554cp f583a;

    static {
        C0523bl.m603a();
        f575a = C0523bl.m605a() ? 30000L : 1800000L;
        f576a = new Object();
    }

    public C0545cg(Context context) {
        this.f578a = context;
    }

    /* renamed from: a */
    private int m746a() {
        try {
            return ((C0522bk) this.f578a).m596a();
        } catch (Exception e) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m749a(boolean z) {
        NetworkInfo networkInfo = null;
        try {
            if (this.f578a != null) {
                networkInfo = null;
                if (this.f578a.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f578a.getPackageName()) == 0) {
                    networkInfo = null;
                    if (this.f579a != null) {
                        networkInfo = this.f579a.getActiveNetworkInfo();
                    }
                }
            }
        } catch (Exception e) {
            networkInfo = null;
        }
        if (this.f581a == null) {
            return;
        }
        if (networkInfo == null || networkInfo.getType() != 1 || !networkInfo.isConnected()) {
            this.f581a.m717d();
            return;
        }
        String m768a = C0548cj.m768a(this.f578a, 1);
        if (this.f581a.m709a() == null || !this.f581a.m709a().equals(m768a)) {
            this.f581a.m711a(m768a);
        }
        if (this.f582a.hasMessages(2)) {
            this.f582a.removeMessages(2);
        }
        Message obtainMessage = this.f582a.obtainMessage(2);
        long j = f575a;
        obtainMessage.obj = Boolean.valueOf(z);
        if (z) {
            this.f582a.sendMessage(obtainMessage);
        } else {
            this.f582a.sendMessageDelayed(obtainMessage, j);
        }
    }

    /* renamed from: a */
    private boolean m750a() {
        long currentTimeMillis = System.currentTimeMillis();
        long m708a = this.f581a.m708a();
        long m615c = C0523bl.m603a().m615c();
        long j = m615c;
        if (m615c == Long.MAX_VALUE) {
            j = f575a;
        }
        String m709a = this.f581a.m709a();
        return m709a != null && m709a.equals(C0548cj.m768a(this.f578a, 1)) && currentTimeMillis - m708a >= j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m752b(boolean z) {
        if (C0523bl.m603a().m614b()) {
            if (z || (m750a() && m754c() && m753b())) {
                m755e();
                this.f581a.m716c();
                this.f581a.m718e();
            }
        }
    }

    /* renamed from: b */
    private boolean m753b() {
        boolean z = true;
        if (C0523bl.m603a().m616c()) {
            long m611b = C0523bl.m603a().m611b();
            if (m611b == Long.MAX_VALUE) {
                m611b = 172800000;
            }
            this.f581a.m713b();
            if (this.f581a.m712b() > m611b) {
                return true;
            }
            z = false;
        }
        return z;
    }

    /* renamed from: c */
    private boolean m754c() {
        long m715c = this.f581a.m715c();
        long m606a = C0523bl.m603a().m606a();
        if (m606a == Long.MAX_VALUE) {
            m606a = 172800000;
        }
        return System.currentTimeMillis() - m715c > m606a;
    }

    /* renamed from: e */
    private void m755e() {
        this.f583a.mo764a(this.f581a.m709a(), this.f581a.m708a(), this.f581a.m712b());
    }

    /* renamed from: f */
    private void m756f() {
        this.f578a.registerReceiver(this.f577a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* renamed from: g */
    private void m757g() {
        if (this.f582a.hasMessages(1)) {
            this.f582a.removeMessages(1);
        }
        if (this.f582a.hasMessages(2)) {
            this.f582a.removeMessages(2);
        }
        this.f578a.unregisterReceiver(this.f577a);
    }

    /* renamed from: a */
    public void m758a() {
        m749a(true);
    }

    /* renamed from: a */
    public void m759a(InterfaceC0554cp interfaceC0554cp) {
        synchronized (f576a) {
            this.f583a = interfaceC0554cp;
        }
    }

    /* renamed from: b */
    public void m760b() {
        this.f581a = new C0541cc(this.f578a);
        this.f579a = (ConnectivityManager) this.f578a.getSystemService("connectivity");
        this.f580a = new HandlerThread("WifiCampStatics");
        this.f580a.start();
        this.f582a = new HandlerC0553co(this, this.f580a.getLooper());
        if (m746a() == 0) {
            m756f();
        }
    }

    /* renamed from: c */
    public void m761c() {
        if (m746a() == 0) {
            m757g();
        }
        this.f579a = null;
        this.f581a.m710a();
        HandlerThread handlerThread = this.f580a;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.f580a = null;
        }
    }

    /* renamed from: d */
    public void m762d() {
        synchronized (f576a) {
            this.f583a = null;
        }
    }
}
