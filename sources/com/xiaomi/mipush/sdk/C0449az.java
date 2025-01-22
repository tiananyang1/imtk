package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.C0508ax;
import com.xiaomi.push.C0574di;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0733jf;
import com.xiaomi.push.C0739jl;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0770n;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0697hx;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.InterfaceC0744jq;
import com.xiaomi.push.service.AbstractC0818ax;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.C0812ar;
import com.xiaomi.push.service.C0822ba;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.mipush.sdk.az */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/az.class */
public class C0449az {

    /* renamed from: a */
    private static C0449az f309a;

    /* renamed from: a */
    private static final ArrayList<a> f310a = new ArrayList<>();

    /* renamed from: b */
    private static boolean f311b;

    /* renamed from: a */
    private Context f312a;

    /* renamed from: a */
    private Handler f314a;

    /* renamed from: a */
    private Messenger f315a;

    /* renamed from: a */
    private boolean f319a;

    /* renamed from: a */
    private List<Message> f318a = new ArrayList();

    /* renamed from: c */
    private boolean f320c = false;

    /* renamed from: a */
    private Intent f313a = null;

    /* renamed from: a */
    private Integer f316a = null;

    /* renamed from: a */
    private String f317a = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.mipush.sdk.az$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/az$a.class */
    public static class a<T extends InterfaceC0744jq<T, ?>> {

        /* renamed from: a */
        EnumC0696hw f321a;

        /* renamed from: a */
        T f322a;

        /* renamed from: a */
        boolean f323a;

        a() {
        }
    }

    private C0449az(Context context) {
        this.f319a = false;
        this.f314a = null;
        this.f312a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.f319a = m237c();
        f311b = m240d();
        this.f314a = new HandlerC0451ba(this, Looper.getMainLooper());
        Intent m232b = m232b();
        if (m232b != null) {
            m233b(m232b);
        }
    }

    /* renamed from: a */
    private int m218a() {
        int i;
        synchronized (this) {
            i = this.f312a.getSharedPreferences("mipush_extra", 0).getInt(Constants.EXTRA_KEY_BOOT_SERVICE_MODE, -1);
        }
        return i;
    }

    /* renamed from: a */
    private Intent m220a() {
        return (!m262a() || "com.xiaomi.xmsf".equals(this.f312a.getPackageName())) ? m241e() : m238d();
    }

    /* renamed from: a */
    private Message m221a(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    /* renamed from: a */
    public static C0449az m224a(Context context) {
        C0449az c0449az;
        synchronized (C0449az.class) {
            try {
                if (f309a == null) {
                    f309a = new C0449az(context);
                }
                c0449az = f309a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c0449az;
    }

    /* renamed from: a */
    private String m227a() {
        try {
            return this.f312a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106 ? "com.xiaomi.push.service.XMPushService" : "com.xiaomi.xmsf.push.service.XMPushService";
        } catch (Exception e) {
            return "com.xiaomi.xmsf.push.service.XMPushService";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x016b  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m230a(java.lang.String r8, com.xiaomi.mipush.sdk.EnumC0455be r9, boolean r10, java.util.HashMap<java.lang.String, java.lang.String> r11) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.C0449az.m230a(java.lang.String, com.xiaomi.mipush.sdk.be, boolean, java.util.HashMap):void");
    }

    /* renamed from: b */
    private Intent m232b() {
        if (!"com.xiaomi.xmsf".equals(this.f312a.getPackageName())) {
            return m234c();
        }
        AbstractC0407b.m74c("pushChannel xmsf create own channel");
        return m241e();
    }

    /* renamed from: b */
    private void m233b(Intent intent) {
        try {
            if (C0770n.m2403a() || Build.VERSION.SDK_INT < 26) {
                this.f312a.startService(intent);
            } else {
                m239d(intent);
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }

    /* renamed from: c */
    private Intent m234c() {
        if (m262a()) {
            AbstractC0407b.m74c("pushChannel app start miui china channel");
            return m238d();
        }
        AbstractC0407b.m74c("pushChannel app start  own channel");
        return m241e();
    }

    /* renamed from: c */
    private void m235c(int i) {
        synchronized (this) {
            this.f312a.getSharedPreferences("mipush_extra", 0).edit().putInt(Constants.EXTRA_KEY_BOOT_SERVICE_MODE, i).commit();
        }
    }

    /* renamed from: c */
    private void m236c(Intent intent) {
        int m2561a = C0809ao.m2557a(this.f312a).m2561a(EnumC0703ic.ServiceBootMode.m1669a(), EnumC0697hx.START.m1615a());
        int m218a = m218a();
        boolean z = m2561a == EnumC0697hx.BIND.m1615a() && f311b;
        int m1615a = (z ? EnumC0697hx.BIND : EnumC0697hx.START).m1615a();
        if (m1615a != m218a) {
            m263a(m1615a);
        }
        if (z) {
            m239d(intent);
        } else {
            m233b(intent);
        }
    }

    /* renamed from: c */
    private boolean m237c() {
        try {
            PackageInfo packageInfo = this.f312a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode >= 105;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: d */
    private Intent m238d() {
        Intent intent = new Intent();
        String packageName = this.f312a.getPackageName();
        intent.setPackage("com.xiaomi.xmsf");
        intent.setClassName("com.xiaomi.xmsf", m227a());
        intent.putExtra("mipush_app_package", packageName);
        m243f();
        return intent;
    }

    /* renamed from: d */
    private void m239d(Intent intent) {
        synchronized (this) {
            if (this.f320c) {
                Message m221a = m221a(intent);
                if (this.f318a.size() >= 50) {
                    this.f318a.remove(0);
                }
                this.f318a.add(m221a);
                return;
            }
            if (this.f315a == null) {
                Context context = this.f312a;
                ServiceConnectionC0453bc serviceConnectionC0453bc = new ServiceConnectionC0453bc(this);
                Context context2 = this.f312a;
                context.bindService(intent, serviceConnectionC0453bc, 1);
                this.f320c = true;
                this.f318a.clear();
                this.f318a.add(m221a(intent));
            } else {
                try {
                    this.f315a.send(m221a(intent));
                } catch (RemoteException e) {
                    this.f315a = null;
                    this.f320c = false;
                }
            }
        }
    }

    /* renamed from: d */
    private boolean m240d() {
        if (!m262a()) {
            return true;
        }
        try {
            return this.f312a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 108;
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: e */
    private Intent m241e() {
        Intent intent = new Intent();
        String packageName = this.f312a.getPackageName();
        m244g();
        intent.setComponent(new ComponentName(this.f312a, "com.xiaomi.push.service.XMPushService"));
        intent.putExtra("mipush_app_package", packageName);
        return intent;
    }

    /* renamed from: e */
    private boolean m242e() {
        String packageName = this.f312a.getPackageName();
        boolean z = true;
        if (!packageName.contains("miui")) {
            if (packageName.contains("xiaomi") || (this.f312a.getApplicationInfo().flags & 1) != 0) {
                return true;
            }
            z = false;
        }
        return z;
    }

    /* renamed from: f */
    private void m243f() {
        try {
            this.f312a.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f312a, "com.xiaomi.push.service.XMPushService"), 2, 1);
        } catch (Throwable th) {
        }
    }

    /* renamed from: g */
    private void m244g() {
        try {
            this.f312a.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f312a, "com.xiaomi.push.service.XMPushService"), 1, 1);
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void m245a() {
        m233b(m220a());
    }

    /* renamed from: a */
    public void m246a(int i) {
        Intent m220a = m220a();
        m220a.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        m220a.putExtra(AbstractC0818ax.f2562z, this.f312a.getPackageName());
        m220a.putExtra(AbstractC0818ax.f2527A, i);
        m236c(m220a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m247a(int i, String str) {
        Intent m220a = m220a();
        m220a.setAction("com.xiaomi.mipush.thirdparty");
        m220a.putExtra("com.xiaomi.mipush.thirdparty_LEVEL", i);
        m220a.putExtra("com.xiaomi.mipush.thirdparty_DESC", str);
        m233b(m220a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m248a(Intent intent) {
        intent.fillIn(m220a(), 24);
        m236c(intent);
    }

    /* renamed from: a */
    public final void m249a(C0702ib c0702ib) {
        Intent m220a = m220a();
        byte[] m2314a = C0743jp.m2314a(c0702ib);
        if (m2314a == null) {
            AbstractC0407b.m70a("send TinyData failed, because tinyDataBytes is null.");
            return;
        }
        m220a.setAction("com.xiaomi.mipush.SEND_TINYDATA");
        m220a.putExtra("mipush_payload", m2314a);
        m233b(m220a);
    }

    /* renamed from: a */
    public final void m250a(C0733jf c0733jf, boolean z) {
        this.f313a = null;
        C0461d.m289a(this.f312a).f342a = c0733jf.m2098a();
        Intent m220a = m220a();
        byte[] m2314a = C0743jp.m2314a(C0442as.m194a(this.f312a, c0733jf, EnumC0696hw.Registration));
        if (m2314a == null) {
            AbstractC0407b.m70a("register fail, because msgBytes is null.");
            return;
        }
        m220a.setAction("com.xiaomi.mipush.REGISTER_APP");
        m220a.putExtra("mipush_app_id", C0461d.m289a(this.f312a).m293a());
        m220a.putExtra("mipush_payload", m2314a);
        m220a.putExtra("mipush_session", this.f317a);
        m220a.putExtra("mipush_env_chanage", z);
        m220a.putExtra("mipush_env_type", C0461d.m289a(this.f312a).m291a());
        if (C0503as.m484b(this.f312a) && m266b()) {
            m236c(m220a);
        } else {
            this.f313a = m220a;
        }
    }

    /* renamed from: a */
    public final void m251a(C0739jl c0739jl) {
        byte[] m2314a = C0743jp.m2314a(C0442as.m194a(this.f312a, c0739jl, EnumC0696hw.UnRegistration));
        if (m2314a == null) {
            AbstractC0407b.m70a("unregister fail, because msgBytes is null.");
            return;
        }
        Intent m220a = m220a();
        m220a.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        m220a.putExtra("mipush_app_id", C0461d.m289a(this.f312a).m293a());
        m220a.putExtra("mipush_payload", m2314a);
        m236c(m220a);
    }

    /* renamed from: a */
    public final <T extends InterfaceC0744jq<T, ?>> void m252a(T t, EnumC0696hw enumC0696hw, C0717iq c0717iq) {
        m254a((C0449az) t, enumC0696hw, !enumC0696hw.equals(EnumC0696hw.Registration), c0717iq);
    }

    /* renamed from: a */
    public <T extends InterfaceC0744jq<T, ?>> void m253a(T t, EnumC0696hw enumC0696hw, boolean z) {
        a aVar = new a();
        aVar.f322a = t;
        aVar.f321a = enumC0696hw;
        aVar.f323a = z;
        synchronized (f310a) {
            f310a.add(aVar);
            if (f310a.size() > 10) {
                f310a.remove(0);
            }
        }
    }

    /* renamed from: a */
    public final <T extends InterfaceC0744jq<T, ?>> void m254a(T t, EnumC0696hw enumC0696hw, boolean z, C0717iq c0717iq) {
        m256a(t, enumC0696hw, z, true, c0717iq, true);
    }

    /* renamed from: a */
    public final <T extends InterfaceC0744jq<T, ?>> void m255a(T t, EnumC0696hw enumC0696hw, boolean z, C0717iq c0717iq, boolean z2) {
        m256a(t, enumC0696hw, z, true, c0717iq, z2);
    }

    /* renamed from: a */
    public final <T extends InterfaceC0744jq<T, ?>> void m256a(T t, EnumC0696hw enumC0696hw, boolean z, boolean z2, C0717iq c0717iq, boolean z3) {
        m257a(t, enumC0696hw, z, z2, c0717iq, z3, this.f312a.getPackageName(), C0461d.m289a(this.f312a).m293a());
    }

    /* renamed from: a */
    public final <T extends InterfaceC0744jq<T, ?>> void m257a(T t, EnumC0696hw enumC0696hw, boolean z, boolean z2, C0717iq c0717iq, boolean z3, String str, String str2) {
        if (!C0461d.m289a(this.f312a).m309c()) {
            if (z2) {
                m253a((C0449az) t, enumC0696hw, z);
                return;
            } else {
                AbstractC0407b.m70a("drop the message before initialization.");
                return;
            }
        }
        C0729jb m195a = C0442as.m195a(this.f312a, t, enumC0696hw, z, str, str2);
        if (c0717iq != null) {
            m195a.m2024a(c0717iq);
        }
        byte[] m2314a = C0743jp.m2314a(m195a);
        if (m2314a == null) {
            AbstractC0407b.m70a("send message fail, because msgBytes is null.");
            return;
        }
        C0574di.m919a(this.f312a.getPackageName(), this.f312a, t, enumC0696hw, m2314a.length);
        Intent m220a = m220a();
        m220a.setAction("com.xiaomi.mipush.SEND_MESSAGE");
        m220a.putExtra("mipush_payload", m2314a);
        m220a.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
        m236c(m220a);
    }

    /* renamed from: a */
    public final void m258a(String str, EnumC0455be enumC0455be, EnumC0463f enumC0463f) {
        C0439ap.m186a(this.f312a).m189a(enumC0455be, "syncing");
        m230a(str, enumC0455be, false, C0466i.m343a(this.f312a, enumC0463f));
    }

    /* renamed from: a */
    public void m259a(String str, String str2) {
        Intent m220a = m220a();
        m220a.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        m220a.putExtra(AbstractC0818ax.f2562z, this.f312a.getPackageName());
        m220a.putExtra(AbstractC0818ax.f2531E, str);
        m220a.putExtra(AbstractC0818ax.f2532F, str2);
        m236c(m220a);
    }

    /* renamed from: a */
    public final void m260a(boolean z) {
        m261a(z, (String) null);
    }

    /* renamed from: a */
    public final void m261a(boolean z, String str) {
        EnumC0455be enumC0455be;
        if (z) {
            C0439ap.m186a(this.f312a).m189a(EnumC0455be.DISABLE_PUSH, "syncing");
            C0439ap.m186a(this.f312a).m189a(EnumC0455be.ENABLE_PUSH, "");
            enumC0455be = EnumC0455be.DISABLE_PUSH;
        } else {
            C0439ap.m186a(this.f312a).m189a(EnumC0455be.ENABLE_PUSH, "syncing");
            C0439ap.m186a(this.f312a).m189a(EnumC0455be.DISABLE_PUSH, "");
            enumC0455be = EnumC0455be.ENABLE_PUSH;
        }
        m230a(str, enumC0455be, true, (HashMap<String, String>) null);
    }

    /* renamed from: a */
    public boolean m262a() {
        return this.f319a && 1 == C0461d.m289a(this.f312a).m291a();
    }

    /* renamed from: a */
    public boolean m263a(int i) {
        if (!C0461d.m289a(this.f312a).m307b()) {
            return false;
        }
        m235c(i);
        C0732je c0732je = new C0732je();
        c0732je.m2058a(C0812ar.m2571a());
        c0732je.m2071b(C0461d.m289a(this.f312a).m293a());
        c0732je.m2079d(this.f312a.getPackageName());
        c0732je.m2075c(EnumC0714in.ClientABTest.f1752a);
        c0732je.f2033a = new HashMap();
        c0732je.f2033a.put("boot_mode", i + "");
        m224a(this.f312a).m254a((C0449az) c0732je, EnumC0696hw.Notification, false, (C0717iq) null);
        return true;
    }

    /* renamed from: b */
    public final void m264b() {
        Intent m220a = m220a();
        m220a.setAction("com.xiaomi.mipush.DISABLE_PUSH");
        m236c(m220a);
    }

    /* renamed from: b */
    public void m265b(int i) {
        Intent m220a = m220a();
        m220a.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        m220a.putExtra(AbstractC0818ax.f2562z, this.f312a.getPackageName());
        m220a.putExtra(AbstractC0818ax.f2528B, i);
        m220a.putExtra(AbstractC0818ax.f2530D, C0508ax.m519b(this.f312a.getPackageName() + i));
        m236c(m220a);
    }

    /* renamed from: b */
    public boolean m266b() {
        boolean z = true;
        if (m262a()) {
            z = true;
            if (m242e()) {
                if (this.f316a == null) {
                    this.f316a = Integer.valueOf(C0822ba.m2613a(this.f312a).m2614a());
                    if (this.f316a.intValue() == 0) {
                        this.f312a.getContentResolver().registerContentObserver(C0822ba.m2613a(this.f312a).m2615a(), false, new C0452bb(this, new Handler(Looper.getMainLooper())));
                    }
                }
                if (this.f316a.intValue() != 0) {
                    return true;
                }
                z = false;
            }
        }
        return z;
    }

    /* renamed from: c */
    public void m267c() {
        Intent intent = this.f313a;
        if (intent != null) {
            m236c(intent);
            this.f313a = null;
        }
    }

    /* renamed from: d */
    public void m268d() {
        synchronized (f310a) {
            Iterator<a> it = f310a.iterator();
            while (it.hasNext()) {
                a next = it.next();
                m256a(next.f322a, next.f321a, next.f323a, false, null, true);
            }
            f310a.clear();
        }
    }

    /* renamed from: e */
    public void m269e() {
        Intent m220a = m220a();
        m220a.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        m220a.putExtra(AbstractC0818ax.f2562z, this.f312a.getPackageName());
        m220a.putExtra(AbstractC0818ax.f2530D, C0508ax.m519b(this.f312a.getPackageName()));
        m236c(m220a);
    }
}
