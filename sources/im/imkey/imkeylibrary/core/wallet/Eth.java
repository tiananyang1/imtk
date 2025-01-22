package im.imkey.imkeylibrary.core.wallet;

import com.subgraph.orchid.Cell;
import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.core.wallet.transaction.SignatureData;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/Eth.class */
public class Eth extends Wallet {
    private static byte[] dataToBytes(String str) {
        return NumericUtil.isValidHex(str) ? NumericUtil.hexToBytes(str) : str.getBytes(Charset.forName("UTF-8"));
    }

    public String displayAddress(String str) {
        Path.checkPath(str);
        String address = getAddress(str);
        Apdu.checkResponse(sendApdu(Apdu.ethCoinReg(Wallet.checkedEthAddress(address).getBytes())));
        return address;
    }

    public String getAddress(String str) {
        Path.checkPath(str);
        selectApplet();
        return publicKeyToAddress(NumericUtil.hexToBytes(getEthXpubHex(str, true).substring(2, Cell.AUTH_CHALLENGE)));
    }

    @Override // im.imkey.imkeylibrary.core.wallet.Wallet
    protected String getAid() {
        return Applet.ETH_AID;
    }

    public String signPersonalMessage(String str, String str2, String str3) {
        int i;
        Path.checkPath(str);
        selectApplet();
        byte[] dataToBytes = dataToBytes(str2);
        byte[] concat = ByteUtil.concat(String.format(Locale.ENGLISH, "\u0019Ethereum Signed Message:\n%d", Integer.valueOf(dataToBytes.length)).getBytes(Charset.forName("UTF-8")), dataToBytes);
        byte[] concat2 = ByteUtil.concat(new byte[]{1}, ByteUtil.concat(new byte[]{(byte) ((concat.length & 65280) >> 8), (byte) (concat.length & 255)}, concat));
        byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(concat2)));
        byte[] concat3 = ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), concat2);
        String sendApdu = sendApdu(Apdu.ethXpub(str, false));
        Apdu.checkResponse(sendApdu);
        String substring = sendApdu.substring(0, sendApdu.length() - 4);
        if (!Arrays.equals(Wallet.checkedEthAddress(Wallet.publicKeyToAddress(NumericUtil.hexToBytes(substring.substring(2, Cell.AUTH_CHALLENGE)))).getBytes(), str3.getBytes())) {
            throw new ImkeyException(Messages.IMKEY_ADDRESS_MISMATCH_WITH_PATH);
        }
        List<String> ethMsgPrepare = Apdu.ethMsgPrepare(ByteUtil.byteArrayToHexString(concat3));
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= ethMsgPrepare.size()) {
                break;
            }
            Apdu.checkResponse(Ble.getInstance().sendApdu(ethMsgPrepare.get(i3), 120));
            i2 = i3 + 1;
        }
        String sendApdu2 = sendApdu(Apdu.ethMsgSign(str));
        Apdu.checkResponse(sendApdu2);
        String substring2 = sendApdu2.substring(2, 66);
        String substring3 = sendApdu2.substring(66, Cell.AUTH_CHALLENGE);
        ECKey fromPublicOnly = ECKey.fromPublicOnly(NumericUtil.hexToBytes(substring.substring(0, Cell.AUTH_CHALLENGE)));
        ECKey.ECDSASignature canonicalised = new ECKey.ECDSASignature(new BigInteger(substring2, 16), new BigInteger(substring3, 16)).toCanonicalised();
        String keccak256 = Hash.keccak256(ByteUtil.byteArrayToHexString(concat));
        int i4 = 0;
        while (true) {
            i = i4;
            if (i >= 4) {
                i = -1;
                break;
            }
            ECKey recoverFromSignature = ECKey.recoverFromSignature(i, canonicalised, Sha256Hash.wrap(keccak256), false);
            if (recoverFromSignature != null && recoverFromSignature.getPubKeyPoint().equals(fromPublicOnly.getPubKeyPoint())) {
                break;
            }
            i4 = i + 1;
        }
        if (i != -1) {
            return new SignatureData((byte) (i + 27), NumericUtil.bigIntegerToBytesWithZeroPadded(canonicalised.r, 32), NumericUtil.bigIntegerToBytesWithZeroPadded(canonicalised.s, 32)).toString();
        }
        throw new RuntimeException("Could not construct a recoverable key. This should never happen.");
    }
}
