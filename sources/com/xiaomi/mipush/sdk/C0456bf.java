package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0488ad;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.service.C0809ao;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* renamed from: com.xiaomi.mipush.sdk.bf */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/bf.class */
public class C0456bf {
    /* renamed from: a */
    public static void m279a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("last_sync_info", -1L);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long m2561a = C0809ao.m2557a(context).m2561a(EnumC0703ic.SyncInfoFrequency.m1669a(), 1209600);
        if (j != -1) {
            if (Math.abs(currentTimeMillis - j) <= m2561a) {
                return;
            } else {
                m281a(context, true);
            }
        }
        sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
    }

    /* renamed from: a */
    public static void m280a(Context context, C0732je c0732je) {
        AbstractC0407b.m70a("need to update local info with: " + c0732je.m2064a());
        String str = c0732je.m2064a().get(Constants.EXTRA_KEY_ACCEPT_TIME);
        if (str != null) {
            MiPushClient.removeAcceptTime(context);
            String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            if (split.length == 2) {
                MiPushClient.addAcceptTime(context, split[0], split[1]);
                if ("00:00".equals(split[0]) && "00:00".equals(split[1])) {
                    C0461d.m289a(context).m299a(true);
                } else {
                    C0461d.m289a(context).m299a(false);
                }
            }
        }
        String str2 = c0732je.m2064a().get(Constants.EXTRA_KEY_ALIASES);
        if (str2 != null) {
            MiPushClient.removeAllAliases(context);
            if (!"".equals(str2)) {
                String[] split2 = str2.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                int length = split2.length;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= length) {
                        break;
                    }
                    MiPushClient.addAlias(context, split2[i2]);
                    i = i2 + 1;
                }
            }
        }
        String str3 = c0732je.m2064a().get(Constants.EXTRA_KEY_TOPICS);
        if (str3 != null) {
            MiPushClient.removeAllTopics(context);
            if (!"".equals(str3)) {
                String[] split3 = str3.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                int length2 = split3.length;
                int i3 = 0;
                while (true) {
                    int i4 = i3;
                    if (i4 >= length2) {
                        break;
                    }
                    MiPushClient.addTopic(context, split3[i4]);
                    i3 = i4 + 1;
                }
            }
        }
        String str4 = c0732je.m2064a().get(Constants.EXTRA_KEY_ACCOUNTS);
        if (str4 == null) {
            return;
        }
        MiPushClient.removeAllAccounts(context);
        if ("".equals(str4)) {
            return;
        }
        String[] split4 = str4.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length3 = split4.length;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= length3) {
                return;
            }
            MiPushClient.addAccount(context, split4[i6]);
            i5 = i6 + 1;
        }
    }

    /* renamed from: a */
    public static void m281a(Context context, boolean z) {
        C0493ai.m439a(context).m443a(new RunnableC0457bg(context, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static String m283c(List<String> list) {
        String m521a = C0509ay.m521a(m284d(list));
        return (TextUtils.isEmpty(m521a) || m521a.length() <= 4) ? "" : m521a.substring(0, 4).toLowerCase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static String m284d(List<String> list) {
        if (C0488ad.m430a(list)) {
            return "";
        }
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, Collator.getInstance(Locale.CHINA));
        Iterator it = arrayList.iterator();
        String str = "";
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return str2;
            }
            String str3 = (String) it.next();
            String str4 = str2;
            if (!TextUtils.isEmpty(str2)) {
                str4 = str2 + Constants.ACCEPT_TIME_SEPARATOR_SP;
            }
            str = str4 + str3;
        }
    }
}
