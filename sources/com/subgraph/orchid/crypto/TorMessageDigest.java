package com.subgraph.orchid.crypto;

import com.subgraph.orchid.TorException;
import com.subgraph.orchid.data.HexDigest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorMessageDigest.class */
public class TorMessageDigest {
    private static final String TOR_DIGEST256_ALGORITHM = "SHA-256";
    public static final int TOR_DIGEST256_SIZE = 32;
    private static final String TOR_DIGEST_ALGORITHM = "SHA-1";
    public static final int TOR_DIGEST_SIZE = 20;
    private final MessageDigest digestInstance;
    private final boolean isDigest256;

    public TorMessageDigest() {
        this(false);
    }

    public TorMessageDigest(boolean z) {
        this.digestInstance = createDigestInstance(z);
        this.isDigest256 = z;
    }

    private MessageDigest createDigestInstance(boolean z) {
        try {
            return MessageDigest.getInstance(z ? "SHA-256" : "SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new TorException(e);
        }
    }

    public byte[] getDigestBytes() {
        try {
            return ((MessageDigest) this.digestInstance.clone()).digest();
        } catch (CloneNotSupportedException e) {
            throw new TorException(e);
        }
    }

    public HexDigest getHexDigest() {
        return HexDigest.createFromDigestBytes(getDigestBytes());
    }

    public boolean isDigest256() {
        return this.isDigest256;
    }

    public byte[] peekDigest(byte[] bArr, int i, int i2) {
        try {
            MessageDigest messageDigest = (MessageDigest) this.digestInstance.clone();
            messageDigest.update(bArr, i, i2);
            return messageDigest.digest();
        } catch (CloneNotSupportedException e) {
            throw new TorException(e);
        }
    }

    public void update(String str) {
        try {
            this.digestInstance.update(str.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            throw new TorException(e);
        }
    }

    public void update(byte[] bArr) {
        this.digestInstance.update(bArr);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.digestInstance.update(bArr, i, i2);
    }
}
