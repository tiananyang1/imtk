package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.push.C0496al;

/* renamed from: com.xiaomi.push.am */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/am.class */
class HandlerC0497am extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0496al f435a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0497am(C0496al c0496al, Looper looper) {
        super(looper);
        this.f435a = c0496al;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        C0496al.b bVar = (C0496al.b) message.obj;
        if (message.what == 0) {
            bVar.m461a();
        } else if (message.what == 1) {
            bVar.mo463c();
        }
        super.handleMessage(message);
    }
}
