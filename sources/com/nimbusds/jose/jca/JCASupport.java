package com.nimbusds.jose.jca;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jca/JCASupport.class */
public final class JCASupport {
    private JCASupport() {
    }

    public static boolean isSupported(EncryptionMethod encryptionMethod) {
        Provider[] providers = Security.getProviders();
        int length = providers.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return false;
            }
            if (isSupported(encryptionMethod, providers[i2])) {
                return true;
            }
            i = i2 + 1;
        }
    }

    public static boolean isSupported(EncryptionMethod encryptionMethod, Provider provider) {
        if (EncryptionMethod.Family.AES_CBC_HMAC_SHA.contains(encryptionMethod)) {
            try {
                Cipher.getInstance("AES/CBC/PKCS5Padding", provider);
                return provider.getService("KeyGenerator", encryptionMethod.equals(EncryptionMethod.A128CBC_HS256) ? "HmacSHA256" : encryptionMethod.equals(EncryptionMethod.A192CBC_HS384) ? "HmacSHA384" : "HmacSHA512") != null;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                return false;
            }
        }
        if (!EncryptionMethod.Family.AES_GCM.contains(encryptionMethod)) {
            return false;
        }
        try {
            Cipher.getInstance("AES/GCM/NoPadding", provider);
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            return false;
        }
    }

    public static boolean isSupported(JWEAlgorithm jWEAlgorithm) {
        Provider[] providers = Security.getProviders();
        int length = providers.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return false;
            }
            if (isSupported(jWEAlgorithm, providers[i2])) {
                return true;
            }
            i = i2 + 1;
        }
    }

    public static boolean isSupported(JWEAlgorithm jWEAlgorithm, Provider provider) {
        String str;
        if (JWEAlgorithm.Family.RSA.contains(jWEAlgorithm)) {
            if (jWEAlgorithm.equals(JWEAlgorithm.RSA1_5)) {
                str = "RSA/ECB/PKCS1Padding";
            } else if (jWEAlgorithm.equals(JWEAlgorithm.RSA_OAEP)) {
                str = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
            } else {
                if (!jWEAlgorithm.equals(JWEAlgorithm.RSA_OAEP_256)) {
                    return false;
                }
                str = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
            }
            try {
                Cipher.getInstance(str, provider);
                return true;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                return false;
            }
        }
        if (JWEAlgorithm.Family.AES_KW.contains(jWEAlgorithm)) {
            return provider.getService("Cipher", "AESWrap") != null;
        }
        if (JWEAlgorithm.Family.ECDH_ES.contains(jWEAlgorithm)) {
            return provider.getService("KeyAgreement", "ECDH") != null;
        }
        if (JWEAlgorithm.Family.AES_GCM_KW.contains(jWEAlgorithm)) {
            try {
                Cipher.getInstance("AES/GCM/NoPadding", provider);
                return true;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
                return false;
            }
        }
        if (JWEAlgorithm.Family.PBES2.contains(jWEAlgorithm)) {
            return provider.getService("KeyGenerator", jWEAlgorithm.equals(JWEAlgorithm.PBES2_HS256_A128KW) ? "HmacSHA256" : jWEAlgorithm.equals(JWEAlgorithm.PBES2_HS384_A192KW) ? "HmacSHA384" : "HmacSHA512") != null;
        }
        return JWEAlgorithm.DIR.equals(jWEAlgorithm);
    }

    public static boolean isSupported(JWSAlgorithm jWSAlgorithm) {
        if (jWSAlgorithm.getName().equals(Algorithm.NONE.getName())) {
            return true;
        }
        Provider[] providers = Security.getProviders();
        int length = providers.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return false;
            }
            if (isSupported(jWSAlgorithm, providers[i2])) {
                return true;
            }
            i = i2 + 1;
        }
    }

    public static boolean isSupported(JWSAlgorithm jWSAlgorithm, Provider provider) {
        String str;
        String str2;
        String str3;
        if (JWSAlgorithm.Family.HMAC_SHA.contains(jWSAlgorithm)) {
            if (jWSAlgorithm.equals(JWSAlgorithm.HS256)) {
                str3 = "HMACSHA256";
            } else if (jWSAlgorithm.equals(JWSAlgorithm.HS384)) {
                str3 = "HMACSHA384";
            } else {
                if (!jWSAlgorithm.equals(JWSAlgorithm.HS512)) {
                    return false;
                }
                str3 = "HMACSHA512";
            }
            return provider.getService("KeyGenerator", str3) != null;
        }
        if (!JWSAlgorithm.Family.RSA.contains(jWSAlgorithm)) {
            if (!JWSAlgorithm.Family.f81EC.contains(jWSAlgorithm)) {
                return false;
            }
            if (jWSAlgorithm.equals(JWSAlgorithm.ES256)) {
                str = "SHA256withECDSA";
            } else if (jWSAlgorithm.equals(JWSAlgorithm.ES384)) {
                str = "SHA384withECDSA";
            } else {
                if (!jWSAlgorithm.equals(JWSAlgorithm.ES512)) {
                    return false;
                }
                str = "SHA512withECDSA";
            }
            return provider.getService("Signature", str) != null;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.RS256)) {
            str2 = "SHA256withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.RS384)) {
            str2 = "SHA384withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.RS512)) {
            str2 = "SHA512withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.PS256)) {
            str2 = "SHA256withRSAandMGF1";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.PS384)) {
            str2 = "SHA384withRSAandMGF1";
        } else {
            if (!jWSAlgorithm.equals(JWSAlgorithm.PS512)) {
                return false;
            }
            str2 = "SHA512withRSAandMGF1";
        }
        return provider.getService("Signature", str2) != null;
    }

    public static boolean isUnlimitedStrength() {
        boolean z = false;
        try {
            if (Cipher.getMaxAllowedKeyLength("AES") >= 256) {
                z = true;
            }
            return z;
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }
}
