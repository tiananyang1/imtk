package com.xiaomi.push.service;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.service.by */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/by.class */
class C0846by extends ContentObserver {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2625a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0846by(XMPushService xMPushService, Handler handler) {
        super(handler);
        this.f2625a = xMPushService;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        boolean m2456f;
        super.onChange(z);
        m2456f = this.f2625a.m2456f();
        AbstractC0407b.m70a("ExtremePowerMode:" + m2456f);
        if (!m2456f) {
            this.f2625a.m2474a(true);
        } else {
            XMPushService xMPushService = this.f2625a;
            xMPushService.m2468a(new XMPushService.C0783f(23, null));
        }
    }
}
