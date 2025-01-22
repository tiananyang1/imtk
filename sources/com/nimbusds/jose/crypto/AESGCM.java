package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.Container;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AESGCM.class */
class AESGCM {
    public static final int AUTH_TAG_BIT_LENGTH = 128;
    public static final int IV_BIT_LENGTH = 96;

    private AESGCM() {
    }

    private static byte[] actualIVOf(Cipher cipher) throws JOSEException {
        GCMParameterSpec actualParamsOf = actualParamsOf(cipher);
        byte[] iv = actualParamsOf.getIV();
        validate(iv, actualParamsOf.getTLen());
        return iv;
    }

    private static GCMParameterSpec actualParamsOf(Cipher cipher) throws JOSEException {
        AlgorithmParameters parameters = cipher.getParameters();
        if (parameters == null) {
            throw new JOSEException("AES GCM ciphers are expected to make use of algorithm parameters");
        }
        try {
            return (GCMParameterSpec) parameters.getParameterSpec(GCMParameterSpec.class);
        } catch (InvalidParameterSpecException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    /* JADX WARN: Type inference failed for: r1v12, types: [byte[], byte[][]] */
    public static byte[] decrypt(SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, Provider provider) throws JOSEException {
        try {
            Cipher cipher = provider != null ? Cipher.getInstance("AES/GCM/NoPadding", provider) : Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, secretKey, new GCMParameterSpec(128, bArr));
            cipher.updateAAD(bArr3);
            try {
                return cipher.doFinal(ByteUtils.concat(new byte[]{bArr2, bArr4}));
            } catch (BadPaddingException | IllegalBlockSizeException e) {
                throw new JOSEException("AES/GCM/NoPadding decryption failed: " + e.getMessage(), e);
            }
        } catch (NoClassDefFoundError e2) {
            return LegacyAESGCM.decrypt(secretKey, bArr, bArr2, bArr3, bArr4);
        } catch (InvalidAlgorithmParameterException e3) {
            e = e3;
            throw new JOSEException("Couldn't create AES/GCM/NoPadding cipher: " + e.getMessage(), e);
        } catch (InvalidKeyException e4) {
            e = e4;
            throw new JOSEException("Couldn't create AES/GCM/NoPadding cipher: " + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            throw new JOSEException("Couldn't create AES/GCM/NoPadding cipher: " + e.getMessage(), e);
        } catch (NoSuchPaddingException e6) {
            e = e6;
            throw new JOSEException("Couldn't create AES/GCM/NoPadding cipher: " + e.getMessage(), e);
        }
    }

    public static AuthenticatedCipherText encrypt(SecretKey secretKey, Container<byte[]> container, byte[] bArr, byte[] bArr2, Provider provider) throws JOSEException {
        byte[] bArr3 = container.get();
        try {
            Cipher cipher = provider != null ? Cipher.getInstance("AES/GCM/NoPadding", provider) : Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, secretKey, new GCMParameterSpec(128, bArr3));
            cipher.updateAAD(bArr2);
            try {
                byte[] doFinal = cipher.doFinal(bArr);
                int length = doFinal.length - ByteUtils.byteLength(128);
                byte[] subArray = ByteUtils.subArray(doFinal, 0, length);
                byte[] subArray2 = ByteUtils.subArray(doFinal, length, ByteUtils.byteLength(128));
                container.set(actualIVOf(cipher));
                return new AuthenticatedCipherText(subArray, subArray2);
            } catch (BadPaddingException | IllegalBlockSizeException e) {
                throw new JOSEException("Couldn't encrypt with AES/GCM/NoPadding: " + e.getMessage(), e);
            }
        } catch (NoClassDefFoundError e2) {
            return LegacyAESGCM.encrypt(secretKey, bArr3, bArr, bArr2);
        } catch (InvalidAlgorithmParameterException e3) {
            e = e3;
            throw new JOSEException("Couldn't create AES/GCM/NoPadding cipher: " + e.getMessage(), e);
        } catch (InvalidKeyException e4) {
            e = e4;
            throw new JOSEException("Couldn't create AES/GCM/NoPadding cipher: " + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            throw new JOSEException("Couldn't create AES/GCM/NoPadding cipher: " + e.getMessage(), e);
        } catch (NoSuchPaddingException e6) {
            e = e6;
            throw new JOSEException("Couldn't create AES/GCM/NoPadding cipher: " + e.getMessage(), e);
        }
    }

    public static byte[] generateIV(SecureRandom secureRandom) {
        byte[] bArr = new byte[12];
        secureRandom.nextBytes(bArr);
        return bArr;
    }

    private static void validate(byte[] bArr, int i) throws JOSEException {
        if (ByteUtils.safeBitLength(bArr) != 96) {
            throw new JOSEException(String.format("IV length of %d bits is required, got %d", 96, Integer.valueOf(ByteUtils.safeBitLength(bArr))));
        }
        if (i != 128) {
            throw new JOSEException(String.format("Authentication tag length of %d bits is required, got %d", 128, Integer.valueOf(i)));
        }
    }
}
