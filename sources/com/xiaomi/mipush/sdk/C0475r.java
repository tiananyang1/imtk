package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.push.InterfaceC0744jq;

/* renamed from: com.xiaomi.mipush.sdk.r */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/r.class */
public class C0475r {
    /* renamed from: a */
    public static <T extends InterfaceC0744jq<T, ?>> void m373a(Context context, Config config) {
        if (config == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_cr_config");
        intent.putExtra("action_cr_event_switch", config.isEventUploadSwitchOpen());
        intent.putExtra("action_cr_event_frequency", config.getEventUploadFrequency());
        intent.putExtra("action_cr_perf_switch", config.isPerfUploadSwitchOpen());
        intent.putExtra("action_cr_perf_frequency", config.getPerfUploadFrequency());
        intent.putExtra("action_cr_event_en", config.isEventEncrypted());
        intent.putExtra("action_cr_max_file_size", config.getMaxFileLength());
        C0449az.m224a(context).m248a(intent);
    }
}
