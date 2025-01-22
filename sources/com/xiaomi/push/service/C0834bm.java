package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0487ac;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0506av;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0700i;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0775r;
import com.xiaomi.push.C0880v;
import com.xiaomi.push.C0883y;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* renamed from: com.xiaomi.push.service.bm */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bm.class */
public class C0834bm {

    /* renamed from: a */
    public static final Object f2610a = new Object();

    /* renamed from: a */
    public static void m2665a(Context context, C0702ib c0702ib) {
        if (!C0880v.m2853b() || Constants.HYBRID_PACKAGE_NAME.equals(c0702ib.m1659e())) {
            AbstractC0407b.m70a("TinyData TinyDataStorage.cacheTinyData cache data to file begin item:" + c0702ib.m1656d() + "  ts:" + System.currentTimeMillis());
            C0493ai.m439a(context).m443a(new RunnableC0835bn(context, c0702ib));
        }
    }

    /* renamed from: a */
    public static byte[] m2666a(Context context) {
        String m2419a = C0775r.m2416a(context).m2419a("mipush", "td_key", "");
        String str = m2419a;
        if (TextUtils.isEmpty(m2419a)) {
            str = C0509ay.m520a(20);
            C0775r.m2416a(context).m2420a("mipush", "td_key", str);
        }
        return m2667a(str);
    }

    /* renamed from: a */
    private static byte[] m2667a(String str) {
        byte[] copyOf = Arrays.copyOf(C0506av.m510a(str), 16);
        copyOf[0] = 68;
        copyOf[15] = 84;
        return copyOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v14, types: [java.io.Closeable, java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* renamed from: c */
    public static void m2669c(Context context, C0702ib c0702ib) {
        Closeable closeable;
        String sb;
        try {
            try {
                byte[] m1629b = C0700i.m1629b(m2666a((Context) context), C0743jp.m2314a(c0702ib));
                if (m1629b != null && m1629b.length >= 1) {
                    if (m1629b.length > 10240) {
                        AbstractC0407b.m70a("TinyData write to cache file failed case too much data content item:" + c0702ib.m1656d() + "  ts:" + System.currentTimeMillis());
                        C0883y.m2858a((Closeable) null);
                        C0883y.m2858a((Closeable) null);
                        return;
                    }
                    context = new BufferedOutputStream(new FileOutputStream(new File(context.getFilesDir(), "tiny_data.data"), true));
                    try {
                        context.write(C0487ac.m429a(m1629b.length));
                        context.write(m1629b);
                        context.flush();
                        AbstractC0407b.m70a("TinyData write to cache file success item:" + c0702ib.m1656d() + "  ts:" + System.currentTimeMillis());
                        C0883y.m2858a((Closeable) null);
                        C0883y.m2858a((Closeable) context);
                        return;
                    } catch (IOException e) {
                        e = e;
                        closeable = context;
                        StringBuilder sb2 = new StringBuilder();
                        Closeable closeable2 = closeable;
                        sb2.append("TinyData write to cache file failed cause io exception item:");
                        Closeable closeable3 = closeable;
                        sb2.append(c0702ib.m1656d());
                        Closeable closeable4 = closeable;
                        sb = sb2.toString();
                        context = closeable;
                        AbstractC0407b.m71a(sb, e);
                        C0883y.m2858a((Closeable) null);
                        C0883y.m2858a(closeable);
                        return;
                    } catch (Exception e2) {
                        e = e2;
                        closeable = context;
                        StringBuilder sb3 = new StringBuilder();
                        Closeable closeable5 = closeable;
                        sb3.append("TinyData write to cache file  failed item:");
                        Closeable closeable6 = closeable;
                        sb3.append(c0702ib.m1656d());
                        Closeable closeable7 = closeable;
                        sb = sb3.toString();
                        context = closeable;
                        AbstractC0407b.m71a(sb, e);
                        C0883y.m2858a((Closeable) null);
                        C0883y.m2858a(closeable);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        C0883y.m2858a((Closeable) null);
                        C0883y.m2858a(context);
                        throw th;
                    }
                }
                AbstractC0407b.m70a("TinyData write to cache file failed case encryption fail item:" + c0702ib.m1656d() + "  ts:" + System.currentTimeMillis());
                C0883y.m2858a((Closeable) null);
                C0883y.m2858a((Closeable) null);
            } catch (IOException e3) {
                e = e3;
                closeable = null;
            } catch (Exception e4) {
                e = e4;
                closeable = null;
            } catch (Throwable th2) {
                th = th2;
                context = 0;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
