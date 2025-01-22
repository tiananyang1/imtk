package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.StandardCharset;
import com.subgraph.orchid.Cell;
import com.sun.jna.Function;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/MACSigner.class */
public class MACSigner extends MACProvider implements JWSSigner {
    public MACSigner(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toByteArray());
    }

    public MACSigner(String str) throws KeyLengthException {
        this(str.getBytes(StandardCharset.UTF_8));
    }

    public MACSigner(SecretKey secretKey) throws KeyLengthException {
        this(secretKey.getEncoded());
    }

    public MACSigner(byte[] bArr) throws KeyLengthException {
        super(bArr, getCompatibleAlgorithms(ByteUtils.bitLength(bArr.length)));
    }

    public static Set<JWSAlgorithm> getCompatibleAlgorithms(int i) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (i >= 256) {
            linkedHashSet.add(JWSAlgorithm.HS256);
        }
        if (i >= 384) {
            linkedHashSet.add(JWSAlgorithm.HS384);
        }
        if (i >= 512) {
            linkedHashSet.add(JWSAlgorithm.HS512);
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public static int getMinRequiredSecretLength(JWSAlgorithm jWSAlgorithm) throws JOSEException {
        if (JWSAlgorithm.HS256.equals(jWSAlgorithm)) {
            return Function.MAX_NARGS;
        }
        if (JWSAlgorithm.HS384.equals(jWSAlgorithm)) {
            return Function.USE_VARARGS;
        }
        if (JWSAlgorithm.HS512.equals(jWSAlgorithm)) {
            return Cell.CELL_LEN;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, SUPPORTED_ALGORITHMS));
    }

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    @Override // com.nimbusds.jose.crypto.MACProvider
    public /* bridge */ /* synthetic */ byte[] getSecret() {
        return super.getSecret();
    }

    @Override // com.nimbusds.jose.crypto.MACProvider
    public /* bridge */ /* synthetic */ SecretKey getSecretKey() {
        return super.getSecretKey();
    }

    @Override // com.nimbusds.jose.crypto.MACProvider
    public /* bridge */ /* synthetic */ String getSecretString() {
        return super.getSecretString();
    }

    @Override // com.nimbusds.jose.JWSSigner
    public Base64URL sign(JWSHeader jWSHeader, byte[] bArr) throws JOSEException {
        int minRequiredSecretLength = getMinRequiredSecretLength(jWSHeader.getAlgorithm());
        if (getSecret().length >= ByteUtils.byteLength(minRequiredSecretLength)) {
            return Base64URL.m3704encode(HMAC.compute(getJCAAlgorithmName(jWSHeader.getAlgorithm()), getSecret(), bArr, getJCAContext().getProvider()));
        }
        throw new KeyLengthException("The secret length for " + jWSHeader.getAlgorithm() + " must be at least " + minRequiredSecretLength + " bits");
    }

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.JWSProvider
    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }
}
