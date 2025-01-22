package com.xiaomi.mipush.sdk;

import android.text.TextUtils;

/* renamed from: com.xiaomi.mipush.sdk.ad */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ad.class */
class C0427ad {

    /* renamed from: a */
    int f276a = 0;

    /* renamed from: a */
    String f277a = "";

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C0427ad)) {
            return false;
        }
        C0427ad c0427ad = (C0427ad) obj;
        return !TextUtils.isEmpty(c0427ad.f277a) && c0427ad.f277a.equals(this.f277a);
    }
}
