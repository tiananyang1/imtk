package im.imkey.imkeylibrary.core.wallet;

import com.subgraph.orchid.Cell;
import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.device.key.KeyManager;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.LogUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.ChildNumber;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/Wallet.class */
public class Wallet {
    public static String checkedEthAddress(String str) {
        String replace = str.toLowerCase().replace("0x", "");
        StringBuilder sb = new StringBuilder();
        String byteArrayToHexString = ByteUtil.byteArrayToHexString(Hash.keccak256(str.getBytes()));
        char[] charArray = replace.toLowerCase().toCharArray();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= charArray.length) {
                return "0x" + sb.toString();
            }
            char c = charArray[i2];
            sb.append((Character.digit(byteArrayToHexString.charAt(i2), 16) & 255) > 7 ? Character.toUpperCase(c) : Character.toLowerCase(c));
            i = i2 + 1;
        }
    }

    public static String publicKeyToAddress(byte[] bArr) {
        byte[] keccak256 = Hash.keccak256(bArr);
        return NumericUtil.bytesToHex(Arrays.copyOfRange(keccak256, keccak256.length - 20, keccak256.length));
    }

    public static byte[] signPackage(Sha256Hash sha256Hash) {
        byte[] privKey = KeyManager.getInstance().getPrivKey();
        if (Arrays.equals(privKey, new byte[32])) {
            throw new ImkeyException(Messages.IMKEY_NOT_BIND_CHECK);
        }
        return ECKey.fromPrivate(privKey).sign(sha256Hash).encodeToDER();
    }

    public static boolean signVerify(byte[] bArr, byte[] bArr2) {
        byte[] sePubKey = KeyManager.getInstance().getSePubKey();
        if (Arrays.equals(sePubKey, new byte[65])) {
            throw new ImkeyException(Messages.IMKEY_NOT_BIND_CHECK);
        }
        return ECKey.fromPublicOnly(sePubKey).verify(Hash.sha256(bArr), bArr2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] addChecksum(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 4];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        System.arraycopy(Sha256Hash.hashTwice(bArr), 0, bArr2, length, 4);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String calComprsPub(String str) {
        String substring = str.substring(2, 66);
        return (!new BigInteger(str.substring(66, Cell.AUTH_CHALLENGE), 16).mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO) ? "03" : "02") + substring;
    }

    public String calcSegWitAddress(int i, String str) {
        byte[] sha256hash160 = Utils.sha256hash160(NumericUtil.hexToBytes(String.format("0x0014%s", NumericUtil.bytesToHex(Utils.sha256hash160(NumericUtil.hexToBytes(str))))));
        byte[] bArr = new byte[sha256hash160.length + 1 + 4];
        bArr[0] = (byte) i;
        System.arraycopy(sha256hash160, 0, bArr, 1, sha256hash160.length);
        System.arraycopy(Sha256Hash.hashTwice(bArr, 0, sha256hash160.length + 1), 0, bArr, sha256hash160.length + 1, 4);
        return Base58.encode(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<ChildNumber> generatePath(String str) {
        ArrayList arrayList = new ArrayList();
        String[] split = str.split("/");
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return arrayList;
            }
            String str2 = split[i2];
            if (!"m".equalsIgnoreCase(str2) && !"".equals(str2.trim())) {
                if (str2.charAt(str2.length() - 1) == '\'') {
                    arrayList.add(new ChildNumber(Integer.parseInt(str2.substring(0, str2.length() - 1)), true));
                } else {
                    arrayList.add(new ChildNumber(Integer.parseInt(str2), false));
                }
            }
            i = i2 + 1;
        }
    }

    protected String getAid() {
        throw new RuntimeException("need overwirte this method");
    }

    protected String[] getChildNumberPath(String str) {
        String str2 = str;
        if (str.endsWith("/")) {
            str2 = str.substring(0, str.length() - 1);
        }
        return str2.split("/");
    }

    public String getCosmosXpubHex(String str, boolean z) {
        String sendApdu = sendApdu(Apdu.cosmosPub(str, z));
        Apdu.checkResponse(sendApdu);
        if (Boolean.valueOf(signVerify(ByteUtil.hexStringToByteArray(sendApdu.substring(0, 194)), ByteUtil.hexStringToByteArray(sendApdu.substring(194, sendApdu.length() - 4)))).booleanValue()) {
            return sendApdu.substring(0, Cell.AUTH_CHALLENGE);
        }
        throw new ImkeyException(Messages.IMKEY_SIGNATURE_VERIFY_FAIL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getDepth(String str) {
        String str2 = str;
        if (str.endsWith("/")) {
            str2 = str.substring(0, str.length() - 1);
        }
        return str2.split("/").length - 1;
    }

    public String getEosXpubHex(String str, boolean z) {
        String sendApdu = sendApdu(Apdu.eosPub(str, z));
        Apdu.checkResponse(sendApdu);
        if (Boolean.valueOf(signVerify(ByteUtil.hexStringToByteArray(sendApdu.substring(0, 194)), ByteUtil.hexStringToByteArray(sendApdu.substring(194, sendApdu.length() - 4)))).booleanValue()) {
            return sendApdu.substring(0, Cell.AUTH_CHALLENGE);
        }
        throw new ImkeyException(Messages.IMKEY_SIGNATURE_VERIFY_FAIL);
    }

    public String getEthXpubHex(String str, boolean z) {
        String sendApdu = sendApdu(Apdu.ethXpub(str, z));
        Apdu.checkResponse(sendApdu);
        if (Boolean.valueOf(signVerify(ByteUtil.hexStringToByteArray(sendApdu.substring(0, 194)), ByteUtil.hexStringToByteArray(sendApdu.substring(194, sendApdu.length() - 4)))).booleanValue()) {
            return sendApdu.substring(0, Cell.AUTH_CHALLENGE);
        }
        throw new ImkeyException(Messages.IMKEY_SIGNATURE_VERIFY_FAIL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getFingerprint(byte[] bArr) {
        return ByteBuffer.wrap(Arrays.copyOfRange(Utils.sha256hash160(bArr), 0, 4)).getInt();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getParentPath(String str) {
        String str2 = str;
        if (str.endsWith("/")) {
            str2 = str.substring(0, str.length() - 1);
        }
        return str2.substring(0, str2.lastIndexOf("/"));
    }

    public String getXpubHex(String str, boolean z) {
        String sendApdu = sendApdu(Apdu.btcXpub(str, z));
        Apdu.checkResponse(sendApdu);
        if (Boolean.valueOf(signVerify(ByteUtil.hexStringToByteArray(sendApdu.substring(0, 194)), ByteUtil.hexStringToByteArray(sendApdu.substring(194, sendApdu.length() - 4)))).booleanValue()) {
            return sendApdu.substring(0, 194);
        }
        throw new ImkeyException(Messages.IMKEY_SIGNATURE_VERIFY_FAIL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String pub2Address(int i, String str) {
        return toBase58(i, Utils.sha256hash160(NumericUtil.hexToBytes(str)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void selectApplet() {
        Apdu.checkResponse(sendApdu(Apdu.select(getAid())));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String sendApdu(String str) {
        return Ble.getInstance().sendApdu(str);
    }

    protected String toBase58(int i, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 1 + 4];
        bArr2[0] = (byte) i;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        System.arraycopy(Sha256Hash.hashTwice(bArr2, 0, bArr.length + 1), 0, bArr2, bArr.length + 1, 4);
        LogUtil.m2866d(ByteUtil.byteArrayToHexString(bArr2));
        return Base58.encode(bArr2);
    }
}
