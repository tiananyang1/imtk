package com.subgraph.orchid.crypto;

import com.subgraph.orchid.TorException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/HybridEncryption.class */
public class HybridEncryption {
    private static final int PK_DATA_LEN = 86;
    private static final int PK_DATA_LEN_WITH_KEY = 70;
    private static final int PK_ENC_LEN = 128;
    private static final int PK_PAD_LEN = 42;
    private final Cipher cipher;

    public HybridEncryption() {
        try {
            this.cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new TorException(e);
        } catch (NoSuchPaddingException e2) {
            throw new TorException(e2);
        }
    }

    private byte[] decryptSimple(byte[] bArr, TorPrivateKey torPrivateKey) {
        try {
            this.cipher.init(2, torPrivateKey.getRSAPrivateKey());
            return this.cipher.doFinal(bArr);
        } catch (InvalidKeyException e) {
            throw new TorException(e);
        } catch (BadPaddingException e2) {
            throw new TorException(e2);
        } catch (IllegalBlockSizeException e3) {
            throw new TorException(e3);
        }
    }

    private byte[] encryptSimple(byte[] bArr, TorPublicKey torPublicKey) {
        try {
            this.cipher.init(1, torPublicKey.getRSAPublicKey());
            return this.cipher.doFinal(bArr);
        } catch (InvalidKeyException e) {
            throw new TorException(e);
        } catch (BadPaddingException e2) {
            throw new TorException(e2);
        } catch (IllegalBlockSizeException e3) {
            throw new TorException(e3);
        }
    }

    public byte[] decrypt(byte[] bArr, TorPrivateKey torPrivateKey) {
        if (bArr.length < 128) {
            throw new TorException("Message is too short");
        }
        if (bArr.length == 128) {
            return decryptSimple(bArr, torPrivateKey);
        }
        byte[] bArr2 = new byte[128];
        byte[] bArr3 = new byte[bArr.length - 128];
        System.arraycopy(bArr, 0, bArr2, 0, 128);
        System.arraycopy(bArr, 128, bArr3, 0, bArr3.length);
        byte[] decryptSimple = decryptSimple(bArr2, torPrivateKey);
        byte[] bArr4 = new byte[16];
        int length = decryptSimple.length - 16;
        byte[] bArr5 = new byte[length];
        System.arraycopy(decryptSimple, 0, bArr4, 0, 16);
        System.arraycopy(decryptSimple, 16, bArr5, 0, length);
        TorStreamCipher.createFromKeyBytes(bArr4).encrypt(bArr3);
        byte[] bArr6 = new byte[bArr5.length + bArr3.length];
        System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
        System.arraycopy(bArr3, 0, bArr6, bArr5.length, bArr3.length);
        return bArr6;
    }

    public byte[] encrypt(byte[] bArr, TorPublicKey torPublicKey) {
        if (bArr.length < 86) {
            return encryptSimple(bArr, torPublicKey);
        }
        TorStreamCipher createWithRandomKey = TorStreamCipher.createWithRandomKey();
        byte[] bArr2 = new byte[86];
        System.arraycopy(createWithRandomKey.getKeyBytes(), 0, bArr2, 0, 16);
        System.arraycopy(bArr, 0, bArr2, 16, 70);
        byte[] encryptSimple = encryptSimple(bArr2, torPublicKey);
        byte[] bArr3 = new byte[bArr.length - 70];
        System.arraycopy(bArr, 70, bArr3, 0, bArr3.length);
        createWithRandomKey.encrypt(bArr3);
        byte[] bArr4 = new byte[encryptSimple.length + bArr3.length];
        System.arraycopy(encryptSimple, 0, bArr4, 0, encryptSimple.length);
        System.arraycopy(bArr3, 0, bArr4, encryptSimple.length, bArr3.length);
        return bArr4;
    }
}
