package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.XMPushService;
import java.io.File;

/* renamed from: com.xiaomi.push.hr */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hr.class */
public class C0691hr implements XMPushService.InterfaceC0789l {

    /* renamed from: a */
    private static boolean f1407a;

    /* renamed from: a */
    private int f1408a;

    /* renamed from: a */
    private Context f1409a;

    /* renamed from: b */
    private boolean f1410b;

    public C0691hr(Context context) {
        this.f1409a = context;
    }

    /* renamed from: a */
    private String m1589a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.f1409a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    /* renamed from: a */
    private void m1590a(Context context) {
        this.f1410b = C0809ao.m2557a(context).m2563a(EnumC0703ic.TinyDataUploadSwitch.m1669a(), true);
        this.f1408a = C0809ao.m2557a(context).m2561a(EnumC0703ic.TinyDataUploadFrequency.m1669a(), 7200);
        this.f1408a = Math.max(60, this.f1408a);
    }

    /* renamed from: a */
    public static void m1591a(boolean z) {
        f1407a = z;
    }

    /* renamed from: a */
    private boolean m1592a() {
        return Math.abs((System.currentTimeMillis() / 1000) - this.f1409a.getSharedPreferences("mipush_extra", 4).getLong("last_tiny_data_upload_timestamp", -1L)) > ((long) this.f1408a);
    }

    /* renamed from: a */
    private boolean m1593a(InterfaceC0695hv interfaceC0695hv) {
        return (!C0503as.m484b(this.f1409a) || interfaceC0695hv == null || TextUtils.isEmpty(m1589a(this.f1409a.getPackageName())) || !new File(this.f1409a.getFilesDir(), "tiny_data.data").exists() || f1407a) ? false : true;
    }

    @Override // com.xiaomi.push.service.XMPushService.InterfaceC0789l
    /* renamed from: a */
    public void mo1594a() {
        m1590a(this.f1409a);
        if (this.f1410b && m1592a()) {
            AbstractC0407b.m70a("TinyData TinyDataCacheProcessor.pingFollowUpAction ts:" + System.currentTimeMillis());
            InterfaceC0695hv m1607a = C0694hu.m1605a(this.f1409a).m1607a();
            if (m1593a(m1607a)) {
                f1407a = true;
                C0692hs.m1596a(this.f1409a, m1607a);
            } else {
                AbstractC0407b.m70a("TinyData TinyDataCacheProcessor.pingFollowUpAction !canUpload(uploader) ts:" + System.currentTimeMillis());
            }
        }
    }
}
