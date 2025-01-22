package io.intercom.android.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import io.intercom.android.sdk.logger.LumberMill;
import io.intercom.android.sdk.twig.Twig;
import io.intercom.android.sdk.utilities.SimpleActivityLifecycleCallbacks;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/LateInitializationPreparer.class */
public class LateInitializationPreparer extends SimpleActivityLifecycleCallbacks {

    @SuppressLint({"StaticFieldLeak"})
    @Nullable
    private static LateInitializationPreparer instance;
    private boolean hasPaused;
    private boolean isRegistered;

    @Nullable
    private Activity lastResumedActivity;
    private final Twig twig = LumberMill.getLogger();
    private final Set<Integer> startedActivities = new HashSet();

    public static LateInitializationPreparer getInstance() {
        if (instance == null) {
            instance = new LateInitializationPreparer();
        }
        return instance;
    }

    public void handlePastLifecycleEvents(Application application, Injector injector) {
        Activity activity = this.lastResumedActivity;
        boolean z = this.hasPaused;
        Set<Integer> set = this.startedActivities;
        unregister(application);
        if (activity != null) {
            this.twig.i("Handling lifecycle events for " + activity + " during late initialisation", new Object[0]);
            LifecycleTracker lifecycleTracker = injector.getLifecycleTracker();
            lifecycleTracker.onActivityStarted(activity);
            lifecycleTracker.onActivityResumed(activity);
            if (z) {
                lifecycleTracker.onActivityPaused(activity);
            }
        }
        if (set.isEmpty()) {
            return;
        }
        injector.getLifecycleTracker().registerActivities(set);
        this.twig.i("Observed Activities with hashcodes " + set + " during late initialization", new Object[0]);
    }

    @VisibleForTesting
    boolean hasPaused() {
        return this.hasPaused;
    }

    @VisibleForTesting
    @Nullable
    Activity lastResumedActivity() {
        return this.lastResumedActivity;
    }

    public void onActivityPaused(Activity activity) {
        this.startedActivities.add(Integer.valueOf(System.identityHashCode(activity)));
        this.hasPaused = true;
    }

    public void onActivityResumed(Activity activity) {
        this.startedActivities.add(Integer.valueOf(System.identityHashCode(activity)));
        this.lastResumedActivity = activity;
        this.hasPaused = false;
    }

    public void onActivityStarted(Activity activity) {
        this.startedActivities.add(Integer.valueOf(System.identityHashCode(activity)));
    }

    public void onActivityStopped(Activity activity) {
        this.startedActivities.remove(Integer.valueOf(System.identityHashCode(activity)));
        if (activity == this.lastResumedActivity) {
            this.lastResumedActivity = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void register(Application application) {
        this.twig.i("Registering for later initialization", new Object[0]);
        synchronized (this) {
            if (!this.isRegistered) {
                application.registerActivityLifecycleCallbacks(this);
                this.isRegistered = true;
            }
        }
    }

    @VisibleForTesting
    Set<Integer> startedActivities() {
        return this.startedActivities;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void unregister(Application application) {
        this.twig.i("Unregistering for later initialization", new Object[0]);
        application.unregisterActivityLifecycleCallbacks(this);
        this.isRegistered = false;
        this.lastResumedActivity = null;
        this.hasPaused = false;
    }
}
