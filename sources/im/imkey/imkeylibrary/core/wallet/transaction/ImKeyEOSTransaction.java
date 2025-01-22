package im.imkey.imkeylibrary.core.wallet.transaction;

import com.google.common.base.Strings;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.core.wallet.Path;
import im.imkey.imkeylibrary.core.wallet.Wallet;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/ImKeyEOSTransaction.class */
public class ImKeyEOSTransaction extends Wallet implements TransactionSigner {
    private byte[] txBuf;
    private List<ToSignObj> txsToSign;

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/ImKeyEOSTransaction$ToSignObj.class */
    public static class ToSignObj {
        private List<String> publicKeys;
        private String txHex;

        public List<String> getPublicKeys() {
            return this.publicKeys;
        }

        public String getTxHex() {
            return this.txHex;
        }

        public void setPublicKeys(List<String> list) {
            this.publicKeys = list;
        }

        public void setTxHex(String str) {
            this.txHex = str;
        }
    }

    public ImKeyEOSTransaction(List<ToSignObj> list) {
        this.txsToSign = list;
    }

    public ImKeyEOSTransaction(byte[] bArr) {
        this.txBuf = bArr;
    }

    @Override // im.imkey.imkeylibrary.core.wallet.Wallet
    protected String getAid() {
        return Applet.EOS_AID;
    }

    @Override // im.imkey.imkeylibrary.core.wallet.transaction.TransactionSigner
    @Deprecated
    public TransactionSignedResult signTransaction(String str, String str2) {
        return null;
    }

    public List<TxMultiSignResult> signTransactions(String str, String str2, String str3, String str4, String str5) {
        byte[] concat;
        Path.checkPath(str5);
        selectApplet();
        ArrayList arrayList = new ArrayList(this.txsToSign.size());
        for (ToSignObj toSignObj : this.txsToSign) {
            byte[] hexToBytes = NumericUtil.hexToBytes(toSignObj.txHex);
            String bytesToHex = NumericUtil.bytesToHex(Hash.sha256(hexToBytes));
            byte[] concat2 = ByteUtil.concat(NumericUtil.hexToBytes(str), hexToBytes);
            byte[] bArr = new byte[32];
            Arrays.fill(bArr, (byte) 0);
            byte[] sha256 = Hash.sha256(ByteUtil.concat(concat2, bArr));
            if (Strings.isNullOrEmpty(str4)) {
                concat = ByteUtil.concat(new byte[]{7, 0}, new byte[]{8, 0});
            } else {
                byte[] bytes = str4.getBytes(Charset.forName("UTF-8"));
                byte length = (byte) bytes.length;
                byte[] bytes2 = str2.getBytes(Charset.forName("UTF-8"));
                concat = ByteUtil.concat(ByteUtil.concat(new byte[]{7, length}, bytes), ByteUtil.concat(new byte[]{8, (byte) bytes2.length}, bytes2));
            }
            ArrayList arrayList2 = new ArrayList(toSignObj.publicKeys.size());
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < toSignObj.publicKeys.size()) {
                    String str6 = (String) toSignObj.publicKeys.get(i2);
                    byte[] bytes3 = str5.getBytes();
                    arrayList2.add(EOSSign.sign(ByteUtil.concat(ByteUtil.concat(new byte[]{1, 32}, sha256), ByteUtil.concat(ByteUtil.concat(new byte[]{2, (byte) bytes3.length}, bytes3), concat)), str6, sha256));
                    i = i2 + 1;
                }
            }
            arrayList.add(new TxMultiSignResult(bytesToHex, arrayList2));
        }
        return arrayList;
    }
}
