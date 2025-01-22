package com.swmansion.gesturehandler.react;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.microsoft.codepush.react.CodePushConstants;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerOrchestrator;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerRootHelper.class */
public class RNGestureHandlerRootHelper {
    private static final float MIN_ALPHA_FOR_TOUCH = 0.1f;
    private final ReactContext mContext;
    private final GestureHandler mJSGestureHandler;
    private final GestureHandlerOrchestrator mOrchestrator;
    private final ReactRootView mReactRootView;
    private boolean mShouldIntercept = false;
    private boolean mPassingTouch = false;

    /* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerRootHelper$RootViewGestureHandler.class */
    private class RootViewGestureHandler extends GestureHandler {
        private RootViewGestureHandler() {
        }

        @Override // com.swmansion.gesturehandler.GestureHandler
        protected void onCancel() {
            RNGestureHandlerRootHelper.this.mShouldIntercept = true;
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            obtain.setAction(3);
            RNGestureHandlerRootHelper.this.mReactRootView.onChildStartedNativeGesture(obtain);
        }

        @Override // com.swmansion.gesturehandler.GestureHandler
        protected void onHandle(MotionEvent motionEvent) {
            if (getState() == 0) {
                begin();
                RNGestureHandlerRootHelper.this.mShouldIntercept = false;
            }
            if (motionEvent.getActionMasked() == 1) {
                end();
            }
        }
    }

    public RNGestureHandlerRootHelper(ReactContext reactContext, ViewGroup viewGroup) {
        UiThreadUtil.assertOnUiThread();
        int id = viewGroup.getId();
        if (id < 1) {
            throw new IllegalStateException("Expect view tag to be set for " + viewGroup);
        }
        RNGestureHandlerModule nativeModule = reactContext.getNativeModule(RNGestureHandlerModule.class);
        RNGestureHandlerRegistry registry = nativeModule.getRegistry();
        this.mReactRootView = findRootViewTag(viewGroup);
        Log.i(CodePushConstants.REACT_NATIVE_LOG_TAG, "[GESTURE HANDLER] Initialize gesture handler for root view " + this.mReactRootView);
        this.mContext = reactContext;
        this.mOrchestrator = new GestureHandlerOrchestrator(viewGroup, registry, new RNViewConfigurationHelper());
        this.mOrchestrator.setMinimumAlphaForTraversal(MIN_ALPHA_FOR_TOUCH);
        this.mJSGestureHandler = new RootViewGestureHandler();
        this.mJSGestureHandler.setTag(-id);
        registry.registerHandler(this.mJSGestureHandler);
        registry.attachHandlerToView(this.mJSGestureHandler.getTag(), id);
        nativeModule.registerRootHelper(this);
    }

    private static ReactRootView findRootViewTag(ViewGroup viewGroup) {
        ViewParent viewParent;
        UiThreadUtil.assertOnUiThread();
        ViewParent viewParent2 = viewGroup;
        while (true) {
            viewParent = viewParent2;
            if (viewParent == null || (viewParent instanceof ReactRootView)) {
                break;
            }
            viewParent2 = viewParent.getParent();
        }
        if (viewParent != null) {
            return (ReactRootView) viewParent;
        }
        throw new IllegalStateException("View " + viewGroup + " has not been mounted under ReactRootView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryCancelAllHandlers() {
        GestureHandler gestureHandler = this.mJSGestureHandler;
        if (gestureHandler == null || gestureHandler.getState() != 2) {
            return;
        }
        this.mJSGestureHandler.activate();
        this.mJSGestureHandler.end();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.mPassingTouch = true;
        this.mOrchestrator.onTouchEvent(motionEvent);
        this.mPassingTouch = false;
        return this.mShouldIntercept;
    }

    public ReactRootView getRootView() {
        return this.mReactRootView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleSetJSResponder(int i, boolean z) {
        if (z) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    RNGestureHandlerRootHelper.this.tryCancelAllHandlers();
                }
            });
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (this.mOrchestrator == null || this.mPassingTouch) {
            return;
        }
        tryCancelAllHandlers();
    }

    public void tearDown() {
        Log.i(CodePushConstants.REACT_NATIVE_LOG_TAG, "[GESTURE HANDLER] Tearing down gesture handler registered for root view " + this.mReactRootView);
        RNGestureHandlerModule nativeModule = this.mContext.getNativeModule(RNGestureHandlerModule.class);
        nativeModule.getRegistry().dropHandler(this.mJSGestureHandler.getTag());
        nativeModule.unregisterRootHelper(this);
    }
}
