package io.intercom.android.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import io.intercom.android.sdk.actions.Actions;
import io.intercom.android.sdk.api.Api;
import io.intercom.android.sdk.api.UserUpdateBatcher;
import io.intercom.android.sdk.identity.AppConfig;
import io.intercom.android.sdk.identity.UserIdentity;
import io.intercom.android.sdk.logger.LumberMill;
import io.intercom.android.sdk.overlay.OverlayPresenter;
import io.intercom.android.sdk.state.State;
import io.intercom.android.sdk.store.Store;
import io.intercom.android.sdk.twig.Twig;
import io.intercom.android.sdk.user.DeviceData;
import io.intercom.android.sdk.utilities.ActivityFinisher;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/ResetManager.class */
public class ResetManager {
    private final ActivityFinisher activityFinisher;
    private final Provider<Api> apiProvider;
    private final Provider<AppConfig> appConfigProvider;
    private final Context context;
    private final OverlayPresenter overlayPresenter;
    private final Store<State> store;
    private final UserIdentity userIdentity;
    private final UserUpdateBatcher userUpdateBatcher;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Twig twig = LumberMill.getLogger();
    private final Runnable hardResetTask = new Runnable() { // from class: io.intercom.android.sdk.ResetManager.1
        @Override // java.lang.Runnable
        public void run() {
            ResetManager.this.hardReset();
        }
    };

    public ResetManager(Provider<Api> provider, UserIdentity userIdentity, OverlayPresenter overlayPresenter, Provider<AppConfig> provider2, Store<State> store, UserUpdateBatcher userUpdateBatcher, Context context, ActivityFinisher activityFinisher) {
        this.apiProvider = provider;
        this.userIdentity = userIdentity;
        this.overlayPresenter = overlayPresenter;
        this.appConfigProvider = provider2;
        this.store = store;
        this.userUpdateBatcher = userUpdateBatcher;
        this.context = context;
        this.activityFinisher = activityFinisher;
    }

    public void clear() {
        this.handler.removeCallbacks(this.hardResetTask);
    }

    public void hardReset() {
        this.handler.removeCallbacks(this.hardResetTask);
        if (isSoftReset()) {
            String deviceToken = DeviceData.getDeviceToken(this.context);
            if (TextUtils.isEmpty(deviceToken)) {
                this.twig.internal("There is no device token to remove.");
            } else {
                this.apiProvider.get().removeDeviceToken(deviceToken, this.userIdentity);
            }
            this.store.dispatch(Actions.hardReset());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSoftReset() {
        return this.userIdentity.isSoftReset();
    }

    public void softReset() {
        this.userUpdateBatcher.submitPendingUpdate();
        this.activityFinisher.finishActivities();
        this.overlayPresenter.softReset();
        this.handler.postDelayed(this.hardResetTask, this.appConfigProvider.get().getSoftResetTimeoutMs());
        this.store.dispatch(Actions.softReset());
        this.twig.i("Successfully reset the user. To resume communicating with Intercom, you can register a user", new Object[0]);
    }
}
