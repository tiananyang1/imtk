package com.nimbusds.jose;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/CommonSEHeader.class */
public abstract class CommonSEHeader extends Header {
    private static final long serialVersionUID = 1;
    private final URI jku;
    private final JWK jwk;
    private final String kid;
    private final List<Base64> x5c;
    private final Base64URL x5t;
    private final Base64URL x5t256;
    private final URI x5u;

    /* JADX INFO: Access modifiers changed from: protected */
    public CommonSEHeader(Algorithm algorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, URI uri, JWK jwk, URI uri2, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, String str2, Map<String, Object> map, Base64URL base64URL3) {
        super(algorithm, jOSEObjectType, str, set, map, base64URL3);
        this.jku = uri;
        this.jwk = jwk;
        this.x5u = uri2;
        this.x5t = base64URL;
        this.x5t256 = base64URL2;
        if (list != null) {
            this.x5c = Collections.unmodifiableList(new ArrayList(list));
        } else {
            this.x5c = null;
        }
        this.kid = str2;
    }

    @Override // com.nimbusds.jose.Header
    public Set<String> getIncludedParams() {
        Set<String> includedParams = super.getIncludedParams();
        if (this.jku != null) {
            includedParams.add("jku");
        }
        if (this.jwk != null) {
            includedParams.add("jwk");
        }
        if (this.x5u != null) {
            includedParams.add("x5u");
        }
        if (this.x5t != null) {
            includedParams.add("x5t");
        }
        if (this.x5t256 != null) {
            includedParams.add("x5t#S256");
        }
        List<Base64> list = this.x5c;
        if (list != null && !list.isEmpty()) {
            includedParams.add("x5c");
        }
        if (this.kid != null) {
            includedParams.add("kid");
        }
        return includedParams;
    }

    public JWK getJWK() {
        return this.jwk;
    }

    public URI getJWKURL() {
        return this.jku;
    }

    public String getKeyID() {
        return this.kid;
    }

    public List<Base64> getX509CertChain() {
        return this.x5c;
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

    @Override // com.nimbusds.jose.Header
    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        URI uri = this.jku;
        if (uri != null) {
            jSONObject.put("jku", uri.toString());
        }
        JWK jwk = this.jwk;
        if (jwk != null) {
            jSONObject.put("jwk", jwk.toJSONObject());
        }
        URI uri2 = this.x5u;
        if (uri2 != null) {
            jSONObject.put("x5u", uri2.toString());
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
        if (list != null && !list.isEmpty()) {
            jSONObject.put("x5c", this.x5c);
        }
        String str = this.kid;
        if (str != null) {
            jSONObject.put("kid", str);
        }
        return jSONObject;
    }
}
