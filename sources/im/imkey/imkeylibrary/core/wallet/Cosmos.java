package im.imkey.imkeylibrary.core.wallet;

import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.foundation.crypto.EccUtil;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.utils.Bech32;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.io.ByteArrayOutputStream;
import org.bitcoinj.core.AddressFormatException;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/Cosmos.class */
public class Cosmos extends Wallet {
    private static byte[] convertBits(byte[] bArr, int i, int i2, int i3, int i4, boolean z) throws AddressFormatException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(64);
        int i5 = (1 << i4) - 1;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < i2; i8++) {
            int i9 = bArr[i8 + i] & 255;
            if ((i9 >>> i3) != 0) {
                throw new AddressFormatException(String.format("Input value '%X' exceeds '%d' bit size", Integer.valueOf(i9), Integer.valueOf(i3)));
            }
            i7 = ((i7 << i3) | i9) & ((1 << ((i3 + i4) - 1)) - 1);
            i6 += i3;
            while (i6 >= i4) {
                i6 -= i4;
                byteArrayOutputStream.write((i7 >>> i6) & i5);
            }
        }
        if (z) {
            if (i6 > 0) {
                byteArrayOutputStream.write((i7 << (i4 - i6)) & i5);
            }
        } else if (i6 >= i3 || ((i7 << (i4 - i6)) & i5) != 0) {
            throw new AddressFormatException("Could not convert bits, invalid padding");
        }
        return byteArrayOutputStream.toByteArray();
    }

    public String displayAddress(String str) {
        Path.checkPath(str);
        String address = getAddress(str);
        Apdu.checkResponse(sendApdu(Apdu.cosmosCoinReg(address.getBytes())));
        return address;
    }

    public String getAddress(String str) {
        Path.checkPath(str);
        selectApplet();
        byte[] pubKeyHash = EccUtil.getCompressECKey(NumericUtil.hexToBytes(getCosmosXpubHex(str, true))).getPubKeyHash();
        return Bech32.encode("cosmos", convertBits(pubKeyHash, 0, pubKeyHash.length, 8, 5, true));
    }

    @Override // im.imkey.imkeylibrary.core.wallet.Wallet
    protected String getAid() {
        return Applet.COSMOS_AID;
    }
}
