package im.imkey.imkeylibrary.core.wallet.transaction.cosmos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.common.base.Strings;
import com.subgraph.orchid.Cell;
import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.foundation.crypto.EccUtil;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.core.wallet.Path;
import im.imkey.imkeylibrary.core.wallet.Wallet;
import im.imkey.imkeylibrary.core.wallet.transaction.TransactionSignedResult;
import im.imkey.imkeylibrary.core.wallet.transaction.TransactionSigner;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.bitcoinj.core.Sha256Hash;
import org.spongycastle.util.encoders.Base64;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/cosmos/ImKeyCosmosTransaction.class */
public class ImKeyCosmosTransaction implements TransactionSigner {
    long accountNumber;
    String chainId;
    StdFee fee;
    String memo;
    List<Map<String, Object>> msgs;
    long sequence;

    public ImKeyCosmosTransaction(long j, String str, StdFee stdFee, String str2, List<Map<String, Object>> list, long j2) {
        this.accountNumber = j;
        this.chainId = str;
        this.fee = stdFee;
        this.memo = str2;
        this.msgs = list;
        this.sequence = j2;
    }

    private ObjectMapper jsonMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        return objectMapper;
    }

    private byte[] serializeTx() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            jsonMapper().writeValue(byteArrayOutputStream, this);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new ImkeyException(Messages.IMKEY_COSMOS_JSON_ERROR, e);
        }
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public long getAccountNumber() {
        return this.accountNumber;
    }

    public String getChainId() {
        return this.chainId;
    }

    public String getCosmosXpubHex(String str, boolean z) {
        String sendApdu = Ble.getInstance().sendApdu(Apdu.cosmosPub(str, z));
        Apdu.checkResponse(sendApdu);
        if (Boolean.valueOf(Wallet.signVerify(ByteUtil.hexStringToByteArray(sendApdu.substring(0, 194)), ByteUtil.hexStringToByteArray(sendApdu.substring(194, sendApdu.length() - 4)))).booleanValue()) {
            return sendApdu.substring(0, Cell.AUTH_CHALLENGE);
        }
        throw new ImkeyException(Messages.IMKEY_SIGNATURE_VERIFY_FAIL);
    }

    public StdFee getFee() {
        return this.fee;
    }

    public String getMemo() {
        return this.memo;
    }

    public List<Map<String, Object>> getMsgs() {
        return this.msgs;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public long getSequence() {
        return this.sequence;
    }

    protected void selectApplet() {
        Apdu.checkResponse(Ble.getInstance().sendApdu(Apdu.select(Applet.COSMOS_AID)));
    }

    public void setAccountNumber(long j) {
        this.accountNumber = j;
    }

    public void setChainId(String str) {
        this.chainId = str;
    }

    public void setFee(StdFee stdFee) {
        this.fee = stdFee;
    }

    public void setMemo(String str) {
        this.memo = str;
    }

    public void setMsgs(List<Map<String, Object>> list) {
        this.msgs = list;
    }

    public void setSequence(long j) {
        this.sequence = j;
    }

    @Override // im.imkey.imkeylibrary.core.wallet.transaction.TransactionSigner
    @Deprecated
    public TransactionSignedResult signTransaction(String str, String str2) {
        return null;
    }

    public TransactionSignedResult signTransaction(String str, String str2, String str3, String str4, String str5, String str6) {
        byte[] concat;
        Path.checkPath(str2);
        selectApplet();
        byte[] sha256 = Hash.sha256(serializeTx());
        if (Strings.isNullOrEmpty(str3)) {
            concat = ByteUtil.concat(ByteUtil.concat(new byte[]{7, 0}, new byte[]{8, 0}), new byte[]{9, 0});
        } else {
            byte[] bytes = str3.getBytes(Charset.forName("UTF-8"));
            byte length = (byte) bytes.length;
            byte[] bytes2 = str4.getBytes(Charset.forName("UTF-8"));
            byte length2 = (byte) bytes2.length;
            byte[] bytes3 = str6.getBytes(Charset.forName("UTF-8"));
            concat = ByteUtil.concat(ByteUtil.concat(ByteUtil.concat(new byte[]{7, length}, bytes), ByteUtil.concat(new byte[]{8, length2}, bytes2)), ByteUtil.concat(new byte[]{9, (byte) bytes3.length}, bytes3));
        }
        byte[] concat2 = ByteUtil.concat(ByteUtil.concat(new byte[]{1, 32}, sha256), concat);
        byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(concat2)));
        List<String> cosmosPrepare = Apdu.cosmosPrepare(NumericUtil.bytesToHex(ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), concat2)));
        for (int i = 0; i < cosmosPrepare.size(); i++) {
            String str7 = cosmosPrepare.get(i);
            int i2 = 20;
            if (i == cosmosPrepare.size() - 1) {
                i2 = 120;
            }
            Apdu.checkResponse(Ble.getInstance().sendApdu(str7, i2));
        }
        String sendApdu = Ble.getInstance().sendApdu(Apdu.cosmosSign(str2));
        Apdu.checkResponse(sendApdu);
        StdTx stdTx = new StdTx(this.msgs, this.fee, Collections.singletonList(new StdSignature(this.accountNumber, Base64.toBase64String(ByteUtil.concat(NumericUtil.bigIntegerToBytesWithZeroPadded(new BigInteger(sendApdu.substring(2, 66), 16), 32), NumericUtil.bigIntegerToBytesWithZeroPadded(EccUtil.getLowS(new BigInteger(sendApdu.substring(66, Cell.AUTH_CHALLENGE), 16)), 32))), new PubKey(Base64.toBase64String(EccUtil.getCompressECKey(NumericUtil.hexToBytes(getCosmosXpubHex(str2, true))).getPubKey())), this.sequence)), this.memo);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            jsonMapper().writeValue(byteArrayOutputStream, stdTx);
            return new TransactionSignedResult(new String(byteArrayOutputStream.toByteArray()), "");
        } catch (IOException e) {
            throw new ImkeyException(Messages.IMKEY_COSMOS_JSON_ERROR, e);
        }
    }
}
