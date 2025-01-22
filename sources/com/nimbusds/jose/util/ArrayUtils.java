package com.nimbusds.jose.util;

import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/ArrayUtils.class */
public class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> T[] concat(T[] tArr, T[]... tArr2) {
        int length = tArr.length;
        int length2 = tArr2.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length2) {
                break;
            }
            length += tArr2[i2].length;
            i = i2 + 1;
        }
        T[] tArr3 = (T[]) Arrays.copyOf(tArr, length);
        int length3 = tArr.length;
        int length4 = tArr2.length;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= length4) {
                return tArr3;
            }
            T[] tArr4 = tArr2[i4];
            System.arraycopy(tArr4, 0, tArr3, length3, tArr4.length);
            length3 += tArr4.length;
            i3 = i4 + 1;
        }
    }
}
