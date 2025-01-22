package com.wei.android.lib.fingerprintidentify.aosp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.CancellationSignal;
import com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompatApi23;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/aosp/FingerprintManagerCompat.class */
public final class FingerprintManagerCompat {
    private static final FingerprintManagerCompatImpl IMPL = new Api23FingerprintManagerCompatImpl();
    private Context mContext;

    /* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/aosp/FingerprintManagerCompat$Api23FingerprintManagerCompatImpl.class */
    private static class Api23FingerprintManagerCompatImpl implements FingerprintManagerCompatImpl {
        static CryptoObject unwrapCryptoObject(FingerprintManagerCompatApi23.CryptoObject cryptoObject) {
            CryptoObject cryptoObject2 = null;
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                cryptoObject2 = new CryptoObject(cryptoObject.getMac());
            }
            return cryptoObject2;
        }

        private static FingerprintManagerCompatApi23.AuthenticationCallback wrapCallback(final AuthenticationCallback authenticationCallback) {
            return new FingerprintManagerCompatApi23.AuthenticationCallback() { // from class: com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompat.Api23FingerprintManagerCompatImpl.1
                @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompatApi23.AuthenticationCallback
                public void onAuthenticationError(int i, CharSequence charSequence) {
                    AuthenticationCallback.this.onAuthenticationError(i, charSequence);
                }

                @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompatApi23.AuthenticationCallback
                public void onAuthenticationFailed() {
                    AuthenticationCallback.this.onAuthenticationFailed();
                }

                @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompatApi23.AuthenticationCallback
                public void onAuthenticationHelp(int i, CharSequence charSequence) {
                    AuthenticationCallback.this.onAuthenticationHelp(i, charSequence);
                }

                @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompatApi23.AuthenticationCallback
                public void onAuthenticationSucceeded(FingerprintManagerCompatApi23.AuthenticationResultInternal authenticationResultInternal) {
                    AuthenticationCallback.this.onAuthenticationSucceeded(new AuthenticationResult(Api23FingerprintManagerCompatImpl.unwrapCryptoObject(authenticationResultInternal.getCryptoObject())));
                }
            };
        }

        private static FingerprintManagerCompatApi23.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
            FingerprintManagerCompatApi23.CryptoObject cryptoObject2 = null;
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                cryptoObject2 = new FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getMac());
            }
            return cryptoObject2;
        }

        @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompat.FingerprintManagerCompatImpl
        public void authenticate(Context context, CryptoObject cryptoObject, int i, CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler) {
            FingerprintManagerCompatApi23.authenticate(context, wrapCryptoObject(cryptoObject), i, cancellationSignal != null ? cancellationSignal.getCancellationSignalObject() : null, wrapCallback(authenticationCallback), handler);
        }

        @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompat.FingerprintManagerCompatImpl
        public boolean hasEnrolledFingerprints(Context context) {
            return FingerprintManagerCompatApi23.hasEnrolledFingerprints(context);
        }

        @Override // com.wei.android.lib.fingerprintidentify.aosp.FingerprintManagerCompat.FingerprintManagerCompatImpl
        public boolean isHardwareDetected(Context context) {
            return FingerprintManagerCompatApi23.isHardwareDetected(context);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/aosp/FingerprintManagerCompat$AuthenticationCallback.class */
    public static abstract class AuthenticationCallback {
        public void onAuthenticationError(int i, CharSequence charSequence) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationHelp(int i, CharSequence charSequence) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/aosp/FingerprintManagerCompat$AuthenticationResult.class */
    public static final class AuthenticationResult {
        private CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject cryptoObject) {
            this.mCryptoObject = cryptoObject;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/aosp/FingerprintManagerCompat$CryptoObject.class */
    public static class CryptoObject {
        private final Cipher mCipher;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(Signature signature) {
            this.mSignature = signature;
            this.mCipher = null;
            this.mMac = null;
        }

        public CryptoObject(Cipher cipher) {
            this.mCipher = cipher;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(Mac mac) {
            this.mMac = mac;
            this.mCipher = null;
            this.mSignature = null;
        }

        public Cipher getCipher() {
            return this.mCipher;
        }

        public Mac getMac() {
            return this.mMac;
        }

        public Signature getSignature() {
            return this.mSignature;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/wei/android/lib/fingerprintidentify/aosp/FingerprintManagerCompat$FingerprintManagerCompatImpl.class */
    private interface FingerprintManagerCompatImpl {
        void authenticate(Context context, CryptoObject cryptoObject, int i, CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler);

        boolean hasEnrolledFingerprints(Context context);

        boolean isHardwareDetected(Context context);
    }

    private FingerprintManagerCompat(Context context) {
        this.mContext = context;
    }

    public static FingerprintManagerCompat from(Context context) {
        return new FingerprintManagerCompat(context);
    }

    public void authenticate(@Nullable CryptoObject cryptoObject, int i, @Nullable CancellationSignal cancellationSignal, @NonNull AuthenticationCallback authenticationCallback, @Nullable Handler handler) {
        IMPL.authenticate(this.mContext, cryptoObject, i, cancellationSignal, authenticationCallback, handler);
    }

    public boolean hasEnrolledFingerprints() {
        return IMPL.hasEnrolledFingerprints(this.mContext);
    }

    public boolean isHardwareDetected() {
        return IMPL.isHardwareDetected(this.mContext);
    }
}
