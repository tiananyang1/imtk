package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.utils.ConstantTimeUtils;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.StandardCharset;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/MACVerifier.class */
public class MACVerifier extends MACProvider implements JWSVerifier, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;

    public MACVerifier(OctetSequenceKey octetSequenceKey) throws JOSEException {
        this(octetSequenceKey.toByteArray());
    }

    public MACVerifier(String str) throws JOSEException {
        this(str.getBytes(StandardCharset.UTF_8));
    }

    public MACVerifier(SecretKey secretKey) throws JOSEException {
        this(secretKey.getEncoded());
    }

    public MACVerifier(byte[] bArr) throws JOSEException {
        this(bArr, null);
    }

    public MACVerifier(byte[] bArr, Set<String> set) throws JOSEException {
        super(bArr, SUPPORTED_ALGORITHMS);
        this.critPolicy = new CriticalHeaderParamsDeferral();
        this.critPolicy.setDeferredCriticalHeaderParams(set);
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
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

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.JWSProvider
    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }

    @Override // com.nimbusds.jose.JWSVerifier
    public boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) throws JOSEException {
        if (this.critPolicy.headerPasses(jWSHeader)) {
            return ConstantTimeUtils.areEqual(HMAC.compute(getJCAAlgorithmName(jWSHeader.getAlgorithm()), getSecret(), bArr, getJCAContext().getProvider()), base64URL.decode());
        }
        return false;
    }
}
