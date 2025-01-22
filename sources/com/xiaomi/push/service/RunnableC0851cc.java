package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.module.C0863a;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.cc */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/cc.class */
class RunnableC0851cc implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f2632a;

    /* renamed from: a */
    final /* synthetic */ C0850cb f2633a;

    /* renamed from: a */
    final /* synthetic */ String f2634a;

    /* renamed from: b */
    final /* synthetic */ String f2635b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0851cc(C0850cb c0850cb, Context context, String str, String str2) {
        this.f2633a = c0850cb;
        this.f2632a = context;
        this.f2634a = str;
        this.f2635b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        StringBuilder sb;
        String str;
        String str2;
        Iterator<C0863a> it = C0858i.m2709a(this.f2632a).m2712a(this.f2634a).iterator();
        while (it.hasNext()) {
            C0863a next = it.next();
            if (XMPushService.m2438a(next.m2760a(), this.f2635b)) {
                if (next.m2761a() >= System.currentTimeMillis()) {
                    byte[] m2767a = next.m2767a();
                    if (m2767a == null) {
                        str2 = "Geo canBeShownMessage content null";
                    } else {
                        Intent m2819a = C0875x.m2819a(m2767a, System.currentTimeMillis());
                        if (m2819a == null) {
                            str2 = "Geo canBeShownMessage intent null";
                        } else {
                            C0875x.m2828a(this.f2633a.f2631a, (String) null, m2767a, m2819a, true);
                            if (C0858i.m2709a(this.f2633a.f2631a).m2710a(next.m2762a()) == 0) {
                                sb = new StringBuilder();
                                str = "show some exit geofence message. then remove this message failed. message_id:";
                                sb.append(str);
                                sb.append(next.m2762a());
                                str2 = sb.toString();
                            }
                        }
                    }
                } else if (C0858i.m2709a(this.f2632a).m2710a(next.m2762a()) == 0) {
                    sb = new StringBuilder();
                    str = "XMPushService remove some geofence message failed. message_id:";
                    sb.append(str);
                    sb.append(next.m2762a());
                    str2 = sb.toString();
                }
                AbstractC0407b.m70a(str2);
            }
        }
    }
}
