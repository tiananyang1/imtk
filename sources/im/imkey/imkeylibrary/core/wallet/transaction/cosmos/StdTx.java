package im.imkey.imkeylibrary.core.wallet.transaction.cosmos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/cosmos/StdTx.class */
public class StdTx {
    StdFee fee;
    String memo;

    @JsonProperty("msg")
    List<Map<String, Object>> msgs;
    List<StdSignature> signatures;

    public StdTx(List<Map<String, Object>> list, StdFee stdFee, List<StdSignature> list2, String str) {
        this.msgs = list;
        this.fee = stdFee;
        this.signatures = list2;
        this.memo = str;
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

    public List<StdSignature> getSignatures() {
        return this.signatures;
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

    public void setSignatures(List<StdSignature> list) {
        this.signatures = list;
    }
}
