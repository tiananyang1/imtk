package com.subgraph.orchid.misc;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/misc/Utils.class */
public class Utils {
    public static boolean constantTimeArrayEquals(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i += (bArr[i2] & 255) ^ (bArr2[i2] & 255);
        }
        return i == 0;
    }
}
