package com.nimbusds.jose.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/ByteUtils.class */
public class ByteUtils {
    public static int bitLength(int i) {
        return i * 8;
    }

    public static int bitLength(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        return bitLength(bArr.length);
    }

    public static int byteLength(int i) {
        return i / 8;
    }

    public static byte[] concat(byte[]... bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length = bArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return byteArrayOutputStream.toByteArray();
                }
                byte[] bArr2 = bArr[i2];
                if (bArr2 != null) {
                    byteArrayOutputStream.write(bArr2);
                }
                i = i2 + 1;
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static int safeBitLength(int i) throws IntegerOverflowException {
        long j = i * 8;
        int i2 = (int) j;
        if (i2 == j) {
            return i2;
        }
        throw new IntegerOverflowException();
    }

    public static int safeBitLength(byte[] bArr) throws IntegerOverflowException {
        if (bArr == null) {
            return 0;
        }
        return safeBitLength(bArr.length);
    }

    public static byte[] subArray(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
        return bArr2;
    }
}
