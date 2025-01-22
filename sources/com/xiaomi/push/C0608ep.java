package com.xiaomi.push;

import android.net.wifi.ScanResult;
import java.util.Comparator;

/* renamed from: com.xiaomi.push.ep */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ep.class */
class C0608ep implements Comparator<ScanResult> {

    /* renamed from: a */
    final /* synthetic */ C0607eo f748a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0608ep(C0607eo c0607eo) {
        this.f748a = c0607eo;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult2.level - scanResult.level;
    }
}
