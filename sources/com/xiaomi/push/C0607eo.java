package com.xiaomi.push;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: com.xiaomi.push.eo */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/eo.class */
public class C0607eo extends AbstractC0601ei {

    /* renamed from: a */
    private Comparator<ScanResult> f747a;

    public C0607eo(Context context, int i) {
        super(context, i);
        this.f747a = new C0608ep(this);
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 8;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.WIFI;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        WifiInfo connectionInfo;
        StringBuilder sb = new StringBuilder();
        try {
            WifiManager wifiManager = (WifiManager) this.f733a.getSystemService("wifi");
            if (wifiManager.isWifiEnabled() && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                sb.append(connectionInfo.getSSID());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(C0509ay.m532c(connectionInfo.getBSSID()));
                sb.append("|");
            }
            List<ScanResult> scanResults = wifiManager.getScanResults();
            if (!C0488ad.m430a(scanResults)) {
                Collections.sort(scanResults, this.f747a);
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= Math.min(10, scanResults.size())) {
                        break;
                    }
                    ScanResult scanResult = scanResults.get(i2);
                    if (i2 > 0) {
                        sb.append(";");
                    }
                    if (scanResult != null) {
                        sb.append(scanResult.SSID);
                        sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                        sb.append(C0509ay.m532c(scanResult.BSSID));
                        sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                        sb.append(scanResult.level);
                    }
                    i = i2 + 1;
                }
            }
        } catch (Throwable th) {
        }
        return sb.toString();
    }
}
