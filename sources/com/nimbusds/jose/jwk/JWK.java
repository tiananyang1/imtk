package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.Serializable;
import java.net.URI;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/JWK.class */
public abstract class JWK implements JSONAware, Serializable {
    public static final String MIME_TYPE = "application/jwk+json; charset=UTF-8";
    private static final long serialVersionUID = 1;
    private final Algorithm alg;
    private final KeyStore keyStore;
    private final String kid;
    private final KeyType kty;
    private final Set<KeyOperation> ops;
    private final KeyUse use;
    private final List<Base64> x5c;

    @Deprecated
    private final Base64URL x5t;
    private Base64URL x5t256;
    private final URI x5u;

    /* JADX INFO: Access modifiers changed from: protected */
    public JWK(KeyType keyType, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        if (keyType == null) {
            throw new IllegalArgumentException("The key type \"kty\" parameter must not be null");
        }
        this.kty = keyType;
        if (!KeyUseAndOpsConsistency.areConsistent(keyUse, set)) {
            throw new IllegalArgumentException("The key use \"use\" and key options \"key_opts\" parameters are not consistent, see RFC 7517, section 4.3");
        }
        this.use = keyUse;
        this.ops = set;
        this.alg = algorithm;
        this.kid = str;
        this.x5u = uri;
        this.x5t = base64URL;
        this.x5t256 = base64URL2;
        this.x5c = list;
        this.keyStore = keyStore;
    }

    public static JWK load(KeyStore keyStore, String str, char[] cArr) throws KeyStoreException, JOSEException {
        Certificate certificate = keyStore.getCertificate(str);
        if (certificate == null) {
            return OctetSequenceKey.m3692load(keyStore, str, cArr);
        }
        if (certificate.getPublicKey() instanceof RSAPublicKey) {
            return RSAKey.m3695load(keyStore, str, cArr);
        }
        if (certificate.getPublicKey() instanceof ECPublicKey) {
            return ECKey.load(keyStore, str, cArr);
        }
        throw new JOSEException("Unsupported public key algorithm: " + certificate.getPublicKey().getAlgorithm());
    }

    public static JWK parse(String str) throws ParseException {
        return parse(JSONObjectUtils.parse(str));
    }

    public static JWK parse(X509Certificate x509Certificate) throws JOSEException {
        if (x509Certificate.getPublicKey() instanceof RSAPublicKey) {
            return RSAKey.m3697parse(x509Certificate);
        }
        if (x509Certificate.getPublicKey() instanceof ECPublicKey) {
            return ECKey.parse(x509Certificate);
        }
        throw new JOSEException("Unsupported public key algorithm: " + x509Certificate.getPublicKey().getAlgorithm());
    }

    public static JWK parse(JSONObject jSONObject) throws ParseException {
        KeyType parse = KeyType.parse(JSONObjectUtils.getString(jSONObject, "kty"));
        if (parse == KeyType.f91EC) {
            return ECKey.parse(jSONObject);
        }
        if (parse == KeyType.RSA) {
            return RSAKey.m3698parse(jSONObject);
        }
        if (parse == KeyType.OCT) {
            return OctetSequenceKey.m3694parse(jSONObject);
        }
        if (parse == KeyType.OKP) {
            return OctetKeyPair.m3691parse(jSONObject);
        }
        throw new ParseException("Unsupported key type \"kty\" parameter: " + parse, 0);
    }

    public Base64URL computeThumbprint() throws JOSEException {
        return computeThumbprint(CommonUtils.SHA256_INSTANCE);
    }

    public Base64URL computeThumbprint(String str) throws JOSEException {
        return ThumbprintUtils.compute(str, this);
    }

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public String getKeyID() {
        return this.kid;
    }

    public Set<KeyOperation> getKeyOperations() {
        return this.ops;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public KeyType getKeyType() {
        return this.kty;
    }

    public KeyUse getKeyUse() {
        return this.use;
    }

    public abstract LinkedHashMap<String, ?> getRequiredParams();

    public List<Base64> getX509CertChain() {
        List<Base64> list = this.x5c;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public Base64URL getX509CertSHA256Thumbprint() {
        return this.x5t256;
    }

    @Deprecated
    public Base64URL getX509CertThumbprint() {
        return this.x5t;
    }

    public URI getX509CertURL() {
        return this.x5u;
    }

    public abstract boolean isPrivate();

    public abstract int size();

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("kty", this.kty.getValue());
        KeyUse keyUse = this.use;
        if (keyUse != null) {
            jSONObject.put("use", keyUse.identifier());
        }
        Set<KeyOperation> set = this.ops;
        if (set != null) {
            ArrayList arrayList = new ArrayList(set.size());
            Iterator<KeyOperation> it = this.ops.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().identifier());
            }
            jSONObject.put("key_ops", arrayList);
        }
        Algorithm algorithm = this.alg;
        if (algorithm != null) {
            jSONObject.put("alg", algorithm.getName());
        }
        String str = this.kid;
        if (str != null) {
            jSONObject.put("kid", str);
        }
        URI uri = this.x5u;
        if (uri != null) {
            jSONObject.put("x5u", uri.toString());
        }
        Base64URL base64URL = this.x5t;
        if (base64URL != null) {
            jSONObject.put("x5t", base64URL.toString());
        }
        Base64URL base64URL2 = this.x5t256;
        if (base64URL2 != null) {
            jSONObject.put("x5t#S256", base64URL2.toString());
        }
        List<Base64> list = this.x5c;
        if (list != null) {
            jSONObject.put("x5c", list);
        }
        return jSONObject;
    }

    public String toJSONString() {
        return toJSONObject().toString();
    }

    public abstract JWK toPublicJWK();

    public String toString() {
        return toJSONObject().toString();
    }
}
