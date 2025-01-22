package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.utils.ECChecks;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.BigIntegerUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.net.URI;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/ECKey.class */
public final class ECKey extends JWK implements AssymetricJWK, CurveBasedJWK {
    public static final Set<Curve> SUPPORTED_CURVES = Collections.unmodifiableSet(new HashSet(Arrays.asList(Curve.P_256, Curve.P_384, Curve.P_521)));
    private static final long serialVersionUID = 1;
    private final Curve crv;

    /* renamed from: d */
    private final Base64URL f84d;
    private final PrivateKey privateKey;

    /* renamed from: x */
    private final Base64URL f85x;

    /* renamed from: y */
    private final Base64URL f86y;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/ECKey$Builder.class */
    public static class Builder {
        private Algorithm alg;
        private final Curve crv;

        /* renamed from: d */
        private Base64URL f87d;
        private String kid;

        /* renamed from: ks */
        private KeyStore f88ks;
        private Set<KeyOperation> ops;
        private PrivateKey priv;
        private KeyUse use;

        /* renamed from: x */
        private final Base64URL f89x;
        private List<Base64> x5c;

        @Deprecated
        private Base64URL x5t;
        private Base64URL x5t256;
        private URI x5u;

        /* renamed from: y */
        private final Base64URL f90y;

        public Builder(Curve curve, Base64URL base64URL, Base64URL base64URL2) {
            if (curve == null) {
                throw new IllegalArgumentException("The curve must not be null");
            }
            this.crv = curve;
            if (base64URL == null) {
                throw new IllegalArgumentException("The 'x' coordinate must not be null");
            }
            this.f89x = base64URL;
            if (base64URL2 == null) {
                throw new IllegalArgumentException("The 'y' coordinate must not be null");
            }
            this.f90y = base64URL2;
        }

        public Builder(Curve curve, ECPublicKey eCPublicKey) {
            this(curve, ECKey.encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineX()), ECKey.encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineY()));
        }

        public Builder(ECKey eCKey) {
            this.crv = eCKey.crv;
            this.f89x = eCKey.f85x;
            this.f90y = eCKey.f86y;
            this.f87d = eCKey.f84d;
            this.priv = eCKey.privateKey;
            this.use = eCKey.getKeyUse();
            this.ops = eCKey.getKeyOperations();
            this.alg = eCKey.getAlgorithm();
            this.kid = eCKey.getKeyID();
            this.x5u = eCKey.getX509CertURL();
            this.x5t = eCKey.getX509CertThumbprint();
            this.x5t256 = eCKey.getX509CertSHA256Thumbprint();
            this.x5c = eCKey.getX509CertChain();
            this.f88ks = eCKey.getKeyStore();
        }

        public Builder algorithm(Algorithm algorithm) {
            this.alg = algorithm;
            return this;
        }

        public ECKey build() {
            try {
                return (this.f87d == null && this.priv == null) ? new ECKey(this.crv, this.f89x, this.f90y, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.f88ks) : this.priv != null ? new ECKey(this.crv, this.f89x, this.f90y, this.priv, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.f88ks) : new ECKey(this.crv, this.f89x, this.f90y, this.f87d, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.f88ks);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }

        /* renamed from: d */
        public Builder m11d(Base64URL base64URL) {
            this.f87d = base64URL;
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
            linkedHashMap.put("kty", KeyType.f91EC.getValue());
            linkedHashMap.put("x", this.f89x.toString());
            linkedHashMap.put("y", this.f90y.toString());
            this.kid = ThumbprintUtils.compute(str, (LinkedHashMap<String, ?>) linkedHashMap).toString();
            return this;
        }

        public Builder keyOperations(Set<KeyOperation> set) {
            this.ops = set;
            return this;
        }

        public Builder keyStore(KeyStore keyStore) {
            this.f88ks = keyStore;
            return this;
        }

        public Builder keyUse(KeyUse keyUse) {
            this.use = keyUse;
            return this;
        }

        public Builder privateKey(PrivateKey privateKey) {
            if (!"EC".equalsIgnoreCase(privateKey.getAlgorithm())) {
                throw new IllegalArgumentException("The private key algorithm must be EC");
            }
            this.priv = privateKey;
            return this;
        }

        public Builder privateKey(ECPrivateKey eCPrivateKey) {
            if (eCPrivateKey != null) {
                this.f87d = ECKey.encodeCoordinate(eCPrivateKey.getParams().getCurve().getField().getFieldSize(), eCPrivateKey.getS());
            }
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

    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        super(KeyType.f91EC, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
        if (curve == null) {
            throw new IllegalArgumentException("The curve must not be null");
        }
        this.crv = curve;
        if (base64URL == null) {
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        this.f85x = base64URL;
        if (base64URL2 == null) {
            throw new IllegalArgumentException("The 'y' coordinate must not be null");
        }
        this.f86y = base64URL2;
        ensurePublicCoordinatesOnCurve(curve, base64URL, base64URL2);
        this.f84d = null;
        this.privateKey = null;
    }

    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL4, Base64URL base64URL5, List<Base64> list, KeyStore keyStore) {
        super(KeyType.f91EC, keyUse, set, algorithm, str, uri, base64URL4, base64URL5, list, keyStore);
        if (curve == null) {
            throw new IllegalArgumentException("The curve must not be null");
        }
        this.crv = curve;
        if (base64URL == null) {
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        this.f85x = base64URL;
        if (base64URL2 == null) {
            throw new IllegalArgumentException("The 'y' coordinate must not be null");
        }
        this.f86y = base64URL2;
        ensurePublicCoordinatesOnCurve(curve, base64URL, base64URL2);
        if (base64URL3 == null) {
            throw new IllegalArgumentException("The 'd' coordinate must not be null");
        }
        this.f84d = base64URL3;
        this.privateKey = null;
    }

    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, PrivateKey privateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        super(KeyType.f91EC, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
        if (curve == null) {
            throw new IllegalArgumentException("The curve must not be null");
        }
        this.crv = curve;
        if (base64URL == null) {
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        this.f85x = base64URL;
        if (base64URL2 == null) {
            throw new IllegalArgumentException("The 'y' coordinate must not be null");
        }
        this.f86y = base64URL2;
        ensurePublicCoordinatesOnCurve(curve, base64URL, base64URL2);
        this.f84d = null;
        this.privateKey = privateKey;
    }

    public ECKey(Curve curve, ECPublicKey eCPublicKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(curve, encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineX()), encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineY()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public ECKey(Curve curve, ECPublicKey eCPublicKey, PrivateKey privateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(curve, encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineX()), encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineY()), privateKey, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public ECKey(Curve curve, ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(curve, encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineX()), encodeCoordinate(eCPublicKey.getParams().getCurve().getField().getFieldSize(), eCPublicKey.getW().getAffineY()), encodeCoordinate(eCPrivateKey.getParams().getCurve().getField().getFieldSize(), eCPrivateKey.getS()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public static Base64URL encodeCoordinate(int i, BigInteger bigInteger) {
        byte[] bytesUnsigned = BigIntegerUtils.toBytesUnsigned(bigInteger);
        int i2 = (i + 7) / 8;
        if (bytesUnsigned.length >= i2) {
            return Base64URL.m3704encode(bytesUnsigned);
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(bytesUnsigned, 0, bArr, i2 - bytesUnsigned.length, bytesUnsigned.length);
        return Base64URL.m3704encode(bArr);
    }

    private static void ensurePublicCoordinatesOnCurve(Curve curve, Base64URL base64URL, Base64URL base64URL2) {
        if (!SUPPORTED_CURVES.contains(curve)) {
            throw new IllegalArgumentException("Unknown / unsupported curve: " + curve);
        }
        if (ECChecks.isPointOnCurve(base64URL.decodeToBigInteger(), base64URL2.decodeToBigInteger(), curve.toECParameterSpec())) {
            return;
        }
        throw new IllegalArgumentException("Invalid EC JWK: The 'x' and 'y' public coordinates are not on the " + curve + " curve");
    }

    public static ECKey load(KeyStore keyStore, String str, char[] cArr) throws KeyStoreException, JOSEException {
        Certificate certificate = keyStore.getCertificate(str);
        if (certificate == null || !(certificate instanceof X509Certificate)) {
            return null;
        }
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (!(x509Certificate.getPublicKey() instanceof ECPublicKey)) {
            throw new JOSEException("Couldn't load EC JWK: The key algorithm is not EC");
        }
        ECKey build = new Builder(parse(x509Certificate)).keyID(str).keyStore(keyStore).build();
        try {
            Key key = keyStore.getKey(str, cArr);
            return key instanceof ECPrivateKey ? new Builder(build).privateKey((ECPrivateKey) key).build() : ((key instanceof PrivateKey) && "EC".equalsIgnoreCase(key.getAlgorithm())) ? new Builder(build).privateKey((PrivateKey) key).build() : build;
        } catch (NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new JOSEException("Couldn't retrieve private EC key (bad pin?): " + e.getMessage(), e);
        }
    }

    public static ECKey parse(String str) throws ParseException {
        return parse(JSONObjectUtils.parse(str));
    }

    public static ECKey parse(X509Certificate x509Certificate) throws JOSEException {
        if (!(x509Certificate.getPublicKey() instanceof ECPublicKey)) {
            throw new JOSEException("The public key of the X.509 certificate is not EC");
        }
        ECPublicKey eCPublicKey = (ECPublicKey) x509Certificate.getPublicKey();
        try {
            String obj = new JcaX509CertificateHolder(x509Certificate).getSubjectPublicKeyInfo().getAlgorithm().getParameters().toString();
            Curve forOID = Curve.forOID(obj);
            if (forOID != null) {
                return new Builder(forOID, eCPublicKey).keyUse(KeyUse.from(x509Certificate)).keyID(x509Certificate.getSerialNumber().toString(10)).x509CertChain(Collections.singletonList(Base64.encode(x509Certificate.getEncoded()))).x509CertSHA256Thumbprint(Base64URL.m3704encode(MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE).digest(x509Certificate.getEncoded()))).build();
            }
            throw new JOSEException("Couldn't determine EC JWK curve for OID " + obj);
        } catch (NoSuchAlgorithmException e) {
            throw new JOSEException("Couldn't encode x5t parameter: " + e.getMessage(), e);
        } catch (CertificateEncodingException e2) {
            throw new JOSEException("Couldn't encode x5c parameter: " + e2.getMessage(), e2);
        }
    }

    public static ECKey parse(JSONObject jSONObject) throws ParseException {
        Curve parse = Curve.parse(JSONObjectUtils.getString(jSONObject, "crv"));
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject, "x"));
        Base64URL base64URL2 = new Base64URL(JSONObjectUtils.getString(jSONObject, "y"));
        if (JWKMetadata.parseKeyType(jSONObject) != KeyType.f91EC) {
            throw new ParseException("The key type \"kty\" must be EC", 0);
        }
        Base64URL base64URL3 = null;
        if (jSONObject.get("d") != null) {
            base64URL3 = new Base64URL(JSONObjectUtils.getString(jSONObject, "d"));
        }
        try {
            return base64URL3 == null ? new ECKey(parse, base64URL, base64URL2, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), (KeyStore) null) : new ECKey(parse, base64URL, base64URL2, base64URL3, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), (KeyStore) null);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    @Override // com.nimbusds.jose.jwk.CurveBasedJWK
    public Curve getCurve() {
        return this.crv;
    }

    public Base64URL getD() {
        return this.f84d;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("crv", this.crv.toString());
        linkedHashMap.put("kty", getKeyType().getValue());
        linkedHashMap.put("x", this.f85x.toString());
        linkedHashMap.put("y", this.f86y.toString());
        return linkedHashMap;
    }

    public Base64URL getX() {
        return this.f85x;
    }

    public Base64URL getY() {
        return this.f86y;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public boolean isPrivate() {
        return (this.f84d == null && this.privateKey == null) ? false : true;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public int size() {
        ECParameterSpec eCParameterSpec = this.crv.toECParameterSpec();
        if (eCParameterSpec != null) {
            return eCParameterSpec.getCurve().getField().getFieldSize();
        }
        throw new UnsupportedOperationException("Couldn't determine field size for curve " + this.crv.getName());
    }

    public ECPrivateKey toECPrivateKey() throws JOSEException {
        return toECPrivateKey(null);
    }

    public ECPrivateKey toECPrivateKey(Provider provider) throws JOSEException {
        if (this.f84d == null) {
            return null;
        }
        ECParameterSpec eCParameterSpec = this.crv.toECParameterSpec();
        if (eCParameterSpec == null) {
            throw new JOSEException("Couldn't get EC parameter spec for curve " + this.crv);
        }
        try {
            return (ECPrivateKey) (provider == null ? KeyFactory.getInstance("EC") : KeyFactory.getInstance("EC", provider)).generatePrivate(new ECPrivateKeySpec(this.f84d.decodeToBigInteger(), eCParameterSpec));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    public ECPublicKey toECPublicKey() throws JOSEException {
        return toECPublicKey(null);
    }

    public ECPublicKey toECPublicKey(Provider provider) throws JOSEException {
        ECParameterSpec eCParameterSpec = this.crv.toECParameterSpec();
        if (eCParameterSpec == null) {
            throw new JOSEException("Couldn't get EC parameter spec for curve " + this.crv);
        }
        try {
            return (ECPublicKey) (provider == null ? KeyFactory.getInstance("EC") : KeyFactory.getInstance("EC", provider)).generatePublic(new ECPublicKeySpec(new ECPoint(this.f85x.decodeToBigInteger(), this.f86y.decodeToBigInteger()), eCParameterSpec));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("crv", this.crv.toString());
        jSONObject.put("x", this.f85x.toString());
        jSONObject.put("y", this.f86y.toString());
        Base64URL base64URL = this.f84d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        return jSONObject;
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public KeyPair toKeyPair() throws JOSEException {
        return toKeyPair(null);
    }

    public KeyPair toKeyPair(Provider provider) throws JOSEException {
        return this.privateKey != null ? new KeyPair(toECPublicKey(provider), this.privateKey) : new KeyPair(toECPublicKey(provider), toECPrivateKey(provider));
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public PrivateKey toPrivateKey() throws JOSEException {
        ECPrivateKey eCPrivateKey = toECPrivateKey();
        return eCPrivateKey != null ? eCPrivateKey : this.privateKey;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public ECKey toPublicJWK() {
        return new ECKey(getCurve(), getX(), getY(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertSHA256Thumbprint(), getX509CertChain(), getKeyStore());
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public PublicKey toPublicKey() throws JOSEException {
        return toECPublicKey();
    }
}
