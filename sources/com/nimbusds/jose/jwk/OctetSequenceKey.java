package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.IntegerOverflowException;
import com.nimbusds.jose.util.JSONObjectUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.net.URI;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/OctetSequenceKey.class */
public final class OctetSequenceKey extends JWK implements SecretJWK {
    private static final long serialVersionUID = 1;

    /* renamed from: k */
    private final Base64URL f97k;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/OctetSequenceKey$Builder.class */
    public static class Builder {
        private Algorithm alg;

        /* renamed from: k */
        private final Base64URL f98k;
        private String kid;

        /* renamed from: ks */
        private KeyStore f99ks;
        private Set<KeyOperation> ops;
        private KeyUse use;
        private List<Base64> x5c;

        @Deprecated
        private Base64URL x5t;
        private Base64URL x5t256;
        private URI x5u;

        public Builder(Base64URL base64URL) {
            if (base64URL == null) {
                throw new IllegalArgumentException("The key value must not be null");
            }
            this.f98k = base64URL;
        }

        public Builder(SecretKey secretKey) {
            this(secretKey.getEncoded());
        }

        public Builder(byte[] bArr) {
            this(Base64URL.m3704encode(bArr));
            if (bArr.length == 0) {
                throw new IllegalArgumentException("The key must have a positive length");
            }
        }

        public Builder algorithm(Algorithm algorithm) {
            this.alg = algorithm;
            return this;
        }

        public OctetSequenceKey build() {
            try {
                return new OctetSequenceKey(this.f98k, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.f99ks);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
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
            linkedHashMap.put("k", this.f98k.toString());
            linkedHashMap.put("kty", KeyType.OCT.getValue());
            this.kid = ThumbprintUtils.compute(str, (LinkedHashMap<String, ?>) linkedHashMap).toString();
            return this;
        }

        public Builder keyOperations(Set<KeyOperation> set) {
            this.ops = set;
            return this;
        }

        public Builder keyStore(KeyStore keyStore) {
            this.f99ks = keyStore;
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

    public OctetSequenceKey(Base64URL base64URL, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL2, Base64URL base64URL3, List<Base64> list, KeyStore keyStore) {
        super(KeyType.OCT, keyUse, set, algorithm, str, uri, base64URL2, base64URL3, list, keyStore);
        if (base64URL == null) {
            throw new IllegalArgumentException("The key value must not be null");
        }
        this.f97k = base64URL;
    }

    /* renamed from: load, reason: collision with other method in class */
    public static OctetSequenceKey m3692load(KeyStore keyStore, String str, char[] cArr) throws KeyStoreException, JOSEException {
        try {
            Key key = keyStore.getKey(str, cArr);
            if (key instanceof SecretKey) {
                return new Builder((SecretKey) key).keyID(str).keyStore(keyStore).build();
            }
            return null;
        } catch (NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new JOSEException("Couldn't retrieve secret key (bad pin?): " + e.getMessage(), e);
        }
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static OctetSequenceKey m3693parse(String str) throws ParseException {
        return m3694parse(JSONObjectUtils.parse(str));
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static OctetSequenceKey m3694parse(JSONObject jSONObject) throws ParseException {
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject, "k"));
        if (JWKMetadata.parseKeyType(jSONObject) == KeyType.OCT) {
            return new OctetSequenceKey(base64URL, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), null);
        }
        throw new ParseException("The key type \"kty\" must be oct", 0);
    }

    public Base64URL getKeyValue() {
        return this.f97k;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("k", this.f97k.toString());
        linkedHashMap.put("kty", getKeyType().toString());
        return linkedHashMap;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public boolean isPrivate() {
        return true;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public int size() {
        try {
            return ByteUtils.safeBitLength(this.f97k.decode());
        } catch (IntegerOverflowException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }

    public byte[] toByteArray() {
        return getKeyValue().decode();
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("k", this.f97k.toString());
        return jSONObject;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public OctetSequenceKey toPublicJWK() {
        return null;
    }

    @Override // com.nimbusds.jose.jwk.SecretJWK
    public SecretKey toSecretKey() {
        return toSecretKey("NONE");
    }

    public SecretKey toSecretKey(String str) {
        return new SecretKeySpec(toByteArray(), str);
    }
}
