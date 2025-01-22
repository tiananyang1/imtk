package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;

/* renamed from: com.xiaomi.push.hs */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hs.class */
public class C0692hs {

    /* renamed from: a */
    private static boolean f1411a;

    /* renamed from: com.xiaomi.push.hs$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hs$a.class */
    static class a implements Runnable {

        /* renamed from: a */
        private Context f1412a;

        /* renamed from: a */
        private InterfaceC0695hv f1413a;

        public a(Context context, InterfaceC0695hv interfaceC0695hv) {
            this.f1413a = interfaceC0695hv;
            this.f1412a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0692hs.m1600c(this.f1412a, this.f1413a);
        }
    }

    /* renamed from: a */
    private static void m1595a(Context context) {
        File file = new File(context.getFilesDir() + "/tdReadTemp");
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    /* renamed from: a */
    public static void m1596a(Context context, InterfaceC0695hv interfaceC0695hv) {
        C0493ai.m439a(context).m443a(new a(context, interfaceC0695hv));
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0063, code lost:            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m75d(r9);     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0162, code lost:            r9 = "TinyData read from cache file failed cause lengthBuffer < 1 || too big. length:" + r0;     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m1597a(android.content.Context r6, com.xiaomi.push.InterfaceC0695hv r7, java.io.File r8, byte[] r9) {
        /*
            Method dump skipped, instructions count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0692hs.m1597a(android.content.Context, com.xiaomi.push.hv, java.io.File, byte[]):void");
    }

    /* renamed from: b */
    private static void m1598b(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 4).edit();
        edit.putLong("last_tiny_data_upload_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0188  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1600c(android.content.Context r5, com.xiaomi.push.InterfaceC0695hv r6) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0692hs.m1600c(android.content.Context, com.xiaomi.push.hv):void");
    }
}
