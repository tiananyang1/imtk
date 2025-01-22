package com.xiaomi.push;

import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.push.fz */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fz.class */
public final class C0645fz extends C0641fv {
    public C0645fz() {
        m1331a("PING", (String) null);
        m1330a("0");
        m1328a(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaomi.push.C0641fv
    /* renamed from: a */
    public ByteBuffer mo1326a(ByteBuffer byteBuffer) {
        return m1324a().length == 0 ? byteBuffer : super.mo1326a(byteBuffer);
    }

    @Override // com.xiaomi.push.C0641fv
    /* renamed from: c */
    public int mo1340c() {
        if (m1324a().length == 0) {
            return 0;
        }
        return super.mo1340c();
    }
}
