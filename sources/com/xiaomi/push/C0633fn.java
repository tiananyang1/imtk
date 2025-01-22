package com.xiaomi.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0632fm;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.fn */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fn.class */
public class C0633fn implements C0632fm.a {

    /* renamed from: a */
    protected Context f918a;

    /* renamed from: a */
    private PendingIntent f917a = null;

    /* renamed from: a */
    private volatile long f916a = 0;

    public C0633fn(Context context) {
        this.f918a = null;
        this.f918a = context;
    }

    /* renamed from: a */
    private void m1276a(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            AlarmManager.class.getMethod("setExact", Integer.TYPE, Long.TYPE, PendingIntent.class).invoke(alarmManager, 0, Long.valueOf(j), pendingIntent);
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaomi.push.C0632fm.a
    /* renamed from: a */
    public long mo1273a() {
        return C0655gi.m1428b();
    }

    @Override // com.xiaomi.push.C0632fm.a
    /* renamed from: a */
    public void mo1273a() {
        if (this.f917a != null) {
            try {
                ((AlarmManager) this.f918a.getSystemService("alarm")).cancel(this.f917a);
            } catch (Exception e) {
            } catch (Throwable th) {
                this.f917a = null;
                AbstractC0407b.m74c("unregister timer");
                this.f916a = 0L;
                throw th;
            }
            this.f917a = null;
            AbstractC0407b.m74c("unregister timer");
            this.f916a = 0L;
        }
        this.f916a = 0L;
    }

    /* renamed from: a */
    public void m1277a(Intent intent, long j) {
        AlarmManager alarmManager = (AlarmManager) this.f918a.getSystemService("alarm");
        this.f917a = PendingIntent.getBroadcast(this.f918a, 0, intent, 0);
        if (Build.VERSION.SDK_INT >= 23) {
            C0504at.m495a(alarmManager, "setExactAndAllowWhileIdle", 0, Long.valueOf(j), this.f917a);
        } else if (Build.VERSION.SDK_INT >= 19) {
            m1276a(alarmManager, j, this.f917a);
        } else {
            alarmManager.set(0, j, this.f917a);
        }
        AbstractC0407b.m74c("register timer " + j);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:            if (r7.f916a < java.lang.System.currentTimeMillis()) goto L19;     */
    @Override // com.xiaomi.push.C0632fm.a
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1274a(boolean r8) {
        /*
            r7 = this;
            r0 = r7
            long r0 = r0.mo1273a()
            r9 = r0
            r0 = r8
            if (r0 != 0) goto L13
            r0 = r7
            long r0 = r0.f916a
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L13
            return
        L13:
            r0 = r8
            if (r0 == 0) goto L1b
            r0 = r7
            r0.mo1273a()
        L1b:
            r0 = r8
            if (r0 != 0) goto L43
            r0 = r7
            long r0 = r0.f916a
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L2b
            goto L43
        L2b:
            r0 = r7
            r1 = r7
            long r1 = r1.f916a
            r2 = r9
            long r1 = r1 + r2
            r0.f916a = r1
            r0 = r7
            long r0 = r0.f916a
            long r1 = java.lang.System.currentTimeMillis()
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L54
            goto L4b
        L43:
            r0 = r9
            long r1 = android.os.SystemClock.elapsedRealtime()
            r2 = r9
            long r1 = r1 % r2
            long r0 = r0 - r1
            r9 = r0
        L4b:
            r0 = r7
            long r1 = java.lang.System.currentTimeMillis()
            r2 = r9
            long r1 = r1 + r2
            r0.f916a = r1
        L54:
            android.content.Intent r0 = new android.content.Intent
            r1 = r0
            java.lang.String r2 = com.xiaomi.push.service.AbstractC0818ax.f2551o
            r1.<init>(r2)
            r11 = r0
            r0 = r11
            r1 = r7
            android.content.Context r1 = r1.f918a
            java.lang.String r1 = r1.getPackageName()
            android.content.Intent r0 = r0.setPackage(r1)
            r0 = r7
            r1 = r11
            r2 = r7
            long r2 = r2.f916a
            r0.m1277a(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0633fn.mo1274a(boolean):void");
    }

    @Override // com.xiaomi.push.C0632fm.a
    /* renamed from: a */
    public boolean mo1275a() {
        return this.f916a != 0;
    }
}
