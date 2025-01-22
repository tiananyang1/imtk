package com.wei.android.lib.fingerprintidentify.impl;

import android.content.Context;
import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;
import com.samsung.android.sdk.pass.SpassInvalidStateException;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

/* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/impl/SamsungFingerprint.class */
public class SamsungFingerprint extends BaseFingerprint {
    private int mResultCode;
    private SpassFingerprint mSpassFingerprint;

    public SamsungFingerprint(Context context, BaseFingerprint.FingerprintIdentifyExceptionListener fingerprintIdentifyExceptionListener) {
        super(context, fingerprintIdentifyExceptionListener);
        this.mResultCode = -1;
        try {
            Spass spass = new Spass();
            spass.initialize(this.mContext);
            this.mSpassFingerprint = new SpassFingerprint(this.mContext);
            setHardwareEnable(spass.isFeatureEnabled(0));
            setRegisteredFingerprint(this.mSpassFingerprint.hasRegisteredFinger());
        } catch (Throwable th) {
            onCatchException(th);
        }
    }

    @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint
    protected void doCancelIdentify() {
        runOnUiThread(new Runnable() { // from class: com.wei.android.lib.fingerprintidentify.impl.SamsungFingerprint.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (SamsungFingerprint.this.mSpassFingerprint != null) {
                        SamsungFingerprint.this.mSpassFingerprint.cancelIdentify();
                    }
                } catch (Throwable th) {
                    SamsungFingerprint.this.onCatchException(th);
                }
            }
        });
    }

    @Override // com.wei.android.lib.fingerprintidentify.base.BaseFingerprint
    protected void doIdentify() {
        runOnUiThread(new Runnable() { // from class: com.wei.android.lib.fingerprintidentify.impl.SamsungFingerprint.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SamsungFingerprint.this.mSpassFingerprint.startIdentify(new SpassFingerprint.IdentifyListener() { // from class: com.wei.android.lib.fingerprintidentify.impl.SamsungFingerprint.1.1
                        @Override // com.samsung.android.sdk.pass.SpassFingerprint.IdentifyListener
                        public void onCompleted() {
                            int i = SamsungFingerprint.this.mResultCode;
                            if (i != 0) {
                                if (i != 4 && i != 16 && i != 51) {
                                    if (i != 100) {
                                        if (i != 7) {
                                            if (i == 8) {
                                                return;
                                            }
                                            if (i != 9 && i != 12 && i != 13) {
                                                SamsungFingerprint.this.onFailed(false);
                                                return;
                                            }
                                        }
                                    }
                                }
                                SamsungFingerprint.this.onNotMatch();
                                return;
                            }
                            SamsungFingerprint.this.onSucceed();
                        }

                        @Override // com.samsung.android.sdk.pass.SpassFingerprint.IdentifyListener
                        public void onFinished(int i) {
                            SamsungFingerprint.this.mResultCode = i;
                        }

                        @Override // com.samsung.android.sdk.pass.SpassFingerprint.IdentifyListener
                        public void onReady() {
                        }

                        @Override // com.samsung.android.sdk.pass.SpassFingerprint.IdentifyListener
                        public void onStarted() {
                        }
                    });
                } catch (Throwable th) {
                    if (!(th instanceof SpassInvalidStateException)) {
                        SamsungFingerprint.this.onCatchException(th);
                        SamsungFingerprint.this.onFailed(false);
                    } else if (((SpassInvalidStateException) th).getType() == 1) {
                        SamsungFingerprint.this.onFailed(true);
                    } else {
                        SamsungFingerprint.this.onCatchException(th);
                        SamsungFingerprint.this.onFailed(false);
                    }
                }
            }
        });
    }
}
