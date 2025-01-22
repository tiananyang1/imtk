package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.service.C0809ao;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.c */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/c.class */
public final class RunnableC0460c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f338a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0460c(Context context) {
        this.f338a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean m276b;
        String m274b;
        m276b = C0450b.m276b(this.f338a);
        if (m276b) {
            m274b = C0450b.m274b(C0809ao.m2557a(this.f338a).m2561a(EnumC0703ic.AggregationSdkMonitorDepth.m1669a(), 4));
            if (TextUtils.isEmpty(m274b)) {
                return;
            }
            MiTinyDataClient.upload(this.f338a, "monitor_upload", "call_stack", 1L, m274b);
            C0450b.m277c(this.f338a);
        }
    }
}
