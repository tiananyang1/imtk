package im.imkey.imkeylibrary.core.wallet;

import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.core.wallet.transaction.EOSSign;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.bitcoinj.core.Base58;
import org.spongycastle.crypto.digests.RIPEMD160Digest;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/Eos.class */
public class Eos extends Wallet {
    public static String eosEcSign(String str, boolean z, String str2, String str3) {
        Path.checkPath(str3);
        byte[] hexToBytes = z ? NumericUtil.hexToBytes(str) : Hash.sha256(str.getBytes(Charset.forName("UTF-8")));
        byte[] bytes = str3.getBytes();
        return EOSSign.signMessage(ByteUtil.concat(ByteUtil.concat(new byte[]{1, 32}, hexToBytes), ByteUtil.concat(new byte[]{2, (byte) bytes.length}, bytes)), str2, hexToBytes);
    }

    public String displayPubKey(String str) {
        Path.checkPath(str);
        String pubKey = getPubKey(str);
        Apdu.checkResponse(sendApdu(Apdu.eosCoinReg(pubKey.getBytes())));
        return pubKey;
    }

    @Override // im.imkey.imkeylibrary.core.wallet.Wallet
    protected String getAid() {
        return Applet.EOS_AID;
    }

    public String getPubKey(String str) {
        Path.checkPath(str);
        selectApplet();
        byte[] hexToBytes = NumericUtil.hexToBytes(calComprsPub(getEosXpubHex(str, true)));
        RIPEMD160Digest rIPEMD160Digest = new RIPEMD160Digest();
        rIPEMD160Digest.update(hexToBytes, 0, hexToBytes.length);
        byte[] bArr = new byte[20];
        rIPEMD160Digest.doFinal(bArr, 0);
        return Applet.EOS_NAME + Base58.encode(ByteUtil.concat(hexToBytes, Arrays.copyOfRange(bArr, 0, 4)));
    }
}
