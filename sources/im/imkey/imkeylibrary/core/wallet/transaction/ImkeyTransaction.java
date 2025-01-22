package im.imkey.imkeylibrary.core.wallet.transaction;

import java.io.IOException;
import java.math.BigInteger;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.UnsafeByteArrayOutputStream;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.TransactionSignature;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/ImkeyTransaction.class */
public class ImkeyTransaction extends Transaction {
    public ImkeyTransaction(NetworkParameters networkParameters) {
        super(networkParameters);
    }

    public byte[] serializeSegWitTransaction(Transaction.SigHash sigHash, boolean z, int i, int i2, long[] jArr) {
        try {
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream();
            Utils.uint32ToByteStreamLE(2L, unsafeByteArrayOutputStream);
            unsafeByteArrayOutputStream.write(i);
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= i) {
                    break;
                }
                getInput(i4).bitcoinSerialize(unsafeByteArrayOutputStream);
                Utils.uint64ToByteStreamLE(BigInteger.valueOf(jArr[i4]), unsafeByteArrayOutputStream);
                i3 = i4 + 1;
            }
            unsafeByteArrayOutputStream.write(i2);
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 >= i2) {
                    Utils.uint32ToByteStreamLE(0L, unsafeByteArrayOutputStream);
                    Utils.uint32ToByteStreamLE(((byte) TransactionSignature.calcSigHashValue(sigHash, z)) & 255, unsafeByteArrayOutputStream);
                    return unsafeByteArrayOutputStream.toByteArray();
                }
                getOutput(i6).bitcoinSerialize(unsafeByteArrayOutputStream);
                i5 = i6 + 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] serializeTransaction(Transaction.SigHash sigHash, boolean z) {
        try {
            int calcSigHashValue = TransactionSignature.calcSigHashValue(sigHash, z);
            byte[] bitcoinSerialize = bitcoinSerialize();
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(bitcoinSerialize.length + 4);
            unsafeByteArrayOutputStream.write(bitcoinSerialize, 0, bitcoinSerialize.length);
            Utils.uint32ToByteStreamLE(((byte) calcSigHashValue) & 255, unsafeByteArrayOutputStream);
            return unsafeByteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
