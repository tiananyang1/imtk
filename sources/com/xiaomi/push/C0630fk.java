package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.manager.ClientReportClient;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.C0833bl;
import com.xiaomi.push.service.C0834bm;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.xiaomi.push.fk */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fk.class */
public class C0630fk {

    /* renamed from: a */
    private static a f909a;

    /* renamed from: a */
    private static Map<String, EnumC0714in> f910a;

    /* renamed from: com.xiaomi.push.fk$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fk$a.class */
    public interface a {
        /* renamed from: a */
        void mo183a(Context context, C0702ib c0702ib);
    }

    /* renamed from: a */
    public static int m1240a(int i) {
        if (i > 0) {
            return i + PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT;
        }
        return -1;
    }

    /* renamed from: a */
    public static int m1241a(Enum r3) {
        if (r3 == null) {
            return -1;
        }
        if (r3 instanceof EnumC0696hw) {
            return r3.ordinal() + 1001;
        }
        if (r3 instanceof EnumC0714in) {
            return r3.ordinal() + 2001;
        }
        if (r3 instanceof EnumC0636fq) {
            return r3.ordinal() + 3001;
        }
        return -1;
    }

    /* renamed from: a */
    public static Config m1242a(Context context) {
        boolean m2563a = C0809ao.m2557a(context).m2563a(EnumC0703ic.PerfUploadSwitch.m1669a(), false);
        boolean m2563a2 = C0809ao.m2557a(context).m2563a(EnumC0703ic.EventUploadSwitch.m1669a(), false);
        return Config.getBuilder().setEventUploadSwitchOpen(m2563a2).setEventUploadFrequency(C0809ao.m2557a(context).m2561a(EnumC0703ic.EventUploadFrequency.m1669a(), 86400)).setPerfUploadSwitchOpen(m2563a).setPerfUploadFrequency(C0809ao.m2557a(context).m2561a(EnumC0703ic.PerfUploadFrequency.m1669a(), 86400)).build(context);
    }

    /* renamed from: a */
    public static EventClientReport m1243a(Context context, String str, String str2, int i, long j, String str3) {
        EventClientReport m1244a = m1244a(str);
        m1244a.eventId = str2;
        m1244a.eventType = i;
        m1244a.eventTime = j;
        m1244a.eventContent = str3;
        return m1244a;
    }

    /* renamed from: a */
    public static EventClientReport m1244a(String str) {
        EventClientReport eventClientReport = new EventClientReport();
        eventClientReport.production = PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT;
        eventClientReport.reportType = 1001;
        eventClientReport.clientInterfaceId = str;
        return eventClientReport;
    }

    /* renamed from: a */
    public static PerfClientReport m1245a() {
        PerfClientReport perfClientReport = new PerfClientReport();
        perfClientReport.production = PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT;
        perfClientReport.reportType = PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT;
        perfClientReport.clientInterfaceId = "P100000";
        return perfClientReport;
    }

    /* renamed from: a */
    public static PerfClientReport m1246a(Context context, int i, long j, long j2) {
        PerfClientReport m1245a = m1245a();
        m1245a.code = i;
        m1245a.perfCounts = j;
        m1245a.perfLatencies = j2;
        return m1245a;
    }

    /* renamed from: a */
    public static C0702ib m1247a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        C0702ib c0702ib = new C0702ib();
        c0702ib.m1655d("category_client_report_data");
        c0702ib.m1639a("push_sdk_channel");
        c0702ib.m1638a(1L);
        c0702ib.m1647b(str);
        c0702ib.m1640a(true);
        c0702ib.m1646b(System.currentTimeMillis());
        c0702ib.m1663g(context.getPackageName());
        c0702ib.m1658e("com.xiaomi.xmsf");
        c0702ib.m1661f(C0833bl.m2661a());
        c0702ib.m1651c("quality_support");
        return c0702ib;
    }

    /* renamed from: a */
    public static EnumC0714in m1248a(String str) {
        if (f910a == null) {
            synchronized (EnumC0714in.class) {
                try {
                    if (f910a == null) {
                        f910a = new HashMap();
                        EnumC0714in[] values = EnumC0714in.values();
                        int length = values.length;
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= length) {
                                break;
                            }
                            EnumC0714in enumC0714in = values[i2];
                            f910a.put(enumC0714in.f1752a.toLowerCase(), enumC0714in);
                            i = i2 + 1;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        EnumC0714in enumC0714in2 = f910a.get(str.toLowerCase());
        return enumC0714in2 != null ? enumC0714in2 : EnumC0714in.Invalid;
    }

    /* renamed from: a */
    public static String m1249a(int i) {
        return i == 1000 ? "E100000" : i == 3000 ? "E100002" : i == 2000 ? "E100001" : "";
    }

    /* renamed from: a */
    public static void m1250a(Context context) {
        ClientReportClient.updateConfig(context, m1242a(context));
    }

    /* renamed from: a */
    public static void m1251a(Context context, Config config) {
        ClientReportClient.init(context, config, new C0628fi(context), new C0629fj(context));
    }

    /* renamed from: a */
    private static void m1252a(Context context, C0702ib c0702ib) {
        if (m1255a(StubApp.getOrigApplicationContext(context.getApplicationContext()))) {
            C0834bm.m2665a(StubApp.getOrigApplicationContext(context.getApplicationContext()), c0702ib);
            return;
        }
        a aVar = f909a;
        if (aVar != null) {
            aVar.mo183a(context, c0702ib);
        }
    }

    /* renamed from: a */
    public static void m1253a(Context context, List<String> list) {
        if (list == null) {
            return;
        }
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                C0702ib m1247a = m1247a(context, it.next());
                if (C0833bl.m2664a(m1247a, false)) {
                    AbstractC0407b.m74c(m1247a.m1656d() + "is not valid...");
                } else {
                    AbstractC0407b.m74c("send event/perf data item id:" + m1247a.m1656d());
                    m1252a(context, m1247a);
                }
            }
        } catch (Throwable th) {
            AbstractC0407b.m75d(th.getMessage());
        }
    }

    /* renamed from: a */
    public static void m1254a(a aVar) {
        f909a = aVar;
    }

    /* renamed from: a */
    public static boolean m1255a(Context context) {
        return (context == null || TextUtils.isEmpty(context.getPackageName()) || !"com.xiaomi.xmsf".equals(context.getPackageName())) ? false : true;
    }
}
