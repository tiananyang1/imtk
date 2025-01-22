package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.IntegerUtils;
import com.nimbusds.jose.util.StandardCharset;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/PBKDF2.class */
class PBKDF2 {
    public static byte[] ZERO_BYTE = new byte[1];

    private PBKDF2() {
    }

    public static SecretKey deriveKey(byte[] bArr, byte[] bArr2, int i, PRFParams pRFParams) throws JOSEException {
        Mac initMac = HMAC.getInitMac(new SecretKeySpec(bArr, pRFParams.getMACAlgorithm()), pRFParams.getMacProvider());
        int macLength = initMac.getMacLength();
        if (pRFParams.getDerivedKeyByteLength() > 4294967295L) {
            throw new JOSEException("derived key too long " + pRFParams.getDerivedKeyByteLength());
        }
        int ceil = (int) Math.ceil(pRFParams.getDerivedKeyByteLength() / macLength);
        int derivedKeyByteLength = pRFParams.getDerivedKeyByteLength();
        int i2 = ceil - 1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= ceil) {
                return new SecretKeySpec(byteArrayOutputStream.toByteArray(), "AES");
            }
            int i5 = i4 + 1;
            byte[] extractBlock = extractBlock(bArr2, i, i5, initMac);
            byte[] bArr3 = extractBlock;
            if (i4 == i2) {
                bArr3 = ByteUtils.subArray(extractBlock, 0, derivedKeyByteLength - (macLength * i2));
            }
            byteArrayOutputStream.write(bArr3, 0, bArr3.length);
            i3 = i5;
        }
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [byte[], byte[][]] */
    private static byte[] extractBlock(byte[] bArr, int i, int i2, Mac mac) {
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        int i3 = 1;
        while (true) {
            int i4 = i3;
            if (i4 > i) {
                return bArr2;
            }
            if (i4 == 1) {
                bArr2 = mac.doFinal(ByteUtils.concat(new byte[]{bArr, IntegerUtils.toBytes(i2)}));
                bArr3 = bArr2;
            } else {
                bArr3 = mac.doFinal(bArr3);
                for (int i5 = 0; i5 < bArr3.length; i5++) {
                    bArr2[i5] = (byte) (bArr3[i5] ^ bArr2[i5]);
                }
            }
            i3 = i4 + 1;
        }
    }

    public static byte[] formatSalt(JWEAlgorithm jWEAlgorithm, byte[] bArr) throws JOSEException {
        byte[] bytes = jWEAlgorithm.toString().getBytes(StandardCharset.UTF_8);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(ZERO_BYTE);
            byteArrayOutputStream.write(bArr);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }
}
