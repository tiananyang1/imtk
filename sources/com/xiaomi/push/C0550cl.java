package com.xiaomi.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.cl */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cl.class */
public class C0550cl extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ C0545cg f590a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0550cl(C0545cg c0545cg) {
        this.f590a = c0545cg;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        HandlerC0553co handlerC0553co;
        HandlerC0553co handlerC0553co2;
        HandlerC0553co handlerC0553co3;
        HandlerC0553co handlerC0553co4;
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            handlerC0553co = this.f590a.f582a;
            if (handlerC0553co.hasMessages(1)) {
                handlerC0553co4 = this.f590a.f582a;
                handlerC0553co4.removeMessages(1);
            }
            handlerC0553co2 = this.f590a.f582a;
            Message obtainMessage = handlerC0553co2.obtainMessage(1);
            handlerC0553co3 = this.f590a.f582a;
            handlerC0553co3.sendMessageDelayed(obtainMessage, 10000L);
        }
    }
}
