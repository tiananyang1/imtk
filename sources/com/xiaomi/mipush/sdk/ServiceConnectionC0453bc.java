package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.bc */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/bc.class */
public class ServiceConnectionC0453bc implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ C0449az f327a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC0453bc(C0449az c0449az) {
        this.f327a = c0449az;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        List<Message> list;
        List list2;
        Messenger messenger;
        synchronized (this.f327a) {
            this.f327a.f315a = new Messenger(iBinder);
            this.f327a.f320c = false;
            list = this.f327a.f318a;
            for (Message message : list) {
                try {
                    messenger = this.f327a.f315a;
                    messenger.send(message);
                } catch (RemoteException e) {
                    AbstractC0407b.m72a(e);
                }
            }
            list2 = this.f327a.f318a;
            list2.clear();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f327a.f315a = null;
        this.f327a.f320c = false;
    }
}
