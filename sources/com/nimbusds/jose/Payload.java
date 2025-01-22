package com.nimbusds.jose;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jose.util.StandardCharset;
import com.nimbusds.jwt.SignedJWT;
import java.io.Serializable;
import java.text.ParseException;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/Payload.class */
public final class Payload implements Serializable {
    private static final long serialVersionUID = 1;
    private final Base64URL base64URL;
    private final byte[] bytes;
    private final JSONObject jsonObject;
    private final JWSObject jwsObject;
    private final Origin origin;
    private final SignedJWT signedJWT;
    private final String string;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/Payload$Origin.class */
    public enum Origin {
        JSON,
        STRING,
        BYTE_ARRAY,
        BASE64URL,
        JWS_OBJECT,
        SIGNED_JWT;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static Origin[] valuesCustom() {
            Origin[] valuesCustom = values();
            int length = valuesCustom.length;
            Origin[] originArr = new Origin[length];
            System.arraycopy(valuesCustom, 0, originArr, 0, length);
            return originArr;
        }
    }

    public Payload(JWSObject jWSObject) {
        if (jWSObject == null) {
            throw new IllegalArgumentException("The JWS object must not be null");
        }
        if (jWSObject.getState() == JWSObject.State.UNSIGNED) {
            throw new IllegalArgumentException("The JWS object must be signed");
        }
        this.jsonObject = null;
        this.string = null;
        this.bytes = null;
        this.base64URL = null;
        this.jwsObject = jWSObject;
        this.signedJWT = null;
        this.origin = Origin.JWS_OBJECT;
    }

    public Payload(Base64URL base64URL) {
        if (base64URL == null) {
            throw new IllegalArgumentException("The Base64URL-encoded object must not be null");
        }
        this.jsonObject = null;
        this.string = null;
        this.bytes = null;
        this.base64URL = base64URL;
        this.jwsObject = null;
        this.signedJWT = null;
        this.origin = Origin.BASE64URL;
    }

    public Payload(SignedJWT signedJWT) {
        if (signedJWT == null) {
            throw new IllegalArgumentException("The signed JWT must not be null");
        }
        if (signedJWT.getState() == JWSObject.State.UNSIGNED) {
            throw new IllegalArgumentException("The JWT must be signed");
        }
        this.jsonObject = null;
        this.string = null;
        this.bytes = null;
        this.base64URL = null;
        this.signedJWT = signedJWT;
        this.jwsObject = signedJWT;
        this.origin = Origin.SIGNED_JWT;
    }

    public Payload(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The string must not be null");
        }
        this.jsonObject = null;
        this.string = str;
        this.bytes = null;
        this.base64URL = null;
        this.jwsObject = null;
        this.signedJWT = null;
        this.origin = Origin.STRING;
    }

    public Payload(JSONObject jSONObject) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("The JSON object must not be null");
        }
        this.jsonObject = jSONObject;
        this.string = null;
        this.bytes = null;
        this.base64URL = null;
        this.jwsObject = null;
        this.signedJWT = null;
        this.origin = Origin.JSON;
    }

    public Payload(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("The byte array must not be null");
        }
        this.jsonObject = null;
        this.string = null;
        this.bytes = bArr;
        this.base64URL = null;
        this.jwsObject = null;
        this.signedJWT = null;
        this.origin = Origin.BYTE_ARRAY;
    }

    private static String byteArrayToString(byte[] bArr) {
        if (bArr != null) {
            return new String(bArr, StandardCharset.UTF_8);
        }
        return null;
    }

    private static byte[] stringToByteArray(String str) {
        if (str != null) {
            return str.getBytes(StandardCharset.UTF_8);
        }
        return null;
    }

    public Origin getOrigin() {
        return this.origin;
    }

    public Base64URL toBase64URL() {
        Base64URL base64URL = this.base64URL;
        return base64URL != null ? base64URL : Base64URL.m3704encode(toBytes());
    }

    public byte[] toBytes() {
        byte[] bArr = this.bytes;
        if (bArr != null) {
            return bArr;
        }
        Base64URL base64URL = this.base64URL;
        return base64URL != null ? base64URL.decode() : stringToByteArray(toString());
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject != null) {
            return jSONObject;
        }
        String payload = toString();
        if (payload == null) {
            return null;
        }
        try {
            return JSONObjectUtils.parse(payload);
        } catch (ParseException e) {
            return null;
        }
    }

    public JWSObject toJWSObject() {
        JWSObject jWSObject = this.jwsObject;
        if (jWSObject != null) {
            return jWSObject;
        }
        try {
            return JWSObject.m3652parse(toString());
        } catch (ParseException e) {
            return null;
        }
    }

    public SignedJWT toSignedJWT() {
        SignedJWT signedJWT = this.signedJWT;
        if (signedJWT != null) {
            return signedJWT;
        }
        try {
            return SignedJWT.m3709parse(toString());
        } catch (ParseException e) {
            return null;
        }
    }

    public String toString() {
        String str = this.string;
        if (str != null) {
            return str;
        }
        JWSObject jWSObject = this.jwsObject;
        if (jWSObject != null) {
            return jWSObject.getParsedString() != null ? this.jwsObject.getParsedString() : this.jwsObject.serialize();
        }
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject != null) {
            return jSONObject.toString();
        }
        byte[] bArr = this.bytes;
        if (bArr != null) {
            return byteArrayToString(bArr);
        }
        Base64URL base64URL = this.base64URL;
        if (base64URL != null) {
            return base64URL.decodeToString();
        }
        return null;
    }

    public <T> T toType(PayloadTransformer<T> payloadTransformer) {
        return payloadTransformer.transform(this);
    }
}
