package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.mipush.sdk.g */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/g.class */
public class C0464g implements AbstractPushManager {

    /* renamed from: a */
    private static volatile C0464g f361a;

    /* renamed from: a */
    private Context f362a;

    /* renamed from: a */
    private PushConfiguration f363a;

    /* renamed from: a */
    private Map<EnumC0463f, AbstractPushManager> f364a = new HashMap();

    private C0464g(Context context) {
        this.f362a = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    /* renamed from: a */
    public static C0464g m331a(Context context) {
        if (f361a == null) {
            synchronized (C0464g.class) {
                try {
                    if (f361a == null) {
                        f361a = new C0464g(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f361a;
    }

    /* renamed from: a */
    private void m332a() {
        AbstractPushManager m333a;
        AbstractPushManager m333a2;
        AbstractPushManager m333a3;
        PushConfiguration pushConfiguration = this.f363a;
        if (pushConfiguration != null) {
            if (pushConfiguration.getOpenHmsPush()) {
                StringBuilder sb = new StringBuilder();
                sb.append("ASSEMBLE_PUSH : ");
                sb.append(" HW user switch : " + this.f363a.getOpenHmsPush() + " HW online switch : " + C0466i.m351a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_HUAWEI) + " HW isSupport : " + EnumC0440aq.HUAWEI.equals(C0471n.m362a(this.f362a)));
                AbstractC0407b.m70a(sb.toString());
            }
            if (this.f363a.getOpenHmsPush() && C0466i.m351a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_HUAWEI) && EnumC0440aq.HUAWEI.equals(C0471n.m362a(this.f362a))) {
                if (!m337a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI)) {
                    m336a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI, C0444au.m198a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_HUAWEI));
                }
                AbstractC0407b.m74c("hw manager add to list");
            } else if (m337a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI) && (m333a = m333a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI)) != null) {
                m335a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI);
                m333a.unregister();
            }
            if (this.f363a.getOpenFCMPush()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("ASSEMBLE_PUSH : ");
                sb2.append(" FCM user switch : " + this.f363a.getOpenFCMPush() + " FCM online switch : " + C0466i.m351a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_FCM) + " FCM isSupport : " + C0471n.m364a(this.f362a));
                AbstractC0407b.m70a(sb2.toString());
            }
            if (this.f363a.getOpenFCMPush() && C0466i.m351a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_FCM) && C0471n.m364a(this.f362a)) {
                if (!m337a(EnumC0463f.ASSEMBLE_PUSH_FCM)) {
                    m336a(EnumC0463f.ASSEMBLE_PUSH_FCM, C0444au.m198a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_FCM));
                }
                AbstractC0407b.m74c("fcm manager add to list");
            } else if (m337a(EnumC0463f.ASSEMBLE_PUSH_FCM) && (m333a2 = m333a(EnumC0463f.ASSEMBLE_PUSH_FCM)) != null) {
                m335a(EnumC0463f.ASSEMBLE_PUSH_FCM);
                m333a2.unregister();
            }
            if (this.f363a.getOpenCOSPush()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("ASSEMBLE_PUSH : ");
                sb3.append(" COS user switch : " + this.f363a.getOpenCOSPush() + " COS online switch : " + C0466i.m351a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_COS) + " COS isSupport : " + C0471n.m365b(this.f362a));
                AbstractC0407b.m70a(sb3.toString());
            }
            if (this.f363a.getOpenCOSPush() && C0466i.m351a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_COS) && C0471n.m365b(this.f362a)) {
                m336a(EnumC0463f.ASSEMBLE_PUSH_COS, C0444au.m198a(this.f362a, EnumC0463f.ASSEMBLE_PUSH_COS));
            } else {
                if (!m337a(EnumC0463f.ASSEMBLE_PUSH_COS) || (m333a3 = m333a(EnumC0463f.ASSEMBLE_PUSH_COS)) == null) {
                    return;
                }
                m335a(EnumC0463f.ASSEMBLE_PUSH_COS);
                m333a3.unregister();
            }
        }
    }

    /* renamed from: a */
    public AbstractPushManager m333a(EnumC0463f enumC0463f) {
        return this.f364a.get(enumC0463f);
    }

    /* renamed from: a */
    public void m334a(PushConfiguration pushConfiguration) {
        this.f363a = pushConfiguration;
    }

    /* renamed from: a */
    public void m335a(EnumC0463f enumC0463f) {
        this.f364a.remove(enumC0463f);
    }

    /* renamed from: a */
    public void m336a(EnumC0463f enumC0463f, AbstractPushManager abstractPushManager) {
        if (abstractPushManager != null) {
            if (this.f364a.containsKey(enumC0463f)) {
                this.f364a.remove(enumC0463f);
            }
            this.f364a.put(enumC0463f, abstractPushManager);
        }
    }

    /* renamed from: a */
    public boolean m337a(EnumC0463f enumC0463f) {
        return this.f364a.containsKey(enumC0463f);
    }

    /* renamed from: b */
    public boolean m338b(EnumC0463f enumC0463f) {
        PushConfiguration pushConfiguration;
        int i = C0465h.f365a[enumC0463f.ordinal()];
        if (i == 1) {
            PushConfiguration pushConfiguration2 = this.f363a;
            if (pushConfiguration2 != null) {
                return pushConfiguration2.getOpenHmsPush();
            }
            return false;
        }
        if (i != 2) {
            if (i == 3 && (pushConfiguration = this.f363a) != null) {
                return pushConfiguration.getOpenCOSPush();
            }
            return false;
        }
        PushConfiguration pushConfiguration3 = this.f363a;
        if (pushConfiguration3 != null) {
            return pushConfiguration3.getOpenFCMPush();
        }
        return false;
    }

    @Override // com.xiaomi.mipush.sdk.AbstractPushManager
    public void register() {
        AbstractC0407b.m70a("ASSEMBLE_PUSH : assemble push register");
        if (this.f364a.size() <= 0) {
            m332a();
        }
        if (this.f364a.size() > 0) {
            for (AbstractPushManager abstractPushManager : this.f364a.values()) {
                if (abstractPushManager != null) {
                    abstractPushManager.register();
                }
            }
            C0466i.m344a(this.f362a);
        }
    }

    @Override // com.xiaomi.mipush.sdk.AbstractPushManager
    public void unregister() {
        AbstractC0407b.m70a("ASSEMBLE_PUSH : assemble push unregister");
        for (AbstractPushManager abstractPushManager : this.f364a.values()) {
            if (abstractPushManager != null) {
                abstractPushManager.unregister();
            }
        }
        this.f364a.clear();
    }
}
