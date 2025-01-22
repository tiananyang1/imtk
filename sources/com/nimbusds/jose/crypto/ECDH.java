package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.sun.jna.Function;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ECDH.class */
class ECDH {

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ECDH$AlgorithmMode.class */
    public enum AlgorithmMode {
        DIRECT,
        KW;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static AlgorithmMode[] valuesCustom() {
            AlgorithmMode[] valuesCustom = values();
            int length = valuesCustom.length;
            AlgorithmMode[] algorithmModeArr = new AlgorithmMode[length];
            System.arraycopy(valuesCustom, 0, algorithmModeArr, 0, length);
            return algorithmModeArr;
        }
    }

    private ECDH() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SecretKey deriveSharedKey(JWEHeader jWEHeader, SecretKey secretKey, ConcatKDF concatKDF) throws JOSEException {
        String name;
        int sharedKeyLength = sharedKeyLength(jWEHeader.getAlgorithm(), jWEHeader.getEncryptionMethod());
        AlgorithmMode resolveAlgorithmMode = resolveAlgorithmMode(jWEHeader.getAlgorithm());
        if (resolveAlgorithmMode == AlgorithmMode.DIRECT) {
            name = jWEHeader.getEncryptionMethod().getName();
        } else {
            if (resolveAlgorithmMode != AlgorithmMode.KW) {
                throw new JOSEException("Unsupported JWE ECDH algorithm mode: " + resolveAlgorithmMode);
            }
            name = jWEHeader.getAlgorithm().getName();
        }
        return concatKDF.deriveKey(secretKey, sharedKeyLength, ConcatKDF.encodeDataWithLength(name.getBytes(Charset.forName("ASCII"))), ConcatKDF.encodeDataWithLength(jWEHeader.getAgreementPartyUInfo()), ConcatKDF.encodeDataWithLength(jWEHeader.getAgreementPartyVInfo()), ConcatKDF.encodeIntData(sharedKeyLength), ConcatKDF.encodeNoData());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SecretKey deriveSharedSecret(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey, Provider provider) throws JOSEException {
        try {
            KeyAgreement keyAgreement = provider != null ? KeyAgreement.getInstance("ECDH", provider) : KeyAgreement.getInstance("ECDH");
            try {
                keyAgreement.init(eCPrivateKey);
                keyAgreement.doPhase(eCPublicKey, true);
                return new SecretKeySpec(keyAgreement.generateSecret(), "AES");
            } catch (InvalidKeyException e) {
                throw new JOSEException("Invalid key for ECDH key agreement: " + e.getMessage(), e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new JOSEException("Couldn't get an ECDH key agreement instance: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmMode resolveAlgorithmMode(JWEAlgorithm jWEAlgorithm) throws JOSEException {
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES)) {
            return AlgorithmMode.DIRECT;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A128KW) || jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A192KW) || jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A256KW)) {
            return AlgorithmMode.KW;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(jWEAlgorithm, ECDHCryptoProvider.SUPPORTED_ALGORITHMS));
    }

    static int sharedKeyLength(JWEAlgorithm jWEAlgorithm, EncryptionMethod encryptionMethod) throws JOSEException {
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES)) {
            int cekBitLength = encryptionMethod.cekBitLength();
            if (cekBitLength != 0) {
                return cekBitLength;
            }
            throw new JOSEException("Unsupported JWE encryption method " + encryptionMethod);
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A128KW)) {
            return 128;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A192KW)) {
            return 192;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A256KW)) {
            return Function.MAX_NARGS;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(jWEAlgorithm, ECDHCryptoProvider.SUPPORTED_ALGORITHMS));
    }
}
