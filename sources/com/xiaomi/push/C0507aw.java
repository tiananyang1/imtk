package com.xiaomi.push;

import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.xiaomi.push.aw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/aw.class */
public class C0507aw {
    /* renamed from: a */
    public static String m515a(String str) {
        try {
            return String.valueOf(C0506av.m513a(MessageDigest.getInstance("SHA1").digest(str.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | Exception e) {
            AbstractC0407b.m71a("CloudCoder.hash4SHA1 ", e);
            throw new IllegalStateException("failed to SHA1");
        }
    }

    /* renamed from: a */
    public static String m516a(String str, String str2, Map<String, String> map, String str3) {
        if (TextUtils.isEmpty(str3)) {
            throw new InvalidParameterException("security is not nullable");
        }
        ArrayList<String> arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(str.toUpperCase());
        }
        if (str2 != null) {
            arrayList.add(Uri.parse(str2).getEncodedPath());
        }
        boolean z = true;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry entry : new TreeMap(map).entrySet()) {
                arrayList.add(String.format("%s=%s", entry.getKey(), entry.getValue()));
            }
        }
        arrayList.add(str3);
        StringBuilder sb = new StringBuilder();
        for (String str4 : arrayList) {
            if (!z) {
                sb.append('&');
            }
            sb.append(str4);
            z = false;
        }
        return m515a(sb.toString());
    }
}
