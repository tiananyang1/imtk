package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import com.microsoft.codepush.react.CodePushConstants;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0496al;
import com.xiaomi.push.service.C0830bi;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.dl */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dl.class */
public class C0577dl {

    /* renamed from: a */
    private static volatile C0577dl f683a;

    /* renamed from: a */
    private Context f684a;

    /* renamed from: a */
    private final ConcurrentLinkedQueue<b> f685a = new ConcurrentLinkedQueue<>();

    /* renamed from: com.xiaomi.push.dl$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dl$a.class */
    class a extends b {
        a() {
            super();
        }

        @Override // com.xiaomi.push.C0577dl.b, com.xiaomi.push.C0496al.b
        /* renamed from: b */
        public void mo462b() {
            C0577dl.this.m933b();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.dl$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dl$b.class */
    public class b extends C0496al.b {

        /* renamed from: a */
        long f687a = System.currentTimeMillis();

        /* JADX INFO: Access modifiers changed from: package-private */
        public b() {
        }

        /* renamed from: a */
        public boolean mo938a() {
            return true;
        }

        @Override // com.xiaomi.push.C0496al.b
        /* renamed from: b */
        public void mo462b() {
        }

        /* renamed from: b */
        final boolean m939b() {
            return System.currentTimeMillis() - this.f687a > 172800000;
        }
    }

    /* renamed from: com.xiaomi.push.dl$c */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dl$c.class */
    class c extends b {

        /* renamed from: a */
        int f689a;

        /* renamed from: a */
        File f691a;

        /* renamed from: a */
        String f692a;

        /* renamed from: a */
        boolean f693a;

        /* renamed from: b */
        String f694b;

        /* renamed from: b */
        boolean f695b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public c(String str, String str2, File file, boolean z) {
            super();
            this.f692a = str;
            this.f694b = str2;
            this.f691a = file;
            this.f695b = z;
        }

        /* renamed from: c */
        private boolean m940c() {
            int i;
            SharedPreferences sharedPreferences = C0577dl.this.f684a.getSharedPreferences("log.timestamp", 0);
            String string = sharedPreferences.getString("log.requst", "");
            long currentTimeMillis = System.currentTimeMillis();
            try {
                JSONObject jSONObject = new JSONObject(string);
                long j = jSONObject.getLong(CodePushConstants.LATEST_ROLLBACK_TIME_KEY);
                currentTimeMillis = j;
                i = jSONObject.getInt("times");
                currentTimeMillis = j;
            } catch (JSONException e) {
                i = 0;
            }
            if (System.currentTimeMillis() - currentTimeMillis >= 86400000) {
                currentTimeMillis = System.currentTimeMillis();
                i = 0;
            } else if (i > 10) {
                return false;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(CodePushConstants.LATEST_ROLLBACK_TIME_KEY, currentTimeMillis);
                jSONObject2.put("times", i + 1);
                sharedPreferences.edit().putString("log.requst", jSONObject2.toString()).commit();
                return true;
            } catch (JSONException e2) {
                AbstractC0407b.m74c("JSONException on put " + e2.getMessage());
                return true;
            }
        }

        @Override // com.xiaomi.push.C0577dl.b
        /* renamed from: a */
        public boolean mo938a() {
            if (C0503as.m486d(C0577dl.this.f684a)) {
                return true;
            }
            return this.f695b && C0503as.m484b(C0577dl.this.f684a);
        }

        @Override // com.xiaomi.push.C0577dl.b, com.xiaomi.push.C0496al.b
        /* renamed from: b */
        public void mo462b() {
            try {
                if (m940c()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("uid", C0830bi.m2643a());
                    hashMap.put("token", this.f694b);
                    hashMap.put("net", C0503as.m475a(C0577dl.this.f684a));
                    C0503as.m479a(this.f692a, hashMap, this.f691a, "file");
                }
                this.f693a = true;
            } catch (IOException e) {
            }
        }

        @Override // com.xiaomi.push.C0496al.b
        /* renamed from: c */
        public void mo463c() {
            if (!this.f693a) {
                this.f689a++;
                if (this.f689a < 3) {
                    C0577dl.this.f685a.add(this);
                }
            }
            if (this.f693a || this.f689a >= 3) {
                this.f691a.delete();
            }
            C0577dl.this.m930a((1 << this.f689a) * PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT);
        }
    }

    private C0577dl(Context context) {
        this.f684a = context;
        this.f685a.add(new a());
        m934b(0L);
    }

    /* renamed from: a */
    public static C0577dl m928a(Context context) {
        if (f683a == null) {
            synchronized (C0577dl.class) {
                try {
                    if (f683a == null) {
                        f683a = new C0577dl(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        f683a.f684a = context;
        return f683a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m930a(long j) {
        b peek = this.f685a.peek();
        if (peek == null || !peek.mo938a()) {
            return;
        }
        m934b(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m933b() {
        if (C0485aa.m421b() || C0485aa.m420a()) {
            return;
        }
        try {
            File file = new File(this.f684a.getExternalFilesDir(null) + "/.logcache");
            if (!file.exists() || !file.isDirectory()) {
                return;
            }
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return;
                }
                listFiles[i2].delete();
                i = i2 + 1;
            }
        } catch (NullPointerException e) {
        }
    }

    /* renamed from: b */
    private void m934b(long j) {
        if (this.f685a.isEmpty()) {
            return;
        }
        C0679hf.m1533a(new C0579dn(this), j);
    }

    /* renamed from: c */
    private void m935c() {
        while (!this.f685a.isEmpty()) {
            b peek = this.f685a.peek();
            if (peek != null) {
                if (!peek.m939b() && this.f685a.size() <= 6) {
                    return;
                }
                AbstractC0407b.m74c("remove Expired task");
                this.f685a.remove(peek);
            }
        }
    }

    /* renamed from: a */
    public void m936a() {
        m935c();
        m930a(0L);
    }

    /* renamed from: a */
    public void m937a(String str, String str2, Date date, Date date2, int i, boolean z) {
        this.f685a.add(new C0578dm(this, i, date, date2, str, str2, z));
        m934b(0L);
    }
}
