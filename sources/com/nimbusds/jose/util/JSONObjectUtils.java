package com.nimbusds.jose.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/JSONObjectUtils.class */
public class JSONObjectUtils {
    private JSONObjectUtils() {
    }

    public static boolean getBoolean(JSONObject jSONObject, String str) throws ParseException {
        return ((Boolean) getGeneric(jSONObject, str, Boolean.class)).booleanValue();
    }

    public static double getDouble(JSONObject jSONObject, String str) throws ParseException {
        return ((Number) getGeneric(jSONObject, str, Number.class)).doubleValue();
    }

    public static float getFloat(JSONObject jSONObject, String str) throws ParseException {
        return ((Number) getGeneric(jSONObject, str, Number.class)).floatValue();
    }

    private static <T> T getGeneric(JSONObject jSONObject, String str, Class<T> cls) throws ParseException {
        if (!jSONObject.containsKey(str)) {
            throw new ParseException("Missing JSON object member with key \"" + str + "\"", 0);
        }
        if (jSONObject.get(str) == null) {
            throw new ParseException("JSON object member with key \"" + str + "\" has null value", 0);
        }
        T t = (T) jSONObject.get(str);
        if (cls.isAssignableFrom(t.getClass())) {
            return t;
        }
        throw new ParseException("Unexpected type of JSON object member with key \"" + str + "\"", 0);
    }

    public static int getInt(JSONObject jSONObject, String str) throws ParseException {
        return ((Number) getGeneric(jSONObject, str, Number.class)).intValue();
    }

    public static JSONArray getJSONArray(JSONObject jSONObject, String str) throws ParseException {
        return (JSONArray) getGeneric(jSONObject, str, JSONArray.class);
    }

    public static JSONObject getJSONObject(JSONObject jSONObject, String str) throws ParseException {
        return (JSONObject) getGeneric(jSONObject, str, JSONObject.class);
    }

    public static long getLong(JSONObject jSONObject, String str) throws ParseException {
        return ((Number) getGeneric(jSONObject, str, Number.class)).longValue();
    }

    public static String getString(JSONObject jSONObject, String str) throws ParseException {
        return (String) getGeneric(jSONObject, str, String.class);
    }

    public static String[] getStringArray(JSONObject jSONObject, String str) throws ParseException {
        try {
            return (String[]) getJSONArray(jSONObject, str).toArray(new String[0]);
        } catch (ArrayStoreException e) {
            throw new ParseException("JSON object member with key \"" + str + "\" is not an array of strings", 0);
        }
    }

    public static List<String> getStringList(JSONObject jSONObject, String str) throws ParseException {
        return Arrays.asList(getStringArray(jSONObject, str));
    }

    public static URI getURI(JSONObject jSONObject, String str) throws ParseException {
        try {
            return new URI((String) getGeneric(jSONObject, str, String.class));
        } catch (URISyntaxException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public static JSONObject parse(String str) throws ParseException {
        try {
            Object parse = new JSONParser(640).parse(str);
            if (parse instanceof JSONObject) {
                return (JSONObject) parse;
            }
            throw new ParseException("JSON entity is not an object", 0);
        } catch (net.minidev.json.parser.ParseException e) {
            throw new ParseException("Invalid JSON: " + e.getMessage(), 0);
        }
    }

    @Deprecated
    public static JSONObject parseJSONObject(String str) throws ParseException {
        return parse(str);
    }
}
