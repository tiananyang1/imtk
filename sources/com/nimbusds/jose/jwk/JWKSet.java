package com.nimbusds.jose.jwk;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.IOUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/JWKSet.class */
public class JWKSet {
    public static final String MIME_TYPE = "application/jwk-set+json; charset=UTF-8";
    private final List<JWK> keys = new LinkedList();
    private final Map<String, Object> customMembers = new HashMap();

    public JWKSet() {
    }

    public JWKSet(JWK jwk) {
        if (jwk == null) {
            throw new IllegalArgumentException("The JWK must not be null");
        }
        this.keys.add(jwk);
    }

    public JWKSet(List<JWK> list) {
        if (list == null) {
            throw new IllegalArgumentException("The JWK list must not be null");
        }
        this.keys.addAll(list);
    }

    public JWKSet(List<JWK> list, Map<String, Object> map) {
        if (list == null) {
            throw new IllegalArgumentException("The JWK list must not be null");
        }
        this.keys.addAll(list);
        this.customMembers.putAll(map);
    }

    public static JWKSet load(File file) throws IOException, ParseException {
        return parse(IOUtils.readFileToString(file, Charset.forName("UTF-8")));
    }

    public static JWKSet load(URL url) throws IOException, ParseException {
        return load(url, 0, 0, 0);
    }

    public static JWKSet load(URL url, int i, int i2, int i3) throws IOException, ParseException {
        return parse(new DefaultResourceRetriever(i, i2, i3).retrieveResource(url).getContent());
    }

    public static JWKSet load(KeyStore keyStore, PasswordLookup passwordLookup) throws KeyStoreException {
        ECKey load;
        LinkedList linkedList = new LinkedList();
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String nextElement = aliases.nextElement();
            char[] charArray = passwordLookup == null ? "".toCharArray() : passwordLookup.lookupPassword(nextElement);
            Certificate certificate = keyStore.getCertificate(nextElement);
            if (certificate != null) {
                if (certificate.getPublicKey() instanceof RSAPublicKey) {
                    try {
                        RSAKey m3695load = RSAKey.m3695load(keyStore, nextElement, charArray);
                        if (m3695load != null) {
                            linkedList.add(m3695load);
                        }
                    } catch (JOSEException e) {
                    }
                } else if ((certificate.getPublicKey() instanceof ECPublicKey) && (load = ECKey.load(keyStore, nextElement, charArray)) != null) {
                    linkedList.add(load);
                }
            }
        }
        Enumeration<String> aliases2 = keyStore.aliases();
        while (aliases2.hasMoreElements()) {
            String nextElement2 = aliases2.nextElement();
            try {
                OctetSequenceKey m3692load = OctetSequenceKey.m3692load(keyStore, nextElement2, passwordLookup == null ? "".toCharArray() : passwordLookup.lookupPassword(nextElement2));
                if (m3692load != null) {
                    linkedList.add(m3692load);
                }
            } catch (JOSEException e2) {
            }
        }
        return new JWKSet(linkedList);
    }

    public static JWKSet parse(String str) throws ParseException {
        return parse(JSONObjectUtils.parse(str));
    }

    public static JWKSet parse(JSONObject jSONObject) throws ParseException {
        JSONArray jSONArray = JSONObjectUtils.getJSONArray(jSONObject, "keys");
        LinkedList linkedList = new LinkedList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= jSONArray.size()) {
                JWKSet jWKSet = new JWKSet(linkedList);
                for (Map.Entry entry : jSONObject.entrySet()) {
                    if (entry.getKey() != null && !((String) entry.getKey()).equals("keys")) {
                        jWKSet.getAdditionalMembers().put((String) entry.getKey(), entry.getValue());
                    }
                }
                return jWKSet;
            }
            if (!(jSONArray.get(i2) instanceof JSONObject)) {
                throw new ParseException("The \"keys\" JSON array must contain JSON objects only", 0);
            }
            try {
                linkedList.add(JWK.parse((JSONObject) jSONArray.get(i2)));
                i = i2 + 1;
            } catch (ParseException e) {
                throw new ParseException("Invalid JWK at position " + i2 + ": " + e.getMessage(), 0);
            }
        }
    }

    public Map<String, Object> getAdditionalMembers() {
        return this.customMembers;
    }

    public JWK getKeyByKeyId(String str) {
        for (JWK jwk : getKeys()) {
            if (jwk.getKeyID() != null && jwk.getKeyID().equals(str)) {
                return jwk;
            }
        }
        return null;
    }

    public List<JWK> getKeys() {
        return this.keys;
    }

    public JSONObject toJSONObject() {
        return toJSONObject(true);
    }

    public JSONObject toJSONObject(boolean z) {
        JSONObject jSONObject = new JSONObject(this.customMembers);
        JSONArray jSONArray = new JSONArray();
        for (JWK jwk : this.keys) {
            if (z) {
                JWK publicJWK = jwk.toPublicJWK();
                if (publicJWK != null) {
                    jSONArray.add(publicJWK.toJSONObject());
                }
            } else {
                jSONArray.add(jwk.toJSONObject());
            }
        }
        jSONObject.put("keys", jSONArray);
        return jSONObject;
    }

    public JWKSet toPublicJWKSet() {
        LinkedList linkedList = new LinkedList();
        Iterator<JWK> it = this.keys.iterator();
        while (it.hasNext()) {
            JWK publicJWK = it.next().toPublicJWK();
            if (publicJWK != null) {
                linkedList.add(publicJWK);
            }
        }
        return new JWKSet(linkedList, this.customMembers);
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
