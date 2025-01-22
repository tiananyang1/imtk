package im.imkey.imkeylibrary.core.foundation.rlp;

import im.imkey.imkeylibrary.utils.ByteUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/rlp/RlpEncoder.class */
public class RlpEncoder {
    private static final int LIST_OFFSET = 192;
    private static final int STRING_OFFSET = 128;

    public static byte[] encode(RlpType rlpType) {
        return rlpType instanceof RlpString ? encodeString((RlpString) rlpType) : encodeList((RlpList) rlpType);
    }

    private static byte[] encode(byte[] bArr, int i) {
        if (bArr.length == 1 && i == 128 && bArr[0] >= 0 && bArr[0] <= Byte.MAX_VALUE) {
            return bArr;
        }
        if (bArr.length <= 55) {
            byte[] bArr2 = new byte[bArr.length + 1];
            bArr2[0] = (byte) (i + bArr.length);
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            return bArr2;
        }
        byte[] minimalByteArray = toMinimalByteArray(bArr.length);
        byte[] bArr3 = new byte[bArr.length + minimalByteArray.length + 1];
        bArr3[0] = (byte) (i + 55 + minimalByteArray.length);
        System.arraycopy(minimalByteArray, 0, bArr3, 1, minimalByteArray.length);
        System.arraycopy(bArr, 0, bArr3, minimalByteArray.length + 1, bArr.length);
        return bArr3;
    }

    private static byte[] encodeList(RlpList rlpList) {
        List<RlpType> values = rlpList.getValues();
        if (values.isEmpty()) {
            return encode(new byte[0], LIST_OFFSET);
        }
        byte[] bArr = new byte[0];
        Iterator<RlpType> it = values.iterator();
        while (it.hasNext()) {
            bArr = ByteUtil.concat(bArr, encode(it.next()));
        }
        return encode(bArr, LIST_OFFSET);
    }

    private static byte[] encodeString(RlpString rlpString) {
        return encode(rlpString.getBytes(), 128);
    }

    private static byte[] toByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    private static byte[] toMinimalByteArray(int i) {
        byte[] byteArray = toByteArray(i);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= byteArray.length) {
                return new byte[0];
            }
            if (byteArray[i3] != 0) {
                return Arrays.copyOfRange(byteArray, i3, byteArray.length);
            }
            i2 = i3 + 1;
        }
    }
}
