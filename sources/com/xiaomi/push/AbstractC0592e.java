package com.xiaomi.push;

import java.io.IOException;

/* renamed from: com.xiaomi.push.e */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/e.class */
public abstract class AbstractC0592e {
    /* renamed from: a */
    public abstract int mo959a();

    /* renamed from: a */
    public abstract AbstractC0592e mo960a(C0511b c0511b);

    /* renamed from: a */
    public AbstractC0592e m961a(byte[] bArr) {
        return m962a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public AbstractC0592e m962a(byte[] bArr, int i, int i2) {
        try {
            C0511b m535a = C0511b.m535a(bArr, i, i2);
            mo960a(m535a);
            m535a.m545a(0);
            return this;
        } catch (C0565d e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    /* renamed from: a */
    public abstract void mo963a(C0538c c0538c);

    /* renamed from: a */
    public void m964a(byte[] bArr, int i, int i2) {
        try {
            C0538c m672a = C0538c.m672a(bArr, i, i2);
            mo963a(m672a);
            m672a.m698b();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m965a(C0511b c0511b, int i) {
        return c0511b.m548a(i);
    }

    /* renamed from: a */
    public byte[] m966a() {
        byte[] bArr = new byte[mo967b()];
        m964a(bArr, 0, bArr.length);
        return bArr;
    }

    /* renamed from: b */
    public abstract int mo967b();
}
