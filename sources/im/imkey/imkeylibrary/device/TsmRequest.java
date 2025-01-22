package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.exception.ImkeyException;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/TsmRequest.class */
public class TsmRequest {
    /* JADX INFO: Access modifiers changed from: protected */
    public String getStatus(String str) {
        if (str == null || str.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        String str2 = str;
        if (str.length() > 4) {
            str2 = str.substring(str.length() - 4, str.length());
        }
        return str2;
    }
}
