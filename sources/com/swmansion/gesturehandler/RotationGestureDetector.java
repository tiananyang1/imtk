package com.swmansion.gesturehandler;

import android.view.MotionEvent;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/RotationGestureDetector.class */
public class RotationGestureDetector {
    private float mAnchorX;
    private float mAnchorY;
    private double mAngleDiff;
    private long mCurrTime;
    private boolean mInProgress;
    private OnRotationGestureListener mListener;
    private int[] mPointerIds = new int[2];
    private double mPrevAngle;
    private long mPrevTime;

    /* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/RotationGestureDetector$OnRotationGestureListener.class */
    public interface OnRotationGestureListener {
        boolean onRotation(RotationGestureDetector rotationGestureDetector);

        boolean onRotationBegin(RotationGestureDetector rotationGestureDetector);

        void onRotationEnd(RotationGestureDetector rotationGestureDetector);
    }

    public RotationGestureDetector(OnRotationGestureListener onRotationGestureListener) {
        this.mListener = onRotationGestureListener;
    }

    private void finish() {
        if (this.mInProgress) {
            this.mInProgress = false;
            OnRotationGestureListener onRotationGestureListener = this.mListener;
            if (onRotationGestureListener != null) {
                onRotationGestureListener.onRotationEnd(this);
            }
        }
    }

    private void updateCurrent(MotionEvent motionEvent) {
        this.mPrevTime = this.mCurrTime;
        this.mCurrTime = motionEvent.getEventTime();
        int findPointerIndex = motionEvent.findPointerIndex(this.mPointerIds[0]);
        int findPointerIndex2 = motionEvent.findPointerIndex(this.mPointerIds[1]);
        float x = motionEvent.getX(findPointerIndex);
        float y = motionEvent.getY(findPointerIndex);
        float x2 = motionEvent.getX(findPointerIndex2);
        float y2 = motionEvent.getY(findPointerIndex2);
        this.mAnchorX = (x + x2) * 0.5f;
        this.mAnchorY = (y + y2) * 0.5f;
        double d = -Math.atan2(y2 - y, x2 - x);
        if (Double.isNaN(this.mPrevAngle)) {
            this.mAngleDiff = 0.0d;
        } else {
            this.mAngleDiff = this.mPrevAngle - d;
        }
        this.mPrevAngle = d;
        double d2 = this.mAngleDiff;
        if (d2 > 3.141592653589793d) {
            this.mAngleDiff = d2 - 3.141592653589793d;
        } else if (d2 < -3.141592653589793d) {
            this.mAngleDiff = d2 + 3.141592653589793d;
        }
        double d3 = this.mAngleDiff;
        if (d3 > 1.5707963267948966d) {
            this.mAngleDiff = d3 - 3.141592653589793d;
        } else if (d3 < -1.5707963267948966d) {
            this.mAngleDiff = d3 + 3.141592653589793d;
        }
    }

    public float getAnchorX() {
        return this.mAnchorX;
    }

    public float getAnchorY() {
        return this.mAnchorY;
    }

    public double getRotation() {
        return this.mAngleDiff;
    }

    public long getTimeDelta() {
        return this.mCurrTime - this.mPrevTime;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mInProgress = false;
            this.mPointerIds[0] = motionEvent.getPointerId(motionEvent.getActionIndex());
            this.mPointerIds[1] = -1;
            return true;
        }
        if (actionMasked == 1) {
            finish();
            return true;
        }
        if (actionMasked == 2) {
            if (!this.mInProgress) {
                return true;
            }
            updateCurrent(motionEvent);
            OnRotationGestureListener onRotationGestureListener = this.mListener;
            if (onRotationGestureListener == null) {
                return true;
            }
            onRotationGestureListener.onRotation(this);
            return true;
        }
        if (actionMasked != 5) {
            if (actionMasked != 6 || !this.mInProgress) {
                return true;
            }
            int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
            int[] iArr = this.mPointerIds;
            if (pointerId != iArr[0] && pointerId != iArr[1]) {
                return true;
            }
            finish();
            return true;
        }
        if (this.mInProgress) {
            return true;
        }
        this.mPointerIds[1] = motionEvent.getPointerId(motionEvent.getActionIndex());
        this.mInProgress = true;
        this.mPrevTime = motionEvent.getEventTime();
        this.mPrevAngle = Double.NaN;
        updateCurrent(motionEvent);
        OnRotationGestureListener onRotationGestureListener2 = this.mListener;
        if (onRotationGestureListener2 == null) {
            return true;
        }
        onRotationGestureListener2.onRotationBegin(this);
        return true;
    }
}
