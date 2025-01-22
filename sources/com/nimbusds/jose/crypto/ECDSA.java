package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.Curve;
import com.subgraph.orchid.Cell;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.ECKey;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ECDSA.class */
class ECDSA {
    private ECDSA() {
    }

    public static int getSignatureByteArrayLength(JWSAlgorithm jWSAlgorithm) throws JOSEException {
        if (jWSAlgorithm.equals(JWSAlgorithm.ES256)) {
            return 64;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.ES384)) {
            return 96;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.ES512)) {
            return Cell.AUTHORIZE;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, ECDSAProvider.SUPPORTED_ALGORITHMS));
    }

    public static Signature getSignerAndVerifier(JWSAlgorithm jWSAlgorithm, Provider provider) throws JOSEException {
        String str;
        if (jWSAlgorithm.equals(JWSAlgorithm.ES256)) {
            str = "SHA256withECDSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.ES384)) {
            str = "SHA384withECDSA";
        } else {
            if (!jWSAlgorithm.equals(JWSAlgorithm.ES512)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, ECDSAProvider.SUPPORTED_ALGORITHMS));
            }
            str = "SHA512withECDSA";
        }
        try {
            return provider != null ? Signature.getInstance(str, provider) : Signature.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new JOSEException("Unsupported ECDSA algorithm: " + e.getMessage(), e);
        }
    }

    public static JWSAlgorithm resolveAlgorithm(Curve curve) throws JOSEException {
        if (curve == null) {
            throw new JOSEException("The EC key curve is not supported, must be P-256, P-384 or P-521");
        }
        if (Curve.P_256.equals(curve)) {
            return JWSAlgorithm.ES256;
        }
        if (Curve.P_384.equals(curve)) {
            return JWSAlgorithm.ES384;
        }
        if (Curve.P_521.equals(curve)) {
            return JWSAlgorithm.ES512;
        }
        throw new JOSEException("Unexpected curve: " + curve);
    }

    public static JWSAlgorithm resolveAlgorithm(ECKey eCKey) throws JOSEException {
        return resolveAlgorithm(Curve.forECParameterSpec(eCKey.getParams()));
    }

    public static byte[] transcodeSignatureToConcat(byte[] bArr, int i) throws JOSEException {
        int i2;
        int i3;
        int i4;
        if (bArr.length < 8 || bArr[0] != 48) {
            throw new JOSEException("Invalid ECDSA signature format");
        }
        if (bArr[1] > 0) {
            i2 = 2;
        } else {
            if (bArr[1] != -127) {
                throw new JOSEException("Invalid ECDSA signature format");
            }
            i2 = 3;
        }
        byte b = bArr[i2 + 1];
        int i5 = b;
        while (true) {
            i3 = i5;
            if (i3 <= 0 || bArr[((i2 + 2) + b) - i3] != 0) {
                break;
            }
            i5 = i3 - 1;
        }
        int i6 = i2 + 2 + b;
        byte b2 = bArr[i6 + 1];
        int i7 = b2;
        while (true) {
            i4 = i7;
            if (i4 <= 0 || bArr[((i6 + 2) + b2) - i4] != 0) {
                break;
            }
            i7 = i4 - 1;
        }
        int max = Math.max(Math.max(i3, i4), i / 2);
        int i8 = i2 - 1;
        if ((bArr[i8] & 255) != bArr.length - i2 || (bArr[i8] & 255) != b + 2 + 2 + b2 || bArr[i2] != 2 || bArr[i6] != 2) {
            throw new JOSEException("Invalid ECDSA signature format");
        }
        int i9 = max * 2;
        byte[] bArr2 = new byte[i9];
        System.arraycopy(bArr, i6 - i3, bArr2, max - i3, i3);
        System.arraycopy(bArr, ((i6 + 2) + b2) - i4, bArr2, i9 - i4, i4);
        return bArr2;
    }

    public static byte[] transcodeSignatureToDER(byte[] bArr) throws JOSEException {
        int i;
        int i2;
        byte[] bArr2;
        int length = bArr.length / 2;
        int i3 = length;
        while (true) {
            i = i3;
            if (i <= 0 || bArr[length - i] != 0) {
                break;
            }
            i3 = i - 1;
        }
        int i4 = length - i;
        int i5 = bArr[i4] < 0 ? i + 1 : i;
        int i6 = length;
        while (true) {
            i2 = i6;
            if (i2 <= 0 || bArr[(length * 2) - i2] != 0) {
                break;
            }
            i6 = i2 - 1;
        }
        int i7 = (length * 2) - i2;
        int i8 = bArr[i7] < 0 ? i2 + 1 : i2;
        int i9 = i5 + 2 + 2 + i8;
        if (i9 > 255) {
            throw new JOSEException("Invalid ECDSA signature format");
        }
        int i10 = 1;
        if (i9 < 128) {
            bArr2 = new byte[i5 + 4 + 2 + i8];
        } else {
            bArr2 = new byte[i5 + 5 + 2 + i8];
            bArr2[1] = -127;
            i10 = 2;
        }
        bArr2[0] = 48;
        int i11 = i10 + 1;
        bArr2[i10] = (byte) i9;
        int i12 = i11 + 1;
        bArr2[i11] = 2;
        bArr2[i12] = (byte) i5;
        int i13 = i12 + 1 + i5;
        System.arraycopy(bArr, i4, bArr2, i13 - i, i);
        int i14 = i13 + 1;
        bArr2[i13] = 2;
        bArr2[i14] = (byte) i8;
        System.arraycopy(bArr, i7, bArr2, ((i14 + 1) + i8) - i2, i2);
        return bArr2;
    }
}
