package com.subgraph.orchid.crypto;

import com.subgraph.orchid.TorException;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.misc.Utils;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorPublicKey.class */
public class TorPublicKey {
    private RSAPublicKey key;
    private HexDigest keyFingerprint;
    private final String pemBuffer;
    private byte[] rawKeyBytes;

    private TorPublicKey(String str) {
        this.rawKeyBytes = null;
        this.keyFingerprint = null;
        this.pemBuffer = str;
        this.key = null;
    }

    public TorPublicKey(RSAPublicKey rSAPublicKey) {
        this.rawKeyBytes = null;
        this.keyFingerprint = null;
        this.pemBuffer = null;
        this.key = rSAPublicKey;
    }

    private Cipher createCipherInstance() {
        try {
            Cipher cipherInstance = getCipherInstance();
            cipherInstance.init(2, getKey());
            return cipherInstance;
        } catch (InvalidKeyException e) {
            throw new TorException(e);
        }
    }

    public static TorPublicKey createFromPEMBuffer(String str) {
        return new TorPublicKey(str);
    }

    private Cipher getCipherInstance() {
        try {
            try {
                return Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
            } catch (NoSuchProviderException e) {
                return Cipher.getInstance("RSA/ECB/PKCS1Padding");
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new TorException(e2);
        } catch (NoSuchPaddingException e3) {
            throw new TorException(e3);
        }
    }

    private RSAPublicKey getKey() {
        synchronized (this) {
            if (this.key != null) {
                return this.key;
            }
            if (this.pemBuffer != null) {
                try {
                    this.key = new RSAKeyEncoder().parsePEMPublicKey(this.pemBuffer);
                } catch (GeneralSecurityException e) {
                    throw new IllegalArgumentException("Failed to parse PEM encoded key: " + e);
                }
            }
            return this.key;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof TorPublicKey) {
            return ((TorPublicKey) obj).getFingerprint().equals(getFingerprint());
        }
        return false;
    }

    public HexDigest getFingerprint() {
        HexDigest hexDigest;
        synchronized (this) {
            if (this.keyFingerprint == null) {
                this.keyFingerprint = HexDigest.createDigestForData(getRawBytes());
            }
            hexDigest = this.keyFingerprint;
        }
        return hexDigest;
    }

    public RSAPublicKey getRSAPublicKey() {
        return getKey();
    }

    public byte[] getRawBytes() {
        byte[] bArr;
        synchronized (this) {
            if (this.rawKeyBytes == null) {
                this.rawKeyBytes = new RSAKeyEncoder().getPKCS1Encoded(getKey());
            }
            bArr = this.rawKeyBytes;
        }
        return bArr;
    }

    public int hashCode() {
        return getFingerprint().hashCode();
    }

    public String toString() {
        return "Tor Public Key: " + getFingerprint();
    }

    public boolean verifySignature(TorSignature torSignature, TorMessageDigest torMessageDigest) {
        return verifySignatureFromDigestBytes(torSignature, torMessageDigest.getDigestBytes());
    }

    public boolean verifySignature(TorSignature torSignature, HexDigest hexDigest) {
        return verifySignatureFromDigestBytes(torSignature, hexDigest.getRawBytes());
    }

    public boolean verifySignatureFromDigestBytes(TorSignature torSignature, byte[] bArr) {
        try {
            return Utils.constantTimeArrayEquals(createCipherInstance().doFinal(torSignature.getSignatureBytes()), bArr);
        } catch (BadPaddingException e) {
            throw new TorException(e);
        } catch (IllegalBlockSizeException e2) {
            throw new TorException(e2);
        }
    }
}
