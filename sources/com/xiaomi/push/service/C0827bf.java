package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0486ab;
import com.xiaomi.push.C0770n;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.service.bf */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bf.class */
public class C0827bf {

    /* renamed from: a */
    private static C0827bf f2585a;

    /* renamed from: a */
    private static String f2586a;

    /* renamed from: a */
    private Context f2587a;

    /* renamed from: a */
    private boolean f2590a;

    /* renamed from: b */
    private Messenger f2591b;

    /* renamed from: a */
    private List<Message> f2589a = new ArrayList();

    /* renamed from: b */
    private boolean f2592b = false;

    /* renamed from: a */
    private Messenger f2588a = new Messenger(new HandlerC0828bg(this, Looper.getMainLooper()));

    private C0827bf(Context context) {
        this.f2590a = false;
        this.f2587a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (m2636a()) {
            AbstractC0407b.m74c("use miui push service");
            this.f2590a = true;
        }
    }

    /* renamed from: a */
    private Message m2630a(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    /* renamed from: a */
    public static C0827bf m2633a(Context context) {
        if (f2585a == null) {
            f2585a = new C0827bf(context);
        }
        return f2585a;
    }

    /* renamed from: a */
    private void m2635a(Intent intent) {
        synchronized (this) {
            if (this.f2592b) {
                Message m2630a = m2630a(intent);
                if (this.f2589a.size() >= 50) {
                    this.f2589a.remove(0);
                }
                this.f2589a.add(m2630a);
                return;
            }
            if (this.f2591b == null) {
                Context context = this.f2587a;
                ServiceConnectionC0829bh serviceConnectionC0829bh = new ServiceConnectionC0829bh(this);
                Context context2 = this.f2587a;
                context.bindService(intent, serviceConnectionC0829bh, 1);
                this.f2592b = true;
                this.f2589a.clear();
                this.f2589a.add(m2630a(intent));
            } else {
                try {
                    this.f2591b.send(m2630a(intent));
                } catch (RemoteException e) {
                    this.f2591b = null;
                    this.f2592b = false;
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m2636a() {
        if (C0486ab.f409e) {
            return false;
        }
        try {
            PackageInfo packageInfo = this.f2587a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode >= 104;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public boolean m2638a(Intent intent) {
        try {
            if (C0770n.m2403a() || Build.VERSION.SDK_INT < 26) {
                this.f2587a.startService(intent);
                return true;
            }
            m2635a(intent);
            return true;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }
}
