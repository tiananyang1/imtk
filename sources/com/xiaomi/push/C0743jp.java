package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0751jx;
import com.xiaomi.push.C0762kh;
import com.xiaomi.push.service.C0836bo;

/* renamed from: com.xiaomi.push.jp */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jp.class */
public class C0743jp {
    /* renamed from: a */
    public static short m2311a(Context context, C0729jb c0729jb) {
        int m1368a = C0646g.m1357a(context, c0729jb.f2004b).m1368a();
        int i = 0;
        int i2 = C0492ah.m436b(context) ? 4 : 0;
        int i3 = C0492ah.m435a(context) ? 8 : 0;
        if (C0836bo.m2673a(context, c0729jb)) {
            i = 16;
        }
        return (short) (m1368a + 0 + i2 + i3 + i);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    /* renamed from: a */
    public static short m2312a(boolean z, boolean z2, boolean z3) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    /* renamed from: a */
    public static <T extends InterfaceC0744jq<T, ?>> void m2313a(T t, byte[] bArr) {
        if (bArr == null) {
            throw new C0749jv("the message byte is empty.");
        }
        new C0748ju(new C0762kh.a(true, true, bArr.length)).m2335a(t, bArr);
    }

    /* renamed from: a */
    public static <T extends InterfaceC0744jq<T, ?>> byte[] m2314a(T t) {
        if (t == null) {
            return null;
        }
        try {
            return new C0750jw(new C0751jx.a()).m2336a(t);
        } catch (C0749jv e) {
            AbstractC0407b.m71a("convertThriftObjectToBytes catch TException.", e);
            return null;
        }
    }
}
