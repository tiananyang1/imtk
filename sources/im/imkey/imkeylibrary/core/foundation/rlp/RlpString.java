package im.imkey.imkeylibrary.core.foundation.rlp;

import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/rlp/RlpString.class */
public class RlpString implements RlpType {
    private static final byte[] EMPTY = new byte[0];
    private final byte[] value;

    private RlpString(byte[] bArr) {
        this.value = bArr;
    }

    public static RlpString create(byte b) {
        return new RlpString(new byte[]{b});
    }

    public static RlpString create(long j) {
        return create(BigInteger.valueOf(j));
    }

    public static RlpString create(String str) {
        return new RlpString(str.getBytes());
    }

    public static RlpString create(BigInteger bigInteger) {
        if (bigInteger.signum() < 1) {
            return new RlpString(EMPTY);
        }
        byte[] byteArray = bigInteger.toByteArray();
        return byteArray[0] == 0 ? new RlpString(Arrays.copyOfRange(byteArray, 1, byteArray.length)) : new RlpString(byteArray);
    }

    public static RlpString create(byte[] bArr) {
        return new RlpString(bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.value, ((RlpString) obj).value);
    }

    public byte[] getBytes() {
        return this.value;
    }

    public int hashCode() {
        return Arrays.hashCode(this.value);
    }
}
