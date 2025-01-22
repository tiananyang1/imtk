package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/Header.class */
public abstract class Header implements Serializable {
    private static final Map<String, Object> EMPTY_CUSTOM_PARAMS = Collections.unmodifiableMap(new HashMap());
    private static final long serialVersionUID = 1;
    private final Algorithm alg;
    private final Set<String> crit;
    private final String cty;
    private final Map<String, Object> customParams;
    private final Base64URL parsedBase64URL;
    private final JOSEObjectType typ;

    /* JADX INFO: Access modifiers changed from: protected */
    public Header(Algorithm algorithm, JOSEObjectType jOSEObjectType, String str, Set<String> set, Map<String, Object> map, Base64URL base64URL) {
        if (algorithm == null) {
            throw new IllegalArgumentException("The algorithm \"alg\" header parameter must not be null");
        }
        this.alg = algorithm;
        this.typ = jOSEObjectType;
        this.cty = str;
        if (set != null) {
            this.crit = Collections.unmodifiableSet(new HashSet(set));
        } else {
            this.crit = null;
        }
        if (map != null) {
            this.customParams = Collections.unmodifiableMap(new HashMap(map));
        } else {
            this.customParams = EMPTY_CUSTOM_PARAMS;
        }
        this.parsedBase64URL = base64URL;
    }

    protected Header(Header header) {
        this(header.getAlgorithm(), header.getType(), header.getContentType(), header.getCriticalParams(), header.getCustomParams(), header.getParsedBase64URL());
    }

    public static Header parse(Base64URL base64URL) throws ParseException {
        return parse(base64URL.decodeToString(), base64URL);
    }

    public static Header parse(String str) throws ParseException {
        return parse(str, (Base64URL) null);
    }

    public static Header parse(String str, Base64URL base64URL) throws ParseException {
        return parse(JSONObjectUtils.parse(str), base64URL);
    }

    public static Header parse(JSONObject jSONObject) throws ParseException {
        return parse(jSONObject, (Base64URL) null);
    }

    public static Header parse(JSONObject jSONObject, Base64URL base64URL) throws ParseException {
        Algorithm parseAlgorithm = parseAlgorithm(jSONObject);
        if (parseAlgorithm.equals(Algorithm.NONE)) {
            return PlainHeader.m3660parse(jSONObject, base64URL);
        }
        if (parseAlgorithm instanceof JWSAlgorithm) {
            return JWSHeader.m3651parse(jSONObject, base64URL);
        }
        if (parseAlgorithm instanceof JWEAlgorithm) {
            return JWEHeader.m3641parse(jSONObject, base64URL);
        }
        throw new AssertionError("Unexpected algorithm type: " + parseAlgorithm);
    }

    public static Algorithm parseAlgorithm(JSONObject jSONObject) throws ParseException {
        String string = JSONObjectUtils.getString(jSONObject, "alg");
        return string.equals(Algorithm.NONE.getName()) ? Algorithm.NONE : jSONObject.containsKey("enc") ? JWEAlgorithm.parse(string) : JWSAlgorithm.parse(string);
    }

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public String getContentType() {
        return this.cty;
    }

    public Set<String> getCriticalParams() {
        return this.crit;
    }

    public Object getCustomParam(String str) {
        return this.customParams.get(str);
    }

    public Map<String, Object> getCustomParams() {
        return this.customParams;
    }

    public Set<String> getIncludedParams() {
        HashSet hashSet = new HashSet(getCustomParams().keySet());
        hashSet.add("alg");
        if (getType() != null) {
            hashSet.add("typ");
        }
        if (getContentType() != null) {
            hashSet.add("cty");
        }
        if (getCriticalParams() != null && !getCriticalParams().isEmpty()) {
            hashSet.add("crit");
        }
        return hashSet;
    }

    public Base64URL getParsedBase64URL() {
        return this.parsedBase64URL;
    }

    public JOSEObjectType getType() {
        return this.typ;
    }

    public Base64URL toBase64URL() {
        Base64URL base64URL = this.parsedBase64URL;
        Base64URL base64URL2 = base64URL;
        if (base64URL == null) {
            base64URL2 = Base64URL.m3702encode(toString());
        }
        return base64URL2;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject(this.customParams);
        jSONObject.put("alg", this.alg.toString());
        JOSEObjectType jOSEObjectType = this.typ;
        if (jOSEObjectType != null) {
            jSONObject.put("typ", jOSEObjectType.toString());
        }
        String str = this.cty;
        if (str != null) {
            jSONObject.put("cty", str);
        }
        Set<String> set = this.crit;
        if (set != null && !set.isEmpty()) {
            jSONObject.put("crit", new ArrayList(this.crit));
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
