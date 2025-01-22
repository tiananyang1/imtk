package io.fabric.sdk.android.services.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.stub.StubApp;
import io.fabric.sdk.android.Fabric;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy.class */
class AdvertisingInfoServiceStrategy implements AdvertisingInfoStrategy {
    public static final String GOOGLE_PLAY_SERVICES_INTENT = "com.google.android.gms.ads.identifier.service.START";
    public static final String GOOGLE_PLAY_SERVICES_INTENT_PACKAGE_NAME = "com.google.android.gms";
    private static final String GOOGLE_PLAY_SERVICE_PACKAGE_NAME = "com.android.vending";
    private final Context context;

    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingConnection.class */
    private static final class AdvertisingConnection implements ServiceConnection {
        private static final int QUEUE_TIMEOUT_IN_MS = 200;
        private final LinkedBlockingQueue<IBinder> queue;
        private boolean retrieved;

        private AdvertisingConnection() {
            this.retrieved = false;
            this.queue = new LinkedBlockingQueue<>(1);
        }

        public IBinder getBinder() {
            if (this.retrieved) {
                Fabric.getLogger().mo2872e(Fabric.TAG, "getBinder already called");
            }
            this.retrieved = true;
            try {
                return this.queue.poll(200L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return null;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.queue.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.queue.clear();
        }
    }

    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/AdvertisingInfoServiceStrategy$AdvertisingInterface.class */
    private static final class AdvertisingInterface implements IInterface {
        public static final String ADVERTISING_ID_SERVICE_INTERFACE_TOKEN = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
        private static final int AD_TRANSACTION_CODE_ID = 1;
        private static final int AD_TRANSACTION_CODE_LIMIT_AD_TRACKING = 2;
        private static final int FLAGS_NONE = 0;
        private final IBinder binder;

        public AdvertisingInterface(IBinder iBinder) {
            this.binder = iBinder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.binder;
        }

        public String getId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                try {
                    obtain.writeInterfaceToken(ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                    this.binder.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } catch (Exception e) {
                    Fabric.getLogger().mo2870d(Fabric.TAG, "Could not get parcel from Google Play Service to capture AdvertisingId");
                    obtain2.recycle();
                    obtain.recycle();
                    return null;
                }
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isLimitAdTrackingEnabled() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            boolean z = false;
            try {
                try {
                    obtain.writeInterfaceToken(ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                    obtain.writeInt(1);
                    this.binder.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                } catch (Exception e) {
                    Fabric.getLogger().mo2870d(Fabric.TAG, "Could not get parcel from Google Play Service to capture Advertising limitAdTracking");
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }
    }

    public AdvertisingInfoServiceStrategy(Context context) {
        this.context = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    @Override // io.fabric.sdk.android.services.common.AdvertisingInfoStrategy
    public AdvertisingInfo getAdvertisingInfo() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Fabric.getLogger().mo2870d(Fabric.TAG, "AdvertisingInfoServiceStrategy cannot be called on the main thread");
            return null;
        }
        try {
            this.context.getPackageManager().getPackageInfo(GOOGLE_PLAY_SERVICE_PACKAGE_NAME, 0);
            AdvertisingConnection advertisingConnection = new AdvertisingConnection();
            Intent intent = new Intent(GOOGLE_PLAY_SERVICES_INTENT);
            intent.setPackage(GOOGLE_PLAY_SERVICES_INTENT_PACKAGE_NAME);
            try {
                try {
                    if (!this.context.bindService(intent, advertisingConnection, 1)) {
                        Fabric.getLogger().mo2870d(Fabric.TAG, "Could not bind to Google Play Service to capture AdvertisingId");
                        return null;
                    }
                    try {
                        AdvertisingInterface advertisingInterface = new AdvertisingInterface(advertisingConnection.getBinder());
                        return new AdvertisingInfo(advertisingInterface.getId(), advertisingInterface.isLimitAdTrackingEnabled());
                    } catch (Exception e) {
                        Fabric.getLogger().mo2879w(Fabric.TAG, "Exception in binding to Google Play Service to capture AdvertisingId", e);
                        this.context.unbindService(advertisingConnection);
                        return null;
                    }
                } finally {
                    this.context.unbindService(advertisingConnection);
                }
            } catch (Throwable th) {
                Fabric.getLogger().mo2871d(Fabric.TAG, "Could not bind to Google Play Service to capture AdvertisingId", th);
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Fabric.getLogger().mo2870d(Fabric.TAG, "Unable to find Google Play Services package name");
            return null;
        } catch (Exception e3) {
            Fabric.getLogger().mo2871d(Fabric.TAG, "Unable to determine if Google Play Services is available", e3);
            return null;
        }
    }
}
