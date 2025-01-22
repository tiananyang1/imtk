package com.nimbusds.jwt;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.Header;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.text.ParseException;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jwt/JWTParser.class */
public final class JWTParser {
    private JWTParser() {
    }

    public static JWT parse(String str) throws ParseException {
        int indexOf = str.indexOf(".");
        if (indexOf == -1) {
            throw new ParseException("Invalid JWT serialization: Missing dot delimiter(s)", 0);
        }
        try {
            Algorithm parseAlgorithm = Header.parseAlgorithm(JSONObjectUtils.parse(new Base64URL(str.substring(0, indexOf)).decodeToString()));
            if (parseAlgorithm.equals(Algorithm.NONE)) {
                return PlainJWT.m3708parse(str);
            }
            if (parseAlgorithm instanceof JWSAlgorithm) {
                return SignedJWT.m3709parse(str);
            }
            if (parseAlgorithm instanceof JWEAlgorithm) {
                return EncryptedJWT.m3706parse(str);
            }
            throw new AssertionError("Unexpected algorithm type: " + parseAlgorithm);
        } catch (ParseException e) {
            throw new ParseException("Invalid unsecured/JWS/JWE header: " + e.getMessage(), 0);
        }
    }
}
