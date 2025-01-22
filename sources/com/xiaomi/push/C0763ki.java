package com.xiaomi.push;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.xiaomi.push.ki */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ki.class */
public class C0763ki extends AbstractC0766kl {

    /* renamed from: a */
    protected InputStream f2348a;

    /* renamed from: a */
    protected OutputStream f2349a;

    protected C0763ki() {
        this.f2348a = null;
        this.f2349a = null;
    }

    public C0763ki(OutputStream outputStream) {
        this.f2348a = null;
        this.f2349a = null;
        this.f2349a = outputStream;
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public int mo2386a(byte[] bArr, int i, int i2) {
        InputStream inputStream = this.f2348a;
        if (inputStream == null) {
            throw new C0767km(1, "Cannot read from null inputStream");
        }
        try {
            int read = inputStream.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new C0767km(4);
        } catch (IOException e) {
            throw new C0767km(0, e);
        }
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public void mo2387a(byte[] bArr, int i, int i2) {
        OutputStream outputStream = this.f2349a;
        if (outputStream == null) {
            throw new C0767km(1, "Cannot write to null outputStream");
        }
        try {
            outputStream.write(bArr, i, i2);
        } catch (IOException e) {
            throw new C0767km(0, e);
        }
    }
}
