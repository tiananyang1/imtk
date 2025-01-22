package com.xiaomi.push;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import java.util.Calendar;
import java.util.List;

/* renamed from: com.xiaomi.push.em */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/em.class */
public class C0605em extends AbstractC0601ei {

    /* renamed from: a */
    private SharedPreferences f743a;

    public C0605em(Context context, int i) {
        super(context, i);
        this.f743a = context.getSharedPreferences("mipush_extra", 0);
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 9;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.TopApp;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        String str;
        try {
            ActivityManager activityManager = (ActivityManager) this.f733a.getSystemService("activity");
            int i = 0;
            if (Build.VERSION.SDK_INT < 21) {
                str = activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
            } else {
                UsageStatsManager usageStatsManager = (UsageStatsManager) this.f733a.getSystemService("usagestats");
                Calendar calendar = Calendar.getInstance();
                calendar.add(5, -1);
                long timeInMillis = calendar.getTimeInMillis();
                calendar.add(5, 1);
                List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(0, timeInMillis, calendar.getTimeInMillis());
                if (C0488ad.m430a(queryUsageStats)) {
                    return null;
                }
                long j = 0;
                str = "";
                while (i < queryUsageStats.size()) {
                    UsageStats usageStats = queryUsageStats.get(i);
                    long j2 = j;
                    if (usageStats.getLastTimeStamp() > j) {
                        j2 = usageStats.getLastTimeStamp();
                        str = usageStats.getPackageName();
                    }
                    i++;
                    j = j2;
                }
            }
        } catch (Throwable th) {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (TextUtils.equals(str, this.f743a.getString("ltapn", null))) {
            return "^";
        }
        this.f743a.edit().putString("ltapn", str).commit();
        return str;
    }
}
