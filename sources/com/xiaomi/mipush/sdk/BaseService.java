package com.xiaomi.mipush.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.lang.ref.WeakReference;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/BaseService.class */
public abstract class BaseService extends Service {

    /* renamed from: a */
    private HandlerC0418a f239a;

    /* renamed from: com.xiaomi.mipush.sdk.BaseService$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/BaseService$a.class */
    public static class HandlerC0418a extends Handler {

        /* renamed from: a */
        private WeakReference<BaseService> f240a;

        public HandlerC0418a(WeakReference<BaseService> weakReference) {
            this.f240a = weakReference;
        }

        /* renamed from: a */
        public void m118a() {
            if (hasMessages(1001)) {
                removeMessages(1001);
            }
            sendEmptyMessageDelayed(1001, 1000L);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<BaseService> weakReference;
            BaseService baseService;
            if (message.what != 1001 || (weakReference = this.f240a) == null || (baseService = weakReference.get()) == null) {
                return;
            }
            AbstractC0407b.m74c("TimeoutHandler" + baseService.toString() + "  kill self");
            if (!baseService.mo117a()) {
                baseService.stopSelf();
            } else {
                AbstractC0407b.m74c("TimeoutHandler has job");
                sendEmptyMessageDelayed(1001, 1000L);
            }
        }
    }

    /* renamed from: a */
    protected abstract boolean mo117a();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (this.f239a == null) {
            this.f239a = new HandlerC0418a(new WeakReference(this));
        }
        this.f239a.m118a();
    }
}
