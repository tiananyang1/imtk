package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0883y;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/* renamed from: com.xiaomi.mipush.sdk.s */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/s.class */
public class C0476s {

    /* renamed from: a */
    private static volatile C0476s f379a;

    /* renamed from: a */
    private static final Object f380a = new Object();

    /* renamed from: a */
    private Context f381a;

    private C0476s(Context context) {
        this.f381a = context;
    }

    /* renamed from: a */
    public static C0476s m374a(Context context) {
        if (f379a == null) {
            synchronized (C0476s.class) {
                try {
                    if (f379a == null) {
                        f379a = new C0476s(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f379a;
    }

    /* renamed from: a */
    private File m375a(String str) {
        File file = new File(this.f381a.getFilesDir() + "/crash");
        if (!file.exists()) {
            file.mkdirs();
            return null;
        }
        File[] listFiles = file.listFiles();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= listFiles.length) {
                return null;
            }
            if (listFiles[i2].isFile() && listFiles[i2].getName().startsWith(str)) {
                return listFiles[i2];
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public String m376a(File file) {
        if (file == null) {
            return "";
        }
        String[] split = file.getName().split(Constants.COLON_SEPARATOR);
        return split.length != 2 ? "" : split[0];
    }

    /* renamed from: a */
    public ArrayList<File> m377a() {
        ArrayList<File> arrayList = new ArrayList<>();
        File file = new File(this.f381a.getFilesDir() + "/crash");
        if (!file.exists()) {
            file.mkdirs();
            return arrayList;
        }
        File[] listFiles = file.listFiles();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= listFiles.length) {
                return arrayList;
            }
            String[] split = listFiles[i2].getName().split(Constants.COLON_SEPARATOR);
            if (split.length >= 2 && Integer.parseInt(split[1]) >= 1 && listFiles[i2].isFile()) {
                arrayList.add(listFiles[i2]);
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public void m378a(String str, String str2) {
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (f380a) {
            File m375a = m375a(str2);
            if (m375a != null) {
                String[] split = m375a.getName().split(Constants.COLON_SEPARATOR);
                if (split.length < 2) {
                    return;
                }
                m375a.renameTo(new File(this.f381a.getFilesDir() + "/crash/" + str2 + Constants.COLON_SEPARATOR + String.valueOf(Integer.parseInt(split[1]) + 1)));
            } else {
                FileOutputStream fileOutputStream2 = null;
                try {
                    try {
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.f381a.getFilesDir());
                        sb.append("/crash");
                        sb.append("/");
                        sb.append(str2);
                        sb.append(Constants.COLON_SEPARATOR);
                        sb.append("1");
                        FileOutputStream fileOutputStream3 = new FileOutputStream(new File(sb.toString()));
                        try {
                            fileOutputStream3.write(str.getBytes());
                            fileOutputStream3.flush();
                            C0883y.m2858a(fileOutputStream3);
                        } catch (Exception e) {
                            fileOutputStream = fileOutputStream3;
                            e = e;
                            fileOutputStream2 = fileOutputStream;
                            AbstractC0407b.m72a(e);
                            C0883y.m2858a(fileOutputStream);
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream2 = fileOutputStream3;
                            C0883y.m2858a(fileOutputStream2);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = null;
                }
            }
        }
    }
}
