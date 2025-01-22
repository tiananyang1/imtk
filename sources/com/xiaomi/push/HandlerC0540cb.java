package com.xiaomi.push;

import android.os.Handler;
import android.os.Message;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.cb */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cb.class */
public class HandlerC0540cb extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0552cn f540a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC0540cb(C0552cn c0552cn) {
        this.f540a = c0552cn;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Object obj;
        AsyncTaskC0549ck asyncTaskC0549ck;
        C0552cn c0552cn;
        AsyncTaskC0549ck asyncTaskC0549ck2;
        C0542cd c0542cd;
        C0542cd c0542cd2;
        obj = this.f540a.f603a;
        synchronized (obj) {
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    this.f540a.f596a = 0;
                    asyncTaskC0549ck = this.f540a.f602a;
                    if (asyncTaskC0549ck != null) {
                        asyncTaskC0549ck2 = this.f540a.f602a;
                        asyncTaskC0549ck2.cancel(true);
                    }
                    c0552cn = this.f540a;
                } else if (i == 3) {
                    if (message.obj != null) {
                        String str = (String) message.obj;
                        c0542cd = this.f540a.f599a;
                        if (c0542cd != null) {
                            c0542cd2 = this.f540a.f599a;
                            c0542cd2.m739d(str);
                        }
                    }
                    c0552cn = this.f540a;
                }
                c0552cn.m799b();
            } else {
                this.f540a.m791a((HashMap) message.obj);
            }
        }
    }
}
