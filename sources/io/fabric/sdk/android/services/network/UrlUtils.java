package io.fabric.sdk.android.services.network;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.TreeMap;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/network/UrlUtils.class */
public final class UrlUtils {
    public static final String UTF8 = "UTF8";

    private UrlUtils() {
    }

    public static TreeMap<String, String> getQueryParams(String str, boolean z) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        if (str == null) {
            return treeMap;
        }
        String[] split = str.split("&");
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return treeMap;
            }
            String[] split2 = split[i2].split("=");
            if (split2.length == 2) {
                if (z) {
                    treeMap.put(urlDecode(split2[0]), urlDecode(split2[1]));
                } else {
                    treeMap.put(split2[0], split2[1]);
                }
            } else if (!TextUtils.isEmpty(split2[0])) {
                if (z) {
                    treeMap.put(urlDecode(split2[0]), "");
                } else {
                    treeMap.put(split2[0], "");
                }
            }
            i = i2 + 1;
        }
    }

    public static TreeMap<String, String> getQueryParams(URI uri, boolean z) {
        return getQueryParams(uri.getRawQuery(), z);
    }

    public static String percentEncode(String str) {
        int i;
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String urlEncode = urlEncode(str);
        int length = urlEncode.length();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                return sb.toString();
            }
            char charAt = urlEncode.charAt(i3);
            if (charAt == '*') {
                sb.append("%2A");
            } else if (charAt == '+') {
                sb.append("%20");
            } else if (charAt == '%' && (i = i3 + 2) < length && urlEncode.charAt(i3 + 1) == '7' && urlEncode.charAt(i) == 'E') {
                sb.append('~');
                i3 = i;
            } else {
                sb.append(charAt);
            }
            i2 = i3 + 1;
        }
    }

    public static String urlDecode(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLDecoder.decode(str, UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String urlEncode(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
