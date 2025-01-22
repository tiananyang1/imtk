package com.xiaomi.push;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.service.C0830bi;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.xiaomi.push.dk */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dk.class */
class C0576dk {

    /* renamed from: a */
    private static String f675a = "/MiPushLog";

    /* renamed from: a */
    private int f676a;

    /* renamed from: a */
    private boolean f679a;

    /* renamed from: b */
    private String f681b;

    /* renamed from: c */
    private String f682c;

    /* renamed from: a */
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat f677a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* renamed from: b */
    private int f680b = 2097152;

    /* renamed from: a */
    private ArrayList<File> f678a = new ArrayList<>();

    /* renamed from: a */
    private void m921a(BufferedReader bufferedReader, BufferedWriter bufferedWriter, Pattern pattern) {
        int i;
        int i2;
        boolean z;
        int i3;
        char[] cArr = new char[4096];
        int read = bufferedReader.read(cArr);
        boolean z2 = false;
        while (true) {
            boolean z3 = z2;
            if (read == -1 || z3) {
                return;
            }
            String str = new String(cArr, 0, read);
            Matcher matcher = pattern.matcher(str);
            int i4 = 0;
            int i5 = 0;
            while (true) {
                i = i5;
                i2 = read;
                z = z3;
                if (i4 >= read) {
                    break;
                }
                i2 = read;
                z = z3;
                if (!matcher.find(i4)) {
                    break;
                }
                int start = matcher.start();
                String substring = str.substring(start, this.f681b.length() + start);
                if (this.f679a) {
                    i3 = i;
                    if (substring.compareTo(this.f682c) > 0) {
                        z = true;
                        i2 = start;
                        break;
                    }
                } else {
                    i3 = i;
                    if (substring.compareTo(this.f681b) >= 0) {
                        this.f679a = true;
                        i3 = start;
                    }
                }
                int indexOf = str.indexOf(10, start);
                if (indexOf == -1) {
                    indexOf = this.f681b.length();
                }
                i4 = start + indexOf;
                i5 = i3;
            }
            if (this.f679a) {
                int i6 = i2 - i;
                this.f676a += i6;
                if (z) {
                    bufferedWriter.write(cArr, i, i6);
                    return;
                } else {
                    bufferedWriter.write(cArr, i, i6);
                    if (this.f676a > this.f680b) {
                        return;
                    }
                }
            }
            read = bufferedReader.read(cArr);
            z2 = z;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.xiaomi.push.dk] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* renamed from: a */
    private void m922a(File file) {
        BufferedWriter bufferedWriter;
        Closeable closeable;
        Closeable closeable2;
        Closeable closeable3;
        String sb;
        Closeable closeable4;
        Pattern compile = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        BufferedWriter bufferedWriter2 = null;
        BufferedWriter bufferedWriter3 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((File) file)));
                closeable = null;
                closeable2 = null;
                Closeable closeable5 = null;
                try {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("model :");
                    sb2.append(Build.MODEL);
                    sb2.append("; os :");
                    sb2.append(Build.VERSION.INCREMENTAL);
                    sb2.append("; uid :");
                    sb2.append(C0830bi.m2643a());
                    sb2.append("; lng :");
                    sb2.append(Locale.getDefault().toString());
                    sb2.append("; sdk :");
                    sb2.append(38);
                    sb2.append("; andver :");
                    sb2.append(Build.VERSION.SDK_INT);
                    sb2.append("\n");
                    bufferedWriter.write(sb2.toString());
                    this.f676a = 0;
                    Iterator<File> it = this.f678a.iterator();
                    file = 0;
                    while (it.hasNext()) {
                        boolean z = file;
                        file = new BufferedReader(new InputStreamReader(new FileInputStream(it.next())));
                        try {
                            m921a(file, bufferedWriter, compile);
                            file.close();
                        } catch (FileNotFoundException e) {
                            closeable3 = file;
                            e = e;
                            bufferedWriter3 = bufferedWriter;
                            e = e;
                            StringBuilder sb3 = new StringBuilder();
                            BufferedWriter bufferedWriter4 = bufferedWriter3;
                            sb3.append("LOG: filter error = ");
                            BufferedWriter bufferedWriter5 = bufferedWriter3;
                            sb3.append(e.getMessage());
                            BufferedWriter bufferedWriter6 = bufferedWriter3;
                            sb = sb3.toString();
                            closeable4 = closeable3;
                            bufferedWriter = bufferedWriter3;
                            file = closeable4;
                            AbstractC0407b.m74c(sb);
                            C0883y.m2858a(bufferedWriter3);
                            C0883y.m2858a(closeable4);
                            return;
                        } catch (IOException e2) {
                            closeable2 = file;
                            e = e2;
                            bufferedWriter2 = bufferedWriter;
                            e = e;
                            StringBuilder sb4 = new StringBuilder();
                            BufferedWriter bufferedWriter7 = bufferedWriter2;
                            sb4.append("LOG: filter error = ");
                            BufferedWriter bufferedWriter8 = bufferedWriter2;
                            sb4.append(e.getMessage());
                            BufferedWriter bufferedWriter9 = bufferedWriter2;
                            sb = sb4.toString();
                            closeable4 = closeable2;
                            bufferedWriter3 = bufferedWriter2;
                            bufferedWriter = bufferedWriter3;
                            file = closeable4;
                            AbstractC0407b.m74c(sb);
                            C0883y.m2858a(bufferedWriter3);
                            C0883y.m2858a(closeable4);
                            return;
                        } catch (Throwable th) {
                            th = th;
                            Closeable closeable6 = file;
                            th = th;
                            closeable = closeable6;
                            C0883y.m2858a(bufferedWriter);
                            C0883y.m2858a(closeable);
                            throw th;
                        }
                    }
                    closeable = file;
                    closeable2 = file;
                    closeable5 = file;
                    bufferedWriter.write(C0568dc.m871a().m892c());
                    C0883y.m2858a(bufferedWriter);
                    C0883y.m2858a(file);
                } catch (FileNotFoundException e3) {
                    e = e3;
                    closeable3 = closeable5;
                } catch (IOException e4) {
                    e = e4;
                } catch (Throwable th2) {
                    th = th2;
                    C0883y.m2858a(bufferedWriter);
                    C0883y.m2858a(closeable);
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                closeable3 = null;
            } catch (IOException e6) {
                e = e6;
                closeable2 = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = null;
                closeable = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: a */
    C0576dk m923a(File file) {
        if (file.exists()) {
            this.f678a.add(file);
        }
        return this;
    }

    /* renamed from: a */
    C0576dk m924a(Date date, Date date2) {
        String format;
        if (date.after(date2)) {
            this.f681b = this.f677a.format(date2);
            format = this.f677a.format(date);
        } else {
            this.f681b = this.f677a.format(date);
            format = this.f677a.format(date2);
        }
        this.f682c = format;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public File m925a(Context context, Date date, Date date2, File file) {
        File file2;
        if ("com.xiaomi.xmsf".equalsIgnoreCase(context.getPackageName())) {
            file2 = context.getFilesDir();
            m923a(new File(file2, "xmsf.log.1"));
            m923a(new File(file2, "xmsf.log"));
        } else {
            file2 = new File(context.getExternalFilesDir(null) + f675a);
            m923a(new File(file2, "log0.txt"));
            m923a(new File(file2, "log1.txt"));
        }
        if (!file2.isDirectory()) {
            return null;
        }
        File file3 = new File(file, date.getTime() + Constants.ACCEPT_TIME_SEPARATOR_SERVER + date2.getTime() + ".zip");
        if (file3.exists()) {
            return null;
        }
        m924a(date, date2);
        long currentTimeMillis = System.currentTimeMillis();
        File file4 = new File(file, "log.txt");
        m922a(file4);
        AbstractC0407b.m74c("LOG: filter cost = " + (System.currentTimeMillis() - currentTimeMillis));
        if (!file4.exists()) {
            return null;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        C0883y.m2860a(file3, file4);
        AbstractC0407b.m74c("LOG: zip cost = " + (System.currentTimeMillis() - currentTimeMillis2));
        file4.delete();
        if (file3.exists()) {
            return file3;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m926a(int i) {
        if (i != 0) {
            this.f680b = i;
        }
    }
}
