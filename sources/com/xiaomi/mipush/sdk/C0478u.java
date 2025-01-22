package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0488ad;
import com.xiaomi.push.C0491ag;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.C0698hy;
import com.xiaomi.push.C0708ih;
import com.xiaomi.push.C0711ik;
import com.xiaomi.push.C0712il;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0721iu;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0770n;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.C0859j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.xiaomi.mipush.sdk.u */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/u.class */
public class C0478u extends C0493ai.a {

    /* renamed from: a */
    private Context f383a;

    /* renamed from: c */
    private int f385c;

    /* renamed from: a */
    private final int f382a = -1;

    /* renamed from: b */
    private final int f384b = 3600;

    public C0478u(Context context, int i) {
        this.f383a = context;
        this.f385c = i;
    }

    /* renamed from: a */
    private static Location m379a(Context context) {
        Location location;
        Location location2;
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        Location location3 = null;
        try {
            location = locationManager.getLastKnownLocation("network");
        } catch (Exception e) {
            location = null;
        }
        try {
            location2 = locationManager.getLastKnownLocation("gps");
        } catch (Exception e2) {
            location2 = null;
        }
        try {
            location3 = locationManager.getLastKnownLocation("passive");
        } catch (Exception e3) {
        }
        return m380a(location3, m380a(location, location2));
    }

    /* renamed from: a */
    private static Location m380a(Location location, Location location2) {
        if (location == null) {
            return location2;
        }
        if (location2 != null && location.getTime() <= location2.getTime()) {
            return location2;
        }
        return location;
    }

    /* renamed from: a */
    private static C0708ih m381a(Context context) {
        if (!m388a(context)) {
            return null;
        }
        Location m379a = m379a(context);
        C0708ih c0708ih = null;
        if (m379a != null) {
            C0711ik c0711ik = new C0711ik();
            c0711ik.m1745b(m379a.getLatitude());
            c0711ik.m1739a(m379a.getLongitude());
            c0708ih = new C0708ih();
            c0708ih.m1687a(m379a.getAccuracy());
            c0708ih.m1689a(c0711ik);
            c0708ih.m1690a(m379a.getProvider());
            c0708ih.m1688a(new Date().getTime() - m379a.getTime());
        }
        return c0708ih;
    }

    /* renamed from: a */
    private static C0712il m382a(Context context) {
        C0712il c0712il = new C0712il();
        if (C0770n.m2408d()) {
            return c0712il;
        }
        c0712il.m1751a(m383a(context));
        c0712il.m1755b(m389b(context));
        c0712il.m1750a(m381a(context));
        return c0712il;
    }

    /* renamed from: a */
    private static List<C0721iu> m383a(Context context) {
        ArrayList arrayList;
        C0479v c0479v = new C0479v();
        try {
            List<ScanResult> scanResults = ((WifiManager) context.getSystemService("wifi")).getScanResults();
            if (!C0488ad.m430a(scanResults)) {
                Collections.sort(scanResults, c0479v);
                ArrayList arrayList2 = new ArrayList();
                int i = 0;
                while (true) {
                    int i2 = i;
                    arrayList = arrayList2;
                    if (i2 >= Math.min(30, scanResults.size())) {
                        break;
                    }
                    ScanResult scanResult = scanResults.get(i2);
                    if (scanResult != null) {
                        C0721iu c0721iu = new C0721iu();
                        c0721iu.m1882a(TextUtils.isEmpty(scanResult.BSSID) ? "" : scanResult.BSSID);
                        c0721iu.m1881a(scanResult.level);
                        c0721iu.m1887b(scanResult.SSID);
                        arrayList2.add(c0721iu);
                    }
                    i = i2 + 1;
                }
            } else {
                arrayList = null;
            }
            return arrayList;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m384a(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 4).edit();
        edit.putLong("last_upload_lbs_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    /* renamed from: a */
    public static void m385a(Context context, boolean z) {
        C0712il m382a = m382a(context);
        byte[] m2314a = C0743jp.m2314a(m382a);
        C0732je c0732je = new C0732je("-1", false);
        c0732je.m2075c(EnumC0714in.GeoUpdateLoc.f1752a);
        c0732je.m2062a(m2314a);
        c0732je.m2060a(new HashMap());
        c0732je.m2064a().put(Constants.EXTRA_KEY_INITIAL_WIFI_UPLOAD, String.valueOf(z));
        boolean m2720b = C0859j.m2720b(context);
        if (m2720b) {
            c0732je.m2064a().put(Constants.EXTRA_KEY_XMSF_GEO_IS_WORK, String.valueOf(m2720b));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("reportLocInfo locInfo timestamp:");
        sb.append(System.currentTimeMillis());
        sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        sb.append(String.valueOf(m382a.m1749a() != null ? m382a.m1749a() : "null"));
        sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        sb.append(String.valueOf(m382a.f1689b != null ? m382a.f1689b.toString() : null));
        sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        sb.append(String.valueOf(m382a.f1688a != null ? m382a.f1688a.toString() : null));
        AbstractC0407b.m74c(sb.toString());
        C0449az.m224a(context).m254a((C0449az) c0732je, EnumC0696hw.Notification, true, (C0717iq) null);
        m384a(context);
    }

    /* renamed from: a */
    private boolean m386a() {
        if (C0503as.m486d(this.f383a)) {
            return true;
        }
        return C0503as.m487e(this.f383a) && m387a((long) Math.max(60, C0809ao.m2557a(this.f383a).m2561a(EnumC0703ic.UploadNOWIFIGeoLocFrequency.m1669a(), 3600)));
    }

    /* renamed from: a */
    private boolean m387a(long j) {
        return ((float) Math.abs((System.currentTimeMillis() / 1000) - this.f383a.getSharedPreferences("mipush_extra", 4).getLong("last_upload_lbs_data_timestamp", -1L))) > ((float) j) * 0.9f;
    }

    /* renamed from: a */
    protected static boolean m388a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        boolean z = true;
        boolean z2 = packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", packageName) == 0;
        boolean z3 = packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", packageName) == 0;
        if (!z2) {
            if (z3) {
                return true;
            }
            z = false;
        }
        return z;
    }

    /* renamed from: b */
    private static List<C0698hy> m389b(Context context) {
        try {
            List neighboringCellInfo = ((TelephonyManager) context.getSystemService("phone")).getNeighboringCellInfo();
            ArrayList arrayList = null;
            for (int i = 0; i < neighboringCellInfo.size(); i++) {
                NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) neighboringCellInfo.get(i);
                arrayList = new ArrayList();
                if (neighboringCellInfo2.getLac() > 0 || neighboringCellInfo2.getCid() > 0) {
                    C0698hy c0698hy = new C0698hy();
                    c0698hy.m1617a(neighboringCellInfo2.getCid());
                    c0698hy.m1622b((neighboringCellInfo2.getRssi() * 2) - 113);
                    arrayList.add(c0698hy);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            return null;
        }
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 11;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (C0859j.m2723e(this.f383a) && C0809ao.m2557a(this.f383a).m2563a(EnumC0703ic.UploadGeoAppLocSwitch.m1669a(), true) && C0503as.m485c(this.f383a) && m386a() && C0491ag.m433a(this.f383a, String.valueOf(11), this.f385c)) {
            m385a(this.f383a, false);
        }
    }
}
