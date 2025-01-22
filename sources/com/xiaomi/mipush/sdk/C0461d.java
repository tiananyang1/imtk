package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0646g;
import com.xiaomi.push.C0727j;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.xiaomi.mipush.sdk.d */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/d.class */
public class C0461d {

    /* renamed from: a */
    private static volatile C0461d f339a;

    /* renamed from: a */
    private Context f340a;

    /* renamed from: a */
    private a f341a;

    /* renamed from: a */
    String f342a;

    /* renamed from: a */
    private Map<String, a> f343a;

    /* renamed from: com.xiaomi.mipush.sdk.d$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/d$a.class */
    public static class a {

        /* renamed from: a */
        private Context f345a;

        /* renamed from: a */
        public String f346a;

        /* renamed from: b */
        public String f348b;

        /* renamed from: c */
        public String f350c;

        /* renamed from: d */
        public String f351d;

        /* renamed from: e */
        public String f352e;

        /* renamed from: f */
        public String f353f;

        /* renamed from: g */
        public String f354g;

        /* renamed from: h */
        public String f355h;

        /* renamed from: a */
        public boolean f347a = true;

        /* renamed from: b */
        public boolean f349b = false;

        /* renamed from: a */
        public int f344a = 1;

        public a(Context context) {
            this.f345a = context;
        }

        /* renamed from: a */
        public static a m315a(Context context, String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                a aVar = new a(context);
                aVar.f346a = jSONObject.getString("appId");
                aVar.f348b = jSONObject.getString("appToken");
                aVar.f350c = jSONObject.getString("regId");
                aVar.f351d = jSONObject.getString("regSec");
                aVar.f353f = jSONObject.getString("devId");
                aVar.f352e = jSONObject.getString("vName");
                aVar.f347a = jSONObject.getBoolean("valid");
                aVar.f349b = jSONObject.getBoolean("paused");
                aVar.f344a = jSONObject.getInt("envType");
                aVar.f354g = jSONObject.getString("regResource");
                return aVar;
            } catch (Throwable th) {
                AbstractC0407b.m72a(th);
                return null;
            }
        }

        /* renamed from: a */
        private String m316a() {
            Context context = this.f345a;
            return C0646g.m1359a(context, context.getPackageName());
        }

        /* renamed from: a */
        public static String m317a(a aVar) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appId", aVar.f346a);
                jSONObject.put("appToken", aVar.f348b);
                jSONObject.put("regId", aVar.f350c);
                jSONObject.put("regSec", aVar.f351d);
                jSONObject.put("devId", aVar.f353f);
                jSONObject.put("vName", aVar.f352e);
                jSONObject.put("valid", aVar.f347a);
                jSONObject.put("paused", aVar.f349b);
                jSONObject.put("envType", aVar.f344a);
                jSONObject.put("regResource", aVar.f354g);
                return jSONObject.toString();
            } catch (Throwable th) {
                AbstractC0407b.m72a(th);
                return null;
            }
        }

        /* renamed from: a */
        public void m318a() {
            C0461d.m288a(this.f345a).edit().clear().commit();
            this.f346a = null;
            this.f348b = null;
            this.f350c = null;
            this.f351d = null;
            this.f353f = null;
            this.f352e = null;
            this.f347a = false;
            this.f349b = false;
            this.f355h = null;
            this.f344a = 1;
        }

        /* renamed from: a */
        public void m319a(int i) {
            this.f344a = i;
        }

        /* renamed from: a */
        public void m320a(String str, String str2) {
            this.f350c = str;
            this.f351d = str2;
            this.f353f = C0727j.m1998k(this.f345a);
            this.f352e = m316a();
            this.f347a = true;
        }

        /* renamed from: a */
        public void m321a(String str, String str2, String str3) {
            this.f346a = str;
            this.f348b = str2;
            this.f354g = str3;
            SharedPreferences.Editor edit = C0461d.m288a(this.f345a).edit();
            edit.putString("appId", this.f346a);
            edit.putString("appToken", str2);
            edit.putString("regResource", str3);
            edit.commit();
        }

        /* renamed from: a */
        public void m322a(boolean z) {
            this.f349b = z;
        }

        /* renamed from: a */
        public boolean m323a() {
            return m324a(this.f346a, this.f348b);
        }

        /* renamed from: a */
        public boolean m324a(String str, String str2) {
            return TextUtils.equals(this.f346a, str) && TextUtils.equals(this.f348b, str2) && !TextUtils.isEmpty(this.f350c) && !TextUtils.isEmpty(this.f351d) && TextUtils.equals(this.f353f, C0727j.m1998k(this.f345a));
        }

        /* renamed from: b */
        public void m325b() {
            this.f347a = false;
            C0461d.m288a(this.f345a).edit().putBoolean("valid", this.f347a).commit();
        }

        /* renamed from: b */
        public void m326b(String str, String str2, String str3) {
            this.f350c = str;
            this.f351d = str2;
            this.f353f = C0727j.m1998k(this.f345a);
            this.f352e = m316a();
            this.f347a = true;
            this.f355h = str3;
            SharedPreferences.Editor edit = C0461d.m288a(this.f345a).edit();
            edit.putString("regId", str);
            edit.putString("regSec", str2);
            edit.putString("devId", this.f353f);
            edit.putString("vName", m316a());
            edit.putBoolean("valid", true);
            edit.putString("appRegion", str3);
            edit.commit();
        }

        /* renamed from: c */
        public void m327c(String str, String str2, String str3) {
            this.f346a = str;
            this.f348b = str2;
            this.f354g = str3;
        }
    }

    private C0461d(Context context) {
        this.f340a = context;
        m290c();
    }

    /* renamed from: a */
    public static SharedPreferences m288a(Context context) {
        return context.getSharedPreferences("mipush", 0);
    }

    /* renamed from: a */
    public static C0461d m289a(Context context) {
        if (f339a == null) {
            synchronized (C0461d.class) {
                try {
                    if (f339a == null) {
                        f339a = new C0461d(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f339a;
    }

    /* renamed from: c */
    private void m290c() {
        this.f341a = new a(this.f340a);
        this.f343a = new HashMap();
        SharedPreferences m288a = m288a(this.f340a);
        this.f341a.f346a = m288a.getString("appId", null);
        this.f341a.f348b = m288a.getString("appToken", null);
        this.f341a.f350c = m288a.getString("regId", null);
        this.f341a.f351d = m288a.getString("regSec", null);
        this.f341a.f353f = m288a.getString("devId", null);
        if (!TextUtils.isEmpty(this.f341a.f353f) && this.f341a.f353f.startsWith("a-")) {
            this.f341a.f353f = C0727j.m1998k(this.f340a);
            m288a.edit().putString("devId", this.f341a.f353f).commit();
        }
        this.f341a.f352e = m288a.getString("vName", null);
        this.f341a.f347a = m288a.getBoolean("valid", true);
        this.f341a.f349b = m288a.getBoolean("paused", false);
        this.f341a.f344a = m288a.getInt("envType", 1);
        this.f341a.f354g = m288a.getString("regResource", null);
        this.f341a.f355h = m288a.getString("appRegion", null);
    }

    /* renamed from: a */
    public int m291a() {
        return this.f341a.f344a;
    }

    /* renamed from: a */
    public a m292a(String str) {
        if (this.f343a.containsKey(str)) {
            return this.f343a.get(str);
        }
        String str2 = "hybrid_app_info_" + str;
        SharedPreferences m288a = m288a(this.f340a);
        if (!m288a.contains(str2)) {
            return null;
        }
        a m315a = a.m315a(this.f340a, m288a.getString(str2, ""));
        this.f343a.put(str2, m315a);
        return m315a;
    }

    /* renamed from: a */
    public String m293a() {
        return this.f341a.f346a;
    }

    /* renamed from: a */
    public void m294a() {
        this.f341a.m318a();
    }

    /* renamed from: a */
    public void m295a(int i) {
        this.f341a.m319a(i);
        m288a(this.f340a).edit().putInt("envType", i).commit();
    }

    /* renamed from: a */
    public void m296a(String str) {
        SharedPreferences.Editor edit = m288a(this.f340a).edit();
        edit.putString("vName", str);
        edit.commit();
        this.f341a.f352e = str;
    }

    /* renamed from: a */
    public void m297a(String str, a aVar) {
        this.f343a.put(str, aVar);
        m288a(this.f340a).edit().putString("hybrid_app_info_" + str, a.m317a(aVar)).commit();
    }

    /* renamed from: a */
    public void m298a(String str, String str2, String str3) {
        this.f341a.m321a(str, str2, str3);
    }

    /* renamed from: a */
    public void m299a(boolean z) {
        this.f341a.m322a(z);
        m288a(this.f340a).edit().putBoolean("paused", z).commit();
    }

    /* renamed from: a */
    public boolean m300a() {
        Context context = this.f340a;
        return !TextUtils.equals(C0646g.m1359a(context, context.getPackageName()), this.f341a.f352e);
    }

    /* renamed from: a */
    public boolean m301a(String str, String str2) {
        return this.f341a.m324a(str, str2);
    }

    /* renamed from: a */
    public boolean m302a(String str, String str2, String str3) {
        a m292a = m292a(str3);
        return m292a != null && TextUtils.equals(str, m292a.f346a) && TextUtils.equals(str2, m292a.f348b);
    }

    /* renamed from: b */
    public String m303b() {
        return this.f341a.f348b;
    }

    /* renamed from: b */
    public void m304b() {
        this.f341a.m325b();
    }

    /* renamed from: b */
    public void m305b(String str) {
        this.f343a.remove(str);
        m288a(this.f340a).edit().remove("hybrid_app_info_" + str).commit();
    }

    /* renamed from: b */
    public void m306b(String str, String str2, String str3) {
        this.f341a.m326b(str, str2, str3);
    }

    /* renamed from: b */
    public boolean m307b() {
        if (this.f341a.m323a()) {
            return true;
        }
        AbstractC0407b.m70a("Don't send message before initialization succeeded!");
        return false;
    }

    /* renamed from: c */
    public String m308c() {
        return this.f341a.f350c;
    }

    /* renamed from: c */
    public boolean m309c() {
        return this.f341a.m323a();
    }

    /* renamed from: d */
    public String m310d() {
        return this.f341a.f351d;
    }

    /* renamed from: d */
    public boolean m311d() {
        return this.f341a.f349b;
    }

    /* renamed from: e */
    public String m312e() {
        return this.f341a.f354g;
    }

    /* renamed from: e */
    public boolean m313e() {
        return !this.f341a.f347a;
    }

    /* renamed from: f */
    public String m314f() {
        return this.f341a.f355h;
    }
}
