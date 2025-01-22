package com.nimbusds.jwt.proc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.factories.DefaultJWEDecrypterFactory;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.BadJWEException;
import com.nimbusds.jose.proc.BadJWSException;
import com.nimbusds.jose.proc.JWEDecrypterFactory;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerifierFactory;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;
import java.security.Key;
import java.text.ParseException;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jwt/proc/DefaultJWTProcessor.class */
public class DefaultJWTProcessor<C extends SecurityContext> implements ConfigurableJWTProcessor<C> {
    private JWEKeySelector<C> jweKeySelector;
    private JWSKeySelector<C> jwsKeySelector;
    private static final BadJOSEException PLAIN_JWT_REJECTED_EXCEPTION = new BadJOSEException("Unsecured (plain) JWTs are rejected, extend class to handle");
    private static final BadJOSEException NO_JWS_KEY_SELECTOR_EXCEPTION = new BadJOSEException("Signed JWT rejected: No JWS key selector is configured");
    private static final BadJOSEException NO_JWE_KEY_SELECTOR_EXCEPTION = new BadJOSEException("Encrypted JWT rejected: No JWE key selector is configured");
    private static final JOSEException NO_JWS_VERIFIER_FACTORY_EXCEPTION = new JOSEException("No JWS verifier is configured");
    private static final JOSEException NO_JWE_DECRYPTER_FACTORY_EXCEPTION = new JOSEException("No JWE decrypter is configured");
    private static final BadJOSEException NO_JWS_KEY_CANDIDATES_EXCEPTION = new BadJOSEException("Signed JWT rejected: Another algorithm expected, or no matching key(s) found");
    private static final BadJOSEException NO_JWE_KEY_CANDIDATES_EXCEPTION = new BadJOSEException("Encrypted JWT rejected: Another algorithm expected, or no matching key(s) found");
    private static final BadJOSEException INVALID_SIGNATURE = new BadJWSException("Signed JWT rejected: Invalid signature");
    private static final BadJWTException INVALID_NESTED_JWT_EXCEPTION = new BadJWTException("The payload is not a nested signed JWT");
    private static final BadJOSEException NO_MATCHING_VERIFIERS_EXCEPTION = new BadJOSEException("JWS object rejected: No matching verifier(s) found");
    private static final BadJOSEException NO_MATCHING_DECRYPTERS_EXCEPTION = new BadJOSEException("Encrypted JWT rejected: No matching decrypter(s) found");
    private JWSVerifierFactory jwsVerifierFactory = new DefaultJWSVerifierFactory();
    private JWEDecrypterFactory jweDecrypterFactory = new DefaultJWEDecrypterFactory();
    private JWTClaimsSetVerifier<C> claimsVerifier = new DefaultJWTClaimsVerifier();
    private JWTClaimsVerifier deprecatedClaimsVerifier = null;

    private JWTClaimsSet verifyAndReturnClaims(JWT jwt, C c) throws BadJWTException {
        try {
            JWTClaimsSet jWTClaimsSet = jwt.getJWTClaimsSet();
            if (getJWTClaimsSetVerifier() != null) {
                getJWTClaimsSetVerifier().verify(jWTClaimsSet, c);
                return jWTClaimsSet;
            }
            if (getJWTClaimsVerifier() != null) {
                getJWTClaimsVerifier().verify(jWTClaimsSet);
            }
            return jWTClaimsSet;
        } catch (ParseException e) {
            throw new BadJWTException(e.getMessage(), e);
        }
    }

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

    @Override // com.nimbusds.jwt.proc.JWTProcessorConfiguration
    public JWTClaimsSetVerifier<C> getJWTClaimsSetVerifier() {
        return this.claimsVerifier;
    }

    @Override // com.nimbusds.jwt.proc.JWTProcessorConfiguration
    @Deprecated
    public JWTClaimsVerifier getJWTClaimsVerifier() {
        return this.deprecatedClaimsVerifier;
    }

    @Override // com.nimbusds.jwt.proc.JWTProcessor
    public JWTClaimsSet process(EncryptedJWT encryptedJWT, C c) throws BadJOSEException, JOSEException {
        if (getJWEKeySelector() == null) {
            throw NO_JWE_KEY_SELECTOR_EXCEPTION;
        }
        if (getJWEDecrypterFactory() == null) {
            throw NO_JWE_DECRYPTER_FACTORY_EXCEPTION;
        }
        List<? extends Key> selectJWEKeys = getJWEKeySelector().selectJWEKeys(encryptedJWT.getHeader(), c);
        if (selectJWEKeys == null || selectJWEKeys.isEmpty()) {
            throw NO_JWE_KEY_CANDIDATES_EXCEPTION;
        }
        ListIterator<? extends Key> listIterator = selectJWEKeys.listIterator();
        while (listIterator.hasNext()) {
            JWEDecrypter createJWEDecrypter = getJWEDecrypterFactory().createJWEDecrypter(encryptedJWT.getHeader(), listIterator.next());
            if (createJWEDecrypter != null) {
                try {
                    encryptedJWT.decrypt(createJWEDecrypter);
                    if (!"JWT".equalsIgnoreCase(encryptedJWT.getHeader().getContentType())) {
                        return verifyAndReturnClaims(encryptedJWT, c);
                    }
                    SignedJWT signedJWT = encryptedJWT.getPayload().toSignedJWT();
                    if (signedJWT != null) {
                        return process(signedJWT, (SignedJWT) c);
                    }
                    throw INVALID_NESTED_JWT_EXCEPTION;
                } catch (JOSEException e) {
                    if (!listIterator.hasNext()) {
                        throw new BadJWEException("Encrypted JWT rejected: " + e.getMessage(), e);
                    }
                }
            }
        }
        throw NO_MATCHING_DECRYPTERS_EXCEPTION;
    }

    @Override // com.nimbusds.jwt.proc.JWTProcessor
    public JWTClaimsSet process(JWT jwt, C c) throws BadJOSEException, JOSEException {
        if (jwt instanceof SignedJWT) {
            return process((SignedJWT) jwt, (SignedJWT) c);
        }
        if (jwt instanceof EncryptedJWT) {
            return process((EncryptedJWT) jwt, (EncryptedJWT) c);
        }
        if (jwt instanceof PlainJWT) {
            return process((PlainJWT) jwt, (PlainJWT) c);
        }
        throw new JOSEException("Unexpected JWT object type: " + jwt.getClass());
    }

    @Override // com.nimbusds.jwt.proc.JWTProcessor
    public JWTClaimsSet process(PlainJWT plainJWT, C c) throws BadJOSEException, JOSEException {
        verifyAndReturnClaims(plainJWT, c);
        throw PLAIN_JWT_REJECTED_EXCEPTION;
    }

    @Override // com.nimbusds.jwt.proc.JWTProcessor
    public JWTClaimsSet process(SignedJWT signedJWT, C c) throws BadJOSEException, JOSEException {
        if (getJWSKeySelector() == null) {
            throw NO_JWS_KEY_SELECTOR_EXCEPTION;
        }
        if (getJWSVerifierFactory() == null) {
            throw NO_JWS_VERIFIER_FACTORY_EXCEPTION;
        }
        List<? extends Key> selectJWSKeys = getJWSKeySelector().selectJWSKeys(signedJWT.getHeader(), c);
        if (selectJWSKeys == null || selectJWSKeys.isEmpty()) {
            throw NO_JWS_KEY_CANDIDATES_EXCEPTION;
        }
        ListIterator<? extends Key> listIterator = selectJWSKeys.listIterator();
        while (listIterator.hasNext()) {
            JWSVerifier createJWSVerifier = getJWSVerifierFactory().createJWSVerifier(signedJWT.getHeader(), listIterator.next());
            if (createJWSVerifier != null) {
                if (signedJWT.verify(createJWSVerifier)) {
                    return verifyAndReturnClaims(signedJWT, c);
                }
                if (!listIterator.hasNext()) {
                    throw INVALID_SIGNATURE;
                }
            }
        }
        throw NO_MATCHING_VERIFIERS_EXCEPTION;
    }

    @Override // com.nimbusds.jwt.proc.JWTProcessor
    public JWTClaimsSet process(String str, C c) throws ParseException, BadJOSEException, JOSEException {
        return process(JWTParser.parse(str), (JWT) c);
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

    @Override // com.nimbusds.jwt.proc.JWTProcessorConfiguration
    public void setJWTClaimsSetVerifier(JWTClaimsSetVerifier<C> jWTClaimsSetVerifier) {
        this.claimsVerifier = jWTClaimsSetVerifier;
        this.deprecatedClaimsVerifier = null;
    }

    @Override // com.nimbusds.jwt.proc.JWTProcessorConfiguration
    @Deprecated
    public void setJWTClaimsVerifier(JWTClaimsVerifier jWTClaimsVerifier) {
        this.claimsVerifier = null;
        this.deprecatedClaimsVerifier = jWTClaimsVerifier;
    }
}
