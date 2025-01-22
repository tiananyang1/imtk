package com.meituan.android.walle;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/meituan/android/walle/ChannelReader.class */
public final class ChannelReader {
    public static final String CHANNEL_KEY = "channel";

    private ChannelReader() {
    }

    public static ChannelInfo get(File file) {
        Map<String, String> map = getMap(file);
        if (map == null) {
            return null;
        }
        String str = map.get(CHANNEL_KEY);
        map.remove(CHANNEL_KEY);
        return new ChannelInfo(str, map);
    }

    public static Map<String, String> getMap(File file) {
        try {
            String raw = getRaw(file);
            if (raw == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(raw);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                hashMap.put(obj, jSONObject.getString(obj));
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRaw(File file) {
        return PayloadReader.getString(file, ApkUtil.APK_CHANNEL_BLOCK_ID);
    }
}
