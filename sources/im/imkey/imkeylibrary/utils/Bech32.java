package im.imkey.imkeylibrary.utils;

import com.google.common.base.Preconditions;
import java.util.Locale;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/utils/Bech32.class */
public class Bech32 {
    private static final String CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";
    private static final byte[] CHARSET_REV = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15, -1, 10, 17, 21, 20, 26, 30, 7, 5, -1, -1, -1, -1, -1, -1, -1, 29, -1, 24, 13, 25, 9, 8, 23, -1, 18, 22, 31, 27, 19, -1, 1, 0, 3, 16, 11, 28, 12, 14, 6, 4, 2, -1, -1, -1, -1, -1, -1, 29, -1, 24, 13, 25, 9, 8, 23, -1, 18, 22, 31, 27, 19, -1, 1, 0, 3, 16, 11, 28, 12, 14, 6, 4, 2, -1, -1, -1, -1, -1};

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/utils/Bech32$Bech32Data.class */
    public static class Bech32Data {
        public final byte[] data;
        public final String hrp;

        private Bech32Data(String str, byte[] bArr) {
            this.hrp = str;
            this.data = bArr;
        }
    }

    private static byte[] createChecksum(String str, byte[] bArr) {
        byte[] expandHrp = expandHrp(str);
        byte[] bArr2 = new byte[expandHrp.length + bArr.length + 6];
        System.arraycopy(expandHrp, 0, bArr2, 0, expandHrp.length);
        System.arraycopy(bArr, 0, bArr2, expandHrp.length, bArr.length);
        int polymod = polymod(bArr2);
        byte[] bArr3 = new byte[6];
        for (int i = 0; i < 6; i++) {
            bArr3[i] = (byte) (((polymod ^ 1) >>> ((5 - i) * 5)) & 31);
        }
        return bArr3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00d8, code lost:            throw new org.bitcoinj.core.AddressFormatException(java.lang.String.format("InvalidCharacter %c, %d", java.lang.Character.valueOf(r0), java.lang.Integer.valueOf(r10)));     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static im.imkey.imkeylibrary.utils.Bech32.Bech32Data decode(java.lang.String r8) throws org.bitcoinj.core.AddressFormatException {
        /*
            Method dump skipped, instructions count: 512
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: im.imkey.imkeylibrary.utils.Bech32.decode(java.lang.String):im.imkey.imkeylibrary.utils.Bech32$Bech32Data");
    }

    public static String encode(Bech32Data bech32Data) {
        return encode(bech32Data.hrp, bech32Data.data);
    }

    public static String encode(String str, byte[] bArr) {
        Preconditions.checkArgument(str.length() >= 1, "Human-readable part is too short");
        Preconditions.checkArgument(str.length() <= 83, "Human-readable part is too long");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        byte[] createChecksum = createChecksum(lowerCase, bArr);
        byte[] bArr2 = new byte[bArr.length + createChecksum.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(createChecksum, 0, bArr2, bArr.length, createChecksum.length);
        StringBuilder sb = new StringBuilder(lowerCase.length() + 1 + bArr2.length);
        sb.append(lowerCase);
        sb.append('1');
        for (byte b : bArr2) {
            sb.append(CHARSET.charAt(b));
        }
        return sb.toString();
    }

    private static byte[] expandHrp(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length * 2) + 1];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                bArr[length] = 0;
                return bArr;
            }
            int charAt = str.charAt(i2) & 127;
            bArr[i2] = (byte) ((charAt >>> 5) & 7);
            bArr[i2 + length + 1] = (byte) (charAt & 31);
            i = i2 + 1;
        }
    }

    private static int polymod(byte[] bArr) {
        int length = bArr.length;
        int i = 1;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                return i;
            }
            int i4 = (i >>> 25) & 255;
            int i5 = ((i & 33554431) << 5) ^ (bArr[i3] & 255);
            int i6 = i5;
            if ((i4 & 1) != 0) {
                i6 = i5 ^ 996825010;
            }
            int i7 = i6;
            if ((i4 & 2) != 0) {
                i7 = i6 ^ 642813549;
            }
            int i8 = i7;
            if ((i4 & 4) != 0) {
                i8 = i7 ^ 513874426;
            }
            int i9 = i8;
            if ((i4 & 8) != 0) {
                i9 = i8 ^ 1027748829;
            }
            i = i9;
            if ((i4 & 16) != 0) {
                i = i9 ^ 705979059;
            }
            i2 = i3 + 1;
        }
    }

    private static boolean verifyChecksum(String str, byte[] bArr) {
        byte[] expandHrp = expandHrp(str);
        byte[] bArr2 = new byte[expandHrp.length + bArr.length];
        System.arraycopy(expandHrp, 0, bArr2, 0, expandHrp.length);
        System.arraycopy(bArr, 0, bArr2, expandHrp.length, bArr.length);
        return polymod(bArr2) == 1;
    }
}
