package com.xiaomi.push;

import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.xiaomi.push.bg */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bg.class */
final class C0518bg implements FilenameFilter {
    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return (TextUtils.isEmpty(str) || str.toLowerCase().endsWith(".lock")) ? false : true;
    }
}
