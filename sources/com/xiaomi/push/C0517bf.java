package com.xiaomi.push;

import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.xiaomi.push.bf */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bf.class */
final class C0517bf implements FilenameFilter {
    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return (TextUtils.isEmpty(str) || str.toLowerCase().endsWith(".lock")) ? false : true;
    }
}
