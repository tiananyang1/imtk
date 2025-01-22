package io.intercom.android.sdk;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;
import io.intercom.android.sdk.actions.Actions;
import io.intercom.android.sdk.api.DeDuper;
import io.intercom.android.sdk.api.UserUpdateBatcher;
import io.intercom.android.sdk.commons.utilities.TimeProvider;
import io.intercom.android.sdk.errorreporting.ErrorReporter;
import io.intercom.android.sdk.logger.LumberMill;
import io.intercom.android.sdk.metrics.MetricsStore;
import io.intercom.android.sdk.push.SystemNotificationManager;
import io.intercom.android.sdk.state.State;
import io.intercom.android.sdk.store.Selectors;
import io.intercom.android.sdk.store.Store;
import io.intercom.android.sdk.twig.Twig;
import io.intercom.android.sdk.utilities.SimpleActivityLifecycleCallbacks;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/LifecycleTracker.class */
public class LifecycleTracker extends SimpleActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private final DeDuper deDuper;
    private final ErrorReporter errorReporter;
    private final ActivityLifecycleHandler handler;
    private final MetricsStore metricsStore;
    private final ResetManager resetManager;
    final Store<State> store;
    private final SystemNotificationManager systemNotificationManager;
    private final TimeProvider timeProvider;
    private final UserUpdateBatcher userUpdateBatcher;
    private final Set<Integer> startedActivities = new HashSet();
    private final Twig twig = LumberMill.getLogger();

    @VisibleForTesting
    LifecycleTracker(SystemNotificationManager systemNotificationManager, MetricsStore metricsStore, ErrorReporter errorReporter, DeDuper deDuper, TimeProvider timeProvider, UserUpdateBatcher userUpdateBatcher, Store<State> store, ResetManager resetManager, ActivityLifecycleHandler activityLifecycleHandler) {
        this.systemNotificationManager = systemNotificationManager;
        this.metricsStore = metricsStore;
        this.errorReporter = errorReporter;
        this.deDuper = deDuper;
        this.timeProvider = timeProvider;
        this.userUpdateBatcher = userUpdateBatcher;
        this.store = store;
        this.resetManager = resetManager;
        this.handler = activityLifecycleHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LifecycleTracker create(SystemNotificationManager systemNotificationManager, MetricsStore metricsStore, ErrorReporter errorReporter, DeDuper deDuper, TimeProvider timeProvider, UserUpdateBatcher userUpdateBatcher, Store<State> store, ResetManager resetManager, float f) {
        return new LifecycleTracker(systemNotificationManager, metricsStore, errorReporter, deDuper, timeProvider, userUpdateBatcher, store, resetManager, new ActivityLifecycleHandler(Looper.getMainLooper(), store, f));
    }

    private boolean hasApplicationBecomeBackgrounded(Activity activity) {
        return isApplicationInBackground(activity) && !((Boolean) this.store.select(Selectors.APP_IS_BACKGROUNDED)).booleanValue();
    }

    private static boolean isScreenLocked(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    private void registerActivity(Activity activity) {
        this.startedActivities.add(Integer.valueOf(System.identityHashCode(activity)));
        this.twig.i("Started observing " + activity, new Object[0]);
    }

    private void unregisterActivity(Activity activity) {
        this.startedActivities.remove(Integer.valueOf(System.identityHashCode(activity)));
        this.twig.i("Stopped observing " + activity, new Object[0]);
    }

    @VisibleForTesting
    boolean isApplicationInBackground(Activity activity) {
        if (isScreenLocked(activity)) {
            return true;
        }
        return this.startedActivities.isEmpty() && !activity.isChangingConfigurations();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.handler.sendMessage(ActivityLifecycleHandler.getMessage(2, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.handler.removeMessages(1, activity);
        this.handler.sendMessage(ActivityLifecycleHandler.getMessage(0, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        registerActivity(activity);
        if (((Boolean) this.store.select(Selectors.APP_IS_BACKGROUNDED)).booleanValue()) {
            this.store.dispatch(Actions.appEnteredForeground(this.timeProvider.currentTimeMillis()));
            this.metricsStore.loadAndSendMetrics();
            this.errorReporter.sendSavedReport();
            this.systemNotificationManager.clear();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        unregisterActivity(activity);
        this.handler.removeMessages(1, activity);
        this.handler.sendMessage(ActivityLifecycleHandler.getMessage(3, activity));
        if (hasApplicationBecomeBackgrounded(activity)) {
            if (this.resetManager.isSoftReset()) {
                this.resetManager.hardReset();
            }
            this.store.dispatch(Actions.appEnteredBackground(this.timeProvider.currentTimeMillis()));
            this.deDuper.reset();
            this.userUpdateBatcher.submitPendingUpdate();
            this.metricsStore.loadAndSendMetrics();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerActivities(Collection<Integer> collection) {
        this.startedActivities.addAll(collection);
    }
}
