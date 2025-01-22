package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.jwk.RSAKey;
import java.security.PrivateKey;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/RSADecrypter.class */
public class RSADecrypter extends RSACryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    private Exception cekDecryptionException;
    private final CriticalHeaderParamsDeferral critPolicy;
    private final PrivateKey privateKey;

    public RSADecrypter(RSAKey rSAKey) throws JOSEException {
        this.critPolicy = new CriticalHeaderParamsDeferral();
        if (!rSAKey.isPrivate()) {
            throw new JOSEException("The RSA JWK doesn't contain a private part");
        }
        this.privateKey = rSAKey.toPrivateKey();
    }

    public RSADecrypter(PrivateKey privateKey) {
        this(privateKey, null);
    }

    public RSADecrypter(PrivateKey privateKey, Set<String> set) {
        this.critPolicy = new CriticalHeaderParamsDeferral();
        if (privateKey == null) {
            throw new IllegalArgumentException("The private RSA key must not be null");
        }
        if (!privateKey.getAlgorithm().equalsIgnoreCase("RSA")) {
            throw new IllegalArgumentException("The private key algorithm must be RSA");
        }
        this.privateKey = privateKey;
        this.critPolicy.setDeferredCriticalHeaderParams(set);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x005b, code lost:            if (r0 == null) goto L17;     */
    @Override // com.nimbusds.jose.JWEDecrypter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] decrypt(com.nimbusds.jose.JWEHeader r9, com.nimbusds.jose.util.Base64URL r10, com.nimbusds.jose.util.Base64URL r11, com.nimbusds.jose.util.Base64URL r12, com.nimbusds.jose.util.Base64URL r13) throws com.nimbusds.jose.JOSEException {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nimbusds.jose.crypto.RSADecrypter.decrypt(com.nimbusds.jose.JWEHeader, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL):byte[]");
    }

    public Exception getCEKDecryptionException() {
        return this.cekDecryptionException;
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.JWEProvider
    public /* bridge */ /* synthetic */ Set supportedEncryptionMethods() {
        return super.supportedEncryptionMethods();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.JWEProvider
    public /* bridge */ /* synthetic */ Set supportedJWEAlgorithms() {
        return super.supportedJWEAlgorithms();
    }
}
