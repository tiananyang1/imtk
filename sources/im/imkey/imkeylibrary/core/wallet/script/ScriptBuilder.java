package im.imkey.imkeylibrary.core.wallet.script;

import javax.annotation.Nullable;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/script/ScriptBuilder.class */
public class ScriptBuilder {
    public static Script createInputScript(@Nullable TransactionSignature transactionSignature, byte[] bArr) {
        return new org.bitcoinj.script.ScriptBuilder().data(transactionSignature != null ? transactionSignature.encodeToBitcoin() : new byte[0]).data(bArr).build();
    }
}
