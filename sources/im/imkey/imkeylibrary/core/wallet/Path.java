package im.imkey.imkeylibrary.core.wallet;

import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.exception.ImkeyException;
import java.util.regex.Pattern;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/Path.class */
public class Path {
    public static final String BITCOIN_SEGWIT_TESTNET_PATH = "m/49'/1'/0'/";
    public static final String BITCOIN_TESTNET_PATH = "m/44'/1'/0'";
    public static final String BTC_PATH_PREFIX = "m/44'/0'/0'/";
    public static final String BTC_SEGWIT_PATH_PREFIX = "m/49'/0'/0'/";
    public static final String COSMOS_LEDGER = "m/44'/118'/0'/0/0";
    public static final String EOS_LEDGER = "m/44'/194'/0'/0/0";
    public static final String ETH_LEDGER = "m/44'/60'/0'/0/0";

    public static void checkPath(String str) {
        if (str.split("/").length < 2 || str.split("/").length > 10) {
            throw new ImkeyException(Messages.IMKEY_PATH_ILLEGAL);
        }
        if (str.length() > 100) {
            throw new ImkeyException(Messages.IMKEY_PATH_ILLEGAL);
        }
        if (!Pattern.matches("^m/[0-9'/]+$", str)) {
            throw new ImkeyException(Messages.IMKEY_PATH_ILLEGAL);
        }
    }
}
