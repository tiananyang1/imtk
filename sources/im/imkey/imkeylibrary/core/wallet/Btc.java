package im.imkey.imkeylibrary.core.wallet;

import com.subgraph.orchid.Cell;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.nio.ByteBuffer;
import java.util.List;
import org.bitcoinj.core.Base58;
import org.bitcoinj.crypto.ChildNumber;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/Btc.class */
public class Btc extends Wallet {
    public String displayAddress(int i, String str) {
        Path.checkPath(str);
        String address = getAddress(i, str);
        Apdu.checkResponse(sendApdu(Apdu.btcCoinReg(address.getBytes())));
        return address;
    }

    public String displaySegWitAddress(int i, String str) {
        Path.checkPath(str);
        String segWitAddress = getSegWitAddress(i, str);
        Apdu.checkResponse(sendApdu(Apdu.btcCoinReg(segWitAddress.getBytes())));
        return segWitAddress;
    }

    public String getAddress(int i, String str) {
        Path.checkPath(str);
        selectApplet();
        return pub2Address(i, calComprsPub(getXpubHex(str, true).substring(0, Cell.AUTH_CHALLENGE)));
    }

    @Override // im.imkey.imkeylibrary.core.wallet.Wallet
    protected String getAid() {
        return Applet.BTC_AID;
    }

    public String getSegWitAddress(int i, String str) {
        Path.checkPath(str);
        selectApplet();
        if (i < 0 || i >= 256) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        return calcSegWitAddress(i, calComprsPub(getXpubHex(str, true).substring(0, Cell.AUTH_CHALLENGE)));
    }

    public String getXpub(int i, String str) {
        Path.checkPath(str);
        selectApplet();
        String xpubHex = getXpubHex(str, true);
        String calComprsPub = calComprsPub(getXpubHex(getParentPath(str), true).substring(0, Cell.AUTH_CHALLENGE));
        ByteBuffer allocate = ByteBuffer.allocate(78);
        allocate.putInt(i);
        allocate.put((byte) getDepth(str));
        allocate.putInt(getFingerprint(NumericUtil.hexToBytes(calComprsPub)));
        List<ChildNumber> generatePath = generatePath(str);
        allocate.putInt(generatePath.get(generatePath.size() - 1).i());
        allocate.put(NumericUtil.hexToBytes(xpubHex.substring(Cell.AUTH_CHALLENGE, 194)));
        allocate.put(NumericUtil.hexToBytes(calComprsPub(xpubHex.substring(0, Cell.AUTH_CHALLENGE))));
        return Base58.encode(addChecksum(allocate.array()));
    }
}
