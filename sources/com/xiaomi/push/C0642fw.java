package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.service.C0814at;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.fw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fw.class */
public class C0642fw {

    /* renamed from: a */
    private C0647ga f1029a;

    /* renamed from: a */
    private InputStream f1030a;

    /* renamed from: a */
    private volatile boolean f1033a;

    /* renamed from: a */
    private byte[] f1034a;

    /* renamed from: a */
    private ByteBuffer f1031a = ByteBuffer.allocate(2048);

    /* renamed from: b */
    private ByteBuffer f1035b = ByteBuffer.allocate(4);

    /* renamed from: a */
    private Adler32 f1032a = new Adler32();

    /* renamed from: a */
    private C0644fy f1028a = new C0644fy();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0642fw(InputStream inputStream, C0647ga c0647ga) {
        this.f1030a = new BufferedInputStream(inputStream);
        this.f1029a = c0647ga;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0134  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.nio.ByteBuffer m1346a() {
        /*
            Method dump skipped, instructions count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0642fw.m1346a():java.nio.ByteBuffer");
    }

    /* renamed from: a */
    private void m1347a(ByteBuffer byteBuffer, int i) {
        int i2;
        int i3;
        int position = byteBuffer.position();
        do {
            int read = this.f1030a.read(byteBuffer.array(), position, i);
            if (read == -1) {
                throw new EOFException();
            }
            i2 = i - read;
            i3 = position + read;
            position = i3;
            i = i2;
        } while (i2 > 0);
        byteBuffer.position(i3);
    }

    /* renamed from: c */
    private void m1348c() {
        String str;
        StringBuilder sb;
        boolean z = false;
        this.f1033a = false;
        C0641fv m1349a = m1349a();
        if ("CONN".equals(m1349a.m1325a())) {
            C0612et.f m1133a = C0612et.f.m1133a(m1349a.m1335a());
            z = false;
            if (m1133a.m1138a()) {
                this.f1029a.m1431a(m1133a.m1137a());
                z = true;
            }
            if (m1133a.m1142c()) {
                C0612et.b m1134a = m1133a.m1134a();
                C0641fv c0641fv = new C0641fv();
                c0641fv.m1331a("SYNC", "CONF");
                c0641fv.m1333a(m1134a.mo959a(), (String) null);
                this.f1029a.m1374a(c0641fv);
            }
            AbstractC0407b.m70a("[Slim] CONN: host = " + m1133a.m1140b());
        }
        if (!z) {
            AbstractC0407b.m70a("[Slim] Invalid CONN");
            throw new IOException("Invalid Connection");
        }
        this.f1034a = this.f1029a.m1381a();
        while (!this.f1033a) {
            C0641fv m1349a2 = m1349a();
            this.f1029a.m1405d();
            short m1327a = m1349a2.m1327a();
            if (m1327a != 1) {
                if (m1327a != 2) {
                    if (m1327a != 3) {
                        str = "[Slim] unknow blob type " + ((int) m1349a2.m1327a());
                        AbstractC0407b.m70a(str);
                    } else {
                        try {
                            this.f1029a.m1383b(this.f1028a.m1355a(m1349a2.m1335a(), this.f1029a));
                        } catch (Exception e) {
                            e = e;
                            sb = new StringBuilder();
                            sb.append("[Slim] Parse packet from Blob chid=");
                            sb.append(m1349a2.m1324a());
                            sb.append("; Id=");
                            sb.append(m1349a2.m1343e());
                            sb.append(" failure:");
                            sb.append(e.getMessage());
                            str = sb.toString();
                            AbstractC0407b.m70a(str);
                        }
                    }
                } else if ("SECMSG".equals(m1349a2.m1325a()) && ((m1349a2.m1324a() == 2 || m1349a2.m1324a() == 3) && TextUtils.isEmpty(m1349a2.m1338b()))) {
                    try {
                        this.f1029a.m1383b(this.f1028a.m1355a(m1349a2.m1336a(C0814at.m2578a().m2581a(Integer.valueOf(m1349a2.m1324a()).toString(), m1349a2.m1345g()).f2510h), this.f1029a));
                    } catch (Exception e2) {
                        e = e2;
                        sb = new StringBuilder();
                        sb.append("[Slim] Parse packet from Blob chid=");
                        sb.append(m1349a2.m1324a());
                        sb.append("; Id=");
                        sb.append(m1349a2.m1343e());
                        sb.append(" failure:");
                        sb.append(e.getMessage());
                        str = sb.toString();
                        AbstractC0407b.m70a(str);
                    }
                }
            }
            this.f1029a.m1374a(m1349a2);
        }
    }

    /* renamed from: a */
    C0641fv m1349a() {
        int i;
        try {
            ByteBuffer m1346a = m1346a();
            i = m1346a.position();
            try {
                m1346a.flip();
                m1346a.position(8);
                C0641fv c0645fz = i == 8 ? new C0645fz() : C0641fv.m1322a(m1346a.slice());
                AbstractC0407b.m74c("[Slim] Read {cmd=" + c0645fz.m1325a() + ";chid=" + c0645fz.m1324a() + ";len=" + i + "}");
                return c0645fz;
            } catch (IOException e) {
                e = e;
                int i2 = i;
                if (i == 0) {
                    i2 = this.f1031a.position();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("[Slim] read Blob [");
                byte[] array = this.f1031a.array();
                int i3 = i2;
                if (i2 > 128) {
                    i3 = 128;
                }
                sb.append(C0490af.m431a(array, 0, i3));
                sb.append("] Err:");
                sb.append(e.getMessage());
                AbstractC0407b.m70a(sb.toString());
                throw e;
            }
        } catch (IOException e2) {
            e = e2;
            i = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1350a() {
        try {
            m1348c();
        } catch (IOException e) {
            if (!this.f1033a) {
                throw e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m1351b() {
        this.f1033a = true;
    }
}
