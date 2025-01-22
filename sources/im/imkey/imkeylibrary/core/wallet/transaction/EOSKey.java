package im.imkey.imkeylibrary.core.wallet.transaction;

import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.utils.ByteUtil;
import java.util.Arrays;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.VersionedChecksummedBytes;
import org.spongycastle.crypto.digests.RIPEMD160Digest;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/EOSKey.class */
public class EOSKey extends VersionedChecksummedBytes {
    protected EOSKey(int i, byte[] bArr) {
        super(i, bArr);
    }

    protected EOSKey(String str) throws AddressFormatException {
        super(str);
    }

    public static EOSKey fromPrivate(byte[] bArr) {
        return new EOSKey(128, bArr);
    }

    public static EOSKey fromWIF(String str) {
        return new EOSKey(str);
    }

    public static String getPublicKeyApdu(byte[] bArr) {
        RIPEMD160Digest rIPEMD160Digest = new RIPEMD160Digest();
        rIPEMD160Digest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[20];
        rIPEMD160Digest.doFinal(bArr2, 0);
        return Applet.EOS_NAME + Base58.encode(ByteUtil.concat(bArr, Arrays.copyOfRange(bArr2, 0, 4)));
    }

    public static String privateToPublicKey(byte[] bArr) {
        return new EOSKey(128, bArr).getPublicKeyAsHex();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ECKey getECKey() {
        return ECKey.fromPrivate(this.bytes, true);
    }

    public String getPublicKeyAsHex() {
        byte[] pubKey = ECKey.fromPrivate(this.bytes).getPubKey();
        RIPEMD160Digest rIPEMD160Digest = new RIPEMD160Digest();
        rIPEMD160Digest.update(pubKey, 0, pubKey.length);
        byte[] bArr = new byte[20];
        rIPEMD160Digest.doFinal(bArr, 0);
        return Applet.EOS_NAME + Base58.encode(ByteUtil.concat(pubKey, Arrays.copyOfRange(bArr, 0, 4)));
    }
}
