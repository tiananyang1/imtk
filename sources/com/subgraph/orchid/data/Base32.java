package com.subgraph.orchid.data;

import com.subgraph.orchid.TorException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/Base32.class */
public class Base32 {
    private static final String BASE32_CHARS = "abcdefghijklmnopqrstuvwxyz234567";

    public static byte[] base32Decode(String str) {
        int[] stringToIntVector = stringToIntVector(str);
        int length = str.length() * 5;
        if (length % 8 != 0) {
            throw new TorException("Base32 decoded array must be a muliple of 8 bits");
        }
        int i = length / 8;
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i2 / 5;
            bArr[i3] = (byte) decodeByte(i2, stringToIntVector[i4], stringToIntVector[i4 + 1], stringToIntVector[i4 + 2]);
            i2 += 8;
        }
        return bArr;
    }

    public static String base32Encode(byte[] bArr) {
        return base32Encode(bArr, 0, bArr.length);
    }

    public static String base32Encode(byte[] bArr, int i, int i2) {
        int i3 = i2 * 8;
        if (i3 % 5 != 0) {
            throw new TorException("Base32 input length must be a multiple of 5 bits");
        }
        int i4 = i3 / 5;
        StringBuffer stringBuffer = new StringBuffer();
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i5 >= i4) {
                return stringBuffer.toString();
            }
            int i8 = i7 / 8;
            int i9 = (bArr[i8] & 255) << 8;
            int i10 = i7 + 5;
            int i11 = i9;
            if (i10 < i3) {
                i11 = i9 + (bArr[i8 + 1] & 255);
            }
            stringBuffer.append(BASE32_CHARS.charAt((i11 >> (11 - (i7 % 8))) & 31));
            i5++;
            i6 = i10;
        }
    }

    private static int decodeByte(int i, int i2, int i3, int i4) {
        int m60ls;
        int m61rs;
        int i5 = i % 40;
        if (i5 == 0) {
            m60ls = m60ls(i2, 3);
            m61rs = m61rs(i3, 2);
        } else if (i5 == 8) {
            m60ls = m60ls(i2, 6) + m60ls(i3, 1);
            m61rs = m61rs(i4, 4);
        } else if (i5 == 16) {
            m60ls = m60ls(i2, 4);
            m61rs = m61rs(i3, 1);
        } else if (i5 == 24) {
            m60ls = m60ls(i2, 7) + m60ls(i3, 2);
            m61rs = m61rs(i4, 3);
        } else {
            if (i5 != 32) {
                throw new TorException("Illegal bit offset");
            }
            m60ls = m60ls(i2, 5);
            m61rs = i3 & 255;
        }
        return m60ls + m61rs;
    }

    /* renamed from: ls */
    private static int m60ls(int i, int i2) {
        return (i << i2) & 255;
    }

    /* renamed from: rs */
    private static int m61rs(int i, int i2) {
        return (i >> i2) & 255;
    }

    private static int[] stringToIntVector(String str) {
        int i;
        int[] iArr = new int[str.length() + 1];
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= str.length()) {
                return iArr;
            }
            int charAt = str.charAt(i) & 255;
            if (charAt > 96 && charAt < 123) {
                iArr[i] = charAt - 97;
            } else if (charAt > 49 && charAt < 56) {
                iArr[i] = charAt - 24;
            } else {
                if (charAt <= 64 || charAt >= 91) {
                    break;
                }
                iArr[i] = charAt - 65;
            }
            i2 = i + 1;
        }
        throw new TorException("Illegal character in base32 encoded string: " + str.charAt(i));
    }
}
