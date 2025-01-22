package com.microsoft.codepush.react;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushNativeModule.class */
public class CodePushNativeModule extends ReactContextBaseJavaModule {
    private String mBinaryContentsHash;
    private String mClientUniqueId;
    private CodePush mCodePush;
    private LifecycleEventListener mLifecycleEventListener;
    private int mMinimumBackgroundDuration;
    private SettingsManager mSettingsManager;
    private CodePushTelemetryManager mTelemetryManager;
    private CodePushUpdateManager mUpdateManager;

    /* renamed from: com.microsoft.codepush.react.CodePushNativeModule$6 */
    /* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushNativeModule$6.class */
    class AsyncTaskC01626 extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ int val$installMode;
        final /* synthetic */ int val$minimumBackgroundDuration;
        final /* synthetic */ Promise val$promise;
        final /* synthetic */ ReadableMap val$updatePackage;

        AsyncTaskC01626(ReadableMap readableMap, int i, int i2, Promise promise) {
            this.val$updatePackage = readableMap;
            this.val$installMode = i;
            this.val$minimumBackgroundDuration = i2;
            this.val$promise = promise;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            try {
                CodePushNativeModule.this.mUpdateManager.installPackage(CodePushUtils.convertReadableToJsonObject(this.val$updatePackage), CodePushNativeModule.this.mSettingsManager.isPendingUpdate(null));
                String tryGetString = CodePushUtils.tryGetString(this.val$updatePackage, "packageHash");
                if (tryGetString == null) {
                    throw new CodePushUnknownException("Update package to be installed has no hash.");
                }
                CodePushNativeModule.this.mSettingsManager.savePendingUpdate(tryGetString, false);
                if (this.val$installMode == CodePushInstallMode.ON_NEXT_RESUME.getValue() || this.val$installMode == CodePushInstallMode.IMMEDIATE.getValue() || this.val$installMode == CodePushInstallMode.ON_NEXT_SUSPEND.getValue()) {
                    CodePushNativeModule.this.mMinimumBackgroundDuration = this.val$minimumBackgroundDuration;
                    if (CodePushNativeModule.this.mLifecycleEventListener == null) {
                        CodePushNativeModule.this.mLifecycleEventListener = new LifecycleEventListener() { // from class: com.microsoft.codepush.react.CodePushNativeModule.6.1
                            private Date lastPausedDate = null;
                            private Handler appSuspendHandler = new Handler(Looper.getMainLooper());
                            private Runnable loadBundleRunnable = new Runnable() { // from class: com.microsoft.codepush.react.CodePushNativeModule.6.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    CodePushUtils.log("Loading bundle on suspend");
                                    CodePushNativeModule.this.loadBundle();
                                }
                            };

                            public void onHostDestroy() {
                            }

                            public void onHostPause() {
                                this.lastPausedDate = new Date();
                                if (AsyncTaskC01626.this.val$installMode == CodePushInstallMode.ON_NEXT_SUSPEND.getValue() && CodePushNativeModule.this.mSettingsManager.isPendingUpdate(null)) {
                                    this.appSuspendHandler.postDelayed(this.loadBundleRunnable, AsyncTaskC01626.this.val$minimumBackgroundDuration * PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT);
                                }
                            }

                            public void onHostResume() {
                                this.appSuspendHandler.removeCallbacks(this.loadBundleRunnable);
                                if (this.lastPausedDate != null) {
                                    long time = (new Date().getTime() - this.lastPausedDate.getTime()) / 1000;
                                    if (AsyncTaskC01626.this.val$installMode == CodePushInstallMode.IMMEDIATE.getValue() || time >= CodePushNativeModule.this.mMinimumBackgroundDuration) {
                                        CodePushUtils.log("Loading bundle on resume");
                                        CodePushNativeModule.this.loadBundle();
                                    }
                                }
                            }
                        };
                        CodePushNativeModule.this.getReactApplicationContext().addLifecycleEventListener(CodePushNativeModule.this.mLifecycleEventListener);
                    }
                }
                this.val$promise.resolve("");
                return null;
            } catch (CodePushUnknownException e) {
                CodePushUtils.log(e);
                this.val$promise.reject(e);
                return null;
            }
        }
    }

    public CodePushNativeModule(ReactApplicationContext reactApplicationContext, CodePush codePush, CodePushUpdateManager codePushUpdateManager, CodePushTelemetryManager codePushTelemetryManager, SettingsManager settingsManager) {
        super(reactApplicationContext);
        this.mBinaryContentsHash = null;
        this.mClientUniqueId = null;
        this.mLifecycleEventListener = null;
        this.mMinimumBackgroundDuration = 0;
        this.mCodePush = codePush;
        this.mSettingsManager = settingsManager;
        this.mTelemetryManager = codePushTelemetryManager;
        this.mUpdateManager = codePushUpdateManager;
        this.mBinaryContentsHash = CodePushUpdateUtils.getHashForBinaryContents(reactApplicationContext, this.mCodePush.isDebugMode());
        this.mClientUniqueId = Settings.Secure.getString(reactApplicationContext.getContentResolver(), "android_id");
    }

    private void clearLifecycleEventListener() {
        if (this.mLifecycleEventListener != null) {
            getReactApplicationContext().removeLifecycleEventListener(this.mLifecycleEventListener);
            this.mLifecycleEventListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadBundle() {
        clearLifecycleEventListener();
        try {
            this.mCodePush.clearDebugCacheIfNeeded(resolveInstanceManager());
        } catch (Exception e) {
            this.mCodePush.clearDebugCacheIfNeeded(null);
        }
        try {
            final ReactInstanceManager resolveInstanceManager = resolveInstanceManager();
            if (resolveInstanceManager == null) {
                return;
            }
            setJSBundle(resolveInstanceManager, this.mCodePush.getJSBundleFileInternal(this.mCodePush.getAssetsBundleFileName()));
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.microsoft.codepush.react.CodePushNativeModule.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        resolveInstanceManager.recreateReactContextInBackground();
                        CodePushNativeModule.this.mCodePush.initializeUpdateAfterRestart();
                    } catch (Exception e2) {
                        CodePushNativeModule.this.loadBundleLegacy();
                    }
                }
            });
        } catch (Exception e2) {
            CodePushUtils.log("Failed to load the bundle, falling back to restarting the Activity (if it exists). " + e2.getMessage());
            loadBundleLegacy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadBundleLegacy() {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        this.mCodePush.invalidateCurrentInstance();
        currentActivity.runOnUiThread(new Runnable() { // from class: com.microsoft.codepush.react.CodePushNativeModule.1
            @Override // java.lang.Runnable
            public void run() {
                currentActivity.recreate();
            }
        });
    }

    private void resetReactRootViews(ReactInstanceManager reactInstanceManager) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = reactInstanceManager.getClass().getDeclaredField("mAttachedRootViews");
        declaredField.setAccessible(true);
        List<ReactRootView> list = (List) declaredField.get(reactInstanceManager);
        for (ReactRootView reactRootView : list) {
            reactRootView.removeAllViews();
            reactRootView.setId(-1);
        }
        declaredField.set(reactInstanceManager, list);
    }

    private ReactInstanceManager resolveInstanceManager() throws NoSuchFieldException, IllegalAccessException {
        ReactInstanceManager reactInstanceManager = CodePush.getReactInstanceManager();
        if (reactInstanceManager != null) {
            return reactInstanceManager;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return null;
        }
        return currentActivity.getApplication().getReactNativeHost().getReactInstanceManager();
    }

    private void setJSBundle(ReactInstanceManager reactInstanceManager, String str) throws IllegalAccessException {
        try {
            JSBundleLoader createAssetLoader = str.toLowerCase().startsWith(CodePushConstants.ASSETS_BUNDLE_PREFIX) ? JSBundleLoader.createAssetLoader(getReactApplicationContext(), str, false) : JSBundleLoader.createFileLoader(str);
            Field declaredField = reactInstanceManager.getClass().getDeclaredField("mBundleLoader");
            declaredField.setAccessible(true);
            declaredField.set(reactInstanceManager, createAssetLoader);
        } catch (Exception e) {
            CodePushUtils.log("Unable to set JSBundle - CodePush may not support this version of React Native");
            throw new IllegalAccessException("Could not setJSBundle");
        }
    }

    @ReactMethod
    public void clearUpdates() {
        CodePushUtils.log("Clearing updates.");
        this.mCodePush.clearUpdates();
    }

    @ReactMethod
    public void downloadAndReplaceCurrentBundle(String str) {
        try {
            CodePush codePush = this.mCodePush;
            if (CodePush.isUsingTestConfiguration()) {
                try {
                    this.mUpdateManager.downloadAndReplaceCurrentBundle(str, this.mCodePush.getAssetsBundleFileName());
                } catch (IOException e) {
                    throw new CodePushUnknownException("Unable to replace current bundle", e);
                }
            }
        } catch (CodePushMalformedDataException | CodePushUnknownException e2) {
            CodePushUtils.log(e2);
        }
    }

    @ReactMethod
    public void downloadUpdate(final ReadableMap readableMap, final boolean z, final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.microsoft.codepush.react.CodePushNativeModule.3

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.microsoft.codepush.react.CodePushNativeModule$3$1, reason: invalid class name */
            /* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushNativeModule$3$1.class */
            public class AnonymousClass1 implements DownloadProgressCallback {
                private boolean hasScheduledNextFrame = false;
                private DownloadProgress latestDownloadProgress = null;

                AnonymousClass1() {
                }

                @Override // com.microsoft.codepush.react.DownloadProgressCallback
                public void call(DownloadProgress downloadProgress) {
                    if (z) {
                        this.latestDownloadProgress = downloadProgress;
                        if (this.latestDownloadProgress.isCompleted()) {
                            dispatchDownloadProgressEvent();
                        } else {
                            if (this.hasScheduledNextFrame) {
                                return;
                            }
                            this.hasScheduledNextFrame = true;
                            CodePushNativeModule.this.getReactApplicationContext().runOnUiQueueThread(new Runnable() { // from class: com.microsoft.codepush.react.CodePushNativeModule.3.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, new ChoreographerCompat.FrameCallback() { // from class: com.microsoft.codepush.react.CodePushNativeModule.3.1.1.1
                                        public void doFrame(long j) {
                                            if (!AnonymousClass1.this.latestDownloadProgress.isCompleted()) {
                                                AnonymousClass1.this.dispatchDownloadProgressEvent();
                                            }
                                            AnonymousClass1.this.hasScheduledNextFrame = false;
                                        }
                                    });
                                }
                            });
                        }
                    }
                }

                public void dispatchDownloadProgressEvent() {
                    CodePushNativeModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(CodePushConstants.DOWNLOAD_PROGRESS_EVENT_NAME, this.latestDownloadProgress.createWritableMap());
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                try {
                    JSONObject convertReadableToJsonObject = CodePushUtils.convertReadableToJsonObject(readableMap);
                    CodePushUtils.setJSONValueForKey(convertReadableToJsonObject, CodePushConstants.BINARY_MODIFIED_TIME_KEY, "" + CodePushNativeModule.this.mCodePush.getBinaryResourcesModifiedTime());
                    CodePushNativeModule.this.mUpdateManager.downloadPackage(convertReadableToJsonObject, CodePushNativeModule.this.mCodePush.getAssetsBundleFileName(), new AnonymousClass1(), CodePushNativeModule.this.mCodePush.getPublicKey());
                    promise.resolve(CodePushUtils.convertJsonObjectToWritable(CodePushNativeModule.this.mUpdateManager.getPackage(CodePushUtils.tryGetString(readableMap, "packageHash"))));
                    return null;
                } catch (CodePushInvalidUpdateException e) {
                    CodePushUtils.log(e);
                    CodePushNativeModule.this.mSettingsManager.saveFailedUpdate(CodePushUtils.convertReadableToJsonObject(readableMap));
                    promise.reject(e);
                    return null;
                } catch (CodePushUnknownException e2) {
                    e = e2;
                    CodePushUtils.log(e);
                    promise.reject(e);
                    return null;
                } catch (IOException e3) {
                    e = e3;
                    CodePushUtils.log(e);
                    promise.reject(e);
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getConfiguration(Promise promise) {
        try {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("appVersion", this.mCodePush.getAppVersion());
            createMap.putString("clientUniqueId", this.mClientUniqueId);
            createMap.putString("deploymentKey", this.mCodePush.getDeploymentKey());
            createMap.putString("serverUrl", this.mCodePush.getServerUrl());
            if (this.mBinaryContentsHash != null) {
                createMap.putString("packageHash", this.mBinaryContentsHash);
            }
            promise.resolve(createMap);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log(e);
            promise.reject(e);
        }
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("codePushInstallModeImmediate", Integer.valueOf(CodePushInstallMode.IMMEDIATE.getValue()));
        hashMap.put("codePushInstallModeOnNextRestart", Integer.valueOf(CodePushInstallMode.ON_NEXT_RESTART.getValue()));
        hashMap.put("codePushInstallModeOnNextResume", Integer.valueOf(CodePushInstallMode.ON_NEXT_RESUME.getValue()));
        hashMap.put("codePushInstallModeOnNextSuspend", Integer.valueOf(CodePushInstallMode.ON_NEXT_SUSPEND.getValue()));
        hashMap.put("codePushUpdateStateRunning", Integer.valueOf(CodePushUpdateState.RUNNING.getValue()));
        hashMap.put("codePushUpdateStatePending", Integer.valueOf(CodePushUpdateState.PENDING.getValue()));
        hashMap.put("codePushUpdateStateLatest", Integer.valueOf(CodePushUpdateState.LATEST.getValue()));
        return hashMap;
    }

    @ReactMethod
    public void getLatestRollbackInfo(Promise promise) {
        try {
            JSONObject latestRollbackInfo = this.mSettingsManager.getLatestRollbackInfo();
            if (latestRollbackInfo != null) {
                promise.resolve(CodePushUtils.convertJsonObjectToWritable(latestRollbackInfo));
            } else {
                promise.resolve((Object) null);
            }
        } catch (CodePushUnknownException e) {
            CodePushUtils.log(e);
            promise.reject(e);
        }
    }

    public String getName() {
        return "CodePush";
    }

    @ReactMethod
    public void getNewStatusReport(final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.microsoft.codepush.react.CodePushNativeModule.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                WritableMap updateReport;
                try {
                    if (CodePushNativeModule.this.mCodePush.needToReportRollback()) {
                        CodePushNativeModule.this.mCodePush.setNeedToReportRollback(false);
                        JSONArray failedUpdates = CodePushNativeModule.this.mSettingsManager.getFailedUpdates();
                        if (failedUpdates != null && failedUpdates.length() > 0) {
                            try {
                                WritableMap rollbackReport = CodePushNativeModule.this.mTelemetryManager.getRollbackReport(CodePushUtils.convertJsonObjectToWritable(failedUpdates.getJSONObject(failedUpdates.length() - 1)));
                                if (rollbackReport != null) {
                                    promise.resolve(rollbackReport);
                                    return null;
                                }
                            } catch (JSONException e) {
                                throw new CodePushUnknownException("Unable to read failed updates information stored in SharedPreferences.", e);
                            }
                        }
                    } else if (CodePushNativeModule.this.mCodePush.didUpdate()) {
                        JSONObject currentPackage = CodePushNativeModule.this.mUpdateManager.getCurrentPackage();
                        if (currentPackage != null && (updateReport = CodePushNativeModule.this.mTelemetryManager.getUpdateReport(CodePushUtils.convertJsonObjectToWritable(currentPackage))) != null) {
                            promise.resolve(updateReport);
                            return null;
                        }
                    } else if (CodePushNativeModule.this.mCodePush.isRunningBinaryVersion()) {
                        WritableMap binaryUpdateReport = CodePushNativeModule.this.mTelemetryManager.getBinaryUpdateReport(CodePushNativeModule.this.mCodePush.getAppVersion());
                        if (binaryUpdateReport != null) {
                            promise.resolve(binaryUpdateReport);
                            return null;
                        }
                    } else {
                        WritableMap retryStatusReport = CodePushNativeModule.this.mTelemetryManager.getRetryStatusReport();
                        if (retryStatusReport != null) {
                            promise.resolve(retryStatusReport);
                            return null;
                        }
                    }
                    promise.resolve("");
                    return null;
                } catch (CodePushUnknownException e2) {
                    CodePushUtils.log(e2);
                    promise.reject(e2);
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getUpdateMetadata(final int i, final Promise promise) {
        new AsyncTask<Void, Void, Void>() { // from class: com.microsoft.codepush.react.CodePushNativeModule.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                try {
                    JSONObject currentPackage = CodePushNativeModule.this.mUpdateManager.getCurrentPackage();
                    if (currentPackage == null) {
                        promise.resolve((Object) null);
                        return null;
                    }
                    Boolean bool = false;
                    if (currentPackage.has("packageHash")) {
                        bool = Boolean.valueOf(CodePushNativeModule.this.mSettingsManager.isPendingUpdate(currentPackage.optString("packageHash", null)));
                    }
                    if (i == CodePushUpdateState.PENDING.getValue() && !bool.booleanValue()) {
                        promise.resolve((Object) null);
                        return null;
                    }
                    if (i != CodePushUpdateState.RUNNING.getValue() || !bool.booleanValue()) {
                        if (CodePushNativeModule.this.mCodePush.isRunningBinaryVersion()) {
                            CodePushUtils.setJSONValueForKey(currentPackage, "_isDebugOnly", true);
                        }
                        CodePushUtils.setJSONValueForKey(currentPackage, "isPending", bool);
                        promise.resolve(CodePushUtils.convertJsonObjectToWritable(currentPackage));
                        return null;
                    }
                    JSONObject previousPackage = CodePushNativeModule.this.mUpdateManager.getPreviousPackage();
                    if (previousPackage == null) {
                        promise.resolve((Object) null);
                        return null;
                    }
                    promise.resolve(CodePushUtils.convertJsonObjectToWritable(previousPackage));
                    return null;
                } catch (CodePushMalformedDataException e) {
                    CodePushUtils.log(e.getMessage());
                    CodePushNativeModule.this.clearUpdates();
                    promise.resolve((Object) null);
                    return null;
                } catch (CodePushUnknownException e2) {
                    CodePushUtils.log(e2);
                    promise.reject(e2);
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void installUpdate(ReadableMap readableMap, int i, int i2, Promise promise) {
        new AsyncTaskC01626(readableMap, i, i2, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void isFailedUpdate(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(this.mSettingsManager.isFailedHash(str)));
        } catch (CodePushUnknownException e) {
            CodePushUtils.log(e);
            promise.reject(e);
        }
    }

    @ReactMethod
    public void isFirstRun(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(this.mCodePush.didUpdate() && str != null && str.length() > 0 && str.equals(this.mUpdateManager.getCurrentPackageHash())));
        } catch (CodePushUnknownException e) {
            CodePushUtils.log(e);
            promise.reject(e);
        }
    }

    @ReactMethod
    public void notifyApplicationReady(Promise promise) {
        try {
            this.mSettingsManager.removePendingUpdate();
            promise.resolve("");
        } catch (CodePushUnknownException e) {
            CodePushUtils.log(e);
            promise.reject(e);
        }
    }

    @ReactMethod
    public void recordStatusReported(ReadableMap readableMap) {
        try {
            this.mTelemetryManager.recordStatusReported(readableMap);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log(e);
        }
    }

    @ReactMethod
    public void restartApp(boolean z, Promise promise) {
        if (z) {
            try {
                if (!this.mSettingsManager.isPendingUpdate(null)) {
                    promise.resolve(false);
                    return;
                }
            } catch (CodePushUnknownException e) {
                CodePushUtils.log(e);
                promise.reject(e);
                return;
            }
        }
        loadBundle();
        promise.resolve(true);
    }

    @ReactMethod
    public void saveStatusReportForRetry(ReadableMap readableMap) {
        try {
            this.mTelemetryManager.saveStatusReportForRetry(readableMap);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log(e);
        }
    }

    @ReactMethod
    public void setLatestRollbackInfo(String str, Promise promise) {
        try {
            this.mSettingsManager.setLatestRollbackInfo(str);
            promise.resolve((Object) null);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log(e);
            promise.reject(e);
        }
    }
}
