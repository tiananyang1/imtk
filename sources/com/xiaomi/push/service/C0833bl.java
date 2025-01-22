package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0701ia;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0883y;
import com.xiaomi.push.EnumC0714in;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.xiaomi.push.service.bl */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bl.class */
public class C0833bl {

    /* renamed from: a */
    private static AtomicLong f2609a = new AtomicLong(0);

    /* renamed from: a */
    private static SimpleDateFormat f2608a = new SimpleDateFormat("yyyy/MM/dd");

    /* renamed from: a */
    private static String f2607a = f2608a.format(Long.valueOf(System.currentTimeMillis()));

    /* renamed from: a */
    public static String m2661a() {
        String str;
        synchronized (C0833bl.class) {
            try {
                String format = f2608a.format(Long.valueOf(System.currentTimeMillis()));
                if (!TextUtils.equals(f2607a, format)) {
                    f2609a.set(0L);
                    f2607a = format;
                }
                str = format + Constants.ACCEPT_TIME_SEPARATOR_SERVER + f2609a.incrementAndGet();
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    /* renamed from: a */
    public static ArrayList<C0732je> m2662a(List<C0702ib> list, String str, String str2, int i) {
        String str3;
        if (list == null) {
            str3 = "requests can not be null in TinyDataHelper.transToThriftObj().";
        } else {
            if (list.size() != 0) {
                ArrayList<C0732je> arrayList = new ArrayList<>();
                C0701ia c0701ia = new C0701ia();
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    C0702ib c0702ib = list.get(i3);
                    if (c0702ib != null) {
                        int length = C0743jp.m2314a(c0702ib).length;
                        if (length > i) {
                            AbstractC0407b.m75d("TinyData is too big, ignore upload request item:" + c0702ib.m1656d());
                        } else {
                            C0701ia c0701ia2 = c0701ia;
                            int i4 = i2;
                            if (i2 + length > i) {
                                C0732je c0732je = new C0732je("-1", false);
                                c0732je.m2079d(str);
                                c0732je.m2071b(str2);
                                c0732je.m2075c(EnumC0714in.UploadTinyData.f1752a);
                                c0732je.m2062a(C0883y.m2864a(C0743jp.m2314a(c0701ia)));
                                arrayList.add(c0732je);
                                c0701ia2 = new C0701ia();
                                i4 = 0;
                            }
                            c0701ia2.m1633a(c0702ib);
                            i2 = i4 + length;
                            c0701ia = c0701ia2;
                        }
                    }
                }
                if (c0701ia.m1630a() != 0) {
                    C0732je c0732je2 = new C0732je("-1", false);
                    c0732je2.m2079d(str);
                    c0732je2.m2071b(str2);
                    c0732je2.m2075c(EnumC0714in.UploadTinyData.f1752a);
                    c0732je2.m2062a(C0883y.m2864a(C0743jp.m2314a(c0701ia)));
                    arrayList.add(c0732je2);
                }
                return arrayList;
            }
            str3 = "requests.length is 0 in TinyDataHelper.transToThriftObj().";
        }
        AbstractC0407b.m75d(str3);
        return null;
    }

    /* renamed from: a */
    public static void m2663a(Context context, String str, String str2, long j, String str3) {
        C0702ib c0702ib = new C0702ib();
        c0702ib.m1655d(str);
        c0702ib.m1651c(str2);
        c0702ib.m1638a(j);
        c0702ib.m1647b(str3);
        c0702ib.m1639a("push_sdk_channel");
        c0702ib.m1663g(context.getPackageName());
        c0702ib.m1658e(context.getPackageName());
        c0702ib.m1640a(true);
        c0702ib.m1646b(System.currentTimeMillis());
        c0702ib.m1661f(m2661a());
        C0834bm.m2665a(context, c0702ib);
    }

    /* renamed from: a */
    public static boolean m2664a(C0702ib c0702ib, boolean z) {
        String str;
        if (c0702ib == null) {
            str = "item is null, verfiy ClientUploadDataItem failed.";
        } else if (!z && TextUtils.isEmpty(c0702ib.f1508a)) {
            str = "item.channel is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(c0702ib.f1515d)) {
            str = "item.category is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(c0702ib.f1514c)) {
            str = "item.name is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (!C0509ay.m528a(c0702ib.f1515d)) {
            str = "item.category can only contain ascii char, verfiy ClientUploadDataItem failed.";
        } else if (!C0509ay.m528a(c0702ib.f1514c)) {
            str = "item.name can only contain ascii char, verfiy ClientUploadDataItem failed.";
        } else {
            if (c0702ib.f1513b == null || c0702ib.f1513b.length() <= 10240) {
                return false;
            }
            str = "item.data is too large(" + c0702ib.f1513b.length() + "), max size for data is 10240 , verfiy ClientUploadDataItem failed.";
        }
        AbstractC0407b.m70a(str);
        return true;
    }
}
