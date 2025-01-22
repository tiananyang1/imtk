package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.C0493ai;

/* renamed from: com.xiaomi.push.ei */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ei.class */
public abstract class AbstractC0601ei extends C0493ai.a {

    /* renamed from: a */
    protected int f732a;

    /* renamed from: a */
    protected Context f733a;

    public AbstractC0601ei(Context context, int i) {
        this.f732a = i;
        this.f733a = context;
    }

    /* renamed from: a */
    public static void m979a(Context context, C0707ig c0707ig) {
        InterfaceC0584ds m947a = C0585dt.m946a().m947a();
        String mo371a = m947a == null ? "" : m947a.mo371a();
        if (TextUtils.isEmpty(mo371a) || TextUtils.isEmpty(c0707ig.m1679a())) {
            return;
        }
        m980a(context, c0707ig, mo371a);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0120 A[Catch: all -> 0x0139, TRY_ENTER, TRY_LEAVE, TryCatch #9 {, blocks: (B:22:0x0085, B:25:0x008e, B:29:0x0094, B:31:0x0098, B:33:0x011a, B:55:0x0120, B:58:0x0129, B:62:0x012f, B:63:0x0138, B:41:0x0104, B:44:0x010d, B:48:0x0112), top: B:10:0x0024 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m980a(android.content.Context r9, com.xiaomi.push.C0707ig r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.AbstractC0601ei.m980a(android.content.Context, com.xiaomi.push.ig, java.lang.String):void");
    }

    /* renamed from: a */
    public abstract EnumC0699hz mo974a();

    /* renamed from: a */
    public abstract String mo975a();

    /* renamed from: a */
    protected boolean mo976a() {
        return true;
    }

    /* renamed from: b */
    protected boolean mo981b() {
        return C0491ag.m433a(this.f733a, String.valueOf(mo974a()), this.f732a);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (mo981b()) {
            InterfaceC0584ds m947a = C0585dt.m946a().m947a();
            String mo371a = m947a == null ? "" : m947a.mo371a();
            if (!TextUtils.isEmpty(mo371a) && mo976a()) {
                String mo975a = mo975a();
                if (TextUtils.isEmpty(mo975a)) {
                    return;
                }
                C0707ig c0707ig = new C0707ig();
                c0707ig.m1678a(mo975a);
                c0707ig.m1676a(System.currentTimeMillis());
                c0707ig.m1677a(mo974a());
                m980a(this.f733a, c0707ig, mo371a);
            }
        }
    }
}
