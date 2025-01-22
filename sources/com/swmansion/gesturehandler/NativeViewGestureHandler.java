package com.swmansion.gesturehandler;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/NativeViewGestureHandler.class */
public class NativeViewGestureHandler extends GestureHandler<NativeViewGestureHandler> {
    private boolean mDisallowInterruption;
    private boolean mShouldActivateOnStart;

    public NativeViewGestureHandler() {
        setShouldCancelWhenOutside(true);
    }

    private static boolean tryIntercept(View view, MotionEvent motionEvent) {
        return (view instanceof ViewGroup) && ((ViewGroup) view).onInterceptTouchEvent(motionEvent);
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onCancel() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        obtain.setAction(3);
        getView().onTouchEvent(obtain);
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        View view = getView();
        int state = getState();
        if (motionEvent.getActionMasked() == 1) {
            view.onTouchEvent(motionEvent);
            if ((state == 0 || state == 2) && view.isPressed()) {
                activate();
            }
            end();
            return;
        }
        if (state != 0 && state != 2) {
            if (state == 4) {
                view.onTouchEvent(motionEvent);
            }
        } else if (this.mShouldActivateOnStart) {
            tryIntercept(view, motionEvent);
            view.onTouchEvent(motionEvent);
            activate();
        } else if (tryIntercept(view, motionEvent)) {
            view.onTouchEvent(motionEvent);
            activate();
        } else if (state != 2) {
            begin();
        }
    }

    public NativeViewGestureHandler setDisallowInterruption(boolean z) {
        this.mDisallowInterruption = z;
        return this;
    }

    public NativeViewGestureHandler setShouldActivateOnStart(boolean z) {
        this.mShouldActivateOnStart = z;
        return this;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        return !this.mDisallowInterruption;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        if (gestureHandler instanceof NativeViewGestureHandler) {
            NativeViewGestureHandler nativeViewGestureHandler = (NativeViewGestureHandler) gestureHandler;
            if (nativeViewGestureHandler.getState() == 4 && nativeViewGestureHandler.mDisallowInterruption) {
                return false;
            }
        }
        boolean z = !this.mDisallowInterruption;
        int state = getState();
        int state2 = gestureHandler.getState();
        if (state == 4 && state2 == 4 && z) {
            return false;
        }
        boolean z2 = false;
        if (state == 4) {
            z2 = false;
            if (z) {
                z2 = true;
            }
        }
        return z2;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public boolean shouldRequireToWaitForFailure(GestureHandler gestureHandler) {
        return super.shouldRequireToWaitForFailure(gestureHandler);
    }
}
