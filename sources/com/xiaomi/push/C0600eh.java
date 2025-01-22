package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;

/* renamed from: com.xiaomi.push.eh */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/eh.class */
public class C0600eh extends AbstractC0601ei {

    /* renamed from: a */
    public static String f730a = "";

    /* renamed from: b */
    public static String f731b = "";

    public C0600eh(Context context, int i) {
        super(context, i);
    }

    /* renamed from: a */
    private String m978a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String[] split = str2.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        if (split.length <= 10) {
            return str2;
        }
        int length = split.length;
        while (true) {
            int i = length - 1;
            if (i < split.length - 10) {
                return str;
            }
            str = str + split[i];
            length = i;
        }
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 12;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.BroadcastAction;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        String str;
        if (TextUtils.isEmpty(f730a)) {
            str = "";
        } else {
            str = "" + m978a(C0590dy.f725a, f730a);
            f730a = "";
        }
        String str2 = str;
        if (!TextUtils.isEmpty(f731b)) {
            str2 = str + m978a(C0590dy.f726b, f731b);
            f731b = "";
        }
        return str2;
    }
}
