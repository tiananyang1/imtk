package io.intercom.android.sdk;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import io.intercom.android.sdk.actions.Actions;
import io.intercom.android.sdk.state.State;
import io.intercom.android.sdk.store.Store;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/ActivityLifecycleHandler.class */
class ActivityLifecycleHandler extends Handler {
    private static final int ESTIMATED_ACTIVITY_TRANSITION_DURATION_MS = 500;
    static final int PAUSED_CODE = 2;
    static final int READY_FOR_VIEW_CODE = 1;
    static final int RESUMED_CODE = 0;
    static final int STOPPED_CODE = 3;
    private final long readyForViewDelayMs;
    private final Store<State> store;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityLifecycleHandler(Looper looper, Store<State> store, float f) {
        super(looper);
        this.store = store;
        this.readyForViewDelayMs = f * 500.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Message getMessage(int i, Activity activity) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = activity;
        return obtain;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Activity activity = (Activity) message.obj;
        int i = message.what;
        if (i == 0) {
            sendMessageAfterDelay(getMessage(1, activity), this.readyForViewDelayMs);
            return;
        }
        if (i == 1) {
            this.store.dispatch(Actions.activityReadyForViewAttachment(activity));
        } else if (i == 2) {
            this.store.dispatch(Actions.activityPaused(activity));
        } else {
            if (i != 3) {
                return;
            }
            this.store.dispatch(Actions.activityStopped(activity));
        }
    }

    @VisibleForTesting
    void sendMessageAfterDelay(Message message, long j) {
        sendMessageDelayed(message, j);
    }
}
