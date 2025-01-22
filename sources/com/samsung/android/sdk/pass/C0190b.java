package com.samsung.android.sdk.pass;

import com.samsung.android.fingerprint.FingerprintManager;
import com.samsung.android.sdk.pass.SpassFingerprint;

/* renamed from: com.samsung.android.sdk.pass.b */
/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/b.class */
final class C0190b implements FingerprintManager.EnrollFinishListener {

    /* renamed from: a */
    private final /* synthetic */ SpassFingerprint.RegisterListener f168a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0190b(SpassFingerprint.RegisterListener registerListener) {
        this.f168a = registerListener;
    }

    public final void onEnrollFinish() {
        this.f168a.onFinished();
    }
}
