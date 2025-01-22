package com.nimbusds.jose;

import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jose.util.X509CertChainUtils;
import java.net.URI;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWEHeader.class */
public final class JWEHeader extends CommonSEHeader {
    private static final Set<String> REGISTERED_PARAMETER_NAMES;
    private static final long serialVersionUID = 1;
    private final Base64URL apu;
    private final Base64URL apv;
    private final EncryptionMethod enc;
    private final ECKey epk;

    /* renamed from: iv */
    private final Base64URL f78iv;
    private final int p2c;
    private final Base64URL p2s;
    private final Base64URL tag;
    private final CompressionAlgorithm zip;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWEHeader$Builder.class */
    public static class Builder {
        private final JWEAlgorithm alg;
        private Base64URL apu;
        private Base64URL apv;
        private Set<String> crit;
        private String cty;
        private Map<String, Object> customParams;
        private final EncryptionMethod enc;
        private ECKey epk;

        /* renamed from: iv */
        private Base64URL f79iv;
        private URI jku;
        private JWK jwk;
        private String kid;
        private int p2c;
        private Base64URL p2s;
        private Base64URL parsedBase64URL;
        private Base64URL tag;
        private JOSEObjectType typ;
        private List<Base64> x5c;

        @Deprecated
        private Base64URL x5t;
        private Base64URL x5t256;
        private URI x5u;
        private CompressionAlgorithm zip;

        public Builder(JWEAlgorithm jWEAlgorithm, EncryptionMethod encryptionMethod) {
            if (jWEAlgorithm.getName().equals(Algorithm.NONE.getName())) {
                throw new IllegalArgumentException("The JWE algorithm \"alg\" cannot be \"none\"");
            }
            this.alg = jWEAlgorithm;
            if (encryptionMethod == null) {
                throw new IllegalArgumentException("The encryption method \"enc\" parameter must not be null");
            }
            this.enc = encryptionMethod;
        }

        public Builder(JWEHeader jWEHeader) {
            this(jWEHeader.getAlgorithm(), jWEHeader.getEncryptionMethod());
            this.typ = jWEHeader.getType();
            this.cty = jWEHeader.getContentType();
            this.crit = jWEHeader.getCriticalParams();
            this.customParams = jWEHeader.getCustomParams();
            this.jku = jWEHeader.getJWKURL();
            this.jwk = jWEHeader.getJWK();
            this.x5u = jWEHeader.getX509CertURL();
            this.x5t = jWEHeader.getX509CertThumbprint();
            this.x5t256 = jWEHeader.getX509CertSHA256Thumbprint();
            this.x5c = jWEHeader.getX509CertChain();
            this.kid = jWEHeader.getKeyID();
            this.epk = jWEHeader.getEphemeralPublicKey();
            this.zip = jWEHeader.getCompressionAlgorithm();
            this.apu = jWEHeader.getAgreementPartyUInfo();
            this.apv = jWEHeader.getAgreementPartyVInfo();
            this.p2s = jWEHeader.getPBES2Salt();
            this.p2c = jWEHeader.getPBES2Count();
            this.f79iv = jWEHeader.getIV();
            this.tag = jWEHeader.getAuthTag();
            this.customParams = jWEHeader.getCustomParams();
        }

        public Builder agreementPartyUInfo(Base64URL base64URL) {
            this.apu = base64URL;
            return this;
        }

        public Builder agreementPartyVInfo(Base64URL base64URL) {
            this.apv = base64URL;
            return this;
        }

        public Builder authTag(Base64URL base64URL) {
            this.tag = base64URL;
            return this;
        }

        public JWEHeader build() {
            return new JWEHeader(this.alg, this.enc, this.typ, this.cty, this.crit, this.jku, this.jwk, this.x5u, this.x5t, this.x5t256, this.x5c, this.kid, this.epk, this.zip, this.apu, this.apv, this.p2s, this.p2c, this.f79iv, this.tag, this.customParams, this.parsedBase64URL);
        }

        public Builder compressionAlgorithm(CompressionAlgorithm compressionAlgorithm) {
            this.zip = compressionAlgorithm;
            return this;
        }

        public Builder contentType(String str) {
            this.cty = str;
            return this;
        }

        public Builder criticalParams(Set<String> set) {
            this.crit = set;
            return this;
        }

        public Builder customParam(String str, Object obj) {
            if (JWEHeader.getRegisteredParameterNames().contains(str)) {
                throw new IllegalArgumentException("The parameter name \"" + str + "\" matches a registered name");
            }
            if (this.customParams == null) {
                this.customParams = new HashMap();
            }
            this.customParams.put(str, obj);
            return this;
        }

        public Builder customParams(Map<String, Object> map) {
            this.customParams = map;
            return this;
        }

        public Builder ephemeralPublicKey(ECKey eCKey) {
            this.epk = eCKey;
            return this;
        }

        /* renamed from: iv */
        public Builder m10iv(Base64URL base64URL) {
            this.f79iv = base64URL;
            return this;
        }

        public Builder jwk(JWK jwk) {
            this.jwk = jwk;
            return this;
        }

        public Builder jwkURL(URI uri) {
            this.jku = uri;
            return this;
        }

        public Builder keyID(String str) {
            this.kid = str;
            return this;
        }

        public Builder parsedBase64URL(Base64URL base64URL) {
            this.parsedBase64URL = base64URL;
            return this;
        }

        public Builder pbes2Count(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("The PBES2 count parameter must not be negative");
            }
            this.p2c = i;
            return this;
        }

        public Builder pbes2Salt(Base64URL base64URL) {
            this.p2s = base64URL;
            return this;
        }

        public Builder type(JOSEObjectType jOSEObjectType) {
            this.typ = jOSEObjectType;
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

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("alg");
        hashSet.add("enc");
        hashSet.add("epk");
        hashSet.add("zip");
        hashSet.add("jku");
        hashSet.add("jwk");
        hashSet.add("x5u");
        hashSet.add("x5t");
        hashSet.add("x5t#S256");
        hashSet.add("x5c");
        hashSet.add("kid");
        hashSet.add("typ");
        hashSet.add("cty");
        hashSet.add("crit");
        hashSet.add("apu");
        hashSet.add("apv");
        hashSet.add("p2s");
        hashSet.add("p2c");
        hashSet.add("iv");
        hashSet.add("authTag");
        REGISTERED_PARAMETER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public JWEHeader(Algorithm algorithm, EncryptionMethod encryptionMethod, JOSEObjectType jOSEObjectType, String str, Set<String> set, URI uri, JWK jwk, URI uri2, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, String str2, ECKey eCKey, CompressionAlgorithm compressionAlgorithm, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, int i, Base64URL base64URL6, Base64URL base64URL7, Map<String, Object> map, Base64URL base64URL8) {
        super(algorithm, jOSEObjectType, str, set, uri, jwk, uri2, base64URL, base64URL2, list, str2, map, base64URL8);
        if (algorithm.getName().equals(Algorithm.NONE.getName())) {
            throw new IllegalArgumentException("The JWE algorithm cannot be \"none\"");
        }
        if (encryptionMethod == null) {
            throw new IllegalArgumentException("The encryption method \"enc\" parameter must not be null");
        }
        this.enc = encryptionMethod;
        this.epk = eCKey;
        this.zip = compressionAlgorithm;
        this.apu = base64URL3;
        this.apv = base64URL4;
        this.p2s = base64URL5;
        this.p2c = i;
        this.f78iv = base64URL6;
        this.tag = base64URL7;
    }

    public JWEHeader(JWEAlgorithm jWEAlgorithm, EncryptionMethod encryptionMethod) {
        this(jWEAlgorithm, encryptionMethod, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null);
    }

    public JWEHeader(JWEHeader jWEHeader) {
        this(jWEHeader.getAlgorithm(), jWEHeader.getEncryptionMethod(), jWEHeader.getType(), jWEHeader.getContentType(), jWEHeader.getCriticalParams(), jWEHeader.getJWKURL(), jWEHeader.getJWK(), jWEHeader.getX509CertURL(), jWEHeader.getX509CertThumbprint(), jWEHeader.getX509CertSHA256Thumbprint(), jWEHeader.getX509CertChain(), jWEHeader.getKeyID(), jWEHeader.getEphemeralPublicKey(), jWEHeader.getCompressionAlgorithm(), jWEHeader.getAgreementPartyUInfo(), jWEHeader.getAgreementPartyVInfo(), jWEHeader.getPBES2Salt(), jWEHeader.getPBES2Count(), jWEHeader.getIV(), jWEHeader.getAuthTag(), jWEHeader.getCustomParams(), jWEHeader.getParsedBase64URL());
    }

    public static Set<String> getRegisteredParameterNames() {
        return REGISTERED_PARAMETER_NAMES;
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static JWEHeader m3637parse(Base64URL base64URL) throws ParseException {
        return m3639parse(base64URL.decodeToString(), base64URL);
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static JWEHeader m3638parse(String str) throws ParseException {
        return m3641parse(JSONObjectUtils.parse(str), (Base64URL) null);
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static JWEHeader m3639parse(String str, Base64URL base64URL) throws ParseException {
        return m3641parse(JSONObjectUtils.parse(str), base64URL);
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static JWEHeader m3640parse(JSONObject jSONObject) throws ParseException {
        return m3641parse(jSONObject, (Base64URL) null);
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static JWEHeader m3641parse(JSONObject jSONObject, Base64URL base64URL) throws ParseException {
        Algorithm parseAlgorithm = Header.parseAlgorithm(jSONObject);
        if (!(parseAlgorithm instanceof JWEAlgorithm)) {
            throw new ParseException("The algorithm \"alg\" header parameter must be for encryption", 0);
        }
        Builder parsedBase64URL = new Builder((JWEAlgorithm) parseAlgorithm, parseEncryptionMethod(jSONObject)).parsedBase64URL(base64URL);
        for (String str : jSONObject.keySet()) {
            if (!"alg".equals(str) && !"enc".equals(str)) {
                parsedBase64URL = "typ".equals(str) ? parsedBase64URL.type(new JOSEObjectType(JSONObjectUtils.getString(jSONObject, str))) : "cty".equals(str) ? parsedBase64URL.contentType(JSONObjectUtils.getString(jSONObject, str)) : "crit".equals(str) ? parsedBase64URL.criticalParams(new HashSet(JSONObjectUtils.getStringList(jSONObject, str))) : "jku".equals(str) ? parsedBase64URL.jwkURL(JSONObjectUtils.getURI(jSONObject, str)) : "jwk".equals(str) ? parsedBase64URL.jwk(JWK.parse(JSONObjectUtils.getJSONObject(jSONObject, str))) : "x5u".equals(str) ? parsedBase64URL.x509CertURL(JSONObjectUtils.getURI(jSONObject, str)) : "x5t".equals(str) ? parsedBase64URL.x509CertThumbprint(new Base64URL(JSONObjectUtils.getString(jSONObject, str))) : "x5t#S256".equals(str) ? parsedBase64URL.x509CertSHA256Thumbprint(new Base64URL(JSONObjectUtils.getString(jSONObject, str))) : "x5c".equals(str) ? parsedBase64URL.x509CertChain(X509CertChainUtils.parseX509CertChain(JSONObjectUtils.getJSONArray(jSONObject, str))) : "kid".equals(str) ? parsedBase64URL.keyID(JSONObjectUtils.getString(jSONObject, str)) : "epk".equals(str) ? parsedBase64URL.ephemeralPublicKey(ECKey.parse(JSONObjectUtils.getJSONObject(jSONObject, str))) : "zip".equals(str) ? parsedBase64URL.compressionAlgorithm(new CompressionAlgorithm(JSONObjectUtils.getString(jSONObject, str))) : "apu".equals(str) ? parsedBase64URL.agreementPartyUInfo(new Base64URL(JSONObjectUtils.getString(jSONObject, str))) : "apv".equals(str) ? parsedBase64URL.agreementPartyVInfo(new Base64URL(JSONObjectUtils.getString(jSONObject, str))) : "p2s".equals(str) ? parsedBase64URL.pbes2Salt(new Base64URL(JSONObjectUtils.getString(jSONObject, str))) : "p2c".equals(str) ? parsedBase64URL.pbes2Count(JSONObjectUtils.getInt(jSONObject, str)) : "iv".equals(str) ? parsedBase64URL.m10iv(new Base64URL(JSONObjectUtils.getString(jSONObject, str))) : "tag".equals(str) ? parsedBase64URL.authTag(new Base64URL(JSONObjectUtils.getString(jSONObject, str))) : parsedBase64URL.customParam(str, jSONObject.get(str));
            }
        }
        return parsedBase64URL.build();
    }

    private static EncryptionMethod parseEncryptionMethod(JSONObject jSONObject) throws ParseException {
        return EncryptionMethod.parse(JSONObjectUtils.getString(jSONObject, "enc"));
    }

    public Base64URL getAgreementPartyUInfo() {
        return this.apu;
    }

    public Base64URL getAgreementPartyVInfo() {
        return this.apv;
    }

    @Override // com.nimbusds.jose.Header
    public JWEAlgorithm getAlgorithm() {
        return (JWEAlgorithm) super.getAlgorithm();
    }

    public Base64URL getAuthTag() {
        return this.tag;
    }

    public CompressionAlgorithm getCompressionAlgorithm() {
        return this.zip;
    }

    public EncryptionMethod getEncryptionMethod() {
        return this.enc;
    }

    public ECKey getEphemeralPublicKey() {
        return this.epk;
    }

    public Base64URL getIV() {
        return this.f78iv;
    }

    @Override // com.nimbusds.jose.CommonSEHeader, com.nimbusds.jose.Header
    public Set<String> getIncludedParams() {
        Set<String> includedParams = super.getIncludedParams();
        if (this.enc != null) {
            includedParams.add("enc");
        }
        if (this.epk != null) {
            includedParams.add("epk");
        }
        if (this.zip != null) {
            includedParams.add("zip");
        }
        if (this.apu != null) {
            includedParams.add("apu");
        }
        if (this.apv != null) {
            includedParams.add("apv");
        }
        if (this.p2s != null) {
            includedParams.add("p2s");
        }
        if (this.p2c > 0) {
            includedParams.add("p2c");
        }
        if (this.f78iv != null) {
            includedParams.add("iv");
        }
        if (this.tag != null) {
            includedParams.add("tag");
        }
        return includedParams;
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ JWK getJWK() {
        return super.getJWK();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ URI getJWKURL() {
        return super.getJWKURL();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ String getKeyID() {
        return super.getKeyID();
    }

    public int getPBES2Count() {
        return this.p2c;
    }

    public Base64URL getPBES2Salt() {
        return this.p2s;
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ List getX509CertChain() {
        return super.getX509CertChain();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ Base64URL getX509CertSHA256Thumbprint() {
        return super.getX509CertSHA256Thumbprint();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    @Deprecated
    public /* bridge */ /* synthetic */ Base64URL getX509CertThumbprint() {
        return super.getX509CertThumbprint();
    }

    @Override // com.nimbusds.jose.CommonSEHeader
    public /* bridge */ /* synthetic */ URI getX509CertURL() {
        return super.getX509CertURL();
    }

    @Override // com.nimbusds.jose.CommonSEHeader, com.nimbusds.jose.Header
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        EncryptionMethod encryptionMethod = this.enc;
        if (encryptionMethod != null) {
            jSONObject.put("enc", encryptionMethod.toString());
        }
        ECKey eCKey = this.epk;
        if (eCKey != null) {
            jSONObject.put("epk", eCKey.toJSONObject());
        }
        CompressionAlgorithm compressionAlgorithm = this.zip;
        if (compressionAlgorithm != null) {
            jSONObject.put("zip", compressionAlgorithm.toString());
        }
        Base64URL base64URL = this.apu;
        if (base64URL != null) {
            jSONObject.put("apu", base64URL.toString());
        }
        Base64URL base64URL2 = this.apv;
        if (base64URL2 != null) {
            jSONObject.put("apv", base64URL2.toString());
        }
        Base64URL base64URL3 = this.p2s;
        if (base64URL3 != null) {
            jSONObject.put("p2s", base64URL3.toString());
        }
        int i = this.p2c;
        if (i > 0) {
            jSONObject.put("p2c", Integer.valueOf(i));
        }
        Base64URL base64URL4 = this.f78iv;
        if (base64URL4 != null) {
            jSONObject.put("iv", base64URL4.toString());
        }
        Base64URL base64URL5 = this.tag;
        if (base64URL5 != null) {
            jSONObject.put("tag", base64URL5.toString());
        }
        return jSONObject;
    }
}
