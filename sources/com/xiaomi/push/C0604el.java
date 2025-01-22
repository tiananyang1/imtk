package com.xiaomi.push;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* renamed from: com.xiaomi.push.el */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/el.class */
public class C0604el extends AbstractC0601ei {
    public C0604el(Context context, int i) {
        super(context, i);
    }

    /* renamed from: a */
    private double m992a(double d) {
        int i = 1;
        while (true) {
            int i2 = i;
            double d2 = i2;
            if (d2 >= d) {
                return d2;
            }
            i = i2 << 1;
        }
    }

    /* renamed from: a */
    private long m993a(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getBlockSize() * statFs.getBlockCount();
    }

    /* renamed from: b */
    private String m994b() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        String str = "0";
        if (new File("/proc/meminfo").exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            } catch (Exception e) {
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
            }
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader2 = bufferedReader;
                str = "0";
                if (!TextUtils.isEmpty(readLine)) {
                    String[] split = readLine.split("\\s+");
                    bufferedReader2 = bufferedReader;
                    str = "0";
                    if (split != null) {
                        bufferedReader2 = bufferedReader;
                        str = "0";
                        if (split.length >= 2) {
                            double doubleValue = (Double.valueOf(split[1]).doubleValue() / 1024.0d) / 1024.0d;
                            double d = doubleValue;
                            if (doubleValue > 0.5d) {
                                d = Math.ceil(doubleValue);
                            }
                            str = d + "";
                            bufferedReader2 = bufferedReader;
                        }
                    }
                }
            } catch (Exception e2) {
                str = "0";
                if (bufferedReader != null) {
                    bufferedReader2 = bufferedReader;
                    str = "0";
                    bufferedReader2.close();
                }
                return str + "GB";
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
            try {
                bufferedReader2.close();
            } catch (IOException e4) {
            }
        }
        return str + "GB";
    }

    /* renamed from: c */
    private String m995c() {
        return m992a(((m993a(Environment.getDataDirectory()) / 1024.0d) / 1024.0d) / 1024.0d) + "GB";
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 23;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.Storage;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        return "ram:" + m994b() + Constants.ACCEPT_TIME_SEPARATOR_SP + "rom:" + m995c();
    }
}
