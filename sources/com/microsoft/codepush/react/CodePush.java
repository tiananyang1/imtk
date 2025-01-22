package com.microsoft.codepush.react;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.uimanager.ViewManager;
import com.stub.StubApp;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePush.class */
public class CodePush implements ReactPackage {
    private static CodePush mCurrentInstance;
    private static String mPublicKey;
    private static ReactInstanceHolder mReactInstanceHolder;
    private static String mServerUrl = "https://codepush.azurewebsites.net/";
    private static String sAppVersion;
    private static boolean sIsRunningBinaryVersion;
    private static boolean sNeedToReportRollback;
    private static boolean sTestConfigurationFlag;
    private String mAssetsBundleFileName;
    private Context mContext;
    private String mDeploymentKey;
    private boolean mDidUpdate;
    private final boolean mIsDebugMode;
    private SettingsManager mSettingsManager;
    private CodePushTelemetryManager mTelemetryManager;
    private CodePushUpdateManager mUpdateManager;

    public CodePush(String str, Context context) {
        this(str, context, false);
    }

    public CodePush(String str, Context context, boolean z) {
        this.mDidUpdate = false;
        this.mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.mUpdateManager = new CodePushUpdateManager(context.getFilesDir().getAbsolutePath());
        this.mTelemetryManager = new CodePushTelemetryManager(this.mContext);
        this.mDeploymentKey = str;
        this.mIsDebugMode = z;
        this.mSettingsManager = new SettingsManager(this.mContext);
        if (sAppVersion == null) {
            try {
                sAppVersion = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                throw new CodePushUnknownException("Unable to get package info for " + this.mContext.getPackageName(), e);
            }
        }
        mCurrentInstance = this;
        clearDebugCacheIfNeeded(null);
        initializeUpdateAfterRestart();
    }

    public CodePush(String str, Context context, boolean z, int i) {
        this(str, context, z);
        mPublicKey = getPublicKeyByResourceDescriptor(i);
    }

    public CodePush(String str, Context context, boolean z, String str2) {
        this(str, context, z);
        mServerUrl = str2;
    }

    public CodePush(String str, Context context, boolean z, String str2, Integer num) {
        this(str, context, z);
        if (num != null) {
            mPublicKey = getPublicKeyByResourceDescriptor(num.intValue());
        }
        mServerUrl = str2;
    }

    @Deprecated
    public static String getBundleUrl() {
        return getJSBundleFile();
    }

    @Deprecated
    public static String getBundleUrl(String str) {
        return getJSBundleFile(str);
    }

    public static String getJSBundleFile() {
        return getJSBundleFile(CodePushConstants.DEFAULT_JS_BUNDLE_NAME);
    }

    public static String getJSBundleFile(String str) {
        CodePush codePush = mCurrentInstance;
        if (codePush != null) {
            return codePush.getJSBundleFileInternal(str);
        }
        throw new CodePushNotInitializedException("A CodePush instance has not been created yet. Have you added it to your app's list of ReactPackages?");
    }

    private String getPublicKeyByResourceDescriptor(int i) {
        try {
            String string = this.mContext.getString(i);
            if (string.isEmpty()) {
                throw new CodePushInvalidPublicKeyException("Specified public key is empty");
            }
            return string;
        } catch (Resources.NotFoundException e) {
            throw new CodePushInvalidPublicKeyException("Unable to get public key, related resource descriptor " + i + " can not be found", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReactInstanceManager getReactInstanceManager() {
        ReactInstanceHolder reactInstanceHolder = mReactInstanceHolder;
        if (reactInstanceHolder == null) {
            return null;
        }
        return reactInstanceHolder.getReactInstanceManager();
    }

    public static String getServiceUrl() {
        return mServerUrl;
    }

    private boolean hasBinaryVersionChanged(JSONObject jSONObject) {
        return !sAppVersion.equals(jSONObject.optString("appVersion", null));
    }

    private boolean isPackageBundleLatest(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString(CodePushConstants.BINARY_MODIFIED_TIME_KEY, null);
            Long valueOf = optString != null ? Long.valueOf(Long.parseLong(optString)) : null;
            String optString2 = jSONObject.optString("appVersion", null);
            long binaryResourcesModifiedTime = getBinaryResourcesModifiedTime();
            if (valueOf == null || valueOf.longValue() != binaryResourcesModifiedTime) {
                return false;
            }
            if (isUsingTestConfiguration()) {
                return true;
            }
            return sAppVersion.equals(optString2);
        } catch (NumberFormatException e) {
            throw new CodePushUnknownException("Error in reading binary modified date from package metadata", e);
        }
    }

    public static boolean isUsingTestConfiguration() {
        return sTestConfigurationFlag;
    }

    public static void overrideAppVersion(String str) {
        sAppVersion = str;
    }

    private void rollbackPackage() {
        this.mSettingsManager.saveFailedUpdate(this.mUpdateManager.getCurrentPackage());
        this.mUpdateManager.rollbackPackage();
        this.mSettingsManager.removePendingUpdate();
    }

    public static void setReactInstanceHolder(ReactInstanceHolder reactInstanceHolder) {
        mReactInstanceHolder = reactInstanceHolder;
    }

    public static void setUsingTestConfiguration(boolean z) {
        sTestConfigurationFlag = z;
    }

    public void clearDebugCacheIfNeeded(ReactInstanceManager reactInstanceManager) {
        DevSupportManager devSupportManager;
        boolean isReloadOnJSChangeEnabled = (reactInstanceManager == null || (devSupportManager = reactInstanceManager.getDevSupportManager()) == null) ? false : devSupportManager.getDevSettings().isReloadOnJSChangeEnabled();
        if (this.mIsDebugMode && this.mSettingsManager.isPendingUpdate(null) && !isReloadOnJSChangeEnabled) {
            File file = new File(this.mContext.getFilesDir(), "ReactNativeDevBundle.js");
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public void clearUpdates() {
        this.mUpdateManager.clearUpdates();
        this.mSettingsManager.removePendingUpdate();
        this.mSettingsManager.removeFailedUpdates();
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return new ArrayList();
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        CodePushNativeModule codePushNativeModule = new CodePushNativeModule(reactApplicationContext, this, this.mUpdateManager, this.mTelemetryManager, this.mSettingsManager);
        CodePushDialog codePushDialog = new CodePushDialog(reactApplicationContext);
        ArrayList arrayList = new ArrayList();
        arrayList.add(codePushNativeModule);
        arrayList.add(codePushDialog);
        return arrayList;
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList();
    }

    public boolean didUpdate() {
        return this.mDidUpdate;
    }

    public String getAppVersion() {
        return sAppVersion;
    }

    public String getAssetsBundleFileName() {
        return this.mAssetsBundleFileName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getBinaryResourcesModifiedTime() {
        try {
            return Long.parseLong(this.mContext.getResources().getString(this.mContext.getResources().getIdentifier(CodePushConstants.CODE_PUSH_APK_BUILD_TIME_KEY, "string", this.mContext.getPackageName())).replaceAll("\"", ""));
        } catch (Exception e) {
            throw new CodePushUnknownException("Error in getting binary resources modified time", e);
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getDeploymentKey() {
        return this.mDeploymentKey;
    }

    public String getJSBundleFileInternal(String str) {
        String str2;
        this.mAssetsBundleFileName = str;
        String str3 = CodePushConstants.ASSETS_BUNDLE_PREFIX + str;
        try {
            str2 = this.mUpdateManager.getCurrentPackageBundlePath(this.mAssetsBundleFileName);
        } catch (CodePushMalformedDataException e) {
            CodePushUtils.log(e.getMessage());
            clearUpdates();
            str2 = null;
        }
        if (str2 == null) {
            CodePushUtils.logBundleUrl(str3);
            sIsRunningBinaryVersion = true;
            return str3;
        }
        JSONObject currentPackage = this.mUpdateManager.getCurrentPackage();
        if (isPackageBundleLatest(currentPackage)) {
            CodePushUtils.logBundleUrl(str2);
            sIsRunningBinaryVersion = false;
            return str2;
        }
        this.mDidUpdate = false;
        if (!this.mIsDebugMode || hasBinaryVersionChanged(currentPackage)) {
            clearUpdates();
        }
        CodePushUtils.logBundleUrl(str3);
        sIsRunningBinaryVersion = true;
        return str3;
    }

    public String getPackageFolder() {
        JSONObject currentPackage = this.mUpdateManager.getCurrentPackage();
        if (currentPackage == null) {
            return null;
        }
        return this.mUpdateManager.getPackageFolderPath(currentPackage.optString("packageHash"));
    }

    public String getPublicKey() {
        return mPublicKey;
    }

    public String getServerUrl() {
        return mServerUrl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initializeUpdateAfterRestart() {
        this.mDidUpdate = false;
        JSONObject pendingUpdate = this.mSettingsManager.getPendingUpdate();
        if (pendingUpdate != null) {
            JSONObject currentPackage = this.mUpdateManager.getCurrentPackage();
            if (currentPackage == null || (!isPackageBundleLatest(currentPackage) && hasBinaryVersionChanged(currentPackage))) {
                CodePushUtils.log("Skipping initializeUpdateAfterRestart(), binary version is newer");
                return;
            }
            try {
                if (!pendingUpdate.getBoolean(CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY)) {
                    this.mDidUpdate = true;
                    this.mSettingsManager.savePendingUpdate(pendingUpdate.getString("hash"), true);
                } else {
                    CodePushUtils.log("Update did not finish loading the last time, rolling back to a previous version.");
                    sNeedToReportRollback = true;
                    rollbackPackage();
                }
            } catch (JSONException e) {
                throw new CodePushUnknownException("Unable to read pending update metadata stored in SharedPreferences", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invalidateCurrentInstance() {
        mCurrentInstance = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDebugMode() {
        return this.mIsDebugMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isRunningBinaryVersion() {
        return sIsRunningBinaryVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean needToReportRollback() {
        return sNeedToReportRollback;
    }

    public void setDeploymentKey(String str) {
        this.mDeploymentKey = str;
    }

    public void setNeedToReportRollback(boolean z) {
        sNeedToReportRollback = z;
    }
}
