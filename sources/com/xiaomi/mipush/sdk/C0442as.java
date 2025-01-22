package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0506av;
import com.xiaomi.push.C0700i;
import com.xiaomi.push.C0720it;
import com.xiaomi.push.C0722iv;
import com.xiaomi.push.C0723iw;
import com.xiaomi.push.C0728ja;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0734jg;
import com.xiaomi.push.C0735jh;
import com.xiaomi.push.C0736ji;
import com.xiaomi.push.C0738jk;
import com.xiaomi.push.C0740jm;
import com.xiaomi.push.C0742jo;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.InterfaceC0744jq;
import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.mipush.sdk.as */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/as.class */
public class C0442as {
    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static <T extends InterfaceC0744jq<T, ?>> C0729jb m194a(Context context, T t, EnumC0696hw enumC0696hw) {
        return m195a(context, t, enumC0696hw, !enumC0696hw.equals(EnumC0696hw.Registration), context.getPackageName(), C0461d.m289a(context).m293a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static <T extends InterfaceC0744jq<T, ?>> C0729jb m195a(Context context, T t, EnumC0696hw enumC0696hw, boolean z, String str, String str2) {
        String str3;
        byte[] m2314a = C0743jp.m2314a(t);
        if (m2314a != null) {
            C0729jb c0729jb = new C0729jb();
            byte[] bArr = m2314a;
            if (z) {
                String m310d = C0461d.m289a(context).m310d();
                if (TextUtils.isEmpty(m310d)) {
                    str3 = "regSecret is empty, return null";
                } else {
                    try {
                        bArr = C0700i.m1629b(C0506av.m510a(m310d), m2314a);
                    } catch (Exception e) {
                        AbstractC0407b.m75d("encryption error. ");
                        bArr = m2314a;
                    }
                }
            }
            C0720it c0720it = new C0720it();
            c0720it.f1850a = 5L;
            c0720it.f1851a = "fakeid";
            c0729jb.m2025a(c0720it);
            c0729jb.m2027a(ByteBuffer.wrap(bArr));
            c0729jb.m2023a(enumC0696hw);
            c0729jb.m2036b(true);
            c0729jb.m2035b(str);
            c0729jb.m2028a(z);
            c0729jb.m2026a(str2);
            return c0729jb;
        }
        str3 = "invoke convertThriftObjectToBytes method, return null.";
        AbstractC0407b.m70a(str3);
        return null;
    }

    /* renamed from: a */
    public static InterfaceC0744jq m196a(Context context, C0729jb c0729jb) {
        byte[] m2034a;
        if (c0729jb.m2039b()) {
            try {
                m2034a = C0700i.m1628a(C0506av.m510a(C0461d.m289a(context).m310d()), c0729jb.m2034a());
            } catch (Exception e) {
                throw new C0477t("the aes decrypt failed.", e);
            }
        } else {
            m2034a = c0729jb.m2034a();
        }
        InterfaceC0744jq m197a = m197a(c0729jb.m2021a(), c0729jb.f2005b);
        if (m197a != null) {
            C0743jp.m2313a(m197a, m2034a);
        }
        return m197a;
    }

    /* renamed from: a */
    private static InterfaceC0744jq m197a(EnumC0696hw enumC0696hw, boolean z) {
        switch (C0443at.f298a[enumC0696hw.ordinal()]) {
            case 1:
                return new C0734jg();
            case 2:
                return new C0740jm();
            case 3:
                return new C0738jk();
            case 4:
                return new C0742jo();
            case 5:
                return new C0736ji();
            case 6:
                return new C0722iv();
            case 7:
                return new C0728ja();
            case 8:
                return new C0735jh();
            case 9:
                if (z) {
                    return new C0732je();
                }
                C0723iw c0723iw = new C0723iw();
                c0723iw.m1929a(true);
                return c0723iw;
            case 10:
                return new C0728ja();
            default:
                return null;
        }
    }
}
