package com.xiaomi.channel.commonutils.logger;

import android.util.Log;

/* renamed from: com.xiaomi.channel.commonutils.logger.a */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/channel/commonutils/logger/a.class */
public class C0406a implements LoggerInterface {

    /* renamed from: a */
    private String f217a = "xiaomi";

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void log(String str) {
        Log.v(this.f217a, str);
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void log(String str, Throwable th) {
        Log.v(this.f217a, str, th);
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void setTag(String str) {
        this.f217a = str;
    }
}
