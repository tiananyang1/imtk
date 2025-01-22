package com.subgraph.orchid.config;

import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.p002hs.HSDescriptorCookie;
import com.subgraph.orchid.data.Base32;
import com.subgraph.orchid.encoders.Base64;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/config/TorConfigHSAuth.class */
public class TorConfigHSAuth {
    private final Map<String, HSDescriptorCookie> map = new HashMap();

    private HSDescriptorCookie createFromBase64(String str) {
        if (str.length() != 22) {
            throw new IllegalArgumentException();
        }
        byte[] decode = Base64.decode(str + "A=");
        int i = (decode[decode.length - 1] & 255) >> 4;
        byte[] bArr = new byte[decode.length - 1];
        System.arraycopy(decode, 0, bArr, 0, bArr.length);
        if (i == 0) {
            return new HSDescriptorCookie(HSDescriptorCookie.CookieType.COOKIE_BASIC, bArr);
        }
        if (i == 1) {
            return new HSDescriptorCookie(HSDescriptorCookie.CookieType.COOKIE_STEALTH, bArr);
        }
        throw new TorException("Illegal cookie descriptor with flag value: " + i);
    }

    private String validateKey(String str) {
        String str2 = str;
        if (str.endsWith(".onion")) {
            str2 = str.substring(0, str.length() - 6);
        }
        try {
            if (Base32.base32Decode(str2).length == 10) {
                return str2;
            }
            throw new IllegalArgumentException();
        } catch (TorException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(String str, String str2) {
        HSDescriptorCookie createFromBase64 = createFromBase64(str2);
        this.map.put(validateKey(str), createFromBase64);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSDescriptorCookie get(String str) {
        return this.map.get(validateKey(str));
    }
}
