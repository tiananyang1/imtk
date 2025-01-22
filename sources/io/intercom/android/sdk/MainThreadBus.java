package io.intercom.android.sdk;

import android.os.Handler;
import android.os.Looper;
import io.intercom.com.squareup.otto.Bus;
import io.intercom.com.squareup.otto.ThreadEnforcer;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/MainThreadBus.class */
class MainThreadBus extends Bus {
    private final Handler mainThread;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MainThreadBus(ThreadEnforcer threadEnforcer) {
        super(threadEnforcer);
        this.mainThread = new Handler(Looper.getMainLooper());
    }

    public void post(final Object obj) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(obj);
        } else {
            this.mainThread.post(new Runnable() { // from class: io.intercom.android.sdk.MainThreadBus.1
                @Override // java.lang.Runnable
                public void run() {
                    MainThreadBus.super.post(obj);
                }
            });
        }
    }

    public void register(Object obj) {
        try {
            super.register(obj);
        } catch (Exception e) {
        }
    }

    public void unregister(Object obj) {
        try {
            super.unregister(obj);
        } catch (Exception e) {
        }
    }
}
