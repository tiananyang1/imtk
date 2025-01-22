package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.Container;
import com.nimbusds.jose.util.IntegerOverflowException;
import com.subgraph.orchid.Cell;
import com.sun.jna.Function;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ContentCryptoProvider.class */
class ContentCryptoProvider {
    public static final Map<Integer, Set<EncryptionMethod>> COMPATIBLE_ENCRYPTION_METHODS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(EncryptionMethod.A128CBC_HS256);
        linkedHashSet.add(EncryptionMethod.A192CBC_HS384);
        linkedHashSet.add(EncryptionMethod.A256CBC_HS512);
        linkedHashSet.add(EncryptionMethod.A128GCM);
        linkedHashSet.add(EncryptionMethod.A192GCM);
        linkedHashSet.add(EncryptionMethod.A256GCM);
        linkedHashSet.add(EncryptionMethod.A128CBC_HS256_DEPRECATED);
        linkedHashSet.add(EncryptionMethod.A256CBC_HS512_DEPRECATED);
        SUPPORTED_ENCRYPTION_METHODS = Collections.unmodifiableSet(linkedHashSet);
        HashMap hashMap = new HashMap();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        hashSet.add(EncryptionMethod.A128GCM);
        hashSet2.add(EncryptionMethod.A192GCM);
        hashSet3.add(EncryptionMethod.A256GCM);
        hashSet3.add(EncryptionMethod.A128CBC_HS256);
        hashSet3.add(EncryptionMethod.A128CBC_HS256_DEPRECATED);
        hashSet4.add(EncryptionMethod.A192CBC_HS384);
        hashSet5.add(EncryptionMethod.A256CBC_HS512);
        hashSet5.add(EncryptionMethod.A256CBC_HS512_DEPRECATED);
        hashMap.put(128, Collections.unmodifiableSet(hashSet));
        hashMap.put(192, Collections.unmodifiableSet(hashSet2));
        hashMap.put(Integer.valueOf(Function.MAX_NARGS), Collections.unmodifiableSet(hashSet3));
        hashMap.put(Integer.valueOf(Function.USE_VARARGS), Collections.unmodifiableSet(hashSet4));
        hashMap.put(Integer.valueOf(Cell.CELL_LEN), Collections.unmodifiableSet(hashSet5));
        COMPATIBLE_ENCRYPTION_METHODS = Collections.unmodifiableMap(hashMap);
    }

    ContentCryptoProvider() {
    }

    private static void checkCEKLength(SecretKey secretKey, EncryptionMethod encryptionMethod) throws KeyLengthException {
        try {
            if (encryptionMethod.cekBitLength() == ByteUtils.safeBitLength(secretKey.getEncoded())) {
                return;
            }
            throw new KeyLengthException("The Content Encryption Key (CEK) length for " + encryptionMethod + " must be " + encryptionMethod.cekBitLength() + " bits");
        } catch (IntegerOverflowException e) {
            throw new KeyLengthException("The Content Encryption Key (CEK) is too long: " + e.getMessage());
        }
    }

    public static byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, SecretKey secretKey, JWEJCAContext jWEJCAContext) throws JOSEException {
        byte[] decryptAuthenticated;
        checkCEKLength(secretKey, jWEHeader.getEncryptionMethod());
        byte[] compute = AAD.compute(jWEHeader);
        if (jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A128CBC_HS256) || jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A192CBC_HS384) || jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A256CBC_HS512)) {
            decryptAuthenticated = AESCBC.decryptAuthenticated(secretKey, base64URL2.decode(), base64URL3.decode(), compute, base64URL4.decode(), jWEJCAContext.getContentEncryptionProvider(), jWEJCAContext.getMACProvider());
        } else if (jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A128GCM) || jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A192GCM) || jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A256GCM)) {
            decryptAuthenticated = AESGCM.decrypt(secretKey, base64URL2.decode(), base64URL3.decode(), compute, base64URL4.decode(), jWEJCAContext.getContentEncryptionProvider());
        } else {
            if (!jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A128CBC_HS256_DEPRECATED) && !jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A256CBC_HS512_DEPRECATED)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedEncryptionMethod(jWEHeader.getEncryptionMethod(), SUPPORTED_ENCRYPTION_METHODS));
            }
            decryptAuthenticated = AESCBC.decryptWithConcatKDF(jWEHeader, secretKey, base64URL, base64URL2, base64URL3, base64URL4, jWEJCAContext.getContentEncryptionProvider(), jWEJCAContext.getMACProvider());
        }
        return DeflateHelper.applyDecompression(jWEHeader, decryptAuthenticated);
    }

    public static JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr, SecretKey secretKey, Base64URL base64URL, JWEJCAContext jWEJCAContext) throws JOSEException {
        byte[] generateIV;
        AuthenticatedCipherText encryptAuthenticated;
        checkCEKLength(secretKey, jWEHeader.getEncryptionMethod());
        byte[] applyCompression = DeflateHelper.applyCompression(jWEHeader, bArr);
        byte[] compute = AAD.compute(jWEHeader);
        if (jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A128CBC_HS256) || jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A192CBC_HS384) || jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A256CBC_HS512)) {
            generateIV = AESCBC.generateIV(jWEJCAContext.getSecureRandom());
            encryptAuthenticated = AESCBC.encryptAuthenticated(secretKey, generateIV, applyCompression, compute, jWEJCAContext.getContentEncryptionProvider(), jWEJCAContext.getMACProvider());
        } else if (jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A128GCM) || jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A192GCM) || jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A256GCM)) {
            Container container = new Container(AESGCM.generateIV(jWEJCAContext.getSecureRandom()));
            encryptAuthenticated = AESGCM.encrypt(secretKey, container, applyCompression, compute, jWEJCAContext.getContentEncryptionProvider());
            generateIV = (byte[]) container.get();
        } else {
            if (!jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A128CBC_HS256_DEPRECATED) && !jWEHeader.getEncryptionMethod().equals(EncryptionMethod.A256CBC_HS512_DEPRECATED)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedEncryptionMethod(jWEHeader.getEncryptionMethod(), SUPPORTED_ENCRYPTION_METHODS));
            }
            generateIV = AESCBC.generateIV(jWEJCAContext.getSecureRandom());
            encryptAuthenticated = AESCBC.encryptWithConcatKDF(jWEHeader, secretKey, base64URL, generateIV, applyCompression, jWEJCAContext.getContentEncryptionProvider(), jWEJCAContext.getMACProvider());
        }
        return new JWECryptoParts(jWEHeader, base64URL, Base64URL.m3704encode(generateIV), Base64URL.m3704encode(encryptAuthenticated.getCipherText()), Base64URL.m3704encode(encryptAuthenticated.getAuthenticationTag()));
    }

    public static SecretKey generateCEK(EncryptionMethod encryptionMethod, SecureRandom secureRandom) throws JOSEException {
        if (!SUPPORTED_ENCRYPTION_METHODS.contains(encryptionMethod)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedEncryptionMethod(encryptionMethod, SUPPORTED_ENCRYPTION_METHODS));
        }
        byte[] bArr = new byte[ByteUtils.byteLength(encryptionMethod.cekBitLength())];
        secureRandom.nextBytes(bArr);
        return new SecretKeySpec(bArr, "AES");
    }
}
