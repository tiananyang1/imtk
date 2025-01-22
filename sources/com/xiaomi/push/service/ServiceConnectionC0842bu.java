package com.xiaomi.push.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.bu */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bu.class */
public class ServiceConnectionC0842bu implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2621a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC0842bu(XMPushService xMPushService) {
        this.f2621a = xMPushService;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        int i;
        int i2;
        AbstractC0407b.m73b("onServiceConnected " + iBinder);
        Service m2421a = XMJobService.m2421a();
        if (m2421a == null) {
            AbstractC0407b.m70a("XMService connected but innerService is null " + iBinder);
            return;
        }
        XMPushService xMPushService = this.f2621a;
        i = XMPushService.f2387b;
        xMPushService.startForeground(i, XMPushService.m2423a((Context) this.f2621a));
        i2 = XMPushService.f2387b;
        m2421a.startForeground(i2, XMPushService.m2423a((Context) this.f2621a));
        m2421a.stopForeground(true);
        this.f2621a.unbindService(this);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
