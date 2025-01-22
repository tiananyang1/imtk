package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.cs */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cs.class */
public class HandlerC0557cs extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0522bk f606a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0557cs(C0522bk c0522bk, Looper looper) {
        super(looper);
        this.f606a = c0522bk;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 101) {
            this.f606a.m594c();
        } else {
            if (i != 102) {
                return;
            }
            C0523bl.m603a().m613b();
        }
    }
}
