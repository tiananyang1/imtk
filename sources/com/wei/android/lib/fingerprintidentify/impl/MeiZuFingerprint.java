package com.wei.android.lib.fingerprintidentify.impl;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.fingerprints.service.FingerprintManager;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

/* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/impl/MeiZuFingerprint.class */
public class MeiZuFingerprint extends BaseFingerprint {
    private FingerprintManager mMeiZuFingerprintManager;

    public MeiZuFingerprint(Context context, BaseFingerprint.FingerprintIdentifyExceptionListener fingerprintIdentifyExceptionListener) {
        super(context, fingerprintIdentifyExceptionListener);
        try {
            this.mMeiZuFingerprintManager = FingerprintManager.open();
            if (this.mMeiZuFingerprintManager != null) {
                setHardwareEnable(isMeiZuDevice(Build.MANUFACTURER));
                int[] ids = this.mMeiZuFingerprintManager.getIds();
                setRegisteredFingerprint(ids != null && ids.length > 0);
            }
        } catch (Throwable th) {
            onCatchException(th);
        }
        releaseMBack();
    }

    private boolean isMeiZuDevice(String str) {
        return !TextUtils.isEmpty(str) && str.toUpperCase().contains("MEIZU");
    }

    private void releaseMBack() {
        try {
            if (this.mMeiZuFingerprintManager != null) {
                this.mMeiZuFingerprintManager.release();
            }
        } catch (Throwable th) {
            onCatchException(th);
        }
    }

    @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint
    protected void doCancelIdentify() {
        releaseMBack();
    }

    @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint
    protected void doIdentify() {
        try {
            this.mMeiZuFingerprintManager = FingerprintManager.open();
            this.mMeiZuFingerprintManager.startIdentify(new FingerprintManager.IdentifyCallback() { // from class: com.wei.android.lib.fingerprintidentify.impl.MeiZuFingerprint.1
                public void onIdentified(int i, boolean z) {
                    MeiZuFingerprint.this.onSucceed();
                }

                public void onNoMatch() {
                    MeiZuFingerprint.this.onNotMatch();
                }
            }, this.mMeiZuFingerprintManager.getIds());
        } catch (Throwable th) {
            onCatchException(th);
            onFailed(false);
        }
    }
}
