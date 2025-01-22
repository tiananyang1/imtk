package com.nimbusds.jose.proc;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.KeyConverter;
import com.nimbusds.jose.jwk.KeyType;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.security.Key;
import java.security.PublicKey;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/proc/JWSVerificationKeySelector.class */
public class JWSVerificationKeySelector<C extends SecurityContext> extends AbstractJWKSelectorWithSource<C> implements JWSKeySelector<C> {
    private final JWSAlgorithm jwsAlg;

    public JWSVerificationKeySelector(JWSAlgorithm jWSAlgorithm, JWKSource<C> jWKSource) {
        super(jWKSource);
        if (jWSAlgorithm == null) {
            throw new IllegalArgumentException("The JWS algorithm must not be null");
        }
        this.jwsAlg = jWSAlgorithm;
    }

    protected JWKMatcher createJWKMatcher(JWSHeader jWSHeader) {
        if (!getExpectedJWSAlgorithm().equals(jWSHeader.getAlgorithm())) {
            return null;
        }
        if (JWSAlgorithm.Family.RSA.contains(getExpectedJWSAlgorithm()) || JWSAlgorithm.Family.f81EC.contains(getExpectedJWSAlgorithm())) {
            return new JWKMatcher.Builder().keyType(KeyType.forAlgorithm(getExpectedJWSAlgorithm())).keyID(jWSHeader.getKeyID()).keyUses(KeyUse.SIGNATURE, null).algorithms(getExpectedJWSAlgorithm(), null).build();
        }
        if (JWSAlgorithm.Family.HMAC_SHA.contains(getExpectedJWSAlgorithm())) {
            return new JWKMatcher.Builder().keyType(KeyType.forAlgorithm(getExpectedJWSAlgorithm())).keyID(jWSHeader.getKeyID()).privateOnly(true).algorithms(getExpectedJWSAlgorithm(), null).build();
        }
        return null;
    }

    public JWSAlgorithm getExpectedJWSAlgorithm() {
        return this.jwsAlg;
    }

    @Override // com.nimbusds.jose.proc.AbstractJWKSelectorWithSource
    public /* bridge */ /* synthetic */ JWKSource getJWKSource() {
        return super.getJWKSource();
    }

    @Override // com.nimbusds.jose.proc.JWSKeySelector
    public List<Key> selectJWSKeys(JWSHeader jWSHeader, C c) throws KeySourceException {
        JWKMatcher createJWKMatcher;
        if (this.jwsAlg.equals(jWSHeader.getAlgorithm()) && (createJWKMatcher = createJWKMatcher(jWSHeader)) != null) {
            List<JWK> list = getJWKSource().get(new JWKSelector(createJWKMatcher), c);
            LinkedList linkedList = new LinkedList();
            for (Key key : KeyConverter.toJavaKeys(list)) {
                if ((key instanceof PublicKey) || (key instanceof SecretKey)) {
                    linkedList.add(key);
                }
            }
            return linkedList;
        }
        return Collections.emptyList();
    }
}
