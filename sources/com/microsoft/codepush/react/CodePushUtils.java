package com.microsoft.codepush.react;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushUtils.class */
public class CodePushUtils {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.microsoft.codepush.react.CodePushUtils$1 */
    /* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushUtils$1.class */
    public static /* synthetic */ class C01631 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:36:0x005d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.microsoft.codepush.react.CodePushUtils.C01631.$SwitchMap$com$facebook$react$bridge$ReadableType = r0
                int[] r0 = com.microsoft.codepush.react.CodePushUtils.C01631.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L4d
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L14:
                int[] r0 = com.microsoft.codepush.react.CodePushUtils.C01631.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
            L1f:
                int[] r0 = com.microsoft.codepush.react.CodePushUtils.C01631.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L51 java.lang.NoSuchFieldError -> L55
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch: java.lang.NoSuchFieldError -> L55
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L55
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L55
            L2a:
                int[] r0 = com.microsoft.codepush.react.CodePushUtils.C01631.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L55 java.lang.NoSuchFieldError -> L59
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch: java.lang.NoSuchFieldError -> L59
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L59
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L59
            L35:
                int[] r0 = com.microsoft.codepush.react.CodePushUtils.C01631.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L59 java.lang.NoSuchFieldError -> L5d
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch: java.lang.NoSuchFieldError -> L5d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L5d
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L5d
            L40:
                int[] r0 = com.microsoft.codepush.react.CodePushUtils.C01631.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L5d java.lang.NoSuchFieldError -> L61
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch: java.lang.NoSuchFieldError -> L61
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L61
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L61
                return
            L4d:
                r4 = move-exception
                goto L14
            L51:
                r4 = move-exception
                goto L1f
            L55:
                r4 = move-exception
                goto L2a
            L59:
                r4 = move-exception
                goto L35
            L5d:
                r4 = move-exception
                goto L40
            L61:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUtils.C01631.m3626clinit():void");
        }
    }

    public static String appendPathComponent(String str, String str2) {
        return new File(str, str2).getAbsolutePath();
    }

    public static WritableArray convertJsonArrayToWritable(JSONArray jSONArray) {
        WritableArray createArray = Arguments.createArray();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= jSONArray.length()) {
                return createArray;
            }
            try {
                Object obj = jSONArray.get(i2);
                if (obj instanceof JSONObject) {
                    createArray.pushMap(convertJsonObjectToWritable((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    createArray.pushArray(convertJsonArrayToWritable((JSONArray) obj));
                } else if (obj instanceof String) {
                    createArray.pushString((String) obj);
                } else if (obj instanceof Double) {
                    createArray.pushDouble(((Double) obj).doubleValue());
                } else if (obj instanceof Integer) {
                    createArray.pushInt(((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    createArray.pushBoolean(((Boolean) obj).booleanValue());
                } else {
                    if (obj != null) {
                        throw new CodePushUnknownException("Unrecognized object: " + obj);
                    }
                    createArray.pushNull();
                }
                i = i2 + 1;
            } catch (JSONException e) {
                throw new CodePushUnknownException(i2 + " should be within bounds of array " + jSONArray.toString(), e);
            }
        }
    }

    public static WritableMap convertJsonObjectToWritable(JSONObject jSONObject) {
        WritableMap createMap = Arguments.createMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof JSONObject) {
                    createMap.putMap(next, convertJsonObjectToWritable((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    createMap.putArray(next, convertJsonArrayToWritable((JSONArray) obj));
                } else if (obj instanceof String) {
                    createMap.putString(next, (String) obj);
                } else if (obj instanceof Double) {
                    createMap.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof Long) {
                    createMap.putDouble(next, ((Long) obj).doubleValue());
                } else if (obj instanceof Integer) {
                    createMap.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    createMap.putBoolean(next, ((Boolean) obj).booleanValue());
                } else {
                    if (obj != null) {
                        throw new CodePushUnknownException("Unrecognized object: " + obj);
                    }
                    createMap.putNull(next);
                }
            } catch (JSONException e) {
                throw new CodePushUnknownException("Key " + next + " should exist in " + jSONObject.toString() + ".", e);
            }
        }
        return createMap;
    }

    public static JSONArray convertReadableToJsonArray(ReadableArray readableArray) {
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= readableArray.size()) {
                return jSONArray;
            }
            switch (C01631.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i2).ordinal()]) {
                case 1:
                    jSONArray.put(convertReadableToJsonObject(readableArray.getMap(i2)));
                    break;
                case 2:
                    jSONArray.put(convertReadableToJsonArray(readableArray.getArray(i2)));
                    break;
                case 3:
                    jSONArray.put(readableArray.getString(i2));
                    break;
                case 4:
                    Double valueOf = Double.valueOf(readableArray.getDouble(i2));
                    if (valueOf.doubleValue() == Math.floor(valueOf.doubleValue()) && !Double.isInfinite(valueOf.doubleValue())) {
                        jSONArray.put(valueOf.longValue());
                        break;
                    } else {
                        try {
                            jSONArray.put(valueOf.doubleValue());
                            break;
                        } catch (JSONException e) {
                            throw new CodePushUnknownException("Unable to put value " + readableArray.getDouble(i2) + " in JSONArray");
                        }
                    }
                case 5:
                    jSONArray.put(readableArray.getBoolean(i2));
                    break;
                case 6:
                    jSONArray.put((Object) null);
                    break;
            }
            i = i2 + 1;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0032. Please report as an issue. */
    public static JSONObject convertReadableToJsonObject(ReadableMap readableMap) {
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            ReadableType type = readableMap.getType(nextKey);
            try {
                switch (C01631.$SwitchMap$com$facebook$react$bridge$ReadableType[type.ordinal()]) {
                    case 1:
                        jSONObject.put(nextKey, convertReadableToJsonObject(readableMap.getMap(nextKey)));
                    case 2:
                        jSONObject.put(nextKey, convertReadableToJsonArray(readableMap.getArray(nextKey)));
                    case 3:
                        jSONObject.put(nextKey, readableMap.getString(nextKey));
                    case 4:
                        jSONObject.put(nextKey, readableMap.getDouble(nextKey));
                    case 5:
                        jSONObject.put(nextKey, readableMap.getBoolean(nextKey));
                    case 6:
                        jSONObject.put(nextKey, (Object) null);
                    default:
                        throw new CodePushUnknownException("Unrecognized type: " + type + " of key: " + nextKey);
                }
            } catch (JSONException e) {
                throw new CodePushUnknownException("Error setting key: " + nextKey + " in JSONObject", e);
            }
        }
        return jSONObject;
    }

    public static JSONObject getJsonObjectFromFile(String str) throws IOException {
        try {
            return new JSONObject(FileUtils.readFileToString(str));
        } catch (JSONException e) {
            throw new CodePushMalformedDataException(str, e);
        }
    }

    public static String getStringFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader;
        try {
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append("\n");
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            }
            String trim = sb.toString().trim();
            bufferedReader.close();
            if (inputStream != null) {
                inputStream.close();
            }
            return trim;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    public static void log(String str) {
        Log.d(CodePushConstants.REACT_NATIVE_LOG_TAG, "[CodePush] " + str);
    }

    public static void log(Throwable th) {
        Log.e(CodePushConstants.REACT_NATIVE_LOG_TAG, "[CodePush] Exception", th);
    }

    public static void logBundleUrl(String str) {
        log("Loading JS bundle from \"" + str + "\"");
    }

    public static void setJSONValueForKey(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to set value " + obj + " for key " + str + " to JSONObject");
        }
    }

    public static String tryGetString(ReadableMap readableMap, String str) {
        try {
            return readableMap.getString(str);
        } catch (NoSuchKeyException e) {
            return null;
        }
    }

    public static void writeJsonToFile(JSONObject jSONObject, String str) throws IOException {
        FileUtils.writeStringToFile(jSONObject.toString(), str);
    }
}
