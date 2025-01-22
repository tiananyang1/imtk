package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/PlainHeader.class */
public final class PlainHeader extends Header {
    private static final Set<String> REGISTERED_PARAMETER_NAMES;
    private static final long serialVersionUID = 1;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/PlainHeader$Builder.class */
    public static class Builder {
        private Set<String> crit;
        private String cty;
        private Map<String, Object> customParams;
        private Base64URL parsedBase64URL;
        private JOSEObjectType typ;

        public Builder() {
        }

        public Builder(PlainHeader plainHeader) {
            this.typ = plainHeader.getType();
            this.cty = plainHeader.getContentType();
            this.crit = plainHeader.getCriticalParams();
            this.customParams = plainHeader.getCustomParams();
        }

        public PlainHeader build() {
            return new PlainHeader(this.typ, this.cty, this.crit, this.customParams, this.parsedBase64URL);
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
            if (PlainHeader.getRegisteredParameterNames().contains(str)) {
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

        public Builder parsedBase64URL(Base64URL base64URL) {
            this.parsedBase64URL = base64URL;
            return this;
        }

        public Builder type(JOSEObjectType jOSEObjectType) {
            this.typ = jOSEObjectType;
            return this;
        }
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("alg");
        hashSet.add("typ");
        hashSet.add("cty");
        hashSet.add("crit");
        REGISTERED_PARAMETER_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public PlainHeader() {
        this(null, null, null, null, null);
    }

    public PlainHeader(JOSEObjectType jOSEObjectType, String str, Set<String> set, Map<String, Object> map, Base64URL base64URL) {
        super(Algorithm.NONE, jOSEObjectType, str, set, map, base64URL);
    }

    public PlainHeader(PlainHeader plainHeader) {
        this(plainHeader.getType(), plainHeader.getContentType(), plainHeader.getCriticalParams(), plainHeader.getCustomParams(), plainHeader.getParsedBase64URL());
    }

    public static Set<String> getRegisteredParameterNames() {
        return REGISTERED_PARAMETER_NAMES;
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static PlainHeader m3656parse(Base64URL base64URL) throws ParseException {
        return m3658parse(base64URL.decodeToString(), base64URL);
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static PlainHeader m3657parse(String str) throws ParseException {
        return m3658parse(str, (Base64URL) null);
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static PlainHeader m3658parse(String str, Base64URL base64URL) throws ParseException {
        return m3660parse(JSONObjectUtils.parse(str), base64URL);
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static PlainHeader m3659parse(JSONObject jSONObject) throws ParseException {
        return m3660parse(jSONObject, (Base64URL) null);
    }

    /* renamed from: parse, reason: collision with other method in class */
    public static PlainHeader m3660parse(JSONObject jSONObject, Base64URL base64URL) throws ParseException {
        if (Header.parseAlgorithm(jSONObject) != Algorithm.NONE) {
            throw new ParseException("The algorithm \"alg\" header parameter must be \"none\"", 0);
        }
        Builder parsedBase64URL = new Builder().parsedBase64URL(base64URL);
        for (String str : jSONObject.keySet()) {
            if (!"alg".equals(str)) {
                parsedBase64URL = "typ".equals(str) ? parsedBase64URL.type(new JOSEObjectType(JSONObjectUtils.getString(jSONObject, str))) : "cty".equals(str) ? parsedBase64URL.contentType(JSONObjectUtils.getString(jSONObject, str)) : "crit".equals(str) ? parsedBase64URL.criticalParams(new HashSet(JSONObjectUtils.getStringList(jSONObject, str))) : parsedBase64URL.customParam(str, jSONObject.get(str));
            }
        }
        return parsedBase64URL.build();
    }

    @Override // com.nimbusds.jose.Header
    public Algorithm getAlgorithm() {
        return Algorithm.NONE;
    }
}
