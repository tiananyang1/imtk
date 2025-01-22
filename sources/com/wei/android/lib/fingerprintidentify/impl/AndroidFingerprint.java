package com.wei.android.lib.fingerprintidentify.impl;

import android.content.Context;
import android.os.Build;
import android.support.v4.os.CancellationSignal;
import com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompat;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

/* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/impl/AndroidFingerprint.class */
public class AndroidFingerprint extends BaseFingerprint {
    private CancellationSignal mCancellationSignal;
    private FingerprintManagerCompat mFingerprintManagerCompat;

    public AndroidFingerprint(Context context, BaseFingerprint.FingerprintIdentifyExceptionListener fingerprintIdentifyExceptionListener) {
        super(context, fingerprintIdentifyExceptionListener);
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        try {
            this.mFingerprintManagerCompat = FingerprintManagerCompat.from(this.mContext);
            setHardwareEnable(this.mFingerprintManagerCompat.isHardwareDetected());
            setRegisteredFingerprint(this.mFingerprintManagerCompat.hasEnrolledFingerprints());
        } catch (Throwable th) {
            onCatchException(th);
        }
    }

    @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint
    protected void doCancelIdentify() {
        try {
            if (this.mCancellationSignal != null) {
                this.mCancellationSignal.cancel();
            }
        } catch (Throwable th) {
            onCatchException(th);
        }
    }

    @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint
    protected void doIdentify() {
        try {
            this.mCancellationSignal = new CancellationSignal();
            this.mFingerprintManagerCompat.authenticate(null, 0, this.mCancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() { // from class: com.wei.android.lib.fingerprintidentify.impl.AndroidFingerprint.1
                @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationError(int i, CharSequence charSequence) {
                    super.onAuthenticationError(i, charSequence);
                    AndroidFingerprint.this.onFailed(i == 7);
                }

                @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    AndroidFingerprint.this.onNotMatch();
                }

                @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult authenticationResult) {
                    super.onAuthenticationSucceeded(authenticationResult);
                    AndroidFingerprint.this.onSucceed();
                }
            }, null);
        } catch (Throwable th) {
            onCatchException(th);
            onFailed(false);
        }
    }

    @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint
    protected boolean needToCallDoIdentifyAgainAfterNotMatch() {
        return false;
    }
}
