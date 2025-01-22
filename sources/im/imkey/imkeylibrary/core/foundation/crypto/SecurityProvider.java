package im.imkey.imkeylibrary.core.foundation.crypto;

import java.security.Security;
import org.spongycastle.jce.provider.BouncyCastleProvider;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/SecurityProvider.class */
public class SecurityProvider {
    private static boolean ISDECIDE = false;
    private static String PROVIDER_NAME = "SC";

    public static String getProvierName() {
        if (ISDECIDE && Security.getProvider(PROVIDER_NAME) != null) {
            return PROVIDER_NAME;
        }
        Security.addProvider(new BouncyCastleProvider());
        ISDECIDE = true;
        return PROVIDER_NAME;
    }
}
