package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.C0832bk;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.ag */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ag.class */
public final class C0801ag extends C0832bk.a {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2453a;

    /* renamed from: a */
    final /* synthetic */ C0870s f2454a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0801ag(String str, long j, XMPushService xMPushService, C0870s c0870s) {
        super(str, j);
        this.f2453a = xMPushService;
        this.f2454a = c0870s;
    }

    @Override // com.xiaomi.push.service.C0832bk.a
    /* renamed from: a */
    void mo2502a(C0832bk c0832bk) {
        String m2658a = c0832bk.m2658a("GAID", "gaid");
        String m1987b = C0727j.m1987b((Context) this.f2453a);
        AbstractC0407b.m74c("gaid :" + m1987b);
        if (TextUtils.isEmpty(m1987b) || TextUtils.equals(m2658a, m1987b)) {
            return;
        }
        c0832bk.m2660a("GAID", "gaid", m1987b);
        C0732je c0732je = new C0732je();
        c0732je.m2071b(this.f2454a.f2711d);
        c0732je.m2075c(EnumC0714in.ClientInfoUpdate.f1752a);
        c0732je.m2058a(C0812ar.m2571a());
        c0732je.m2060a(new HashMap());
        c0732je.m2064a().put("gaid", m1987b);
        byte[] m2314a = C0743jp.m2314a(C0800af.m2495a(this.f2453a.getPackageName(), this.f2454a.f2711d, c0732je, EnumC0696hw.Notification));
        XMPushService xMPushService = this.f2453a;
        xMPushService.m2473a(xMPushService.getPackageName(), m2314a, true);
    }
}
