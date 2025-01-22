package io.intercom.android.sdk;

import android.content.Context;
import android.support.annotation.Nullable;
import io.intercom.android.sdk.logger.LumberMill;
import io.intercom.android.sdk.twig.Twig;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/IntercomPushManager.class */
public class IntercomPushManager {
    public static final String HOST_APP_INTENT = "host_app_intent";
    public static final String INTERCOM_PUSH_KEY = "intercom_push_key";
    public static final String INTERCOM_PUSH_PATH = "intercom_push_notification_path";
    public static final String MULTIPLE_NOTIFICATIONS = "multiple_notifications";
    private static final String PREFS_SENDER_ID = "intercom_sender_id";
    public static final String PUSH_ONLY_CONVO_ID = "push_only_convo_id";
    public static final String PUSH_ONLY_INSTANCE_ID = "push_only_instance_id";
    private static final Twig TWIG = LumberMill.getLogger();

    /* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/IntercomPushManager$IntercomPushIntegrationType.class */
    public enum IntercomPushIntegrationType {
        FCM,
        NONE
    }

    public static void cacheSenderId(Context context, String str) {
        context.getSharedPreferences("INTERCOM_SDK_PREFS", 0).edit().putString(PREFS_SENDER_ID, str).apply();
    }

    private static boolean fcmModuleInstalled() {
        return getFcmServiceClass() != null;
    }

    @Nullable
    private static Class getFcmServiceClass() {
        try {
            return Class.forName("io.intercom.android.sdk.fcm.IntercomFcmMessengerService");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static IntercomPushIntegrationType getInstalledModuleType() {
        IntercomPushIntegrationType intercomPushIntegrationType = IntercomPushIntegrationType.NONE;
        if (fcmModuleInstalled()) {
            TWIG.internal("FCM is installed");
            intercomPushIntegrationType = IntercomPushIntegrationType.FCM;
        }
        return intercomPushIntegrationType;
    }
}
