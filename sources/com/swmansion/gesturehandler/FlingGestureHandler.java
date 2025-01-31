package com.swmansion.gesturehandler;

import android.os.Handler;
import android.view.MotionEvent;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/FlingGestureHandler.class */
public class FlingGestureHandler extends GestureHandler<FlingGestureHandler> {
    private static final int DEFAULT_DIRECTION = 1;
    private static final long DEFAULT_MAX_DURATION_MS = 800;
    private static final long DEFAULT_MIN_ACCEPTABLE_DELTA = 160;
    private static final int DEFAULT_NUMBER_OF_TOUCHES_REQUIRED = 1;
    private Handler mHandler;
    private int mMaxNumberOfPointersSimultaneously;
    private float mStartX;
    private float mStartY;
    private long mMaxDurationMs = DEFAULT_MAX_DURATION_MS;
    private long mMinAcceptableDelta = DEFAULT_MIN_ACCEPTABLE_DELTA;
    private int mDirection = 1;
    private int mNumberOfPointersRequired = 1;
    private final Runnable mFailDelayed = new Runnable() { // from class: com.swmansion.gesturehandler.FlingGestureHandler.1
        @Override // java.lang.Runnable
        public void run() {
            FlingGestureHandler.this.fail();
        }
    };

    private void endFling(MotionEvent motionEvent) {
        if (tryEndFling(motionEvent)) {
            return;
        }
        fail();
    }

    private void startFling(MotionEvent motionEvent) {
        this.mStartX = motionEvent.getRawX();
        this.mStartY = motionEvent.getRawY();
        begin();
        this.mMaxNumberOfPointersSimultaneously = 1;
        Handler handler = this.mHandler;
        if (handler == null) {
            this.mHandler = new Handler();
        } else {
            handler.removeCallbacksAndMessages(null);
        }
        this.mHandler.postDelayed(this.mFailDelayed, this.mMaxDurationMs);
    }

    private boolean tryEndFling(MotionEvent motionEvent) {
        if (this.mMaxNumberOfPointersSimultaneously != this.mNumberOfPointersRequired) {
            return false;
        }
        if (((this.mDirection & 1) == 0 || motionEvent.getRawX() - this.mStartX <= ((float) this.mMinAcceptableDelta)) && (((this.mDirection & 2) == 0 || this.mStartX - motionEvent.getRawX() <= ((float) this.mMinAcceptableDelta)) && (((this.mDirection & 4) == 0 || this.mStartY - motionEvent.getRawY() <= ((float) this.mMinAcceptableDelta)) && ((this.mDirection & 8) == 0 || motionEvent.getRawY() - this.mStartY <= ((float) this.mMinAcceptableDelta))))) {
            return false;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        activate();
        end();
        return true;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onCancel() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        int state = getState();
        if (state == 0) {
            startFling(motionEvent);
        }
        if (state == 2) {
            tryEndFling(motionEvent);
            if (motionEvent.getPointerCount() > this.mMaxNumberOfPointersSimultaneously) {
                this.mMaxNumberOfPointersSimultaneously = motionEvent.getPointerCount();
            }
            if (motionEvent.getActionMasked() == 1) {
                endFling(motionEvent);
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onReset() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void setDirection(int i) {
        this.mDirection = i;
    }

    public void setNumberOfPointersRequired(int i) {
        this.mNumberOfPointersRequired = i;
    }
}
