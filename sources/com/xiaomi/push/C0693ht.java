package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.C0833bl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.xiaomi.push.ht */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ht.class */
public class C0693ht {
    /* renamed from: a */
    private static HashMap<String, ArrayList<C0702ib>> m1601a(Context context, List<C0702ib> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap<String, ArrayList<C0702ib>> hashMap = new HashMap<>();
        for (C0702ib c0702ib : list) {
            m1604a(context, c0702ib);
            ArrayList<C0702ib> arrayList = hashMap.get(c0702ib.m1652c());
            ArrayList<C0702ib> arrayList2 = arrayList;
            if (arrayList == null) {
                arrayList2 = new ArrayList<>();
                hashMap.put(c0702ib.m1652c(), arrayList2);
            }
            arrayList2.add(c0702ib);
        }
        return hashMap;
    }

    /* renamed from: a */
    private static void m1602a(Context context, InterfaceC0695hv interfaceC0695hv, HashMap<String, ArrayList<C0702ib>> hashMap) {
        for (Map.Entry<String, ArrayList<C0702ib>> entry : hashMap.entrySet()) {
            try {
                ArrayList<C0702ib> value = entry.getValue();
                if (value != null && value.size() != 0) {
                    AbstractC0407b.m70a("TinyData is uploaded immediately item size:" + value.size());
                    interfaceC0695hv.mo1612a(value, value.get(0).m1659e(), entry.getKey());
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public static void m1603a(Context context, InterfaceC0695hv interfaceC0695hv, List<C0702ib> list) {
        HashMap<String, ArrayList<C0702ib>> m1601a = m1601a(context, list);
        if (m1601a != null && m1601a.size() != 0) {
            m1602a(context, interfaceC0695hv, m1601a);
            return;
        }
        AbstractC0407b.m70a("TinyData TinyDataCacheUploader.uploadTinyData itemsUploading == null || itemsUploading.size() == 0  ts:" + System.currentTimeMillis());
    }

    /* renamed from: a */
    private static void m1604a(Context context, C0702ib c0702ib) {
        if (c0702ib.f1511a) {
            c0702ib.m1639a("push_sdk_channel");
        }
        if (TextUtils.isEmpty(c0702ib.m1656d())) {
            c0702ib.m1661f(C0833bl.m2661a());
        }
        c0702ib.m1646b(System.currentTimeMillis());
        if (TextUtils.isEmpty(c0702ib.m1659e())) {
            c0702ib.m1658e(context.getPackageName());
        }
        if (TextUtils.isEmpty(c0702ib.m1652c())) {
            c0702ib.m1658e(c0702ib.m1659e());
        }
    }
}
