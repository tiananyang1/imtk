package im.imkey.imkeylibrary.core.wallet.transaction;

import com.google.common.base.Preconditions;
import com.subgraph.orchid.Cell;
import com.subgraph.orchid.encoders.Hex;
import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.core.wallet.Eth;
import im.imkey.imkeylibrary.core.wallet.Wallet;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Iterator;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/EthereumSign.class */
public class EthereumSign {
    private static byte[] dataToBytes(String str) {
        return NumericUtil.isValidHex(str) ? NumericUtil.hexToBytes(str) : str.getBytes(Charset.forName("UTF-8"));
    }

    public static BigInteger ecRecover(String str, String str2) throws SignatureException {
        byte[] dataToBytes = dataToBytes(str);
        String cleanHexPrefix = NumericUtil.cleanHexPrefix(str2);
        return signedMessageToKey(dataToBytes, new SignatureData((byte) Integer.valueOf(cleanHexPrefix.substring(128), 16).intValue(), Hex.decode(cleanHexPrefix.substring(0, 64)), Hex.decode(cleanHexPrefix.substring(64, 128))));
    }

    public static SignatureData signAsRecoverable(byte[] bArr, ECKey eCKey) {
        int i;
        ECKey.ECDSASignature sign = eCKey.sign(Sha256Hash.wrap(bArr));
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= 4) {
                i = -1;
                break;
            }
            ECKey recoverFromSignature = ECKey.recoverFromSignature(i, sign, Sha256Hash.wrap(bArr), false);
            if (recoverFromSignature != null && recoverFromSignature.getPubKeyPoint().equals(eCKey.getPubKeyPoint())) {
                break;
            }
            i2 = i + 1;
        }
        if (i != -1) {
            return new SignatureData((byte) (i + 27), NumericUtil.bigIntegerToBytesWithZeroPadded(sign.r, 32), NumericUtil.bigIntegerToBytesWithZeroPadded(sign.s, 32));
        }
        throw new RuntimeException("Could not construct a recoverable key. This should never happen.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SignatureData signMessage(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        int i;
        byte[] concat = ByteUtil.concat(ByteUtil.concat(new byte[]{1}, ByteUtil.concat(new byte[]{(byte) ((bArr.length & 65280) >> 8), (byte) (bArr.length & 255)}, bArr)), ByteUtil.concat(bArr3, ByteUtil.concat(bArr4, bArr5)));
        byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(concat)));
        Iterator<String> it = Apdu.ethPrepare(NumericUtil.bytesToHex(ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), concat))).iterator();
        while (it.hasNext()) {
            Apdu.checkResponse(Ble.getInstance().sendApdu(it.next(), 120));
        }
        String str = new String(bArr2);
        String ethXpubHex = new Eth().getEthXpubHex(str, false);
        if (!Arrays.equals(bArr6, Wallet.checkedEthAddress(Wallet.publicKeyToAddress(NumericUtil.hexToBytes(ethXpubHex.substring(2, Cell.AUTH_CHALLENGE)))).getBytes())) {
            throw new ImkeyException(Messages.IMKEY_ADDRESS_MISMATCH_WITH_PATH);
        }
        String sendApdu = Ble.getInstance().sendApdu(Apdu.ethSign(str));
        Apdu.checkResponse(sendApdu);
        String substring = sendApdu.substring(2, 66);
        String substring2 = sendApdu.substring(66, Cell.AUTH_CHALLENGE);
        ECKey fromPublicOnly = ECKey.fromPublicOnly(NumericUtil.hexToBytes(ethXpubHex.substring(0, Cell.AUTH_CHALLENGE)));
        ECKey.ECDSASignature canonicalised = new ECKey.ECDSASignature(new BigInteger(substring, 16), new BigInteger(substring2, 16)).toCanonicalised();
        byte[] keccak256 = Hash.keccak256(bArr);
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= 4) {
                i = -1;
                break;
            }
            ECKey recoverFromSignature = ECKey.recoverFromSignature(i, canonicalised, Sha256Hash.wrap(keccak256), false);
            if (recoverFromSignature != null && recoverFromSignature.getPubKeyPoint().equals(fromPublicOnly.getPubKeyPoint())) {
                break;
            }
            i2 = i + 1;
        }
        if (i != -1) {
            return new SignatureData((byte) (i + 27), NumericUtil.bigIntegerToBytesWithZeroPadded(canonicalised.r, 32), NumericUtil.bigIntegerToBytesWithZeroPadded(canonicalised.s, 32));
        }
        throw new RuntimeException("Could not construct a recoverable key. This should never happen.");
    }

    private static BigInteger signedMessageToKey(byte[] bArr, SignatureData signatureData) throws SignatureException {
        byte[] r = signatureData.getR();
        byte[] s = signatureData.getS();
        Preconditions.checkState(r != null && r.length == 32, "r must be 32 bytes");
        Preconditions.checkState(s != null && s.length == 32, "s must be 32 bytes");
        int v = signatureData.getV() & 255;
        if (v < 27 || v > 34) {
            throw new SignatureException("Header byte out of range: " + v);
        }
        ECKey recoverFromSignature = ECKey.recoverFromSignature(v - 27, new ECKey.ECDSASignature(new BigInteger(1, signatureData.getR()), new BigInteger(1, signatureData.getS())), Sha256Hash.wrap(Hash.keccak256(bArr)), false);
        if (recoverFromSignature == null) {
            throw new SignatureException("Could not recover public key from signature");
        }
        byte[] encoded = recoverFromSignature.getPubKeyPoint().getEncoded(false);
        return NumericUtil.bytesToBigInteger(Arrays.copyOfRange(encoded, 1, encoded.length));
    }
}
