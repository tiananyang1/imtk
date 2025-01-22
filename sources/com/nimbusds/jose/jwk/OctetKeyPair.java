package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.net.URI;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/OctetKeyPair.class */
public class OctetKeyPair extends JWK implements AssymetricJWK, CurveBasedJWK {
    public static final Set<Curve> SUPPORTED_CURVES = Collections.unmodifiableSet(new HashSet(Arrays.asList(Curve.Ed25519, Curve.Ed448, Curve.X25519, Curve.X448)));
    private static final long serialVersionUID = 1;
    private final Curve crv;

    /* renamed from: d */
    private final Base64URL f92d;

    /* renamed from: x */
    private final Base64URL f93x;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/OctetKeyPair$Builder.class */
    public static class Builder {
        private Algorithm alg;
        private final Curve crv;

        /* renamed from: d */
        private Base64URL f94d;
        private String kid;

        /* renamed from: ks */
        private KeyStore f95ks;
        private Set<KeyOperation> ops;
        private KeyUse use;

        /* renamed from: x */
        private final Base64URL f96x;
        private List<Base64> x5c;

        @Deprecated
        private Base64URL x5t;
        private Base64URL x5t256;
        private URI x5u;

        public Builder(Curve curve, Base64URL base64URL) {
            if (curve == null) {
                throw new IllegalArgumentException("The curve must not be null");
            }
            this.crv = curve;
            if (base64URL == null) {
                throw new IllegalArgumentException("The 'x' coordinate must not be null");
            }
            this.f96x = base64URL;
        }

        public Builder(OctetKeyPair octetKeyPair) {
            this.crv = octetKeyPair.crv;
            this.f96x = octetKeyPair.f93x;
            this.f94d = octetKeyPair.f92d;
            this.use = octetKeyPair.getKeyUse();
            this.ops = octetKeyPair.getKeyOperations();
            this.alg = octetKeyPair.getAlgorithm();
            this.kid = octetKeyPair.getKeyID();
            this.x5u = octetKeyPair.getX509CertURL();
            this.x5t = octetKeyPair.getX509CertThumbprint();
            this.x5t256 = octetKeyPair.getX509CertSHA256Thumbprint();
            this.x5c = octetKeyPair.getX509CertChain();
            this.f95ks = octetKeyPair.getKeyStore();
        }

        public Builder algorithm(Algorithm algorithm) {
            this.alg = algorithm;
            return this;
        }

        public OctetKeyPair build() {
            try {
                return this.f94d == null ? new OctetKeyPair(this.crv, this.f96x, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.f95ks) : new OctetKeyPair(this.crv, this.f96x, this.f94d, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.f95ks);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }

        /* renamed from: d */
        public Builder m12d(Base64URL base64URL) {
            this.f94d = base64URL;
            return this;
        }

        public Builder keyID(String str) {
            this.kid = str;
            return this;
        }

        public Builder keyIDFromThumbprint() throws JOSEException {
            return keyIDFromThumbprint(CommonUtils.SHA256_INSTANCE);
        }

        public Builder keyIDFromThumbprint(String str) throws JOSEException {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("crv", this.crv.toString());
            linkedHashMap.put("kty", KeyType.OKP.getValue());
            linkedHashMap.put("x", this.f96x.toString());
            this.kid = ThumbprintUtils.compute(str, (LinkedHashMap<String, ?>) linkedHashMap).toString();
            return this;
        }

        public Builder keyOperations(Set<KeyOperation> set) {
            this.ops = set;
            return this;
        }

        public Builder keyStore(KeyStore keyStore) {
            this.f95ks = keyStore;
            return this;
        }

        public Builder keyUse(KeyUse keyUse) {
            this.use = keyUse;
            return this;
        }

        public Builder x509CertChain(List<Base64> list) {
            this.x5c = list;
            return this;
        }

        public Builder x509CertSHA256Thumbprint(Base64URL base64URL) {
            this.x5t256 = base64URL;
            return this;
        }

        @Deprecated
        public Builder x509CertThumbprint(Base64URL base64URL) {
            this.x5t = base64URL;
            return this;
        }

        public Builder x509CertURL(URI uri) {
            this.x5u = uri;
            return this;
        }
    }

    public OctetKeyPair(Curve curve, Base64URL base64URL, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL2, Base64URL base64URL3, List<Base64> list, KeyStore keyStore) {
        super(KeyType.OKP, keyUse, set, algorithm, str, uri, base64URL2, base64URL3, list, keyStore);
        if (curve == null) {
            throw new IllegalArgumentException("The curve must not be null");
        }
        if (!SUPPORTED_CURVES.contains(curve)) {
            throw new IllegalArgumentException("Unknown / unsupported curve: " + curve);
        }
        this.crv = curve;
        if (base64URL == null) {
            throw new IllegalArgumentException("The 'x' parameter must not be null");
        }
        this.f93x = base64URL;
        this.f92d = null;
    }

    public OctetKeyPair(Curve curve, Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        super(KeyType.OKP, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
        if (curve == null) {
            throw new IllegalArgumentException("The curve must not be null");
        }
        if (!SUPPORTED_CURVES.contains(curve)) {
            throw new IllegalArgumentException("Unknown / unsupported curve: " + curve);
        }
        this.crv = curve;
        if (base64URL == null) {
            throw new IllegalArgumentException("The 'x' parameter must not be null");
        }
        this.f93x = base64URL;
        if (base64URL2 == null) {
            throw new IllegalArgumentException("The 'd' parameter must not be null");
        }
        this.f92d = base64URL2;
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static OctetKeyPair m3690parse(String str) throws ParseException {
        return m3691parse(JSONObjectUtils.parse(str));
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static OctetKeyPair m3691parse(JSONObject jSONObject) throws ParseException {
        Curve parse = Curve.parse(JSONObjectUtils.getString(jSONObject, "crv"));
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject, "x"));
        if (JWKMetadata.parseKeyType(jSONObject) != KeyType.OKP) {
            throw new ParseException("The key type \"kty\" must be OKP", 0);
        }
        Base64URL base64URL2 = null;
        if (jSONObject.get("d") != null) {
            base64URL2 = new Base64URL(JSONObjectUtils.getString(jSONObject, "d"));
        }
        try {
            return base64URL2 == null ? new OctetKeyPair(parse, base64URL, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), null) : new OctetKeyPair(parse, base64URL, base64URL2, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), null);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    @Override // com.nimbusds.jose.jwk.CurveBasedJWK
    public Curve getCurve() {
        return this.crv;
    }

    public Base64URL getD() {
        return this.f92d;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("crv", this.crv.toString());
        linkedHashMap.put("kty", getKeyType().getValue());
        linkedHashMap.put("x", this.f93x.toString());
        return linkedHashMap;
    }

    public Base64URL getX() {
        return this.f93x;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public boolean isPrivate() {
        return this.f92d != null;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public int size() {
        return ByteUtils.bitLength(this.f93x.decode());
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("crv", this.crv.toString());
        jSONObject.put("x", this.f93x.toString());
        Base64URL base64URL = this.f92d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        return jSONObject;
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public KeyPair toKeyPair() throws JOSEException {
        throw new JOSEException("Export to java.security.KeyPair not supported");
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public PrivateKey toPrivateKey() throws JOSEException {
        throw new JOSEException("Export to java.security.PrivateKey not supported");
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public OctetKeyPair toPublicJWK() {
        return new OctetKeyPair(getCurve(), getX(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertSHA256Thumbprint(), getX509CertChain(), getKeyStore());
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public PublicKey toPublicKey() throws JOSEException {
        throw new JOSEException("Export to java.security.PublicKey not supported");
    }
}
