package com.subgraph.orchid.crypto;

import com.subgraph.orchid.TorException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorStreamCipher.class */
public class TorStreamCipher {
    private static final int BLOCK_SIZE = 16;
    public static final int KEY_LEN = 16;
    private final Cipher cipher;
    private final byte[] counter;
    private final byte[] counterOut;
    private final SecretKeySpec key;
    private int keystreamPointer;

    private TorStreamCipher(byte[] bArr) {
        this(bArr, null);
    }

    private TorStreamCipher(byte[] bArr, byte[] bArr2) {
        this.keystreamPointer = -1;
        this.key = keyBytesToSecretKey(bArr);
        this.cipher = createCipher(this.key);
        this.counter = new byte[16];
        this.counterOut = new byte[16];
        if (bArr2 != null) {
            applyIV(bArr2);
        }
    }

    private void applyIV(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(bArr, 0, this.counter, 0, 16);
    }

    private static Cipher createCipher(SecretKeySpec secretKeySpec) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(1, secretKeySpec);
            return cipher;
        } catch (GeneralSecurityException e) {
            throw new TorException(e);
        }
    }

    public static TorStreamCipher createFromKeyBytes(byte[] bArr) {
        return new TorStreamCipher(bArr);
    }

    public static TorStreamCipher createFromKeyBytesWithIV(byte[] bArr, byte[] bArr2) {
        return new TorStreamCipher(bArr, bArr2);
    }

    public static TorStreamCipher createWithRandomKey() {
        return new TorStreamCipher(generateRandomKey().getEncoded());
    }

    private void encryptCounter() {
        try {
            this.cipher.doFinal(this.counter, 0, 16, this.counterOut, 0);
        } catch (GeneralSecurityException e) {
            throw new TorException(e);
        }
    }

    private static SecretKey generateRandomKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return keyGenerator.generateKey();
        } catch (GeneralSecurityException e) {
            throw new TorException(e);
        }
    }

    private void incrementCounter() {
        int i = 1;
        for (int length = this.counter.length - 1; length >= 0; length--) {
            int i2 = (this.counter[length] & 255) + i;
            i = i2 > 255 ? 1 : 0;
            this.counter[length] = (byte) i2;
        }
    }

    private static SecretKeySpec keyBytesToSecretKey(byte[] bArr) {
        return new SecretKeySpec(bArr, "AES");
    }

    private byte nextKeystreamByte() {
        int i = this.keystreamPointer;
        if (i == -1 || i >= 16) {
            updateCounter();
        }
        byte[] bArr = this.counterOut;
        int i2 = this.keystreamPointer;
        this.keystreamPointer = i2 + 1;
        return bArr[i2];
    }

    private void updateCounter() {
        encryptCounter();
        incrementCounter();
        this.keystreamPointer = 0;
    }

    public void encrypt(byte[] bArr) {
        encrypt(bArr, 0, bArr.length);
    }

    public void encrypt(byte[] bArr, int i, int i2) {
        synchronized (this) {
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < i2) {
                    int i5 = i4 + i;
                    bArr[i5] = (byte) (bArr[i5] ^ nextKeystreamByte());
                    i3 = i4 + 1;
                }
            }
        }
    }

    public byte[] getKeyBytes() {
        return this.key.getEncoded();
    }
}
