package com.swmansion.gesturehandler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/PanGestureHandler.class */
public class PanGestureHandler extends GestureHandler<PanGestureHandler> {
    private static int DEFAULT_MAX_POINTERS = 10;
    private static int DEFAULT_MIN_POINTERS = 1;
    private static float MAX_VALUE_IGNORE = Float.MIN_VALUE;
    private static float MIN_VALUE_IGNORE = Float.MAX_VALUE;
    private float mActiveOffsetXEnd;
    private float mActiveOffsetXStart;
    private float mActiveOffsetYEnd;
    private float mActiveOffsetYStart;
    private boolean mAverageTouches;
    private float mFailOffsetXEnd;
    private float mFailOffsetXStart;
    private float mFailOffsetYEnd;
    private float mFailOffsetYStart;
    private float mLastVelocityX;
    private float mLastVelocityY;
    private float mLastX;
    private float mLastY;
    private int mMaxPointers;
    private float mMinDistSq;
    private int mMinPointers;
    private float mMinVelocitySq;
    private float mMinVelocityX;
    private float mMinVelocityY;
    private float mOffsetX;
    private float mOffsetY;
    private float mStartX;
    private float mStartY;
    private VelocityTracker mVelocityTracker;

    public PanGestureHandler(Context context) {
        float f = MAX_VALUE_IGNORE;
        this.mMinDistSq = f;
        float f2 = MIN_VALUE_IGNORE;
        this.mActiveOffsetXStart = f2;
        this.mActiveOffsetXEnd = f;
        this.mFailOffsetXStart = f;
        this.mFailOffsetXEnd = f2;
        this.mActiveOffsetYStart = f2;
        this.mActiveOffsetYEnd = f;
        this.mFailOffsetYStart = f;
        this.mFailOffsetYEnd = f2;
        this.mMinVelocityX = f2;
        this.mMinVelocityY = f2;
        this.mMinVelocitySq = f2;
        this.mMinPointers = DEFAULT_MIN_POINTERS;
        this.mMaxPointers = DEFAULT_MAX_POINTERS;
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMinDistSq = scaledTouchSlop * scaledTouchSlop;
    }

    private static void addVelocityMovement(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        velocityTracker.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    private boolean shouldActivate() {
        float f = (this.mLastX - this.mStartX) + this.mOffsetX;
        float f2 = this.mActiveOffsetXStart;
        if (f2 != MIN_VALUE_IGNORE && f < f2) {
            return true;
        }
        float f3 = this.mActiveOffsetXEnd;
        if (f3 != MAX_VALUE_IGNORE && f > f3) {
            return true;
        }
        float f4 = (this.mLastY - this.mStartY) + this.mOffsetY;
        float f5 = this.mActiveOffsetYStart;
        if (f5 != MIN_VALUE_IGNORE && f4 < f5) {
            return true;
        }
        float f6 = this.mActiveOffsetYEnd;
        if (f6 != MAX_VALUE_IGNORE && f4 > f6) {
            return true;
        }
        float f7 = this.mMinDistSq;
        if (f7 != MIN_VALUE_IGNORE && (f * f) + (f4 * f4) >= f7) {
            return true;
        }
        float f8 = this.mLastVelocityX;
        float f9 = this.mMinVelocityX;
        if (f9 != MIN_VALUE_IGNORE) {
            if (f9 < 0.0f && f8 <= f9) {
                return true;
            }
            float f10 = this.mMinVelocityX;
            if (f10 >= 0.0f && f8 >= f10) {
                return true;
            }
        }
        float f11 = this.mLastVelocityY;
        float f12 = this.mMinVelocityY;
        if (f12 != MIN_VALUE_IGNORE) {
            if (f12 < 0.0f && f8 <= f12) {
                return true;
            }
            float f13 = this.mMinVelocityY;
            if (f13 >= 0.0f && f8 >= f13) {
                return true;
            }
        }
        float f14 = this.mMinVelocitySq;
        return f14 != MIN_VALUE_IGNORE && (f8 * f8) + (f11 * f11) >= f14;
    }

    private boolean shouldFail() {
        float f = (this.mLastX - this.mStartX) + this.mOffsetX;
        float f2 = this.mFailOffsetXStart;
        if (f2 != MAX_VALUE_IGNORE && f < f2) {
            return true;
        }
        float f3 = this.mFailOffsetXEnd;
        if (f3 != MIN_VALUE_IGNORE && f > f3) {
            return true;
        }
        float f4 = (this.mLastY - this.mStartY) + this.mOffsetY;
        float f5 = this.mFailOffsetYStart;
        if (f5 != MAX_VALUE_IGNORE && f4 < f5) {
            return true;
        }
        float f6 = this.mFailOffsetYEnd;
        return f6 != MIN_VALUE_IGNORE && f4 > f6;
    }

    public float getTranslationX() {
        return (this.mLastX - this.mStartX) + this.mOffsetX;
    }

    public float getTranslationY() {
        return (this.mLastY - this.mStartY) + this.mOffsetY;
    }

    public float getVelocityX() {
        return this.mLastVelocityX;
    }

    public float getVelocityY() {
        return this.mLastVelocityY;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        int state = getState();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 6 || actionMasked == 5) {
            this.mOffsetX += this.mLastX - this.mStartX;
            this.mOffsetY += this.mLastY - this.mStartY;
            this.mLastX = GestureUtils.getLastPointerX(motionEvent, this.mAverageTouches);
            this.mLastY = GestureUtils.getLastPointerY(motionEvent, this.mAverageTouches);
            this.mStartX = this.mLastX;
            this.mStartY = this.mLastY;
        } else {
            this.mLastX = GestureUtils.getLastPointerX(motionEvent, this.mAverageTouches);
            this.mLastY = GestureUtils.getLastPointerY(motionEvent, this.mAverageTouches);
        }
        if (state != 0 || motionEvent.getPointerCount() < this.mMinPointers) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                addVelocityMovement(velocityTracker, motionEvent);
                this.mVelocityTracker.computeCurrentVelocity(PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT);
                this.mLastVelocityX = this.mVelocityTracker.getXVelocity();
                this.mLastVelocityY = this.mVelocityTracker.getYVelocity();
            }
        } else {
            this.mStartX = this.mLastX;
            this.mStartY = this.mLastY;
            this.mOffsetX = 0.0f;
            this.mOffsetY = 0.0f;
            this.mVelocityTracker = VelocityTracker.obtain();
            addVelocityMovement(this.mVelocityTracker, motionEvent);
            begin();
        }
        if (actionMasked == 1) {
            if (state == 4 || state == 2) {
                end();
                return;
            } else {
                fail();
                return;
            }
        }
        if (actionMasked == 5 && motionEvent.getPointerCount() > this.mMaxPointers) {
            if (state == 4) {
                cancel();
                return;
            } else {
                fail();
                return;
            }
        }
        if (actionMasked == 6 && state == 4 && motionEvent.getPointerCount() < this.mMinPointers) {
            fail();
            return;
        }
        if (state == 2) {
            if (shouldFail()) {
                fail();
            } else if (shouldActivate()) {
                this.mStartX = this.mLastX;
                this.mStartY = this.mLastY;
                activate();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onReset() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public PanGestureHandler setActiveOffsetXEnd(float f) {
        this.mActiveOffsetXEnd = f;
        return this;
    }

    public PanGestureHandler setActiveOffsetXStart(float f) {
        this.mActiveOffsetXStart = f;
        return this;
    }

    public PanGestureHandler setActiveOffsetYEnd(float f) {
        this.mActiveOffsetYEnd = f;
        return this;
    }

    public PanGestureHandler setActiveOffsetYStart(float f) {
        this.mActiveOffsetYStart = f;
        return this;
    }

    public PanGestureHandler setAverageTouches(boolean z) {
        this.mAverageTouches = z;
        return this;
    }

    public PanGestureHandler setFailOffsetXEnd(float f) {
        this.mFailOffsetXEnd = f;
        return this;
    }

    public PanGestureHandler setFailOffsetXStart(float f) {
        this.mFailOffsetXStart = f;
        return this;
    }

    public PanGestureHandler setFailOffsetYEnd(float f) {
        this.mFailOffsetYEnd = f;
        return this;
    }

    public PanGestureHandler setFailOffsetYStart(float f) {
        this.mFailOffsetYStart = f;
        return this;
    }

    public PanGestureHandler setMaxPointers(int i) {
        this.mMaxPointers = i;
        return this;
    }

    public PanGestureHandler setMinDist(float f) {
        this.mMinDistSq = f * f;
        return this;
    }

    public PanGestureHandler setMinPointers(int i) {
        this.mMinPointers = i;
        return this;
    }

    public PanGestureHandler setMinVelocity(float f) {
        this.mMinVelocitySq = f * f;
        return this;
    }

    public PanGestureHandler setMinVelocityX(float f) {
        this.mMinVelocityX = f;
        return this;
    }

    public PanGestureHandler setMinVelocityY(float f) {
        this.mMinVelocityY = f;
        return this;
    }
}
