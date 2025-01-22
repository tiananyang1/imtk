package im.imkey.imkeylibrary.core.wallet.transaction.cosmos;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/cosmos/PubKey.class */
public class PubKey {
    String type = "tendermint/PubKeySecp256k1";
    String value;

    public PubKey(String str) {
        this.value = str;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
