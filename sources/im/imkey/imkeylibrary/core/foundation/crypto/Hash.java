package im.imkey.imkeylibrary.core.foundation.crypto;

import com.sun.jna.Function;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.NumericUtil;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/Hash.class */
public class Hash {
    public static byte[] generateMac(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length + 16];
        System.arraycopy(bArr, 16, bArr3, 0, 16);
        System.arraycopy(bArr2, 0, bArr3, 16, bArr2.length);
        return keccak256(bArr3);
    }

    public static String keccak256(String str) {
        return NumericUtil.bytesToHex(keccak256(NumericUtil.hexToBytes(str)));
    }

    public static byte[] keccak256(byte[] bArr) {
        return keccak256(bArr, 0, bArr.length);
    }

    private static byte[] keccak256(byte[] bArr, int i, int i2) {
        Keccak keccak = new Keccak(Function.MAX_NARGS);
        keccak.update(bArr, i, i2);
        return keccak.digest().array();
    }

    public static byte[] sha1(byte[] bArr) {
        return sha1(bArr, 0, bArr.length);
    }

    private static byte[] sha1(byte[] bArr, int i, int i2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(bArr, i, i2);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new ImkeyException(Messages.IMKEY_SHA_EXCEPTION);
        }
    }

    public static String sha256(String str) {
        return NumericUtil.bytesToHex(sha256(NumericUtil.hexToBytes(str)));
    }

    public static byte[] sha256(byte[] bArr) {
        return sha256(bArr, 0, bArr.length);
    }

    private static byte[] sha256(byte[] bArr, int i, int i2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE);
            messageDigest.update(bArr, i, i2);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new ImkeyException(Messages.IMKEY_SHA_EXCEPTION);
        }
    }
}
