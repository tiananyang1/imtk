package com.swmansion.gesturehandler;

import android.view.MotionEvent;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/GestureUtils.class */
public class GestureUtils {
    public static float getLastPointerX(MotionEvent motionEvent, boolean z) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        int actionIndex = motionEvent.getActionMasked() == 6 ? motionEvent.getActionIndex() : -1;
        if (!z) {
            int pointerCount = motionEvent.getPointerCount() - 1;
            int i = pointerCount;
            if (pointerCount == actionIndex) {
                i = pointerCount - 1;
            }
            return motionEvent.getX(i) + rawX;
        }
        float f = 0.0f;
        int pointerCount2 = motionEvent.getPointerCount();
        int i2 = 0;
        int i3 = 0;
        while (i2 < pointerCount2) {
            int i4 = i3;
            float f2 = f;
            if (i2 != actionIndex) {
                f2 = f + motionEvent.getX(i2) + rawX;
                i4 = i3 + 1;
            }
            i2++;
            i3 = i4;
            f = f2;
        }
        return f / i3;
    }

    public static float getLastPointerY(MotionEvent motionEvent, boolean z) {
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        int actionIndex = motionEvent.getActionMasked() == 6 ? motionEvent.getActionIndex() : -1;
        if (!z) {
            int pointerCount = motionEvent.getPointerCount() - 1;
            int i = pointerCount;
            if (pointerCount == actionIndex) {
                i = pointerCount - 1;
            }
            return motionEvent.getY(i) + rawY;
        }
        float f = 0.0f;
        int pointerCount2 = motionEvent.getPointerCount();
        int i2 = 0;
        int i3 = 0;
        while (i2 < pointerCount2) {
            int i4 = i3;
            float f2 = f;
            if (i2 != actionIndex) {
                f2 = f + motionEvent.getY(i2) + rawY;
                i4 = i3 + 1;
            }
            i2++;
            i3 = i4;
            f = f2;
        }
        return f / i3;
    }
}
