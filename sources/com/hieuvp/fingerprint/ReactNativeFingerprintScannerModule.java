package com.hieuvp.fingerprint;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

@ReactModule(name = "ReactNativeFingerprintScanner")
/* loaded from: classes08-dex2jar.jar:com/hieuvp/fingerprint/ReactNativeFingerprintScannerModule.class */
public class ReactNativeFingerprintScannerModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    public static final int MAX_AVAILABLE_TIMES = Integer.MAX_VALUE;
    public static final String TYPE_FINGERPRINT = "Fingerprint";
    private FingerprintIdentify mFingerprintIdentify;
    private final ReactApplicationContext mReactContext;

    public ReactNativeFingerprintScannerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mReactContext = reactApplicationContext;
    }

    private String getErrorMessage() {
        if (!getFingerprintIdentify().isHardwareEnable()) {
            return "FingerprintScannerNotSupported";
        }
        if (!getFingerprintIdentify().isRegisteredFingerprint()) {
            return "FingerprintScannerNotEnrolled";
        }
        if (getFingerprintIdentify().isFingerprintEnable()) {
            return null;
        }
        return "FingerprintScannerNotAvailable";
    }

    private FingerprintIdentify getFingerprintIdentify() {
        FingerprintIdentify fingerprintIdentify = this.mFingerprintIdentify;
        if (fingerprintIdentify != null) {
            return fingerprintIdentify;
        }
        this.mReactContext.addLifecycleEventListener(this);
        this.mFingerprintIdentify = new FingerprintIdentify(getCurrentActivity(), new BaseFingerprint.FingerprintIdentifyExceptionListener() { // from class: com.hieuvp.fingerprint.ReactNativeFingerprintScannerModule.1
            @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint.FingerprintIdentifyExceptionListener
            public void onCatchException(Throwable th) {
                ReactNativeFingerprintScannerModule.this.mReactContext.removeLifecycleEventListener(ReactNativeFingerprintScannerModule.this);
            }
        });
        return this.mFingerprintIdentify;
    }

    @ReactMethod
    public void authenticate(final Promise promise) {
        String errorMessage = getErrorMessage();
        if (errorMessage != null) {
            promise.reject(errorMessage, TYPE_FINGERPRINT);
            release();
        } else {
            getFingerprintIdentify().resumeIdentify();
            getFingerprintIdentify().startIdentify(Integer.MAX_VALUE, new BaseFingerprint.FingerprintIdentifyListener() { // from class: com.hieuvp.fingerprint.ReactNativeFingerprintScannerModule.2
                @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint.FingerprintIdentifyListener
                public void onFailed(boolean z) {
                    if (z) {
                        promise.reject("AuthenticationFailed", "DeviceLocked");
                    } else {
                        promise.reject("AuthenticationFailed", ReactNativeFingerprintScannerModule.TYPE_FINGERPRINT);
                    }
                    ReactNativeFingerprintScannerModule.this.release();
                }

                @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint.FingerprintIdentifyListener
                public void onNotMatch(int i) {
                    ReactNativeFingerprintScannerModule.this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("FINGERPRINT_SCANNER_AUTHENTICATION", "AuthenticationNotMatch");
                }

                @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint.FingerprintIdentifyListener
                public void onStartFailedByDeviceLocked() {
                    promise.reject("AuthenticationFailed", "DeviceLocked");
                }

                @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint.FingerprintIdentifyListener
                public void onSucceed() {
                    promise.resolve(true);
                    ReactNativeFingerprintScannerModule.this.release();
                }
            });
        }
    }

    public String getName() {
        return "ReactNativeFingerprintScanner";
    }

    @ReactMethod
    public void isSensorAvailable(Promise promise) {
        String errorMessage = getErrorMessage();
        if (errorMessage != null) {
            promise.reject(errorMessage, TYPE_FINGERPRINT);
        } else {
            promise.resolve(TYPE_FINGERPRINT);
        }
    }

    public void onHostDestroy() {
        release();
    }

    public void onHostPause() {
    }

    public void onHostResume() {
    }

    @ReactMethod
    public void release() {
        getFingerprintIdentify().cancelIdentify();
        this.mFingerprintIdentify = null;
        this.mReactContext.removeLifecycleEventListener(this);
    }
}
