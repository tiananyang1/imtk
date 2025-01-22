package com.xiaomi.push;

import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.service.C0824bc;
import com.xiaomi.push.service.C0830bi;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.zip.Adler32;

/* renamed from: com.xiaomi.push.fx */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fx.class */
public class C0643fx {

    /* renamed from: a */
    private int f1036a;

    /* renamed from: a */
    private C0647ga f1037a;

    /* renamed from: a */
    private OutputStream f1038a;

    /* renamed from: a */
    ByteBuffer f1039a;

    /* renamed from: a */
    private Adler32 f1040a;

    /* renamed from: a */
    private byte[] f1041a;

    /* renamed from: b */
    private int f1042b;

    /* renamed from: b */
    private ByteBuffer f1043b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public C0643fx(OutputStream outputStream, C0647ga c0647ga) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    /* renamed from: a */
    public int m1352a(C0641fv c0641fv) {
        int mo1340c = c0641fv.mo1340c();
        if (mo1340c > 32768) {
            AbstractC0407b.m70a("Blob size=" + mo1340c + " should be less than 32768 Drop blob chid=" + c0641fv.m1324a() + " id=" + c0641fv.m1343e());
            return 0;
        }
        this.f1039a.clear();
        int i = mo1340c + 8 + 4;
        if (i > this.f1039a.capacity() || this.f1039a.capacity() > 4096) {
            this.f1039a = ByteBuffer.allocate(i);
        }
        this.f1039a.putShort((short) -15618);
        this.f1039a.putShort((short) 5);
        this.f1039a.putInt(mo1340c);
        int position = this.f1039a.position();
        this.f1039a = c0641fv.mo1326a(this.f1039a);
        if (!"CONN".equals(c0641fv.m1325a())) {
            if (this.f1041a == null) {
                this.f1041a = this.f1037a.m1381a();
            }
            C0824bc.m2625a(this.f1041a, this.f1039a.array(), true, position, mo1340c);
        }
        this.f1040a.reset();
        this.f1040a.update(this.f1039a.array(), 0, this.f1039a.position());
        this.f1043b.putInt(0, (int) this.f1040a.getValue());
        this.f1038a.write(this.f1039a.array(), 0, this.f1039a.position());
        this.f1038a.write(this.f1043b.array(), 0, 4);
        this.f1038a.flush();
        int position2 = this.f1039a.position() + 4;
        AbstractC0407b.m74c("[Slim] Wrote {cmd=" + c0641fv.m1325a() + ";chid=" + c0641fv.m1324a() + ";len=" + position2 + "}");
        return position2;
    }

    /* renamed from: a */
    public void m1353a() {
        C0612et.e eVar = new C0612et.e();
        eVar.m1104a(106);
        eVar.m1106a(Build.MODEL);
        eVar.m1110b(Build.VERSION.INCREMENTAL);
        eVar.m1115c(C0830bi.m2643a());
        eVar.m1109b(38);
        eVar.m1119d(this.f1037a.m1395b());
        eVar.m1123e(this.f1037a.mo1372a());
        eVar.m1126f(Locale.getDefault().toString());
        eVar.m1114c(Build.VERSION.SDK_INT);
        byte[] mo1417a = this.f1037a.mo1372a().mo1417a();
        if (mo1417a != null) {
            eVar.m1105a(C0612et.b.m1059a(mo1417a));
        }
        C0641fv c0641fv = new C0641fv();
        c0641fv.m1328a(0);
        c0641fv.m1331a("CONN", (String) null);
        c0641fv.m1329a(0L, "xiaomi.com", null);
        c0641fv.m1333a(eVar.mo959a(), (String) null);
        m1352a(c0641fv);
        AbstractC0407b.m70a("[slim] open conn: andver=" + Build.VERSION.SDK_INT + " sdk=38 hash=" + C0830bi.m2643a() + " tz=" + this.f1036a + Constants.COLON_SEPARATOR + this.f1042b + " Model=" + Build.MODEL + " os=" + Build.VERSION.INCREMENTAL);
    }

    /* renamed from: b */
    public void m1354b() {
        C0641fv c0641fv = new C0641fv();
        c0641fv.m1331a("CLOSE", (String) null);
        m1352a(c0641fv);
        this.f1038a.close();
    }
}
