package com.microsoft.codepush.react;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushTelemetryManager.class */
public class CodePushTelemetryManager {
    private final String APP_VERSION_KEY = "appVersion";
    private final String DEPLOYMENT_FAILED_STATUS = "DeploymentFailed";
    private final String DEPLOYMENT_KEY_KEY = "deploymentKey";
    private final String DEPLOYMENT_SUCCEEDED_STATUS = "DeploymentSucceeded";
    private final String LABEL_KEY = "label";
    private final String LAST_DEPLOYMENT_REPORT_KEY = "CODE_PUSH_LAST_DEPLOYMENT_REPORT";
    private final String PACKAGE_KEY = "package";
    private final String PREVIOUS_DEPLOYMENT_KEY_KEY = "previousDeploymentKey";
    private final String PREVIOUS_LABEL_OR_APP_VERSION_KEY = "previousLabelOrAppVersion";
    private final String RETRY_DEPLOYMENT_REPORT_KEY = "CODE_PUSH_RETRY_DEPLOYMENT_REPORT";
    private final String STATUS_KEY = SettingsJsonConstants.APP_STATUS_KEY;
    private SharedPreferences mSettings;

    public CodePushTelemetryManager(Context context) {
        this.mSettings = context.getSharedPreferences("CodePush", 0);
    }

    private void clearRetryStatusReport() {
        this.mSettings.edit().remove("CODE_PUSH_RETRY_DEPLOYMENT_REPORT").commit();
    }

    private String getDeploymentKeyFromStatusReportIdentifier(String str) {
        String[] split = str.split(Constants.COLON_SEPARATOR);
        if (split.length > 0) {
            return split[0];
        }
        return null;
    }

    private String getPackageStatusReportIdentifier(ReadableMap readableMap) {
        String tryGetString = CodePushUtils.tryGetString(readableMap, "deploymentKey");
        String tryGetString2 = CodePushUtils.tryGetString(readableMap, "label");
        if (tryGetString == null || tryGetString2 == null) {
            return null;
        }
        return tryGetString + Constants.COLON_SEPARATOR + tryGetString2;
    }

    private String getPreviousStatusReportIdentifier() {
        return this.mSettings.getString("CODE_PUSH_LAST_DEPLOYMENT_REPORT", null);
    }

    private String getVersionLabelFromStatusReportIdentifier(String str) {
        String[] split = str.split(Constants.COLON_SEPARATOR);
        if (split.length > 1) {
            return split[1];
        }
        return null;
    }

    private boolean isStatusReportIdentifierCodePushLabel(String str) {
        return str != null && str.contains(Constants.COLON_SEPARATOR);
    }

    private void saveStatusReportedForIdentifier(String str) {
        this.mSettings.edit().putString("CODE_PUSH_LAST_DEPLOYMENT_REPORT", str).commit();
    }

    public WritableMap getBinaryUpdateReport(String str) {
        String previousStatusReportIdentifier = getPreviousStatusReportIdentifier();
        if (previousStatusReportIdentifier == null) {
            clearRetryStatusReport();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("appVersion", str);
            return createMap;
        }
        if (previousStatusReportIdentifier.equals(str)) {
            return null;
        }
        clearRetryStatusReport();
        WritableMap createMap2 = Arguments.createMap();
        if (isStatusReportIdentifierCodePushLabel(previousStatusReportIdentifier)) {
            String deploymentKeyFromStatusReportIdentifier = getDeploymentKeyFromStatusReportIdentifier(previousStatusReportIdentifier);
            String versionLabelFromStatusReportIdentifier = getVersionLabelFromStatusReportIdentifier(previousStatusReportIdentifier);
            createMap2.putString("appVersion", str);
            createMap2.putString("previousDeploymentKey", deploymentKeyFromStatusReportIdentifier);
            createMap2.putString("previousLabelOrAppVersion", versionLabelFromStatusReportIdentifier);
        } else {
            createMap2.putString("appVersion", str);
            createMap2.putString("previousLabelOrAppVersion", previousStatusReportIdentifier);
        }
        return createMap2;
    }

    public WritableMap getRetryStatusReport() {
        String string = this.mSettings.getString("CODE_PUSH_RETRY_DEPLOYMENT_REPORT", null);
        if (string == null) {
            return null;
        }
        clearRetryStatusReport();
        try {
            return CodePushUtils.convertJsonObjectToWritable(new JSONObject(string));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public WritableMap getRollbackReport(WritableMap writableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.putMap("package", writableMap);
        createMap.putString(SettingsJsonConstants.APP_STATUS_KEY, "DeploymentFailed");
        return createMap;
    }

    public WritableMap getUpdateReport(WritableMap writableMap) {
        String packageStatusReportIdentifier = getPackageStatusReportIdentifier(writableMap);
        String previousStatusReportIdentifier = getPreviousStatusReportIdentifier();
        if (packageStatusReportIdentifier == null) {
            return null;
        }
        if (previousStatusReportIdentifier == null) {
            clearRetryStatusReport();
            WritableMap createMap = Arguments.createMap();
            createMap.putMap("package", writableMap);
            createMap.putString(SettingsJsonConstants.APP_STATUS_KEY, "DeploymentSucceeded");
            return createMap;
        }
        if (previousStatusReportIdentifier.equals(packageStatusReportIdentifier)) {
            return null;
        }
        clearRetryStatusReport();
        WritableMap createMap2 = Arguments.createMap();
        if (!isStatusReportIdentifierCodePushLabel(previousStatusReportIdentifier)) {
            createMap2.putMap("package", writableMap);
            createMap2.putString(SettingsJsonConstants.APP_STATUS_KEY, "DeploymentSucceeded");
            createMap2.putString("previousLabelOrAppVersion", previousStatusReportIdentifier);
            return createMap2;
        }
        String deploymentKeyFromStatusReportIdentifier = getDeploymentKeyFromStatusReportIdentifier(previousStatusReportIdentifier);
        String versionLabelFromStatusReportIdentifier = getVersionLabelFromStatusReportIdentifier(previousStatusReportIdentifier);
        createMap2.putMap("package", writableMap);
        createMap2.putString(SettingsJsonConstants.APP_STATUS_KEY, "DeploymentSucceeded");
        createMap2.putString("previousDeploymentKey", deploymentKeyFromStatusReportIdentifier);
        createMap2.putString("previousLabelOrAppVersion", versionLabelFromStatusReportIdentifier);
        return createMap2;
    }

    public void recordStatusReported(ReadableMap readableMap) {
        if (readableMap.hasKey(SettingsJsonConstants.APP_STATUS_KEY) && "DeploymentFailed".equals(readableMap.getString(SettingsJsonConstants.APP_STATUS_KEY))) {
            return;
        }
        if (readableMap.hasKey("appVersion")) {
            saveStatusReportedForIdentifier(readableMap.getString("appVersion"));
        } else if (readableMap.hasKey("package")) {
            saveStatusReportedForIdentifier(getPackageStatusReportIdentifier(readableMap.getMap("package")));
        }
    }

    public void saveStatusReportForRetry(ReadableMap readableMap) {
        this.mSettings.edit().putString("CODE_PUSH_RETRY_DEPLOYMENT_REPORT", CodePushUtils.convertReadableToJsonObject(readableMap).toString()).commit();
    }
}
