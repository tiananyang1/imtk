package io.intercom.android.sdk;

import android.app.Application;
import android.app.TaskStackBuilder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.intercom.android.sdk.identity.Registration;
import io.intercom.android.sdk.logger.LumberMill;
import io.intercom.android.sdk.twig.Twig;
import io.intercom.android.sdk.utilities.ValidatorUtil;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/Intercom.class */
public abstract class Intercom {
    public static final String PUSH_RECEIVER = "intercom_sdk";

    @Nullable
    private static Intercom instance;
    public static final Visibility VISIBLE = Visibility.VISIBLE;
    public static final Visibility GONE = Visibility.GONE;
    private static final Twig TWIG = LumberMill.getLogger();

    /* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/Intercom$LogLevel.class */
    public @interface LogLevel {
        public static final int ASSERT = 7;
        public static final int DEBUG = 3;
        public static final int DISABLED = 8;
        public static final int ERROR = 6;
        public static final int INFO = 4;
        public static final int VERBOSE = 2;
        public static final int WARN = 5;
    }

    /* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/Intercom$Visibility.class */
    public enum Visibility {
        GONE,
        VISIBLE
    }

    public static Intercom client() {
        Intercom intercom;
        synchronized (Intercom.class) {
            try {
                if (instance == null) {
                    throw new IllegalStateException("Please call Intercom.initialize() before requesting the client.");
                }
                intercom = instance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return intercom;
    }

    public static void initialize(Application application, String str, String str2) {
        synchronized (Intercom.class) {
            try {
                if (instance != null) {
                    TWIG.i("Intercom has already been initialized", new Object[0]);
                    return;
                }
                if (ValidatorUtil.isValidConstructorParams(application, str, str2)) {
                    TWIG.i("Intercom has already been initialized", new Object[0]);
                    instance = RealIntercom.create(application, str, str2);
                    LateInitializationPreparer.getInstance().handlePastLifecycleEvents(application, Injector.get());
                } else {
                    instance = new InvalidIntercom();
                }
            } finally {
            }
        }
    }

    public static void registerForLaterInitialisation(@NonNull Application application) {
        synchronized (Intercom.class) {
            try {
                if (instance != null) {
                    TWIG.i("Intercom has already been initialized", new Object[0]);
                } else {
                    if (application == null) {
                        throw new NullPointerException("Cannot call registerForLaterInitialisation() with a null Application");
                    }
                    LateInitializationPreparer.getInstance().register(application);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void setLogLevel(@LogLevel int i) {
        LumberMill.setLogLevel(i);
    }

    public static void unregisterForLateInitialisation(@NonNull Application application) {
        if (application == null) {
            throw new NullPointerException("Cannot call unregisterForLateInitialisation() with a null Application");
        }
        LateInitializationPreparer.getInstance().unregister(application);
    }

    public abstract void addUnreadConversationCountListener(@NonNull UnreadConversationCountListener unreadConversationCountListener);

    @Deprecated
    public abstract void displayConversationsList();

    public abstract void displayHelpCenter();

    public abstract void displayMessageComposer();

    public abstract void displayMessageComposer(String str);

    public abstract void displayMessenger();

    public abstract int getUnreadConversationCount();

    public abstract void handlePushMessage();

    public abstract void handlePushMessage(TaskStackBuilder taskStackBuilder);

    public abstract void hideMessenger();

    public abstract void logEvent(String str);

    public abstract void logEvent(String str, Map<String, ?> map);

    public abstract void logout();

    public abstract void registerIdentifiedUser(Registration registration);

    public abstract void registerUnidentifiedUser();

    public abstract void removeUnreadConversationCountListener(UnreadConversationCountListener unreadConversationCountListener);

    @Deprecated
    public abstract void reset();

    public abstract void setBottomPadding(int i);

    public abstract void setInAppMessageVisibility(Visibility visibility);

    public abstract void setLauncherVisibility(Visibility visibility);

    public abstract void setUserHash(String str);

    public abstract void updateUser(UserAttributes userAttributes);
}
