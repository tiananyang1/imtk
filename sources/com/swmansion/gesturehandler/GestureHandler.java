package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import android.view.View;
import com.swmansion.gesturehandler.GestureHandler;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/GestureHandler.class */
public class GestureHandler<T extends GestureHandler> {
    public static final int DIRECTION_DOWN = 8;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 4;
    private static final int HIT_SLOP_BOTTOM_IDX = 3;
    private static final int HIT_SLOP_HEIGHT_IDX = 5;
    private static final int HIT_SLOP_LEFT_IDX = 0;
    public static final float HIT_SLOP_NONE = Float.NaN;
    private static final int HIT_SLOP_RIGHT_IDX = 2;
    private static final int HIT_SLOP_TOP_IDX = 1;
    private static final int HIT_SLOP_WIDTH_IDX = 4;
    private static int MAX_POINTERS_COUNT = 11;
    public static final int STATE_ACTIVE = 4;
    public static final int STATE_BEGAN = 2;
    public static final int STATE_CANCELLED = 3;
    public static final int STATE_END = 5;
    public static final int STATE_FAILED = 1;
    public static final int STATE_UNDETERMINED = 0;
    private static MotionEvent.PointerCoords[] sPointerCoords;
    private static MotionEvent.PointerProperties[] sPointerProps;
    int mActivationIndex;
    private float[] mHitSlop;
    private GestureHandlerInteractionController mInteractionController;
    boolean mIsActive;
    boolean mIsAwaiting;
    private float mLastEventOffsetX;
    private float mLastEventOffsetY;
    private float mLastX;
    private float mLastY;
    private OnTouchEventListener<T> mListener;
    private GestureHandlerOrchestrator mOrchestrator;
    private boolean mShouldCancelWhenOutside;
    private int mTag;
    private View mView;
    private boolean mWithinBounds;

    /* renamed from: mX */
    private float f214mX;

    /* renamed from: mY */
    private float f215mY;
    private final int[] mTrackedPointerIDs = new int[MAX_POINTERS_COUNT];
    private int mTrackedPointersCount = 0;
    private int mState = 0;
    private boolean mEnabled = true;
    private int mNumberOfPointers = 0;

    private MotionEvent adaptEvent(MotionEvent motionEvent) {
        int i;
        int i2;
        int i3;
        if (!needAdapt(motionEvent)) {
            return motionEvent;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i4 = 2;
        int i5 = 0;
        if (actionMasked == 0 || actionMasked == 5) {
            int actionIndex = motionEvent.getActionIndex();
            i = actionIndex;
            if (this.mTrackedPointerIDs[motionEvent.getPointerId(actionIndex)] != -1) {
                i2 = actionIndex;
                i3 = 5;
                if (this.mTrackedPointersCount == 1) {
                    i3 = 0;
                    i2 = actionIndex;
                }
                int i6 = i3;
                i = i2;
                i4 = i6;
            }
        } else if (actionMasked == 1 || actionMasked == 6) {
            int actionIndex2 = motionEvent.getActionIndex();
            i = actionIndex2;
            if (this.mTrackedPointerIDs[motionEvent.getPointerId(actionIndex2)] != -1) {
                i2 = actionIndex2;
                i3 = 6;
                if (this.mTrackedPointersCount == 1) {
                    i3 = 1;
                    i2 = actionIndex2;
                }
                int i62 = i3;
                i = i2;
                i4 = i62;
            }
        } else {
            i4 = actionMasked;
            i = -1;
        }
        initPointerProps(this.mTrackedPointersCount);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        motionEvent.setLocation(motionEvent.getRawX(), motionEvent.getRawY());
        int pointerCount = motionEvent.getPointerCount();
        int i7 = 0;
        while (true) {
            int i8 = i7;
            if (i5 >= pointerCount) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), i4, i8, sPointerProps, sPointerCoords, motionEvent.getMetaState(), motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
                motionEvent.setLocation(x, y);
                obtain.setLocation(x, y);
                return obtain;
            }
            int pointerId = motionEvent.getPointerId(i5);
            int i9 = i4;
            int i10 = i8;
            if (this.mTrackedPointerIDs[pointerId] != -1) {
                motionEvent.getPointerProperties(i5, sPointerProps[i8]);
                sPointerProps[i8].id = this.mTrackedPointerIDs[pointerId];
                motionEvent.getPointerCoords(i5, sPointerCoords[i8]);
                i9 = i4;
                if (i5 == i) {
                    i9 = i4 | (i8 << 8);
                }
                i10 = i8 + 1;
            }
            i5++;
            i4 = i9;
            i7 = i10;
        }
    }

    private int findNextLocalPointerId() {
        int i;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.mTrackedPointersCount) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                i = i4;
                int[] iArr = this.mTrackedPointerIDs;
                if (i >= iArr.length || iArr[i] == i3) {
                    break;
                }
                i4 = i + 1;
            }
            if (i == this.mTrackedPointerIDs.length) {
                return i3;
            }
            i2 = i3 + 1;
        }
    }

    private static boolean hitSlopSet(float f) {
        return !Float.isNaN(f);
    }

    private static void initPointerProps(int i) {
        int i2 = i;
        if (sPointerProps == null) {
            int i3 = MAX_POINTERS_COUNT;
            sPointerProps = new MotionEvent.PointerProperties[i3];
            sPointerCoords = new MotionEvent.PointerCoords[i3];
            i2 = i;
        }
        while (i2 > 0) {
            MotionEvent.PointerProperties[] pointerPropertiesArr = sPointerProps;
            int i4 = i2 - 1;
            if (pointerPropertiesArr[i4] != null) {
                return;
            }
            pointerPropertiesArr[i4] = new MotionEvent.PointerProperties();
            sPointerCoords[i4] = new MotionEvent.PointerCoords();
            i2--;
        }
    }

    private void moveToState(int i) {
        int i2 = this.mState;
        if (i2 == i) {
            return;
        }
        this.mState = i;
        this.mOrchestrator.onHandlerStateChange(this, i, i2);
        onStateChange(i, i2);
    }

    private boolean needAdapt(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != this.mTrackedPointersCount) {
            return true;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            int[] iArr = this.mTrackedPointerIDs;
            if (i2 >= iArr.length) {
                return false;
            }
            if (iArr[i2] != -1 && iArr[i2] != i2) {
                return true;
            }
            i = i2 + 1;
        }
    }

    public static String stateToString(int i) {
        if (i == 0) {
            return "UNDETERMINED";
        }
        if (i == 1) {
            return "FAILED";
        }
        if (i == 2) {
            return "BEGIN";
        }
        if (i == 3) {
            return "CANCELLED";
        }
        if (i == 4) {
            return "ACTIVE";
        }
        if (i != 5) {
            return null;
        }
        return "END";
    }

    public final void activate() {
        int i = this.mState;
        if (i == 0 || i == 2) {
            moveToState(4);
        }
    }

    public final void begin() {
        if (this.mState == 0) {
            moveToState(2);
        }
    }

    public final void cancel() {
        int i = this.mState;
        if (i == 4 || i == 0 || i == 2) {
            onCancel();
            moveToState(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchStateChange(int i, int i2) {
        OnTouchEventListener<T> onTouchEventListener = this.mListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onStateChange(this, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchTouchEvent(MotionEvent motionEvent) {
        OnTouchEventListener<T> onTouchEventListener = this.mListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onTouchEvent(this, motionEvent);
        }
    }

    public final void end() {
        int i = this.mState;
        if (i == 2 || i == 4) {
            moveToState(5);
        }
    }

    public final void fail() {
        int i = this.mState;
        if (i == 4 || i == 0 || i == 2) {
            moveToState(1);
        }
    }

    public float getLastAbsolutePositionX() {
        return this.mLastX;
    }

    public float getLastAbsolutePositionY() {
        return this.mLastY;
    }

    public float getLastRelativePositionX() {
        return this.mLastX - this.mLastEventOffsetX;
    }

    public float getLastRelativePositionY() {
        return this.mLastY - this.mLastEventOffsetY;
    }

    public int getNumberOfPointers() {
        return this.mNumberOfPointers;
    }

    public int getState() {
        return this.mState;
    }

    public int getTag() {
        return this.mTag;
    }

    public View getView() {
        return this.mView;
    }

    public float getX() {
        return this.f214mX;
    }

    public float getY() {
        return this.f215mY;
    }

    public final void handle(MotionEvent motionEvent) {
        int i;
        if (!this.mEnabled || (i = this.mState) == 3 || i == 1 || i == 5 || this.mTrackedPointersCount < 1) {
            return;
        }
        MotionEvent adaptEvent = adaptEvent(motionEvent);
        this.f214mX = adaptEvent.getX();
        this.f215mY = adaptEvent.getY();
        this.mNumberOfPointers = adaptEvent.getPointerCount();
        this.mWithinBounds = isWithinBounds(this.mView, this.f214mX, this.f215mY);
        if (this.mShouldCancelWhenOutside && !this.mWithinBounds) {
            int i2 = this.mState;
            if (i2 == 4) {
                cancel();
                return;
            } else {
                if (i2 == 2) {
                    fail();
                    return;
                }
                return;
            }
        }
        this.mLastX = GestureUtils.getLastPointerX(adaptEvent, true);
        this.mLastY = GestureUtils.getLastPointerY(adaptEvent, true);
        this.mLastEventOffsetX = adaptEvent.getRawX() - adaptEvent.getX();
        this.mLastEventOffsetY = adaptEvent.getRawY() - adaptEvent.getY();
        onHandle(adaptEvent);
        if (adaptEvent != motionEvent) {
            adaptEvent.recycle();
        }
    }

    public boolean hasCommonPointers(GestureHandler gestureHandler) {
        int i = 0;
        while (true) {
            int i2 = i;
            int[] iArr = this.mTrackedPointerIDs;
            if (i2 >= iArr.length) {
                return false;
            }
            if (iArr[i2] != -1 && gestureHandler.mTrackedPointerIDs[i2] != -1) {
                return true;
            }
            i = i2 + 1;
        }
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isWithinBounds() {
        return this.mWithinBounds;
    }

    public boolean isWithinBounds(View view, float f, float f2) {
        float f3;
        float f4;
        float f5;
        float width = view.getWidth();
        float height = view.getHeight();
        float[] fArr = this.mHitSlop;
        float f6 = 0.0f;
        float f7 = 0.0f;
        if (fArr != null) {
            float f8 = fArr[0];
            float f9 = fArr[1];
            float f10 = fArr[2];
            float f11 = fArr[3];
            float f12 = hitSlopSet(f8) ? 0.0f - f8 : 0.0f;
            if (hitSlopSet(f9)) {
                f7 = 0.0f - f11;
            }
            float f13 = width;
            if (hitSlopSet(f10)) {
                f13 = width + f10;
            }
            float f14 = height;
            if (hitSlopSet(f11)) {
                f14 = height + f11;
            }
            float[] fArr2 = this.mHitSlop;
            float f15 = fArr2[4];
            float f16 = fArr2[5];
            float f17 = f13;
            float f18 = f12;
            if (hitSlopSet(f15)) {
                if (hitSlopSet(f8)) {
                    f17 = f13;
                    f18 = f12;
                    if (!hitSlopSet(f10)) {
                        f17 = f15 + f12;
                        f18 = f12;
                    }
                } else {
                    f18 = f13 - f15;
                    f17 = f13;
                }
            }
            f5 = f17;
            f6 = f7;
            f3 = f18;
            f4 = f14;
            if (hitSlopSet(f16)) {
                if (hitSlopSet(f7)) {
                    f5 = f17;
                    f6 = f7;
                    f3 = f18;
                    f4 = f14;
                    if (!hitSlopSet(f14)) {
                        f4 = f7 + f16;
                        f5 = f17;
                        f6 = f7;
                        f3 = f18;
                    }
                } else {
                    f6 = f14 - f16;
                    f5 = f17;
                    f3 = f18;
                    f4 = f14;
                }
            }
        } else {
            f3 = 0.0f;
            f4 = height;
            f5 = width;
        }
        return f >= f3 && f <= f5 && f2 >= f6 && f2 <= f4;
    }

    protected void onCancel() {
    }

    protected void onHandle(MotionEvent motionEvent) {
        moveToState(1);
    }

    protected void onReset() {
    }

    protected void onStateChange(int i, int i2) {
    }

    public final void prepare(View view, GestureHandlerOrchestrator gestureHandlerOrchestrator) {
        if (this.mView != null || this.mOrchestrator != null) {
            throw new IllegalStateException("Already prepared or hasn't been reset");
        }
        Arrays.fill(this.mTrackedPointerIDs, -1);
        this.mTrackedPointersCount = 0;
        this.mState = 0;
        this.mView = view;
        this.mOrchestrator = gestureHandlerOrchestrator;
    }

    public final void reset() {
        this.mView = null;
        this.mOrchestrator = null;
        Arrays.fill(this.mTrackedPointerIDs, -1);
        this.mTrackedPointersCount = 0;
        onReset();
    }

    public T setEnabled(boolean z) {
        if (this.mView != null) {
            cancel();
        }
        this.mEnabled = z;
        return this;
    }

    public T setHitSlop(float f) {
        return setHitSlop(f, f, f, f, Float.NaN, Float.NaN);
    }

    public T setHitSlop(float f, float f2, float f3, float f4, float f5, float f6) {
        if (this.mHitSlop == null) {
            this.mHitSlop = new float[6];
        }
        float[] fArr = this.mHitSlop;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f6;
        if (hitSlopSet(f5) && hitSlopSet(f) && hitSlopSet(f3)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined");
        }
        if (hitSlopSet(f5) && !hitSlopSet(f) && !hitSlopSet(f3)) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined");
        }
        if (hitSlopSet(f6) && hitSlopSet(f4) && hitSlopSet(f2)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined");
        }
        if (!hitSlopSet(f6) || hitSlopSet(f4)) {
            return this;
        }
        if (hitSlopSet(f2)) {
            return this;
        }
        throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined");
    }

    public T setInteractionController(GestureHandlerInteractionController gestureHandlerInteractionController) {
        this.mInteractionController = gestureHandlerInteractionController;
        return this;
    }

    public GestureHandler setOnTouchEventListener(OnTouchEventListener<T> onTouchEventListener) {
        this.mListener = onTouchEventListener;
        return this;
    }

    public T setShouldCancelWhenOutside(boolean z) {
        this.mShouldCancelWhenOutside = z;
        return this;
    }

    public void setTag(int i) {
        this.mTag = i;
    }

    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldHandlerBeCancelledBy(this, gestureHandler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        if (gestureHandler == this) {
            return true;
        }
        GestureHandlerInteractionController gestureHandlerInteractionController = this.mInteractionController;
        if (gestureHandlerInteractionController != null) {
            return gestureHandlerInteractionController.shouldRecognizeSimultaneously(this, gestureHandler);
        }
        return false;
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldRequireHandlerToWaitForFailure(this, gestureHandler);
    }

    public boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldWaitForHandlerFailure(this, gestureHandler);
    }

    public void startTrackingPointer(int i) {
        int[] iArr = this.mTrackedPointerIDs;
        if (iArr[i] == -1) {
            iArr[i] = findNextLocalPointerId();
            this.mTrackedPointersCount++;
        }
    }

    public void stopTrackingPointer(int i) {
        int[] iArr = this.mTrackedPointerIDs;
        if (iArr[i] != -1) {
            iArr[i] = -1;
            this.mTrackedPointersCount--;
        }
    }

    public String toString() {
        View view = this.mView;
        return getClass().getSimpleName() + "@[" + this.mTag + "]:" + (view == null ? null : view.getClass().getSimpleName());
    }

    public boolean wantEvents() {
        int i;
        return (!this.mEnabled || (i = this.mState) == 1 || i == 3 || i == 5 || this.mTrackedPointersCount <= 0) ? false : true;
    }
}
