package com.xiaomi.push;

import android.content.Context;
import android.os.Environment;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* renamed from: com.xiaomi.push.ag */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ag.class */
public class C0491ag {

    /* renamed from: a */
    private static final String f415a = Environment.getExternalStorageDirectory().getPath() + "/mipush/";

    /* renamed from: b */
    private static final String f416b = f415a + "lcfp";

    /* renamed from: c */
    private static final String f417c = f415a + "lcfp.lock";

    /* renamed from: a */
    public static boolean m433a(Context context, String str, long j) {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2;
        FileLock fileLock = null;
        Closeable closeable = null;
        FileLock fileLock2 = null;
        try {
            try {
                File file = new File(f417c);
                C0883y.m2863a(file);
                randomAccessFile2 = new RandomAccessFile(file, "rw");
                fileLock = null;
            } catch (IOException e) {
                e = e;
                randomAccessFile = null;
            } catch (Throwable th) {
                th = th;
                closeable = null;
                if (0 != 0) {
                    try {
                        fileLock2.release();
                    } catch (IOException e2) {
                    }
                }
                C0883y.m2858a(closeable);
                throw th;
            }
            try {
                FileLock lock = randomAccessFile2.getChannel().lock();
                fileLock = lock;
                boolean m434b = m434b(context, str, j);
                if (lock != null && lock.isValid()) {
                    try {
                        lock.release();
                    } catch (IOException e3) {
                    }
                }
                C0883y.m2858a(randomAccessFile2);
                return m434b;
            } catch (IOException e4) {
                e = e4;
                randomAccessFile = randomAccessFile2;
                e.printStackTrace();
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e5) {
                    }
                }
                C0883y.m2858a(randomAccessFile);
                return true;
            }
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0 && fileLock2.isValid()) {
                fileLock2.release();
            }
            C0883y.m2858a(closeable);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01b9 A[EDGE_INSN: B:29:0x01b9->B:30:0x01b9 BREAK  A[LOOP:0: B:18:0x016f->B:28:0x0196], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v66, types: [java.io.Closeable] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m434b(android.content.Context r6, java.lang.String r7, long r8) {
        /*
            Method dump skipped, instructions count: 463
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0491ag.m434b(android.content.Context, java.lang.String, long):boolean");
    }
}
