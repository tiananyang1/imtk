package com.subgraph.orchid.crypto;

import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.misc.Utils;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorNTorKeyAgreement.class */
public class TorNTorKeyAgreement implements TorKeyAgreement {
    static final int AUTH_INPUT_LEN = 178;
    static final int CURVE25519_OUTPUT_LEN = 32;
    public static final int CURVE25519_PUBKEY_LEN = 32;
    static final int DIGEST256_LEN = 32;
    static final int DIGEST_LEN = 20;
    static final int KEY_LEN = 16;
    static final int NTOR_ONIONSKIN_LEN = 84;
    static final String PROTOID = "ntor-curve25519-sha256-1";
    static final int SECRET_INPUT_LEN = 204;
    static final String SERVER_STR = "Server";

    /* renamed from: cs */
    static final Charset f193cs = Charset.forName("ISO-8859-1");
    private boolean isBad;
    private final HexDigest peerIdentity;
    private final byte[] peerNTorOnionKey;
    private final TorRandom random = new TorRandom();
    private final byte[] secretKey_x = generateSecretKey();
    private final byte[] publicKey_X = getPublicKeyForPrivate(this.secretKey_x);

    public TorNTorKeyAgreement(HexDigest hexDigest, byte[] bArr) {
        this.peerIdentity = hexDigest;
        this.peerNTorOnionKey = bArr;
    }

    private byte[] buildAuthInput(byte[] bArr, byte[] bArr2) {
        ByteBuffer makeBuffer = makeBuffer(AUTH_INPUT_LEN);
        makeBuffer.put(bArr);
        makeBuffer.put(this.peerIdentity.getRawBytes());
        makeBuffer.put(this.peerNTorOnionKey);
        makeBuffer.put(bArr2);
        makeBuffer.put(this.publicKey_X);
        makeBuffer.put(PROTOID.getBytes(f193cs));
        makeBuffer.put("Server".getBytes(f193cs));
        return makeBuffer.array();
    }

    private byte[] buildSecretInput(byte[] bArr) {
        ByteBuffer makeBuffer = makeBuffer(SECRET_INPUT_LEN);
        makeBuffer.put(scalarMult(bArr));
        makeBuffer.put(scalarMult(this.peerNTorOnionKey));
        makeBuffer.put(this.peerIdentity.getRawBytes());
        makeBuffer.put(this.peerNTorOnionKey);
        makeBuffer.put(this.publicKey_X);
        makeBuffer.put(bArr);
        makeBuffer.put(PROTOID.getBytes());
        return makeBuffer.array();
    }

    private ByteBuffer makeBuffer(int i) {
        return ByteBuffer.wrap(new byte[i]);
    }

    private byte[] scalarMult(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        Curve25519.crypto_scalarmult(bArr2, this.secretKey_x, bArr);
        this.isBad |= isAllZero(bArr2);
        return bArr2;
    }

    @Override // com.subgraph.orchid.crypto.TorKeyAgreement
    public byte[] createOnionSkin() {
        ByteBuffer makeBuffer = makeBuffer(84);
        makeBuffer.put(this.peerIdentity.getRawBytes());
        makeBuffer.put(this.peerNTorOnionKey);
        makeBuffer.put(this.publicKey_X);
        return makeBuffer.array();
    }

    @Override // com.subgraph.orchid.crypto.TorKeyAgreement
    public boolean deriveKeysFromHandshakeResponse(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.isBad = false;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        byte[] bArr4 = new byte[32];
        wrap.get(bArr4);
        wrap.get(new byte[32]);
        byte[] buildSecretInput = buildSecretInput(bArr4);
        byte[] tweak = tweak("mac", buildAuthInput(tweak("verify", buildSecretInput), bArr4));
        this.isBad = (!Utils.constantTimeArrayEquals(tweak, r0)) | this.isBad;
        new TorRFC5869KeyDerivation(tweak("key_extract", buildSecretInput)).deriveKeys(bArr2, bArr3);
        return !this.isBad;
    }

    byte[] generateSecretKey() {
        byte[] bytes = this.random.getBytes(32);
        bytes[0] = (byte) (bytes[0] & 248);
        bytes[31] = (byte) (bytes[31] & Byte.MAX_VALUE);
        bytes[31] = (byte) (bytes[31] | 64);
        return bytes;
    }

    public byte[] getNtorCreateMagic() {
        return "ntorNTORntorNTOR".getBytes(f193cs);
    }

    byte[] getPublicKeyForPrivate(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        Curve25519.crypto_scalarmult_base(bArr2, bArr);
        return bArr2;
    }

    byte[] getStringConstant(String str) {
        if (str == null || str.isEmpty()) {
            return PROTOID.getBytes(f193cs);
        }
        return ("ntor-curve25519-sha256-1:" + str).getBytes(f193cs);
    }

    byte[] hmac256(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return mac.doFinal(bArr);
        } catch (InvalidKeyException e) {
            throw new IllegalStateException("Failed to create HmacSHA256 instance: " + e);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("Failed to create HmacSHA256 instance: " + e2);
        }
    }

    boolean isAllZero(byte[] bArr) {
        boolean z = true;
        for (byte b : bArr) {
            z &= b == 0;
        }
        return z;
    }

    byte[] tweak(String str, byte[] bArr) {
        return hmac256(bArr, getStringConstant(str));
    }
}
