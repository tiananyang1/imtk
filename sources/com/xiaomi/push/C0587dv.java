package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.service.C0809ao;

/* renamed from: com.xiaomi.push.dv */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dv.class */
final class C0587dv implements InterfaceC0591dz {
    /* renamed from: a */
    private void m952a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (C0491ag.m433a(context, String.valueOf(12), 1L)) {
                C0707ig c0707ig = new C0707ig();
                c0707ig.m1678a(str + Constants.COLON_SEPARATOR + str2);
                c0707ig.m1676a(System.currentTimeMillis());
                c0707ig.m1677a(EnumC0699hz.BroadcastAction);
                AbstractC0601ei.m979a(context, c0707ig);
            }
        } catch (Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m954b(Context context, Intent intent) {
        int m1626a;
        try {
            String dataString = intent.getDataString();
            if (TextUtils.isEmpty(dataString)) {
                return;
            }
            String[] split = dataString.split(Constants.COLON_SEPARATOR);
            if (split.length < 2 || TextUtils.isEmpty(split[1])) {
                return;
            }
            String str = split[1];
            long currentTimeMillis = System.currentTimeMillis();
            boolean m2563a = C0809ao.m2557a(context).m2563a(EnumC0703ic.BroadcastActionCollectionSwitch.m1669a(), true);
            if (TextUtils.equals("android.intent.action.PACKAGE_RESTARTED", intent.getAction())) {
                if (C0491ag.m433a(context, String.valueOf(12), 1L) && m2563a) {
                    if (TextUtils.isEmpty(C0600eh.f730a)) {
                        C0600eh.f730a += C0590dy.f725a + Constants.COLON_SEPARATOR;
                    }
                    C0600eh.f730a += str + "(" + currentTimeMillis + ")" + Constants.ACCEPT_TIME_SEPARATOR_SP;
                    return;
                }
                return;
            }
            if (TextUtils.equals("android.intent.action.PACKAGE_CHANGED", intent.getAction())) {
                if (C0491ag.m433a(context, String.valueOf(12), 1L) && m2563a) {
                    if (TextUtils.isEmpty(C0600eh.f731b)) {
                        C0600eh.f731b += C0590dy.f726b + Constants.COLON_SEPARATOR;
                    }
                    C0600eh.f731b += str + "(" + currentTimeMillis + ")" + Constants.ACCEPT_TIME_SEPARATOR_SP;
                    return;
                }
                return;
            }
            if (TextUtils.equals("android.intent.action.PACKAGE_ADDED", intent.getAction())) {
                if (intent.getExtras().getBoolean("android.intent.extra.REPLACING") || !m2563a) {
                    return;
                } else {
                    m1626a = EnumC0699hz.BroadcastActionAdded.m1626a();
                }
            } else if (TextUtils.equals("android.intent.action.PACKAGE_REMOVED", intent.getAction())) {
                if (intent.getExtras().getBoolean("android.intent.extra.REPLACING") || !m2563a) {
                    return;
                } else {
                    m1626a = EnumC0699hz.BroadcastActionRemoved.m1626a();
                }
            } else if (TextUtils.equals("android.intent.action.PACKAGE_REPLACED", intent.getAction())) {
                if (!m2563a) {
                    return;
                } else {
                    m1626a = EnumC0699hz.BroadcastActionReplaced.m1626a();
                }
            } else if (!TextUtils.equals("android.intent.action.PACKAGE_DATA_CLEARED", intent.getAction()) || !m2563a) {
                return;
            } else {
                m1626a = EnumC0699hz.BroadcastActionDataCleared.m1626a();
            }
            m952a(context, String.valueOf(m1626a), str);
        } catch (Throwable th) {
        }
    }

    @Override // com.xiaomi.push.InterfaceC0591dz
    /* renamed from: a */
    public void mo955a(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        C0493ai.m439a(context).m443a(new RunnableC0588dw(this, context, intent));
    }
}
