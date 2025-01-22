package com.xiaomi.push;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

/* renamed from: com.xiaomi.push.ed */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ed.class */
public class C0596ed extends AbstractC0601ei {
    public C0596ed(Context context, int i) {
        super(context, i);
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 5;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.AppActiveList;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        StringBuilder sb = new StringBuilder();
        try {
            for (ActivityManager.RunningTaskInfo runningTaskInfo : ((ActivityManager) this.f733a.getSystemService("activity")).getRunningTasks(10)) {
                if (runningTaskInfo != null && runningTaskInfo.topActivity != null) {
                    if (sb.length() > 0) {
                        sb.append(";");
                    }
                    sb.append(runningTaskInfo.topActivity.getPackageName());
                }
            }
        } catch (Throwable th) {
        }
        if (TextUtils.isEmpty(sb)) {
            return null;
        }
        return sb.toString();
    }
}
