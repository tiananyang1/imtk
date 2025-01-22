package com.swmansion.gesturehandler.react;

import android.util.SparseArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerInteractionController;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerInteractionManager.class */
public class RNGestureHandlerInteractionManager implements GestureHandlerInteractionController {
    private static final String KEY_SIMULTANEOUS_HANDLERS = "simultaneousHandlers";
    private static final String KEY_WAIT_FOR = "waitFor";
    private SparseArray<int[]> mWaitForRelations = new SparseArray<>();
    private SparseArray<int[]> mSimultaneousRelations = new SparseArray<>();

    private int[] convertHandlerTagsArray(ReadableMap readableMap, String str) {
        ReadableArray array = readableMap.getArray(str);
        int[] iArr = new int[array.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= iArr.length) {
                return iArr;
            }
            iArr[i2] = array.getInt(i2);
            i = i2 + 1;
        }
    }

    public void configureInteractions(GestureHandler gestureHandler, ReadableMap readableMap) {
        gestureHandler.setInteractionController(this);
        if (readableMap.hasKey(KEY_WAIT_FOR)) {
            this.mWaitForRelations.put(gestureHandler.getTag(), convertHandlerTagsArray(readableMap, KEY_WAIT_FOR));
        }
        if (readableMap.hasKey(KEY_SIMULTANEOUS_HANDLERS)) {
            this.mSimultaneousRelations.put(gestureHandler.getTag(), convertHandlerTagsArray(readableMap, KEY_SIMULTANEOUS_HANDLERS));
        }
    }

    public void dropRelationsForHandlerWithTag(int i) {
        this.mWaitForRelations.remove(i);
        this.mSimultaneousRelations.remove(i);
    }

    public void reset() {
        this.mWaitForRelations.clear();
        this.mSimultaneousRelations.clear();
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        int[] iArr = this.mSimultaneousRelations.get(gestureHandler.getTag());
        if (iArr == null) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= iArr.length) {
                return false;
            }
            if (iArr[i2] == gestureHandler2.getTag()) {
                return true;
            }
            i = i2 + 1;
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldRequireHandlerToWaitForFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        int[] iArr = this.mWaitForRelations.get(gestureHandler.getTag());
        if (iArr == null) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= iArr.length) {
                return false;
            }
            if (iArr[i2] == gestureHandler2.getTag()) {
                return true;
            }
            i = i2 + 1;
        }
    }
}
