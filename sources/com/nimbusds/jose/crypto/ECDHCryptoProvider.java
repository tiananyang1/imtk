package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.jwk.Curve;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ECDHCryptoProvider.class */
public abstract class ECDHCryptoProvider extends BaseJWEProvider {
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<Curve> SUPPORTED_ELLIPTIC_CURVES;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS = ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS;
    private final ConcatKDF concatKDF;
    private final Curve curve;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWEAlgorithm.ECDH_ES);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A128KW);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A192KW);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A256KW);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        linkedHashSet2.add(Curve.P_256);
        linkedHashSet2.add(Curve.P_384);
        linkedHashSet2.add(Curve.P_521);
        SUPPORTED_ELLIPTIC_CURVES = Collections.unmodifiableSet(linkedHashSet2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ECDHCryptoProvider(Curve curve) throws JOSEException {
        super(SUPPORTED_ALGORITHMS, ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS);
        Curve curve2 = curve != null ? curve : new Curve("unknown");
        if (!SUPPORTED_ELLIPTIC_CURVES.contains(curve)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedEllipticCurve(curve2, SUPPORTED_ELLIPTIC_CURVES));
        }
        this.curve = curve;
        this.concatKDF = new ConcatKDF(CommonUtils.SHA256_INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ConcatKDF getConcatKDF() {
        return this.concatKDF;
    }

    public Curve getCurve() {
        return this.curve;
    }

    public Set<Curve> supportedEllipticCurves() {
        return SUPPORTED_ELLIPTIC_CURVES;
    }
}
