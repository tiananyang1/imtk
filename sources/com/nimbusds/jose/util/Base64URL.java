package com.nimbusds.jose.util;

import java.math.BigInteger;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/Base64URL.class */
public class Base64URL extends Base64 {
    public Base64URL(String str) {
        super(str);
    }

    /* renamed from: encode, reason: collision with other method in class */
    public static Base64URL m3702encode(String str) {
        return m3704encode(str.getBytes(CHARSET));
    }

    /* renamed from: encode, reason: collision with other method in class */
    public static Base64URL m3703encode(BigInteger bigInteger) {
        return m3704encode(BigIntegerUtils.toBytesUnsigned(bigInteger));
    }

    /* renamed from: encode, reason: collision with other method in class */
    public static Base64URL m3704encode(byte[] bArr) {
        return new Base64URL(Base64Codec.encodeToString(bArr, true));
    }

    @Override // com.nimbusds.jose.util.Base64
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Base64URL) && toString().equals(obj.toString());
    }
}
