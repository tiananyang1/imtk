package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.co */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/co.class */
public class HandlerC0553co extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0545cg f604a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0553co(C0545cg c0545cg, Looper looper) {
        super(looper);
        this.f604a = c0545cg;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        boolean z = false;
        if (i == 1) {
            this.f604a.m749a(false);
        } else {
            if (i != 2) {
                return;
            }
            if (message.obj != null) {
                z = ((Boolean) message.obj).booleanValue();
            }
            this.f604a.m752b(z);
        }
    }
}
