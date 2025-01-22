package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.ej */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ej.class */
public class C0602ej extends AbstractC0601ei {

    /* renamed from: a */
    private boolean f734a;

    /* renamed from: b */
    private boolean f735b;

    /* renamed from: c */
    private boolean f736c;

    /* renamed from: d */
    private boolean f737d;

    public C0602ej(Context context, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        super(context, i);
        this.f734a = z;
        this.f735b = z2;
        if (C0770n.m2408d()) {
            this.f735b = false;
        }
        this.f736c = z3;
        this.f737d = z4;
    }

    /* renamed from: a */
    private String m982a(Context context) {
        String str = "";
        if (!this.f737d) {
            return "off";
        }
        try {
            if (!C0770n.m2408d()) {
                Iterator<String> it = C0727j.m1983a(context).iterator();
                String str2 = "";
                while (it.hasNext()) {
                    String next = it.next();
                    String str3 = str2;
                    if (!TextUtils.isEmpty(str2)) {
                        str3 = str2 + ";";
                    }
                    str2 = str3 + C0509ay.m521a(next) + Constants.ACCEPT_TIME_SEPARATOR_SP + C0509ay.m530b(next);
                }
                str = str2;
            }
            return str;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: b */
    private String m983b() {
        if (!this.f734a) {
            return "off";
        }
        try {
            String m984c = m984c();
            if (TextUtils.isEmpty(m984c)) {
                return "";
            }
            return C0509ay.m521a(m984c) + Constants.ACCEPT_TIME_SEPARATOR_SP + C0509ay.m530b(m984c);
        } catch (Throwable th) {
            return "";
        }
    }

    @TargetApi(9)
    /* renamed from: c */
    private String m984c() {
        if (C0770n.m2408d()) {
            return "";
        }
        String macAddress = Build.VERSION.SDK_INT < 23 ? ((WifiManager) this.f733a.getSystemService("wifi")).getConnectionInfo().getMacAddress() : "";
        if (!TextUtils.isEmpty(macAddress)) {
            return macAddress;
        }
        if (Build.VERSION.SDK_INT < 9) {
            return "";
        }
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= length) {
                            break;
                        }
                        sb.append(String.format("%02x:", Byte.valueOf(hardwareAddress[i2])));
                        i = i2 + 1;
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString().toUpperCase();
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    private String m985d() {
        if (!this.f735b) {
            return "off";
        }
        try {
            String subscriberId = ((TelephonyManager) this.f733a.getSystemService("phone")).getSubscriberId();
            if (TextUtils.isEmpty(subscriberId)) {
                return "";
            }
            return C0509ay.m521a(subscriberId) + Constants.ACCEPT_TIME_SEPARATOR_SP + C0509ay.m530b(subscriberId);
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: e */
    private String m986e() {
        if (!this.f736c) {
            return "off";
        }
        try {
            String simSerialNumber = ((TelephonyManager) this.f733a.getSystemService("phone")).getSimSerialNumber();
            if (TextUtils.isEmpty(simSerialNumber)) {
                return "";
            }
            return C0509ay.m521a(simSerialNumber) + Constants.ACCEPT_TIME_SEPARATOR_SP + C0509ay.m530b(simSerialNumber);
        } catch (Throwable th) {
            return "";
        }
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 13;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.DeviceBaseInfo;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        return m983b() + "|" + m985d() + "|" + m986e() + "|" + m982a(this.f733a);
    }
}
