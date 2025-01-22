package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0660gn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.xiaomi.push.service.w */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/w.class */
public class C0874w {

    /* renamed from: a */
    private static final Map<String, byte[]> f2727a = new HashMap();

    /* renamed from: a */
    private static ArrayList<Pair<String, byte[]>> f2726a = new ArrayList<>();

    /* renamed from: a */
    public static void m2813a(Context context, int i, String str) {
        synchronized (f2727a) {
            for (String str2 : f2727a.keySet()) {
                m2814a(context, str2, f2727a.get(str2), i, str);
            }
            f2727a.clear();
        }
    }

    /* renamed from: a */
    public static void m2814a(Context context, String str, byte[] bArr, int i, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, C0800af.m2497a(str));
    }

    /* renamed from: a */
    public static void m2815a(XMPushService xMPushService) {
        try {
            synchronized (f2727a) {
                for (String str : f2727a.keySet()) {
                    C0800af.m2501a(xMPushService, str, f2727a.get(str));
                }
                f2727a.clear();
            }
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            xMPushService.m2466a(10, e);
        }
    }

    /* renamed from: a */
    public static void m2816a(String str, byte[] bArr) {
        synchronized (f2727a) {
            f2727a.put(str, bArr);
        }
    }

    /* renamed from: b */
    public static void m2817b(XMPushService xMPushService) {
        ArrayList<Pair<String, byte[]>> arrayList;
        try {
            synchronized (f2726a) {
                arrayList = f2726a;
                f2726a = new ArrayList<>();
            }
            Iterator<Pair<String, byte[]>> it = arrayList.iterator();
            while (it.hasNext()) {
                Pair<String, byte[]> next = it.next();
                C0800af.m2501a(xMPushService, (String) next.first, (byte[]) next.second);
            }
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            xMPushService.m2466a(10, e);
        }
    }

    /* renamed from: b */
    public static void m2818b(String str, byte[] bArr) {
        synchronized (f2726a) {
            f2726a.add(new Pair<>(str, bArr));
            if (f2726a.size() > 50) {
                f2726a.remove(0);
            }
        }
    }
}
