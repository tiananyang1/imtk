package com.sensorsdata.analytics.android.sdk;

import android.text.TextUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataHttpURLConnectionHelper.class */
class SensorsDataHttpURLConnectionHelper {
    private static final int HTTP_307 = 307;

    SensorsDataHttpURLConnectionHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getLocation(HttpURLConnection httpURLConnection, String str) throws MalformedURLException {
        if (httpURLConnection == null || TextUtils.isEmpty(str)) {
            return null;
        }
        String headerField = httpURLConnection.getHeaderField(HttpRequest.HEADER_LOCATION);
        String str2 = headerField;
        if (TextUtils.isEmpty(headerField)) {
            str2 = httpURLConnection.getHeaderField("location");
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        String str3 = str2;
        if (!str2.startsWith("http://")) {
            str3 = str2;
            if (!str2.startsWith("https://")) {
                URL url = new URL(str);
                str3 = url.getProtocol() + "://" + url.getHost() + str2;
            }
        }
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean needRedirects(int i) {
        return i == 301 || i == 302 || i == 307;
    }
}
