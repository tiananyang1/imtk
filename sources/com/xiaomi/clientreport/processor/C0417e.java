package com.xiaomi.clientreport.processor;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.data.C0408a;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.push.C0883y;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.xiaomi.clientreport.processor.e */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/processor/e.class */
public class C0417e {
    /* renamed from: a */
    private static PerfClientReport m107a(PerfClientReport perfClientReport, String str) {
        long[] m115a;
        if (perfClientReport == null || (m115a = m115a(str)) == null) {
            return null;
        }
        perfClientReport.perfCounts = m115a[0];
        perfClientReport.perfLatencies = m115a[1];
        return perfClientReport;
    }

    /* renamed from: a */
    private static PerfClientReport m108a(String str) {
        PerfClientReport perfClientReport;
        PerfClientReport perfClientReport2 = null;
        try {
            String[] m116a = m116a(str);
            perfClientReport = null;
            if (m116a != null) {
                perfClientReport = null;
                if (m116a.length >= 4) {
                    perfClientReport = null;
                    if (!TextUtils.isEmpty(m116a[0])) {
                        perfClientReport = null;
                        if (!TextUtils.isEmpty(m116a[1])) {
                            perfClientReport = null;
                            if (!TextUtils.isEmpty(m116a[2])) {
                                perfClientReport = null;
                                if (!TextUtils.isEmpty(m116a[3])) {
                                    PerfClientReport blankInstance = PerfClientReport.getBlankInstance();
                                    blankInstance.production = Integer.parseInt(m116a[0]);
                                    blankInstance.clientInterfaceId = m116a[1];
                                    blankInstance.reportType = Integer.parseInt(m116a[2]);
                                    perfClientReport2 = blankInstance;
                                    blankInstance.code = Integer.parseInt(m116a[3]);
                                    return blankInstance;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            AbstractC0407b.m74c("parse per key error");
            perfClientReport = perfClientReport2;
        }
        return perfClientReport;
    }

    /* renamed from: a */
    public static String m109a(PerfClientReport perfClientReport) {
        return perfClientReport.production + "#" + perfClientReport.clientInterfaceId + "#" + perfClientReport.reportType + "#" + perfClientReport.code;
    }

    /* renamed from: a */
    private static HashMap<String, String> m110a(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        HashMap<String, String> hashMap = new HashMap<>();
        if (!TextUtils.isEmpty(str) && new File(str).exists()) {
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(str));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                C0883y.m2858a(bufferedReader);
                                return hashMap;
                            }
                            String[] split = readLine.split("%%%");
                            if (split.length >= 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
                                hashMap.put(split[0], split[1]);
                            }
                        } catch (Exception e) {
                            e = e;
                            AbstractC0407b.m72a(e);
                            C0883y.m2858a(bufferedReader);
                            return hashMap;
                        } catch (Throwable th2) {
                            th = th2;
                            C0883y.m2858a(bufferedReader);
                            throw th;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = null;
                }
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
            }
        }
        return hashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x016f, code lost:            if (r6 != null) goto L69;     */
    /* JADX WARN: Removed duplicated region for block: B:77:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b0  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> m111a(android.content.Context r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.clientreport.processor.C0417e.m111a(android.content.Context, java.lang.String):java.util.List");
    }

    /* renamed from: a */
    private static void m112a(String str, HashMap<String, String> hashMap) {
        BufferedWriter bufferedWriter;
        BufferedWriter bufferedWriter2;
        if (TextUtils.isEmpty(str) || hashMap == null || hashMap.size() == 0) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file));
            } catch (Exception e) {
                e = e;
                bufferedWriter = null;
            } catch (Throwable th) {
                th = th;
                C0883y.m2858a((Closeable) null);
                throw th;
            }
            try {
                Iterator<String> it = hashMap.keySet().iterator();
                while (true) {
                    bufferedWriter2 = bufferedWriter;
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    String str2 = hashMap.get(next);
                    StringBuilder sb = new StringBuilder();
                    sb.append(next);
                    sb.append("%%%");
                    sb.append(str2);
                    bufferedWriter.write(sb.toString());
                    bufferedWriter.newLine();
                }
            } catch (Exception e2) {
                e = e2;
                AbstractC0407b.m72a(e);
                bufferedWriter2 = bufferedWriter;
                C0883y.m2858a(bufferedWriter2);
            }
            C0883y.m2858a(bufferedWriter2);
        } catch (Throwable th2) {
            th = th2;
            C0883y.m2858a((Closeable) null);
            throw th;
        }
    }

    /* renamed from: a */
    public static void m113a(String str, C0408a[] c0408aArr) {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2;
        RandomAccessFile randomAccessFile3;
        if (c0408aArr == null || c0408aArr.length <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        Closeable closeable = null;
        FileLock fileLock = null;
        FileLock fileLock2 = null;
        try {
            try {
                File file = new File(str + ".lock");
                C0883y.m2863a(file);
                randomAccessFile3 = new RandomAccessFile(file, "rw");
                fileLock2 = null;
            } catch (Throwable th) {
                th = th;
                closeable = null;
                if (0 != 0) {
                    try {
                        fileLock.release();
                    } catch (IOException e) {
                        AbstractC0407b.m72a(e);
                    }
                }
                C0883y.m2858a(closeable);
                throw th;
            }
            try {
                FileLock lock = randomAccessFile3.getChannel().lock();
                HashMap<String, String> m110a = m110a(str);
                int length = c0408aArr.length;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= length) {
                        break;
                    }
                    C0408a c0408a = c0408aArr[i2];
                    if (c0408a != null) {
                        String m109a = m109a((PerfClientReport) c0408a);
                        long j = ((PerfClientReport) c0408a).perfCounts;
                        long j2 = ((PerfClientReport) c0408a).perfLatencies;
                        if (!TextUtils.isEmpty(m109a) && j > 0 && j2 >= 0) {
                            m114a(m110a, m109a, j, j2);
                        }
                    }
                    i = i2 + 1;
                }
                fileLock2 = lock;
                m112a(str, m110a);
                randomAccessFile2 = randomAccessFile3;
                if (lock != null) {
                    randomAccessFile2 = randomAccessFile3;
                    if (lock.isValid()) {
                        try {
                            lock.release();
                            randomAccessFile2 = randomAccessFile3;
                        } catch (IOException e2) {
                            e = e2;
                            randomAccessFile = randomAccessFile3;
                            AbstractC0407b.m72a(e);
                            randomAccessFile2 = randomAccessFile;
                            C0883y.m2858a(randomAccessFile2);
                        }
                    }
                }
            } catch (Throwable th2) {
                randomAccessFile = randomAccessFile3;
                AbstractC0407b.m74c("failed to write perf to file ");
                randomAccessFile2 = randomAccessFile;
                if (fileLock2 != null) {
                    randomAccessFile2 = randomAccessFile;
                    if (fileLock2.isValid()) {
                        try {
                            fileLock2.release();
                            randomAccessFile2 = randomAccessFile;
                        } catch (IOException e3) {
                            e = e3;
                            AbstractC0407b.m72a(e);
                            randomAccessFile2 = randomAccessFile;
                            C0883y.m2858a(randomAccessFile2);
                        }
                    }
                }
                C0883y.m2858a(randomAccessFile2);
            }
            C0883y.m2858a(randomAccessFile2);
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0 && fileLock.isValid()) {
                fileLock.release();
            }
            C0883y.m2858a(closeable);
            throw th;
        }
    }

    /* renamed from: a */
    private static void m114a(HashMap<String, String> hashMap, String str, long j, long j2) {
        StringBuilder sb;
        String str2 = hashMap.get(str);
        if (TextUtils.isEmpty(str2)) {
            sb = new StringBuilder();
        } else {
            long[] m115a = m115a(str2);
            if (m115a == null || m115a[0] <= 0 || m115a[1] < 0) {
                sb = new StringBuilder();
            } else {
                j += m115a[0];
                j2 += m115a[1];
                sb = new StringBuilder();
            }
        }
        sb.append(j);
        sb.append("#");
        sb.append(j2);
        hashMap.put(str, sb.toString());
    }

    /* renamed from: a */
    protected static long[] m115a(String str) {
        long[] jArr = new long[2];
        try {
            String[] split = str.split("#");
            if (split.length >= 2) {
                jArr[0] = Long.parseLong(split[0].trim());
                jArr[1] = Long.parseLong(split[1].trim());
            }
            return jArr;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return null;
        }
    }

    /* renamed from: a */
    private static String[] m116a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split("#");
    }
}
