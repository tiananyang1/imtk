package com.sensorsdata.analytics.android.sdk.util;

import com.sensorsdata.analytics.android.sdk.SALog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/util/JSONUtils.class */
public class JSONUtils {
    private static void addIndentBlank(StringBuilder sb, int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                return;
            }
            try {
                sb.append('\t');
                i2 = i3 + 1;
            } catch (Exception e) {
                SALog.printStackTrace(e);
                return;
            }
        }
    }

    public static String formatJson(String str) {
        boolean z;
        int i;
        if (str == null) {
            return "";
        }
        try {
            if ("".equals(str)) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            char c = 0;
            boolean z2 = false;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i2 >= str.length()) {
                    return sb.toString();
                }
                char charAt = str.charAt(i2);
                if (charAt == '\"') {
                    boolean z3 = z2;
                    if (c != '\\') {
                        z3 = !z2;
                    }
                    sb.append(charAt);
                    z = z3;
                    i = i4;
                } else if (charAt != ',') {
                    if (charAt != '[') {
                        if (charAt != ']') {
                            if (charAt != '{') {
                                if (charAt != '}') {
                                    sb.append(charAt);
                                    z = z2;
                                    i = i4;
                                }
                            }
                        }
                        i = i4;
                        if (!z2) {
                            sb.append('\n');
                            i = i4 - 1;
                            addIndentBlank(sb, i);
                        }
                        sb.append(charAt);
                        z = z2;
                    }
                    sb.append(charAt);
                    z = z2;
                    i = i4;
                    if (!z2) {
                        sb.append('\n');
                        i = i4 + 1;
                        addIndentBlank(sb, i);
                        z = z2;
                    }
                } else {
                    sb.append(charAt);
                    z = z2;
                    i = i4;
                    if (c != '\\') {
                        z = z2;
                        i = i4;
                        if (!z2) {
                            sb.append('\n');
                            addIndentBlank(sb, i4);
                            z = z2;
                            i = i4;
                        }
                    }
                }
                i2++;
                c = charAt;
                z2 = z;
                i3 = i;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return "";
        }
    }

    public static String optionalStringKey(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }
}
