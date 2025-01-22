package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.xiaomi.push.w */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/w.class */
public final class C0881w {

    /* renamed from: a */
    private static final Set<String> f2733a = Collections.synchronizedSet(new HashSet());

    /* renamed from: a */
    private Context f2734a;

    /* renamed from: a */
    private RandomAccessFile f2735a;

    /* renamed from: a */
    private String f2736a;

    /* renamed from: a */
    private FileLock f2737a;

    private C0881w(Context context) {
        this.f2734a = context;
    }

    /* renamed from: a */
    public static C0881w m2854a(Context context, File file) {
        AbstractC0407b.m74c("Locking: " + file.getAbsolutePath());
        String str = file.getAbsolutePath() + ".LOCK";
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.getParentFile().mkdirs();
            file2.createNewFile();
        }
        if (!f2733a.add(str)) {
            throw new IOException("abtain lock failure");
        }
        C0881w c0881w = new C0881w(context);
        c0881w.f2736a = str;
        try {
            c0881w.f2735a = new RandomAccessFile(file2, "rw");
            c0881w.f2737a = c0881w.f2735a.getChannel().lock();
            AbstractC0407b.m74c("Locked: " + str + " :" + c0881w.f2737a);
            return c0881w;
        } finally {
            if (c0881w.f2737a == null) {
                RandomAccessFile randomAccessFile = c0881w.f2735a;
                if (randomAccessFile != null) {
                    C0883y.m2858a(randomAccessFile);
                }
                f2733a.remove(c0881w.f2736a);
            }
        }
    }

    /* renamed from: a */
    public void m2855a() {
        AbstractC0407b.m74c("unLock: " + this.f2737a);
        FileLock fileLock = this.f2737a;
        if (fileLock != null && fileLock.isValid()) {
            try {
                this.f2737a.release();
            } catch (IOException e) {
            }
            this.f2737a = null;
        }
        RandomAccessFile randomAccessFile = this.f2735a;
        if (randomAccessFile != null) {
            C0883y.m2858a(randomAccessFile);
        }
        f2733a.remove(this.f2736a);
    }
}
