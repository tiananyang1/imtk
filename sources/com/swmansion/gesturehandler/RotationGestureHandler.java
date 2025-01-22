package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import com.swmansion.gesturehandler.RotationGestureDetector;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/RotationGestureHandler.class */
public class RotationGestureHandler extends GestureHandler<RotationGestureHandler> {
    private static final double ROTATION_RECOGNITION_THRESHOLD = 0.08726646259971647d;
    private RotationGestureDetector.OnRotationGestureListener mGestureListener = new RotationGestureDetector.OnRotationGestureListener() { // from class: com.swmansion.gesturehandler.RotationGestureHandler.1
        @Override // com.swmansion.gesturehandler.RotationGestureDetector.OnRotationGestureListener
        public boolean onRotation(RotationGestureDetector rotationGestureDetector) {
            double d = RotationGestureHandler.this.mLastRotation;
            RotationGestureHandler.this.mLastRotation += rotationGestureDetector.getRotation();
            long timeDelta = rotationGestureDetector.getTimeDelta();
            if (timeDelta > 0) {
                RotationGestureHandler rotationGestureHandler = RotationGestureHandler.this;
                rotationGestureHandler.mLastVelocity = (rotationGestureHandler.mLastRotation - d) / timeDelta;
            }
            if (Math.abs(RotationGestureHandler.this.mLastRotation) < RotationGestureHandler.ROTATION_RECOGNITION_THRESHOLD || RotationGestureHandler.this.getState() != 2) {
                return true;
            }
            RotationGestureHandler.this.activate();
            return true;
        }

        @Override // com.swmansion.gesturehandler.RotationGestureDetector.OnRotationGestureListener
        public boolean onRotationBegin(RotationGestureDetector rotationGestureDetector) {
            return true;
        }

        @Override // com.swmansion.gesturehandler.RotationGestureDetector.OnRotationGestureListener
        public void onRotationEnd(RotationGestureDetector rotationGestureDetector) {
            RotationGestureHandler.this.end();
        }
    };
    private double mLastRotation;
    private double mLastVelocity;
    private RotationGestureDetector mRotationGestureDetector;

    public RotationGestureHandler() {
        setShouldCancelWhenOutside(false);
    }

    public float getAnchorX() {
        RotationGestureDetector rotationGestureDetector = this.mRotationGestureDetector;
        if (rotationGestureDetector == null) {
            return Float.NaN;
        }
        return rotationGestureDetector.getAnchorX();
    }

    public float getAnchorY() {
        RotationGestureDetector rotationGestureDetector = this.mRotationGestureDetector;
        if (rotationGestureDetector == null) {
            return Float.NaN;
        }
        return rotationGestureDetector.getAnchorY();
    }

    public double getRotation() {
        return this.mLastRotation;
    }

    public double getVelocity() {
        return this.mLastVelocity;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        int state = getState();
        if (state == 0) {
            this.mLastVelocity = 0.0d;
            this.mLastRotation = 0.0d;
            this.mRotationGestureDetector = new RotationGestureDetector(this.mGestureListener);
            begin();
        }
        RotationGestureDetector rotationGestureDetector = this.mRotationGestureDetector;
        if (rotationGestureDetector != null) {
            rotationGestureDetector.onTouchEvent(motionEvent);
        }
        if (motionEvent.getActionMasked() == 1) {
            if (state == 4) {
                end();
            } else {
                fail();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onReset() {
        this.mRotationGestureDetector = null;
        this.mLastVelocity = 0.0d;
        this.mLastRotation = 0.0d;
    }
}
