package com.xiaomi.push;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.xiaomi.push.InterfaceC0560cv;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.cu */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cu.class */
public class ServiceConnectionC0559cu implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ C0558ct f624a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC0559cu(C0558ct c0558ct) {
        this.f624a = c0558ct;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.v("GeoFencingServiceWrapper", "*** GeoFencingService connected ***");
        this.f624a.f611a = InterfaceC0560cv.a.m824a(iBinder);
        if (this.f624a.f610a != null) {
            this.f624a.f610a.sendEmptyMessage(3);
            this.f624a.f610a.sendEmptyMessageDelayed(2, 60000L);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.v("GeoFencingServiceWrapper", "*** GeoFencingService disconnected ***");
        this.f624a.f611a = null;
    }
}
