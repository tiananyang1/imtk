package com.nimbusds.jose.proc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.PlainObject;
import com.nimbusds.jose.crypto.factories.DefaultJWEDecrypterFactory;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;
import com.nimbusds.jose.proc.SecurityContext;
import java.security.Key;
import java.text.ParseException;
import java.util.List;
import java.util.ListIterator;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/proc/DefaultJOSEProcessor.class */
public class DefaultJOSEProcessor<C extends SecurityContext> implements ConfigurableJOSEProcessor<C> {
    private JWEKeySelector<C> jweKeySelector;
    private JWSKeySelector<C> jwsKeySelector;
    private static final BadJOSEException PLAIN_JOSE_REJECTED_EXCEPTION = new BadJOSEException("Unsecured (plain) JOSE objects are rejected, extend class to handle");
    private static final BadJOSEException NO_JWS_KEY_SELECTOR_EXCEPTION = new BadJOSEException("JWS object rejected: No JWS key selector is configured");
    private static final BadJOSEException NO_JWE_KEY_SELECTOR_EXCEPTION = new BadJOSEException("JWE object rejected: No JWE key selector is configured");
    private static final JOSEException NO_JWS_VERIFIER_FACTORY_EXCEPTION = new JOSEException("No JWS verifier is configured");
    private static final JOSEException NO_JWE_DECRYPTER_FACTORY_EXCEPTION = new JOSEException("No JWE decrypter is configured");
    private static final BadJOSEException NO_JWS_KEY_CANDIDATES_EXCEPTION = new BadJOSEException("JWS object rejected: Another algorithm expected, or no matching key(s) found");
    private static final BadJOSEException NO_JWE_KEY_CANDIDATES_EXCEPTION = new BadJOSEException("JWE object rejected: Another algorithm expected, or no matching key(s) found");
    private static final BadJOSEException INVALID_SIGNATURE = new BadJWSException("JWS object rejected: Invalid signature");
    private static final BadJOSEException NO_MATCHING_VERIFIERS_EXCEPTION = new BadJOSEException("JWS object rejected: No matching verifier(s) found");
    private static final BadJOSEException NO_MATCHING_DECRYPTERS_EXCEPTION = new BadJOSEException("JWE object rejected: No matching decrypter(s) found");
    private JWSVerifierFactory jwsVerifierFactory = new DefaultJWSVerifierFactory();
    private JWEDecrypterFactory jweDecrypterFactory = new DefaultJWEDecrypterFactory();

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JWEDecrypterFactory getJWEDecrypterFactory() {
        return this.jweDecrypterFactory;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JWEKeySelector<C> getJWEKeySelector() {
        return this.jweKeySelector;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JWSKeySelector<C> getJWSKeySelector() {
        return this.jwsKeySelector;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JWSVerifierFactory getJWSVerifierFactory() {
        return this.jwsVerifierFactory;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(JOSEObject jOSEObject, C c) throws BadJOSEException, JOSEException {
        if (jOSEObject instanceof JWSObject) {
            return process((JWSObject) jOSEObject, (JWSObject) c);
        }
        if (jOSEObject instanceof JWEObject) {
            return process((JWEObject) jOSEObject, (JWEObject) c);
        }
        if (jOSEObject instanceof PlainObject) {
            return process((PlainObject) jOSEObject, (PlainObject) c);
        }
        throw new JOSEException("Unexpected JOSE object type: " + jOSEObject.getClass());
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(JWEObject jWEObject, C c) throws BadJOSEException, JOSEException {
        JWSObject jWSObject;
        if (getJWEKeySelector() == null) {
            throw NO_JWE_KEY_SELECTOR_EXCEPTION;
        }
        if (getJWEDecrypterFactory() == null) {
            throw NO_JWE_DECRYPTER_FACTORY_EXCEPTION;
        }
        List<? extends Key> selectJWEKeys = getJWEKeySelector().selectJWEKeys(jWEObject.getHeader(), c);
        if (selectJWEKeys == null || selectJWEKeys.isEmpty()) {
            throw NO_JWE_KEY_CANDIDATES_EXCEPTION;
        }
        ListIterator<? extends Key> listIterator = selectJWEKeys.listIterator();
        while (listIterator.hasNext()) {
            JWEDecrypter createJWEDecrypter = getJWEDecrypterFactory().createJWEDecrypter(jWEObject.getHeader(), listIterator.next());
            if (createJWEDecrypter != null) {
                try {
                    jWEObject.decrypt(createJWEDecrypter);
                    if ("JWT".equalsIgnoreCase(jWEObject.getHeader().getContentType()) && (jWSObject = jWEObject.getPayload().toJWSObject()) != null) {
                        return process(jWSObject, (JWSObject) c);
                    }
                    return jWEObject.getPayload();
                } catch (JOSEException e) {
                    if (!listIterator.hasNext()) {
                        throw new BadJWEException("JWE object rejected: " + e.getMessage(), e);
                    }
                }
            }
        }
        throw NO_MATCHING_DECRYPTERS_EXCEPTION;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(JWSObject jWSObject, C c) throws BadJOSEException, JOSEException {
        if (getJWSKeySelector() == null) {
            throw NO_JWS_KEY_SELECTOR_EXCEPTION;
        }
        if (getJWSVerifierFactory() == null) {
            throw NO_JWS_VERIFIER_FACTORY_EXCEPTION;
        }
        List<? extends Key> selectJWSKeys = getJWSKeySelector().selectJWSKeys(jWSObject.getHeader(), c);
        if (selectJWSKeys == null || selectJWSKeys.isEmpty()) {
            throw NO_JWS_KEY_CANDIDATES_EXCEPTION;
        }
        ListIterator<? extends Key> listIterator = selectJWSKeys.listIterator();
        while (listIterator.hasNext()) {
            JWSVerifier createJWSVerifier = getJWSVerifierFactory().createJWSVerifier(jWSObject.getHeader(), listIterator.next());
            if (createJWSVerifier != null) {
                if (jWSObject.verify(createJWSVerifier)) {
                    return jWSObject.getPayload();
                }
                if (!listIterator.hasNext()) {
                    throw INVALID_SIGNATURE;
                }
            }
        }
        throw NO_MATCHING_VERIFIERS_EXCEPTION;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(PlainObject plainObject, C c) throws BadJOSEException {
        throw PLAIN_JOSE_REJECTED_EXCEPTION;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(String str, C c) throws ParseException, BadJOSEException, JOSEException {
        return process(JOSEObject.parse(str), (JOSEObject) c);
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWEDecrypterFactory(JWEDecrypterFactory jWEDecrypterFactory) {
        this.jweDecrypterFactory = jWEDecrypterFactory;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWEKeySelector(JWEKeySelector<C> jWEKeySelector) {
        this.jweKeySelector = jWEKeySelector;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWSKeySelector(JWSKeySelector<C> jWSKeySelector) {
        this.jwsKeySelector = jWSKeySelector;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWSVerifierFactory(JWSVerifierFactory jWSVerifierFactory) {
        this.jwsVerifierFactory = jWSVerifierFactory;
    }
}
