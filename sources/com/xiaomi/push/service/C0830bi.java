package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0496al;
import com.xiaomi.push.C0538c;
import com.xiaomi.push.C0611es;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.C0679hf;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0880v;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.service.bi */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bi.class */
public class C0830bi {

    /* renamed from: a */
    private static C0830bi f2595a = new C0830bi();

    /* renamed from: a */
    private static String f2596a;

    /* renamed from: a */
    private C0496al.b f2597a;

    /* renamed from: a */
    private C0611es.a f2598a;

    /* renamed from: a */
    private List<a> f2599a = new ArrayList();

    /* renamed from: com.xiaomi.push.service.bi$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bi$a.class */
    public static abstract class a {
        /* renamed from: a */
        public void mo2612a(C0611es.a aVar) {
        }

        /* renamed from: a */
        public void mo1577a(C0612et.b bVar) {
        }
    }

    private C0830bi() {
    }

    /* renamed from: a */
    public static C0830bi m2642a() {
        return f2595a;
    }

    /* renamed from: a */
    public static String m2643a() {
        String str;
        synchronized (C0830bi.class) {
            try {
                if (f2596a == null) {
                    SharedPreferences sharedPreferences = C0880v.m2849a().getSharedPreferences("XMPushServiceConfig", 0);
                    f2596a = sharedPreferences.getString("DeviceUUID", null);
                    if (f2596a == null) {
                        f2596a = C0727j.m1982a(C0880v.m2849a(), false);
                        if (f2596a != null) {
                            sharedPreferences.edit().putString("DeviceUUID", f2596a).commit();
                        }
                    }
                }
                str = f2596a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    /* renamed from: b */
    private void m2646b() {
        if (this.f2598a == null) {
            m2648d();
        }
    }

    /* renamed from: c */
    private void m2647c() {
        if (this.f2597a != null) {
            return;
        }
        this.f2597a = new C0831bj(this);
        C0679hf.m1532a(this.f2597a);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m2648d() {
        /*
            r5 = this;
            r0 = 0
            r9 = r0
            r0 = 0
            r6 = r0
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L3b
            r1 = r0
            android.content.Context r2 = com.xiaomi.push.C0880v.m2849a()     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L3b
            java.lang.String r3 = "XMCloudCfg"
            java.io.FileInputStream r2 = r2.openFileInput(r3)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L3b
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L3b
            r7 = r0
            r0 = r5
            r1 = r7
            com.xiaomi.push.b r1 = com.xiaomi.push.C0511b.m534a(r1)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            com.xiaomi.push.es$a r1 = com.xiaomi.push.C0611es.a.m1009b(r1)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            r0.f2598a = r1     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            r0 = r7
            r0.close()     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2f
            r0 = r7
            com.xiaomi.push.C0883y.m2858a(r0)
            goto L6e
        L2b:
            r6 = move-exception
            goto L81
        L2f:
            r8 = move-exception
            goto L3f
        L33:
            r8 = move-exception
            r0 = r6
            r7 = r0
            r0 = r8
            r6 = r0
            goto L81
        L3b:
            r8 = move-exception
            r0 = r9
            r7 = r0
        L3f:
            r0 = r7
            r6 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L33
            r1 = r0
            r1.<init>()     // Catch: java.lang.Throwable -> L33
            r9 = r0
            r0 = r7
            r6 = r0
            r0 = r9
            java.lang.String r1 = "load config failure: "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L33
            r0 = r7
            r6 = r0
            r0 = r9
            r1 = r8
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L33
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L33
            r0 = r7
            r6 = r0
            r0 = r9
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L33
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m70a(r0)     // Catch: java.lang.Throwable -> L33
            r0 = r7
            com.xiaomi.push.C0883y.m2858a(r0)
        L6e:
            r0 = r5
            com.xiaomi.push.es$a r0 = r0.f2598a
            if (r0 != 0) goto L80
            r0 = r5
            com.xiaomi.push.es$a r1 = new com.xiaomi.push.es$a
            r2 = r1
            r2.<init>()
            r0.f2598a = r1
        L80:
            return
        L81:
            r0 = r7
            com.xiaomi.push.C0883y.m2858a(r0)
            r0 = r6
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0830bi.m2648d():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m2649e() {
        try {
            if (this.f2598a != null) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(C0880v.m2849a().openFileOutput("XMCloudCfg", 0));
                C0538c m670a = C0538c.m670a(bufferedOutputStream);
                this.f2598a.mo963a(m670a);
                m670a.m682a();
                bufferedOutputStream.close();
            }
        } catch (Exception e) {
            AbstractC0407b.m70a("save config failure: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m2650a() {
        m2646b();
        C0611es.a aVar = this.f2598a;
        if (aVar != null) {
            return aVar.m1018c();
        }
        return 0;
    }

    /* renamed from: a */
    public C0611es.a m2651a() {
        m2646b();
        return this.f2598a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2652a() {
        synchronized (this) {
            this.f2599a.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2653a(C0612et.b bVar) {
        a[] aVarArr;
        if (bVar.m1069d() && bVar.m1068d() > m2650a()) {
            m2647c();
        }
        synchronized (this) {
            aVarArr = (a[]) this.f2599a.toArray(new a[this.f2599a.size()]);
        }
        int length = aVarArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            aVarArr[i2].mo1577a(bVar);
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public void m2654a(a aVar) {
        synchronized (this) {
            this.f2599a.add(aVar);
        }
    }
}
