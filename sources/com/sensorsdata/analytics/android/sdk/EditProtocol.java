package com.sensorsdata.analytics.android.sdk;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/EditProtocol.class */
public class EditProtocol {
    private static final Class<?>[] NO_PARAMS = new Class[0];
    private static final String TAG = "SA.EProtocol";
    private final ResourceIds mResourceIds;

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/EditProtocol$BadInstructionsException.class */
    public static class BadInstructionsException extends Exception {
        private static final long serialVersionUID = -4062004792184145311L;

        public BadInstructionsException(String str) {
            super(str);
        }

        public BadInstructionsException(String str, Throwable th) {
            super(str, th);
        }
    }

    public EditProtocol(ResourceIds resourceIds) {
        this.mResourceIds = resourceIds;
    }

    private PropertyDescription readPropertyDescription(Class<?> cls, JSONObject jSONObject) throws BadInstructionsException {
        Caller caller;
        try {
            String string = jSONObject.getString("name");
            String str = null;
            if (jSONObject.has("get")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("get");
                caller = new Caller(cls, jSONObject2.getString("selector"), NO_PARAMS, Class.forName(jSONObject2.getJSONObject("result").getString("type")));
            } else {
                caller = null;
            }
            if (jSONObject.has("set")) {
                str = jSONObject.getJSONObject("set").getString("selector");
            }
            return new PropertyDescription(string, cls, caller, str);
        } catch (ClassNotFoundException e) {
            throw new BadInstructionsException("Can't read property JSON, relevant arg/return class not found", e);
        } catch (NoSuchMethodException e2) {
            throw new BadInstructionsException("Can't create property reader", e2);
        } catch (JSONException e3) {
            throw new BadInstructionsException("Can't read property JSON", e3);
        }
    }

    public ViewSnapshot readSnapshotConfig(JSONObject jSONObject) throws BadInstructionsException {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONObject("config").getJSONArray("classes");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= jSONArray.length()) {
                    return new ViewSnapshot(arrayList, this.mResourceIds);
                }
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                Class<?> cls = Class.forName(jSONObject2.getString("name"));
                JSONArray jSONArray2 = jSONObject2.getJSONArray("properties");
                int i3 = 0;
                while (true) {
                    int i4 = i3;
                    if (i4 < jSONArray2.length()) {
                        arrayList.add(readPropertyDescription(cls, jSONArray2.getJSONObject(i4)));
                        i3 = i4 + 1;
                    }
                }
                i = i2 + 1;
            }
        } catch (ClassNotFoundException e) {
            throw new BadInstructionsException("Can't resolve types for snapshot configuration", e);
        } catch (JSONException e2) {
            throw new BadInstructionsException("Can't read snapshot configuration", e2);
        }
    }
}
