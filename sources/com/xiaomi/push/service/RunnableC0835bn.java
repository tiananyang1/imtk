package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.C0883y;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.bn */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bn.class */
public final class RunnableC0835bn implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f2611a;

    /* renamed from: a */
    final /* synthetic */ C0702ib f2612a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0835bn(Context context, C0702ib c0702ib) {
        this.f2611a = context;
        this.f2612a = c0702ib;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        RandomAccessFile randomAccessFile;
        synchronized (C0834bm.f2610a) {
            FileLock fileLock = null;
            RandomAccessFile randomAccessFile2 = null;
            RandomAccessFile randomAccessFile3 = null;
            try {
                try {
                    File file = new File(this.f2611a.getFilesDir(), "tiny_data.lock");
                    C0883y.m2863a(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    fileLock = null;
                } catch (Exception e) {
                    e = e;
                    randomAccessFile = null;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile3 = null;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.release();
                        } catch (IOException e2) {
                            AbstractC0407b.m72a(e2);
                        }
                    }
                    C0883y.m2858a(randomAccessFile3);
                    throw th;
                }
                try {
                    FileLock lock = randomAccessFile.getChannel().lock();
                    fileLock = lock;
                    randomAccessFile3 = randomAccessFile;
                    C0834bm.m2669c(this.f2611a, this.f2612a);
                    randomAccessFile2 = randomAccessFile;
                    if (lock != null) {
                        randomAccessFile2 = randomAccessFile;
                        if (lock.isValid()) {
                            try {
                                lock.release();
                                randomAccessFile2 = randomAccessFile;
                            } catch (IOException e3) {
                                AbstractC0407b.m72a(e3);
                                randomAccessFile2 = randomAccessFile;
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    randomAccessFile3 = randomAccessFile;
                    AbstractC0407b.m72a(e);
                    randomAccessFile2 = randomAccessFile;
                    if (fileLock != null) {
                        randomAccessFile2 = randomAccessFile;
                        if (fileLock.isValid()) {
                            try {
                                fileLock.release();
                                randomAccessFile2 = randomAccessFile;
                            } catch (IOException e5) {
                                AbstractC0407b.m72a(e5);
                                randomAccessFile2 = randomAccessFile;
                            }
                        }
                    }
                    C0883y.m2858a(randomAccessFile2);
                }
                C0883y.m2858a(randomAccessFile2);
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile2 != null && randomAccessFile2.isValid()) {
                    randomAccessFile2.release();
                }
                C0883y.m2858a(randomAccessFile3);
                throw th;
            }
        }
    }
}
