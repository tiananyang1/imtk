package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.C0833bl;
import com.xiaomi.push.service.C0834bm;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.hu */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hu.class */
public class C0694hu {

    /* renamed from: a */
    private static C0694hu f1414a;

    /* renamed from: a */
    private final Context f1415a;

    /* renamed from: a */
    private Map<String, InterfaceC0695hv> f1416a = new HashMap();

    private C0694hu(Context context) {
        this.f1415a = context;
    }

    /* renamed from: a */
    public static C0694hu m1605a(Context context) {
        if (context == null) {
            AbstractC0407b.m75d("[TinyDataManager]:mContext is null, TinyDataManager.getInstance(Context) failed.");
            return null;
        }
        if (f1414a == null) {
            synchronized (C0694hu.class) {
                try {
                    if (f1414a == null) {
                        f1414a = new C0694hu(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f1414a;
    }

    /* renamed from: a */
    private boolean m1606a(String str, String str2, String str3, String str4, long j, String str5) {
        C0702ib c0702ib = new C0702ib();
        c0702ib.m1655d(str3);
        c0702ib.m1651c(str4);
        c0702ib.m1638a(j);
        c0702ib.m1647b(str5);
        c0702ib.m1640a(true);
        c0702ib.m1639a("push_sdk_channel");
        c0702ib.m1658e(str2);
        AbstractC0407b.m70a("TinyData TinyDataManager.upload item:" + c0702ib.m1656d() + "   ts:" + System.currentTimeMillis());
        return m1610a(c0702ib, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public InterfaceC0695hv m1607a() {
        InterfaceC0695hv interfaceC0695hv = this.f1416a.get("UPLOADER_PUSH_CHANNEL");
        if (interfaceC0695hv != null) {
            return interfaceC0695hv;
        }
        InterfaceC0695hv interfaceC0695hv2 = this.f1416a.get("UPLOADER_HTTP");
        if (interfaceC0695hv2 != null) {
            return interfaceC0695hv2;
        }
        return null;
    }

    /* renamed from: a */
    Map<String, InterfaceC0695hv> m1608a() {
        return this.f1416a;
    }

    /* renamed from: a */
    public void m1609a(InterfaceC0695hv interfaceC0695hv, String str) {
        if (interfaceC0695hv == null) {
            AbstractC0407b.m75d("[TinyDataManager]: please do not add null mUploader to TinyDataManager.");
        } else if (TextUtils.isEmpty(str)) {
            AbstractC0407b.m75d("[TinyDataManager]: can not add a provider from unkown resource.");
        } else {
            m1608a().put(str, interfaceC0695hv);
        }
    }

    /* renamed from: a */
    public boolean m1610a(C0702ib c0702ib, String str) {
        if (TextUtils.isEmpty(str)) {
            AbstractC0407b.m70a("pkgName is null or empty, upload ClientUploadDataItem failed.");
            return false;
        }
        if (C0833bl.m2664a(c0702ib, false)) {
            return false;
        }
        if (TextUtils.isEmpty(c0702ib.m1656d())) {
            c0702ib.m1661f(C0833bl.m2661a());
        }
        c0702ib.m1663g(str);
        C0834bm.m2665a(this.f1415a, c0702ib);
        return true;
    }

    /* renamed from: a */
    public boolean m1611a(String str, String str2, long j, String str3) {
        return m1606a(this.f1415a.getPackageName(), this.f1415a.getPackageName(), str, str2, j, str3);
    }
}
