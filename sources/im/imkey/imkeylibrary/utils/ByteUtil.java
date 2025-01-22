package im.imkey.imkeylibrary.utils;

import com.sun.jna.Function;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.exception.ImkeyException;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/utils/ByteUtil.class */
public class ByteUtil {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [int] */
    public static String byteArrayToHexString(byte[] bArr) {
        if (bArr == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return sb.toString().toUpperCase(Locale.CHINA);
            }
            byte b = bArr[i2];
            if (b < 0) {
                b += Function.MAX_NARGS;
            }
            if (b < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(b));
            i = i2 + 1;
        }
    }

    public static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + bArr2.length);
        System.arraycopy(bArr2, 0, copyOf, bArr.length, bArr2.length);
        return copyOf;
    }

    public static byte[] hexStringToByteArray(String str) {
        if (str == null || str.length() == 0 || str.length() % 2 != 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return bArr;
            }
            int i3 = i2 + 1;
            try {
                bArr[i2] = (byte) Integer.parseInt(str.substring(i2 * 2, i3 * 2), 16);
            } catch (Exception e) {
                bArr[i2] = 0;
            }
            i = i3;
        }
    }

    public static byte[] longToByteArray(long j) {
        return new byte[]{(byte) (j >>> 56), (byte) (j >>> 48), (byte) (j >>> 40), (byte) (j >>> 32), (byte) (j >>> 24), (byte) (j >>> 16), (byte) (j >>> 8), (byte) j};
    }

    private static byte[] trimLeadingBytes(byte[] bArr, byte b) {
        int i;
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= bArr.length - 1 || bArr[i] != b) {
                break;
            }
            i2 = i + 1;
        }
        return Arrays.copyOfRange(bArr, i, bArr.length);
    }

    public static byte[] trimLeadingZeroes(byte[] bArr) {
        return trimLeadingBytes(bArr, (byte) 0);
    }
}
