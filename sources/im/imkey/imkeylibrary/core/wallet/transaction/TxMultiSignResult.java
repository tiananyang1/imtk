package im.imkey.imkeylibrary.core.wallet.transaction;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/TxMultiSignResult.class */
public class TxMultiSignResult {
    List<String> signed;
    String txHash;

    public TxMultiSignResult(String str, List<String> list) {
        this.txHash = str;
        this.signed = list;
    }

    public List<String> getSigned() {
        return this.signed;
    }

    public String getTxHash() {
        return this.txHash;
    }

    public void setSigned(List<String> list) {
        this.signed = list;
    }

    public void setTxHash(String str) {
        this.txHash = str;
    }

    public String toString() {
        return "TxMultiSignResult{txHash='" + this.txHash + "', signed=" + this.signed + '}';
    }
}
