package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.text.ParseException;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JOSEObject.class */
public abstract class JOSEObject implements Serializable {
    public static final String MIME_TYPE_COMPACT = "application/jose; charset=UTF-8";
    public static final String MIME_TYPE_JS = "application/jose+json; charset=UTF-8";
    private static final long serialVersionUID = 1;
    private Base64URL[] parsedParts;
    private Payload payload;

    /* JADX INFO: Access modifiers changed from: protected */
    public JOSEObject() {
        this.payload = null;
        this.parsedParts = null;
    }

    protected JOSEObject(Payload payload) {
        this.payload = payload;
    }

    public static JOSEObject parse(String str) throws ParseException {
        try {
            Algorithm parseAlgorithm = Header.parseAlgorithm(JSONObjectUtils.parse(split(str)[0].decodeToString()));
            if (parseAlgorithm.equals(Algorithm.NONE)) {
                return PlainObject.m3661parse(str);
            }
            if (parseAlgorithm instanceof JWSAlgorithm) {
                return JWSObject.m3652parse(str);
            }
            if (parseAlgorithm instanceof JWEAlgorithm) {
                return JWEObject.m3642parse(str);
            }
            throw new AssertionError("Unexpected algorithm type: " + parseAlgorithm);
        } catch (ParseException e) {
            throw new ParseException("Invalid unsecured/JWS/JWE header: " + e.getMessage(), 0);
        }
    }

    public static Base64URL[] split(String str) throws ParseException {
        String trim = str.trim();
        int indexOf = trim.indexOf(".");
        if (indexOf == -1) {
            throw new ParseException("Invalid serialized unsecured/JWS/JWE object: Missing part delimiters", 0);
        }
        int i = indexOf + 1;
        int indexOf2 = trim.indexOf(".", i);
        if (indexOf2 == -1) {
            throw new ParseException("Invalid serialized unsecured/JWS/JWE object: Missing second delimiter", 0);
        }
        int i2 = indexOf2 + 1;
        int indexOf3 = trim.indexOf(".", i2);
        if (indexOf3 == -1) {
            return new Base64URL[]{new Base64URL(trim.substring(0, indexOf)), new Base64URL(trim.substring(i, indexOf2)), new Base64URL(trim.substring(i2))};
        }
        int i3 = indexOf3 + 1;
        int indexOf4 = trim.indexOf(".", i3);
        if (indexOf4 == -1) {
            throw new ParseException("Invalid serialized JWE object: Missing fourth delimiter", 0);
        }
        if (indexOf4 == -1 || trim.indexOf(".", indexOf4 + 1) == -1) {
            return new Base64URL[]{new Base64URL(trim.substring(0, indexOf)), new Base64URL(trim.substring(i, indexOf2)), new Base64URL(trim.substring(i2, indexOf3)), new Base64URL(trim.substring(i3, indexOf4)), new Base64URL(trim.substring(indexOf4 + 1))};
        }
        throw new ParseException("Invalid serialized unsecured/JWS/JWE object: Too many part delimiters", 0);
    }

    public abstract Header getHeader();

    public Base64URL[] getParsedParts() {
        return this.parsedParts;
    }

    public String getParsedString() {
        if (this.parsedParts == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Base64URL[] base64URLArr = this.parsedParts;
        int length = base64URLArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return sb.toString();
            }
            Base64URL base64URL = base64URLArr[i2];
            if (sb.length() > 0) {
                sb.append('.');
            }
            if (base64URL != null) {
                sb.append(base64URL.toString());
            }
            i = i2 + 1;
        }
    }

    public Payload getPayload() {
        return this.payload;
    }

    public abstract String serialize();

    /* JADX INFO: Access modifiers changed from: protected */
    public void setParsedParts(Base64URL... base64URLArr) {
        this.parsedParts = base64URLArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
