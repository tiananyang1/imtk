package com.nimbusds.jose.util;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.Charset;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONValue;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/Base64.class */
public class Base64 implements JSONAware, Serializable {
    public static final Charset CHARSET = Charset.forName("UTF-8");
    private static final long serialVersionUID = 1;
    private final String value;

    public Base64(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The Base64 value must not be null");
        }
        this.value = str;
    }

    public static Base64 encode(String str) {
        return encode(str.getBytes(CHARSET));
    }

    public static Base64 encode(BigInteger bigInteger) {
        return encode(BigIntegerUtils.toBytesUnsigned(bigInteger));
    }

    public static Base64 encode(byte[] bArr) {
        return new Base64(Base64Codec.encodeToString(bArr, false));
    }

    public byte[] decode() {
        return Base64Codec.decode(this.value);
    }

    public BigInteger decodeToBigInteger() {
        return new BigInteger(1, decode());
    }

    public String decodeToString() {
        return new String(decode(), CHARSET);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Base64) && toString().equals(obj.toString());
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toJSONString() {
        return "\"" + JSONValue.escape(this.value) + "\"";
    }

    public String toString() {
        return this.value;
    }
}
