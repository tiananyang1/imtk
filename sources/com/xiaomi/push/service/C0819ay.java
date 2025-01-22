package com.xiaomi.push.service;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.AbstractC0649gc;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.C0563cy;
import com.xiaomi.push.C0568dc;
import com.xiaomi.push.C0611es;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.C0678he;
import com.xiaomi.push.C0690hq;
import com.xiaomi.push.C0880v;
import com.xiaomi.push.InterfaceC0567db;
import com.xiaomi.push.service.C0830bi;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.ay */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ay.class */
public class C0819ay extends C0830bi.a implements C0568dc.a {

    /* renamed from: a */
    private long f2563a;

    /* renamed from: a */
    private XMPushService f2564a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.ay$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ay$a.class */
    public static class a implements C0568dc.b {
        a() {
        }

        @Override // com.xiaomi.push.C0568dc.b
        /* renamed from: a */
        public String mo899a(String str) {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.appendQueryParameter("sdkver", String.valueOf(38));
            buildUpon.appendQueryParameter("osver", String.valueOf(Build.VERSION.SDK_INT));
            buildUpon.appendQueryParameter("os", C0678he.m1528a(Build.MODEL + Constants.COLON_SEPARATOR + Build.VERSION.INCREMENTAL));
            buildUpon.appendQueryParameter("mi", String.valueOf(C0880v.m2848a()));
            String builder = buildUpon.toString();
            AbstractC0407b.m74c("fetch bucket from : " + builder);
            URL url = new URL(builder);
            int port = url.getPort() == -1 ? 80 : url.getPort();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String m476a = C0503as.m476a(C0880v.m2849a(), url);
                C0690hq.m1584a(url.getHost() + Constants.COLON_SEPARATOR + port, (int) (System.currentTimeMillis() - currentTimeMillis), null);
                return m476a;
            } catch (IOException e) {
                C0690hq.m1584a(url.getHost() + Constants.COLON_SEPARATOR + port, -1, e);
                throw e;
            }
        }
    }

    /* renamed from: com.xiaomi.push.service.ay$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ay$b.class */
    static class b extends C0568dc {
        protected b(Context context, InterfaceC0567db interfaceC0567db, C0568dc.b bVar, String str) {
            super(context, interfaceC0567db, bVar, str);
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
        @Override // com.xiaomi.push.C0568dc
        /* renamed from: a */
        protected String mo881a(ArrayList<String> arrayList, String str, String str2, boolean z) {
            throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
        }
    }

    C0819ay(XMPushService xMPushService) {
        this.f2564a = xMPushService;
    }

    /* renamed from: a */
    public static void m2611a(XMPushService xMPushService) {
        C0819ay c0819ay = new C0819ay(xMPushService);
        C0830bi.m2642a().m2654a(c0819ay);
        synchronized (C0568dc.class) {
            try {
                C0568dc.m876a(c0819ay);
                C0568dc.m875a(xMPushService, null, new a(), "0", "push", "2.2");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.xiaomi.push.C0568dc.a
    /* renamed from: a */
    public C0568dc mo898a(Context context, InterfaceC0567db interfaceC0567db, C0568dc.b bVar, String str) {
        return new b(context, interfaceC0567db, bVar, str);
    }

    @Override // com.xiaomi.push.service.C0830bi.a
    /* renamed from: a */
    public void mo2612a(C0611es.a aVar) {
    }

    @Override // com.xiaomi.push.service.C0830bi.a
    /* renamed from: a */
    public void mo1577a(C0612et.b bVar) {
        C0563cy m887b;
        boolean z;
        if (bVar.m1064b() && bVar.m1062a() && System.currentTimeMillis() - this.f2563a > 3600000) {
            AbstractC0407b.m70a("fetch bucket :" + bVar.m1062a());
            this.f2563a = System.currentTimeMillis();
            C0568dc m871a = C0568dc.m871a();
            m871a.m883a();
            m871a.m889b();
            AbstractC0649gc m2462a = this.f2564a.m2462a();
            if (m2462a == null || (m887b = m871a.m887b(m2462a.m1388a().m1420c())) == null) {
                return;
            }
            ArrayList<String> m831a = m887b.m831a();
            Iterator<String> it = m831a.iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                } else if (it.next().equals(m2462a.mo1389a())) {
                    z = false;
                    break;
                }
            }
            if (!z || m831a.isEmpty()) {
                return;
            }
            AbstractC0407b.m70a("bucket changed, force reconnect");
            this.f2564a.m2466a(0, (Exception) null);
            this.f2564a.m2474a(false);
        }
    }
}
