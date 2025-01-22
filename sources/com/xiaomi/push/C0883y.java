package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* renamed from: com.xiaomi.push.y */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/y.class */
public class C0883y {

    /* renamed from: a */
    public static final String[] f2739a = {"jpg", "png", "bmp", "gif", "webp"};

    /* renamed from: a */
    public static String m2857a(File file) {
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        StringWriter stringWriter = new StringWriter();
        try {
            inputStreamReader2 = new InputStreamReader(new BufferedInputStream(new FileInputStream(file)));
            inputStreamReader = inputStreamReader2;
        } catch (IOException e) {
            e = e;
            inputStreamReader2 = null;
        } catch (Throwable th) {
            th = th;
            inputStreamReader = null;
            m2858a(inputStreamReader);
            m2858a(stringWriter);
            throw th;
        }
        try {
            try {
                char[] cArr = new char[2048];
                while (true) {
                    int read = inputStreamReader2.read(cArr);
                    if (read == -1) {
                        String stringWriter2 = stringWriter.toString();
                        m2858a(inputStreamReader2);
                        m2858a(stringWriter);
                        return stringWriter2;
                    }
                    stringWriter.write(cArr, 0, read);
                }
            } catch (Throwable th2) {
                th = th2;
                m2858a(inputStreamReader);
                m2858a(stringWriter);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            StringBuilder sb = new StringBuilder();
            InputStreamReader inputStreamReader3 = inputStreamReader2;
            sb.append("read file :");
            InputStreamReader inputStreamReader4 = inputStreamReader2;
            sb.append(file.getAbsolutePath());
            InputStreamReader inputStreamReader5 = inputStreamReader2;
            sb.append(" failure :");
            InputStreamReader inputStreamReader6 = inputStreamReader2;
            sb.append(e.getMessage());
            inputStreamReader = inputStreamReader2;
            AbstractC0407b.m74c(sb.toString());
            m2858a(inputStreamReader2);
            m2858a(stringWriter);
            return null;
        }
    }

    /* renamed from: a */
    public static void m2858a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public static void m2859a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= listFiles.length) {
                    break;
                }
                m2859a(listFiles[i2]);
                i = i2 + 1;
            }
        } else if (!file.exists()) {
            return;
        }
        file.delete();
    }

    /* renamed from: a */
    public static void m2860a(File file, File file2) {
        ZipOutputStream zipOutputStream;
        ZipOutputStream zipOutputStream2 = null;
        try {
            try {
                zipOutputStream = new ZipOutputStream(new FileOutputStream(file, false));
                try {
                    m2862a(zipOutputStream, file2, null, null);
                    m2858a(zipOutputStream);
                } catch (FileNotFoundException e) {
                    m2858a(zipOutputStream);
                } catch (IOException e2) {
                    e = e2;
                    StringBuilder sb = new StringBuilder();
                    ZipOutputStream zipOutputStream3 = zipOutputStream;
                    sb.append("zip file failure + ");
                    ZipOutputStream zipOutputStream4 = zipOutputStream;
                    sb.append(e.getMessage());
                    zipOutputStream2 = zipOutputStream;
                    AbstractC0407b.m70a(sb.toString());
                    m2858a(zipOutputStream);
                } catch (Throwable th) {
                    zipOutputStream2 = zipOutputStream;
                    th = th;
                    m2858a(zipOutputStream2);
                    throw th;
                }
            } catch (FileNotFoundException e3) {
                zipOutputStream = null;
                m2858a(zipOutputStream);
            } catch (IOException e4) {
                e = e4;
                zipOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public static void m2861a(File file, String str) {
        BufferedWriter bufferedWriter;
        if (!file.exists()) {
            AbstractC0407b.m74c("mkdir " + file.getAbsolutePath());
            file.getParentFile().mkdirs();
        }
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                BufferedWriter bufferedWriter3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                try {
                    bufferedWriter3.write(str);
                    m2858a(bufferedWriter3);
                } catch (IOException e) {
                    bufferedWriter = bufferedWriter3;
                    e = e;
                    StringBuilder sb = new StringBuilder();
                    BufferedWriter bufferedWriter4 = bufferedWriter;
                    sb.append("write file :");
                    BufferedWriter bufferedWriter5 = bufferedWriter;
                    sb.append(file.getAbsolutePath());
                    BufferedWriter bufferedWriter6 = bufferedWriter;
                    sb.append(" failure :");
                    BufferedWriter bufferedWriter7 = bufferedWriter;
                    sb.append(e.getMessage());
                    BufferedWriter bufferedWriter8 = bufferedWriter;
                    AbstractC0407b.m74c(sb.toString());
                    m2858a(bufferedWriter);
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter2 = bufferedWriter3;
                    m2858a(bufferedWriter2);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                bufferedWriter = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public static void m2862a(ZipOutputStream zipOutputStream, File file, String str, FileFilter fileFilter) {
        FileInputStream fileInputStream;
        ZipEntry zipEntry;
        FileInputStream fileInputStream2;
        String sb;
        String str2 = str;
        if (str == null) {
            str2 = "";
        }
        FileInputStream fileInputStream3 = null;
        try {
            try {
                if (file.isDirectory()) {
                    File[] listFiles = fileFilter != null ? file.listFiles(fileFilter) : file.listFiles();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    sb2.append(File.separator);
                    zipOutputStream.putNextEntry(new ZipEntry(sb2.toString()));
                    if (TextUtils.isEmpty(str2)) {
                        sb = "";
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str2);
                        sb3.append(File.separator);
                        sb = sb3.toString();
                    }
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= listFiles.length) {
                            break;
                        }
                        File file2 = listFiles[i2];
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(sb);
                        sb4.append(listFiles[i2].getName());
                        m2862a(zipOutputStream, file2, sb4.toString(), null);
                        i = i2 + 1;
                    }
                    File[] listFiles2 = file.listFiles(new C0884z());
                    if (listFiles2 != null) {
                        int length = listFiles2.length;
                        int i3 = 0;
                        while (true) {
                            int i4 = i3;
                            if (i4 >= length) {
                                break;
                            }
                            File file3 = listFiles2[i4];
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(sb);
                            sb5.append(File.separator);
                            sb5.append(file3.getName());
                            m2862a(zipOutputStream, file3, sb5.toString(), fileFilter);
                            i3 = i4 + 1;
                        }
                    }
                    fileInputStream2 = null;
                } else {
                    if (TextUtils.isEmpty(str2)) {
                        Date date = new Date();
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append(String.valueOf(date.getTime()));
                        sb6.append(".txt");
                        zipEntry = new ZipEntry(sb6.toString());
                    } else {
                        zipEntry = new ZipEntry(str2);
                    }
                    zipOutputStream.putNextEntry(zipEntry);
                    FileInputStream fileInputStream4 = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream4.read(bArr);
                            fileInputStream2 = fileInputStream4;
                            if (read == -1) {
                                break;
                            } else {
                                zipOutputStream.write(bArr, 0, read);
                            }
                        }
                    } catch (IOException e) {
                        fileInputStream = fileInputStream4;
                        e = e;
                        StringBuilder sb7 = new StringBuilder();
                        FileInputStream fileInputStream5 = fileInputStream;
                        sb7.append("zipFiction failed with exception:");
                        FileInputStream fileInputStream6 = fileInputStream;
                        sb7.append(e.toString());
                        FileInputStream fileInputStream7 = fileInputStream;
                        AbstractC0407b.m75d(sb7.toString());
                        m2858a(fileInputStream);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream3 = fileInputStream4;
                        m2858a(fileInputStream3);
                        throw th;
                    }
                }
                m2858a(fileInputStream2);
            } catch (IOException e2) {
                e = e2;
                fileInputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public static boolean m2863a(File file) {
        try {
            if (file.isDirectory()) {
                return false;
            }
            if (file.exists()) {
                return true;
            }
            File parentFile = file.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                return file.createNewFile();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static byte[] m2864a(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception e) {
            return bArr;
        }
    }

    /* renamed from: b */
    public static void m2865b(File file, File file2) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            return;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read < 0) {
                        fileInputStream.close();
                        fileOutputStream.close();
                        return;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            fileOutputStream = null;
        }
    }
}
