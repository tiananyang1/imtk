package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.C0809ao;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.eq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/eq.class */
public class C0609eq extends AbstractC0601ei {

    /* renamed from: a */
    private SharedPreferences f749a;

    /* renamed from: a */
    private InterfaceC0539ca f750a;

    /* renamed from: a */
    private final Object f751a;

    /* renamed from: a */
    private String f752a;

    public C0609eq(Context context, int i) {
        super(context, i);
        this.f751a = new Object();
        this.f750a = new C0610er(this);
        m1005a(context);
    }

    /* renamed from: a */
    private void m1005a(Context context) {
        C0522bk.m591a(context).m602b();
        C0522bk.m590a().m599a(this.f750a, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m1006b(String str) {
        try {
            String jSONArray = new JSONObject(str).getJSONArray("devices").toString();
            String substring = jSONArray.substring(1, jSONArray.length() - 1);
            return !TextUtils.isEmpty(substring) ? C0509ay.m532c(substring) : "";
        } catch (Throwable th) {
            return "";
        }
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 14;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.WifiDevicesMac;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        if (C0503as.m486d(this.f733a)) {
            C0522bk.m590a().m598a();
            synchronized (this.f751a) {
                try {
                    this.f751a.wait(10000L);
                } catch (Exception e) {
                    AbstractC0407b.m72a(e);
                }
            }
            this.f749a = this.f733a.getSharedPreferences("mipush_extra", 4);
            SharedPreferences.Editor edit = this.f749a.edit();
            edit.putLong("last_mac_upload_timestamp", System.currentTimeMillis());
            edit.commit();
        }
        String str = this.f752a;
        this.f752a = "";
        return str;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: b */
    protected boolean mo981b() {
        if (m1007c()) {
            return C0491ag.m433a(this.f733a, String.valueOf(mo185a()), this.f732a);
        }
        int max = Math.max(3600, C0809ao.m2557a(this.f733a).m2561a(EnumC0703ic.WifiDevicesMacWifiUnchangedCollectionFrequency.m1669a(), 7200));
        long currentTimeMillis = System.currentTimeMillis();
        this.f749a = this.f733a.getSharedPreferences("mipush_extra", 4);
        return ((((float) Math.abs(currentTimeMillis - this.f749a.getLong("last_mac_upload_timestamp", 0L))) > (((float) (max * PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT)) * 0.9f) ? 1 : (((float) Math.abs(currentTimeMillis - this.f749a.getLong("last_mac_upload_timestamp", 0L))) == (((float) (max * PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT)) * 0.9f) ? 0 : -1)) >= 0) && C0491ag.m433a(this.f733a, String.valueOf(mo185a()), (long) max);
    }

    /* renamed from: c */
    public boolean m1007c() {
        WifiInfo connectionInfo;
        try {
            this.f749a = this.f733a.getSharedPreferences("mipush_extra", 4);
            String string = this.f749a.getString("last_wifi_ssid", "");
            WifiManager wifiManager = (WifiManager) this.f733a.getSystemService("wifi");
            if (!wifiManager.isWifiEnabled() || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return true;
            }
            SharedPreferences.Editor edit = this.f749a.edit();
            edit.putString("last_wifi_ssid", connectionInfo.getSSID());
            edit.commit();
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return !TextUtils.equals(connectionInfo.getSSID(), string);
        } catch (Throwable th) {
            return true;
        }
    }
}
