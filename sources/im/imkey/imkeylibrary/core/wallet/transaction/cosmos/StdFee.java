package im.imkey.imkeylibrary.core.wallet.transaction.cosmos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/cosmos/StdFee.class */
public class StdFee {
    List<Coin> amount;
    long gas;

    public StdFee(List<Coin> list, long j) {
        this.amount = list;
        this.gas = j;
    }

    public List<Coin> getAmount() {
        return this.amount;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public long getGas() {
        return this.gas;
    }

    public void setAmount(List<Coin> list) {
        this.amount = list;
    }

    public void setGas(long j) {
        this.gas = j;
    }
}
