package com.xiaomi.push;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.C0809ao;

/* renamed from: com.xiaomi.push.ea */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ea.class */
public class C0593ea {

    /* renamed from: a */
    private static volatile C0593ea f727a;

    /* renamed from: a */
    private Context f728a;

    private C0593ea(Context context) {
        this.f728a = context;
    }

    /* renamed from: a */
    private int m968a(int i) {
        return Math.max(60, i);
    }

    /* renamed from: a */
    public static C0593ea m969a(Context context) {
        if (f727a == null) {
            synchronized (C0593ea.class) {
                try {
                    if (f727a == null) {
                        f727a = new C0593ea(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f727a;
    }

    /* renamed from: a */
    private boolean m971a() {
        if (Build.VERSION.SDK_INT < 14) {
            return false;
        }
        try {
            ((Application) (this.f728a instanceof Application ? this.f728a : StubApp.getOrigApplicationContext(this.f728a.getApplicationContext()))).registerActivityLifecycleCallbacks(new C0583dr(this.f728a, String.valueOf(System.currentTimeMillis() / 1000)));
            return true;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m972b() {
        C0493ai m439a = C0493ai.m439a(this.f728a);
        C0809ao m2557a = C0809ao.m2557a(this.f728a);
        SharedPreferences sharedPreferences = this.f728a.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferences.getLong("first_try_ts", currentTimeMillis);
        if (j == currentTimeMillis) {
            sharedPreferences.edit().putLong("first_try_ts", currentTimeMillis).commit();
        }
        if (Math.abs(currentTimeMillis - j) < 172800000) {
            return;
        }
        boolean m2563a = m2557a.m2563a(EnumC0703ic.ScreenSizeCollectionSwitch.m1669a(), true);
        boolean m2563a2 = m2557a.m2563a(EnumC0703ic.AndroidVnCollectionSwitch.m1669a(), true);
        boolean m2563a3 = m2557a.m2563a(EnumC0703ic.AndroidVcCollectionSwitch.m1669a(), true);
        boolean m2563a4 = m2557a.m2563a(EnumC0703ic.AndroidIdCollectionSwitch.m1669a(), true);
        boolean m2563a5 = m2557a.m2563a(EnumC0703ic.OperatorSwitch.m1669a(), true);
        if (m2563a || m2563a2 || m2563a3 || m2563a4 || m2563a5) {
            int m968a = m968a(m2557a.m2561a(EnumC0703ic.DeviceInfoCollectionFrequency.m1669a(), 1209600));
            m439a.m447a(new C0603ek(this.f728a, m968a, m2563a, m2563a2, m2563a3, m2563a4, m2563a5), m968a, 30);
        }
        boolean m2563a6 = m2557a.m2563a(EnumC0703ic.MacCollectionSwitch.m1669a(), true);
        boolean m2563a7 = m2557a.m2563a(EnumC0703ic.IMSICollectionSwitch.m1669a(), true);
        boolean m2563a8 = m2557a.m2563a(EnumC0703ic.IccidCollectionSwitch.m1669a(), true);
        boolean m2563a9 = m2557a.m2563a(EnumC0703ic.DeviceIdSwitch.m1669a(), true);
        if (m2563a6 || m2563a7 || m2563a8 || m2563a9) {
            int m968a2 = m968a(m2557a.m2561a(EnumC0703ic.DeviceBaseInfoCollectionFrequency.m1669a(), 1209600));
            m439a.m447a(new C0602ej(this.f728a, m968a2, m2563a6, m2563a7, m2563a8, m2563a9), m968a2, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.AppInstallListCollectionSwitch.m1669a(), true)) {
            int m968a3 = m968a(m2557a.m2561a(EnumC0703ic.AppInstallListCollectionFrequency.m1669a(), 86400));
            m439a.m447a(new C0597ee(this.f728a, m968a3), m968a3, 30);
        }
        if (Build.VERSION.SDK_INT < 21 && m2557a.m2563a(EnumC0703ic.AppActiveListCollectionSwitch.m1669a(), true)) {
            int m968a4 = m968a(m2557a.m2561a(EnumC0703ic.AppActiveListCollectionFrequency.m1669a(), 900));
            m439a.m447a(new C0596ed(this.f728a, m968a4), m968a4, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.StorageCollectionSwitch.m1669a(), true)) {
            int m968a5 = m968a(m2557a.m2561a(EnumC0703ic.StorageCollectionFrequency.m1669a(), 86400));
            m439a.m447a(new C0604el(this.f728a, m968a5), m968a5, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.BluetoothCollectionSwitch.m1669a(), true)) {
            int m968a6 = m968a(m2557a.m2561a(EnumC0703ic.BluetoothCollectionFrequency.m1669a(), 10800));
            m439a.m447a(new C0599eg(this.f728a, m968a6), m968a6, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.AccountCollectionSwitch.m1669a(), true)) {
            int m968a7 = m968a(m2557a.m2561a(EnumC0703ic.AccountCollectionFrequency.m1669a(), 604800));
            m439a.m447a(new C0595ec(this.f728a, m968a7), m968a7, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.WifiCollectionSwitch.m1669a(), true)) {
            int m968a8 = m968a(m2557a.m2561a(EnumC0703ic.WifiCollectionFrequency.m1669a(), 900));
            m439a.m447a(new C0607eo(this.f728a, m968a8), m968a8, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.TopAppCollectionSwitch.m1669a(), true)) {
            int m968a9 = m968a(m2557a.m2561a(EnumC0703ic.TopAppCollectionFrequency.m1669a(), 300));
            m439a.m447a(new C0605em(this.f728a, m968a9), m968a9, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.BroadcastActionCollectionSwitch.m1669a(), true)) {
            int m968a10 = m968a(m2557a.m2561a(EnumC0703ic.BroadcastActionCollectionFrequency.m1669a(), 900));
            m439a.m447a(new C0600eh(this.f728a, m968a10), m968a10, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.WifiDevicesMacCollectionSwitch.m1669a(), false)) {
            int m968a11 = m968a(m2557a.m2561a(EnumC0703ic.WifiDevicesMacCollectionFrequency.m1669a(), 900));
            m439a.m447a(new C0609eq(this.f728a, m968a11), m968a11, 30);
        }
        if (m2557a.m2563a(EnumC0703ic.ActivityTSSwitch.m1669a(), false)) {
            m971a();
        }
        if (m2557a.m2563a(EnumC0703ic.UploadSwitch.m1669a(), true)) {
            m439a.m447a(new C0606en(this.f728a), m968a(m2557a.m2561a(EnumC0703ic.UploadFrequency.m1669a(), 86400)), 60);
        }
        if (m2557a.m2563a(EnumC0703ic.BatteryCollectionSwitch.m1669a(), false)) {
            int m968a12 = m968a(m2557a.m2561a(EnumC0703ic.BatteryCollectionFrequency.m1669a(), 3600));
            m439a.m447a(new C0598ef(this.f728a, m968a12), m968a12, 30);
        }
    }

    /* renamed from: a */
    public void m973a() {
        C0493ai.m439a(this.f728a).m444a(new RunnableC0594eb(this), 30);
    }
}
