package com.xiaomi.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Handler;

/* renamed from: com.xiaomi.push.bx */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bx.class */
class C0535bx extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ C0533bv f527a;

    private C0535bx(C0533bv c0533bv) {
        this.f527a = c0533bv;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        NetworkInfo networkInfo;
        Handler m645a;
        int i;
        if (intent == null || (action = intent.getAction()) == null || !action.equals("android.net.conn.CONNECTIVITY_CHANGE") || (networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo")) == null) {
            return;
        }
        if (networkInfo.isConnected()) {
            m645a = C0533bv.m645a(this.f527a);
            i = 200;
        } else {
            if (networkInfo.getState() != NetworkInfo.State.DISCONNECTED) {
                return;
            }
            m645a = C0533bv.m645a(this.f527a);
            i = 201;
        }
        m645a.obtainMessage(i, networkInfo).sendToTarget();
    }
}
