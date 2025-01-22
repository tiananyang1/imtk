package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jca.JCAAware;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.IntegerUtils;
import com.nimbusds.jose.util.StandardCharset;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ConcatKDF.class */
class ConcatKDF implements JCAAware<JCAContext> {
    private final JCAContext jcaContext = new JCAContext();
    private final String jcaHashAlg;

    public ConcatKDF(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The JCA hash algorithm must not be null");
        }
        this.jcaHashAlg = str;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [byte[], byte[][]] */
    public static byte[] composeOtherInfo(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        return ByteUtils.concat(new byte[]{bArr, bArr2, bArr3, bArr4, bArr5});
    }

    public static int computeDigestCycles(int i, int i2) {
        return ((i2 + i) - 1) / i;
    }

    public static byte[] encodeDataWithLength(Base64URL base64URL) {
        return encodeDataWithLength(base64URL != null ? base64URL.decode() : null);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [byte[], byte[][]] */
    public static byte[] encodeDataWithLength(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        return ByteUtils.concat(new byte[]{IntegerUtils.toBytes(bArr.length), bArr});
    }

    public static byte[] encodeIntData(int i) {
        return IntegerUtils.toBytes(i);
    }

    public static byte[] encodeNoData() {
        return new byte[0];
    }

    public static byte[] encodeStringData(String str) {
        return encodeDataWithLength(str != null ? str.getBytes(StandardCharset.UTF_8) : null);
    }

    private MessageDigest getMessageDigest() throws JOSEException {
        Provider provider = getJCAContext().getProvider();
        try {
            return provider == null ? MessageDigest.getInstance(this.jcaHashAlg) : MessageDigest.getInstance(this.jcaHashAlg, provider);
        } catch (NoSuchAlgorithmException e) {
            throw new JOSEException("Couldn't get message digest for KDF: " + e.getMessage(), e);
        }
    }

    public SecretKey deriveKey(SecretKey secretKey, int i, byte[] bArr) throws JOSEException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MessageDigest messageDigest = getMessageDigest();
        int i2 = 1;
        while (true) {
            int i3 = i2;
            if (i3 > computeDigestCycles(ByteUtils.safeBitLength(messageDigest.getDigestLength()), i)) {
                break;
            }
            messageDigest.update(IntegerUtils.toBytes(i3));
            messageDigest.update(secretKey.getEncoded());
            if (bArr != null) {
                messageDigest.update(bArr);
            }
            try {
                byteArrayOutputStream.write(messageDigest.digest());
                i2 = i3 + 1;
            } catch (IOException e) {
                throw new JOSEException("Couldn't write derived key: " + e.getMessage(), e);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int byteLength = ByteUtils.byteLength(i);
        return byteArray.length == byteLength ? new SecretKeySpec(byteArray, "AES") : new SecretKeySpec(ByteUtils.subArray(byteArray, 0, byteLength), "AES");
    }

    public SecretKey deriveKey(SecretKey secretKey, int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) throws JOSEException {
        return deriveKey(secretKey, i, composeOtherInfo(bArr, bArr2, bArr3, bArr4, bArr5));
    }

    public String getHashAlgorithm() {
        return this.jcaHashAlg;
    }

    @Override // com.nimbusds.jose.jca.JCAAware
    public JCAContext getJCAContext() {
        return this.jcaContext;
    }
}
