package com.nimbusds.jose.util;

import java.math.BigInteger;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/BigIntegerUtils.class */
public class BigIntegerUtils {
    private BigIntegerUtils() {
    }

    public static byte[] toBytesUnsigned(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        if (bigInteger.bitLength() % 8 != 0 && (bigInteger.bitLength() / 8) + 1 == bitLength / 8) {
            return byteArray;
        }
        int i = 0;
        int length = byteArray.length;
        int i2 = length;
        if (bigInteger.bitLength() % 8 == 0) {
            i2 = length - 1;
            i = 1;
        }
        int i3 = bitLength / 8;
        byte[] bArr = new byte[i3];
        System.arraycopy(byteArray, i, bArr, i3 - i2, i2);
        return bArr;
    }
}
