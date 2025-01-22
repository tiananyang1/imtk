package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.C0581dp;
import com.xiaomi.push.C0883y;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.y */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/y.class */
public final class RunnableC0482y implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f393a;

    /* renamed from: a */
    final /* synthetic */ boolean f394a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0482y(Context context, boolean z) {
        this.f393a = context;
        this.f394a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        File file;
        HashMap<String, String> m184a;
        String str;
        File logFile;
        try {
            m184a = C0435al.m184a(this.f393a, "");
            if (this.f394a) {
                str = this.f393a.getFilesDir().getAbsolutePath();
            } else {
                str = this.f393a.getExternalFilesDir(null).getAbsolutePath() + C0581dp.f709a;
            }
            logFile = Logger.getLogFile(str);
        } catch (Throwable th) {
            th = th;
            file = null;
        }
        if (logFile == null) {
            AbstractC0407b.m70a("log file null");
            return;
        }
        file = new File(str, this.f393a.getPackageName() + ".zip");
        try {
            C0883y.m2860a(file, logFile);
            if (file.exists()) {
                C0503as.m479a((this.f394a ? "https://api.xmpush.xiaomi.com/upload/xmsf_log?file=" : "https://api.xmpush.xiaomi.com/upload/app_log?file=") + file.getName(), m184a, file, "file");
            } else {
                AbstractC0407b.m70a("zip log file failed");
            }
        } catch (Throwable th2) {
            th = th2;
            AbstractC0407b.m72a(th);
            if (file == null) {
            } else {
                return;
            }
        }
        if (file == null && file.exists()) {
            file.delete();
        }
    }
}
