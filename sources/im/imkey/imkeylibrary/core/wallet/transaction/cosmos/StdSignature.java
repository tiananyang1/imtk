package im.imkey.imkeylibrary.core.wallet.transaction.cosmos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/cosmos/StdSignature.class */
public class StdSignature {
    long accountNumber;
    PubKey pubKey;
    long sequence;
    String signature;

    public StdSignature(long j, String str, PubKey pubKey, long j2) {
        this.accountNumber = j;
        this.signature = str;
        this.pubKey = pubKey;
        this.sequence = j2;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public long getAccountNumber() {
        return this.accountNumber;
    }

    public PubKey getPubKey() {
        return this.pubKey;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public long getSequence() {
        return this.sequence;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setAccountNumber(long j) {
        this.accountNumber = j;
    }

    public void setPubKey(PubKey pubKey) {
        this.pubKey = pubKey;
    }

    public void setSequence(long j) {
        this.sequence = j;
    }

    public void setSignature(String str) {
        this.signature = str;
    }
}
