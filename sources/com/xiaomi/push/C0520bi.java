package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.xiaomi.push.bi */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bi.class */
public class C0520bi implements InterfaceC0661go {

    /* renamed from: a */
    public static boolean f482a;

    /* renamed from: a */
    private AbstractC0649gc f484a;

    /* renamed from: a */
    private SimpleDateFormat f487a = new SimpleDateFormat("hh:mm:ss aaa");

    /* renamed from: a */
    private a f483a = null;

    /* renamed from: b */
    private a f488b = null;

    /* renamed from: a */
    private InterfaceC0652gf f485a = null;

    /* renamed from: a */
    private final String f486a = "[Slim] ";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.bi$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bi$a.class */
    public class a implements InterfaceC0654gh, InterfaceC0662gp {

        /* renamed from: a */
        String f490a;

        a(boolean z) {
            this.f490a = z ? " RCV " : " Sent ";
        }

        @Override // com.xiaomi.push.InterfaceC0654gh
        /* renamed from: a */
        public void mo583a(C0641fv c0641fv) {
            String str;
            StringBuilder sb;
            if (C0520bi.f482a) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("[Slim] ");
                sb2.append(C0520bi.this.f487a.format(new Date()));
                sb2.append(this.f490a);
                String c0641fv2 = c0641fv.toString();
                sb = sb2;
                str = c0641fv2;
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("[Slim] ");
                sb3.append(C0520bi.this.f487a.format(new Date()));
                sb3.append(this.f490a);
                sb3.append(" Blob [");
                sb3.append(c0641fv.m1325a());
                sb3.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb3.append(c0641fv.m1324a());
                sb3.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb3.append(c0641fv.m1343e());
                str = "]";
                sb = sb3;
            }
            sb.append(str);
            AbstractC0407b.m74c(sb.toString());
        }

        @Override // com.xiaomi.push.InterfaceC0654gh
        /* renamed from: a */
        public void mo584a(AbstractC0666gt abstractC0666gt) {
            String str;
            StringBuilder sb;
            if (C0520bi.f482a) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("[Slim] ");
                sb2.append(C0520bi.this.f487a.format(new Date()));
                sb2.append(this.f490a);
                sb2.append(" PKT ");
                String mo1456a = abstractC0666gt.mo1456a();
                sb = sb2;
                str = mo1456a;
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("[Slim] ");
                sb3.append(C0520bi.this.f487a.format(new Date()));
                sb3.append(this.f490a);
                sb3.append(" PKT [");
                sb3.append(abstractC0666gt.m1492k());
                sb3.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb3.append(abstractC0666gt.m1491j());
                str = "]";
                sb = sb3;
            }
            sb.append(str);
            AbstractC0407b.m74c(sb.toString());
        }

        @Override // com.xiaomi.push.InterfaceC0662gp
        /* renamed from: a */
        public boolean mo585a(AbstractC0666gt abstractC0666gt) {
            return true;
        }
    }

    static {
        boolean z = true;
        if (C0880v.m2848a() != 1) {
            z = false;
        }
        f482a = z;
    }

    public C0520bi(AbstractC0649gc abstractC0649gc) {
        this.f484a = null;
        this.f484a = abstractC0649gc;
        m582a();
    }

    /* renamed from: a */
    private void m582a() {
        this.f483a = new a(true);
        this.f488b = new a(false);
        AbstractC0649gc abstractC0649gc = this.f484a;
        a aVar = this.f483a;
        abstractC0649gc.m1392a(aVar, aVar);
        AbstractC0649gc abstractC0649gc2 = this.f484a;
        a aVar2 = this.f488b;
        abstractC0649gc2.m1400b(aVar2, aVar2);
        this.f485a = new C0521bj(this);
    }
}
