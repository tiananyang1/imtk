package im.imkey.imkeylibrary.utils;

import com.google.common.base.Strings;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/utils/NumericUtil.class */
public class NumericUtil {
    private static final String HEX_PREFIX = "0x";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String beBigEndianHex(String str) {
        return ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? str : reverseHex(str);
    }

    public static String beLittleEndianHex(String str) {
        return ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? str : reverseHex(str);
    }

    public static byte[] bigIntegerToBytesWithZeroPadded(BigInteger bigInteger, int i) {
        int length;
        byte[] bArr = new byte[i];
        byte[] byteArray = bigInteger.toByteArray();
        int i2 = 1;
        if (byteArray[0] == 0) {
            length = byteArray.length - 1;
        } else {
            length = byteArray.length;
            i2 = 0;
        }
        if (length <= i) {
            System.arraycopy(byteArray, i2, bArr, i - length, length);
            return bArr;
        }
        throw new RuntimeException("Input is too large to put in byte array of size " + i);
    }

    public static String bigIntegerToHex(BigInteger bigInteger) {
        return bigInteger.toString(16);
    }

    public static String bigIntegerToHexWithZeroPadded(BigInteger bigInteger, int i) {
        String bigIntegerToHex = bigIntegerToHex(bigInteger);
        int length = bigIntegerToHex.length();
        if (length > i) {
            throw new UnsupportedOperationException("Value " + bigIntegerToHex + "is larger then length " + i);
        }
        if (bigInteger.signum() < 0) {
            throw new UnsupportedOperationException("Value cannot be negative");
        }
        String str = bigIntegerToHex;
        if (length < i) {
            str = Strings.repeat("0", i - length) + bigIntegerToHex;
        }
        return str;
    }

    public static BigInteger bytesToBigInteger(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public static BigInteger bytesToBigInteger(byte[] bArr, int i, int i2) {
        return bytesToBigInteger(Arrays.copyOfRange(bArr, i, i2 + i));
    }

    public static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr.length == 0) {
            return "";
        }
        int length = bArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return sb.toString();
            }
            sb.append(String.format("%02x", Byte.valueOf(bArr[i2])));
            i = i2 + 1;
        }
    }

    public static int bytesToInt(byte[] bArr) {
        return ByteBuffer.wrap(bArr).getInt();
    }

    public static String cleanHexPrefix(String str) {
        String str2 = str;
        if (hasHexPrefix(str)) {
            str2 = str.substring(2);
        }
        return str2;
    }

    public static byte[] generateRandomBytes(int i) {
        byte[] bArr = new byte[i];
        SECURE_RANDOM.nextBytes(bArr);
        return bArr;
    }

    private static boolean hasHexPrefix(String str) {
        boolean z = false;
        if (str.length() > 1) {
            z = false;
            if (str.charAt(0) == '0') {
                z = false;
                if (str.charAt(1) == 'x') {
                    z = true;
                }
            }
        }
        return z;
    }

    public static BigInteger hexToBigInteger(String str) {
        return new BigInteger(cleanHexPrefix(str), 16);
    }

    public static byte[] hexToBytes(String str) {
        byte[] bArr;
        String cleanHexPrefix = cleanHexPrefix(str);
        int length = cleanHexPrefix.length();
        int i = 0;
        if (length == 0) {
            return new byte[0];
        }
        if (length % 2 != 0) {
            bArr = new byte[(length / 2) + 1];
            bArr[0] = (byte) Character.digit(cleanHexPrefix.charAt(0), 16);
            i = 1;
        } else {
            bArr = new byte[length / 2];
        }
        while (i < length) {
            int i2 = i + 1;
            bArr[i2 / 2] = (byte) ((Character.digit(cleanHexPrefix.charAt(i), 16) << 4) + Character.digit(cleanHexPrefix.charAt(i2), 16));
            i += 2;
        }
        return bArr;
    }

    public static byte[] hexToBytesLittleEndian(String str) {
        byte[] hexToBytes = hexToBytes(str);
        if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            return hexToBytes;
        }
        int length = hexToBytes.length / 2;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return hexToBytes;
            }
            byte b = hexToBytes[i2];
            hexToBytes[i2] = hexToBytes[(hexToBytes.length - 1) - i2];
            hexToBytes[(hexToBytes.length - 1) - i2] = b;
            i = i2 + 1;
        }
    }

    public static byte[] intToBytes(int i) {
        byte[] array = ByteBuffer.allocate(4).putInt(i).array();
        int length = array.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length && array[i3] == 0; i3++) {
            i2++;
        }
        int i4 = i2;
        if (i2 == 4) {
            i4 = 3;
        }
        return Arrays.copyOfRange(array, i4, array.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:            if (r4.startsWith("0X") != false) goto L10;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isValidHex(java.lang.String r4) {
        /*
            r0 = r4
            if (r0 != 0) goto L6
            r0 = 0
            return r0
        L6:
            r0 = r4
            java.lang.String r1 = "0x"
            boolean r0 = r0.startsWith(r1)
            if (r0 != 0) goto L1a
            r0 = r4
            r5 = r0
            r0 = r4
            java.lang.String r1 = "0X"
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L24
        L1a:
            r0 = r4
            r1 = 2
            r2 = r4
            int r2 = r2.length()
            java.lang.String r0 = r0.substring(r1, r2)
            r5 = r0
        L24:
            r0 = r5
            int r0 = r0.length()
            if (r0 == 0) goto L3d
            r0 = r5
            int r0 = r0.length()
            r1 = 2
            int r0 = r0 % r1
            if (r0 == 0) goto L36
            r0 = 0
            return r0
        L36:
            java.lang.String r0 = "[0-9a-fA-F]+"
            r1 = r5
            boolean r0 = java.util.regex.Pattern.matches(r0, r1)
            return r0
        L3d:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: im.imkey.imkeylibrary.utils.NumericUtil.isValidHex(java.lang.String):boolean");
    }

    public static String prependHexPrefix(String str) {
        String str2 = str;
        if (str.length() > 1) {
            str2 = str;
            if (!hasHexPrefix(str)) {
                str2 = HEX_PREFIX + str;
            }
        }
        return str2;
    }

    public static byte[] reverseBytes(byte[] bArr) {
        int length = bArr.length / 2;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return bArr;
            }
            byte b = bArr[i2];
            bArr[i2] = bArr[(bArr.length - 1) - i2];
            bArr[(bArr.length - 1) - i2] = b;
            i = i2 + 1;
        }
    }

    private static String reverseHex(String str) {
        return bytesToHex(reverseBytes(hexToBytes(str)));
    }
}
