package com.nimbusds.jose.util;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/IntegerUtils.class */
public class IntegerUtils {
    private IntegerUtils() {
    }

    public static byte[] toBytes(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }
}
