package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.service.C0824bc;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.push.fv */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fv.class */
public class C0641fv {

    /* renamed from: a */
    private C0612et.a f1024a;

    /* renamed from: a */
    String f1025a;

    /* renamed from: a */
    private short f1026a;

    /* renamed from: b */
    private byte[] f1027b;

    /* renamed from: b */
    private static String f1023b = C0678he.m1527a(5) + Constants.ACCEPT_TIME_SEPARATOR_SERVER;

    /* renamed from: a */
    private static long f1021a = 0;

    /* renamed from: a */
    private static final byte[] f1022a = new byte[0];

    public C0641fv() {
        this.f1026a = (short) 2;
        this.f1027b = f1022a;
        this.f1025a = null;
        this.f1024a = new C0612et.a();
    }

    C0641fv(C0612et.a aVar, short s, byte[] bArr) {
        this.f1026a = (short) 2;
        this.f1027b = f1022a;
        this.f1025a = null;
        this.f1024a = aVar;
        this.f1026a = s;
        this.f1027b = bArr;
    }

    @Deprecated
    /* renamed from: a */
    public static C0641fv m1321a(AbstractC0666gt abstractC0666gt, String str) {
        int i;
        C0641fv c0641fv = new C0641fv();
        try {
            i = Integer.parseInt(abstractC0666gt.m1492k());
        } catch (Exception e) {
            AbstractC0407b.m70a("Blob parse chid err " + e.getMessage());
            i = 1;
        }
        c0641fv.m1328a(i);
        c0641fv.m1330a(abstractC0666gt.m1491j());
        c0641fv.m1342c(abstractC0666gt.m1496m());
        c0641fv.m1339b(abstractC0666gt.m1498n());
        c0641fv.m1331a("XMLMSG", (String) null);
        try {
            c0641fv.m1333a(abstractC0666gt.mo1456a().getBytes("utf8"), str);
            if (TextUtils.isEmpty(str)) {
                c0641fv.m1332a((short) 3);
                return c0641fv;
            }
            c0641fv.m1332a((short) 2);
            c0641fv.m1331a("SECMSG", (String) null);
            return c0641fv;
        } catch (UnsupportedEncodingException e2) {
            AbstractC0407b.m70a("Blob setPayload err： " + e2.getMessage());
            return c0641fv;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C0641fv m1322a(ByteBuffer byteBuffer) {
        try {
            ByteBuffer slice = byteBuffer.slice();
            short s = slice.getShort(0);
            short s2 = slice.getShort(2);
            int i = slice.getInt(4);
            C0612et.a aVar = new C0612et.a();
            aVar.m962a(slice.array(), slice.arrayOffset() + 8, (int) s2);
            byte[] bArr = new byte[i];
            slice.position(s2 + 8);
            slice.get(bArr, 0, i);
            return new C0641fv(aVar, s, bArr);
        } catch (Exception e) {
            AbstractC0407b.m70a("read Blob err :" + e.getMessage());
            throw new IOException("Malformed Input");
        }
    }

    /* renamed from: d */
    public static String m1323d() {
        String sb;
        synchronized (C0641fv.class) {
            try {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(f1023b);
                long j = f1021a;
                f1021a = 1 + j;
                sb2.append(Long.toString(j));
                sb = sb2.toString();
            } catch (Throwable th) {
                throw th;
            }
        }
        return sb;
    }

    /* renamed from: a */
    public int m1324a() {
        return this.f1024a.m1036c();
    }

    /* renamed from: a */
    public String m1325a() {
        return this.f1024a.m1039c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public ByteBuffer mo1326a(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = byteBuffer;
        if (byteBuffer == null) {
            byteBuffer2 = ByteBuffer.allocate(mo1340c());
        }
        byteBuffer2.putShort(this.f1026a);
        byteBuffer2.putShort((short) this.f1024a.mo959a());
        byteBuffer2.putInt(this.f1027b.length);
        int position = byteBuffer2.position();
        this.f1024a.m962a(byteBuffer2.array(), byteBuffer2.arrayOffset() + position, this.f1024a.mo959a());
        byteBuffer2.position(position + this.f1024a.mo959a());
        byteBuffer2.put(this.f1027b);
        return byteBuffer2;
    }

    /* renamed from: a */
    public short m1327a() {
        return this.f1026a;
    }

    /* renamed from: a */
    public void m1328a(int i) {
        this.f1024a.m1027a(i);
    }

    /* renamed from: a */
    public void m1329a(long j, String str, String str2) {
        if (j != 0) {
            this.f1024a.m1028a(j);
        }
        if (!TextUtils.isEmpty(str)) {
            this.f1024a.m1029a(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.f1024a.m1033b(str2);
    }

    /* renamed from: a */
    public void m1330a(String str) {
        this.f1024a.m1047e(str);
    }

    /* renamed from: a */
    public void m1331a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("command should not be empty");
        }
        this.f1024a.m1038c(str);
        this.f1024a.m1026a();
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.f1024a.m1043d(str2);
    }

    /* renamed from: a */
    public void m1332a(short s) {
        this.f1026a = s;
    }

    /* renamed from: a */
    public void m1333a(byte[] bArr, String str) {
        if (TextUtils.isEmpty(str)) {
            this.f1024a.m1037c(0);
            this.f1027b = bArr;
        } else {
            this.f1024a.m1037c(1);
            this.f1027b = C0824bc.m2624a(C0824bc.m2622a(str, m1343e()), bArr);
        }
    }

    /* renamed from: a */
    public boolean m1334a() {
        return this.f1024a.m1057j();
    }

    /* renamed from: a */
    public byte[] m1335a() {
        return this.f1027b;
    }

    /* renamed from: a */
    public byte[] m1336a(String str) {
        if (this.f1024a.m1046e() == 1) {
            return C0824bc.m2624a(C0824bc.m2622a(str, m1343e()), this.f1027b);
        }
        if (this.f1024a.m1046e() == 0) {
            return this.f1027b;
        }
        AbstractC0407b.m70a("unknow cipher = " + this.f1024a.m1046e());
        return this.f1027b;
    }

    /* renamed from: b */
    public int m1337b() {
        return this.f1024a.m1050f();
    }

    /* renamed from: b */
    public String m1338b() {
        return this.f1024a.m1044d();
    }

    /* renamed from: b */
    public void m1339b(String str) {
        this.f1025a = str;
    }

    /* renamed from: c */
    public int mo1340c() {
        return this.f1024a.mo967b() + 8 + this.f1027b.length;
    }

    /* renamed from: c */
    public String m1341c() {
        return this.f1024a.m1052f();
    }

    /* renamed from: c */
    public void m1342c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int indexOf = str.indexOf("@");
        try {
            long parseLong = Long.parseLong(str.substring(0, indexOf));
            int indexOf2 = str.indexOf("/", indexOf);
            String substring = str.substring(indexOf + 1, indexOf2);
            String substring2 = str.substring(indexOf2 + 1);
            this.f1024a.m1028a(parseLong);
            this.f1024a.m1029a(substring);
            this.f1024a.m1033b(substring2);
        } catch (Exception e) {
            AbstractC0407b.m70a("Blob parse user err " + e.getMessage());
        }
    }

    /* renamed from: e */
    public String m1343e() {
        String m1048e = this.f1024a.m1048e();
        if ("ID_NOT_AVAILABLE".equals(m1048e)) {
            return null;
        }
        if (!this.f1024a.m1054g()) {
            m1048e = m1323d();
            this.f1024a.m1047e(m1048e);
        }
        return m1048e;
    }

    /* renamed from: f */
    public String m1344f() {
        return this.f1025a;
    }

    /* renamed from: g */
    public String m1345g() {
        if (!this.f1024a.m1035b()) {
            return null;
        }
        return Long.toString(this.f1024a.m1025a()) + "@" + this.f1024a.m1030a() + "/" + this.f1024a.m1034b();
    }

    public String toString() {
        return "Blob [chid=" + m1324a() + "; Id=" + m1343e() + "; cmd=" + m1325a() + "; type=" + ((int) m1327a()) + "; from=" + m1345g() + " ]";
    }
}
