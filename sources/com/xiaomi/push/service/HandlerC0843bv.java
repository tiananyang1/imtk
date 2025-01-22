package com.xiaomi.push.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/* renamed from: com.xiaomi.push.service.bv */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bv.class */
class HandlerC0843bv extends Handler {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2622a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC0843bv(XMPushService xMPushService) {
        this.f2622a = xMPushService;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        String str;
        super.handleMessage(message);
        if (message != null) {
            try {
                int i = message.what;
                if (i == 17) {
                    if (message.obj != null) {
                        this.f2622a.onStart((Intent) message.obj, XMPushService.f2386a);
                        return;
                    }
                    return;
                }
                if (i != 18) {
                    return;
                }
                Message obtain = Message.obtain((Handler) null, 0);
                obtain.what = 18;
                Bundle bundle = new Bundle();
                str = this.f2622a.f2402a;
                bundle.putString("xmsf_region", str);
                obtain.setData(bundle);
                message.replyTo.send(obtain);
            } catch (Throwable th) {
            }
        }
    }
}
