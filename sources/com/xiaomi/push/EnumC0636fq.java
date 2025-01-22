package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushClient;

/* renamed from: com.xiaomi.push.fq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fq.class */
public enum EnumC0636fq {
    COMMAND_REGISTER(MiPushClient.COMMAND_REGISTER),
    COMMAND_UNREGISTER(MiPushClient.COMMAND_UNREGISTER),
    COMMAND_SET_ALIAS(MiPushClient.COMMAND_SET_ALIAS),
    COMMAND_UNSET_ALIAS(MiPushClient.COMMAND_UNSET_ALIAS),
    COMMAND_SET_ACCOUNT(MiPushClient.COMMAND_SET_ACCOUNT),
    COMMAND_UNSET_ACCOUNT(MiPushClient.COMMAND_UNSET_ACCOUNT),
    COMMAND_SUBSCRIBE_TOPIC(MiPushClient.COMMAND_SUBSCRIBE_TOPIC),
    COMMAND_UNSUBSCRIBE_TOPIC(MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC),
    COMMAND_SET_ACCEPT_TIME(MiPushClient.COMMAND_SET_ACCEPT_TIME),
    COMMAND_CHK_VDEVID("check-vdeviceid");


    /* renamed from: a */
    public final String f934a;

    EnumC0636fq(String str) {
        this.f934a = str;
    }

    /* renamed from: a */
    public static int m1279a(String str) {
        int i = -1;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        EnumC0636fq[] values = values();
        int length = values.length;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                return i;
            }
            EnumC0636fq enumC0636fq = values[i3];
            if (enumC0636fq.f934a.equals(str)) {
                i = C0630fk.m1241a(enumC0636fq);
            }
            i2 = i3 + 1;
        }
    }
}
