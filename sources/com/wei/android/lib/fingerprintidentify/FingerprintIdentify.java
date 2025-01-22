package com.wei.android.lib.fingerprintidentify;

import android.content.Context;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;
import com.wei.android.lib.fingerprintidentify.impl.AndroidFingerprint;
import com.wei.android.lib.fingerprintidentify.impl.MeiZuFingerprint;
import com.wei.android.lib.fingerprintidentify.impl.SamsungFingerprint;

/* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/FingerprintIdentify.class */
public class FingerprintIdentify {
    private BaseFingerprint mFingerprint;
    private BaseFingerprint mSubFingerprint;

    public FingerprintIdentify(Context context) {
        this(context, null);
    }

    public FingerprintIdentify(Context context, BaseFingerprint.FingerprintIdentifyExceptionListener fingerprintIdentifyExceptionListener) {
        AndroidFingerprint androidFingerprint = new AndroidFingerprint(context, fingerprintIdentifyExceptionListener);
        if (androidFingerprint.isHardwareEnable()) {
            this.mSubFingerprint = androidFingerprint;
            if (androidFingerprint.isRegisteredFingerprint()) {
                this.mFingerprint = androidFingerprint;
                return;
            }
        }
        SamsungFingerprint samsungFingerprint = new SamsungFingerprint(context, fingerprintIdentifyExceptionListener);
        if (samsungFingerprint.isHardwareEnable()) {
            this.mSubFingerprint = samsungFingerprint;
            if (samsungFingerprint.isRegisteredFingerprint()) {
                this.mFingerprint = samsungFingerprint;
                return;
            }
        }
        MeiZuFingerprint meiZuFingerprint = new MeiZuFingerprint(context, fingerprintIdentifyExceptionListener);
        if (meiZuFingerprint.isHardwareEnable()) {
            this.mSubFingerprint = meiZuFingerprint;
            if (meiZuFingerprint.isRegisteredFingerprint()) {
                this.mFingerprint = meiZuFingerprint;
            }
        }
    }

    public void cancelIdentify() {
        BaseFingerprint baseFingerprint = this.mFingerprint;
        if (baseFingerprint != null) {
            baseFingerprint.cancelIdentify();
        }
    }

    public boolean isFingerprintEnable() {
        BaseFingerprint baseFingerprint = this.mFingerprint;
        return baseFingerprint != null && baseFingerprint.isEnable();
    }

    public boolean isHardwareEnable() {
        if (isFingerprintEnable()) {
            return true;
        }
        BaseFingerprint baseFingerprint = this.mSubFingerprint;
        return baseFingerprint != null && baseFingerprint.isHardwareEnable();
    }

    public boolean isRegisteredFingerprint() {
        if (isFingerprintEnable()) {
            return true;
        }
        BaseFingerprint baseFingerprint = this.mSubFingerprint;
        return baseFingerprint != null && baseFingerprint.isRegisteredFingerprint();
    }

    public void resumeIdentify() {
        if (isFingerprintEnable()) {
            this.mFingerprint.resumeIdentify();
        }
    }

    public void startIdentify(int i, BaseFingerprint.FingerprintIdentifyListener fingerprintIdentifyListener) {
        if (isFingerprintEnable()) {
            this.mFingerprint.startIdentify(i, fingerprintIdentifyListener);
        }
    }
}
