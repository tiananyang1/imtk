package com.nimbusds.jose.jwk;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.StandardCharset;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import net.minidev.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/ThumbprintUtils.class */
public final class ThumbprintUtils {
    public static Base64URL compute(JWK jwk) throws JOSEException {
        return compute(CommonUtils.SHA256_INSTANCE, jwk);
    }

    public static Base64URL compute(String str, JWK jwk) throws JOSEException {
        return compute(str, jwk.getRequiredParams());
    }

    public static Base64URL compute(String str, LinkedHashMap<String, ?> linkedHashMap) throws JOSEException {
        String jSONString = JSONObject.toJSONString(linkedHashMap);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(jSONString.getBytes(StandardCharset.UTF_8));
            return Base64URL.m3704encode(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new JOSEException("Couldn't compute JWK thumbprint: Unsupported hash algorithm: " + e.getMessage(), e);
        }
    }
}
