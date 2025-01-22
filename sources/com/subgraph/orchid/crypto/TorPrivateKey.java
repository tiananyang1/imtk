package com.subgraph.orchid.crypto;

import com.subgraph.orchid.TorException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorPrivateKey.class */
public class TorPrivateKey {
    private final RSAPrivateKey privateKey;
    private final TorPublicKey publicKey;

    TorPrivateKey(RSAPrivateKey rSAPrivateKey, RSAPublicKey rSAPublicKey) {
        this.privateKey = rSAPrivateKey;
        this.publicKey = new TorPublicKey(rSAPublicKey);
    }

    static KeyPairGenerator createGenerator() {
        try {
            return KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new TorException(e);
        }
    }

    public static TorPrivateKey generateNewKeypair() {
        KeyPairGenerator createGenerator = createGenerator();
        createGenerator.initialize(1024, new SecureRandom());
        KeyPair generateKeyPair = createGenerator.generateKeyPair();
        return new TorPrivateKey((RSAPrivateKey) generateKeyPair.getPrivate(), (RSAPublicKey) generateKeyPair.getPublic());
    }

    public TorPublicKey getPublicKey() {
        return this.publicKey;
    }

    public RSAPrivateKey getRSAPrivateKey() {
        return this.privateKey;
    }

    public RSAPublicKey getRSAPublicKey() {
        return this.publicKey.getRSAPublicKey();
    }
}
