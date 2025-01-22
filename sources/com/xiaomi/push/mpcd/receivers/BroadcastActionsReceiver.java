package com.xiaomi.push.mpcd.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.push.InterfaceC0591dz;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/mpcd/receivers/BroadcastActionsReceiver.class */
public class BroadcastActionsReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private InterfaceC0591dz f2356a;

    public BroadcastActionsReceiver(InterfaceC0591dz interfaceC0591dz) {
        this.f2356a = interfaceC0591dz;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        InterfaceC0591dz interfaceC0591dz = this.f2356a;
        if (interfaceC0591dz != null) {
            interfaceC0591dz.mo955a(context, intent);
        }
    }
}
