package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.push.C0488ad;
import com.xiaomi.push.C0713im;
import com.xiaomi.push.C0715io;
import com.xiaomi.push.C0730jc;
import com.xiaomi.push.C0731jd;
import com.xiaomi.push.EnumC0704id;
import com.xiaomi.push.EnumC0705ie;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.service.ap */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ap.class */
public class C0810ap {
    /* renamed from: a */
    public static int m2565a(C0809ao c0809ao, EnumC0704id enumC0704id) {
        String m2566a = m2566a(enumC0704id);
        int i = 0;
        if (C0811aq.f2482a[enumC0704id.ordinal()] == 1) {
            i = 1;
        }
        return c0809ao.f2481a.getInt(m2566a, i);
    }

    /* renamed from: a */
    private static String m2566a(EnumC0704id enumC0704id) {
        return "oc_version_" + enumC0704id.m1671a();
    }

    /* renamed from: a */
    private static List<Pair<Integer, Object>> m2567a(List<C0715io> list, boolean z) {
        if (C0488ad.m430a(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (C0715io c0715io : list) {
            int m1767a = c0715io.m1767a();
            EnumC0705ie m1672a = EnumC0705ie.m1672a(c0715io.m1775b());
            if (m1672a != null) {
                if (z && c0715io.f1765a) {
                    arrayList.add(new Pair(Integer.valueOf(m1767a), null));
                } else {
                    int i = C0811aq.f2483b[m1672a.ordinal()];
                    arrayList.add(i != 1 ? i != 2 ? i != 3 ? i != 4 ? null : new Pair(Integer.valueOf(m1767a), Boolean.valueOf(c0715io.m1787g())) : new Pair(Integer.valueOf(m1767a), c0715io.m1770a()) : new Pair(Integer.valueOf(m1767a), Long.valueOf(c0715io.m1769a())) : new Pair(Integer.valueOf(m1767a), Integer.valueOf(c0715io.m1778c())));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m2568a(C0809ao c0809ao, EnumC0704id enumC0704id, int i) {
        c0809ao.f2481a.edit().putInt(m2566a(enumC0704id), i).commit();
    }

    /* renamed from: a */
    public static void m2569a(C0809ao c0809ao, C0730jc c0730jc) {
        c0809ao.m2564b(m2567a(c0730jc.m2048a(), true));
    }

    /* renamed from: a */
    public static void m2570a(C0809ao c0809ao, C0731jd c0731jd) {
        for (C0713im c0713im : c0731jd.m2053a()) {
            if (c0713im.m1758a() > m2565a(c0809ao, c0713im.m1760a())) {
                m2568a(c0809ao, c0713im.m1760a(), c0713im.m1758a());
                c0809ao.m2562a(m2567a(c0713im.f1697a, false));
            }
        }
    }
}
