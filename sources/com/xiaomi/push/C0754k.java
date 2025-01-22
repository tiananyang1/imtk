package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.push.k */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/k.class */
final class C0754k {

    /* renamed from: com.xiaomi.push.k$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/k$a.class */
    static final class a {

        /* renamed from: a */
        private final String f2329a;

        /* renamed from: a */
        private final boolean f2330a;

        a(String str, boolean z) {
            this.f2329a = str;
            this.f2330a = z;
        }

        /* renamed from: a */
        public String m2380a() {
            return this.f2329a;
        }
    }

    /* renamed from: com.xiaomi.push.k$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/k$b.class */
    private static final class b implements ServiceConnection {

        /* renamed from: a */
        private final LinkedBlockingQueue<IBinder> f2331a;

        /* renamed from: a */
        boolean f2332a;

        private b() {
            this.f2332a = false;
            this.f2331a = new LinkedBlockingQueue<>(1);
        }

        /* renamed from: a */
        public IBinder m2381a() {
            if (this.f2332a) {
                throw new IllegalStateException();
            }
            this.f2332a = true;
            return this.f2331a.poll(30000L, TimeUnit.MILLISECONDS);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f2331a.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* renamed from: com.xiaomi.push.k$c */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/k$c.class */
    private static final class c implements IInterface {

        /* renamed from: a */
        private IBinder f2333a;

        public c(IBinder iBinder) {
            this.f2333a = iBinder;
        }

        /* renamed from: a */
        public String m2382a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(AdvertisingInfoServiceStrategy.AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                this.f2333a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f2333a;
        }
    }

    /* renamed from: a */
    public static a m2379a(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            b bVar = new b();
            Intent intent = new Intent(AdvertisingInfoServiceStrategy.GOOGLE_PLAY_SERVICES_INTENT);
            intent.setPackage(AdvertisingInfoServiceStrategy.GOOGLE_PLAY_SERVICES_INTENT_PACKAGE_NAME);
            try {
                if (context.bindService(intent, bVar, 1)) {
                    try {
                        IBinder m2381a = bVar.m2381a();
                        if (m2381a != null) {
                            a aVar = new a(new c(m2381a).m2382a(), false);
                            context.unbindService(bVar);
                            return aVar;
                        }
                        context.unbindService(bVar);
                    } catch (Exception e) {
                        throw e;
                    }
                }
                throw new IOException("Google Play connection failed");
            } catch (Throwable th) {
                context.unbindService(bVar);
                throw th;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }
}
