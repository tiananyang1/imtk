package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.io.File;
import java.util.HashMap;

/* renamed from: com.xiaomi.push.x */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/x.class */
public class C0882x {

    /* renamed from: a */
    private static final HashMap<String, String> f2738a = new HashMap<>();

    static {
        f2738a.put("FFD8FF", "jpg");
        f2738a.put("89504E47", "png");
        f2738a.put("47494638", "gif");
        f2738a.put("474946", "gif");
        f2738a.put("424D", "bmp");
    }

    /* renamed from: a */
    public static long m2856a(File file) {
        long j;
        long length;
        long j2 = 0;
        long j3 = 0;
        try {
            File[] listFiles = file.listFiles();
            int i = 0;
            while (true) {
                int i2 = i;
                j3 = j2;
                j = j2;
                if (i2 >= listFiles.length) {
                    break;
                }
                long j4 = j2;
                if (listFiles[i2].isDirectory()) {
                    long j5 = j2;
                    length = m2856a(listFiles[i2]);
                } else {
                    length = listFiles[i2].length();
                }
                j2 += length;
                i = i2 + 1;
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            j = j3;
        }
        return j;
    }
}
