package com.samsung.android.sdk.pass;

import android.app.Dialog;
import com.samsung.android.fingerprint.FingerprintEvent;
import com.samsung.android.sdk.pass.SpassFingerprint;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.samsung.android.sdk.pass.e */
/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/e.class */
public final class RunnableC0193e implements Runnable {

    /* renamed from: a */
    private /* synthetic */ SpassFingerprint.C0188c f174a;

    /* renamed from: b */
    private final /* synthetic */ FingerprintEvent f175b;

    /* renamed from: c */
    private final /* synthetic */ SpassFingerprint.IdentifyListener f176c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0193e(SpassFingerprint.C0188c c0188c, FingerprintEvent fingerprintEvent, SpassFingerprint.IdentifyListener identifyListener) {
        this.f174a = c0188c;
        this.f175b = fingerprintEvent;
        this.f176c = identifyListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SpassFingerprint spassFingerprint;
        SpassFingerprint spassFingerprint2;
        SpassFingerprint spassFingerprint3;
        SpassFingerprint spassFingerprint4;
        Dialog dialog;
        SpassFingerprint spassFingerprint5;
        boolean z;
        SpassFingerprint spassFingerprint6;
        SpassFingerprint spassFingerprint7;
        SpassFingerprint spassFingerprint8;
        SpassFingerprint spassFingerprint9;
        SpassFingerprint unused;
        if (this.f175b.eventId == 13) {
            spassFingerprint = SpassFingerprint.this;
            spassFingerprint.f138c = this.f175b.getFingerIndex();
            if (this.f175b.eventStatus == 12) {
                spassFingerprint9 = SpassFingerprint.this;
                spassFingerprint9.f139d = this.f175b.getImageQualityFeedback();
            }
            SpassFingerprint.IdentifyListener identifyListener = this.f176c;
            unused = SpassFingerprint.this;
            identifyListener.onFinished(SpassFingerprint.m14a(this.f175b.eventStatus));
            this.f176c.onCompleted();
            spassFingerprint2 = SpassFingerprint.this;
            spassFingerprint2.f138c = -1;
            spassFingerprint3 = SpassFingerprint.this;
            spassFingerprint3.f139d = null;
            spassFingerprint4 = SpassFingerprint.this;
            dialog = spassFingerprint4.f155u;
            if (dialog != null) {
                spassFingerprint8 = SpassFingerprint.this;
                spassFingerprint8.f155u = null;
            }
            spassFingerprint5 = SpassFingerprint.this;
            z = spassFingerprint5.f151p;
            if (z) {
                return;
            }
            spassFingerprint6 = SpassFingerprint.this;
            spassFingerprint6.f151p = true;
            try {
                spassFingerprint7 = SpassFingerprint.this;
                SpassFingerprint.m19b(spassFingerprint7, "IdentifyListener.onFinished");
            } catch (SecurityException e) {
                throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
            }
        }
    }
}
