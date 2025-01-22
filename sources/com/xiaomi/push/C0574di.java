package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* renamed from: com.xiaomi.push.di */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/di.class */
public class C0574di {
    /* renamed from: a */
    public static int m914a(Context context, int i) {
        int m1535a = C0681hh.m1535a(context);
        if (-1 == m1535a) {
            return -1;
        }
        return (i * (m1535a == 0 ? 13 : 11)) / 10;
    }

    /* renamed from: a */
    public static int m915a(EnumC0696hw enumC0696hw) {
        return C0630fk.m1240a(enumC0696hw.m1614a());
    }

    /* renamed from: a */
    public static int m916a(InterfaceC0744jq interfaceC0744jq, EnumC0696hw enumC0696hw) {
        int i;
        switch (C0575dj.f674a[enumC0696hw.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return C0630fk.m1240a(enumC0696hw.m1614a());
            case 11:
                int m1240a = C0630fk.m1240a(enumC0696hw.m1614a());
                i = m1240a;
                if (interfaceC0744jq != null) {
                    try {
                        if (interfaceC0744jq instanceof C0723iw) {
                            String str = ((C0723iw) interfaceC0744jq).f1925d;
                            i = m1240a;
                            if (!TextUtils.isEmpty(str)) {
                                i = m1240a;
                                if (C0630fk.m1241a(C0630fk.m1248a(str)) != -1) {
                                    i = C0630fk.m1241a(C0630fk.m1248a(str));
                                    break;
                                }
                            }
                        } else {
                            i = m1240a;
                            if (interfaceC0744jq instanceof C0732je) {
                                String str2 = ((C0732je) interfaceC0744jq).f2038d;
                                i = m1240a;
                                if (!TextUtils.isEmpty(str2)) {
                                    i = m1240a;
                                    if (C0630fk.m1241a(C0630fk.m1248a(str2)) != -1) {
                                        i = C0630fk.m1241a(C0630fk.m1248a(str2));
                                    }
                                    int i2 = i;
                                    if (EnumC0714in.UploadTinyData.equals(C0630fk.m1248a(str2))) {
                                        return -1;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        AbstractC0407b.m75d("PERF_ERROR : parse Notification type error");
                        return m1240a;
                    }
                }
                break;
            case 12:
                int m1240a2 = C0630fk.m1240a(enumC0696hw.m1614a());
                i = m1240a2;
                if (interfaceC0744jq != null) {
                    try {
                        if (interfaceC0744jq instanceof C0728ja) {
                            String m2003a = ((C0728ja) interfaceC0744jq).m2003a();
                            i = m1240a2;
                            if (!TextUtils.isEmpty(m2003a)) {
                                i = m1240a2;
                                if (EnumC0636fq.m1279a(m2003a) != -1) {
                                    i = EnumC0636fq.m1279a(m2003a);
                                    break;
                                }
                            }
                        } else {
                            i = m1240a2;
                            if (interfaceC0744jq instanceof C0726iz) {
                                String m1958a = ((C0726iz) interfaceC0744jq).m1958a();
                                i = m1240a2;
                                if (!TextUtils.isEmpty(m1958a)) {
                                    i = m1240a2;
                                    if (EnumC0636fq.m1279a(m1958a) != -1) {
                                        return EnumC0636fq.m1279a(m1958a);
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        AbstractC0407b.m75d("PERF_ERROR : parse Command type error");
                        i = m1240a2;
                        break;
                    }
                }
                break;
            default:
                return -1;
        }
        return i;
    }

    /* renamed from: a */
    public static void m917a(String str, Context context, int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        int m914a = m914a(context, i2);
        if (i != C0630fk.m1241a(EnumC0714in.UploadTinyData)) {
            AbstractC0407b.m70a("Perf_code  packetType  " + i + "  length " + m914a);
            C0631fl.m1256a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m1258a(str, i, 1L, (long) m914a);
        }
    }

    /* renamed from: a */
    public static void m918a(String str, Context context, C0729jb c0729jb, int i) {
        EnumC0696hw m2021a;
        if (context == null || c0729jb == null || (m2021a = c0729jb.m2021a()) == null) {
            return;
        }
        int m915a = m915a(m2021a);
        int i2 = i;
        if (i <= 0) {
            byte[] m2314a = C0743jp.m2314a(c0729jb);
            i2 = m2314a != null ? m2314a.length : 0;
        }
        m917a(str, context, m915a, i2);
    }

    /* renamed from: a */
    public static void m919a(String str, Context context, InterfaceC0744jq interfaceC0744jq, EnumC0696hw enumC0696hw, int i) {
        m917a(str, context, m916a(interfaceC0744jq, enumC0696hw), i);
    }

    /* renamed from: a */
    public static void m920a(String str, Context context, byte[] bArr) {
        if (context == null || bArr == null || bArr.length <= 0) {
            return;
        }
        C0729jb c0729jb = new C0729jb();
        try {
            C0743jp.m2313a(c0729jb, bArr);
            m918a(str, context, c0729jb, bArr.length);
        } catch (C0749jv e) {
            AbstractC0407b.m70a("fail to convert bytes to container");
        }
    }
}
