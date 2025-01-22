package im.imkey.imkeylibrary.core.wallet.transaction;

import com.subgraph.orchid.Cell;
import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.foundation.crypto.EccUtil;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.core.wallet.Btc;
import im.imkey.imkeylibrary.core.wallet.Path;
import im.imkey.imkeylibrary.core.wallet.Wallet;
import im.imkey.imkeylibrary.device.Applet;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.LogUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.UnsafeByteArrayOutputStream;
import org.bitcoinj.core.Utils;
import org.bitcoinj.core.VarInt;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/ImKeyBitcoinTransaction.class */
public class ImKeyBitcoinTransaction extends Wallet {
    private static final long DUST_THRESHOLD = 2730;
    private long amount;
    private int changeIdx;
    private HashMap<String, Object> extra;
    private long fee;
    private String feeDis;
    private String from;
    private long locktime;
    private String memo;
    private List<UTXO> outputs;
    private String payment;

    /* renamed from: to */
    private String f2743to;
    private String toDis;

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/ImKeyBitcoinTransaction$UTXO.class */
    public static class UTXO {
        private String address;
        private long amount;
        private String derivedPath;
        private String scriptPubKey;
        private long sequence;
        private String txHash;
        private int vout;

        public UTXO(String str, int i, long j, String str2, String str3, String str4) {
            this.sequence = 4294967295L;
            this.txHash = str;
            this.vout = i;
            this.amount = j;
            this.address = str2;
            this.scriptPubKey = str3;
            this.derivedPath = str4;
        }

        public UTXO(String str, int i, long j, String str2, String str3, String str4, long j2) {
            this.sequence = 4294967295L;
            this.txHash = str;
            this.vout = i;
            this.amount = j;
            this.address = str2;
            this.scriptPubKey = str3;
            this.derivedPath = str4;
            this.sequence = j2;
        }

        public String getAddress() {
            return this.address;
        }

        public long getAmount() {
            return this.amount;
        }

        public String getDerivedPath() {
            return this.derivedPath;
        }

        public String getScriptPubKey() {
            return this.scriptPubKey;
        }

        public long getSequence() {
            return this.sequence;
        }

        public String getTxHash() {
            return this.txHash;
        }

        public int getVout() {
            return this.vout;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public void setAmount(long j) {
            this.amount = j;
        }

        public void setDerivedPath(String str) {
            this.derivedPath = str;
        }

        public void setScriptPubKey(String str) {
            this.scriptPubKey = str;
        }

        public void setSequence(long j) {
            this.sequence = j;
        }

        public void setTxHash(String str) {
            this.txHash = str;
        }

        public void setVout(int i) {
            this.vout = i;
        }

        public String toString() {
            return "UTXO{txHash='" + this.txHash + "', vout=" + this.vout + ", amount=" + this.amount + ", address='" + this.address + "', scriptPubKey='" + this.scriptPubKey + "', derivedPath='" + this.derivedPath + "'}";
        }
    }

    public ImKeyBitcoinTransaction(String str, int i, long j, long j2, ArrayList<UTXO> arrayList, String str2, String str3, String str4, String str5) {
        this.locktime = 0L;
        this.f2743to = str;
        this.amount = j;
        this.fee = j2;
        this.outputs = arrayList;
        this.changeIdx = i;
        this.payment = str2;
        this.toDis = str3;
        this.from = str4;
        this.feeDis = str5;
        if (j < DUST_THRESHOLD) {
            throw new ImkeyException(Messages.IMKEY_AMOUNT_LESS_THAN_MINIMUM);
        }
    }

    public ImKeyBitcoinTransaction(String str, int i, long j, long j2, ArrayList<UTXO> arrayList, HashMap<String, Object> hashMap, String str2, String str3, String str4, String str5) {
        this(str, i, j, j2, arrayList, str2, str3, str4, str5);
        this.extra = hashMap;
    }

    private byte[] getHash160(String str) {
        byte[] decodeChecked = Base58.decodeChecked(str);
        byte[] bArr = new byte[decodeChecked.length - 1];
        System.arraycopy(decodeChecked, 1, bArr, 0, decodeChecked.length - 1);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getAddressFromPubKey(NetworkParameters networkParameters, byte[] bArr) {
        return new Address(networkParameters, Utils.sha256hash160(bArr)).toBase58();
    }

    @Override // im.imkey.imkeylibrary.core.wallet.Wallet
    protected String getAid() {
        return Applet.BTC_AID;
    }

    public long getAmount() {
        return this.amount;
    }

    public int getChangeIdx() {
        return this.changeIdx;
    }

    public long getFee() {
        return this.fee;
    }

    public long getLocktime() {
        return this.locktime;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BigInteger getLowS(BigInteger bigInteger) {
        BigInteger bigInteger2 = bigInteger;
        if (bigInteger.compareTo(Constants.HALF_CURVE_ORDER) > 0) {
            bigInteger2 = Constants.CURVE_N.subtract(bigInteger);
        }
        return bigInteger2;
    }

    public String getMemo() {
        return this.memo;
    }

    public List<UTXO> getOutputs() {
        return this.outputs;
    }

    public String getTo() {
        return this.f2743to;
    }

    public void setAmount(long j) {
        this.amount = j;
    }

    public void setChangeIdx(int i) {
        this.changeIdx = i;
    }

    public void setFee(long j) {
        this.fee = j;
    }

    public void setMemo(String str) {
        this.memo = str;
    }

    public void setOutputs(List<UTXO> list) {
        this.outputs = list;
    }

    public void setTo(String str) {
        this.f2743to = str;
    }

    public TransactionSignedResult signSegWitTransaction(String str, String str2) {
        NetworkParameters fromID;
        long j;
        Script createOutputScript;
        String hexString;
        int i;
        boolean z;
        byte[] bArr;
        Path.checkPath(str2);
        String str3 = str2;
        if (!str2.endsWith("/")) {
            str3 = str2 + "/";
        }
        selectApplet();
        if (Constants.MAINNET.equals(str)) {
            fromID = NetworkParameters.fromID("org.bitcoin.production");
        } else {
            if (!Constants.TESTNET.equals(str)) {
                throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
            }
            fromID = NetworkParameters.fromID("org.bitcoin.test");
        }
        ImkeyTransaction imkeyTransaction = new ImkeyTransaction(MainNetParams.get());
        String xpubHex = new Btc().getXpubHex(str3.substring(0, str3.length() - 1), false);
        DeterministicKey createMasterPubKeyFromBytes = HDKeyDerivation.createMasterPubKeyFromBytes(NumericUtil.hexToBytes(xpubHex.substring(0, Cell.AUTH_CHALLENGE)), NumericUtil.hexToBytes(xpubHex.substring(Cell.AUTH_CHALLENGE)));
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= getOutputs().size()) {
                long[] jArr = new long[getOutputs().size()];
                Iterator<UTXO> it = getOutputs().iterator();
                long j2 = 0;
                while (true) {
                    j = j2;
                    if (!it.hasNext()) {
                        break;
                    }
                    j2 = j + it.next().getAmount();
                }
                if (j < getAmount()) {
                    throw new ImkeyException(Messages.IMKEY_INSUFFICIENT_FUNDS);
                }
                long amount = j - (getAmount() + getFee());
                Address fromBase58 = Address.fromBase58(fromID, this.f2743to);
                if (fromBase58.isP2SHAddress()) {
                    createOutputScript = ScriptBuilder.createP2SHOutputScript(fromBase58.getHash160());
                    hexString = Integer.toHexString(fromID.getP2SHHeader());
                } else {
                    createOutputScript = ScriptBuilder.createOutputScript(fromBase58);
                    hexString = Integer.toHexString(fromID.getAddressHeader());
                }
                Script createP2SHOutputScript = ScriptBuilder.createP2SHOutputScript(getHash160(new Btc().getSegWitAddress(fromID.getP2SHHeader(), str3 + "1/" + String.valueOf(this.changeIdx))));
                imkeyTransaction.addOutput(Coin.valueOf(getAmount()), createOutputScript);
                if (amount >= DUST_THRESHOLD) {
                    imkeyTransaction.addOutput(Coin.valueOf(amount), createP2SHOutputScript);
                    i = 2;
                    z = true;
                } else {
                    i = 1;
                    z = false;
                }
                HashMap<String, Object> hashMap = this.extra;
                if (hashMap != null) {
                    byte[] hexToBytes = NumericUtil.hexToBytes((String) hashMap.get("opReturn"));
                    if (hexToBytes.length > 80) {
                        throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
                    }
                    bArr = ScriptBuilder.createOpReturnScript(hexToBytes).getProgram();
                    imkeyTransaction.addOutput(new TransactionOutput(fromID, (Transaction) null, Coin.ZERO, bArr));
                    i++;
                } else {
                    bArr = null;
                }
                byte[] serializeSegWitTransaction = imkeyTransaction.serializeSegWitTransaction(Transaction.SigHash.ALL, false, imkeyTransaction.getInputs().size(), i, jArr);
                serializeSegWitTransaction[4] = (byte) this.outputs.size();
                String str4 = NumericUtil.bytesToHex(serializeSegWitTransaction) + ByteUtil.byteArrayToHexString(ByteUtil.longToByteArray(this.fee));
                String str5 = hexString;
                if (hexString.length() % 2 != 0) {
                    str5 = "0" + hexString;
                }
                byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str4 + str5);
                byte[] concat = ByteUtil.concat(new byte[]{1}, ByteUtil.concat(new byte[]{(byte) hexStringToByteArray.length}, hexStringToByteArray));
                byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(concat)));
                List<String> btcSegwitPrepare = Apdu.btcSegwitPrepare((byte) 0, ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), concat));
                LogUtil.m2866d("btc prepare....");
                int i4 = 0;
                while (true) {
                    int i5 = i4;
                    if (i5 >= btcSegwitPrepare.size()) {
                        break;
                    }
                    Apdu.checkResponse(Ble.getInstance().sendApdu(btcSegwitPrepare.get(i5), i5 == btcSegwitPrepare.size() - 1 ? 120 : 20));
                    i4 = i5 + 1;
                }
                UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream();
                UnsafeByteArrayOutputStream unsafeByteArrayOutputStream2 = new UnsafeByteArrayOutputStream();
                int i6 = 0;
                while (true) {
                    int i7 = i6;
                    if (i7 >= getOutputs().size()) {
                        break;
                    }
                    ECKey eCKeyFromPublicOnly = EccUtil.getECKeyFromPublicOnly((byte[]) arrayList.get(i7));
                    UTXO utxo = getOutputs().get(i7);
                    TransactionInput transactionInput = new TransactionInput(fromID, (Transaction) null, NumericUtil.hexToBytes(String.format("0x76a914%s88ac", NumericUtil.bytesToHex(eCKeyFromPublicOnly.getPubKeyHash()))), new TransactionOutPoint(fromID, utxo.vout, Sha256Hash.wrap(utxo.txHash)), Coin.valueOf(utxo.getAmount()));
                    jArr[i7] = utxo.getAmount();
                    imkeyTransaction.addInput(transactionInput);
                    try {
                        unsafeByteArrayOutputStream.write(NumericUtil.reverseBytes(NumericUtil.hexToBytes(utxo.txHash)));
                        Utils.uint32ToByteStreamLE(utxo.getVout(), unsafeByteArrayOutputStream);
                        Utils.uint32ToByteStreamLE(utxo.getSequence(), unsafeByteArrayOutputStream2);
                        i6 = i7 + 1;
                    } catch (IOException e) {
                        throw new ImkeyException("OutputStream error");
                    }
                }
                List<String> btcSegwitPrepare2 = Apdu.btcSegwitPrepare((byte) 64, unsafeByteArrayOutputStream.toByteArray());
                btcSegwitPrepare2.addAll(Apdu.btcSegwitPrepare(Byte.MIN_VALUE, unsafeByteArrayOutputStream2.toByteArray()));
                Iterator<String> it2 = btcSegwitPrepare2.iterator();
                while (it2.hasNext()) {
                    Apdu.checkResponse(Ble.getInstance().sendApdu(it2.next(), 20));
                }
                try {
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    int i8 = 0;
                    while (true) {
                        int i9 = i8;
                        if (i9 >= getOutputs().size()) {
                            break;
                        }
                        UnsafeByteArrayOutputStream unsafeByteArrayOutputStream3 = new UnsafeByteArrayOutputStream();
                        UTXO utxo2 = getOutputs().get(i9);
                        ECKey eCKeyFromPublicOnly2 = EccUtil.getECKeyFromPublicOnly((byte[]) arrayList.get(i9));
                        arrayList3.add(String.format("0014%s", NumericUtil.bytesToHex(eCKeyFromPublicOnly2.getPubKeyHash())));
                        unsafeByteArrayOutputStream3.write(NumericUtil.reverseBytes(NumericUtil.hexToBytes(utxo2.getTxHash())));
                        Utils.uint32ToByteStreamLE(utxo2.getVout(), unsafeByteArrayOutputStream3);
                        byte[] hexToBytes2 = NumericUtil.hexToBytes(String.format("0x76a914%s88ac", NumericUtil.bytesToHex(eCKeyFromPublicOnly2.getPubKeyHash())));
                        byte[] bArr2 = new byte[hexToBytes2.length + 1];
                        bArr2[0] = (byte) hexToBytes2.length;
                        System.arraycopy(hexToBytes2, 0, bArr2, 1, hexToBytes2.length);
                        unsafeByteArrayOutputStream3.write(bArr2);
                        Utils.uint64ToByteStreamLE(BigInteger.valueOf(utxo2.getAmount()), unsafeByteArrayOutputStream3);
                        Utils.uint32ToByteStreamLE(utxo2.getSequence(), unsafeByteArrayOutputStream3);
                        byte[] byteArray = unsafeByteArrayOutputStream3.toByteArray();
                        byte[] bArr3 = new byte[byteArray.length + 1];
                        bArr3[0] = (byte) byteArray.length;
                        System.arraycopy(byteArray, 0, bArr3, 1, byteArray.length);
                        String str6 = str3 + this.outputs.get(i9).derivedPath;
                        byte[] bArr4 = new byte[str6.getBytes().length + 1];
                        bArr4[0] = (byte) str6.getBytes().length;
                        System.arraycopy(str6.getBytes(), 0, bArr4, 1, str6.getBytes().length);
                        String sendApdu = Ble.getInstance().sendApdu(Apdu.btcSegwitSign(Boolean.valueOf(i9 == getOutputs().size() - 1), 1, ByteUtil.concat(bArr3, bArr4)));
                        Apdu.checkResponse(sendApdu);
                        LogUtil.m2866d("signResult" + i9 + "：" + sendApdu);
                        arrayList2.add(ByteUtil.concat(new TransactionSignature(new BigInteger(sendApdu.substring(2, 66), 16), getLowS(new BigInteger(sendApdu.substring(66, Cell.AUTH_CHALLENGE), 16))).encodeToDER(), new byte[]{1}));
                        i8 = i9 + 1;
                    }
                    OutputStream[] outputStreamArr = {new UnsafeByteArrayOutputStream(), new UnsafeByteArrayOutputStream()};
                    new UnsafeByteArrayOutputStream();
                    int i10 = 0;
                    while (i10 < 2) {
                        OutputStream outputStream = outputStreamArr[i10];
                        Utils.uint32ToByteStreamLE(2L, outputStream);
                        if (i10 == 0) {
                            outputStream.write(0);
                            outputStream.write(1);
                        }
                        outputStream.write(new VarInt(getOutputs().size()).encode());
                        int i11 = 0;
                        while (true) {
                            int i12 = i11;
                            if (i12 >= getOutputs().size()) {
                                break;
                            }
                            UTXO utxo3 = getOutputs().get(i12);
                            outputStream.write(NumericUtil.reverseBytes(NumericUtil.hexToBytes(utxo3.txHash)));
                            Utils.uint32ToByteStreamLE(utxo3.getVout(), outputStream);
                            outputStream.write(23);
                            outputStream.write(22);
                            outputStream.write(NumericUtil.hexToBytes((String) arrayList3.get(i12)));
                            Utils.uint32ToByteStreamLE(utxo3.getSequence(), outputStream);
                            i11 = i12 + 1;
                        }
                        outputStream.write(new VarInt(i).encode());
                        Utils.uint64ToByteStreamLE(BigInteger.valueOf(this.amount), outputStream);
                        outputStream.write(new VarInt(createOutputScript.getProgram().length).encode());
                        outputStream.write(createOutputScript.getProgram());
                        if (z) {
                            Utils.uint64ToByteStreamLE(BigInteger.valueOf(amount), outputStream);
                            outputStream.write(new VarInt(createP2SHOutputScript.getProgram().length).encode());
                            outputStream.write(createP2SHOutputScript.getProgram());
                        }
                        if (bArr != null) {
                            Utils.uint64ToByteStreamLE(BigInteger.valueOf(0L), outputStream);
                            byte[] bArr5 = bArr;
                            outputStream.write(new VarInt(bArr5.length).encode());
                            outputStream.write(bArr5);
                        }
                        ArrayList arrayList4 = arrayList2;
                        int i13 = i;
                        OutputStream outputStream2 = outputStream;
                        if (i10 == 0) {
                            int i14 = 0;
                            while (true) {
                                int i15 = i14;
                                arrayList4 = arrayList2;
                                i13 = i;
                                outputStream2 = outputStream;
                                if (i15 >= arrayList2.size()) {
                                    break;
                                }
                                ECKey eCKeyFromPublicOnly3 = EccUtil.getECKeyFromPublicOnly((byte[]) arrayList.get(i15));
                                byte[] bArr6 = (byte[]) arrayList2.get(i15);
                                outputStream.write(new VarInt(2L).encode());
                                outputStream.write(new VarInt(bArr6.length).encode());
                                outputStream.write(bArr6);
                                outputStream.write(new VarInt(eCKeyFromPublicOnly3.getPubKey().length).encode());
                                outputStream.write(eCKeyFromPublicOnly3.getPubKey());
                                i14 = i15 + 1;
                            }
                        }
                        Utils.uint32ToByteStreamLE(this.locktime, outputStream2);
                        i10++;
                        i = i13;
                        arrayList2 = arrayList4;
                    }
                    byte[] byteArray2 = outputStreamArr[0].toByteArray();
                    return new TransactionSignedResult(NumericUtil.bytesToHex(byteArray2), NumericUtil.beBigEndianHex(NumericUtil.bytesToHex(Sha256Hash.hashTwice(outputStreamArr[1].toByteArray()))), NumericUtil.beBigEndianHex(NumericUtil.bytesToHex(Sha256Hash.hashTwice(byteArray2))));
                } catch (IOException e2) {
                    throw new ImkeyException("OutputStream error");
                }
            }
            byte[] pubKey = EccUtil.deriveChildKeyFromPublic(createMasterPubKeyFromBytes, this.outputs.get(i3).derivedPath).getPubKey();
            if (!verifyAddrSegwit(getOutputs().get(i3).getAddress(), fromID, pubKey)) {
                throw new ImkeyException(Messages.IMKEY_ADDRESS_MISMATCH_WITH_PATH);
            }
            arrayList.add(pubKey);
            i2 = i3 + 1;
        }
    }

    public TransactionSignedResult signTransaction(String str, String str2) {
        NetworkParameters fromID;
        int i;
        Path.checkPath(str2);
        String str3 = str2;
        if (!str2.endsWith("/")) {
            str3 = str2 + "/";
        }
        if (getOutputs().size() > 252) {
            throw new ImkeyException(Messages.IMKEY_EXCEEDED_MAX_UTXO_NUMBER);
        }
        selectApplet();
        if (Constants.MAINNET.equals(str)) {
            fromID = NetworkParameters.fromID("org.bitcoin.production");
        } else {
            if (!Constants.TESTNET.equals(str)) {
                throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
            }
            fromID = NetworkParameters.fromID("org.bitcoin.test");
        }
        ImkeyTransaction imkeyTransaction = new ImkeyTransaction(fromID);
        String xpubHex = new Btc().getXpubHex(str3.substring(0, str3.length() - 1), false);
        DeterministicKey createMasterPubKeyFromBytes = HDKeyDerivation.createMasterPubKeyFromBytes(NumericUtil.hexToBytes(xpubHex.substring(0, Cell.AUTH_CHALLENGE)), NumericUtil.hexToBytes(xpubHex.substring(Cell.AUTH_CHALLENGE)));
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < getOutputs().size()) {
                byte[] pubKey = EccUtil.deriveChildKeyFromPublic(createMasterPubKeyFromBytes, this.outputs.get(i3).derivedPath).getPubKey();
                if (!verifyAddr(getOutputs().get(i3).getAddress(), fromID, pubKey)) {
                    throw new ImkeyException(Messages.IMKEY_ADDRESS_MISMATCH_WITH_PATH);
                }
                arrayList.add(pubKey);
                i2 = i3 + 1;
            } else {
                long j = 0;
                Iterator<UTXO> it = getOutputs().iterator();
                while (it.hasNext()) {
                    j += it.next().getAmount();
                }
                if (j < getAmount()) {
                    throw new ImkeyException(Messages.IMKEY_INSUFFICIENT_FUNDS);
                }
                imkeyTransaction.addOutput(Coin.valueOf(getAmount()), Address.fromBase58(fromID, getTo()));
                long amount = j - (getAmount() + getFee());
                if (amount >= DUST_THRESHOLD) {
                    imkeyTransaction.addOutput(Coin.valueOf(amount), Address.fromBase58(fromID, new Btc().getAddress(fromID.getAddressHeader(), str3 + "1/" + String.valueOf(this.changeIdx))));
                }
                HashMap<String, Object> hashMap = this.extra;
                if (hashMap != null) {
                    byte[] hexToBytes = NumericUtil.hexToBytes((String) hashMap.get("opReturn"));
                    if (hexToBytes.length > 80) {
                        throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
                    }
                    imkeyTransaction.addOutput(Coin.ZERO, ScriptBuilder.createOpReturnScript(hexToBytes));
                }
                byte[] serializeTransaction = imkeyTransaction.serializeTransaction(Transaction.SigHash.ALL, false);
                serializeTransaction[4] = (byte) this.outputs.size();
                String str4 = NumericUtil.bytesToHex(serializeTransaction) + ByteUtil.byteArrayToHexString(ByteUtil.longToByteArray(this.fee));
                String hexString = Integer.toHexString(Address.fromBase58(fromID, getTo()).getVersion());
                String str5 = hexString;
                if (hexString.length() % 2 != 0) {
                    str5 = "0" + hexString;
                }
                byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str4 + str5);
                byte[] concat = ByteUtil.concat(new byte[]{1}, ByteUtil.concat(new byte[]{(byte) hexStringToByteArray.length}, hexStringToByteArray));
                byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(concat)));
                List<String> btcPrepare = Apdu.btcPrepare((byte) 65, (byte) 0, ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), concat));
                LogUtil.m2866d("btc prepare....");
                int i4 = 0;
                while (true) {
                    int i5 = i4;
                    if (i5 >= btcPrepare.size()) {
                        break;
                    }
                    String str6 = btcPrepare.get(i5);
                    int i6 = 20;
                    if (i5 == btcPrepare.size() - 1) {
                        i6 = 120;
                    }
                    Apdu.checkResponse(Ble.getInstance().sendApdu(str6, i6));
                    i4 = i5 + 1;
                }
                for (UTXO utxo : getOutputs()) {
                    imkeyTransaction.addInput(Sha256Hash.wrap(utxo.getTxHash()), utxo.getVout(), new Script(NumericUtil.hexToBytes(utxo.getScriptPubKey())));
                }
                ImkeyTransaction imkeyTransaction2 = new ImkeyTransaction(fromID);
                int size = (getOutputs().size() - 1) / 5;
                int i7 = 0;
                while (true) {
                    int i8 = i7;
                    if (i8 >= size + 1) {
                        String bytesToHex = NumericUtil.bytesToHex(imkeyTransaction.bitcoinSerialize());
                        return new TransactionSignedResult(bytesToHex, NumericUtil.beBigEndianHex(Hash.sha256(Hash.sha256(bytesToHex))));
                    }
                    int i9 = 0;
                    while (true) {
                        int i10 = i9;
                        if (i10 >= getOutputs().size()) {
                            break;
                        }
                        if (i10 < i8 * 5 || i10 >= (i8 + 1) * 5) {
                            imkeyTransaction2.addInput(Sha256Hash.wrap(getOutputs().get(i10).getTxHash()), getOutputs().get(i10).getVout(), new Script(new byte[0]));
                        } else {
                            imkeyTransaction2.addInput(Sha256Hash.wrap(getOutputs().get(i10).getTxHash()), getOutputs().get(i10).getVout(), new Script(NumericUtil.hexToBytes(getOutputs().get(i10).getScriptPubKey())));
                        }
                        byte[] serializeTransaction2 = imkeyTransaction2.serializeTransaction(Transaction.SigHash.ALL, false);
                        byte[] bArr = new byte[serializeTransaction2.length - 13];
                        System.arraycopy(serializeTransaction2, 4, bArr, 0, bArr.length);
                        bArr[0] = (byte) i10;
                        Apdu.checkResponse(Ble.getInstance().sendApdu(Apdu.btcPrepareInput(Byte.MIN_VALUE, NumericUtil.bytesToHex(bArr))));
                        imkeyTransaction2.clearInputs();
                        i9 = i10 + 1;
                    }
                    int i11 = i8 * 5;
                    while (true) {
                        int i12 = i11;
                        i = i8 + 1;
                        if (i12 < i * 5 && i12 < arrayList.size()) {
                            byte[] hexToBytes2 = getOutputs().get(i12).getAddress().equals(getAddressFromPubKey(fromID, (byte[]) arrayList.get(i12))) ? (byte[]) arrayList.get(i12) : NumericUtil.hexToBytes(calComprsPub(NumericUtil.bytesToHex((byte[]) arrayList.get(i12))));
                            TransactionInput input = imkeyTransaction.getInput(i12);
                            String sendApdu = Ble.getInstance().sendApdu(Apdu.btcSign(i12, 1, str3 + this.outputs.get(i12).derivedPath));
                            LogUtil.m2866d("signResult" + i12 + "：" + sendApdu);
                            Apdu.checkResponse(sendApdu);
                            String substring = sendApdu.substring(2, 66);
                            String substring2 = sendApdu.substring(66, Cell.AUTH_CHALLENGE);
                            LogUtil.m2866d("\n********************");
                            LogUtil.m2866d(i12 + " r:" + substring);
                            LogUtil.m2866d(i12 + " s:" + substring2);
                            input.setScriptSig(im.imkey.imkeylibrary.core.wallet.script.ScriptBuilder.createInputScript(new TransactionSignature(new BigInteger(substring, 16), getLowS(new BigInteger(substring2, 16))), hexToBytes2));
                            i11 = i12 + 1;
                        }
                    }
                    i7 = i;
                }
            }
        }
    }

    public String toString() {
        return "ImKeyBitcoinTransaction{to='" + this.f2743to + "', amount=" + this.amount + ", outputs=" + this.outputs + ", memo='" + this.memo + "', fee=" + this.fee + ", changeIdx=" + this.changeIdx + '}';
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean verifyAddr(String str, NetworkParameters networkParameters, byte[] bArr) {
        return str.equals(getAddressFromPubKey(networkParameters, bArr)) || str.equals(getAddressFromPubKey(networkParameters, NumericUtil.hexToBytes(calComprsPub(NumericUtil.bytesToHex(bArr)))));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean verifyAddrSegwit(String str, NetworkParameters networkParameters, byte[] bArr) {
        String byteArrayToHexString = ByteUtil.byteArrayToHexString(bArr);
        if (bArr.length == 65) {
            byteArrayToHexString = calComprsPub(ByteUtil.byteArrayToHexString(bArr));
        }
        return str.equals(new Wallet().calcSegWitAddress(networkParameters.getP2SHHeader(), byteArrayToHexString));
    }
}
