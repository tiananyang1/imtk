package im.imkey.imkeylibrary.core.wallet.transaction;

import com.subgraph.orchid.Cell;
import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.wallet.Wallet;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/EOSSign.class */
public class EOSSign {
    private static String calComprsPub(String str) {
        String substring = str.substring(2, 66);
        return (!new BigInteger(str.substring(66, Cell.AUTH_CHALLENGE), 16).mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO) ? "03" : "02") + substring;
    }

    private static byte calV(byte[] bArr, ECKey.ECDSASignature eCDSASignature, String str) {
        int i;
        ECKey fromPublicOnly = ECKey.fromPublicOnly(NumericUtil.hexToBytes(str.substring(0, Cell.AUTH_CHALLENGE)));
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= 4) {
                i = -1;
                break;
            }
            ECKey recoverFromSignature = ECKey.recoverFromSignature(i, eCDSASignature, Sha256Hash.wrap(bArr), false);
            if (recoverFromSignature != null && recoverFromSignature.getPubKeyPoint().equals(fromPublicOnly.getPubKeyPoint())) {
                break;
            }
            i2 = i + 1;
        }
        if (i != -1) {
            return (byte) (i + 27 + 4);
        }
        throw new RuntimeException("Could not construct a recoverable key. This should never happen.");
    }

    private static ECKey.ECDSASignature eosSign(byte[] bArr, BigInteger bigInteger) {
        EOSECDSASigner eOSECDSASigner = new EOSECDSASigner(new MyHMacDSAKCalculator(new SHA256Digest()));
        eOSECDSASigner.init(true, new ECPrivateKeyParameters(bigInteger, ECKey.CURVE));
        BigInteger[] generateSignature = eOSECDSASigner.generateSignature(bArr);
        return new ECKey.ECDSASignature(generateSignature[0], generateSignature[1]).toCanonicalised();
    }

    private static String selectApplet() {
        return Ble.getInstance().sendApdu(Apdu.select(Applet.EOS_AID));
    }

    private static String serialEOSSignature(byte[] bArr) {
        byte[] concat = ByteUtil.concat(bArr, "K1".getBytes());
        RIPEMD160Digest rIPEMD160Digest = new RIPEMD160Digest();
        rIPEMD160Digest.update(concat, 0, concat.length);
        byte[] bArr2 = new byte[20];
        rIPEMD160Digest.doFinal(bArr2, 0);
        return "SIG_K1_" + Base58.encode(ByteUtil.concat(bArr, Arrays.copyOfRange(bArr2, 0, 4)));
    }

    @Deprecated
    public static String sign(byte[] bArr, String str) {
        SignatureData signAsRecoverable = signAsRecoverable(bArr, EOSKey.fromWIF(str).getECKey());
        return serialEOSSignature(ByteUtil.concat(ByteUtil.concat(NumericUtil.intToBytes(signAsRecoverable.getV()), signAsRecoverable.getR()), signAsRecoverable.getS()));
    }

    public static String sign(byte[] bArr, String str, byte[] bArr2) {
        byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(bArr)));
        Iterator<String> it = Apdu.eosPrepare(NumericUtil.bytesToHex(ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), bArr))).iterator();
        String str2 = "";
        while (it.hasNext()) {
            str2 = Ble.getInstance().sendApdu(it.next(), 120);
            Apdu.checkResponse(str2);
        }
        if (!EOSKey.getPublicKeyApdu(NumericUtil.hexToBytes(calComprsPub(str2))).equals(str)) {
            throw new ImkeyException(Messages.IMKEY_PUBLICKEY_MISMATCH_WITH_PATH);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            String sendApdu = Ble.getInstance().sendApdu(Apdu.eosTxSign(i2));
            Apdu.checkResponse(sendApdu);
            String substring = sendApdu.substring(2, 66);
            String substring2 = sendApdu.substring(66, Cell.AUTH_CHALLENGE);
            byte[] hexToBytes = NumericUtil.hexToBytes(substring);
            ECKey.ECDSASignature canonicalised = new ECKey.ECDSASignature(new BigInteger(hexToBytes), new BigInteger(NumericUtil.hexToBytes(substring2))).toCanonicalised();
            byte[] encodeToDER = canonicalised.encodeToDER();
            byte calV = calV(bArr2, canonicalised, str2);
            byte b = encodeToDER[3];
            byte b2 = encodeToDER[b + 5];
            if (b == 32 && b2 == 32) {
                return serialEOSSignature(ByteUtil.concat(ByteUtil.concat(new byte[]{calV}, hexToBytes), NumericUtil.bigIntegerToBytesWithZeroPadded(canonicalised.s, 32)));
            }
            i = i2 + 1;
        }
    }

    public static String sign(byte[] bArr, byte[] bArr2) {
        SignatureData signAsRecoverable = signAsRecoverable(bArr, EOSKey.fromPrivate(bArr2).getECKey());
        return serialEOSSignature(ByteUtil.concat(ByteUtil.concat(NumericUtil.intToBytes(signAsRecoverable.getV()), signAsRecoverable.getR()), signAsRecoverable.getS()));
    }

    private static SignatureData signAsRecoverable(byte[] bArr, ECKey eCKey) {
        int i;
        ECKey.ECDSASignature eosSign = eosSign(bArr, eCKey.getPrivKey());
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= 4) {
                i = -1;
                break;
            }
            ECKey recoverFromSignature = ECKey.recoverFromSignature(i, eosSign, Sha256Hash.wrap(bArr), false);
            if (recoverFromSignature != null && recoverFromSignature.getPubKeyPoint().equals(eCKey.getPubKeyPoint())) {
                break;
            }
            i2 = i + 1;
        }
        if (i != -1) {
            return new SignatureData((byte) (i + 27 + 4), NumericUtil.bigIntegerToBytesWithZeroPadded(eosSign.r, 32), NumericUtil.bigIntegerToBytesWithZeroPadded(eosSign.s, 32));
        }
        throw new ImkeyException("Could not construct a recoverable key. This should never happen.");
    }

    public static String signMessage(byte[] bArr, String str, byte[] bArr2) {
        selectApplet();
        ByteUtil.byteArrayToHexString(Sha256Hash.hash(bArr));
        byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(bArr)));
        Iterator<String> it = Apdu.eosMsgPrepare(NumericUtil.bytesToHex(ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), bArr))).iterator();
        String str2 = "";
        while (it.hasNext()) {
            str2 = Ble.getInstance().sendApdu(it.next(), 120);
            Apdu.checkResponse(str2);
        }
        if (!EOSKey.getPublicKeyApdu(NumericUtil.hexToBytes(calComprsPub(str2))).equals(str)) {
            throw new ImkeyException(Messages.IMKEY_PUBLICKEY_MISMATCH_WITH_PATH);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            String sendApdu = Ble.getInstance().sendApdu(Apdu.eosMsgSign(i2));
            Apdu.checkResponse(sendApdu);
            String substring = sendApdu.substring(2, 66);
            String substring2 = sendApdu.substring(66, Cell.AUTH_CHALLENGE);
            byte[] hexToBytes = NumericUtil.hexToBytes(substring);
            ECKey.ECDSASignature canonicalised = new ECKey.ECDSASignature(new BigInteger(hexToBytes), new BigInteger(NumericUtil.hexToBytes(substring2))).toCanonicalised();
            byte[] encodeToDER = canonicalised.encodeToDER();
            byte calV = calV(bArr2, canonicalised, str2);
            byte b = encodeToDER[3];
            byte b2 = encodeToDER[b + 5];
            if (b == 32 && b2 == 32) {
                return serialEOSSignature(ByteUtil.concat(ByteUtil.concat(new byte[]{calV}, hexToBytes), NumericUtil.bigIntegerToBytesWithZeroPadded(canonicalised.s, 32)));
            }
            i = i2 + 1;
        }
    }
}
