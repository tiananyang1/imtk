package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.StandardCharset;
import java.text.ParseException;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWSObject.class */
public class JWSObject extends JOSEObject {
    private static final long serialVersionUID = 1;
    private final JWSHeader header;
    private Base64URL signature;
    private final String signingInputString;
    private State state;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWSObject$State.class */
    public enum State {
        UNSIGNED,
        SIGNED,
        VERIFIED;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static State[] valuesCustom() {
            State[] valuesCustom = values();
            int length = valuesCustom.length;
            State[] stateArr = new State[length];
            System.arraycopy(valuesCustom, 0, stateArr, 0, length);
            return stateArr;
        }
    }

    public JWSObject(JWSHeader jWSHeader, Payload payload) {
        if (jWSHeader == null) {
            throw new IllegalArgumentException("The JWS header must not be null");
        }
        this.header = jWSHeader;
        if (payload == null) {
            throw new IllegalArgumentException("The payload must not be null");
        }
        setPayload(payload);
        this.signingInputString = composeSigningInput(jWSHeader.toBase64URL(), payload.toBase64URL());
        this.signature = null;
        this.state = State.UNSIGNED;
    }

    public JWSObject(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) throws ParseException {
        if (base64URL == null) {
            throw new IllegalArgumentException("The first part must not be null");
        }
        try {
            this.header = JWSHeader.m3647parse(base64URL);
            if (base64URL2 == null) {
                throw new IllegalArgumentException("The second part must not be null");
            }
            setPayload(new Payload(base64URL2));
            this.signingInputString = composeSigningInput(base64URL, base64URL2);
            if (base64URL3 == null) {
                throw new IllegalArgumentException("The third part must not be null");
            }
            this.signature = base64URL3;
            this.state = State.SIGNED;
            setParsedParts(base64URL, base64URL2, base64URL3);
        } catch (ParseException e) {
            throw new ParseException("Invalid JWS header: " + e.getMessage(), 0);
        }
    }

    private static String composeSigningInput(Base64URL base64URL, Base64URL base64URL2) {
        return String.valueOf(base64URL.toString()) + '.' + base64URL2.toString();
    }

    private void ensureJWSSignerSupport(JWSSigner jWSSigner) throws JOSEException {
        if (jWSSigner.supportedJWSAlgorithms().contains(getHeader().getAlgorithm())) {
            return;
        }
        throw new JOSEException("The \"" + getHeader().getAlgorithm() + "\" algorithm is not allowed or supported by the JWS signer: Supported algorithms: " + jWSSigner.supportedJWSAlgorithms());
    }

    private void ensureSignedOrVerifiedState() {
        if (this.state != State.SIGNED && this.state != State.VERIFIED) {
            throw new IllegalStateException("The JWS object must be in a signed or verified state");
        }
    }

    private void ensureUnsignedState() {
        if (this.state != State.UNSIGNED) {
            throw new IllegalStateException("The JWS object must be in an unsigned state");
        }
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static JWSObject m3652parse(String str) throws ParseException {
        Base64URL[] split = JOSEObject.split(str);
        if (split.length == 3) {
            return new JWSObject(split[0], split[1], split[2]);
        }
        throw new ParseException("Unexpected number of Base64URL parts, must be three", 0);
    }

    @Override // com.nimbusds.jose.JOSEObject
    public JWSHeader getHeader() {
        return this.header;
    }

    public Base64URL getSignature() {
        return this.signature;
    }

    public byte[] getSigningInput() {
        return this.signingInputString.getBytes(StandardCharset.UTF_8);
    }

    public State getState() {
        return this.state;
    }

    @Override // com.nimbusds.jose.JOSEObject
    public String serialize() {
        ensureSignedOrVerifiedState();
        return String.valueOf(this.signingInputString) + '.' + this.signature.toString();
    }

    public void sign(JWSSigner jWSSigner) throws JOSEException {
        synchronized (this) {
            ensureUnsignedState();
            ensureJWSSignerSupport(jWSSigner);
            try {
                try {
                    this.signature = jWSSigner.sign(getHeader(), getSigningInput());
                    this.state = State.SIGNED;
                } catch (JOSEException e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw new JOSEException(e2.getMessage(), e2);
            }
        }
    }

    public boolean verify(JWSVerifier jWSVerifier) throws JOSEException {
        boolean verify;
        synchronized (this) {
            ensureSignedOrVerifiedState();
            try {
                verify = jWSVerifier.verify(getHeader(), getSigningInput(), getSignature());
                if (verify) {
                    this.state = State.VERIFIED;
                }
            } catch (JOSEException e) {
                throw e;
            } catch (Exception e2) {
                throw new JOSEException(e2.getMessage(), e2);
            }
        }
        return verify;
    }
}
