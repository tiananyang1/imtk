package com.xiaomi.push;

import java.io.File;
import java.io.FileFilter;

/* renamed from: com.xiaomi.push.z */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/z.class */
final class C0884z implements FileFilter {
    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
