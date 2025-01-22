package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.view.MotionEvent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.views.view.ReactViewGroup;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerRootView.class */
public class RNGestureHandlerRootView extends ReactViewGroup {

    @Nullable
    private RNGestureHandlerRootHelper mRootHelper;

    public RNGestureHandlerRootView(Context context) {
        super(context);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (((RNGestureHandlerRootHelper) Assertions.assertNotNull(this.mRootHelper)).dispatchTouchEvent(motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRootHelper == null) {
            this.mRootHelper = new RNGestureHandlerRootHelper(getContext(), this);
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        ((RNGestureHandlerRootHelper) Assertions.assertNotNull(this.mRootHelper)).requestDisallowInterceptTouchEvent(z);
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void tearDown() {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.tearDown();
        }
    }
}
