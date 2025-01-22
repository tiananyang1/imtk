package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0686hm;
import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.XMPushService;
import java.util.Hashtable;

/* renamed from: com.xiaomi.push.hq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hq.class */
public class C0690hq {

    /* renamed from: a */
    private static final int f1405a = EnumC0637fr.PING_RTT.m1281a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.hq$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hq$a.class */
    public static class a {

        /* renamed from: a */
        static Hashtable<Integer, Long> f1406a = new Hashtable<>();
    }

    /* renamed from: a */
    public static void m1578a() {
        m1580a(0, f1405a);
    }

    /* renamed from: a */
    public static void m1579a(int i) {
        C0638fs m1570a = C0688ho.m1568a().m1570a();
        m1570a.m1284a(EnumC0637fr.CHANNEL_STATS_COUNTER.m1281a());
        m1570a.m1296c(i);
        C0688ho.m1568a().m1573a(m1570a);
    }

    /* renamed from: a */
    public static void m1580a(int i, int i2) {
        synchronized (C0690hq.class) {
            try {
                if (i2 < 16777215) {
                    a.f1406a.put(Integer.valueOf((i << 24) | i2), Long.valueOf(System.currentTimeMillis()));
                } else {
                    AbstractC0407b.m75d("stats key should less than 16777215");
                }
            } finally {
            }
        }
    }

    /* renamed from: a */
    public static void m1581a(int i, int i2, int i3, String str, int i4) {
        C0638fs m1570a = C0688ho.m1568a().m1570a();
        m1570a.m1283a((byte) i);
        m1570a.m1284a(i2);
        m1570a.m1291b(i3);
        m1570a.m1292b(str);
        m1570a.m1296c(i4);
        C0688ho.m1568a().m1573a(m1570a);
    }

    /* renamed from: a */
    public static void m1582a(int i, int i2, String str, int i3) {
        synchronized (C0690hq.class) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                int i4 = (i << 24) | i2;
                if (a.f1406a.containsKey(Integer.valueOf(i4))) {
                    C0638fs m1570a = C0688ho.m1568a().m1570a();
                    m1570a.m1284a(i2);
                    m1570a.m1291b((int) (currentTimeMillis - a.f1406a.get(Integer.valueOf(i4)).longValue()));
                    m1570a.m1292b(str);
                    if (i3 > -1) {
                        m1570a.m1296c(i3);
                    }
                    C0688ho.m1568a().m1573a(m1570a);
                    a.f1406a.remove(Integer.valueOf(i2));
                } else {
                    AbstractC0407b.m75d("stats key not found");
                }
            } finally {
            }
        }
    }

    /* renamed from: a */
    public static void m1583a(XMPushService xMPushService, C0814at.b bVar) {
        new C0683hj(xMPushService, bVar).m1554a();
    }

    /* renamed from: a */
    public static void m1584a(String str, int i, Exception exc) {
        C0638fs m1570a = C0688ho.m1568a().m1570a();
        if (i > 0) {
            m1570a.m1284a(EnumC0637fr.GSLB_REQUEST_SUCCESS.m1281a());
            m1570a.m1292b(str);
            m1570a.m1291b(i);
            C0688ho.m1568a().m1573a(m1570a);
            return;
        }
        try {
            C0686hm.a m1556a = C0686hm.m1556a(exc);
            m1570a.m1284a(m1556a.f1384a.m1281a());
            m1570a.m1297c(m1556a.f1385a);
            m1570a.m1292b(str);
            C0688ho.m1568a().m1573a(m1570a);
        } catch (NullPointerException e) {
        }
    }

    /* renamed from: a */
    public static void m1585a(String str, Exception exc) {
        try {
            C0686hm.a m1558b = C0686hm.m1558b(exc);
            C0638fs m1570a = C0688ho.m1568a().m1570a();
            m1570a.m1284a(m1558b.f1384a.m1281a());
            m1570a.m1297c(m1558b.f1385a);
            m1570a.m1292b(str);
            C0688ho.m1568a().m1573a(m1570a);
        } catch (NullPointerException e) {
        }
    }

    /* renamed from: a */
    public static byte[] m1586a() {
        C0639ft m1571a = C0688ho.m1568a().m1571a();
        if (m1571a != null) {
            return C0743jp.m2314a(m1571a);
        }
        return null;
    }

    /* renamed from: b */
    public static void m1587b() {
        m1582a(0, f1405a, null, -1);
    }

    /* renamed from: b */
    public static void m1588b(String str, Exception exc) {
        try {
            C0686hm.a m1560d = C0686hm.m1560d(exc);
            C0638fs m1570a = C0688ho.m1568a().m1570a();
            m1570a.m1284a(m1560d.f1384a.m1281a());
            m1570a.m1297c(m1560d.f1385a);
            m1570a.m1292b(str);
            C0688ho.m1568a().m1573a(m1570a);
        } catch (NullPointerException e) {
        }
    }
}
