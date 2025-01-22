package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0486ab;
import com.xiaomi.push.C0650gd;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.EnumC0774q;

/* renamed from: com.xiaomi.push.service.t */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/t.class */
public class C0871t {

    /* renamed from: a */
    private static C0870s f2714a;

    /* renamed from: a */
    private static a f2715a;

    /* renamed from: com.xiaomi.push.service.t$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/t$a.class */
    public interface a {
        /* renamed from: a */
        void mo2674a();
    }

    /* renamed from: a */
    public static C0870s m2795a(Context context) {
        synchronized (C0871t.class) {
            try {
                if (f2714a != null) {
                    return f2714a;
                }
                SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
                String string = sharedPreferences.getString("uuid", null);
                String string2 = sharedPreferences.getString("token", null);
                String string3 = sharedPreferences.getString("security", null);
                String string4 = sharedPreferences.getString(Constants.APP_ID, null);
                String string5 = sharedPreferences.getString("app_token", null);
                String string6 = sharedPreferences.getString(Constants.PACKAGE_NAME, null);
                String string7 = sharedPreferences.getString("device_id", null);
                int i = sharedPreferences.getInt("env_type", 1);
                String str = string7;
                if (!TextUtils.isEmpty(string7)) {
                    str = string7;
                    if (string7.startsWith("a-")) {
                        str = C0727j.m1998k(context);
                        sharedPreferences.edit().putString("device_id", str).commit();
                    }
                }
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                    return null;
                }
                String m1998k = C0727j.m1998k(context);
                if ("com.xiaomi.xmsf".equals(context.getPackageName()) || TextUtils.isEmpty(m1998k) || TextUtils.isEmpty(str) || str.equals(m1998k)) {
                    f2714a = new C0870s(string, string2, string3, string4, string5, string6, i);
                    return f2714a;
                }
                AbstractC0407b.m75d("erase the old account.");
                m2799a(context);
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(41:3|4|(3:8|9|(38:11|12|(1:14)|15|16|(1:18)|19|20|(2:22|23)|24|(2:26|27)|28|29|(2:31|32)|33|34|35|36|37|(1:39)(1:96)|40|(14:42|43|44|45|46|(1:48)|49|50|51|52|(4:54|55|56|(1:58))|59|60|(1:62))|63|64|(1:66)|67|68|(1:70)|71|72|73|(1:75)|76|77|(2:79|(9:81|82|83|(1:85)|86|87|88|89|90)(1:92))|93|94|95))|101|12|(0)|15|16|(0)|19|20|(0)|24|(0)|28|29|(0)|33|34|35|36|37|(0)(0)|40|(0)|63|64|(0)|67|68|(0)|71|72|73|(0)|76|77|(0)|93|94|95) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x00ef, code lost:            r11 = null;     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x00e9, code lost:            r11 = move-exception;     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00ea, code lost:            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m72a(r11);     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007b A[Catch: all -> 0x0358, TRY_ENTER, TryCatch #1 {all -> 0x0358, blocks: (B:4:0x0003, B:6:0x002f, B:8:0x003b, B:11:0x005e, B:12:0x0070, B:14:0x007b, B:16:0x0088, B:18:0x0095, B:20:0x00a2, B:24:0x00ab, B:29:0x00b8, B:33:0x00c3, B:36:0x00da, B:39:0x00f4, B:40:0x00ff, B:44:0x0149, B:46:0x0151, B:48:0x0157, B:50:0x0176, B:52:0x017e, B:56:0x0188, B:58:0x0190, B:60:0x01b9, B:62:0x01bf, B:63:0x01cb, B:64:0x01ce, B:66:0x0202, B:68:0x0213, B:70:0x021e, B:72:0x022f, B:75:0x0276, B:77:0x027d, B:79:0x0283, B:81:0x0298, B:83:0x02c2, B:85:0x02ca, B:86:0x02e9, B:87:0x02ec, B:92:0x033b, B:99:0x00ea), top: B:3:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0095 A[Catch: all -> 0x0358, TryCatch #1 {all -> 0x0358, blocks: (B:4:0x0003, B:6:0x002f, B:8:0x003b, B:11:0x005e, B:12:0x0070, B:14:0x007b, B:16:0x0088, B:18:0x0095, B:20:0x00a2, B:24:0x00ab, B:29:0x00b8, B:33:0x00c3, B:36:0x00da, B:39:0x00f4, B:40:0x00ff, B:44:0x0149, B:46:0x0151, B:48:0x0157, B:50:0x0176, B:52:0x017e, B:56:0x0188, B:58:0x0190, B:60:0x01b9, B:62:0x01bf, B:63:0x01cb, B:64:0x01ce, B:66:0x0202, B:68:0x0213, B:70:0x021e, B:72:0x022f, B:75:0x0276, B:77:0x027d, B:79:0x0283, B:81:0x0298, B:83:0x02c2, B:85:0x02ca, B:86:0x02e9, B:87:0x02ec, B:92:0x033b, B:99:0x00ea), top: B:3:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f4 A[Catch: all -> 0x0358, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0358, blocks: (B:4:0x0003, B:6:0x002f, B:8:0x003b, B:11:0x005e, B:12:0x0070, B:14:0x007b, B:16:0x0088, B:18:0x0095, B:20:0x00a2, B:24:0x00ab, B:29:0x00b8, B:33:0x00c3, B:36:0x00da, B:39:0x00f4, B:40:0x00ff, B:44:0x0149, B:46:0x0151, B:48:0x0157, B:50:0x0176, B:52:0x017e, B:56:0x0188, B:58:0x0190, B:60:0x01b9, B:62:0x01bf, B:63:0x01cb, B:64:0x01ce, B:66:0x0202, B:68:0x0213, B:70:0x021e, B:72:0x022f, B:75:0x0276, B:77:0x027d, B:79:0x0283, B:81:0x0298, B:83:0x02c2, B:85:0x02ca, B:86:0x02e9, B:87:0x02ec, B:92:0x033b, B:99:0x00ea), top: B:3:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0202 A[Catch: all -> 0x0358, TRY_ENTER, TryCatch #1 {all -> 0x0358, blocks: (B:4:0x0003, B:6:0x002f, B:8:0x003b, B:11:0x005e, B:12:0x0070, B:14:0x007b, B:16:0x0088, B:18:0x0095, B:20:0x00a2, B:24:0x00ab, B:29:0x00b8, B:33:0x00c3, B:36:0x00da, B:39:0x00f4, B:40:0x00ff, B:44:0x0149, B:46:0x0151, B:48:0x0157, B:50:0x0176, B:52:0x017e, B:56:0x0188, B:58:0x0190, B:60:0x01b9, B:62:0x01bf, B:63:0x01cb, B:64:0x01ce, B:66:0x0202, B:68:0x0213, B:70:0x021e, B:72:0x022f, B:75:0x0276, B:77:0x027d, B:79:0x0283, B:81:0x0298, B:83:0x02c2, B:85:0x02ca, B:86:0x02e9, B:87:0x02ec, B:92:0x033b, B:99:0x00ea), top: B:3:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x021e A[Catch: all -> 0x0358, TryCatch #1 {all -> 0x0358, blocks: (B:4:0x0003, B:6:0x002f, B:8:0x003b, B:11:0x005e, B:12:0x0070, B:14:0x007b, B:16:0x0088, B:18:0x0095, B:20:0x00a2, B:24:0x00ab, B:29:0x00b8, B:33:0x00c3, B:36:0x00da, B:39:0x00f4, B:40:0x00ff, B:44:0x0149, B:46:0x0151, B:48:0x0157, B:50:0x0176, B:52:0x017e, B:56:0x0188, B:58:0x0190, B:60:0x01b9, B:62:0x01bf, B:63:0x01cb, B:64:0x01ce, B:66:0x0202, B:68:0x0213, B:70:0x021e, B:72:0x022f, B:75:0x0276, B:77:0x027d, B:79:0x0283, B:81:0x0298, B:83:0x02c2, B:85:0x02ca, B:86:0x02e9, B:87:0x02ec, B:92:0x033b, B:99:0x00ea), top: B:3:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0276 A[Catch: all -> 0x0358, TRY_ENTER, TryCatch #1 {all -> 0x0358, blocks: (B:4:0x0003, B:6:0x002f, B:8:0x003b, B:11:0x005e, B:12:0x0070, B:14:0x007b, B:16:0x0088, B:18:0x0095, B:20:0x00a2, B:24:0x00ab, B:29:0x00b8, B:33:0x00c3, B:36:0x00da, B:39:0x00f4, B:40:0x00ff, B:44:0x0149, B:46:0x0151, B:48:0x0157, B:50:0x0176, B:52:0x017e, B:56:0x0188, B:58:0x0190, B:60:0x01b9, B:62:0x01bf, B:63:0x01cb, B:64:0x01ce, B:66:0x0202, B:68:0x0213, B:70:0x021e, B:72:0x022f, B:75:0x0276, B:77:0x027d, B:79:0x0283, B:81:0x0298, B:83:0x02c2, B:85:0x02ca, B:86:0x02e9, B:87:0x02ec, B:92:0x033b, B:99:0x00ea), top: B:3:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0283 A[Catch: all -> 0x0358, TryCatch #1 {all -> 0x0358, blocks: (B:4:0x0003, B:6:0x002f, B:8:0x003b, B:11:0x005e, B:12:0x0070, B:14:0x007b, B:16:0x0088, B:18:0x0095, B:20:0x00a2, B:24:0x00ab, B:29:0x00b8, B:33:0x00c3, B:36:0x00da, B:39:0x00f4, B:40:0x00ff, B:44:0x0149, B:46:0x0151, B:48:0x0157, B:50:0x0176, B:52:0x017e, B:56:0x0188, B:58:0x0190, B:60:0x01b9, B:62:0x01bf, B:63:0x01cb, B:64:0x01ce, B:66:0x0202, B:68:0x0213, B:70:0x021e, B:72:0x022f, B:75:0x0276, B:77:0x027d, B:79:0x0283, B:81:0x0298, B:83:0x02c2, B:85:0x02ca, B:86:0x02e9, B:87:0x02ec, B:92:0x033b, B:99:0x00ea), top: B:3:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0364  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.xiaomi.push.service.C0870s m2796a(android.content.Context r10, java.lang.String r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 875
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0871t.m2796a(android.content.Context, java.lang.String, java.lang.String, java.lang.String):com.xiaomi.push.service.s");
    }

    /* renamed from: a */
    public static String m2797a(Context context) {
        StringBuilder sb;
        String str;
        String m2490a = C0794a.m2485a(context).m2490a();
        if (C0486ab.m427b()) {
            sb = new StringBuilder();
            sb.append("http://");
            sb.append(C0650gd.f1077b);
            str = ":9085";
        } else if (EnumC0774q.Global.name().equals(m2490a)) {
            sb = new StringBuilder();
            str = "https://register.xmpush.global.xiaomi.com";
        } else if (EnumC0774q.Europe.name().equals(m2490a)) {
            sb = new StringBuilder();
            str = "https://fr.register.xmpush.global.xiaomi.com";
        } else if (EnumC0774q.Russia.name().equals(m2490a)) {
            sb = new StringBuilder();
            str = "https://ru.register.xmpush.global.xiaomi.com";
        } else if (EnumC0774q.India.name().equals(m2490a)) {
            sb = new StringBuilder();
            str = "https://idmb.register.xmpush.global.xiaomi.com";
        } else {
            sb = new StringBuilder();
            sb.append("https://");
            str = C0486ab.m426a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com";
        }
        sb.append(str);
        sb.append("/pass/v2/register");
        return sb.toString();
    }

    /* renamed from: a */
    public static void m2798a() {
        a aVar = f2715a;
        if (aVar != null) {
            aVar.mo2674a();
        }
    }

    /* renamed from: a */
    public static void m2799a(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        f2714a = null;
        m2798a();
    }

    /* renamed from: a */
    public static void m2800a(Context context, C0870s c0870s) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", c0870s.f2708a);
        edit.putString("security", c0870s.f2710c);
        edit.putString("token", c0870s.f2709b);
        edit.putString(Constants.APP_ID, c0870s.f2711d);
        edit.putString(Constants.PACKAGE_NAME, c0870s.f2713f);
        edit.putString("app_token", c0870s.f2712e);
        edit.putString("device_id", C0727j.m1998k(context));
        edit.putInt("env_type", c0870s.f2707a);
        edit.commit();
        m2798a();
    }

    /* renamed from: a */
    public static void m2801a(a aVar) {
        f2715a = aVar;
    }

    /* renamed from: a */
    private static boolean m2802a(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }
}
