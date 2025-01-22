package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/LegacyAESGCM.class */
class LegacyAESGCM {
    public static final int AUTH_TAG_BIT_LENGTH = 128;

    private LegacyAESGCM() {
    }

    public static AESEngine createAESCipher(SecretKey secretKey, boolean z) {
        AESEngine aESEngine = new AESEngine();
        aESEngine.init(z, new KeyParameter(secretKey.getEncoded()));
        return aESEngine;
    }

    private static GCMBlockCipher createAESGCMCipher(SecretKey secretKey, boolean z, byte[] bArr, byte[] bArr2) {
        GCMBlockCipher gCMBlockCipher = new GCMBlockCipher(createAESCipher(secretKey, z));
        gCMBlockCipher.init(z, new AEADParameters(new KeyParameter(secretKey.getEncoded()), 128, bArr, bArr2));
        return gCMBlockCipher;
    }

    public static byte[] decrypt(SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws JOSEException {
        GCMBlockCipher createAESGCMCipher = createAESGCMCipher(secretKey, false, bArr, bArr3);
        byte[] bArr5 = new byte[bArr2.length + bArr4.length];
        System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
        System.arraycopy(bArr4, 0, bArr5, bArr2.length, bArr4.length);
        byte[] bArr6 = new byte[createAESGCMCipher.getOutputSize(bArr5.length)];
        try {
            createAESGCMCipher.doFinal(bArr6, createAESGCMCipher.processBytes(bArr5, 0, bArr5.length, bArr6, 0));
            return bArr6;
        } catch (InvalidCipherTextException e) {
            throw new JOSEException("Couldn't validate GCM authentication tag: " + e.getMessage(), e);
        }
    }

    public static AuthenticatedCipherText encrypt(SecretKey secretKey, byte[] bArr, byte[] bArr2, byte[] bArr3) throws JOSEException {
        GCMBlockCipher createAESGCMCipher = createAESGCMCipher(secretKey, true, bArr, bArr3);
        byte[] bArr4 = new byte[createAESGCMCipher.getOutputSize(bArr2.length)];
        int processBytes = createAESGCMCipher.processBytes(bArr2, 0, bArr2.length, bArr4, 0);
        try {
            int doFinal = (processBytes + createAESGCMCipher.doFinal(bArr4, processBytes)) - 16;
            byte[] bArr5 = new byte[doFinal];
            byte[] bArr6 = new byte[16];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr5.length);
            System.arraycopy(bArr4, doFinal, bArr6, 0, bArr6.length);
            return new AuthenticatedCipherText(bArr5, bArr6);
        } catch (InvalidCipherTextException e) {
            throw new JOSEException("Couldn't generate GCM authentication tag: " + e.getMessage(), e);
        }
    }
}
