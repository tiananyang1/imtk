package im.imkey.imkeylibrary.core.wallet.transaction.cosmos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/cosmos/Coin.class */
public class Coin {
    long amount;
    String denom;

    public Coin(String str, long j) {
        this.denom = str;
        this.amount = j;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public long getAmount() {
        return this.amount;
    }

    public String getDenom() {
        return this.denom;
    }

    public void setAmount(long j) {
        this.amount = j;
    }

    public void setDenom(String str) {
        this.denom = str;
    }
}
