package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerEnabledRootView.class */
public class RNGestureHandlerEnabledRootView extends ReactRootView {

    @Nullable
    private RNGestureHandlerRootHelper mGestureRootHelper;

    @Nullable
    private ReactInstanceManager mReactInstanceManager;

    public RNGestureHandlerEnabledRootView(Context context) {
        super(context);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mGestureRootHelper;
        if (rNGestureHandlerRootHelper == null || !rNGestureHandlerRootHelper.dispatchTouchEvent(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void initialize() {
        if (this.mGestureRootHelper == null) {
            this.mGestureRootHelper = new RNGestureHandlerRootHelper(this.mReactInstanceManager.getCurrentReactContext(), this);
            return;
        }
        throw new IllegalStateException("GestureHandler already initialized for root view " + this);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mGestureRootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.requestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle) {
        super.startReactApplication(reactInstanceManager, str, bundle);
        this.mReactInstanceManager = reactInstanceManager;
    }

    public void tearDown() {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mGestureRootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.tearDown();
            this.mGestureRootHelper = null;
        }
    }
}
