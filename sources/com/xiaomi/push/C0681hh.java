package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.providers.C0773a;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.xiaomi.push.hh */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hh.class */
public class C0681hh {

    /* renamed from: a */
    private static C0496al f1364a = new C0496al(true);

    /* renamed from: a */
    private static int f1362a = -1;

    /* renamed from: a */
    private static long f1363a = System.currentTimeMillis();

    /* renamed from: a */
    private static final Object f1366a = new Object();

    /* renamed from: a */
    private static List<a> f1368a = Collections.synchronizedList(new ArrayList());

    /* renamed from: a */
    private static String f1367a = "";

    /* renamed from: a */
    private static C0773a f1365a = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.hh$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hh$a.class */
    public static class a {

        /* renamed from: a */
        public int f1369a;

        /* renamed from: a */
        public long f1370a;

        /* renamed from: a */
        public String f1371a;

        /* renamed from: b */
        public int f1372b;

        /* renamed from: b */
        public long f1373b;

        /* renamed from: b */
        public String f1374b;

        public a(String str, long j, int i, int i2, String str2, long j2) {
            this.f1371a = "";
            this.f1370a = 0L;
            this.f1369a = -1;
            this.f1372b = -1;
            this.f1374b = "";
            this.f1373b = 0L;
            this.f1371a = str;
            this.f1370a = j;
            this.f1369a = i;
            this.f1372b = i2;
            this.f1374b = str2;
            this.f1373b = j2;
        }

        /* renamed from: a */
        public boolean m1550a(a aVar) {
            return TextUtils.equals(aVar.f1371a, this.f1371a) && TextUtils.equals(aVar.f1374b, this.f1374b) && aVar.f1369a == this.f1369a && aVar.f1372b == this.f1372b && Math.abs(aVar.f1370a - this.f1370a) <= 5000;
        }
    }

    /* renamed from: a */
    public static int m1535a(Context context) {
        if (f1362a == -1) {
            f1362a = m1548b(context);
        }
        return f1362a;
    }

    /* renamed from: a */
    public static int m1536a(String str) {
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            return str.getBytes().length;
        }
    }

    /* renamed from: a */
    private static long m1537a(int i, long j, boolean z, long j2, boolean z2) {
        if (z && z2) {
            long j3 = f1363a;
            f1363a = j2;
            if (j2 - j3 > 30000 && j > 1024) {
                return j * 2;
            }
        }
        return (j * (i == 0 ? 13 : 11)) / 10;
    }

    /* renamed from: a */
    private static C0773a m1538a(Context context) {
        C0773a c0773a = f1365a;
        if (c0773a != null) {
            return c0773a;
        }
        f1365a = new C0773a(context);
        return f1365a;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:23:0x0045
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    /* renamed from: a */
    private static java.lang.String m1540a(android.content.Context r3) {
        /*
            java.lang.Class<com.xiaomi.push.hh> r0 = com.xiaomi.push.C0681hh.class
            monitor-enter(r0)
            boolean r0 = com.xiaomi.push.C0770n.m2408d()     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto Lf
            java.lang.Class<com.xiaomi.push.hh> r0 = com.xiaomi.push.C0681hh.class
            monitor-exit(r0)
            java.lang.String r0 = ""
            return r0
        Lf:
            java.lang.String r0 = com.xiaomi.push.C0681hh.f1367a     // Catch: java.lang.Throwable -> L3f
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L3f
            if (r0 != 0) goto L21
            java.lang.String r0 = com.xiaomi.push.C0681hh.f1367a     // Catch: java.lang.Throwable -> L3f
            r3 = r0
            java.lang.Class<com.xiaomi.push.hh> r0 = com.xiaomi.push.C0681hh.class
            monitor-exit(r0)
            r0 = r3
            return r0
        L21:
            r0 = r3
            java.lang.String r1 = "phone"
            java.lang.Object r0 = r0.getSystemService(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L45
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L45
            r3 = r0
            r0 = r3
            if (r0 == 0) goto L36
            r0 = r3
            java.lang.String r0 = r0.getSubscriberId()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L45
            com.xiaomi.push.C0681hh.f1367a = r0     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L45
        L36:
            java.lang.String r0 = com.xiaomi.push.C0681hh.f1367a     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L3f java.lang.Exception -> L45
            r3 = r0
            java.lang.Class<com.xiaomi.push.hh> r0 = com.xiaomi.push.C0681hh.class
            monitor-exit(r0)
            r0 = r3
            return r0
        L3f:
            r3 = move-exception
            java.lang.Class<com.xiaomi.push.hh> r0 = com.xiaomi.push.C0681hh.class
            monitor-exit(r0)
            r0 = r3
            throw r0
        L45:
            r3 = move-exception
            goto L36
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0681hh.m1540a(android.content.Context):java.lang.String");
    }

    /* renamed from: a */
    public static void m1542a(Context context) {
        f1362a = m1548b(context);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    /* renamed from: a */
    private static void m1543a(Context context, String str, long j, boolean z, long j2) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    /* renamed from: a */
    public static void m1544a(Context context, String str, long j, boolean z, boolean z2, long j2) {
        m1543a(context, str, m1537a(m1535a(context), j, z, j2, z2), z, j2);
    }

    /* renamed from: a */
    private static void m1546a(a aVar) {
        for (a aVar2 : f1368a) {
            if (aVar2.m1550a(aVar)) {
                aVar2.f1373b += aVar.f1373b;
                return;
            }
        }
        f1368a.add(aVar);
    }

    /* renamed from: a */
    public static void m1547a(String str) {
        synchronized (C0681hh.class) {
            try {
                if (!C0770n.m2408d() && !TextUtils.isEmpty(str)) {
                    f1367a = str;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: b */
    private static int m1548b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -1;
            }
            return activeNetworkInfo.getType();
        } catch (Exception e) {
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m1549b(Context context, List<a> list) {
        try {
            synchronized (C0773a.f2365a) {
                SQLiteDatabase writableDatabase = m1538a(context).getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    for (a aVar : list) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Constants.PACKAGE_NAME, aVar.f1371a);
                        contentValues.put("message_ts", Long.valueOf(aVar.f1370a));
                        contentValues.put("network_type", Integer.valueOf(aVar.f1369a));
                        contentValues.put("bytes", Long.valueOf(aVar.f1373b));
                        contentValues.put("rcv", Integer.valueOf(aVar.f1372b));
                        contentValues.put("imsi", aVar.f1374b);
                        writableDatabase.insert("traffic", null, contentValues);
                    }
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e) {
            AbstractC0407b.m72a(e);
        }
    }
}
