package com.xiaomi.push.service;

import android.content.Context;
import android.os.Messenger;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.AbstractC0649gc;
import com.xiaomi.push.AbstractC0666gt;
import com.xiaomi.push.C0574di;
import com.xiaomi.push.C0641fv;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.C0720it;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0749jv;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.InterfaceC0744jq;
import com.xiaomi.push.service.C0814at;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.af */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/af.class */
public final class C0800af {
    /* renamed from: a */
    static C0641fv m2492a(XMPushService xMPushService, byte[] bArr) {
        C0729jb c0729jb = new C0729jb();
        try {
            C0743jp.m2313a(c0729jb, bArr);
            return m2493a(C0871t.m2795a((Context) xMPushService), xMPushService, c0729jb);
        } catch (C0749jv e) {
            AbstractC0407b.m72a(e);
            return null;
        }
    }

    /* renamed from: a */
    static C0641fv m2493a(C0870s c0870s, Context context, C0729jb c0729jb) {
        try {
            C0641fv c0641fv = new C0641fv();
            c0641fv.m1328a(5);
            c0641fv.m1342c(c0870s.f2708a);
            c0641fv.m1339b(m2496a(c0729jb));
            c0641fv.m1331a("SECMSG", SettingsJsonConstants.PROMPT_MESSAGE_KEY);
            String str = c0870s.f2708a;
            c0729jb.f1999a.f1851a = str.substring(0, str.indexOf("@"));
            c0729jb.f1999a.f1855c = str.substring(str.indexOf("/") + 1);
            c0641fv.m1333a(C0743jp.m2314a(c0729jb), c0870s.f2710c);
            c0641fv.m1332a((short) 1);
            AbstractC0407b.m70a("try send mi push message. packagename:" + c0729jb.f2004b + " action:" + c0729jb.f1997a);
            return c0641fv;
        } catch (NullPointerException e) {
            AbstractC0407b.m72a(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C0729jb m2494a(String str, String str2) {
        C0732je c0732je = new C0732je();
        c0732je.m2071b(str2);
        c0732je.m2075c("package uninstalled");
        c0732je.m2058a(AbstractC0666gt.m1481i());
        c0732je.m2061a(false);
        return m2495a(str, str2, c0732je, EnumC0696hw.Notification);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <T extends InterfaceC0744jq<T, ?>> C0729jb m2495a(String str, String str2, T t, EnumC0696hw enumC0696hw) {
        byte[] m2314a = C0743jp.m2314a(t);
        C0729jb c0729jb = new C0729jb();
        C0720it c0720it = new C0720it();
        c0720it.f1850a = 5L;
        c0720it.f1851a = "fakeid";
        c0729jb.m2025a(c0720it);
        c0729jb.m2027a(ByteBuffer.wrap(m2314a));
        c0729jb.m2023a(enumC0696hw);
        c0729jb.m2036b(true);
        c0729jb.m2035b(str);
        c0729jb.m2028a(false);
        c0729jb.m2026a(str2);
        return c0729jb;
    }

    /* renamed from: a */
    private static String m2496a(C0729jb c0729jb) {
        if (c0729jb.f1998a != null && c0729jb.f1998a.f1829b != null) {
            String str = c0729jb.f1998a.f1829b.get("ext_traffic_source_pkg");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return c0729jb.f2004b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m2497a(String str) {
        return str + ".permission.MIPUSH_RECEIVE";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2498a(XMPushService xMPushService) {
        C0870s m2795a = C0871t.m2795a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext()));
        if (m2795a != null) {
            C0814at.b m2793a = C0871t.m2795a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m2793a(xMPushService);
            m2500a(xMPushService, m2793a);
            C0814at.m2578a().m2589a(m2793a);
            C0832bk.m2657a(xMPushService).m2659a(new C0801ag("GAID", 172800L, xMPushService, m2795a));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2499a(XMPushService xMPushService, C0729jb c0729jb) {
        C0574di.m918a(c0729jb.m2037b(), StubApp.getOrigApplicationContext(xMPushService.getApplicationContext()), c0729jb, -1);
        AbstractC0649gc m2462a = xMPushService.m2462a();
        if (m2462a == null) {
            throw new C0660gn("try send msg while connection is null.");
        }
        if (!m2462a.mo1380a()) {
            throw new C0660gn("Don't support XMPP connection.");
        }
        C0641fv m2493a = m2493a(C0871t.m2795a((Context) xMPushService), xMPushService, c0729jb);
        if (m2493a != null) {
            m2462a.mo1382b(m2493a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2500a(XMPushService xMPushService, C0814at.b bVar) {
        bVar.m2605a((Messenger) null);
        bVar.m2606a(new C0802ah(xMPushService));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2501a(XMPushService xMPushService, String str, byte[] bArr) {
        C0574di.m920a(str, StubApp.getOrigApplicationContext(xMPushService.getApplicationContext()), bArr);
        AbstractC0649gc m2462a = xMPushService.m2462a();
        if (m2462a == null) {
            throw new C0660gn("try send msg while connection is null.");
        }
        if (!m2462a.mo1380a()) {
            throw new C0660gn("Don't support XMPP connection.");
        }
        C0641fv m2492a = m2492a(xMPushService, bArr);
        if (m2492a != null) {
            m2462a.mo1382b(m2492a);
        } else {
            C0874w.m2814a(xMPushService, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, "not a valid message");
        }
    }
}
