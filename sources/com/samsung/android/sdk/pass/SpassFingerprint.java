package com.samsung.android.sdk.pass;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.samsung.android.fingerprint.FingerprintEvent;
import com.samsung.android.fingerprint.FingerprintIdentifyDialog;
import com.samsung.android.fingerprint.IFingerprintClient;
import com.samsung.android.sdk.pass.support.IFingerprintManagerProxy;
import com.samsung.android.sdk.pass.support.SdkSupporter;
import com.samsung.android.sdk.pass.support.p001v1.FingerprintManagerProxyFactory;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;

/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/SpassFingerprint.class */
public class SpassFingerprint {
    public static final String ACTION_FINGERPRINT_ADDED = "com.samsung.android.intent.action.FINGERPRINT_ADDED";
    public static final String ACTION_FINGERPRINT_REMOVED = "com.samsung.android.intent.action.FINGERPRINT_REMOVED";
    public static final String ACTION_FINGERPRINT_RESET = "com.samsung.android.intent.action.FINGERPRINT_RESET";
    public static final int STATUS_AUTHENTIFICATION_FAILED = 16;
    public static final int STATUS_AUTHENTIFICATION_PASSWORD_SUCCESS = 100;
    public static final int STATUS_AUTHENTIFICATION_SUCCESS = 0;
    public static final int STATUS_BUTTON_PRESSED = 9;
    public static final int STATUS_OPERATION_DENIED = 51;
    public static final int STATUS_QUALITY_FAILED = 12;
    public static final int STATUS_SENSOR_FAILED = 7;
    public static final int STATUS_TIMEOUT_FAILED = 4;
    public static final int STATUS_USER_CANCELLED = 8;
    public static final int STATUS_USER_CANCELLED_BY_TOUCH_OUTSIDE = 13;

    /* renamed from: q */
    private static String f135q = "sdk_version";

    /* renamed from: a */
    private IFingerprintManagerProxy f136a;

    /* renamed from: b */
    private Context f137b;

    /* renamed from: n */
    private int f149n;

    /* renamed from: o */
    private boolean f150o;

    /* renamed from: r */
    private boolean f152r;

    /* renamed from: s */
    private boolean f153s;

    /* renamed from: y */
    private Handler f159y;

    /* renamed from: c */
    private int f138c = -1;

    /* renamed from: d */
    private String f139d = null;

    /* renamed from: e */
    private ArrayList f140e = null;

    /* renamed from: f */
    private String f141f = null;

    /* renamed from: g */
    private int f142g = -1;

    /* renamed from: h */
    private String f143h = null;

    /* renamed from: i */
    private int f144i = -1;

    /* renamed from: j */
    private int[] f145j = null;

    /* renamed from: k */
    private boolean f146k = false;

    /* renamed from: l */
    private String f147l = null;

    /* renamed from: m */
    private String f148m = null;

    /* renamed from: p */
    private boolean f151p = false;

    /* renamed from: t */
    private IBinder f154t = null;

    /* renamed from: u */
    private Dialog f155u = null;

    /* renamed from: v */
    private IFingerprintClient f156v = null;

    /* renamed from: w */
    private Bundle f157w = null;

    /* renamed from: x */
    private IFingerprintClient f158x = null;

    /* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/SpassFingerprint$IdentifyListener.class */
    public interface IdentifyListener {
        void onCompleted();

        void onFinished(int i);

        void onReady();

        void onStarted();
    }

    /* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/SpassFingerprint$RegisterListener.class */
    public interface RegisterListener {
        void onFinished();
    }

    /* renamed from: com.samsung.android.sdk.pass.SpassFingerprint$a */
    /* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/SpassFingerprint$a.class */
    private static final class C0186a {

        /* renamed from: a */
        private Bundle f160a = new Bundle();

        public C0186a(String str) {
            if (str == null || str.length() <= 0) {
                return;
            }
            this.f160a.putString("appName", str);
        }

        /* renamed from: a */
        public final C0186a m37a() {
            String format = String.format("%d.%d.%d", 1, 2, 2);
            this.f160a.putString(SpassFingerprint.f135q, "Pass-v" + format);
            return this;
        }

        /* renamed from: a */
        public final C0186a m38a(int[] iArr) {
            if (iArr != null && iArr.length > 0) {
                this.f160a.putIntArray("request_template_index_list", iArr);
            }
            return this;
        }

        /* renamed from: b */
        public final Bundle m39b() {
            return this.f160a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.samsung.android.sdk.pass.SpassFingerprint$b */
    /* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/SpassFingerprint$b.class */
    public final class C0187b extends IFingerprintClient.Stub {

        /* renamed from: a */
        private IdentifyListener f161a;

        private C0187b(IdentifyListener identifyListener) {
            this.f161a = identifyListener;
        }

        /* synthetic */ C0187b(SpassFingerprint spassFingerprint, IdentifyListener identifyListener, byte b) {
            this(identifyListener);
        }

        /* renamed from: a */
        public final void m41a(IdentifyListener identifyListener) {
            this.f161a = identifyListener;
        }

        public final void onFingerprintEvent(FingerprintEvent fingerprintEvent) throws RemoteException {
            if (fingerprintEvent == null) {
                Log.w("SpassFingerprintSDK", "onFingerprintEvent: null event will be ignored!");
                return;
            }
            try {
                IdentifyListener identifyListener = this.f161a;
                if (identifyListener == null || SpassFingerprint.this.f159y == null) {
                    return;
                }
                SpassFingerprint.this.f159y.post(new RunnableC0191c(this, fingerprintEvent, identifyListener));
                int i = 13;
                if (SpassFingerprint.this.f150o) {
                    i = 16;
                }
                if (fingerprintEvent.eventId == i) {
                    Log.d("SpassFingerprintSDK", "mCompletedEventId: " + i);
                    if (SpassFingerprint.this.f154t != null && SpassFingerprint.this.f136a != null) {
                        SpassFingerprint.this.f136a.unregisterClient(SpassFingerprint.this.f154t);
                        SpassFingerprint.this.f154t = null;
                    }
                    if (SpassFingerprint.this.f156v != null) {
                        SpassFingerprint.this.f156v = null;
                    }
                    identifyListener.onCompleted();
                    this.f161a = null;
                }
            } catch (Exception e) {
                Log.w("SpassFingerprintSDK", "onFingerprintEvent: Error : " + e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.samsung.android.sdk.pass.SpassFingerprint$c */
    /* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/SpassFingerprint$c.class */
    public final class C0188c implements FingerprintIdentifyDialog.FingerprintListener {

        /* renamed from: a */
        private IdentifyListener f163a;

        /* renamed from: b */
        private FingerprintEvent f164b;

        private C0188c(IdentifyListener identifyListener) {
            this.f163a = identifyListener;
        }

        /* synthetic */ C0188c(SpassFingerprint spassFingerprint, IdentifyListener identifyListener, byte b) {
            this(identifyListener);
        }

        /* renamed from: a */
        public final void m44a() {
            FingerprintEvent fingerprintEvent = this.f164b;
            IdentifyListener identifyListener = this.f163a;
            if (fingerprintEvent == null || identifyListener == null || SpassFingerprint.this.f159y == null) {
                return;
            }
            SpassFingerprint.this.f159y.post(new RunnableC0193e(this, fingerprintEvent, identifyListener));
            this.f163a = null;
            this.f164b = null;
        }

        public final void onEvent(FingerprintEvent fingerprintEvent) {
            try {
                if (fingerprintEvent.eventId == 13 || SpassFingerprint.this.f159y == null) {
                    this.f164b = fingerprintEvent;
                } else {
                    SpassFingerprint.this.f159y.post(new RunnableC0192d(this, fingerprintEvent));
                }
            } catch (Exception e) {
                Log.w("SpassFingerprintSDK", "onFingerprintEvent: Error : " + e);
            }
        }
    }

    public SpassFingerprint(Context context) {
        this.f149n = 0;
        this.f150o = false;
        this.f152r = false;
        this.f153s = false;
        this.f137b = context;
        Context context2 = this.f137b;
        if (context2 == null) {
            throw new IllegalArgumentException("context is null.");
        }
        try {
            context2.getPackageManager();
            if (!this.f152r) {
                this.f153s = this.f137b.getPackageManager().hasSystemFeature("com.sec.feature.fingerprint_manager_service");
                this.f152r = true;
            }
            if (this.f153s) {
                try {
                    Class<?> cls = Class.forName(SdkSupporter.CLASSNAME_FINGERPRINT_MANAGER);
                    Method method = cls.getMethod("getInstance", Context.class);
                    Method method2 = cls.getMethod("getVersion", new Class[0]);
                    Object invoke = method.invoke(null, this.f137b);
                    if (invoke != null) {
                        this.f149n = ((Integer) method2.invoke(invoke, new Object[0])).intValue();
                    }
                } catch (Exception e) {
                    Log.w("SpassFingerprintSDK", "getVersion failed : " + e);
                }
                int i = this.f149n >>> 24;
                if ((i > 1 ? 1 : i) > 0) {
                    this.f136a = FingerprintManagerProxyFactory.create(this.f137b);
                }
                this.f159y = new Handler(context.getMainLooper());
            }
            SdkSupporter.copyStaticFields(this, SpassFingerprint.class, SdkSupporter.CLASSNAME_FINGERPRINT_MANAGER, "EVENT_IDENTIFY_");
            IFingerprintManagerProxy iFingerprintManagerProxy = this.f136a;
            if (iFingerprintManagerProxy != null) {
                try {
                    if (iFingerprintManagerProxy.getSensorType() == 2) {
                        this.f150o = true;
                    }
                } catch (Exception e2) {
                }
            }
        } catch (NullPointerException e3) {
            throw new IllegalArgumentException("context is not valid.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ int m14a(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 4;
        if (i != 4) {
            i2 = 51;
            if (i != 51) {
                i2 = 100;
                if (i != 100) {
                    i2 = 7;
                    if (i != 7) {
                        i2 = 8;
                        if (i != 8) {
                            i2 = 9;
                            if (i != 9) {
                                i2 = 12;
                                if (i != 12) {
                                    i2 = 13;
                                    if (i != 13) {
                                        return 16;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return i2;
    }

    /* renamed from: a */
    private boolean m18a(String str) {
        String packageName = this.f137b.getPackageName();
        try {
            Resources resourcesForApplication = this.f137b.getPackageManager().getResourcesForApplication(packageName);
            if (resourcesForApplication == null) {
                return false;
            }
            try {
                int identifier = resourcesForApplication.getIdentifier(str, "drawable", packageName);
                if (identifier == 0 || identifier == -1) {
                    return false;
                }
                return BitmapFactory.decodeResource(resourcesForApplication, identifier) != null;
            } catch (Resources.NotFoundException e) {
                return false;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ void m19b(SpassFingerprint spassFingerprint, String str) {
        if (m28g()) {
            if (spassFingerprint.f137b.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
                Log.d("SM_SDK", " com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY is not allowed ");
                return;
            }
            ContentValues contentValues = new ContentValues();
            String name = spassFingerprint.getClass().getPackage().getName();
            String str2 = String.valueOf(spassFingerprint.f137b.getPackageName()) + "#9";
            contentValues.put(Constants.APP_ID, name);
            contentValues.put("feature", str2);
            contentValues.put("extra", str);
            Intent intent = new Intent();
            intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
            intent.putExtra("data", contentValues);
            intent.setPackage("com.samsung.android.providers.context");
            spassFingerprint.f137b.sendBroadcast(intent);
        }
    }

    /* renamed from: f */
    private void m26f() throws UnsupportedOperationException {
        synchronized (this) {
            if (!this.f153s) {
                throw new UnsupportedOperationException("Fingerprint Service is not supported in the platform.");
            }
            if (this.f136a == null) {
                throw new UnsupportedOperationException("Fingerprint Service is not running on the device.");
            }
        }
    }

    /* renamed from: g */
    private static boolean m28g() {
        try {
            Class<?> cls = Class.forName("com.samsung.android.feature.FloatingFeature");
            boolean booleanValue = ((Boolean) cls.getMethod("getEnableStatus", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE")).booleanValue();
            Log.d("SecFloating", "floating feature : " + booleanValue);
            return booleanValue;
        } catch (Exception e) {
            Log.d("SecFloating", "Floating feature is not supported (non-samsung device)");
            try {
                Class<?> cls2 = Class.forName("com.samsung.android.feature.semFloatingFeature");
                boolean booleanValue2 = ((Boolean) cls2.getMethod("getBoolean", String.class).invoke(cls2.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE")).booleanValue();
                Log.d("SecFloating", "floating feature : " + booleanValue2);
                return booleanValue2;
            } catch (Exception e2) {
                Log.d("SecFloating", "Floating feature is not supported this device (non-samsung device)");
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m33a() {
        return this.f153s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean m34b() {
        m26f();
        return this.f149n >= 16843008;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final boolean m35c() {
        m26f();
        boolean z = false;
        try {
            Class.forName(SdkSupporter.CLASSNAME_FINGERPRINT_MANAGER).getMethod("isSupportFingerprintIds", new Class[0]);
            z = true;
        } catch (Exception e) {
            Log.w("SpassFingerprintSDK", e);
        }
        return z ? this.f136a.isSupportFingerprintIds() : m34b();
    }

    public void cancelIdentify() {
        m26f();
        if (this.f154t == null && this.f156v == null && this.f155u == null) {
            throw new IllegalStateException("No Identify request.");
        }
        IBinder iBinder = this.f154t;
        if (iBinder != null) {
            if (!this.f136a.cancel(iBinder)) {
                throw new IllegalStateException("cancel() returned RESULT_FAILED due to FingerprintService Error.");
            }
        } else if (this.f156v != null || this.f155u != null) {
            this.f136a.notifyAppActivityState(4, null);
        }
        this.f156v = null;
        this.f155u = null;
    }

    public void changeStandbyString(String str) {
        m26f();
        if (m36d()) {
            throw new IllegalStateException("setStandbyString is not supported.");
        }
        if (str == null) {
            throw new IllegalArgumentException("the standby text passed is null.");
        }
        if (str.length() > 100) {
            throw new IllegalArgumentException("the standby text passed is longer than 100 characters.");
        }
        this.f148m = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final boolean m36d() {
        m26f();
        boolean z = true;
        boolean z2 = false;
        try {
            Class.forName(SdkSupporter.CLASSNAME_FINGERPRINT_MANAGER).getMethod("isSupportBackupPassword", new Class[0]);
            z2 = true;
        } catch (Exception e) {
            Log.w("SpassFingerprintSDK", e);
        }
        if (z2) {
            z = this.f136a.isSupportBackupPassword();
        }
        return z;
    }

    public String getGuideForPoorQuality() {
        m26f();
        String str = this.f139d;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("FingerprintGuide is Invalid. This API must be called inside IdentifyListener.onFinished() with STATUS_QUALITY_FAILED only.");
    }

    public int getIdentifiedFingerprintIndex() {
        m26f();
        int i = this.f138c;
        if (i != -1) {
            return i;
        }
        throw new IllegalStateException("FingerprintIndex is Invalid. This API must be called inside IdentifyListener.onFinished() only.");
    }

    public SparseArray getRegisteredFingerprintName() {
        m26f();
        SparseArray sparseArray = new SparseArray();
        int enrolledFingers = this.f136a.getEnrolledFingers();
        if (enrolledFingers <= 0) {
            return null;
        }
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 > 10) {
                return sparseArray;
            }
            if (((1 << i2) & enrolledFingers) != 0) {
                sparseArray.put(i2, this.f136a.getIndexName(i2));
            }
            i = i2 + 1;
        }
    }

    public SparseArray getRegisteredFingerprintUniqueID() {
        m26f();
        if (!m35c()) {
            throw new IllegalStateException("getRegisteredFingerprintUniqueID is not supported.");
        }
        SparseArray sparseArray = new SparseArray();
        int enrolledFingers = this.f136a.getEnrolledFingers();
        if (enrolledFingers <= 0) {
            return null;
        }
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 > 10) {
                return sparseArray;
            }
            if (((1 << i2) & enrolledFingers) != 0) {
                sparseArray.put(i2, this.f136a.getFingerprintId(i2));
            }
            i = i2 + 1;
        }
    }

    public boolean hasRegisteredFinger() {
        m26f();
        return this.f136a.getEnrolledFingers() != 0;
    }

    public void registerFinger(Context context, RegisterListener registerListener) {
        m26f();
        if (context == null) {
            throw new IllegalArgumentException("activityContext passed is null.");
        }
        if (registerListener == null) {
            throw new IllegalArgumentException("listener passed is null.");
        }
        if (this.f136a.isEnrolling()) {
            this.f136a.notifyEnrollEnd();
        }
        try {
            context.getPackageManager();
            try {
                this.f136a.startEnrollActivity(context, new C0190b(registerListener), toString());
            } catch (UndeclaredThrowableException e) {
                throw new IllegalArgumentException("activityContext is invalid");
            }
        } catch (NullPointerException e2) {
            throw new IllegalArgumentException("activityContext is invalid");
        }
    }

    public void setCanceledOnTouchOutside(boolean z) {
        m26f();
        if (!m34b()) {
            throw new IllegalStateException("setCanceledOnTouchOutside is not supported.");
        }
        this.f146k = z;
    }

    public void setDialogBgTransparency(int i) {
        m26f();
        if (!m34b()) {
            throw new IllegalStateException("setDialogBGTransparency is not supported.");
        }
        if (i < 0 || i > 255) {
            throw new IllegalArgumentException("the transparency passed is not valid.");
        }
        this.f144i = i;
    }

    public void setDialogButton(String str) {
        m26f();
        if (m36d()) {
            throw new IllegalStateException("setDialogButton is not supported.");
        }
        if (str == null) {
            throw new IllegalArgumentException("the buttonText passed is null.");
        }
        if (str.length() > 32) {
            throw new IllegalArgumentException("the title text passed is longer than 32 characters.");
        }
        this.f147l = str;
    }

    public void setDialogIcon(String str) {
        m26f();
        if (!m34b()) {
            throw new IllegalStateException("setDialogIcon is not supported.");
        }
        if (str == null) {
            throw new IllegalArgumentException("the iconName passed is null.");
        }
        if (!m18a(str)) {
            throw new IllegalArgumentException("the iconName passed is not valid.");
        }
        this.f143h = str;
    }

    public void setDialogTitle(String str, int i) {
        m26f();
        if (!m34b()) {
            throw new IllegalStateException("setDialogTitle is not supported.");
        }
        if (str == null) {
            throw new IllegalArgumentException("the titletext passed is null.");
        }
        if (str.length() > 256) {
            throw new IllegalArgumentException("the title text passed is longer than 256 characters.");
        }
        if ((i >>> 24) != 0) {
            throw new IllegalArgumentException("alpha value is not supported in the titleColor.");
        }
        this.f141f = str;
        this.f142g = i - 16777216;
    }

    public void setIntendedFingerprintIndex(ArrayList arrayList) {
        m26f();
        if (arrayList == null) {
            Log.w("SpassFingerprintSDK", "requestedIndex is null. Identify is carried out for all indexes.");
            return;
        }
        if (!m34b()) {
            throw new IllegalStateException("setIntendedFingerprintIndex is not supported.");
        }
        this.f140e = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= arrayList.size()) {
                return;
            }
            this.f140e.add((Integer) arrayList.get(i2));
            i = i2 + 1;
        }
    }

    public void startIdentify(IdentifyListener identifyListener) {
        m26f();
        if (identifyListener == null) {
            throw new IllegalArgumentException("listener passed is null.");
        }
        if (this.f154t != null) {
            throw new IllegalStateException("Identify request is denied because a previous request is still in progress.");
        }
        if (this.f158x == null) {
            this.f158x = new C0187b(this, identifyListener, (byte) 0);
        }
        ArrayList arrayList = this.f140e;
        if (arrayList != null) {
            this.f145j = new int[arrayList.size()];
            for (int i = 0; i < this.f140e.size(); i++) {
                this.f145j[i] = ((Integer) this.f140e.get(i)).intValue();
            }
        }
        this.f157w = new C0186a(this.f137b.getPackageName()).m38a(this.f145j).m37a().m39b();
        this.f154t = this.f136a.registerClient(this.f158x, this.f157w);
        IBinder iBinder = this.f154t;
        if (iBinder == null) {
            throw new IllegalStateException("failed because registerClient returned null.");
        }
        int identify = this.f136a.identify(iBinder, null);
        if (identify == -2) {
            this.f136a.unregisterClient(this.f154t);
            this.f154t = null;
            throw new IllegalStateException("Identify request is denied because a previous request is still in progress.");
        }
        if (identify == 51) {
            this.f136a.unregisterClient(this.f154t);
            this.f154t = null;
            throw new SpassInvalidStateException("Identify request is denied because 5 identify attempts are failed.", 1);
        }
        if (identify != 0) {
            if (this.f136a.hasPendingCommand()) {
                this.f136a.cancel(this.f154t);
            }
            this.f136a.unregisterClient(this.f154t);
            this.f154t = null;
            throw new IllegalStateException("Identify operation is failed.");
        }
        this.f158x.m41a(identifyListener);
        this.f140e = null;
        this.f145j = null;
    }

    public void startIdentifyWithDialog(Context context, IdentifyListener identifyListener, boolean z) {
        m26f();
        if (context == null) {
            throw new IllegalArgumentException("activityContext passed is null.");
        }
        if (identifyListener == null) {
            throw new IllegalArgumentException("listener passed is null.");
        }
        try {
            context.getPackageManager();
            if (!m34b()) {
                C0188c c0188c = new C0188c(this, identifyListener, (byte) 0);
                this.f155u = this.f136a.showIdentifyDialog(context, c0188c, null, z);
                Dialog dialog = this.f155u;
                if (dialog == null) {
                    throw new IllegalStateException("Identify operation is failed.");
                }
                dialog.setOnDismissListener(new DialogInterfaceOnDismissListenerC0189a(c0188c));
                this.f155u.show();
                return;
            }
            ArrayList arrayList = this.f140e;
            if (arrayList != null && arrayList.size() > 0) {
                this.f145j = new int[this.f140e.size()];
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.f140e.size()) {
                        break;
                    }
                    this.f145j[i2] = ((Integer) this.f140e.get(i2)).intValue();
                    i = i2 + 1;
                }
            }
            this.f156v = new C0187b(this, identifyListener, (byte) 0);
            try {
                String format = String.format("%d.%d.%d", 1, 2, 2);
                Bundle bundle = new Bundle();
                bundle.putBoolean("password", z);
                bundle.putString("packageName", context.getPackageName());
                bundle.putString(f135q, "Pass-v" + format);
                bundle.putBoolean("demandExtraEvent", true);
                if (this.f145j != null) {
                    bundle.putIntArray("request_template_index_list", this.f145j);
                }
                if (this.f141f != null) {
                    bundle.putString("titletext", this.f141f);
                }
                if (this.f142g != -1) {
                    bundle.putInt("titlecolor", this.f142g);
                }
                if (this.f143h != null) {
                    bundle.putString("iconname", this.f143h);
                }
                if (this.f144i != -1) {
                    bundle.putInt("transparency", this.f144i);
                }
                if (this.f146k) {
                    bundle.putBoolean("touchoutside", this.f146k);
                }
                if (this.f147l != null) {
                    bundle.putString("button_name", this.f147l);
                }
                if (this.f148m != null) {
                    bundle.putString("standby_string", this.f148m);
                }
                if (this.f136a.identifyWithDialog(context, this.f156v, bundle) == 0) {
                } else {
                    throw new IllegalStateException("Identify operation is failed.");
                }
            } finally {
                this.f140e = null;
                this.f145j = null;
                this.f141f = null;
                this.f142g = -1;
                this.f144i = -1;
                this.f143h = null;
                this.f146k = false;
                this.f148m = null;
                this.f147l = null;
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("activityContext is invalid");
        }
    }
}
