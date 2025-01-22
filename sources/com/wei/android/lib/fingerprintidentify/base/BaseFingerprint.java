package com.wei.android.lib.fingerprintidentify.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/base/BaseFingerprint.class */
public abstract class BaseFingerprint {
    protected Context mContext;
    private FingerprintIdentifyExceptionListener mExceptionListener;
    private FingerprintIdentifyListener mIdentifyListener;
    private int mNumberOfFailures = 0;
    private int mMaxAvailableTimes = 3;
    private boolean mIsHardwareEnable = false;
    private boolean mIsRegisteredFingerprint = false;
    private boolean mIsCalledStartIdentify = false;
    private boolean mIsCanceledIdentify = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/base/BaseFingerprint$FingerprintIdentifyExceptionListener.class */
    public interface FingerprintIdentifyExceptionListener {
        void onCatchException(Throwable th);
    }

    /* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/base/BaseFingerprint$FingerprintIdentifyListener.class */
    public interface FingerprintIdentifyListener {
        void onFailed(boolean z);

        void onNotMatch(int i);

        void onStartFailedByDeviceLocked();

        void onSucceed();
    }

    public BaseFingerprint(Context context, FingerprintIdentifyExceptionListener fingerprintIdentifyExceptionListener) {
        this.mContext = context;
        this.mExceptionListener = fingerprintIdentifyExceptionListener;
    }

    public void cancelIdentify() {
        this.mIsCanceledIdentify = true;
        doCancelIdentify();
    }

    protected abstract void doCancelIdentify();

    protected abstract void doIdentify();

    public boolean isEnable() {
        return this.mIsHardwareEnable && this.mIsRegisteredFingerprint;
    }

    public boolean isHardwareEnable() {
        return this.mIsHardwareEnable;
    }

    public boolean isRegisteredFingerprint() {
        return this.mIsRegisteredFingerprint;
    }

    protected boolean needToCallDoIdentifyAgainAfterNotMatch() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCatchException(Throwable th) {
        FingerprintIdentifyExceptionListener fingerprintIdentifyExceptionListener = this.mExceptionListener;
        if (fingerprintIdentifyExceptionListener == null || th == null) {
            return;
        }
        fingerprintIdentifyExceptionListener.onCatchException(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onFailed(final boolean z) {
        if (this.mIsCanceledIdentify) {
            return;
        }
        boolean z2 = z && this.mNumberOfFailures == 0;
        this.mNumberOfFailures = this.mMaxAvailableTimes;
        if (this.mIdentifyListener != null) {
            final boolean z3 = z2;
            runOnUiThread(new Runnable() { // from class: com.wei.android.lib.fingerprintidentify.base.BaseFingerprint.3
                @Override // java.lang.Runnable
                public void run() {
                    if (z3) {
                        BaseFingerprint.this.mIdentifyListener.onStartFailedByDeviceLocked();
                    } else {
                        BaseFingerprint.this.mIdentifyListener.onFailed(z);
                    }
                }
            });
        }
        cancelIdentify();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onNotMatch() {
        if (this.mIsCanceledIdentify) {
            return;
        }
        int i = this.mNumberOfFailures + 1;
        this.mNumberOfFailures = i;
        int i2 = this.mMaxAvailableTimes;
        if (i >= i2) {
            onFailed(false);
            return;
        }
        if (this.mIdentifyListener != null) {
            final int i3 = i2 - this.mNumberOfFailures;
            runOnUiThread(new Runnable() { // from class: com.wei.android.lib.fingerprintidentify.base.BaseFingerprint.2
                @Override // java.lang.Runnable
                public void run() {
                    BaseFingerprint.this.mIdentifyListener.onNotMatch(i3);
                }
            });
        }
        if (needToCallDoIdentifyAgainAfterNotMatch()) {
            doIdentify();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSucceed() {
        if (this.mIsCanceledIdentify) {
            return;
        }
        this.mNumberOfFailures = this.mMaxAvailableTimes;
        if (this.mIdentifyListener != null) {
            runOnUiThread(new Runnable() { // from class: com.wei.android.lib.fingerprintidentify.base.BaseFingerprint.1
                @Override // java.lang.Runnable
                public void run() {
                    BaseFingerprint.this.mIdentifyListener.onSucceed();
                }
            });
        }
        cancelIdentify();
    }

    public void resumeIdentify() {
        if (!this.mIsCalledStartIdentify || this.mIdentifyListener == null || this.mNumberOfFailures >= this.mMaxAvailableTimes) {
            return;
        }
        this.mIsCanceledIdentify = false;
        doIdentify();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHardwareEnable(boolean z) {
        this.mIsHardwareEnable = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setRegisteredFingerprint(boolean z) {
        this.mIsRegisteredFingerprint = z;
    }

    public void startIdentify(int i, FingerprintIdentifyListener fingerprintIdentifyListener) {
        this.mMaxAvailableTimes = i;
        this.mIsCalledStartIdentify = true;
        this.mIdentifyListener = fingerprintIdentifyListener;
        this.mIsCanceledIdentify = false;
        this.mNumberOfFailures = 0;
        doIdentify();
    }
}
