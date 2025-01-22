package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.bh */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bh.class */
public class ServiceConnectionC0829bh implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ C0827bf f2594a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC0829bh(C0827bf c0827bf) {
        this.f2594a = c0827bf;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        List<Message> list;
        List list2;
        Messenger messenger;
        synchronized (this.f2594a) {
            this.f2594a.f2591b = new Messenger(iBinder);
            this.f2594a.f2592b = false;
            list = this.f2594a.f2589a;
            for (Message message : list) {
                try {
                    messenger = this.f2594a.f2591b;
                    messenger.send(message);
                } catch (RemoteException e) {
                    AbstractC0407b.m72a(e);
                }
            }
            list2 = this.f2594a.f2589a;
            list2.clear();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f2594a.f2591b = null;
        this.f2594a.f2592b = false;
    }
}
