package com.xiaomi.push;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;

/* renamed from: com.xiaomi.push.dr */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dr.class */
public class C0583dr implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private Context f716a;

    /* renamed from: a */
    private String f717a;

    /* renamed from: b */
    private String f718b;

    public C0583dr(Context context, String str) {
        this.f717a = "";
        this.f716a = context;
        this.f717a = str;
    }

    /* renamed from: a */
    private void m945a(String str) {
        C0707ig c0707ig = new C0707ig();
        c0707ig.m1678a(str);
        c0707ig.m1676a(System.currentTimeMillis());
        c0707ig.m1677a(EnumC0699hz.ActivityActiveTimeStamp);
        AbstractC0601ei.m979a(this.f716a, c0707ig);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        String localClassName = activity.getLocalClassName();
        if (TextUtils.isEmpty(this.f717a) || TextUtils.isEmpty(localClassName)) {
            return;
        }
        this.f718b = "";
        if (!TextUtils.isEmpty(this.f718b) && !TextUtils.equals(this.f718b, localClassName)) {
            this.f717a = "";
            return;
        }
        m945a(this.f716a.getPackageName() + "|" + localClassName + Constants.COLON_SEPARATOR + this.f717a + Constants.ACCEPT_TIME_SEPARATOR_SP + String.valueOf(System.currentTimeMillis() / 1000));
        this.f717a = "";
        this.f718b = "";
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (TextUtils.isEmpty(this.f718b)) {
            this.f718b = activity.getLocalClassName();
        }
        this.f717a = String.valueOf(System.currentTimeMillis() / 1000);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
