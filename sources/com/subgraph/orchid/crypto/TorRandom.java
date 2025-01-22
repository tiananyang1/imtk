package com.subgraph.orchid.crypto;

import com.subgraph.orchid.TorException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorRandom.class */
public class TorRandom {
    private final SecureRandom random = createRandom();

    private static SecureRandom createRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new TorException(e);
        }
    }

    public byte[] getBytes(int i) {
        byte[] bArr = new byte[i];
        this.random.nextBytes(bArr);
        return bArr;
    }

    public int nextInt() {
        return this.random.nextInt() & Integer.MAX_VALUE;
    }

    public int nextInt(int i) {
        return this.random.nextInt(i);
    }

    public long nextLong() {
        return this.random.nextLong() & Long.MAX_VALUE;
    }

    public long nextLong(long j) {
        long nextLong;
        long j2;
        do {
            nextLong = nextLong();
            j2 = nextLong % j;
        } while ((nextLong - j2) + (j - 1) < 0);
        return j2;
    }
}
