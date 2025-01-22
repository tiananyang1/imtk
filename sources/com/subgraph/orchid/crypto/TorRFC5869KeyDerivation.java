package com.subgraph.orchid.crypto;

import com.subgraph.orchid.Tor;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorRFC5869KeyDerivation.class */
public class TorRFC5869KeyDerivation {
    private static final String M_EXPAND = "ntor-curve25519-sha256-1:key_expand";
    private static final byte[] M_EXPAND_BYTES = M_EXPAND.getBytes(Tor.getDefaultCharset());
    private static final String PROTOID = "ntor-curve25519-sha256-1";
    private final byte[] seed;

    public TorRFC5869KeyDerivation(byte[] bArr) {
        this.seed = new byte[bArr.length];
        System.arraycopy(bArr, 0, this.seed, 0, bArr.length);
    }

    private Mac createMacInstance() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.seed, "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return mac;
        } catch (InvalidKeyException e) {
            throw new IllegalStateException("Could not create HmacSHA256 instance: " + e);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("Could not create HmacSHA256 instance: " + e2);
        }
    }

    private byte[] expandRound(int i, byte[] bArr) {
        ByteBuffer byteBuffer;
        if (i == 1) {
            byteBuffer = makeBuffer(M_EXPAND_BYTES.length + 1);
        } else {
            ByteBuffer makeBuffer = makeBuffer(M_EXPAND_BYTES.length + 32 + 1);
            makeBuffer.put(bArr);
            byteBuffer = makeBuffer;
        }
        byteBuffer.put(M_EXPAND_BYTES);
        byteBuffer.put((byte) i);
        return createMacInstance().doFinal(byteBuffer.array());
    }

    private ByteBuffer makeBuffer(int i) {
        return ByteBuffer.wrap(new byte[i]);
    }

    public ByteBuffer deriveKeys(int i) {
        ByteBuffer makeBuffer = makeBuffer(i);
        int i2 = 1;
        byte[] bArr = null;
        while (makeBuffer.hasRemaining()) {
            bArr = expandRound(i2, bArr);
            if (bArr.length > makeBuffer.remaining()) {
                makeBuffer.put(bArr, 0, makeBuffer.remaining());
            } else {
                makeBuffer.put(bArr);
            }
            i2++;
        }
        makeBuffer.flip();
        return makeBuffer;
    }

    public void deriveKeys(byte[] bArr, byte[] bArr2) {
        ByteBuffer deriveKeys = deriveKeys(bArr.length + bArr2.length);
        deriveKeys.get(bArr);
        deriveKeys.get(bArr2);
    }
}
