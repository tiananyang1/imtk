package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.IntegerOverflowException;
import com.nimbusds.jose.util.JSONObjectUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.Serializable;
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
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAMultiPrimePrivateCrtKeySpec;
import java.security.spec.RSAOtherPrimeInfo;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/RSAKey.class */
public final class RSAKey extends JWK implements AssymetricJWK {
    private static final long serialVersionUID = 1;

    /* renamed from: d */
    private final Base64URL f100d;

    /* renamed from: dp */
    private final Base64URL f101dp;

    /* renamed from: dq */
    private final Base64URL f102dq;

    /* renamed from: e */
    private final Base64URL f103e;

    /* renamed from: n */
    private final Base64URL f104n;
    private final List<OtherPrimesInfo> oth;

    /* renamed from: p */
    private final Base64URL f105p;
    private final PrivateKey privateKey;

    /* renamed from: q */
    private final Base64URL f106q;

    /* renamed from: qi */
    private final Base64URL f107qi;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/RSAKey$Builder.class */
    public static class Builder {
        private Algorithm alg;

        /* renamed from: d */
        private Base64URL f108d;

        /* renamed from: dp */
        private Base64URL f109dp;

        /* renamed from: dq */
        private Base64URL f110dq;

        /* renamed from: e */
        private final Base64URL f111e;
        private String kid;

        /* renamed from: ks */
        private KeyStore f112ks;

        /* renamed from: n */
        private final Base64URL f113n;
        private Set<KeyOperation> ops;
        private List<OtherPrimesInfo> oth;

        /* renamed from: p */
        private Base64URL f114p;
        private PrivateKey priv;

        /* renamed from: q */
        private Base64URL f115q;

        /* renamed from: qi */
        private Base64URL f116qi;
        private KeyUse use;
        private List<Base64> x5c;

        @Deprecated
        private Base64URL x5t;
        private Base64URL x5t256;
        private URI x5u;

        public Builder(RSAKey rSAKey) {
            this.f113n = rSAKey.f104n;
            this.f111e = rSAKey.f103e;
            this.f108d = rSAKey.f100d;
            this.f114p = rSAKey.f105p;
            this.f115q = rSAKey.f106q;
            this.f109dp = rSAKey.f101dp;
            this.f110dq = rSAKey.f102dq;
            this.f116qi = rSAKey.f107qi;
            this.oth = rSAKey.oth;
            this.priv = rSAKey.privateKey;
            this.use = rSAKey.getKeyUse();
            this.ops = rSAKey.getKeyOperations();
            this.alg = rSAKey.getAlgorithm();
            this.kid = rSAKey.getKeyID();
            this.x5u = rSAKey.getX509CertURL();
            this.x5t = rSAKey.getX509CertThumbprint();
            this.x5t256 = rSAKey.getX509CertSHA256Thumbprint();
            this.x5c = rSAKey.getX509CertChain();
            this.f112ks = rSAKey.getKeyStore();
        }

        public Builder(Base64URL base64URL, Base64URL base64URL2) {
            if (base64URL == null) {
                throw new IllegalArgumentException("The modulus value must not be null");
            }
            this.f113n = base64URL;
            if (base64URL2 == null) {
                throw new IllegalArgumentException("The public exponent value must not be null");
            }
            this.f111e = base64URL2;
        }

        public Builder(RSAPublicKey rSAPublicKey) {
            this.f113n = Base64URL.m3703encode(rSAPublicKey.getModulus());
            this.f111e = Base64URL.m3703encode(rSAPublicKey.getPublicExponent());
        }

        public Builder algorithm(Algorithm algorithm) {
            this.alg = algorithm;
            return this;
        }

        public RSAKey build() {
            try {
                return new RSAKey(this.f113n, this.f111e, this.f108d, this.f114p, this.f115q, this.f109dp, this.f110dq, this.f116qi, this.oth, this.priv, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.f112ks);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }

        public Builder firstCRTCoefficient(Base64URL base64URL) {
            this.f116qi = base64URL;
            return this;
        }

        public Builder firstFactorCRTExponent(Base64URL base64URL) {
            this.f109dp = base64URL;
            return this;
        }

        public Builder firstPrimeFactor(Base64URL base64URL) {
            this.f114p = base64URL;
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
            linkedHashMap.put("e", this.f111e.toString());
            linkedHashMap.put("kty", KeyType.RSA.getValue());
            linkedHashMap.put("n", this.f113n.toString());
            this.kid = ThumbprintUtils.compute(str, (LinkedHashMap<String, ?>) linkedHashMap).toString();
            return this;
        }

        public Builder keyOperations(Set<KeyOperation> set) {
            this.ops = set;
            return this;
        }

        public Builder keyStore(KeyStore keyStore) {
            this.f112ks = keyStore;
            return this;
        }

        public Builder keyUse(KeyUse keyUse) {
            this.use = keyUse;
            return this;
        }

        public Builder otherPrimes(List<OtherPrimesInfo> list) {
            this.oth = list;
            return this;
        }

        public Builder privateExponent(Base64URL base64URL) {
            this.f108d = base64URL;
            return this;
        }

        public Builder privateKey(PrivateKey privateKey) {
            if (!"RSA".equalsIgnoreCase(privateKey.getAlgorithm())) {
                throw new IllegalArgumentException("The private key algorithm must be RSA");
            }
            this.priv = privateKey;
            return this;
        }

        public Builder privateKey(RSAMultiPrimePrivateCrtKey rSAMultiPrimePrivateCrtKey) {
            this.f108d = Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrivateExponent());
            this.f114p = Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrimeP());
            this.f115q = Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrimeQ());
            this.f109dp = Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentP());
            this.f110dq = Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentQ());
            this.f116qi = Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getCrtCoefficient());
            this.oth = OtherPrimesInfo.toList(rSAMultiPrimePrivateCrtKey.getOtherPrimeInfo());
            return this;
        }

        public Builder privateKey(RSAPrivateCrtKey rSAPrivateCrtKey) {
            this.f108d = Base64URL.m3703encode(rSAPrivateCrtKey.getPrivateExponent());
            this.f114p = Base64URL.m3703encode(rSAPrivateCrtKey.getPrimeP());
            this.f115q = Base64URL.m3703encode(rSAPrivateCrtKey.getPrimeQ());
            this.f109dp = Base64URL.m3703encode(rSAPrivateCrtKey.getPrimeExponentP());
            this.f110dq = Base64URL.m3703encode(rSAPrivateCrtKey.getPrimeExponentQ());
            this.f116qi = Base64URL.m3703encode(rSAPrivateCrtKey.getCrtCoefficient());
            return this;
        }

        public Builder privateKey(RSAPrivateKey rSAPrivateKey) {
            if (rSAPrivateKey instanceof RSAPrivateCrtKey) {
                return privateKey((RSAPrivateCrtKey) rSAPrivateKey);
            }
            if (rSAPrivateKey instanceof RSAMultiPrimePrivateCrtKey) {
                return privateKey((RSAMultiPrimePrivateCrtKey) rSAPrivateKey);
            }
            this.f108d = Base64URL.m3703encode(rSAPrivateKey.getPrivateExponent());
            return this;
        }

        public Builder secondFactorCRTExponent(Base64URL base64URL) {
            this.f110dq = base64URL;
            return this;
        }

        public Builder secondPrimeFactor(Base64URL base64URL) {
            this.f115q = base64URL;
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

    @Immutable
    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/RSAKey$OtherPrimesInfo.class */
    public static class OtherPrimesInfo implements Serializable {
        private static final long serialVersionUID = 1;

        /* renamed from: d */
        private final Base64URL f117d;

        /* renamed from: r */
        private final Base64URL f118r;

        /* renamed from: t */
        private final Base64URL f119t;

        public OtherPrimesInfo(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) {
            if (base64URL == null) {
                throw new IllegalArgumentException("The prime factor must not be null");
            }
            this.f118r = base64URL;
            if (base64URL2 == null) {
                throw new IllegalArgumentException("The factor CRT exponent must not be null");
            }
            this.f117d = base64URL2;
            if (base64URL3 == null) {
                throw new IllegalArgumentException("The factor CRT coefficient must not be null");
            }
            this.f119t = base64URL3;
        }

        public OtherPrimesInfo(RSAOtherPrimeInfo rSAOtherPrimeInfo) {
            this.f118r = Base64URL.m3703encode(rSAOtherPrimeInfo.getPrime());
            this.f117d = Base64URL.m3703encode(rSAOtherPrimeInfo.getExponent());
            this.f119t = Base64URL.m3703encode(rSAOtherPrimeInfo.getCrtCoefficient());
        }

        public static List<OtherPrimesInfo> toList(RSAOtherPrimeInfo[] rSAOtherPrimeInfoArr) {
            ArrayList arrayList = new ArrayList();
            if (rSAOtherPrimeInfoArr == null) {
                return arrayList;
            }
            int length = rSAOtherPrimeInfoArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return arrayList;
                }
                arrayList.add(new OtherPrimesInfo(rSAOtherPrimeInfoArr[i2]));
                i = i2 + 1;
            }
        }

        public Base64URL getFactorCRTCoefficient() {
            return this.f119t;
        }

        public Base64URL getFactorCRTExponent() {
            return this.f117d;
        }

        public Base64URL getPrimeFactor() {
            return this.f118r;
        }
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        this(base64URL, base64URL2, null, null, null, null, null, null, null, null, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL4, Base64URL base64URL5, List<Base64> list, KeyStore keyStore) {
        this(base64URL, base64URL2, base64URL3, null, null, null, null, null, null, null, keyUse, set, algorithm, str, uri, base64URL4, base64URL5, list, keyStore);
        if (base64URL3 == null) {
            throw new IllegalArgumentException("The private exponent must not be null");
        }
    }

    @Deprecated
    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, Base64URL base64URL8, List<OtherPrimesInfo> list, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL9, Base64URL base64URL10, List<Base64> list2) {
        this(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, list, null, keyUse, set, algorithm, str, uri, base64URL9, base64URL10, list2, null);
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, Base64URL base64URL8, List<OtherPrimesInfo> list, PrivateKey privateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL9, Base64URL base64URL10, List<Base64> list2, KeyStore keyStore) {
        super(KeyType.RSA, keyUse, set, algorithm, str, uri, base64URL9, base64URL10, list2, keyStore);
        if (base64URL == null) {
            throw new IllegalArgumentException("The modulus value must not be null");
        }
        this.f104n = base64URL;
        if (base64URL2 == null) {
            throw new IllegalArgumentException("The public exponent value must not be null");
        }
        this.f103e = base64URL2;
        this.f100d = base64URL3;
        if (base64URL4 != null && base64URL5 != null && base64URL6 != null && base64URL7 != null && base64URL8 != null) {
            this.f105p = base64URL4;
            this.f106q = base64URL5;
            this.f101dp = base64URL6;
            this.f102dq = base64URL7;
            this.f107qi = base64URL8;
            if (list != null) {
                this.oth = Collections.unmodifiableList(list);
            } else {
                this.oth = Collections.emptyList();
            }
        } else if (base64URL4 == null && base64URL5 == null && base64URL6 == null && base64URL7 == null && base64URL8 == null && list == null) {
            this.f105p = null;
            this.f106q = null;
            this.f101dp = null;
            this.f102dq = null;
            this.f107qi = null;
            this.oth = Collections.emptyList();
        } else {
            if (base64URL4 != null || base64URL5 != null || base64URL6 != null || base64URL7 != null || base64URL8 != null) {
                if (base64URL4 == null) {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The first prime factor must not be null");
                }
                if (base64URL5 == null) {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The second prime factor must not be null");
                }
                if (base64URL6 == null) {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The first factor CRT exponent must not be null");
                }
                if (base64URL7 != null) {
                    throw new IllegalArgumentException("Incomplete second private (CRT) representation: The first CRT coefficient must not be null");
                }
                throw new IllegalArgumentException("Incomplete second private (CRT) representation: The second factor CRT exponent must not be null");
            }
            this.f105p = null;
            this.f106q = null;
            this.f101dp = null;
            this.f102dq = null;
            this.f107qi = null;
            this.oth = Collections.emptyList();
        }
        this.privateKey = privateKey;
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, List<OtherPrimesInfo> list, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL8, Base64URL base64URL9, List<Base64> list2, KeyStore keyStore) {
        this(base64URL, base64URL2, null, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, list, null, keyUse, set, algorithm, str, uri, base64URL8, base64URL9, list2, keyStore);
        if (base64URL3 == null) {
            throw new IllegalArgumentException("The first prime factor must not be null");
        }
        if (base64URL4 == null) {
            throw new IllegalArgumentException("The second prime factor must not be null");
        }
        if (base64URL5 == null) {
            throw new IllegalArgumentException("The first factor CRT exponent must not be null");
        }
        if (base64URL6 == null) {
            throw new IllegalArgumentException("The second factor CRT exponent must not be null");
        }
        if (base64URL7 == null) {
            throw new IllegalArgumentException("The first CRT coefficient must not be null");
        }
    }

    public RSAKey(RSAPublicKey rSAPublicKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.m3703encode(rSAPublicKey.getModulus()), Base64URL.m3703encode(rSAPublicKey.getPublicExponent()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, PrivateKey privateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.m3703encode(rSAPublicKey.getModulus()), Base64URL.m3703encode(rSAPublicKey.getPublicExponent()), null, null, null, null, null, null, null, privateKey, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAMultiPrimePrivateCrtKey rSAMultiPrimePrivateCrtKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.m3703encode(rSAPublicKey.getModulus()), Base64URL.m3703encode(rSAPublicKey.getPublicExponent()), Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrivateExponent()), Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrimeP()), Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrimeQ()), Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentP()), Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentQ()), Base64URL.m3703encode(rSAMultiPrimePrivateCrtKey.getCrtCoefficient()), OtherPrimesInfo.toList(rSAMultiPrimePrivateCrtKey.getOtherPrimeInfo()), null, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAPrivateCrtKey rSAPrivateCrtKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.m3703encode(rSAPublicKey.getModulus()), Base64URL.m3703encode(rSAPublicKey.getPublicExponent()), Base64URL.m3703encode(rSAPrivateCrtKey.getPrivateExponent()), Base64URL.m3703encode(rSAPrivateCrtKey.getPrimeP()), Base64URL.m3703encode(rSAPrivateCrtKey.getPrimeQ()), Base64URL.m3703encode(rSAPrivateCrtKey.getPrimeExponentP()), Base64URL.m3703encode(rSAPrivateCrtKey.getPrimeExponentQ()), Base64URL.m3703encode(rSAPrivateCrtKey.getCrtCoefficient()), null, null, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAPrivateKey rSAPrivateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(Base64URL.m3703encode(rSAPublicKey.getModulus()), Base64URL.m3703encode(rSAPublicKey.getPublicExponent()), Base64URL.m3703encode(rSAPrivateKey.getPrivateExponent()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, keyStore);
    }

    /* renamed from: load, reason: collision with other method in class */
    public static RSAKey m3695load(KeyStore keyStore, String str, char[] cArr) throws KeyStoreException, JOSEException {
        Certificate certificate = keyStore.getCertificate(str);
        if (certificate == null || !(certificate instanceof X509Certificate)) {
            return null;
        }
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (!(x509Certificate.getPublicKey() instanceof RSAPublicKey)) {
            throw new JOSEException("Couldn't load RSA JWK: The key algorithm is not RSA");
        }
        RSAKey build = new Builder(m3697parse(x509Certificate)).keyID(str).keyStore(keyStore).build();
        try {
            Key key = keyStore.getKey(str, cArr);
            return key instanceof RSAPrivateKey ? new Builder(build).privateKey((RSAPrivateKey) key).build() : ((key instanceof PrivateKey) && "RSA".equalsIgnoreCase(key.getAlgorithm())) ? new Builder(build).privateKey((PrivateKey) key).build() : build;
        } catch (NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new JOSEException("Couldn't retrieve private RSA key (bad pin?): " + e.getMessage(), e);
        }
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static RSAKey m3696parse(String str) throws ParseException {
        return m3698parse(JSONObjectUtils.parse(str));
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static RSAKey m3697parse(X509Certificate x509Certificate) throws JOSEException {
        if (!(x509Certificate.getPublicKey() instanceof RSAPublicKey)) {
            throw new JOSEException("The public key of the X.509 certificate is not RSA");
        }
        try {
            return new Builder((RSAPublicKey) x509Certificate.getPublicKey()).keyUse(KeyUse.from(x509Certificate)).keyID(x509Certificate.getSerialNumber().toString(10)).x509CertChain(Collections.singletonList(Base64.encode(x509Certificate.getEncoded()))).x509CertSHA256Thumbprint(Base64URL.m3704encode(MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE).digest(x509Certificate.getEncoded()))).build();
        } catch (NoSuchAlgorithmException e) {
            throw new JOSEException("Couldn't encode x5t parameter: " + e.getMessage(), e);
        } catch (CertificateEncodingException e2) {
            throw new JOSEException("Couldn't encode x5c parameter: " + e2.getMessage(), e2);
        }
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static RSAKey m3698parse(JSONObject jSONObject) throws ParseException {
        ArrayList arrayList;
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject, "n"));
        Base64URL base64URL2 = new Base64URL(JSONObjectUtils.getString(jSONObject, "e"));
        if (KeyType.parse(JSONObjectUtils.getString(jSONObject, "kty")) != KeyType.RSA) {
            throw new ParseException("The key type \"kty\" must be RSA", 0);
        }
        Base64URL base64URL3 = jSONObject.containsKey("d") ? new Base64URL(JSONObjectUtils.getString(jSONObject, "d")) : null;
        Base64URL base64URL4 = jSONObject.containsKey("p") ? new Base64URL(JSONObjectUtils.getString(jSONObject, "p")) : null;
        Base64URL base64URL5 = jSONObject.containsKey("q") ? new Base64URL(JSONObjectUtils.getString(jSONObject, "q")) : null;
        Base64URL base64URL6 = jSONObject.containsKey("dp") ? new Base64URL(JSONObjectUtils.getString(jSONObject, "dp")) : null;
        Base64URL base64URL7 = jSONObject.containsKey("dq") ? new Base64URL(JSONObjectUtils.getString(jSONObject, "dq")) : null;
        Base64URL base64URL8 = jSONObject.containsKey("qi") ? new Base64URL(JSONObjectUtils.getString(jSONObject, "qi")) : null;
        if (jSONObject.containsKey("oth")) {
            JSONArray jSONArray = JSONObjectUtils.getJSONArray(jSONObject, "oth");
            arrayList = new ArrayList(jSONArray.size());
            Iterator it = jSONArray.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) next;
                    arrayList.add(new OtherPrimesInfo(new Base64URL(JSONObjectUtils.getString(jSONObject2, "r")), new Base64URL(JSONObjectUtils.getString(jSONObject2, "dq")), new Base64URL(JSONObjectUtils.getString(jSONObject2, "t"))));
                }
            }
        } else {
            arrayList = null;
        }
        try {
            return new RSAKey(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, arrayList, null, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), null);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public Base64URL getFirstCRTCoefficient() {
        return this.f107qi;
    }

    public Base64URL getFirstFactorCRTExponent() {
        return this.f101dp;
    }

    public Base64URL getFirstPrimeFactor() {
        return this.f105p;
    }

    public Base64URL getModulus() {
        return this.f104n;
    }

    public List<OtherPrimesInfo> getOtherPrimes() {
        return this.oth;
    }

    public Base64URL getPrivateExponent() {
        return this.f100d;
    }

    public Base64URL getPublicExponent() {
        return this.f103e;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("e", this.f103e.toString());
        linkedHashMap.put("kty", getKeyType().getValue());
        linkedHashMap.put("n", this.f104n.toString());
        return linkedHashMap;
    }

    public Base64URL getSecondFactorCRTExponent() {
        return this.f102dq;
    }

    public Base64URL getSecondPrimeFactor() {
        return this.f106q;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public boolean isPrivate() {
        return (this.f100d == null && this.f105p == null && this.privateKey == null) ? false : true;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public int size() {
        try {
            return ByteUtils.safeBitLength(this.f104n.decode());
        } catch (IntegerOverflowException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("n", this.f104n.toString());
        jSONObject.put("e", this.f103e.toString());
        Base64URL base64URL = this.f100d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        Base64URL base64URL2 = this.f105p;
        if (base64URL2 != null) {
            jSONObject.put("p", base64URL2.toString());
        }
        Base64URL base64URL3 = this.f106q;
        if (base64URL3 != null) {
            jSONObject.put("q", base64URL3.toString());
        }
        Base64URL base64URL4 = this.f101dp;
        if (base64URL4 != null) {
            jSONObject.put("dp", base64URL4.toString());
        }
        Base64URL base64URL5 = this.f102dq;
        if (base64URL5 != null) {
            jSONObject.put("dq", base64URL5.toString());
        }
        Base64URL base64URL6 = this.f107qi;
        if (base64URL6 != null) {
            jSONObject.put("qi", base64URL6.toString());
        }
        List<OtherPrimesInfo> list = this.oth;
        if (list == null || list.isEmpty()) {
            return jSONObject;
        }
        JSONArray jSONArray = new JSONArray();
        for (OtherPrimesInfo otherPrimesInfo : this.oth) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("r", otherPrimesInfo.f118r.toString());
            jSONObject2.put("d", otherPrimesInfo.f117d.toString());
            jSONObject2.put("t", otherPrimesInfo.f119t.toString());
            jSONArray.add(jSONObject2);
        }
        jSONObject.put("oth", jSONArray);
        return jSONObject;
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public KeyPair toKeyPair() throws JOSEException {
        return new KeyPair(toRSAPublicKey(), toPrivateKey());
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public PrivateKey toPrivateKey() throws JOSEException {
        RSAPrivateKey rSAPrivateKey = toRSAPrivateKey();
        return rSAPrivateKey != null ? rSAPrivateKey : this.privateKey;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public RSAKey toPublicJWK() {
        return new RSAKey(getModulus(), getPublicExponent(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertSHA256Thumbprint(), getX509CertChain(), getKeyStore());
    }

    @Override // com.nimbusds.jose.jwk.AssymetricJWK
    public PublicKey toPublicKey() throws JOSEException {
        return toRSAPublicKey();
    }

    public RSAPrivateKey toRSAPrivateKey() throws JOSEException {
        RSAPrivateKeySpec rSAPrivateCrtKeySpec;
        if (this.f100d == null) {
            return null;
        }
        BigInteger decodeToBigInteger = this.f104n.decodeToBigInteger();
        BigInteger decodeToBigInteger2 = this.f100d.decodeToBigInteger();
        if (this.f105p == null) {
            rSAPrivateCrtKeySpec = new RSAPrivateKeySpec(decodeToBigInteger, decodeToBigInteger2);
        } else {
            BigInteger decodeToBigInteger3 = this.f103e.decodeToBigInteger();
            BigInteger decodeToBigInteger4 = this.f105p.decodeToBigInteger();
            BigInteger decodeToBigInteger5 = this.f106q.decodeToBigInteger();
            BigInteger decodeToBigInteger6 = this.f101dp.decodeToBigInteger();
            BigInteger decodeToBigInteger7 = this.f102dq.decodeToBigInteger();
            BigInteger decodeToBigInteger8 = this.f107qi.decodeToBigInteger();
            List<OtherPrimesInfo> list = this.oth;
            if (list == null || list.isEmpty()) {
                rSAPrivateCrtKeySpec = new RSAPrivateCrtKeySpec(decodeToBigInteger, decodeToBigInteger3, decodeToBigInteger2, decodeToBigInteger4, decodeToBigInteger5, decodeToBigInteger6, decodeToBigInteger7, decodeToBigInteger8);
            } else {
                RSAOtherPrimeInfo[] rSAOtherPrimeInfoArr = new RSAOtherPrimeInfo[this.oth.size()];
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.oth.size()) {
                        break;
                    }
                    OtherPrimesInfo otherPrimesInfo = this.oth.get(i2);
                    rSAOtherPrimeInfoArr[i2] = new RSAOtherPrimeInfo(otherPrimesInfo.getPrimeFactor().decodeToBigInteger(), otherPrimesInfo.getFactorCRTExponent().decodeToBigInteger(), otherPrimesInfo.getFactorCRTCoefficient().decodeToBigInteger());
                    i = i2 + 1;
                }
                rSAPrivateCrtKeySpec = new RSAMultiPrimePrivateCrtKeySpec(decodeToBigInteger, decodeToBigInteger3, decodeToBigInteger2, decodeToBigInteger4, decodeToBigInteger5, decodeToBigInteger6, decodeToBigInteger7, decodeToBigInteger8, rSAOtherPrimeInfoArr);
            }
        }
        try {
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(rSAPrivateCrtKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    public RSAPublicKey toRSAPublicKey() throws JOSEException {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(this.f104n.decodeToBigInteger(), this.f103e.decodeToBigInteger()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }
}
