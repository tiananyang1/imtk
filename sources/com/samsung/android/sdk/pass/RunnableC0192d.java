package com.samsung.android.sdk.pass;

import com.samsung.android.fingerprint.FingerprintEvent;
import com.samsung.android.sdk.pass.SpassFingerprint;

/* renamed from: com.samsung.android.sdk.pass.d */
/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/d.class */
final class RunnableC0192d implements Runnable {

    /* renamed from: a */
    private /* synthetic */ SpassFingerprint.C0188c f172a;

    /* renamed from: b */
    private final /* synthetic */ FingerprintEvent f173b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0192d(SpassFingerprint.C0188c c0188c, FingerprintEvent fingerprintEvent) {
        this.f172a = c0188c;
        this.f173b = fingerprintEvent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SpassFingerprint.IdentifyListener identifyListener;
        SpassFingerprint.IdentifyListener identifyListener2;
        SpassFingerprint.IdentifyListener identifyListener3;
        identifyListener = this.f172a.f163a;
        if (identifyListener != null) {
            int i = this.f173b.eventId;
            if (i == 11) {
                identifyListener2 = this.f172a.f163a;
                identifyListener2.onReady();
            } else {
                if (i != 12) {
                    return;
                }
                identifyListener3 = this.f172a.f163a;
                identifyListener3.onStarted();
            }
        }
    }
}
