package com.nimbusds.jwt;

import com.nimbusds.jose.util.DateUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jwt/JWTClaimsSet.class */
public final class JWTClaimsSet implements Serializable {
    private static final String AUDIENCE_CLAIM = "aud";
    private static final String EXPIRATION_TIME_CLAIM = "exp";
    private static final String ISSUED_AT_CLAIM = "iat";
    private static final String ISSUER_CLAIM = "iss";
    private static final String JWT_ID_CLAIM = "jti";
    private static final String NOT_BEFORE_CLAIM = "nbf";
    private static final Set<String> REGISTERED_CLAIM_NAMES;
    private static final String SUBJECT_CLAIM = "sub";
    private static final long serialVersionUID = 1;
    private final Map<String, Object> claims;

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jwt/JWTClaimsSet$Builder.class */
    public static class Builder {
        private final Map<String, Object> claims = new LinkedHashMap();

        public Builder() {
        }

        public Builder(JWTClaimsSet jWTClaimsSet) {
            this.claims.putAll(jWTClaimsSet.claims);
        }

        public Builder audience(String str) {
            if (str == null) {
                this.claims.put(JWTClaimsSet.AUDIENCE_CLAIM, null);
                return this;
            }
            this.claims.put(JWTClaimsSet.AUDIENCE_CLAIM, Collections.singletonList(str));
            return this;
        }

        public Builder audience(List<String> list) {
            this.claims.put(JWTClaimsSet.AUDIENCE_CLAIM, list);
            return this;
        }

        public JWTClaimsSet build() {
            return new JWTClaimsSet(this.claims, null);
        }

        public Builder claim(String str, Object obj) {
            this.claims.put(str, obj);
            return this;
        }

        public Builder expirationTime(Date date) {
            this.claims.put(JWTClaimsSet.EXPIRATION_TIME_CLAIM, date);
            return this;
        }

        public Builder issueTime(Date date) {
            this.claims.put(JWTClaimsSet.ISSUED_AT_CLAIM, date);
            return this;
        }

        public Builder issuer(String str) {
            this.claims.put(JWTClaimsSet.ISSUER_CLAIM, str);
            return this;
        }

        public Builder jwtID(String str) {
            this.claims.put(JWTClaimsSet.JWT_ID_CLAIM, str);
            return this;
        }

        public Builder notBeforeTime(Date date) {
            this.claims.put(JWTClaimsSet.NOT_BEFORE_CLAIM, date);
            return this;
        }

        public Builder subject(String str) {
            this.claims.put(JWTClaimsSet.SUBJECT_CLAIM, str);
            return this;
        }
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(ISSUER_CLAIM);
        hashSet.add(SUBJECT_CLAIM);
        hashSet.add(AUDIENCE_CLAIM);
        hashSet.add(EXPIRATION_TIME_CLAIM);
        hashSet.add(NOT_BEFORE_CLAIM);
        hashSet.add(ISSUED_AT_CLAIM);
        hashSet.add(JWT_ID_CLAIM);
        REGISTERED_CLAIM_NAMES = Collections.unmodifiableSet(hashSet);
    }

    private JWTClaimsSet(Map<String, Object> map) {
        this.claims = new LinkedHashMap();
        this.claims.putAll(map);
    }

    /* synthetic */ JWTClaimsSet(Map map, JWTClaimsSet jWTClaimsSet) {
        this(map);
    }

    public static Set<String> getRegisteredNames() {
        return REGISTERED_CLAIM_NAMES;
    }

    public static JWTClaimsSet parse(String str) throws ParseException {
        return parse(JSONObjectUtils.parse(str));
    }

    public static JWTClaimsSet parse(JSONObject jSONObject) throws ParseException {
        Builder builder = new Builder();
        for (String str : jSONObject.keySet()) {
            if (str.equals(ISSUER_CLAIM)) {
                builder.issuer(JSONObjectUtils.getString(jSONObject, ISSUER_CLAIM));
            } else if (str.equals(SUBJECT_CLAIM)) {
                builder.subject(JSONObjectUtils.getString(jSONObject, SUBJECT_CLAIM));
            } else if (str.equals(AUDIENCE_CLAIM)) {
                Object obj = jSONObject.get(AUDIENCE_CLAIM);
                if (obj instanceof String) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(JSONObjectUtils.getString(jSONObject, AUDIENCE_CLAIM));
                    builder.audience(arrayList);
                } else if (obj instanceof List) {
                    builder.audience(JSONObjectUtils.getStringList(jSONObject, AUDIENCE_CLAIM));
                }
            } else if (str.equals(EXPIRATION_TIME_CLAIM)) {
                builder.expirationTime(new Date(JSONObjectUtils.getLong(jSONObject, EXPIRATION_TIME_CLAIM) * 1000));
            } else if (str.equals(NOT_BEFORE_CLAIM)) {
                builder.notBeforeTime(new Date(JSONObjectUtils.getLong(jSONObject, NOT_BEFORE_CLAIM) * 1000));
            } else if (str.equals(ISSUED_AT_CLAIM)) {
                builder.issueTime(new Date(JSONObjectUtils.getLong(jSONObject, ISSUED_AT_CLAIM) * 1000));
            } else if (str.equals(JWT_ID_CLAIM)) {
                builder.jwtID(JSONObjectUtils.getString(jSONObject, JWT_ID_CLAIM));
            } else {
                builder.claim(str, jSONObject.get(str));
            }
        }
        return builder.build();
    }

    public List<String> getAudience() {
        Object claim = getClaim(AUDIENCE_CLAIM);
        if (claim instanceof String) {
            return Collections.singletonList((String) claim);
        }
        try {
            List<String> stringListClaim = getStringListClaim(AUDIENCE_CLAIM);
            return stringListClaim != null ? Collections.unmodifiableList(stringListClaim) : Collections.emptyList();
        } catch (ParseException e) {
            return Collections.emptyList();
        }
    }

    public Boolean getBooleanClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null || (claim instanceof Boolean)) {
            return (Boolean) claim;
        }
        throw new ParseException("The \"" + str + "\" claim is not a Boolean", 0);
    }

    public Object getClaim(String str) {
        return this.claims.get(str);
    }

    public Map<String, Object> getClaims() {
        return Collections.unmodifiableMap(this.claims);
    }

    public Date getDateClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Date) {
            return (Date) claim;
        }
        if (claim instanceof Number) {
            return DateUtils.fromSecondsSinceEpoch(((Number) claim).longValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not a Date", 0);
    }

    public Double getDoubleClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Double.valueOf(((Number) claim).doubleValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not a Double", 0);
    }

    public Date getExpirationTime() {
        try {
            return getDateClaim(EXPIRATION_TIME_CLAIM);
        } catch (ParseException e) {
            return null;
        }
    }

    public Float getFloatClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Float.valueOf(((Number) claim).floatValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not a Float", 0);
    }

    public Integer getIntegerClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Integer.valueOf(((Number) claim).intValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not an Integer", 0);
    }

    public Date getIssueTime() {
        try {
            return getDateClaim(ISSUED_AT_CLAIM);
        } catch (ParseException e) {
            return null;
        }
    }

    public String getIssuer() {
        try {
            return getStringClaim(ISSUER_CLAIM);
        } catch (ParseException e) {
            return null;
        }
    }

    public JSONObject getJSONObjectClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof JSONObject) {
            return (JSONObject) claim;
        }
        if (!(claim instanceof Map)) {
            throw new ParseException("The \"" + str + "\" claim is not a JSON object or Map", 0);
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : ((Map) claim).entrySet()) {
            if (entry.getKey() instanceof String) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    public String getJWTID() {
        try {
            return getStringClaim(JWT_ID_CLAIM);
        } catch (ParseException e) {
            return null;
        }
    }

    public Long getLongClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null) {
            return null;
        }
        if (claim instanceof Number) {
            return Long.valueOf(((Number) claim).longValue());
        }
        throw new ParseException("The \"" + str + "\" claim is not a Number", 0);
    }

    public Date getNotBeforeTime() {
        try {
            return getDateClaim(NOT_BEFORE_CLAIM);
        } catch (ParseException e) {
            return null;
        }
    }

    public String[] getStringArrayClaim(String str) throws ParseException {
        if (getClaim(str) == null) {
            return null;
        }
        try {
            List list = (List) getClaim(str);
            String[] strArr = new String[list.size()];
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= strArr.length) {
                    return strArr;
                }
                try {
                    strArr[i2] = (String) list.get(i2);
                    i = i2 + 1;
                } catch (ClassCastException e) {
                    throw new ParseException("The \"" + str + "\" claim is not a list / JSON array of strings", 0);
                }
            }
        } catch (ClassCastException e2) {
            throw new ParseException("The \"" + str + "\" claim is not a list / JSON array", 0);
        }
    }

    public String getStringClaim(String str) throws ParseException {
        Object claim = getClaim(str);
        if (claim == null || (claim instanceof String)) {
            return (String) claim;
        }
        throw new ParseException("The \"" + str + "\" claim is not a String", 0);
    }

    public List<String> getStringListClaim(String str) throws ParseException {
        String[] stringArrayClaim = getStringArrayClaim(str);
        if (stringArrayClaim == null) {
            return null;
        }
        return Collections.unmodifiableList(Arrays.asList(stringArrayClaim));
    }

    public String getSubject() {
        try {
            return getStringClaim(SUBJECT_CLAIM);
        } catch (ParseException e) {
            return null;
        }
    }

    public URI getURIClaim(String str) throws ParseException {
        String stringClaim = getStringClaim(str);
        if (stringClaim == null) {
            return null;
        }
        try {
            return new URI(stringClaim);
        } catch (URISyntaxException e) {
            throw new ParseException("The \"" + str + "\" claim is not a URI: " + e.getMessage(), 0);
        }
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Object> entry : this.claims.entrySet()) {
            if (entry.getValue() instanceof Date) {
                jSONObject.put(entry.getKey(), Long.valueOf(DateUtils.toSecondsSinceEpoch((Date) entry.getValue())));
            } else if (AUDIENCE_CLAIM.equals(entry.getKey())) {
                List<String> audience = getAudience();
                if (audience != null && !audience.isEmpty()) {
                    if (audience.size() == 1) {
                        jSONObject.put(AUDIENCE_CLAIM, audience.get(0));
                    } else {
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.addAll(audience);
                        jSONObject.put(AUDIENCE_CLAIM, jSONArray);
                    }
                }
            } else if (entry.getValue() != null) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toJSONString();
    }

    public <T> T toType(JWTClaimsSetTransformer<T> jWTClaimsSetTransformer) {
        return jWTClaimsSetTransformer.transform(this);
    }
}
