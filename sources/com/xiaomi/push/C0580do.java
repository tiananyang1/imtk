package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;

/* renamed from: com.xiaomi.push.do */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/do.class */
public class C0580do implements LoggerInterface {

    /* renamed from: a */
    private LoggerInterface f706a;

    /* renamed from: b */
    private LoggerInterface f707b;

    public C0580do(LoggerInterface loggerInterface, LoggerInterface loggerInterface2) {
        this.f706a = null;
        this.f707b = null;
        this.f706a = loggerInterface;
        this.f707b = loggerInterface2;
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void log(String str) {
        LoggerInterface loggerInterface = this.f706a;
        if (loggerInterface != null) {
            loggerInterface.log(str);
        }
        LoggerInterface loggerInterface2 = this.f707b;
        if (loggerInterface2 != null) {
            loggerInterface2.log(str);
        }
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void log(String str, Throwable th) {
        LoggerInterface loggerInterface = this.f706a;
        if (loggerInterface != null) {
            loggerInterface.log(str, th);
        }
        LoggerInterface loggerInterface2 = this.f707b;
        if (loggerInterface2 != null) {
            loggerInterface2.log(str, th);
        }
    }

    @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
    public void setTag(String str) {
    }
}
