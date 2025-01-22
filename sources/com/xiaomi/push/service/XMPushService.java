package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.AbstractC0649gc;
import com.xiaomi.push.AbstractC0666gt;
import com.xiaomi.push.C0486ab;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.C0508ax;
import com.xiaomi.push.C0516be;
import com.xiaomi.push.C0568dc;
import com.xiaomi.push.C0577dl;
import com.xiaomi.push.C0620fa;
import com.xiaomi.push.C0630fk;
import com.xiaomi.push.C0632fm;
import com.xiaomi.push.C0641fv;
import com.xiaomi.push.C0647ga;
import com.xiaomi.push.C0650gd;
import com.xiaomi.push.C0655gi;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.C0664gr;
import com.xiaomi.push.C0665gs;
import com.xiaomi.push.C0668gv;
import com.xiaomi.push.C0681hh;
import com.xiaomi.push.C0688ho;
import com.xiaomi.push.C0690hq;
import com.xiaomi.push.C0691hr;
import com.xiaomi.push.C0694hu;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0733jf;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0749jv;
import com.xiaomi.push.C0880v;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.EnumC0774q;
import com.xiaomi.push.InterfaceC0652gf;
import com.xiaomi.push.InterfaceC0654gh;
import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.C0860k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService.class */
public class XMPushService extends Service implements InterfaceC0652gf {

    /* renamed from: a */
    public static int f2386a;

    /* renamed from: b */
    private static final int f2387b = Process.myPid();

    /* renamed from: a */
    private ContentObserver f2390a;

    /* renamed from: a */
    private C0647ga f2392a;

    /* renamed from: a */
    private AbstractC0649gc f2393a;

    /* renamed from: a */
    private C0650gd f2394a;

    /* renamed from: a */
    private C0782e f2396a;

    /* renamed from: a */
    private C0825bd f2398a;

    /* renamed from: a */
    private C0853d f2399a;

    /* renamed from: a */
    private String f2402a;

    /* renamed from: a */
    private long f2388a = 0;

    /* renamed from: a */
    protected Class f2401a = XMJobService.class;

    /* renamed from: a */
    private C0813as f2397a = null;

    /* renamed from: a */
    private C0860k f2400a = null;

    /* renamed from: a */
    Messenger f2391a = null;

    /* renamed from: a */
    private ArrayList<InterfaceC0789l> f2403a = new ArrayList<>();

    /* renamed from: a */
    private InterfaceC0654gh f2395a = new C0837bp(this);

    /* renamed from: a */
    final BroadcastReceiver f2389a = new C0850cb(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$a.class */
    public class C0778a extends AbstractC0786i {

        /* renamed from: a */
        C0814at.b f2405a;

        public C0778a(C0814at.b bVar) {
            super(9);
            this.f2405a = null;
            this.f2405a = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "bind the client. " + this.f2405a.f2509g;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            String str;
            try {
                if (!XMPushService.this.m2483c()) {
                    AbstractC0407b.m75d("trying bind while the connection is not created, quit!");
                    return;
                }
                C0814at.b m2581a = C0814at.m2578a().m2581a(this.f2405a.f2509g, this.f2405a.f2503b);
                if (m2581a == null) {
                    str = "ignore bind because the channel " + this.f2405a.f2509g + " is removed ";
                } else if (m2581a.f2497a == C0814at.c.unbind) {
                    m2581a.m2607a(C0814at.c.binding, 0, 0, (String) null, (String) null);
                    XMPushService.this.f2393a.mo1376a(m2581a);
                    C0690hq.m1583a(XMPushService.this, m2581a);
                    return;
                } else {
                    str = "trying duplicate bind, ingore! " + m2581a.f2497a;
                }
                AbstractC0407b.m70a(str);
            } catch (Exception e) {
                AbstractC0407b.m72a(e);
                XMPushService.this.m2466a(10, e);
            } catch (Throwable th) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$b.class */
    public static class C0779b extends AbstractC0786i {

        /* renamed from: a */
        private final C0814at.b f2406a;

        public C0779b(C0814at.b bVar) {
            super(12);
            this.f2406a = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "bind time out. chid=" + this.f2406a.f2509g;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            this.f2406a.m2607a(C0814at.c.unbind, 1, 21, (String) null, (String) null);
        }

        public boolean equals(Object obj) {
            if (obj instanceof C0779b) {
                return TextUtils.equals(((C0779b) obj).f2406a.f2509g, this.f2406a.f2509g);
            }
            return false;
        }

        public int hashCode() {
            return this.f2406a.f2509g.hashCode();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$c */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$c.class */
    class C0780c extends AbstractC0786i {

        /* renamed from: a */
        private C0641fv f2407a;

        public C0780c(C0641fv c0641fv) {
            super(8);
            this.f2407a = null;
            this.f2407a = c0641fv;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "receive a message.";
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            XMPushService.this.f2397a.m2575a(this.f2407a);
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$d */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$d.class */
    public class C0781d extends AbstractC0786i {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C0781d() {
            super(1);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "do reconnect..";
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            if (XMPushService.this.m2477a()) {
                XMPushService.this.m2455f();
            } else {
                AbstractC0407b.m70a("should not connect. quit the job.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$e */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$e.class */
    public class C0782e extends BroadcastReceiver {
        C0782e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            XMPushService.this.onStart(intent, XMPushService.f2386a);
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$f */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$f.class */
    public class C0783f extends AbstractC0786i {

        /* renamed from: a */
        public Exception f2412a;

        /* renamed from: b */
        public int f2413b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0783f(int i, Exception exc) {
            super(2);
            this.f2413b = i;
            this.f2412a = exc;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "disconnect the connection.";
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            XMPushService.this.m2466a(this.f2413b, this.f2412a);
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$g */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$g.class */
    class C0784g extends AbstractC0786i {
        C0784g() {
            super(65535);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "Init Job";
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            XMPushService.this.m2446c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$h */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$h.class */
    public class C0785h extends AbstractC0786i {

        /* renamed from: a */
        private Intent f2415a;

        public C0785h(Intent intent) {
            super(15);
            this.f2415a = null;
            this.f2415a = intent;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "Handle intent action = " + this.f2415a.getAction();
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            XMPushService.this.m2447c(this.f2415a);
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$i */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$i.class */
    public static abstract class AbstractC0786i extends C0860k.b {
        public AbstractC0786i(int i) {
            super(i);
        }

        /* renamed from: a */
        public abstract String mo1440a();

        /* renamed from: a */
        public abstract void mo1441a();

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2660a != 4 && this.f2660a != 8) {
                AbstractC0407b.m70a("JOB: " + mo1440a());
            }
            mo1441a();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$j */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$j.class */
    class C0787j extends AbstractC0786i {
        public C0787j() {
            super(5);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "ask the job queue to quit";
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            XMPushService.this.f2400a.m2727a();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$k */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$k.class */
    class C0788k extends AbstractC0786i {

        /* renamed from: a */
        private AbstractC0666gt f2418a;

        public C0788k(AbstractC0666gt abstractC0666gt) {
            super(8);
            this.f2418a = null;
            this.f2418a = abstractC0666gt;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "receive a message.";
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            XMPushService.this.f2397a.m2576a(this.f2418a);
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$l */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$l.class */
    public interface InterfaceC0789l {
        /* renamed from: a */
        void mo1594a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$m */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$m.class */
    public class C0790m extends AbstractC0786i {

        /* renamed from: a */
        boolean f2421a;

        public C0790m(boolean z) {
            super(4);
            this.f2421a = z;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "send ping..";
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            if (XMPushService.this.m2483c()) {
                try {
                    if (!this.f2421a) {
                        C0690hq.m1578a();
                    }
                    XMPushService.this.f2393a.mo1401b(this.f2421a);
                } catch (C0660gn e) {
                    AbstractC0407b.m72a(e);
                    XMPushService.this.m2466a(10, e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$n */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$n.class */
    public class C0791n extends AbstractC0786i {

        /* renamed from: a */
        C0814at.b f2423a;

        public C0791n(C0814at.b bVar) {
            super(4);
            this.f2423a = null;
            this.f2423a = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "rebind the client. " + this.f2423a.f2509g;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            try {
                this.f2423a.m2607a(C0814at.c.unbind, 1, 16, (String) null, (String) null);
                XMPushService.this.f2393a.mo1377a(this.f2423a.f2509g, this.f2423a.f2503b);
                this.f2423a.m2607a(C0814at.c.binding, 1, 16, (String) null, (String) null);
                XMPushService.this.f2393a.mo1376a(this.f2423a);
            } catch (C0660gn e) {
                AbstractC0407b.m72a(e);
                XMPushService.this.m2466a(10, e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$o */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$o.class */
    public class C0792o extends AbstractC0786i {
        C0792o() {
            super(3);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "reset the connection.";
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            XMPushService.this.m2466a(11, (Exception) null);
            if (XMPushService.this.m2477a()) {
                XMPushService.this.m2455f();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$p */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMPushService$p.class */
    public class C0793p extends AbstractC0786i {

        /* renamed from: a */
        C0814at.b f2426a;

        /* renamed from: a */
        String f2427a;

        /* renamed from: b */
        int f2428b;

        /* renamed from: b */
        String f2429b;

        public C0793p(C0814at.b bVar, int i, String str, String str2) {
            super(9);
            this.f2426a = null;
            this.f2426a = bVar;
            this.f2428b = i;
            this.f2427a = str;
            this.f2429b = str2;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public String mo1440a() {
            return "unbind the channel. " + this.f2426a.f2509g;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
        /* renamed from: a */
        public void mo1441a() {
            if (this.f2426a.f2497a != C0814at.c.unbind && XMPushService.this.f2393a != null) {
                try {
                    XMPushService.this.f2393a.mo1377a(this.f2426a.f2509g, this.f2426a.f2503b);
                } catch (C0660gn e) {
                    AbstractC0407b.m72a(e);
                    XMPushService.this.m2466a(10, e);
                }
            }
            this.f2426a.m2607a(C0814at.c.unbind, this.f2428b, 0, this.f2429b, this.f2427a);
        }
    }

    static {
        C0568dc.m877a("app.chat.xiaomi.net", "app.chat.xiaomi.net");
        C0568dc.m877a("app.chat.xiaomi.net", "42.62.94.2:443");
        C0568dc.m877a("app.chat.xiaomi.net", "114.54.23.2");
        C0568dc.m877a("app.chat.xiaomi.net", "111.13.142.2");
        C0568dc.m877a("app.chat.xiaomi.net", "111.206.200.2");
        f2386a = 1;
    }

    @TargetApi(11)
    /* renamed from: a */
    public static Notification m2423a(Context context) {
        Intent intent = new Intent(context, (Class<?>) XMPushService.class);
        if (Build.VERSION.SDK_INT >= 11) {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(context.getApplicationInfo().icon);
            builder.setContentTitle("Push Service");
            builder.setContentText("Push Service");
            builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0));
            return builder.getNotification();
        }
        Notification notification = new Notification();
        try {
            notification.getClass().getMethod("setLatestEventInfo", Context.class, CharSequence.class, CharSequence.class, PendingIntent.class).invoke(notification, context, "Push Service", "Push Service", PendingIntent.getService(context, 0, intent, 0));
            return notification;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return notification;
        }
    }

    /* renamed from: a */
    private AbstractC0666gt m2427a(AbstractC0666gt abstractC0666gt, String str, String str2) {
        StringBuilder sb;
        String str3;
        C0814at m2578a = C0814at.m2578a();
        List<String> m2584a = m2578a.m2584a(str);
        if (m2584a.isEmpty()) {
            sb = new StringBuilder();
            str3 = "open channel should be called first before sending a packet, pkg=";
        } else {
            abstractC0666gt.m1501o(str);
            String m1492k = abstractC0666gt.m1492k();
            str = m1492k;
            if (TextUtils.isEmpty(m1492k)) {
                str = m2584a.get(0);
                abstractC0666gt.m1495l(str);
            }
            C0814at.b m2581a = m2578a.m2581a(str, abstractC0666gt.m1496m());
            if (!m2483c()) {
                sb = new StringBuilder();
                str3 = "drop a packet as the channel is not connected, chid=";
            } else {
                if (m2581a != null && m2581a.f2497a == C0814at.c.binded) {
                    if (TextUtils.equals(str2, m2581a.f2511i)) {
                        return abstractC0666gt;
                    }
                    sb = new StringBuilder();
                    sb.append("invalid session. ");
                    sb.append(str2);
                    AbstractC0407b.m70a(sb.toString());
                    return null;
                }
                sb = new StringBuilder();
                str3 = "drop a packet as the channel is not opened, chid=";
            }
        }
        sb.append(str3);
        sb.append(str);
        AbstractC0407b.m70a(sb.toString());
        return null;
    }

    /* renamed from: a */
    private C0814at.b m2429a(String str, Intent intent) {
        C0814at.b m2581a = C0814at.m2578a().m2581a(str, intent.getStringExtra(AbstractC0818ax.f2552p));
        C0814at.b bVar = m2581a;
        if (m2581a == null) {
            bVar = new C0814at.b(this);
        }
        bVar.f2509g = intent.getStringExtra(AbstractC0818ax.f2554r);
        bVar.f2503b = intent.getStringExtra(AbstractC0818ax.f2552p);
        bVar.f2505c = intent.getStringExtra(AbstractC0818ax.f2556t);
        bVar.f2499a = intent.getStringExtra(AbstractC0818ax.f2562z);
        bVar.f2507e = intent.getStringExtra(AbstractC0818ax.f2560x);
        bVar.f2508f = intent.getStringExtra(AbstractC0818ax.f2561y);
        bVar.f2501a = intent.getBooleanExtra(AbstractC0818ax.f2559w, false);
        bVar.f2510h = intent.getStringExtra(AbstractC0818ax.f2558v);
        bVar.f2511i = intent.getStringExtra(AbstractC0818ax.f2529C);
        bVar.f2506d = intent.getStringExtra(AbstractC0818ax.f2557u);
        bVar.f2498a = this.f2399a;
        bVar.m2605a((Messenger) intent.getParcelableExtra(AbstractC0818ax.f2533G));
        bVar.f2491a = StubApp.getOrigApplicationContext(getApplicationContext());
        C0814at.m2578a().m2589a(bVar);
        return bVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0090  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00d7 -> B:5:0x0026). Please report as a decompilation issue!!! */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m2431a() {
        /*
            r6 = this;
            com.xiaomi.push.C0499ao.m464a()
            long r0 = android.os.SystemClock.elapsedRealtime()
            r7 = r0
            java.lang.Object r0 = new java.lang.Object
            r1 = r0
            r1.<init>()
            r13 = r0
            java.lang.String r0 = "com.xiaomi.xmsf"
            r1 = r6
            java.lang.String r1 = r1.getPackageName()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L7f
            r0 = r6
            com.xiaomi.push.service.ba r0 = com.xiaomi.push.service.C0822ba.m2613a(r0)
            r14 = r0
            r0 = 0
            r12 = r0
        L26:
            r0 = r12
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L3a
            r0 = r12
            r11 = r0
            r0 = r14
            int r0 = r0.m2614a()
            if (r0 != 0) goto L84
        L3a:
            r0 = r12
            r11 = r0
            r0 = r12
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L62
            java.lang.String r0 = "ro.miui.region"
            java.lang.String r0 = com.xiaomi.push.C0770n.m2401a(r0)
            r12 = r0
            r0 = r12
            r11 = r0
            r0 = r12
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L62
            java.lang.String r0 = "ro.product.locale.region"
            java.lang.String r0 = com.xiaomi.push.C0770n.m2401a(r0)
            r11 = r0
        L62:
            r0 = r13
            monitor-enter(r0)     // Catch: java.lang.InterruptedException -> Ld5
            r0 = r13
            r1 = 100
            r0.wait(r1)     // Catch: java.lang.Throwable -> L77
            r0 = r13
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            r0 = r11
            r12 = r0
            goto L26
        L77:
            r12 = move-exception
            r0 = r13
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            r0 = r12
            throw r0     // Catch: java.lang.InterruptedException -> Ld5
        L7f:
            java.lang.String r0 = com.xiaomi.push.C0770n.m2405b()     // Catch: java.lang.InterruptedException -> Ld5
            r11 = r0
        L84:
            r0 = r11
            r12 = r0
            r0 = r11
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L9a
            r0 = r11
            com.xiaomi.push.q r0 = com.xiaomi.push.C0770n.m2399a(r0)
            java.lang.String r0 = r0.name()
            r12 = r0
        L9a:
            long r0 = android.os.SystemClock.elapsedRealtime()
            r9 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r11 = r0
            r0 = r11
            java.lang.String r1 = "wait region :"
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r11
            r1 = r12
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r11
            java.lang.String r1 = " cost = "
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r11
            r1 = r9
            r2 = r7
            long r1 = r1 - r2
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r11
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m70a(r0)
            r0 = r12
            return r0
        Ld5:
            r12 = move-exception
            r0 = r11
            r12 = r0
            goto L26
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.XMPushService.m2431a():java.lang.String");
    }

    /* renamed from: a */
    private void m2433a(Intent intent) {
        C0641fv c0641fv;
        String stringExtra = intent.getStringExtra(AbstractC0818ax.f2562z);
        String stringExtra2 = intent.getStringExtra(AbstractC0818ax.f2529C);
        Bundle bundleExtra = intent.getBundleExtra("ext_packet");
        C0814at m2578a = C0814at.m2578a();
        if (bundleExtra != null) {
            C0665gs c0665gs = (C0665gs) m2427a(new C0665gs(bundleExtra), stringExtra, stringExtra2);
            if (c0665gs == null) {
                return;
            } else {
                c0641fv = C0641fv.m1321a(c0665gs, m2578a.m2581a(c0665gs.m1492k(), c0665gs.m1496m()).f2510h);
            }
        } else {
            byte[] byteArrayExtra = intent.getByteArrayExtra("ext_raw_packet");
            c0641fv = null;
            if (byteArrayExtra != null) {
                long longExtra = intent.getLongExtra(AbstractC0818ax.f2552p, 0L);
                String stringExtra3 = intent.getStringExtra(AbstractC0818ax.f2553q);
                String stringExtra4 = intent.getStringExtra("ext_chid");
                C0814at.b m2581a = m2578a.m2581a(stringExtra4, Long.toString(longExtra));
                c0641fv = null;
                if (m2581a != null) {
                    c0641fv = new C0641fv();
                    try {
                        c0641fv.m1328a(Integer.parseInt(stringExtra4));
                    } catch (NumberFormatException e) {
                    }
                    c0641fv.m1331a("SECMSG", (String) null);
                    c0641fv.m1329a(longExtra, "xiaomi.com", stringExtra3);
                    c0641fv.m1330a(intent.getStringExtra("ext_pkt_id"));
                    c0641fv.m1333a(byteArrayExtra, m2581a.f2510h);
                }
            }
        }
        if (c0641fv != null) {
            m2448c(new C0826be(this, c0641fv));
        }
    }

    /* renamed from: a */
    private void m2434a(Intent intent, int i) {
        byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
        boolean booleanExtra = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
        C0732je c0732je = new C0732je();
        try {
            C0743jp.m2313a(c0732je, byteArrayExtra);
            C0493ai.m439a(StubApp.getOrigApplicationContext(getApplicationContext())).m446a((C0493ai.a) new C0821b(c0732je, new WeakReference(this), booleanExtra), i);
        } catch (C0749jv e) {
            AbstractC0407b.m75d("aw_ping : send help app ping  error");
        }
    }

    /* renamed from: a */
    private void m2437a(String str, int i) {
        Collection<C0814at.b> m2583a = C0814at.m2578a().m2583a(str);
        if (m2583a != null) {
            for (C0814at.b bVar : m2583a) {
                if (bVar != null) {
                    m2468a(new C0793p(bVar, i, null, null));
                }
            }
        }
        C0814at.m2578a().m2590a(str);
    }

    /* renamed from: a */
    public static boolean m2438a(int i, String str) {
        if (TextUtils.equals(str, "Enter") && i == 1) {
            return true;
        }
        return TextUtils.equals(str, "Leave") && i == 2;
    }

    /* renamed from: a */
    private boolean m2441a(String str, Intent intent) {
        C0814at.b m2581a = C0814at.m2578a().m2581a(str, intent.getStringExtra(AbstractC0818ax.f2552p));
        boolean z = false;
        if (m2581a != null) {
            z = false;
            if (str != null) {
                String stringExtra = intent.getStringExtra(AbstractC0818ax.f2529C);
                String stringExtra2 = intent.getStringExtra(AbstractC0818ax.f2558v);
                z = false;
                if (!TextUtils.isEmpty(m2581a.f2511i)) {
                    z = false;
                    if (!TextUtils.equals(stringExtra, m2581a.f2511i)) {
                        AbstractC0407b.m70a("session changed. old session=" + m2581a.f2511i + ", new session=" + stringExtra + " chid = " + str);
                        z = true;
                    }
                }
                if (!stringExtra2.equals(m2581a.f2510h)) {
                    AbstractC0407b.m70a("security changed. chid = " + str + " sechash = " + C0508ax.m518a(stringExtra2));
                    z = true;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m2442a(String str, String str2, Context context) {
        if (TextUtils.equals("Leave", str) && !TextUtils.equals("Enter", C0856g.m2692a(context).m2699a(str2))) {
            return false;
        }
        if (C0856g.m2692a(context).m2696a(str2, str) != 0) {
            return true;
        }
        AbstractC0407b.m70a("update geofence statue failed geo_id:" + str2);
        return false;
    }

    /* renamed from: b */
    private void m2443b(Intent intent) {
        String stringExtra = intent.getStringExtra(AbstractC0818ax.f2562z);
        String stringExtra2 = intent.getStringExtra(AbstractC0818ax.f2529C);
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
        C0665gs[] c0665gsArr = new C0665gs[parcelableArrayExtra.length];
        intent.getBooleanExtra("ext_encrypt", true);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < parcelableArrayExtra.length) {
                c0665gsArr[i2] = new C0665gs((Bundle) parcelableArrayExtra[i2]);
                c0665gsArr[i2] = (C0665gs) m2427a(c0665gsArr[i2], stringExtra, stringExtra2);
                if (c0665gsArr[i2] == null) {
                    return;
                } else {
                    i = i2 + 1;
                }
            } else {
                C0814at m2578a = C0814at.m2578a();
                C0641fv[] c0641fvArr = new C0641fv[c0665gsArr.length];
                int i3 = 0;
                while (true) {
                    int i4 = i3;
                    if (i4 >= c0665gsArr.length) {
                        m2448c(new C0848c(this, c0641fvArr));
                        return;
                    } else {
                        C0665gs c0665gs = c0665gsArr[i4];
                        c0641fvArr[i4] = C0641fv.m1321a(c0665gs, m2578a.m2581a(c0665gs.m1492k(), c0665gs.m1496m()).f2510h);
                        i3 = i4 + 1;
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private void m2445b(boolean z) {
        this.f2388a = System.currentTimeMillis();
        if (m2483c()) {
            if (this.f2393a.m1406d() || this.f2393a.m1408e() || C0503as.m486d(this)) {
                m2448c(new C0790m(z));
                return;
            }
            m2448c(new C0783f(17, null));
        }
        m2474a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m2446c() {
        String str;
        C0794a m2485a = C0794a.m2485a(StubApp.getOrigApplicationContext(getApplicationContext()));
        String m2490a = m2485a.m2490a();
        String str2 = m2490a;
        if (TextUtils.isEmpty(m2490a)) {
            str2 = m2431a();
        }
        if (TextUtils.isEmpty(str2)) {
            this.f2402a = EnumC0774q.China.name();
        } else {
            this.f2402a = str2;
            m2485a.m2491a(str2);
            if (EnumC0774q.Global.name().equals(this.f2402a)) {
                str = "app.chat.global.xiaomi.net";
            } else if (EnumC0774q.Europe.name().equals(this.f2402a)) {
                str = "fr.app.chat.global.xiaomi.net";
            } else if (EnumC0774q.Russia.name().equals(this.f2402a)) {
                str = "ru.app.chat.global.xiaomi.net";
            } else if (EnumC0774q.India.name().equals(this.f2402a)) {
                str = "idmb.app.chat.global.xiaomi.net";
            }
            C0650gd.m1412a(str);
        }
        if (m2458g()) {
            C0847bz c0847bz = new C0847bz(this, 11);
            m2468a(c0847bz);
            C0871t.m2801a(new C0849ca(this, c0847bz));
        }
        C0493ai.m439a(this).m446a((C0493ai.a) new C0855f(this), 86400);
        try {
            if (C0880v.m2851a()) {
                this.f2399a.m2679a(this);
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m2447c(Intent intent) {
        String str;
        C0853d c0853d;
        boolean z;
        int i;
        String format;
        AbstractC0786i c0791n;
        String m519b;
        int i2;
        String str2;
        AbstractC0786i c0852cd;
        C0814at.b m2581a;
        C0814at m2578a = C0814at.m2578a();
        boolean z2 = true;
        boolean z3 = true;
        if (AbstractC0818ax.f2540d.equalsIgnoreCase(intent.getAction()) || AbstractC0818ax.f2546j.equalsIgnoreCase(intent.getAction())) {
            String stringExtra = intent.getStringExtra(AbstractC0818ax.f2554r);
            if (!TextUtils.isEmpty(intent.getStringExtra(AbstractC0818ax.f2558v))) {
                if (stringExtra == null) {
                    str = "channel id is empty, do nothing!";
                    AbstractC0407b.m75d(str);
                    return;
                }
                boolean m2441a = m2441a(stringExtra, intent);
                C0814at.b m2429a = m2429a(stringExtra, intent);
                if (C0503as.m484b(this)) {
                    if (!m2483c()) {
                        m2474a(true);
                        return;
                    }
                    if (m2429a.f2497a == C0814at.c.unbind) {
                        c0791n = new C0778a(m2429a);
                    } else if (m2441a) {
                        c0791n = new C0791n(m2429a);
                    } else if (m2429a.f2497a == C0814at.c.binding) {
                        format = String.format("the client is binding. %1$s %2$s.", m2429a.f2509g, C0814at.b.m2596a(m2429a.f2503b));
                    } else {
                        if (m2429a.f2497a != C0814at.c.binded) {
                            return;
                        }
                        c0853d = this.f2399a;
                        z = true;
                        i = 0;
                    }
                    m2448c(c0791n);
                }
                c0853d = this.f2399a;
                z = false;
                i = 2;
                c0853d.m2682a(this, m2429a, z, i, null);
                return;
            }
            format = "security is empty. ignore.";
            AbstractC0407b.m70a(format);
            return;
        }
        if (AbstractC0818ax.f2545i.equalsIgnoreCase(intent.getAction())) {
            String stringExtra2 = intent.getStringExtra(AbstractC0818ax.f2562z);
            String stringExtra3 = intent.getStringExtra(AbstractC0818ax.f2554r);
            String stringExtra4 = intent.getStringExtra(AbstractC0818ax.f2552p);
            AbstractC0407b.m70a("Service called close channel chid = " + stringExtra3 + " res = " + C0814at.b.m2596a(stringExtra4));
            if (TextUtils.isEmpty(stringExtra3)) {
                Iterator<String> it = m2578a.m2584a(stringExtra2).iterator();
                while (it.hasNext()) {
                    m2437a(it.next(), 2);
                }
                return;
            } else if (TextUtils.isEmpty(stringExtra4)) {
                m2437a(stringExtra3, 2);
                return;
            } else {
                m2472a(stringExtra3, stringExtra4, 2, null, null);
                return;
            }
        }
        if (AbstractC0818ax.f2541e.equalsIgnoreCase(intent.getAction())) {
            m2433a(intent);
            return;
        }
        if (AbstractC0818ax.f2543g.equalsIgnoreCase(intent.getAction())) {
            m2443b(intent);
            return;
        }
        if (AbstractC0818ax.f2542f.equalsIgnoreCase(intent.getAction())) {
            AbstractC0666gt m2427a = m2427a(new C0664gr(intent.getBundleExtra("ext_packet")), intent.getStringExtra(AbstractC0818ax.f2562z), intent.getStringExtra(AbstractC0818ax.f2529C));
            if (m2427a == null) {
                return;
            } else {
                c0791n = new C0826be(this, C0641fv.m1321a(m2427a, m2578a.m2581a(m2427a.m1492k(), m2427a.m1496m()).f2510h));
            }
        } else {
            if (!AbstractC0818ax.f2544h.equalsIgnoreCase(intent.getAction())) {
                if (!AbstractC0818ax.f2547k.equals(intent.getAction())) {
                    if (AbstractC0818ax.f2548l.equals(intent.getAction())) {
                        String stringExtra5 = intent.getStringExtra(AbstractC0818ax.f2562z);
                        List<String> m2584a = m2578a.m2584a(stringExtra5);
                        if (!m2584a.isEmpty()) {
                            String stringExtra6 = intent.getStringExtra(AbstractC0818ax.f2554r);
                            String stringExtra7 = intent.getStringExtra(AbstractC0818ax.f2552p);
                            String str3 = stringExtra6;
                            if (TextUtils.isEmpty(stringExtra6)) {
                                str3 = m2584a.get(0);
                            }
                            if (TextUtils.isEmpty(stringExtra7)) {
                                Collection<C0814at.b> m2583a = m2578a.m2583a(str3);
                                m2581a = null;
                                if (m2583a != null) {
                                    m2581a = null;
                                    if (!m2583a.isEmpty()) {
                                        m2581a = m2583a.iterator().next();
                                    }
                                }
                            } else {
                                m2581a = m2578a.m2581a(str3, stringExtra7);
                            }
                            if (m2581a != null) {
                                if (intent.hasExtra(AbstractC0818ax.f2560x)) {
                                    m2581a.f2507e = intent.getStringExtra(AbstractC0818ax.f2560x);
                                }
                                if (intent.hasExtra(AbstractC0818ax.f2561y)) {
                                    m2581a.f2508f = intent.getStringExtra(AbstractC0818ax.f2561y);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        str2 = "open channel should be called first before update info, pkg=" + stringExtra5;
                    } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
                        if (C0822ba.m2613a(StubApp.getOrigApplicationContext(getApplicationContext())).m2616a() && C0822ba.m2613a(StubApp.getOrigApplicationContext(getApplicationContext())).m2614a() == 0) {
                            str2 = "register without being provisioned. " + intent.getStringExtra("mipush_app_package");
                        } else {
                            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                            String stringExtra8 = intent.getStringExtra("mipush_app_package");
                            boolean booleanExtra = intent.getBooleanExtra("mipush_env_chanage", false);
                            int intExtra = intent.getIntExtra("mipush_env_type", 1);
                            C0872u.m2803a(this).m2810d(stringExtra8);
                            if (!booleanExtra || "com.xiaomi.xmsf".equals(getPackageName())) {
                                m2475a(byteArrayExtra, stringExtra8);
                                return;
                            }
                            c0852cd = new C0852cd(this, 14, intExtra, byteArrayExtra, stringExtra8);
                        }
                    } else {
                        if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                            String stringExtra9 = intent.getStringExtra("mipush_app_package");
                            byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                            boolean booleanExtra2 = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                            if ("com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                                C0872u.m2803a(this).m2804a(stringExtra9);
                            }
                            m2473a(stringExtra9, byteArrayExtra2, booleanExtra2);
                            return;
                        }
                        if (!AbstractC0823bb.f2571a.equals(intent.getAction())) {
                            if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(intent.getAction())) {
                                String stringExtra10 = intent.getStringExtra(AbstractC0818ax.f2562z);
                                int intExtra2 = intent.getIntExtra(AbstractC0818ax.f2527A, -2);
                                if (TextUtils.isEmpty(stringExtra10)) {
                                    return;
                                }
                                if (intExtra2 >= -1) {
                                    C0803ai.m2524a(this, stringExtra10, intExtra2);
                                    return;
                                } else {
                                    C0803ai.m2525a(this, stringExtra10, intent.getStringExtra(AbstractC0818ax.f2531E), intent.getStringExtra(AbstractC0818ax.f2532F));
                                    return;
                                }
                            }
                            if ("com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(intent.getAction())) {
                                String stringExtra11 = intent.getStringExtra(AbstractC0818ax.f2562z);
                                String stringExtra12 = intent.getStringExtra(AbstractC0818ax.f2530D);
                                if (intent.hasExtra(AbstractC0818ax.f2528B)) {
                                    i2 = intent.getIntExtra(AbstractC0818ax.f2528B, 0);
                                    m519b = C0508ax.m519b(stringExtra11 + i2);
                                    z2 = false;
                                } else {
                                    m519b = C0508ax.m519b(stringExtra11);
                                    i2 = 0;
                                }
                                if (!TextUtils.isEmpty(stringExtra11) && TextUtils.equals(stringExtra12, m519b)) {
                                    if (z2) {
                                        C0803ai.m2536b((Context) this, stringExtra11);
                                        return;
                                    } else {
                                        C0803ai.m2537b(this, stringExtra11, i2);
                                        return;
                                    }
                                }
                                str = "invalid notification for " + stringExtra11;
                                AbstractC0407b.m75d(str);
                                return;
                            }
                            if ("com.xiaomi.mipush.DISABLE_PUSH".equals(intent.getAction())) {
                                String stringExtra13 = intent.getStringExtra("mipush_app_package");
                                if (!TextUtils.isEmpty(stringExtra13)) {
                                    C0872u.m2803a(this).m2806b(stringExtra13);
                                }
                                if ("com.xiaomi.xmsf".equals(getPackageName())) {
                                    return;
                                }
                                C0782e c0782e = this.f2396a;
                                if (c0782e != null) {
                                    unregisterReceiver(c0782e);
                                    this.f2396a = null;
                                }
                                this.f2400a.m2734b();
                                m2468a(new C0838bq(this, 2));
                                C0814at.m2578a().m2592b();
                                C0814at.m2578a().m2587a(this, 0);
                                C0814at.m2578a().m2585a();
                                C0830bi.m2642a().m2652a();
                                C0632fm.m1268a();
                                return;
                            }
                            if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                                String stringExtra14 = intent.getStringExtra("mipush_app_package");
                                byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
                                String stringExtra15 = intent.getStringExtra("mipush_app_id");
                                String stringExtra16 = intent.getStringExtra("mipush_app_token");
                                if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                                    C0872u.m2803a(this).m2808c(stringExtra14);
                                }
                                if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                                    C0872u.m2803a(this).m2811e(stringExtra14);
                                    C0872u.m2803a(this).m2812f(stringExtra14);
                                }
                                if (byteArrayExtra3 == null) {
                                    C0874w.m2814a(this, stringExtra14, byteArrayExtra3, ErrorCode.ERROR_INVALID_PAYLOAD, "null payload");
                                    return;
                                }
                                C0874w.m2818b(stringExtra14, byteArrayExtra3);
                                m2468a(new C0873v(this, stringExtra14, stringExtra15, stringExtra16, byteArrayExtra3));
                                if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction()) && this.f2396a == null) {
                                    this.f2396a = new C0782e();
                                    registerReceiver(this.f2396a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                                    return;
                                }
                                return;
                            }
                            if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                                String stringExtra17 = intent.getStringExtra("mipush_app_package");
                                byte[] byteArrayExtra4 = intent.getByteArrayExtra("mipush_payload");
                                C0702ib c0702ib = new C0702ib();
                                try {
                                    C0743jp.m2313a(c0702ib, byteArrayExtra4);
                                    C0694hu.m1605a(this).m1610a(c0702ib, stringExtra17);
                                    return;
                                } catch (C0749jv e) {
                                    AbstractC0407b.m72a(e);
                                    return;
                                }
                            }
                            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
                                AbstractC0407b.m70a("Service called on timer");
                                C0632fm.m1271a(false);
                                if (!m2454e()) {
                                    return;
                                }
                            } else {
                                if (!"com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                                    if ("com.xiaomi.mipush.thirdparty".equals(intent.getAction())) {
                                        AbstractC0407b.m70a("on thirdpart push :" + intent.getStringExtra("com.xiaomi.mipush.thirdparty_DESC"));
                                        C0632fm.m1270a(this, intent.getIntExtra("com.xiaomi.mipush.thirdparty_LEVEL", 0));
                                        return;
                                    }
                                    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                                        m2451d();
                                        return;
                                    }
                                    if ("action_cr_config".equals(intent.getAction())) {
                                        boolean booleanExtra3 = intent.getBooleanExtra("action_cr_event_switch", false);
                                        long longExtra = intent.getLongExtra("action_cr_event_frequency", 86400L);
                                        boolean booleanExtra4 = intent.getBooleanExtra("action_cr_perf_switch", false);
                                        long longExtra2 = intent.getLongExtra("action_cr_perf_frequency", 86400L);
                                        boolean booleanExtra5 = intent.getBooleanExtra("action_cr_event_en", true);
                                        long longExtra3 = intent.getLongExtra("action_cr_max_file_size", Config.DEFAULT_MAX_FILE_LENGTH);
                                        Config build = Config.getBuilder().setEventUploadSwitchOpen(booleanExtra3).setEventUploadFrequency(longExtra).setPerfUploadSwitchOpen(booleanExtra4).setPerfUploadFrequency(longExtra2).setAESKey(C0516be.m566a(StubApp.getOrigApplicationContext(getApplicationContext()))).setEventEncrypted(booleanExtra5).setMaxFileLength(longExtra3).build(StubApp.getOrigApplicationContext(getApplicationContext()));
                                        if ("com.xiaomi.xmsf".equals(getPackageName()) || longExtra <= 0 || longExtra2 <= 0 || longExtra3 <= 0) {
                                            return;
                                        }
                                        C0630fk.m1251a(StubApp.getOrigApplicationContext(getApplicationContext()), build);
                                        return;
                                    }
                                    if (!"action_help_ping".equals(intent.getAction())) {
                                        if ("action_aw_app_logic".equals(intent.getAction())) {
                                            m2452d(intent);
                                            return;
                                        }
                                        return;
                                    }
                                    boolean booleanExtra6 = intent.getBooleanExtra("extra_help_ping_switch", false);
                                    int intExtra3 = intent.getIntExtra("extra_help_ping_frequency", 0);
                                    int i3 = intExtra3;
                                    if (intExtra3 >= 0) {
                                        i3 = intExtra3;
                                        if (intExtra3 < 30) {
                                            AbstractC0407b.m74c("aw_ping: frquency need > 30s.");
                                            i3 = 30;
                                        }
                                    }
                                    if (i3 < 0) {
                                        booleanExtra6 = false;
                                    }
                                    AbstractC0407b.m70a("aw_ping: receive a aw_ping message. switch: " + booleanExtra6 + " frequency: " + i3);
                                    if (!booleanExtra6 || i3 <= 0 || "com.xiaomi.xmsf".equals(getPackageName())) {
                                        return;
                                    }
                                    m2434a(intent, i3);
                                    return;
                                }
                                AbstractC0407b.m70a("Service called on check alive.");
                                if (!m2454e()) {
                                    return;
                                }
                            }
                            m2445b(false);
                            return;
                        }
                        String stringExtra18 = intent.getStringExtra("uninstall_pkg_name");
                        if (stringExtra18 == null || TextUtils.isEmpty(stringExtra18.trim())) {
                            return;
                        }
                        try {
                            getPackageManager().getPackageInfo(stringExtra18, 0);
                            z3 = false;
                        } catch (PackageManager.NameNotFoundException e2) {
                        }
                        if (!"com.xiaomi.channel".equals(stringExtra18) || C0814at.m2578a().m2583a("1").isEmpty() || !z3) {
                            SharedPreferences sharedPreferences = getSharedPreferences("pref_registered_pkg_names", 0);
                            String string = sharedPreferences.getString(stringExtra18, null);
                            if (TextUtils.isEmpty(string) || !z3) {
                                return;
                            }
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.remove(stringExtra18);
                            edit.commit();
                            if (C0803ai.m2538b((Context) this, stringExtra18)) {
                                C0803ai.m2536b((Context) this, stringExtra18);
                            }
                            C0803ai.m2523a((Context) this, stringExtra18);
                            if (!m2483c() || string == null) {
                                return;
                            }
                            try {
                                C0800af.m2499a(this, C0800af.m2494a(stringExtra18, string));
                                AbstractC0407b.m70a("uninstall " + stringExtra18 + " msg sent");
                                return;
                            } catch (C0660gn e3) {
                                AbstractC0407b.m75d("Fail to send Message: " + e3.getMessage());
                                m2466a(10, e3);
                                return;
                            }
                        }
                        m2437a("1", 0);
                        str2 = "close the miliao channel as the app is uninstalled.";
                    }
                    AbstractC0407b.m70a(str2);
                    return;
                }
                String stringExtra19 = intent.getStringExtra(AbstractC0818ax.f2554r);
                String stringExtra20 = intent.getStringExtra(AbstractC0818ax.f2552p);
                if (stringExtra19 == null) {
                    return;
                }
                AbstractC0407b.m70a("request reset connection from chid = " + stringExtra19);
                C0814at.b m2581a2 = C0814at.m2578a().m2581a(stringExtra19, stringExtra20);
                if (m2581a2 == null || !m2581a2.f2510h.equals(intent.getStringExtra(AbstractC0818ax.f2558v)) || m2581a2.f2497a != C0814at.c.binded) {
                    return;
                }
                AbstractC0649gc m2462a = m2462a();
                if (m2462a != null && m2462a.m1394a(System.currentTimeMillis() - 15000)) {
                    return;
                } else {
                    c0852cd = new C0792o();
                }
                m2448c(c0852cd);
                return;
            }
            AbstractC0666gt m2427a2 = m2427a(new C0668gv(intent.getBundleExtra("ext_packet")), intent.getStringExtra(AbstractC0818ax.f2562z), intent.getStringExtra(AbstractC0818ax.f2529C));
            if (m2427a2 == null) {
                return;
            } else {
                c0791n = new C0826be(this, C0641fv.m1321a(m2427a2, m2578a.m2581a(m2427a2.m1492k(), m2427a2.m1496m()).f2510h));
            }
        }
        m2448c(c0791n);
    }

    /* renamed from: c */
    private void m2448c(AbstractC0786i abstractC0786i) {
        this.f2400a.m2730a(abstractC0786i);
    }

    /* renamed from: c */
    private void m2450c(boolean z) {
        try {
            if (C0880v.m2851a()) {
                sendBroadcast(z ? new Intent("miui.intent.action.NETWORK_CONNECTED") : new Intent("miui.intent.action.NETWORK_BLOCKED"));
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }

    /* renamed from: d */
    private void m2451d() {
        NetworkInfo networkInfo;
        try {
            networkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            networkInfo = null;
        }
        if (networkInfo != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("network changed,");
            sb.append("[type: " + networkInfo.getTypeName() + "[" + networkInfo.getSubtypeName() + "], state: " + networkInfo.getState() + "/" + networkInfo.getDetailedState());
            AbstractC0407b.m70a(sb.toString());
            NetworkInfo.State state = networkInfo.getState();
            if (state == NetworkInfo.State.SUSPENDED || state == NetworkInfo.State.UNKNOWN) {
                return;
            }
        } else {
            AbstractC0407b.m70a("network changed, no active network");
        }
        if (C0688ho.m1567a() != null) {
            C0688ho.m1567a().m1564a();
        }
        C0681hh.m1542a((Context) this);
        this.f2392a.m1407e();
        if (C0503as.m484b(this)) {
            if (m2483c() && m2454e()) {
                m2445b(false);
            }
            if (!m2483c() && !m2484d()) {
                this.f2400a.m2728a(1);
                m2468a(new C0781d());
            }
            C0577dl.m928a(this).m936a();
        } else {
            m2468a(new C0783f(2, null));
        }
        m2453e();
    }

    /* renamed from: d */
    private void m2452d(Intent intent) {
        int i;
        try {
            C0620fa.m1220a(StubApp.getOrigApplicationContext(getApplicationContext())).m1229a(new C0820az());
            String stringExtra = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra == null) {
                return;
            }
            C0732je c0732je = new C0732je();
            C0743jp.m2313a(c0732je, byteArrayExtra);
            String m2072b = c0732je.m2072b();
            Map<String, String> m2064a = c0732je.m2064a();
            if (m2064a != null) {
                String str = m2064a.get("extra_help_aw_info");
                String str2 = m2064a.get("extra_aw_app_online_cmd");
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                try {
                    i = Integer.parseInt(str2);
                } catch (NumberFormatException e) {
                    i = 0;
                }
                if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(m2072b) || TextUtils.isEmpty(str)) {
                    return;
                }
                C0620fa.m1220a(StubApp.getOrigApplicationContext(getApplicationContext())).m1227a(this, str, i, stringExtra, m2072b);
            }
        } catch (C0749jv e2) {
            AbstractC0407b.m75d("aw_logic: translate fail. " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m2453e() {
        if (!m2477a()) {
            C0632fm.m1268a();
        } else {
            if (C0632fm.m1272a()) {
                return;
            }
            C0632fm.m1271a(true);
        }
    }

    /* renamed from: e */
    private boolean m2454e() {
        if (System.currentTimeMillis() - this.f2388a < 30000) {
            return false;
        }
        return C0503as.m485c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m2455f() {
        String str;
        AbstractC0649gc abstractC0649gc = this.f2393a;
        if (abstractC0649gc == null || !abstractC0649gc.m1402b()) {
            AbstractC0649gc abstractC0649gc2 = this.f2393a;
            if (abstractC0649gc2 == null || !abstractC0649gc2.m1404c()) {
                this.f2394a.m1419b(C0503as.m475a((Context) this));
                m2457g();
                if (this.f2393a == null) {
                    C0814at.m2578a().m2586a(this);
                    m2450c(false);
                    return;
                }
                return;
            }
            str = "try to connect while is connected.";
        } else {
            str = "try to connect while connecting.";
        }
        AbstractC0407b.m75d(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public boolean m2456f() {
        boolean z = false;
        if ("com.xiaomi.xmsf".equals(getPackageName())) {
            z = false;
            if (Settings.Secure.getInt(getContentResolver(), "EXTREME_POWER_MODE_ENABLE", 0) == 1) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: g */
    private void m2457g() {
        try {
            this.f2392a.m1392a(this.f2395a, new C0841bt(this));
            this.f2392a.m1437f();
            this.f2393a = this.f2392a;
        } catch (C0660gn e) {
            AbstractC0407b.m71a("fail to create Slim connection", e);
            this.f2392a.mo1398b(3, e);
        }
    }

    /* renamed from: g */
    private boolean m2458g() {
        return "com.xiaomi.xmsf".equals(getPackageName()) || !C0872u.m2803a(this).m2807b(getPackageName());
    }

    /* renamed from: h */
    private void m2459h() {
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(f2387b, new Notification());
        } else {
            bindService(new Intent(this, (Class<?>) this.f2401a), new ServiceConnectionC0842bu(this), 1);
        }
    }

    /* renamed from: h */
    private boolean m2460h() {
        if (TextUtils.equals(getPackageName(), "com.xiaomi.xmsf")) {
            return false;
        }
        return C0809ao.m2557a(this).m2563a(EnumC0703ic.ForegroundServiceSwitch.m1669a(), false);
    }

    /* renamed from: i */
    private void m2461i() {
        synchronized (this.f2403a) {
            this.f2403a.clear();
        }
    }

    /* renamed from: a */
    public AbstractC0649gc m2462a() {
        return this.f2393a;
    }

    /* renamed from: a */
    public C0853d m2463a() {
        return new C0853d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2464a() {
        if (System.currentTimeMillis() - this.f2388a >= C0655gi.m1423a() && C0503as.m485c(this)) {
            m2445b(true);
        }
    }

    /* renamed from: a */
    public void m2465a(int i) {
        this.f2400a.m2728a(i);
    }

    /* renamed from: a */
    public void m2466a(int i, Exception exc) {
        StringBuilder sb = new StringBuilder();
        sb.append("disconnect ");
        sb.append(hashCode());
        sb.append(", ");
        AbstractC0649gc abstractC0649gc = this.f2393a;
        sb.append(abstractC0649gc == null ? null : Integer.valueOf(abstractC0649gc.hashCode()));
        AbstractC0407b.m70a(sb.toString());
        AbstractC0649gc abstractC0649gc2 = this.f2393a;
        if (abstractC0649gc2 != null) {
            abstractC0649gc2.mo1398b(i, exc);
            this.f2393a = null;
        }
        m2465a(7);
        m2465a(4);
        C0814at.m2578a().m2587a(this, i);
    }

    /* renamed from: a */
    public void m2467a(C0641fv c0641fv) {
        AbstractC0649gc abstractC0649gc = this.f2393a;
        if (abstractC0649gc == null) {
            throw new C0660gn("try send msg while connection is null.");
        }
        abstractC0649gc.mo1382b(c0641fv);
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo586a(AbstractC0649gc abstractC0649gc) {
        C0688ho.m1567a().mo586a(abstractC0649gc);
        m2450c(true);
        this.f2398a.m2628a();
        Iterator<C0814at.b> it = C0814at.m2578a().m2582a().iterator();
        while (it.hasNext()) {
            m2468a(new C0778a(it.next()));
        }
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo587a(AbstractC0649gc abstractC0649gc, int i, Exception exc) {
        C0688ho.m1567a().mo587a(abstractC0649gc, i, exc);
        m2474a(false);
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo588a(AbstractC0649gc abstractC0649gc, Exception exc) {
        C0688ho.m1567a().mo588a(abstractC0649gc, exc);
        m2450c(false);
        m2474a(false);
    }

    /* renamed from: a */
    public void m2468a(AbstractC0786i abstractC0786i) {
        m2469a(abstractC0786i, 0L);
    }

    /* renamed from: a */
    public void m2469a(AbstractC0786i abstractC0786i, long j) {
        try {
            this.f2400a.m2731a(abstractC0786i, j);
        } catch (IllegalStateException e) {
        }
    }

    /* renamed from: a */
    public void m2470a(InterfaceC0789l interfaceC0789l) {
        synchronized (this.f2403a) {
            this.f2403a.add(interfaceC0789l);
        }
    }

    /* renamed from: a */
    public void m2471a(C0814at.b bVar) {
        if (bVar != null) {
            long m2602a = bVar.m2602a();
            AbstractC0407b.m70a("schedule rebind job in " + (m2602a / 1000));
            m2469a(new C0778a(bVar), m2602a);
        }
    }

    /* renamed from: a */
    public void m2472a(String str, String str2, int i, String str3, String str4) {
        C0814at.b m2581a = C0814at.m2578a().m2581a(str, str2);
        if (m2581a != null) {
            m2468a(new C0793p(m2581a, i, str4, str3));
        }
        C0814at.m2578a().m2591a(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2473a(String str, byte[] bArr, boolean z) {
        Collection<C0814at.b> m2583a = C0814at.m2578a().m2583a("5");
        if (m2583a.isEmpty()) {
            if (!z) {
                return;
            }
        } else if (m2583a.iterator().next().f2497a == C0814at.c.binded) {
            m2468a(new C0839br(this, 4, str, bArr));
            return;
        } else if (!z) {
            return;
        }
        C0874w.m2818b(str, bArr);
    }

    /* renamed from: a */
    public void m2474a(boolean z) {
        this.f2398a.m2629a(z);
    }

    /* renamed from: a */
    public void m2475a(byte[] bArr, String str) {
        if (bArr == null) {
            C0874w.m2814a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, "null payload");
            AbstractC0407b.m70a("register request without payload");
            return;
        }
        C0729jb c0729jb = new C0729jb();
        try {
            C0743jp.m2313a(c0729jb, bArr);
            if (c0729jb.f1997a != EnumC0696hw.Registration) {
                C0874w.m2814a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " registration action required.");
                AbstractC0407b.m70a("register request with invalid payload");
                return;
            }
            C0733jf c0733jf = new C0733jf();
            try {
                C0743jp.m2313a(c0733jf, c0729jb.m2034a());
                C0874w.m2816a(c0729jb.m2037b(), bArr);
                m2468a(new C0873v(this, c0729jb.m2037b(), c0733jf.m2105b(), c0733jf.m2110c(), bArr));
            } catch (C0749jv e) {
                AbstractC0407b.m72a(e);
                C0874w.m2814a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " data action error.");
            }
        } catch (C0749jv e2) {
            AbstractC0407b.m72a(e2);
            C0874w.m2814a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " data container error.");
        }
    }

    /* renamed from: a */
    public void m2476a(C0641fv[] c0641fvArr) {
        AbstractC0649gc abstractC0649gc = this.f2393a;
        if (abstractC0649gc == null) {
            throw new C0660gn("try send msg while connection is null.");
        }
        abstractC0649gc.mo1379a(c0641fvArr);
    }

    /* renamed from: a */
    public boolean m2477a() {
        return C0503as.m484b(this) && C0814at.m2578a().m2580a() > 0 && !m2482b() && m2458g() && !m2456f();
    }

    /* renamed from: a */
    public boolean m2478a(int i) {
        return this.f2400a.m2733a(i);
    }

    /* renamed from: b */
    public C0853d m2479b() {
        return this.f2399a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2480b() {
        Iterator it = new ArrayList(this.f2403a).iterator();
        while (it.hasNext()) {
            ((InterfaceC0789l) it.next()).mo1594a();
        }
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: b */
    public void mo589b(AbstractC0649gc abstractC0649gc) {
        AbstractC0407b.m74c("begin to connect...");
        C0688ho.m1567a().mo589b(abstractC0649gc);
    }

    /* renamed from: b */
    public void m2481b(AbstractC0786i abstractC0786i) {
        this.f2400a.m2729a(abstractC0786i.f2660a, abstractC0786i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0040, code lost:            if (r0.getBoolean(null) != false) goto L9;     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m2482b() {
        /*
            r3 = this;
            r0 = 0
            r4 = r0
            java.lang.String r0 = "miui.os.Build"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Throwable -> L47
            r8 = r0
            r0 = r8
            java.lang.String r1 = "IS_CM_CUSTOMIZATION_TEST"
            java.lang.reflect.Field r0 = r0.getField(r1)     // Catch: java.lang.Throwable -> L47
            r6 = r0
            r0 = r8
            java.lang.String r1 = "IS_CU_CUSTOMIZATION_TEST"
            java.lang.reflect.Field r0 = r0.getField(r1)     // Catch: java.lang.Throwable -> L47
            r7 = r0
            r0 = r8
            java.lang.String r1 = "IS_CT_CUSTOMIZATION_TEST"
            java.lang.reflect.Field r0 = r0.getField(r1)     // Catch: java.lang.Throwable -> L47
            r8 = r0
            r0 = r6
            r1 = 0
            boolean r0 = r0.getBoolean(r1)     // Catch: java.lang.Throwable -> L47
            if (r0 != 0) goto L43
            r0 = r7
            r1 = 0
            boolean r0 = r0.getBoolean(r1)     // Catch: java.lang.Throwable -> L47
            if (r0 != 0) goto L43
            r0 = r8
            r1 = 0
            boolean r0 = r0.getBoolean(r1)     // Catch: java.lang.Throwable -> L47
            r5 = r0
            r0 = r5
            if (r0 == 0) goto L45
        L43:
            r0 = 1
            r4 = r0
        L45:
            r0 = r4
            return r0
        L47:
            r6 = move-exception
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.XMPushService.m2482b():boolean");
    }

    /* renamed from: c */
    public boolean m2483c() {
        AbstractC0649gc abstractC0649gc = this.f2393a;
        return abstractC0649gc != null && abstractC0649gc.m1404c();
    }

    /* renamed from: d */
    public boolean m2484d() {
        AbstractC0649gc abstractC0649gc = this.f2393a;
        return abstractC0649gc != null && abstractC0649gc.m1402b();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f2391a.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        Uri uriFor;
        super.onCreate();
        C0880v.m2850a((Context) this);
        C0870s m2795a = C0871t.m2795a((Context) this);
        if (m2795a != null) {
            C0486ab.m425a(m2795a.f2707a);
        }
        this.f2391a = new Messenger(new HandlerC0843bv(this));
        C0819ay.m2611a(this);
        this.f2394a = new C0844bw(this, null, 5222, "xiaomi.com", null);
        this.f2394a.m1415a(true);
        this.f2392a = new C0647ga(this, this.f2394a);
        this.f2399a = m2463a();
        C0632fm.m1269a(this);
        this.f2392a.m1391a(this);
        this.f2397a = new C0813as(this);
        this.f2398a = new C0825bd(this);
        new C0854e().m2686a();
        C0688ho.m1568a().m1574a(this);
        this.f2400a = new C0860k("Connection Controller Thread");
        C0814at m2578a = C0814at.m2578a();
        m2578a.m2592b();
        m2578a.m2588a(new C0845bx(this));
        if (m2460h()) {
            m2459h();
        }
        C0694hu.m1605a(this).m1609a(new C0862m(this), "UPLOADER_PUSH_CHANNEL");
        m2470a(new C0691hr(this));
        m2468a(new C0784g());
        if (m2458g()) {
            this.f2396a = new C0782e();
            registerReceiver(this.f2396a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if ("com.xiaomi.xmsf".equals(getPackageName()) && (uriFor = Settings.Secure.getUriFor("EXTREME_POWER_MODE_ENABLE")) != null) {
            this.f2390a = new C0846by(this, new Handler(Looper.getMainLooper()));
            try {
                getContentResolver().registerContentObserver(uriFor, false, this.f2390a);
            } catch (Throwable th) {
                AbstractC0407b.m70a("register observer err:" + th.getMessage());
            }
        }
        AbstractC0407b.m70a("XMPushService created pid = " + f2387b);
    }

    @Override // android.app.Service
    public void onDestroy() {
        C0782e c0782e = this.f2396a;
        if (c0782e != null) {
            unregisterReceiver(c0782e);
        }
        if ("com.xiaomi.xmsf".equals(getPackageName()) && this.f2390a != null) {
            try {
                getContentResolver().unregisterContentObserver(this.f2390a);
            } catch (Throwable th) {
                AbstractC0407b.m70a("unregister observer err:" + th.getMessage());
            }
        }
        this.f2400a.m2734b();
        m2468a(new C0840bs(this, 2));
        m2468a(new C0787j());
        C0814at.m2578a().m2592b();
        C0814at.m2578a().m2587a(this, 15);
        C0814at.m2578a().m2585a();
        this.f2392a.m1399b(this);
        C0830bi.m2642a().m2652a();
        C0632fm.m1268a();
        m2461i();
        super.onDestroy();
        AbstractC0407b.m70a("Service destroyed");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        C0785h c0785h;
        if (intent == null) {
            AbstractC0407b.m75d("onStart() with intent NULL");
        } else {
            AbstractC0407b.m74c(String.format("onStart() with intent.Action = %s, chid = %s", intent.getAction(), intent.getStringExtra(AbstractC0818ax.f2554r)));
        }
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
            if (this.f2400a.m2732a()) {
                AbstractC0407b.m75d("ERROR, the job controller is blocked.");
                C0814at.m2578a().m2587a(this, 14);
                stopSelf();
                return;
            }
            c0785h = new C0785h(intent);
        } else if ("com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
            return;
        } else {
            c0785h = new C0785h(intent);
        }
        m2468a(c0785h);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return f2386a;
    }
}
