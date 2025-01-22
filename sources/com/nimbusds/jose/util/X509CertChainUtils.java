package com.nimbusds.jose.util;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import net.minidev.json.JSONArray;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/X509CertChainUtils.class */
public class X509CertChainUtils {
    private X509CertChainUtils() {
    }

    public static List<Base64> parseX509CertChain(JSONArray jSONArray) throws ParseException {
        LinkedList linkedList = new LinkedList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= jSONArray.size()) {
                return linkedList;
            }
            Object obj = jSONArray.get(i2);
            if (obj == null) {
                throw new ParseException("The X.509 certificate at position " + i2 + " must not be null", 0);
            }
            if (!(obj instanceof String)) {
                throw new ParseException("The X.509 certificate at position " + i2 + " must be encoded as a Base64 string", 0);
            }
            linkedList.add(new Base64((String) obj));
            i = i2 + 1;
        }
    }
}
