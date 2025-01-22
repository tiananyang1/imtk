package com.squareup.okhttp;

import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import okio.ByteString;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/Credentials.class */
public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        try {
            return "Basic " + ByteString.of((str + Constants.COLON_SEPARATOR + str2).getBytes("ISO-8859-1")).base64();
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }
}
