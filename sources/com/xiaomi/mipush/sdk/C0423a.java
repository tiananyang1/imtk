package com.xiaomi.mipush.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.push.C0630fk;
import com.xiaomi.push.C0631fl;
import java.util.HashSet;
import java.util.Set;

@TargetApi(14)
/* renamed from: com.xiaomi.mipush.sdk.a */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/a.class */
public class C0423a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private Set<String> f269a = new HashSet();

    /* renamed from: a */
    private static void m169a(Application application) {
        application.registerActivityLifecycleCallbacks(new C0423a());
    }

    /* renamed from: a */
    public static void m170a(Context context) {
        m169a((Application) StubApp.getOrigApplicationContext(context.getApplicationContext()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        String m1249a;
        int i;
        String str;
        C0631fl c0631fl;
        String str2;
        Intent intent = activity.getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("messageId");
        int intExtra = intent.getIntExtra("eventMessageType", -1);
        if (TextUtils.isEmpty(stringExtra) || intExtra <= 0 || this.f269a.contains(stringExtra)) {
            return;
        }
        this.f269a.add(stringExtra);
        if (intExtra == 3000) {
            C0631fl m1256a = C0631fl.m1256a(StubApp.getOrigApplicationContext(activity.getApplicationContext()));
            str2 = activity.getPackageName();
            m1249a = C0630fk.m1249a(intExtra);
            i = 3008;
            str = "App calls by business message is visiable";
            c0631fl = m1256a;
        } else {
            if (intExtra != 1000) {
                return;
            }
            C0631fl m1256a2 = C0631fl.m1256a(StubApp.getOrigApplicationContext(activity.getApplicationContext()));
            String packageName = activity.getPackageName();
            m1249a = C0630fk.m1249a(intExtra);
            i = 1008;
            str = "app calls by notification message is visiable";
            c0631fl = m1256a2;
            str2 = packageName;
        }
        c0631fl.m1263a(str2, m1249a, stringExtra, i, str);
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
