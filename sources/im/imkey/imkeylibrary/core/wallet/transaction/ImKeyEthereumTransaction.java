package im.imkey.imkeylibrary.core.wallet.transaction;

import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.core.foundation.rlp.RlpEncoder;
import im.imkey.imkeylibrary.core.foundation.rlp.RlpList;
import im.imkey.imkeylibrary.core.foundation.rlp.RlpString;
import im.imkey.imkeylibrary.core.foundation.rlp.RlpType;
import im.imkey.imkeylibrary.core.wallet.Path;
import im.imkey.imkeylibrary.core.wallet.Wallet;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/ImKeyEthereumTransaction.class */
public class ImKeyEthereumTransaction extends Wallet implements TransactionSigner {
    private String data;
    private String fee;
    private BigInteger gasLimit;
    private BigInteger gasPrice;
    private BigInteger nonce;
    private String payment;
    private String receiver;
    private String sender;

    /* renamed from: to */
    private String f2744to;
    private BigInteger value;

    public ImKeyEthereumTransaction(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, String str, BigInteger bigInteger4, String str2, HashMap<String, String> hashMap) {
        this.nonce = bigInteger;
        this.gasPrice = bigInteger2;
        this.gasLimit = bigInteger3;
        this.f2744to = str;
        this.value = bigInteger4;
        if (str2 != null) {
            this.data = NumericUtil.cleanHexPrefix(str2);
        }
        if (hashMap.isEmpty()) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        this.payment = hashMap.get("payment");
        this.receiver = hashMap.get("receiver");
        this.sender = hashMap.get("sender");
        this.fee = hashMap.get("fee");
    }

    private static SignatureData createEip155SignatureData(SignatureData signatureData, byte b) {
        return new SignatureData(signatureData.getV() + (b * 2) + 8, signatureData.getR(), signatureData.getS());
    }

    List<RlpType> asRlpValues(SignatureData signatureData) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(RlpString.create(getNonce()));
        arrayList.add(RlpString.create(getGasPrice()));
        arrayList.add(RlpString.create(getGasLimit()));
        String to = getTo();
        if (to == null || to.length() <= 0) {
            arrayList.add(RlpString.create(""));
        } else {
            arrayList.add(RlpString.create(NumericUtil.hexToBytes(to)));
        }
        arrayList.add(RlpString.create(getValue()));
        arrayList.add(RlpString.create(NumericUtil.hexToBytes(getData())));
        if (signatureData != null && (signatureData.getV() & 4294967295L) > 0) {
            arrayList.add(RlpString.create(signatureData.getV()));
            arrayList.add(RlpString.create(ByteUtil.trimLeadingZeroes(signatureData.getR())));
            arrayList.add(RlpString.create(ByteUtil.trimLeadingZeroes(signatureData.getS())));
        }
        return arrayList;
    }

    String calcTxHash(String str) {
        return NumericUtil.prependHexPrefix(Hash.keccak256(str));
    }

    byte[] encodeToRLP(SignatureData signatureData) {
        return RlpEncoder.encode(new RlpList(asRlpValues(signatureData)));
    }

    @Override // im.imkey.imkeylibrary.core.wallet.Wallet
    protected String getAid() {
        return Applet.ETH_AID;
    }

    public String getData() {
        return this.data;
    }

    public String getFee() {
        return this.fee;
    }

    public BigInteger getGasLimit() {
        return this.gasLimit;
    }

    public BigInteger getGasPrice() {
        return this.gasPrice;
    }

    public BigInteger getNonce() {
        return this.nonce;
    }

    public String getPayment() {
        return this.payment;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public String getSender() {
        return this.sender;
    }

    public String getTo() {
        return this.f2744to;
    }

    public BigInteger getValue() {
        return this.value;
    }

    @Override // im.imkey.imkeylibrary.core.wallet.transaction.TransactionSigner
    public TransactionSignedResult signTransaction(String str, String str2) {
        Path.checkPath(str2);
        selectApplet();
        String signTransaction = signTransaction((byte) Integer.parseInt(str), str2.getBytes());
        return new TransactionSignedResult(signTransaction, calcTxHash(signTransaction));
    }

    String signTransaction(byte b, byte[] bArr) {
        byte[] encodeToRLP = encodeToRLP(new SignatureData(b, new byte[0], new byte[0]));
        byte[] bytes = getPayment().getBytes(Charset.forName("UTF-8"));
        byte[] concat = ByteUtil.concat(new byte[]{7}, ByteUtil.concat(new byte[]{(byte) bytes.length}, bytes));
        byte[] bytes2 = getReceiver().getBytes(Charset.forName("UTF-8"));
        byte[] concat2 = ByteUtil.concat(new byte[]{8}, ByteUtil.concat(new byte[]{(byte) bytes2.length}, bytes2));
        byte[] bytes3 = getFee().getBytes(Charset.forName("UTF-8"));
        return NumericUtil.bytesToHex(encodeToRLP(createEip155SignatureData(EthereumSign.signMessage(encodeToRLP, bArr, concat, concat2, ByteUtil.concat(new byte[]{9}, ByteUtil.concat(new byte[]{(byte) bytes3.length}, bytes3)), getSender().getBytes(Charset.forName("UTF-8"))), b)));
    }
}
