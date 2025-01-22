package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.bj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bj.class */
public class C0521bj implements InterfaceC0652gf {

    /* renamed from: a */
    final /* synthetic */ C0520bi f491a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0521bj(C0520bi c0520bi) {
        this.f491a = c0520bi;
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo586a(AbstractC0649gc abstractC0649gc) {
        AbstractC0649gc abstractC0649gc2;
        StringBuilder sb = new StringBuilder();
        sb.append("[Slim] ");
        sb.append(this.f491a.f487a.format(new Date()));
        sb.append(" Connection reconnected (");
        abstractC0649gc2 = this.f491a.f484a;
        sb.append(abstractC0649gc2.hashCode());
        sb.append(")");
        AbstractC0407b.m74c(sb.toString());
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo587a(AbstractC0649gc abstractC0649gc, int i, Exception exc) {
        AbstractC0649gc abstractC0649gc2;
        StringBuilder sb = new StringBuilder();
        sb.append("[Slim] ");
        sb.append(this.f491a.f487a.format(new Date()));
        sb.append(" Connection closed (");
        abstractC0649gc2 = this.f491a.f484a;
        sb.append(abstractC0649gc2.hashCode());
        sb.append(")");
        AbstractC0407b.m74c(sb.toString());
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo588a(AbstractC0649gc abstractC0649gc, Exception exc) {
        AbstractC0649gc abstractC0649gc2;
        StringBuilder sb = new StringBuilder();
        sb.append("[Slim] ");
        sb.append(this.f491a.f487a.format(new Date()));
        sb.append(" Reconnection failed due to an exception (");
        abstractC0649gc2 = this.f491a.f484a;
        sb.append(abstractC0649gc2.hashCode());
        sb.append(")");
        AbstractC0407b.m74c(sb.toString());
        exc.printStackTrace();
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: b */
    public void mo589b(AbstractC0649gc abstractC0649gc) {
        AbstractC0649gc abstractC0649gc2;
        StringBuilder sb = new StringBuilder();
        sb.append("[Slim] ");
        sb.append(this.f491a.f487a.format(new Date()));
        sb.append(" Connection started (");
        abstractC0649gc2 = this.f491a.f484a;
        sb.append(abstractC0649gc2.hashCode());
        sb.append(")");
        AbstractC0407b.m74c(sb.toString());
    }
}
