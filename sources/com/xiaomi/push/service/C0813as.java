package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.AbstractC0666gt;
import com.xiaomi.push.C0563cy;
import com.xiaomi.push.C0568dc;
import com.xiaomi.push.C0577dl;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.C0641fv;
import com.xiaomi.push.C0650gd;
import com.xiaomi.push.C0663gq;
import com.xiaomi.push.C0664gr;
import com.xiaomi.push.C0665gs;
import com.xiaomi.push.C0681hh;
import com.xiaomi.push.C0690hq;
import com.xiaomi.push.EnumC0637fr;
import com.xiaomi.push.service.C0814at;
import java.util.Date;

/* renamed from: com.xiaomi.push.service.as */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/as.class */
public class C0813as {

    /* renamed from: a */
    private XMPushService f2486a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0813as(XMPushService xMPushService) {
        this.f2486a = xMPushService;
    }

    /* renamed from: a */
    private void m2572a(C0663gq c0663gq) {
        String m1452c = c0663gq.m1452c();
        if (TextUtils.isEmpty(m1452c)) {
            return;
        }
        String[] split = m1452c.split(";");
        C0563cy m880a = C0568dc.m871a().m880a(C0650gd.m1411a(), false);
        if (m880a == null || split.length <= 0) {
            return;
        }
        m880a.m843a(split);
        this.f2486a.m2466a(20, (Exception) null);
        this.f2486a.m2474a(true);
    }

    /* renamed from: b */
    private void m2573b(AbstractC0666gt abstractC0666gt) {
        C0814at.b m2581a;
        String m1494l = abstractC0666gt.m1494l();
        String m1492k = abstractC0666gt.m1492k();
        if (TextUtils.isEmpty(m1494l) || TextUtils.isEmpty(m1492k) || (m2581a = C0814at.m2578a().m2581a(m1492k, m1494l)) == null) {
            return;
        }
        C0681hh.m1544a(this.f2486a, m2581a.f2499a, C0681hh.m1536a(abstractC0666gt.mo1456a()), true, true, System.currentTimeMillis());
    }

    /* renamed from: c */
    private void m2574c(C0641fv c0641fv) {
        C0814at.b m2581a;
        String m1345g = c0641fv.m1345g();
        String num = Integer.toString(c0641fv.m1324a());
        if (TextUtils.isEmpty(m1345g) || TextUtils.isEmpty(num) || (m2581a = C0814at.m2578a().m2581a(num, m1345g)) == null) {
            return;
        }
        C0681hh.m1544a(this.f2486a, m2581a.f2499a, c0641fv.mo1340c(), true, true, System.currentTimeMillis());
    }

    /* renamed from: a */
    public void m2575a(C0641fv c0641fv) {
        if (5 != c0641fv.m1324a()) {
            m2574c(c0641fv);
        }
        try {
            m2577b(c0641fv);
        } catch (Exception e) {
            AbstractC0407b.m71a("handle Blob chid = " + c0641fv.m1324a() + " cmd = " + c0641fv.m1325a() + " packetid = " + c0641fv.m1343e() + " failure ", e);
        }
    }

    /* renamed from: a */
    public void m2576a(AbstractC0666gt abstractC0666gt) {
        if (!"5".equals(abstractC0666gt.m1492k())) {
            m2573b(abstractC0666gt);
        }
        String m1492k = abstractC0666gt.m1492k();
        String str = m1492k;
        if (TextUtils.isEmpty(m1492k)) {
            str = "1";
            abstractC0666gt.m1495l("1");
        }
        if (str.equals("0")) {
            AbstractC0407b.m70a("Received wrong packet with chid = 0 : " + abstractC0666gt.mo1456a());
        }
        if (abstractC0666gt instanceof C0664gr) {
            C0663gq m1483a = abstractC0666gt.m1483a("kick");
            if (m1483a != null) {
                String m1494l = abstractC0666gt.m1494l();
                String m1449a = m1483a.m1449a("type");
                String m1449a2 = m1483a.m1449a("reason");
                AbstractC0407b.m70a("kicked by server, chid=" + str + " res=" + C0814at.b.m2596a(m1494l) + " type=" + m1449a + " reason=" + m1449a2);
                if (!"wait".equals(m1449a)) {
                    this.f2486a.m2472a(str, m1494l, 3, m1449a2, m1449a);
                    C0814at.m2578a().m2591a(str, m1494l);
                    return;
                }
                C0814at.b m2581a = C0814at.m2578a().m2581a(str, m1494l);
                if (m2581a != null) {
                    this.f2486a.m2471a(m2581a);
                    m2581a.m2607a(C0814at.c.unbind, 3, 0, m1449a2, m1449a);
                    return;
                }
                return;
            }
        } else if (abstractC0666gt instanceof C0665gs) {
            C0665gs c0665gs = (C0665gs) abstractC0666gt;
            if ("redir".equals(c0665gs.m1464b())) {
                C0663gq a = c0665gs.m1461a("hosts");
                if (a != null) {
                    m2572a(a);
                    return;
                }
                return;
            }
        }
        this.f2486a.m2479b().m2684a(this.f2486a, str, abstractC0666gt);
    }

    /* renamed from: b */
    public void m2577b(C0641fv c0641fv) {
        StringBuilder sb;
        String m1156a;
        String str;
        C0814at.c cVar;
        int i;
        String m1325a = c0641fv.m1325a();
        if (c0641fv.m1324a() != 0) {
            String num = Integer.toString(c0641fv.m1324a());
            if (!"SECMSG".equals(c0641fv.m1325a())) {
                if (!"BIND".equals(m1325a)) {
                    if ("KICK".equals(m1325a)) {
                        C0612et.g m1143a = C0612et.g.m1143a(c0641fv.m1335a());
                        String m1345g = c0641fv.m1345g();
                        String m1145a = m1143a.m1145a();
                        String m1148b = m1143a.m1148b();
                        AbstractC0407b.m70a("kicked by server, chid=" + num + " res= " + C0814at.b.m2596a(m1345g) + " type=" + m1145a + " reason=" + m1148b);
                        if (!"wait".equals(m1145a)) {
                            this.f2486a.m2472a(num, m1345g, 3, m1148b, m1145a);
                            C0814at.m2578a().m2591a(num, m1345g);
                            return;
                        }
                        C0814at.b m2581a = C0814at.m2578a().m2581a(num, m1345g);
                        if (m2581a != null) {
                            this.f2486a.m2471a(m2581a);
                            m2581a.m2607a(C0814at.c.unbind, 3, 0, m1148b, m1145a);
                            return;
                        }
                        return;
                    }
                    return;
                }
                C0612et.d m1090a = C0612et.d.m1090a(c0641fv.m1335a());
                String m1345g2 = c0641fv.m1345g();
                C0814at.b m2581a2 = C0814at.m2578a().m2581a(num, m1345g2);
                if (m2581a2 == null) {
                    return;
                }
                if (m1090a.m1094a()) {
                    AbstractC0407b.m70a("SMACK: channel bind succeeded, chid=" + c0641fv.m1324a());
                    m2581a2.m2607a(C0814at.c.binded, 1, 0, (String) null, (String) null);
                    return;
                }
                String m1093a = m1090a.m1093a();
                if ("auth".equals(m1093a)) {
                    if ("invalid-sig".equals(m1090a.m1096b())) {
                        AbstractC0407b.m70a("SMACK: bind error invalid-sig token = " + m2581a2.f2505c + " sec = " + m2581a2.f2510h);
                        C0690hq.m1581a(0, EnumC0637fr.BIND_INVALID_SIG.m1281a(), 1, null, 0);
                    }
                    cVar = C0814at.c.unbind;
                    i = 5;
                } else {
                    if (!"cancel".equals(m1093a)) {
                        if ("wait".equals(m1093a)) {
                            this.f2486a.m2471a(m2581a2);
                            m2581a2.m2607a(C0814at.c.unbind, 1, 7, m1090a.m1096b(), m1093a);
                        }
                        str = "SMACK: channel bind failed, chid=" + num + " reason=" + m1090a.m1096b();
                        AbstractC0407b.m70a(str);
                    }
                    cVar = C0814at.c.unbind;
                    i = 7;
                }
                m2581a2.m2607a(cVar, 1, i, m1090a.m1096b(), m1093a);
                C0814at.m2578a().m2591a(num, m1345g2);
                str = "SMACK: channel bind failed, chid=" + num + " reason=" + m1090a.m1096b();
                AbstractC0407b.m70a(str);
            }
            if (!c0641fv.m1334a()) {
                this.f2486a.m2479b().m2683a(this.f2486a, num, c0641fv);
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Recv SECMSG errCode = ");
            sb2.append(c0641fv.m1337b());
            sb2.append(" errStr = ");
            m1156a = c0641fv.m1341c();
            sb = sb2;
        } else {
            if ("PING".equals(m1325a)) {
                byte[] m1335a = c0641fv.m1335a();
                if (m1335a != null && m1335a.length > 0) {
                    C0612et.j m1164a = C0612et.j.m1164a(m1335a);
                    if (m1164a.m1170b()) {
                        C0830bi.m2642a().m2653a(m1164a.m1166a());
                    }
                }
                if (!"com.xiaomi.xmsf".equals(this.f2486a.getPackageName())) {
                    this.f2486a.m2464a();
                }
                if ("1".equals(c0641fv.m1343e())) {
                    AbstractC0407b.m70a("received a server ping");
                } else {
                    C0690hq.m1587b();
                }
                this.f2486a.m2480b();
                return;
            }
            if ("SYNC".equals(m1325a)) {
                if ("CONF".equals(c0641fv.m1338b())) {
                    C0830bi.m2642a().m2653a(C0612et.b.m1059a(c0641fv.m1335a()));
                    return;
                }
                if (TextUtils.equals("U", c0641fv.m1338b())) {
                    C0612et.k m1171a = C0612et.k.m1171a(c0641fv.m1335a());
                    C0577dl.m928a(this.f2486a).m937a(m1171a.m1177a(), m1171a.m1182b(), new Date(m1171a.m1172a()), new Date(m1171a.m1179b()), m1171a.m1184c() * 1024, m1171a.m1187e());
                    C0641fv c0641fv2 = new C0641fv();
                    c0641fv2.m1328a(0);
                    c0641fv2.m1331a(c0641fv.m1325a(), "UCA");
                    c0641fv2.m1330a(c0641fv.m1343e());
                    XMPushService xMPushService = this.f2486a;
                    xMPushService.m2468a(new C0826be(xMPushService, c0641fv2));
                    return;
                }
                if (!TextUtils.equals("P", c0641fv.m1338b())) {
                    return;
                }
                C0612et.i m1160a = C0612et.i.m1160a(c0641fv.m1335a());
                C0641fv c0641fv3 = new C0641fv();
                c0641fv3.m1328a(0);
                c0641fv3.m1331a(c0641fv.m1325a(), "PCA");
                c0641fv3.m1330a(c0641fv.m1343e());
                C0612et.i iVar = new C0612et.i();
                if (m1160a.m1163a()) {
                    iVar.m1162a(m1160a.m1161a());
                }
                c0641fv3.m1333a(iVar.mo959a(), (String) null);
                XMPushService xMPushService2 = this.f2486a;
                xMPushService2.m2468a(new C0826be(xMPushService2, c0641fv3));
                StringBuilder sb3 = new StringBuilder();
                sb3.append("ACK msgP: id = ");
                String m1343e = c0641fv.m1343e();
                sb = sb3;
                m1156a = m1343e;
            } else {
                if (!"NOTIFY".equals(c0641fv.m1325a())) {
                    return;
                }
                C0612et.h m1153a = C0612et.h.m1153a(c0641fv.m1335a());
                sb = new StringBuilder();
                sb.append("notify by server err = ");
                sb.append(m1153a.m1159c());
                sb.append(" desc = ");
                m1156a = m1153a.m1156a();
            }
        }
        sb.append(m1156a);
        str = sb.toString();
        AbstractC0407b.m70a(str);
    }
}
