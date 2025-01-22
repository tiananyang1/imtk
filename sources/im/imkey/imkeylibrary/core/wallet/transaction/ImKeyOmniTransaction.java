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
import im.imkey.imkeylibrary.core.wallet.transaction.ImKeyBitcoinTransaction;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.LogUtil;
import im.imkey.imkeylibrary.utils.NumericUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.UnsafeByteArrayOutputStream;
import org.bitcoinj.core.Utils;
import org.bitcoinj.core.VarInt;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/ImKeyOmniTransaction.class */
public class ImKeyOmniTransaction extends ImKeyBitcoinTransaction {
    private static final Coin MIN_NONDUST_OUTPUT = Coin.valueOf(546);
    private long btcChangeAmount;
    private byte[] hashOutputs;
    private Script omniExtraScript;
    private List<Output> outputs;
    private int propertyId;
    private Address receiver;
    private byte[] receiverScriptPubKey;
    private final List<String> redeemScripts;
    private Address sender;
    private byte[] senderScriptPubKey;
    private long totalBtcAmount;
    private final List<byte[]> witnesses;

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/ImKeyOmniTransaction$Output.class */
    public static class Output {
        Address address;
        byte[] scriptData;
        long value;

        public Output(long j, Address address, byte[] bArr) {
            this.value = j;
            this.address = address;
            this.scriptData = bArr;
        }
    }

    public ImKeyOmniTransaction(String str, long j, long j2, int i, ArrayList<ImKeyBitcoinTransaction.UTXO> arrayList, String str2, String str3, String str4, String str5) {
        super(str, 0, j, j2, arrayList, str2, str3, str4, str5);
        this.witnesses = new ArrayList();
        this.redeemScripts = new ArrayList();
        this.propertyId = i;
        this.totalBtcAmount = calcTotalBtcAmount();
        this.btcChangeAmount = this.totalBtcAmount - minimumBtcAmount();
    }

    private long calcTotalBtcAmount() {
        long j;
        Iterator<ImKeyBitcoinTransaction.UTXO> it = getOutputs().iterator();
        long j2 = 0;
        while (true) {
            j = j2;
            if (!it.hasNext()) {
                break;
            }
            j2 = j + it.next().getAmount();
        }
        if (j >= minimumBtcAmount()) {
            return j;
        }
        throw new ImkeyException(Messages.IMKEY_INSUFFICIENT_FUNDS);
    }

    private Script createOmniExtraData(long j) {
        return new ScriptBuilder().op(106).data(ByteUtil.concat(ByteUtil.concat(NumericUtil.hexToBytes("0x6f6d6e6900000000"), NumericUtil.bigIntegerToBytesWithZeroPadded(BigInteger.valueOf(this.propertyId), 4)), NumericUtil.bigIntegerToBytesWithZeroPadded(BigInteger.valueOf(j), 8))).build();
    }

    private byte[] hashSequence() throws IOException {
        UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream();
        Iterator<ImKeyBitcoinTransaction.UTXO> it = getOutputs().iterator();
        while (it.hasNext()) {
            Utils.uint32ToByteStreamLE(it.next().getSequence(), unsafeByteArrayOutputStream);
        }
        return Sha256Hash.hashTwice(unsafeByteArrayOutputStream.toByteArray());
    }

    private long minimumBtcAmount() {
        return MIN_NONDUST_OUTPUT.value + getFee();
    }

    @Override // im.imkey.imkeylibrary.core.wallet.transaction.ImKeyBitcoinTransaction
    public TransactionSignedResult signSegWitTransaction(String str, String str2) {
        NetworkParameters fromID;
        Script createOutputScript;
        String hexString;
        Path.checkPath(str2);
        String str3 = str2;
        if (!str2.endsWith("/")) {
            str3 = str2 + "/";
        }
        if (Constants.MAINNET.equals(str)) {
            fromID = NetworkParameters.fromID("org.bitcoin.production");
        } else {
            if (!Constants.TESTNET.equals(str)) {
                throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
            }
            fromID = NetworkParameters.fromID("org.bitcoin.test");
        }
        selectApplet();
        ImkeyTransaction imkeyTransaction = new ImkeyTransaction(MainNetParams.get());
        String xpubHex = new Btc().getXpubHex(str3.substring(0, str3.length() - 1), false);
        DeterministicKey createMasterPubKeyFromBytes = HDKeyDerivation.createMasterPubKeyFromBytes(NumericUtil.hexToBytes(xpubHex.substring(0, Cell.AUTH_CHALLENGE)), NumericUtil.hexToBytes(xpubHex.substring(Cell.AUTH_CHALLENGE)));
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getOutputs().size()) {
                if (this.btcChangeAmount < MIN_NONDUST_OUTPUT.value) {
                    throw new ImkeyException(Messages.IMKEY_AMOUNT_LESS_THAN_MINIMUM);
                }
                long[] jArr = new long[getOutputs().size()];
                Address fromBase58 = Address.fromBase58(fromID, getTo());
                if (fromBase58.isP2SHAddress()) {
                    createOutputScript = ScriptBuilder.createP2SHOutputScript(fromBase58.getHash160());
                    hexString = Integer.toHexString(fromID.getP2SHHeader());
                } else {
                    createOutputScript = ScriptBuilder.createOutputScript(fromBase58);
                    hexString = Integer.toHexString(fromID.getAddressHeader());
                }
                this.sender = Address.fromBase58(fromID, getOutputs().get(0).getAddress());
                Script createP2SHOutputScript = ScriptBuilder.createP2SHOutputScript(this.sender.getHash160());
                imkeyTransaction.addOutput(Coin.valueOf(this.btcChangeAmount), createP2SHOutputScript);
                imkeyTransaction.addOutput(Coin.valueOf(MIN_NONDUST_OUTPUT.value), createOutputScript);
                this.omniExtraScript = createOmniExtraData(getAmount());
                imkeyTransaction.addOutput(Coin.ZERO, this.omniExtraScript);
                int size = imkeyTransaction.getOutputs().size();
                String str4 = NumericUtil.bytesToHex(imkeyTransaction.serializeSegWitTransaction(Transaction.SigHash.ALL, false, 0, size, jArr)) + ByteUtil.byteArrayToHexString(ByteUtil.longToByteArray(getFee()));
                if (hexString.length() % 2 != 0) {
                    hexString = "0" + hexString;
                }
                byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str4 + hexString);
                byte[] concat = ByteUtil.concat(new byte[]{1}, ByteUtil.concat(new byte[]{(byte) hexStringToByteArray.length}, hexStringToByteArray));
                byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(concat)));
                List<String> omniSegwitPrepare = Apdu.omniSegwitPrepare((byte) 0, ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), concat));
                LogUtil.m2866d("btc prepare....");
                int i3 = 0;
                while (true) {
                    int i4 = i3;
                    if (i4 >= omniSegwitPrepare.size()) {
                        break;
                    }
                    Apdu.checkResponse(Ble.getInstance().sendApdu(omniSegwitPrepare.get(i4), i4 == omniSegwitPrepare.size() - 1 ? 120 : 20));
                    i3 = i4 + 1;
                }
                UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream();
                UnsafeByteArrayOutputStream unsafeByteArrayOutputStream2 = new UnsafeByteArrayOutputStream();
                int i5 = 0;
                while (true) {
                    int i6 = i5;
                    if (i6 >= getOutputs().size()) {
                        break;
                    }
                    ECKey eCKeyFromPublicOnly = EccUtil.getECKeyFromPublicOnly((byte[]) arrayList.get(i6));
                    ImKeyBitcoinTransaction.UTXO utxo = getOutputs().get(i6);
                    TransactionInput transactionInput = new TransactionInput(fromID, (Transaction) null, NumericUtil.hexToBytes(String.format("0x76a914%s88ac", NumericUtil.bytesToHex(eCKeyFromPublicOnly.getPubKeyHash()))), new TransactionOutPoint(fromID, utxo.getVout(), Sha256Hash.wrap(utxo.getTxHash())), Coin.valueOf(utxo.getAmount()));
                    jArr[i6] = utxo.getAmount();
                    imkeyTransaction.addInput(transactionInput);
                    try {
                        unsafeByteArrayOutputStream.write(NumericUtil.reverseBytes(NumericUtil.hexToBytes(utxo.getTxHash())));
                        Utils.uint32ToByteStreamLE(utxo.getVout(), unsafeByteArrayOutputStream);
                        Utils.uint32ToByteStreamLE(utxo.getSequence(), unsafeByteArrayOutputStream2);
                        i5 = i6 + 1;
                    } catch (IOException e) {
                        throw new ImkeyException("OutputStream error");
                    }
                }
                List<String> omniSegwitPrepare2 = Apdu.omniSegwitPrepare((byte) 64, unsafeByteArrayOutputStream.toByteArray());
                omniSegwitPrepare2.addAll(Apdu.omniSegwitPrepare(Byte.MIN_VALUE, unsafeByteArrayOutputStream2.toByteArray()));
                Iterator<String> it = omniSegwitPrepare2.iterator();
                while (it.hasNext()) {
                    Apdu.checkResponse(Ble.getInstance().sendApdu(it.next(), 20));
                }
                try {
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    int i7 = 0;
                    while (true) {
                        int i8 = i7;
                        if (i8 >= getOutputs().size()) {
                            break;
                        }
                        UnsafeByteArrayOutputStream unsafeByteArrayOutputStream3 = new UnsafeByteArrayOutputStream();
                        ImKeyBitcoinTransaction.UTXO utxo2 = getOutputs().get(i8);
                        ECKey eCKeyFromPublicOnly2 = EccUtil.getECKeyFromPublicOnly((byte[]) arrayList.get(i8));
                        arrayList3.add(String.format("0014%s", NumericUtil.bytesToHex(eCKeyFromPublicOnly2.getPubKeyHash())));
                        unsafeByteArrayOutputStream3.write(NumericUtil.reverseBytes(NumericUtil.hexToBytes(utxo2.getTxHash())));
                        Utils.uint32ToByteStreamLE(utxo2.getVout(), unsafeByteArrayOutputStream3);
                        byte[] hexToBytes = NumericUtil.hexToBytes(String.format("0x76a914%s88ac", NumericUtil.bytesToHex(eCKeyFromPublicOnly2.getPubKeyHash())));
                        byte[] bArr = new byte[hexToBytes.length + 1];
                        bArr[0] = (byte) hexToBytes.length;
                        System.arraycopy(hexToBytes, 0, bArr, 1, hexToBytes.length);
                        unsafeByteArrayOutputStream3.write(bArr);
                        Utils.uint64ToByteStreamLE(BigInteger.valueOf(utxo2.getAmount()), unsafeByteArrayOutputStream3);
                        Utils.uint32ToByteStreamLE(utxo2.getSequence(), unsafeByteArrayOutputStream3);
                        byte[] byteArray = unsafeByteArrayOutputStream3.toByteArray();
                        byte[] bArr2 = new byte[byteArray.length + 1];
                        bArr2[0] = (byte) byteArray.length;
                        System.arraycopy(byteArray, 0, bArr2, 1, byteArray.length);
                        String str5 = str3 + utxo2.getDerivedPath();
                        byte[] bArr3 = new byte[str5.getBytes().length + 1];
                        bArr3[0] = (byte) str5.getBytes().length;
                        System.arraycopy(str5.getBytes(), 0, bArr3, 1, str5.getBytes().length);
                        String sendApdu = Ble.getInstance().sendApdu(Apdu.btcSegwitSign(Boolean.valueOf(i8 == getOutputs().size() - 1), 1, ByteUtil.concat(bArr2, bArr3)));
                        Apdu.checkResponse(sendApdu);
                        LogUtil.m2866d("signResult" + i8 + "：" + sendApdu);
                        arrayList2.add(ByteUtil.concat(new TransactionSignature(new BigInteger(sendApdu.substring(2, 66), 16), getLowS(new BigInteger(sendApdu.substring(66, Cell.AUTH_CHALLENGE), 16))).encodeToDER(), new byte[]{1}));
                        i7 = i8 + 1;
                    }
                    OutputStream[] outputStreamArr = {new UnsafeByteArrayOutputStream(), new UnsafeByteArrayOutputStream()};
                    new UnsafeByteArrayOutputStream();
                    int i9 = 0;
                    while (i9 < 2) {
                        OutputStream outputStream = outputStreamArr[i9];
                        Utils.uint32ToByteStreamLE(2L, outputStream);
                        if (i9 == 0) {
                            outputStream.write(0);
                            outputStream.write(1);
                        }
                        outputStream.write(new VarInt(getOutputs().size()).encode());
                        int i10 = 0;
                        while (true) {
                            int i11 = i10;
                            if (i11 >= getOutputs().size()) {
                                break;
                            }
                            ImKeyBitcoinTransaction.UTXO utxo3 = getOutputs().get(i11);
                            outputStream.write(NumericUtil.reverseBytes(NumericUtil.hexToBytes(utxo3.getTxHash())));
                            Utils.uint32ToByteStreamLE(utxo3.getVout(), outputStream);
                            outputStream.write(23);
                            outputStream.write(22);
                            outputStream.write(NumericUtil.hexToBytes((String) arrayList3.get(i11)));
                            Utils.uint32ToByteStreamLE(utxo3.getSequence(), outputStream);
                            i10 = i11 + 1;
                        }
                        outputStream.write(new VarInt(size).encode());
                        Utils.uint64ToByteStreamLE(BigInteger.valueOf(this.btcChangeAmount), outputStream);
                        outputStream.write(new VarInt(createP2SHOutputScript.getProgram().length).encode());
                        outputStream.write(createP2SHOutputScript.getProgram());
                        Utils.uint64ToByteStreamLE(BigInteger.valueOf(MIN_NONDUST_OUTPUT.value), outputStream);
                        outputStream.write(new VarInt(createOutputScript.getProgram().length).encode());
                        outputStream.write(createOutputScript.getProgram());
                        Utils.uint64ToByteStreamLE(BigInteger.valueOf(0L), outputStream);
                        outputStream.write(new VarInt(this.omniExtraScript.getProgram().length).encode());
                        outputStream.write(this.omniExtraScript.getProgram());
                        ArrayList arrayList4 = arrayList2;
                        if (i9 == 0) {
                            int i12 = 0;
                            while (true) {
                                int i13 = i12;
                                arrayList4 = arrayList2;
                                if (i13 >= arrayList2.size()) {
                                    break;
                                }
                                ECKey eCKeyFromPublicOnly3 = EccUtil.getECKeyFromPublicOnly((byte[]) arrayList.get(i13));
                                byte[] bArr4 = (byte[]) arrayList2.get(i13);
                                outputStream.write(new VarInt(2L).encode());
                                outputStream.write(new VarInt(bArr4.length).encode());
                                outputStream.write(bArr4);
                                outputStream.write(new VarInt(eCKeyFromPublicOnly3.getPubKey().length).encode());
                                outputStream.write(eCKeyFromPublicOnly3.getPubKey());
                                i12 = i13 + 1;
                            }
                        }
                        Utils.uint32ToByteStreamLE(getLocktime(), outputStream);
                        i9++;
                        arrayList2 = arrayList4;
                    }
                    byte[] byteArray2 = outputStreamArr[0].toByteArray();
                    return new TransactionSignedResult(NumericUtil.bytesToHex(byteArray2), NumericUtil.beBigEndianHex(NumericUtil.bytesToHex(Sha256Hash.hashTwice(outputStreamArr[1].toByteArray()))), NumericUtil.beBigEndianHex(NumericUtil.bytesToHex(Sha256Hash.hashTwice(byteArray2))));
                } catch (IOException e2) {
                    throw new ImkeyException("OutputStream error");
                }
            }
            byte[] pubKey = EccUtil.deriveChildKeyFromPublic(createMasterPubKeyFromBytes, getOutputs().get(i2).getDerivedPath()).getPubKey();
            if (!verifyAddrSegwit(getOutputs().get(i2).getAddress(), fromID, pubKey)) {
                throw new ImkeyException(Messages.IMKEY_ADDRESS_MISMATCH_WITH_PATH);
            }
            arrayList.add(pubKey);
            i = i2 + 1;
        }
    }

    @Override // im.imkey.imkeylibrary.core.wallet.transaction.ImKeyBitcoinTransaction
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
        if (this.btcChangeAmount < MIN_NONDUST_OUTPUT.value) {
            throw new ImkeyException(Messages.IMKEY_AMOUNT_LESS_THAN_MINIMUM);
        }
        if (Constants.MAINNET.equals(str)) {
            fromID = NetworkParameters.fromID("org.bitcoin.production");
        } else {
            if (!Constants.TESTNET.equals(str)) {
                throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
            }
            fromID = NetworkParameters.fromID("org.bitcoin.test");
        }
        selectApplet();
        ImkeyTransaction imkeyTransaction = new ImkeyTransaction(fromID);
        String xpubHex = new Btc().getXpubHex(str3.substring(0, str3.length() - 1), false);
        DeterministicKey createMasterPubKeyFromBytes = HDKeyDerivation.createMasterPubKeyFromBytes(NumericUtil.hexToBytes(xpubHex.substring(0, Cell.AUTH_CHALLENGE)), NumericUtil.hexToBytes(xpubHex.substring(Cell.AUTH_CHALLENGE)));
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < getOutputs().size()) {
                byte[] pubKey = EccUtil.deriveChildKeyFromPublic(createMasterPubKeyFromBytes, getOutputs().get(i3).getDerivedPath()).getPubKey();
                if (!verifyAddr(getOutputs().get(i3).getAddress(), fromID, pubKey)) {
                    throw new ImkeyException(Messages.IMKEY_ADDRESS_MISMATCH_WITH_PATH);
                }
                arrayList.add(pubKey);
                i2 = i3 + 1;
            } else {
                this.sender = Address.fromBase58(fromID, getOutputs().get(0).getAddress());
                imkeyTransaction.addOutput(Coin.valueOf(this.btcChangeAmount), this.sender);
                imkeyTransaction.addOutput(MIN_NONDUST_OUTPUT, Address.fromBase58(fromID, getTo()));
                imkeyTransaction.addOutput(Coin.ZERO, createOmniExtraData(getAmount()));
                String str4 = NumericUtil.bytesToHex(imkeyTransaction.serializeTransaction(Transaction.SigHash.ALL, false)) + ByteUtil.byteArrayToHexString(ByteUtil.longToByteArray(getFee()));
                String hexString = Integer.toHexString(Address.fromBase58(fromID, getTo()).getVersion());
                String str5 = hexString;
                if (hexString.length() % 2 != 0) {
                    str5 = "0" + hexString;
                }
                byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str4 + str5);
                hexStringToByteArray[4] = (byte) getOutputs().size();
                byte[] concat = ByteUtil.concat(new byte[]{1}, ByteUtil.concat(new byte[]{(byte) hexStringToByteArray.length}, hexStringToByteArray));
                byte[] signPackage = Wallet.signPackage(Sha256Hash.wrap(Sha256Hash.hashTwice(concat)));
                byte[] concat2 = ByteUtil.concat(ByteUtil.concat(new byte[]{0}, ByteUtil.concat(new byte[]{(byte) signPackage.length}, signPackage)), concat);
                LogUtil.m2866d("btc prepare....");
                Apdu.checkResponse(Ble.getInstance().sendApdu(Apdu.omniPrepareData((byte) 0, NumericUtil.bytesToHex(concat2)), 120));
                for (ImKeyBitcoinTransaction.UTXO utxo : getOutputs()) {
                    imkeyTransaction.addInput(Sha256Hash.wrap(utxo.getTxHash()), utxo.getVout(), new Script(NumericUtil.hexToBytes(utxo.getScriptPubKey())));
                }
                ImkeyTransaction imkeyTransaction2 = new ImkeyTransaction(fromID);
                int size = (getOutputs().size() - 1) / 5;
                int i4 = 0;
                while (true) {
                    int i5 = i4;
                    if (i5 >= size + 1) {
                        String bytesToHex = NumericUtil.bytesToHex(imkeyTransaction.bitcoinSerialize());
                        return new TransactionSignedResult(bytesToHex, NumericUtil.beBigEndianHex(Hash.sha256(Hash.sha256(bytesToHex))));
                    }
                    int i6 = 0;
                    while (true) {
                        int i7 = i6;
                        if (i7 >= getOutputs().size()) {
                            break;
                        }
                        if (i7 < i5 * 5 || i7 >= (i5 + 1) * 5) {
                            imkeyTransaction2.addInput(Sha256Hash.wrap(getOutputs().get(i7).getTxHash()), getOutputs().get(i7).getVout(), new Script(new byte[0]));
                        } else {
                            imkeyTransaction2.addInput(Sha256Hash.wrap(getOutputs().get(i7).getTxHash()), getOutputs().get(i7).getVout(), new Script(NumericUtil.hexToBytes(getOutputs().get(i7).getScriptPubKey())));
                        }
                        byte[] serializeTransaction = imkeyTransaction2.serializeTransaction(Transaction.SigHash.ALL, false);
                        byte[] bArr = new byte[serializeTransaction.length - 13];
                        System.arraycopy(serializeTransaction, 4, bArr, 0, bArr.length);
                        bArr[0] = (byte) i7;
                        Apdu.checkResponse(Ble.getInstance().sendApdu(Apdu.btcPrepareInput(Byte.MIN_VALUE, NumericUtil.bytesToHex(bArr))));
                        imkeyTransaction2.clearInputs();
                        i6 = i7 + 1;
                    }
                    int i8 = i5 * 5;
                    while (true) {
                        int i9 = i8;
                        i = i5 + 1;
                        if (i9 < i * 5 && i9 < arrayList.size()) {
                            ImKeyBitcoinTransaction.UTXO utxo2 = getOutputs().get(i9);
                            byte[] hexToBytes = utxo2.getAddress().equals(getAddressFromPubKey(fromID, (byte[]) arrayList.get(i9))) ? (byte[]) arrayList.get(i9) : NumericUtil.hexToBytes(calComprsPub(NumericUtil.bytesToHex((byte[]) arrayList.get(i9))));
                            TransactionInput input = imkeyTransaction.getInput(i9);
                            String sendApdu = Ble.getInstance().sendApdu(Apdu.btcSign(i9, 1, str3 + utxo2.getDerivedPath()));
                            LogUtil.m2866d("signResult" + i9 + "：" + sendApdu);
                            Apdu.checkResponse(sendApdu);
                            String substring = sendApdu.substring(2, 66);
                            String substring2 = sendApdu.substring(66, Cell.AUTH_CHALLENGE);
                            LogUtil.m2866d("\n********************");
                            LogUtil.m2866d(i5 + " r:" + substring);
                            LogUtil.m2866d(i5 + " s:" + substring2);
                            input.setScriptSig(im.imkey.imkeylibrary.core.wallet.script.ScriptBuilder.createInputScript(new TransactionSignature(new BigInteger(substring, 16), getLowS(new BigInteger(substring2, 16))), hexToBytes));
                            i8 = i9 + 1;
                        }
                    }
                    i4 = i;
                }
            }
        }
    }
}
