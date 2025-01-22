package com.samsung.android.sdk.pass;

import android.content.DialogInterface;
import com.samsung.android.sdk.pass.SpassFingerprint;

/* renamed from: com.samsung.android.sdk.pass.a */
/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/a.class */
final class DialogInterfaceOnDismissListenerC0189a implements DialogInterface.OnDismissListener {

    /* renamed from: a */
    private final /* synthetic */ SpassFingerprint.C0188c f167a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterfaceOnDismissListenerC0189a(SpassFingerprint.C0188c c0188c) {
        this.f167a = c0188c;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.f167a.m44a();
    }
}
