package com.xiaomi.mipush.sdk.help;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.C0472o;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/help/HelpService.class */
public class HelpService extends IntentService {
    public HelpService() {
        super("intentService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra("awake_info"))) {
            return;
        }
        C0472o.m366a(this, intent, null);
    }
}
