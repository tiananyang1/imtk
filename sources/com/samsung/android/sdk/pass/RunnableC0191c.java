package com.samsung.android.sdk.pass;

import com.samsung.android.fingerprint.FingerprintEvent;
import com.samsung.android.sdk.pass.SpassFingerprint;

/* renamed from: com.samsung.android.sdk.pass.c */
/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/c.class */
final class RunnableC0191c implements Runnable {

    /* renamed from: a */
    private /* synthetic */ SpassFingerprint.C0187b f169a;

    /* renamed from: b */
    private final /* synthetic */ FingerprintEvent f170b;

    /* renamed from: c */
    private final /* synthetic */ SpassFingerprint.IdentifyListener f171c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0191c(SpassFingerprint.C0187b c0187b, FingerprintEvent fingerprintEvent, SpassFingerprint.IdentifyListener identifyListener) {
        this.f169a = c0187b;
        this.f170b = fingerprintEvent;
        this.f171c = identifyListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SpassFingerprint spassFingerprint;
        SpassFingerprint spassFingerprint2;
        SpassFingerprint spassFingerprint3;
        SpassFingerprint spassFingerprint4;
        boolean z;
        SpassFingerprint spassFingerprint5;
        SpassFingerprint spassFingerprint6;
        SpassFingerprint spassFingerprint7;
        SpassFingerprint unused;
        switch (this.f170b.eventId) {
            case 11:
                this.f171c.onReady();
                return;
            case 12:
                this.f171c.onStarted();
                return;
            case 13:
                spassFingerprint = SpassFingerprint.this;
                spassFingerprint.f138c = this.f170b.getFingerIndex();
                if (this.f170b.eventStatus == 12) {
                    spassFingerprint7 = SpassFingerprint.this;
                    spassFingerprint7.f139d = this.f170b.getImageQualityFeedback();
                }
                SpassFingerprint.IdentifyListener identifyListener = this.f171c;
                unused = SpassFingerprint.this;
                identifyListener.onFinished(SpassFingerprint.m14a(this.f170b.eventStatus));
                spassFingerprint2 = SpassFingerprint.this;
                spassFingerprint2.f138c = -1;
                spassFingerprint3 = SpassFingerprint.this;
                spassFingerprint3.f139d = null;
                spassFingerprint4 = SpassFingerprint.this;
                z = spassFingerprint4.f151p;
                if (z) {
                    return;
                }
                spassFingerprint5 = SpassFingerprint.this;
                spassFingerprint5.f151p = true;
                try {
                    spassFingerprint6 = SpassFingerprint.this;
                    SpassFingerprint.m19b(spassFingerprint6, "IdentifyListener.onFinished");
                    return;
                } catch (SecurityException e) {
                    throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
                }
            default:
                return;
        }
    }
}
