package com.xiaomi.mipush.sdk;

import android.net.wifi.ScanResult;
import java.util.Comparator;

/* renamed from: com.xiaomi.mipush.sdk.v */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/v.class */
final class C0479v implements Comparator<ScanResult> {
    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult2.level - scanResult.level;
    }
}
