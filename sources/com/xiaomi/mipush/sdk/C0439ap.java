package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.mipush.sdk.ap */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ap.class */
public class C0439ap {

    /* renamed from: a */
    private static volatile C0439ap f289a;

    /* renamed from: a */
    private Context f290a;

    /* renamed from: a */
    private List<C0427ad> f291a = new ArrayList();

    private C0439ap(Context context) {
        this.f290a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (this.f290a == null) {
            this.f290a = context;
        }
    }

    /* renamed from: a */
    public static C0439ap m186a(Context context) {
        if (f289a == null) {
            synchronized (C0439ap.class) {
                try {
                    if (f289a == null) {
                        f289a = new C0439ap(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f289a;
    }

    /* renamed from: a */
    public int m187a(String str) {
        synchronized (this.f291a) {
            C0427ad c0427ad = new C0427ad();
            c0427ad.f277a = str;
            if (this.f291a.contains(c0427ad)) {
                for (C0427ad c0427ad2 : this.f291a) {
                    if (c0427ad2.equals(c0427ad)) {
                        return c0427ad2.f276a;
                    }
                }
            }
            return 0;
        }
    }

    /* renamed from: a */
    public String m188a(EnumC0455be enumC0455be) {
        String string;
        synchronized (this) {
            string = this.f290a.getSharedPreferences("mipush_extra", 0).getString(enumC0455be.name(), "");
        }
        return string;
    }

    /* renamed from: a */
    public void m189a(EnumC0455be enumC0455be, String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f290a.getSharedPreferences("mipush_extra", 0);
            sharedPreferences.edit().putString(enumC0455be.name(), str).commit();
        }
    }

    /* renamed from: a */
    public void m190a(String str) {
        synchronized (this.f291a) {
            C0427ad c0427ad = new C0427ad();
            c0427ad.f276a = 0;
            c0427ad.f277a = str;
            if (this.f291a.contains(c0427ad)) {
                this.f291a.remove(c0427ad);
            }
            this.f291a.add(c0427ad);
        }
    }

    /* renamed from: a */
    public boolean m191a(String str) {
        synchronized (this.f291a) {
            C0427ad c0427ad = new C0427ad();
            c0427ad.f277a = str;
            return this.f291a.contains(c0427ad);
        }
    }

    /* renamed from: b */
    public void m192b(String str) {
        synchronized (this.f291a) {
            C0427ad c0427ad = new C0427ad();
            c0427ad.f277a = str;
            C0427ad c0427ad2 = c0427ad;
            if (this.f291a.contains(c0427ad)) {
                Iterator<C0427ad> it = this.f291a.iterator();
                do {
                    c0427ad2 = c0427ad;
                    if (!it.hasNext()) {
                        break;
                    } else {
                        c0427ad2 = it.next();
                    }
                } while (!c0427ad.equals(c0427ad2));
            }
            c0427ad2.f276a++;
            this.f291a.remove(c0427ad2);
            this.f291a.add(c0427ad2);
        }
    }

    /* renamed from: c */
    public void m193c(String str) {
        synchronized (this.f291a) {
            C0427ad c0427ad = new C0427ad();
            c0427ad.f277a = str;
            if (this.f291a.contains(c0427ad)) {
                this.f291a.remove(c0427ad);
            }
        }
    }
}
