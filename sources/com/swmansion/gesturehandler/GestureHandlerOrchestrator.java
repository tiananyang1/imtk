package com.swmansion.gesturehandler;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/GestureHandlerOrchestrator.class */
public class GestureHandlerOrchestrator {
    private static final float DEFAULT_MIN_ALPHA_FOR_TRAVERSAL = 0.0f;
    private static final int SIMULTANEOUS_GESTURE_HANDLER_LIMIT = 20;
    private final GestureHandlerRegistry mHandlerRegistry;
    private final ViewConfigurationHelper mViewConfigHelper;
    private final ViewGroup mWrapperView;
    private static final PointF sTempPoint = new PointF();
    private static final float[] sMatrixTransformCoords = new float[2];
    private static final Matrix sInverseMatrix = new Matrix();
    private static final float[] sTempCoords = new float[2];
    private static final Comparator<GestureHandler> sHandlersComparator = new Comparator<GestureHandler>() { // from class: com.swmansion.gesturehandler.GestureHandlerOrchestrator.1
        @Override // java.util.Comparator
        public int compare(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
            if ((gestureHandler.mIsActive && gestureHandler2.mIsActive) || (gestureHandler.mIsAwaiting && gestureHandler2.mIsAwaiting)) {
                return Integer.signum(gestureHandler2.mActivationIndex - gestureHandler.mActivationIndex);
            }
            if (gestureHandler.mIsActive) {
                return -1;
            }
            if (gestureHandler2.mIsActive) {
                return 1;
            }
            if (gestureHandler.mIsAwaiting) {
                return -1;
            }
            return gestureHandler2.mIsAwaiting ? 1 : 0;
        }
    };
    private final GestureHandler[] mGestureHandlers = new GestureHandler[20];
    private final GestureHandler[] mAwaitingHandlers = new GestureHandler[20];
    private final GestureHandler[] mPreparedHandlers = new GestureHandler[20];
    private final GestureHandler[] mHandlersToCancel = new GestureHandler[20];
    private int mGestureHandlersCount = 0;
    private int mAwaitingHandlersCount = 0;
    private boolean mIsHandlingTouch = false;
    private int mHandlingChangeSemaphore = 0;
    private boolean mFinishedHandlersCleanupScheduled = false;
    private int mActivationIndex = 0;
    private float mMinAlphaForTraversal = DEFAULT_MIN_ALPHA_FOR_TRAVERSAL;

    public GestureHandlerOrchestrator(ViewGroup viewGroup, GestureHandlerRegistry gestureHandlerRegistry, ViewConfigurationHelper viewConfigurationHelper) {
        this.mWrapperView = viewGroup;
        this.mHandlerRegistry = gestureHandlerRegistry;
        this.mViewConfigHelper = viewConfigurationHelper;
    }

    private void addAwaitingHandler(GestureHandler gestureHandler) {
        int i = 0;
        while (true) {
            int i2 = i;
            int i3 = this.mAwaitingHandlersCount;
            if (i2 >= i3) {
                GestureHandler[] gestureHandlerArr = this.mAwaitingHandlers;
                if (i3 >= gestureHandlerArr.length) {
                    throw new IllegalStateException("Too many recognizers");
                }
                this.mAwaitingHandlersCount = i3 + 1;
                gestureHandlerArr[i3] = gestureHandler;
                gestureHandler.mIsAwaiting = true;
                int i4 = this.mActivationIndex;
                this.mActivationIndex = i4 + 1;
                gestureHandler.mActivationIndex = i4;
                return;
            }
            if (this.mAwaitingHandlers[i2] == gestureHandler) {
                return;
            } else {
                i = i2 + 1;
            }
        }
    }

    private boolean canReceiveEvents(View view) {
        return view.getVisibility() == 0 && view.getAlpha() >= this.mMinAlphaForTraversal;
    }

    private static boolean canRunSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return gestureHandler == gestureHandler2 || gestureHandler.shouldRecognizeSimultaneously(gestureHandler2) || gestureHandler2.shouldRecognizeSimultaneously(gestureHandler);
    }

    private void cancelAll() {
        int i = this.mAwaitingHandlersCount;
        while (true) {
            int i2 = i - 1;
            if (i2 < 0) {
                break;
            }
            this.mAwaitingHandlers[i2].cancel();
            i = i2;
        }
        int i3 = this.mGestureHandlersCount;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= i3) {
                break;
            }
            this.mPreparedHandlers[i5] = this.mGestureHandlers[i5];
            i4 = i5 + 1;
        }
        int i6 = i3;
        while (true) {
            int i7 = i6 - 1;
            if (i7 < 0) {
                return;
            }
            this.mPreparedHandlers[i7].cancel();
            i6 = i7;
        }
    }

    private void cleanupAwaitingHandlers() {
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i >= this.mAwaitingHandlersCount) {
                this.mAwaitingHandlersCount = i3;
                return;
            }
            int i4 = i3;
            if (this.mAwaitingHandlers[i].mIsAwaiting) {
                GestureHandler[] gestureHandlerArr = this.mAwaitingHandlers;
                gestureHandlerArr[i3] = gestureHandlerArr[i];
                i4 = i3 + 1;
            }
            i++;
            i2 = i4;
        }
    }

    private void cleanupFinishedHandlers() {
        boolean z;
        int i;
        int i2 = this.mGestureHandlersCount - 1;
        boolean z2 = false;
        while (true) {
            z = z2;
            if (i2 < 0) {
                break;
            }
            GestureHandler gestureHandler = this.mGestureHandlers[i2];
            boolean z3 = z;
            if (isFinished(gestureHandler.getState())) {
                z3 = z;
                if (!gestureHandler.mIsAwaiting) {
                    this.mGestureHandlers[i2] = null;
                    gestureHandler.reset();
                    gestureHandler.mIsActive = false;
                    gestureHandler.mIsAwaiting = false;
                    gestureHandler.mActivationIndex = Integer.MAX_VALUE;
                    z3 = true;
                }
            }
            i2--;
            z2 = z3;
        }
        if (z) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                i = i4;
                if (i3 >= this.mGestureHandlersCount) {
                    break;
                }
                GestureHandler[] gestureHandlerArr = this.mGestureHandlers;
                int i5 = i;
                if (gestureHandlerArr[i3] != null) {
                    gestureHandlerArr[i] = gestureHandlerArr[i3];
                    i5 = i + 1;
                }
                i3++;
                i4 = i5;
            }
            this.mGestureHandlersCount = i;
        }
        this.mFinishedHandlersCleanupScheduled = false;
    }

    private void deliverEventToGestureHandler(GestureHandler gestureHandler, MotionEvent motionEvent) {
        if (!isViewAttachedUnderWrapper(gestureHandler.getView())) {
            gestureHandler.cancel();
            return;
        }
        if (gestureHandler.wantEvents()) {
            int actionMasked = motionEvent.getActionMasked();
            if (gestureHandler.mIsAwaiting && actionMasked == 2) {
                return;
            }
            float[] fArr = sTempCoords;
            extractCoordsForView(gestureHandler.getView(), motionEvent, fArr);
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            motionEvent.setLocation(fArr[0], fArr[1]);
            gestureHandler.handle(motionEvent);
            if (gestureHandler.mIsActive) {
                gestureHandler.dispatchTouchEvent(motionEvent);
            }
            motionEvent.setLocation(x, y);
            if (actionMasked == 1 || actionMasked == 6) {
                gestureHandler.stopTrackingPointer(motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
    }

    private void extractCoordsForView(View view, MotionEvent motionEvent, float[] fArr) {
        if (view == this.mWrapperView) {
            fArr[0] = motionEvent.getX();
            fArr[1] = motionEvent.getY();
        } else {
            if (view == null || !(view.getParent() instanceof ViewGroup)) {
                throw new IllegalArgumentException("Parent is null? View is no longer in the tree");
            }
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            extractCoordsForView(viewGroup, motionEvent, fArr);
            PointF pointF = sTempPoint;
            transformTouchPointToViewCoords(fArr[0], fArr[1], viewGroup, view, pointF);
            fArr[0] = pointF.x;
            fArr[1] = pointF.y;
        }
    }

    private void extractGestureHandlers(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        sTempCoords[0] = motionEvent.getX(actionIndex);
        sTempCoords[1] = motionEvent.getY(actionIndex);
        traverseWithPointerEvents(this.mWrapperView, sTempCoords, pointerId);
        extractGestureHandlers(this.mWrapperView, sTempCoords, pointerId);
    }

    private boolean extractGestureHandlers(ViewGroup viewGroup, float[] fArr, int i) {
        int childCount = viewGroup.getChildCount();
        while (true) {
            int i2 = childCount - 1;
            if (i2 < 0) {
                return false;
            }
            View childInDrawingOrderAtIndex = this.mViewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, i2);
            if (canReceiveEvents(childInDrawingOrderAtIndex)) {
                PointF pointF = sTempPoint;
                transformTouchPointToViewCoords(fArr[0], fArr[1], viewGroup, childInDrawingOrderAtIndex, pointF);
                float f = fArr[0];
                float f2 = fArr[1];
                fArr[0] = pointF.x;
                fArr[1] = pointF.y;
                boolean traverseWithPointerEvents = (!isClipping(childInDrawingOrderAtIndex) || isTransformedTouchPointInView(fArr[0], fArr[1], childInDrawingOrderAtIndex)) ? traverseWithPointerEvents(childInDrawingOrderAtIndex, fArr, i) : false;
                fArr[0] = f;
                fArr[1] = f2;
                if (traverseWithPointerEvents) {
                    return true;
                }
            }
            childCount = i2;
        }
    }

    private boolean hasOtherHandlerToWaitFor(GestureHandler gestureHandler) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.mGestureHandlersCount) {
                return false;
            }
            GestureHandler gestureHandler2 = this.mGestureHandlers[i2];
            if (!isFinished(gestureHandler2.getState()) && shouldHandlerWaitForOther(gestureHandler, gestureHandler2)) {
                return true;
            }
            i = i2 + 1;
        }
    }

    private boolean isClipping(View view) {
        return !(view instanceof ViewGroup) || this.mViewConfigHelper.isViewClippingChildren((ViewGroup) view);
    }

    private static boolean isFinished(int i) {
        boolean z = true;
        if (i != 3) {
            z = true;
            if (i != 1) {
                if (i == 5) {
                    return true;
                }
                z = false;
            }
        }
        return z;
    }

    private static boolean isTransformedTouchPointInView(float f, float f2, View view) {
        return f >= DEFAULT_MIN_ALPHA_FOR_TRAVERSAL && f <= ((float) view.getWidth()) && f2 >= DEFAULT_MIN_ALPHA_FOR_TRAVERSAL && f2 < ((float) view.getHeight());
    }

    private boolean isViewAttachedUnderWrapper(@Nullable View view) {
        ViewParent viewParent;
        boolean z = false;
        if (view == null) {
            return false;
        }
        if (view == this.mWrapperView) {
            return true;
        }
        ViewParent parent = view.getParent();
        while (true) {
            viewParent = parent;
            if (viewParent == null || viewParent == this.mWrapperView) {
                break;
            }
            parent = viewParent.getParent();
        }
        if (viewParent == this.mWrapperView) {
            z = true;
        }
        return z;
    }

    private void makeActive(GestureHandler gestureHandler) {
        int i;
        int state = gestureHandler.getState();
        gestureHandler.mIsAwaiting = false;
        gestureHandler.mIsActive = true;
        int i2 = this.mActivationIndex;
        this.mActivationIndex = i2 + 1;
        gestureHandler.mActivationIndex = i2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = i4;
            if (i3 >= this.mGestureHandlersCount) {
                break;
            }
            GestureHandler gestureHandler2 = this.mGestureHandlers[i3];
            int i5 = i;
            if (shouldHandlerBeCancelledBy(gestureHandler2, gestureHandler)) {
                this.mHandlersToCancel[i] = gestureHandler2;
                i5 = i + 1;
            }
            i3++;
            i4 = i5;
        }
        int i6 = i;
        while (true) {
            int i7 = i6 - 1;
            if (i7 < 0) {
                break;
            }
            this.mHandlersToCancel[i7].cancel();
            i6 = i7;
        }
        int i8 = this.mAwaitingHandlersCount;
        while (true) {
            int i9 = i8 - 1;
            if (i9 < 0) {
                break;
            }
            GestureHandler gestureHandler3 = this.mAwaitingHandlers[i9];
            if (shouldHandlerBeCancelledBy(gestureHandler3, gestureHandler)) {
                gestureHandler3.cancel();
                gestureHandler3.mIsAwaiting = false;
            }
            i8 = i9;
        }
        cleanupAwaitingHandlers();
        gestureHandler.dispatchStateChange(4, 2);
        if (state != 4) {
            gestureHandler.dispatchStateChange(5, 4);
            if (state != 5) {
                gestureHandler.dispatchStateChange(0, 5);
            }
        }
    }

    private void recordHandlerIfNotPresent(GestureHandler gestureHandler, View view) {
        int i = 0;
        while (true) {
            int i2 = i;
            int i3 = this.mGestureHandlersCount;
            if (i2 >= i3) {
                GestureHandler[] gestureHandlerArr = this.mGestureHandlers;
                if (i3 >= gestureHandlerArr.length) {
                    throw new IllegalStateException("Too many recognizers");
                }
                this.mGestureHandlersCount = i3 + 1;
                gestureHandlerArr[i3] = gestureHandler;
                gestureHandler.mIsActive = false;
                gestureHandler.mIsAwaiting = false;
                gestureHandler.mActivationIndex = Integer.MAX_VALUE;
                gestureHandler.prepare(view, this);
                return;
            }
            if (this.mGestureHandlers[i2] == gestureHandler) {
                return;
            } else {
                i = i2 + 1;
            }
        }
    }

    private boolean recordViewHandlersForPointer(View view, float[] fArr, int i) {
        ArrayList<GestureHandler> handlersForView = this.mHandlerRegistry.getHandlersForView(view);
        boolean z = false;
        if (handlersForView != null) {
            int size = handlersForView.size();
            int i2 = 0;
            boolean z2 = false;
            while (true) {
                z = z2;
                if (i2 >= size) {
                    break;
                }
                GestureHandler gestureHandler = handlersForView.get(i2);
                boolean z3 = z;
                if (gestureHandler.isEnabled()) {
                    z3 = z;
                    if (gestureHandler.isWithinBounds(view, fArr[0], fArr[1])) {
                        recordHandlerIfNotPresent(gestureHandler, view);
                        gestureHandler.startTrackingPointer(i);
                        z3 = true;
                    }
                }
                i2++;
                z2 = z3;
            }
        }
        return z;
    }

    private void scheduleFinishedHandlersCleanup() {
        if (this.mIsHandlingTouch || this.mHandlingChangeSemaphore != 0) {
            this.mFinishedHandlersCleanupScheduled = true;
        } else {
            cleanupFinishedHandlers();
        }
    }

    private static boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if (!gestureHandler.hasCommonPointers(gestureHandler2) || canRunSimultaneously(gestureHandler, gestureHandler2)) {
            return false;
        }
        if (gestureHandler == gestureHandler2) {
            return true;
        }
        if (gestureHandler.mIsAwaiting || gestureHandler.getState() == 4) {
            return gestureHandler.shouldBeCancelledBy(gestureHandler2);
        }
        return true;
    }

    private static boolean shouldHandlerWaitForOther(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if (gestureHandler != gestureHandler2) {
            return gestureHandler.shouldWaitForHandlerFailure(gestureHandler2) || gestureHandler2.shouldRequireToWaitForFailure(gestureHandler);
        }
        return false;
    }

    private static boolean shouldHandlerlessViewBecomeTouchTarget(View view, float[] fArr) {
        boolean z = false;
        if (((view instanceof ViewGroup) && view.getBackground() == null) ? false : true) {
            z = false;
            if (isTransformedTouchPointInView(fArr[0], fArr[1], view)) {
                z = true;
            }
        }
        return z;
    }

    private static void transformTouchPointToViewCoords(float f, float f2, ViewGroup viewGroup, View view, PointF pointF) {
        float scrollX = (f + viewGroup.getScrollX()) - view.getLeft();
        float scrollY = (f2 + viewGroup.getScrollY()) - view.getTop();
        Matrix matrix = view.getMatrix();
        float f3 = scrollX;
        float f4 = scrollY;
        if (!matrix.isIdentity()) {
            float[] fArr = sMatrixTransformCoords;
            fArr[0] = scrollX;
            fArr[1] = scrollY;
            Matrix matrix2 = sInverseMatrix;
            matrix.invert(matrix2);
            matrix2.mapPoints(fArr);
            f3 = fArr[0];
            f4 = fArr[1];
        }
        pointF.set(f3, f4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0095, code lost:            if (shouldHandlerlessViewBecomeTouchTarget(r6, r7) != false) goto L35;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean traverseWithPointerEvents(android.view.View r6, float[] r7, int r8) {
        /*
            r5 = this;
            r0 = r5
            com.swmansion.gesturehandler.ViewConfigurationHelper r0 = r0.mViewConfigHelper
            r1 = r6
            com.swmansion.gesturehandler.PointerEventsConfig r0 = r0.getPointerEventsConfigForView(r1)
            r11 = r0
            com.swmansion.gesturehandler.PointerEventsConfig r0 = com.swmansion.gesturehandler.PointerEventsConfig.NONE
            r12 = r0
            r0 = 0
            r10 = r0
            r0 = 0
            r9 = r0
            r0 = r11
            r1 = r12
            if (r0 != r1) goto L20
            r0 = 0
            return r0
        L20:
            r0 = r11
            com.swmansion.gesturehandler.PointerEventsConfig r1 = com.swmansion.gesturehandler.PointerEventsConfig.BOX_ONLY
            if (r0 != r1) goto L40
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r8
            boolean r0 = r0.recordViewHandlersForPointer(r1, r2, r3)
            if (r0 != 0) goto L3a
            r0 = r6
            r1 = r7
            boolean r0 = shouldHandlerlessViewBecomeTouchTarget(r0, r1)
            if (r0 == 0) goto L3d
        L3a:
            r0 = 1
            r9 = r0
        L3d:
            r0 = r9
            return r0
        L40:
            r0 = r11
            com.swmansion.gesturehandler.PointerEventsConfig r1 = com.swmansion.gesturehandler.PointerEventsConfig.BOX_NONE
            if (r0 != r1) goto L5c
            r0 = r6
            boolean r0 = r0 instanceof android.view.ViewGroup
            if (r0 == 0) goto L5a
            r0 = r5
            r1 = r6
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r2 = r7
            r3 = r8
            boolean r0 = r0.extractGestureHandlers(r1, r2, r3)
            return r0
        L5a:
            r0 = 0
            return r0
        L5c:
            r0 = r11
            com.swmansion.gesturehandler.PointerEventsConfig r1 = com.swmansion.gesturehandler.PointerEventsConfig.AUTO
            if (r0 != r1) goto L9e
            r0 = r6
            boolean r0 = r0 instanceof android.view.ViewGroup
            if (r0 == 0) goto L7a
            r0 = r5
            r1 = r6
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r2 = r7
            r3 = r8
            boolean r0 = r0.extractGestureHandlers(r1, r2, r3)
            r9 = r0
            goto L7d
        L7a:
            r0 = 0
            r9 = r0
        L7d:
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r8
            boolean r0 = r0.recordViewHandlersForPointer(r1, r2, r3)
            if (r0 != 0) goto L98
            r0 = r9
            if (r0 != 0) goto L98
            r0 = r10
            r9 = r0
            r0 = r6
            r1 = r7
            boolean r0 = shouldHandlerlessViewBecomeTouchTarget(r0, r1)
            if (r0 == 0) goto L9b
        L98:
            r0 = 1
            r9 = r0
        L9b:
            r0 = r9
            return r0
        L9e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r6 = r0
            r0 = r6
            java.lang.String r1 = "Unknown pointer event type: "
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            r1 = r11
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = r0
            r2 = r6
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.GestureHandlerOrchestrator.traverseWithPointerEvents(android.view.View, float[], int):boolean");
    }

    private void tryActivate(GestureHandler gestureHandler) {
        if (hasOtherHandlerToWaitFor(gestureHandler)) {
            addAwaitingHandler(gestureHandler);
        } else {
            makeActive(gestureHandler);
            gestureHandler.mIsAwaiting = false;
        }
    }

    public void deliverEventToGestureHandlers(MotionEvent motionEvent) {
        int i = this.mGestureHandlersCount;
        System.arraycopy(this.mGestureHandlers, 0, this.mPreparedHandlers, 0, i);
        Arrays.sort(this.mPreparedHandlers, 0, i, sHandlersComparator);
        for (int i2 = 0; i2 < i; i2++) {
            deliverEventToGestureHandler(this.mPreparedHandlers[i2], motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onHandlerStateChange(GestureHandler gestureHandler, int i, int i2) {
        this.mHandlingChangeSemaphore++;
        if (isFinished(i)) {
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= this.mAwaitingHandlersCount) {
                    break;
                }
                GestureHandler gestureHandler2 = this.mAwaitingHandlers[i4];
                if (shouldHandlerWaitForOther(gestureHandler2, gestureHandler)) {
                    if (i == 5) {
                        gestureHandler2.cancel();
                        gestureHandler2.mIsAwaiting = false;
                    } else {
                        tryActivate(gestureHandler2);
                    }
                }
                i3 = i4 + 1;
            }
            cleanupAwaitingHandlers();
        }
        if (i == 4) {
            tryActivate(gestureHandler);
        } else if (i2 != 4 && i2 != 5) {
            gestureHandler.dispatchStateChange(i, i2);
        } else if (gestureHandler.mIsActive) {
            gestureHandler.dispatchStateChange(i, i2);
        }
        this.mHandlingChangeSemaphore--;
        scheduleFinishedHandlersCleanup();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mIsHandlingTouch = true;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5) {
            extractGestureHandlers(motionEvent);
        } else if (actionMasked == 3) {
            cancelAll();
        }
        deliverEventToGestureHandlers(motionEvent);
        this.mIsHandlingTouch = false;
        if (!this.mFinishedHandlersCleanupScheduled || this.mHandlingChangeSemaphore != 0) {
            return true;
        }
        cleanupFinishedHandlers();
        return true;
    }

    public void setMinimumAlphaForTraversal(float f) {
        this.mMinAlphaForTraversal = f;
    }
}
