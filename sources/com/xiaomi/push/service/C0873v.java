package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.XMPushService;
import java.io.IOException;
import java.util.Collection;
import org.json.JSONException;

/* renamed from: com.xiaomi.push.service.v */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/v.class */
public class C0873v extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    private XMPushService f2721a;

    /* renamed from: a */
    private String f2722a;

    /* renamed from: a */
    private byte[] f2723a;

    /* renamed from: b */
    private String f2724b;

    /* renamed from: c */
    private String f2725c;

    public C0873v(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.f2721a = xMPushService;
        this.f2722a = str;
        this.f2723a = bArr;
        this.f2724b = str2;
        this.f2725c = str3;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "register app";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        C0814at.b next;
        C0870s m2795a = C0871t.m2795a((Context) this.f2721a);
        C0870s c0870s = m2795a;
        if (m2795a == null) {
            try {
                c0870s = C0871t.m2796a(this.f2721a, this.f2722a, this.f2724b, this.f2725c);
            } catch (IOException | JSONException e) {
                AbstractC0407b.m72a(e);
                c0870s = m2795a;
            }
        }
        if (c0870s == null) {
            AbstractC0407b.m75d("no account for mipush");
            C0874w.m2813a(this.f2721a, ErrorCode.ERROR_AUTHERICATION_ERROR, "no account.");
            return;
        }
        Collection<C0814at.b> m2583a = C0814at.m2578a().m2583a("5");
        if (m2583a.isEmpty()) {
            next = c0870s.m2793a(this.f2721a);
            C0800af.m2500a(this.f2721a, next);
            C0814at.m2578a().m2589a(next);
        } else {
            next = m2583a.iterator().next();
        }
        if (!this.f2721a.m2483c()) {
            this.f2721a.m2474a(true);
            return;
        }
        try {
            if (next.f2497a == C0814at.c.binded) {
                C0800af.m2501a(this.f2721a, this.f2722a, this.f2723a);
            } else if (next.f2497a == C0814at.c.unbind) {
                XMPushService xMPushService = this.f2721a;
                XMPushService xMPushService2 = this.f2721a;
                xMPushService2.getClass();
                xMPushService.m2468a(new XMPushService.C0778a(next));
            }
        } catch (C0660gn e2) {
            AbstractC0407b.m72a(e2);
            this.f2721a.m2466a(10, e2);
        }
    }
}
