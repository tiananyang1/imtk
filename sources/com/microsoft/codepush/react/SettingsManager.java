package com.microsoft.codepush.react;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/SettingsManager.class */
public class SettingsManager {
    private SharedPreferences mSettings;

    public SettingsManager(Context context) {
        this.mSettings = context.getSharedPreferences("CodePush", 0);
    }

    public JSONArray getFailedUpdates() {
        String string = this.mSettings.getString(CodePushConstants.FAILED_UPDATES_KEY, null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(string);
        } catch (JSONException e) {
            JSONArray jSONArray = new JSONArray();
            this.mSettings.edit().putString(CodePushConstants.FAILED_UPDATES_KEY, jSONArray.toString()).commit();
            return jSONArray;
        }
    }

    public JSONObject getLatestRollbackInfo() {
        String string = this.mSettings.getString(CodePushConstants.LATEST_ROLLBACK_INFO_KEY, null);
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            CodePushUtils.log("Unable to parse latest rollback metadata " + string + " stored in SharedPreferences");
            return null;
        }
    }

    public JSONObject getPendingUpdate() {
        String string = this.mSettings.getString(CodePushConstants.PENDING_UPDATE_KEY, null);
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            CodePushUtils.log("Unable to parse pending update metadata " + string + " stored in SharedPreferences");
            return null;
        }
    }

    public boolean isFailedHash(String str) {
        JSONArray failedUpdates = getFailedUpdates();
        if (str == null) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= failedUpdates.length()) {
                return false;
            }
            try {
                if (str.equals(failedUpdates.getJSONObject(i2).getString("packageHash"))) {
                    return true;
                }
                i = i2 + 1;
            } catch (JSONException e) {
                throw new CodePushUnknownException("Unable to read failedUpdates data stored in SharedPreferences.", e);
            }
        }
    }

    public boolean isPendingUpdate(String str) {
        JSONObject pendingUpdate = getPendingUpdate();
        if (pendingUpdate == null) {
            return false;
        }
        try {
            if (pendingUpdate.getBoolean(CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY)) {
                return false;
            }
            if (str != null) {
                return pendingUpdate.getString("hash").equals(str);
            }
            return true;
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to read pending update metadata in isPendingUpdate.", e);
        }
    }

    public void removeFailedUpdates() {
        this.mSettings.edit().remove(CodePushConstants.FAILED_UPDATES_KEY).commit();
    }

    public void removePendingUpdate() {
        this.mSettings.edit().remove(CodePushConstants.PENDING_UPDATE_KEY).commit();
    }

    public void saveFailedUpdate(JSONObject jSONObject) {
        JSONArray jSONArray;
        try {
            if (isFailedHash(jSONObject.getString("packageHash"))) {
                return;
            }
            String string = this.mSettings.getString(CodePushConstants.FAILED_UPDATES_KEY, null);
            if (string == null) {
                jSONArray = new JSONArray();
            } else {
                try {
                    jSONArray = new JSONArray(string);
                } catch (JSONException e) {
                    throw new CodePushMalformedDataException("Unable to parse failed updates information " + string + " stored in SharedPreferences", e);
                }
            }
            jSONArray.put(jSONObject);
            this.mSettings.edit().putString(CodePushConstants.FAILED_UPDATES_KEY, jSONArray.toString()).commit();
        } catch (JSONException e2) {
            throw new CodePushUnknownException("Unable to read package hash from package.", e2);
        }
    }

    public void savePendingUpdate(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("hash", str);
            jSONObject.put(CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY, z);
            this.mSettings.edit().putString(CodePushConstants.PENDING_UPDATE_KEY, jSONObject.toString()).commit();
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to save pending update.", e);
        }
    }

    public void setLatestRollbackInfo(String str) {
        JSONObject jSONObject;
        int i;
        JSONObject latestRollbackInfo = getLatestRollbackInfo();
        if (latestRollbackInfo != null) {
            jSONObject = latestRollbackInfo;
            i = 0;
            try {
                if (latestRollbackInfo.getString("packageHash").equals(str)) {
                    i = latestRollbackInfo.getInt(CodePushConstants.LATEST_ROLLBACK_COUNT_KEY);
                    jSONObject = latestRollbackInfo;
                }
            } catch (JSONException e) {
                CodePushUtils.log("Unable to parse latest rollback info.");
                jSONObject = latestRollbackInfo;
                i = 0;
            }
        } else {
            jSONObject = new JSONObject();
            i = 0;
        }
        try {
            jSONObject.put("packageHash", str);
            jSONObject.put(CodePushConstants.LATEST_ROLLBACK_TIME_KEY, System.currentTimeMillis());
            jSONObject.put(CodePushConstants.LATEST_ROLLBACK_COUNT_KEY, i + 1);
            this.mSettings.edit().putString(CodePushConstants.LATEST_ROLLBACK_INFO_KEY, jSONObject.toString()).commit();
        } catch (JSONException e2) {
            throw new CodePushUnknownException("Unable to save latest rollback info.", e2);
        }
    }
}
