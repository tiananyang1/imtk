package im.imkey.imkeylibrary.core.wallet.transaction;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/TransactionSignedResult.class */
public class TransactionSignedResult {
    private String signedTx;
    private String txHash;
    private String wtxID;

    public TransactionSignedResult(String str, String str2) {
        this.signedTx = str;
        this.txHash = str2;
    }

    public TransactionSignedResult(String str, String str2, String str3) {
        this.signedTx = str;
        this.txHash = str2;
        this.wtxID = str3;
    }

    public String getSignedTx() {
        return this.signedTx;
    }

    public String getTxHash() {
        return this.txHash;
    }

    public String getWtxID() {
        return this.wtxID;
    }

    public void setSignedTx(String str) {
        this.signedTx = str;
    }

    public void setTxHash(String str) {
        this.txHash = str;
    }

    public void setWtxID(String str) {
        this.wtxID = str;
    }

    public String toString() {
        return "TransactionSignedResult{signedTx='" + this.signedTx + "',\n\n txHash='" + this.txHash + "',\n\n wtxID='" + this.wtxID + "'}";
    }
}
