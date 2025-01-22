package com.subgraph.orchid.crypto;

import com.subgraph.orchid.TorException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Arrays;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorTapKeyAgreement.class */
public class TorTapKeyAgreement implements TorKeyAgreement {
    public static final int DH_LEN = 128;
    public static final int DH_SEC_LEN = 40;

    /* renamed from: dh */
    private final KeyAgreement f195dh;
    private final KeyPair keyPair;
    private final TorPublicKey onionKey;
    private static final BigInteger P1024 = new BigInteger("00FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE65381FFFFFFFFFFFFFFFF", 16);

    /* renamed from: G */
    private static final BigInteger f194G = new BigInteger("2");
    private static final int PRIVATE_KEY_SIZE = 320;
    private static final DHParameterSpec DH_PARAMETER_SPEC = new DHParameterSpec(P1024, f194G, PRIVATE_KEY_SIZE);

    public TorTapKeyAgreement() {
        this(null);
    }

    public TorTapKeyAgreement(TorPublicKey torPublicKey) {
        this.keyPair = generateKeyPair();
        this.f195dh = createDH();
        this.onionKey = torPublicKey;
    }

    private final KeyAgreement createDH() {
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(this.keyPair.getPrivate());
            return keyAgreement;
        } catch (GeneralSecurityException e) {
            throw new TorException(e);
        }
    }

    private final KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
            keyPairGenerator.initialize(DH_PARAMETER_SPEC);
            return keyPairGenerator.generateKeyPair();
        } catch (GeneralSecurityException e) {
            throw new TorException(e);
        }
    }

    public static boolean isValidPublicValue(BigInteger bigInteger) {
        return bigInteger.signum() >= 1 && !bigInteger.equals(BigInteger.ONE) && bigInteger.compareTo(P1024.subtract(BigInteger.ONE)) < 0;
    }

    @Override // com.subgraph.orchid.crypto.TorKeyAgreement
    public byte[] createOnionSkin() {
        return new HybridEncryption().encrypt(getPublicKeyBytes(), this.onionKey);
    }

    public boolean deriveKeysFromDHPublicAndHash(BigInteger bigInteger, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (!isValidPublicValue(bigInteger)) {
            throw new TorException("Illegal DH public value");
        }
        new TorKeyDerivation(getSharedSecret(bigInteger)).deriveKeys(bArr2, bArr3);
        return Arrays.equals(bArr3, bArr);
    }

    @Override // com.subgraph.orchid.crypto.TorKeyAgreement
    public boolean deriveKeysFromHandshakeResponse(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        byte[] bArr4 = new byte[128];
        byte[] bArr5 = new byte[20];
        wrap.get(bArr4);
        wrap.get(bArr5);
        return deriveKeysFromDHPublicAndHash(new BigInteger(1, bArr4), bArr5, bArr2, bArr3);
    }

    public byte[] getPublicKeyBytes() {
        byte[] bArr = new byte[128];
        byte[] byteArray = getPublicValue().toByteArray();
        if (byteArray[0] == 0 && byteArray.length == 129) {
            System.arraycopy(byteArray, 1, bArr, 0, 128);
            return bArr;
        }
        if (byteArray.length > 128) {
            throw new IllegalStateException("Public value is longer than DH_LEN but not because of sign bit");
        }
        System.arraycopy(byteArray, 0, bArr, 128 - byteArray.length, byteArray.length);
        return bArr;
    }

    public BigInteger getPublicValue() {
        return ((DHPublicKey) this.keyPair.getPublic()).getY();
    }

    public byte[] getSharedSecret(BigInteger bigInteger) {
        try {
            this.f195dh.doPhase(KeyFactory.getInstance("DH").generatePublic(new DHPublicKeySpec(bigInteger, P1024, f194G)), true);
            return this.f195dh.generateSecret();
        } catch (GeneralSecurityException e) {
            throw new TorException(e);
        }
    }
}
