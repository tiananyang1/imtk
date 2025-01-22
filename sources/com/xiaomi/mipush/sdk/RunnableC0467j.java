package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.j */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/j.class */
public final class RunnableC0467j implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f367a;

    /* renamed from: a */
    final /* synthetic */ EnumC0463f f368a;

    /* renamed from: a */
    final /* synthetic */ String f369a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0467j(String str, Context context, EnumC0463f enumC0463f) {
        this.f369a = str;
        this.f367a = context;
        this.f368a = enumC0463f;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        if (TextUtils.isEmpty(this.f369a)) {
            return;
        }
        String[] split = this.f369a.split(Constants.WAVE_SEPARATOR);
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                str = "";
                break;
            }
            String str2 = split[i2];
            if (!TextUtils.isEmpty(str2) && str2.startsWith("token:")) {
                str = str2.substring(str2.indexOf(Constants.COLON_SEPARATOR) + 1);
                break;
            }
            i = i2 + 1;
        }
        if (TextUtils.isEmpty(str)) {
            AbstractC0407b.m70a("ASSEMBLE_PUSH : receive incorrect token");
            return;
        }
        AbstractC0407b.m70a("ASSEMBLE_PUSH : receive correct token");
        C0466i.m357d(this.f367a, this.f368a, str);
        C0466i.m344a(this.f367a);
    }
}
