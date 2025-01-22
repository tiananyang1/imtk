package com.sensorsdata.analytics.android.sdk;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/Pathfinder.class */
public class Pathfinder {
    private static final String TAG = "SA.PathFinder";
    private final IntStack mIndexStack = new IntStack();

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/Pathfinder$Accumulator.class */
    public interface Accumulator {
        void accumulate(View view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/Pathfinder$IntStack.class */
    public static class IntStack {
        private static final int MAX_INDEX_STACK_SIZE = 256;
        private final int[] mStack = new int[256];
        private int mStackSize = 0;

        public int alloc() {
            int i = this.mStackSize;
            this.mStackSize = i + 1;
            this.mStack[i] = 0;
            return i;
        }

        public void free() {
            this.mStackSize--;
            int i = this.mStackSize;
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException(i);
            }
        }

        public boolean full() {
            return this.mStack.length == this.mStackSize;
        }

        public void increment(int i) {
            int[] iArr = this.mStack;
            iArr[i] = iArr[i] + 1;
        }

        public int read(int i) {
            return this.mStack[i];
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/Pathfinder$PathElement.class */
    public static class PathElement {
        public static final int SHORTEST_PREFIX = 1;
        public static final int ZERO_LENGTH_PREFIX = 0;
        public final int index;
        public final int prefix;
        public final String viewClassName;
        public final int viewId;

        public PathElement(int i, String str, int i2, int i3) {
            this.prefix = i;
            this.viewClassName = str;
            this.index = i2;
            this.viewId = i3;
        }

        public String toString() {
            try {
                JSONObject jSONObject = new JSONObject();
                if (this.prefix == 1) {
                    jSONObject.put("prefix", "shortest");
                }
                if (this.viewClassName != null) {
                    jSONObject.put("view_class", this.viewClassName);
                }
                if (this.index > -1) {
                    jSONObject.put("index", this.index);
                }
                if (this.viewId > -1) {
                    jSONObject.put("id", this.viewId);
                }
                return jSONObject.toString();
            } catch (JSONException e) {
                throw new RuntimeException("Can't serialize PathElement to String", e);
            }
        }
    }

    private View findPrefixedMatch(PathElement pathElement, View view, int i) {
        View findPrefixedMatch;
        int read = this.mIndexStack.read(i);
        if (matches(pathElement, view)) {
            this.mIndexStack.increment(i);
            if (pathElement.index == -1 || pathElement.index == read) {
                return view;
            }
        }
        if (pathElement.prefix != 1 || !(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= childCount) {
                return null;
            }
            View childAt = viewGroup.getChildAt(i3);
            if (childAt != null && (findPrefixedMatch = findPrefixedMatch(pathElement, childAt, i)) != null) {
                return findPrefixedMatch;
            }
            i2 = i3 + 1;
        }
    }

    private void findTargetsInMatchedView(View view, List<PathElement> list, Accumulator accumulator) {
        if (list.isEmpty()) {
            accumulator.accumulate(view);
            return;
        }
        if (this.mIndexStack.full()) {
            SALog.m55i(TAG, "Path is too deep, there is no memory to perfrom the finding");
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            PathElement pathElement = list.get(0);
            List<PathElement> subList = list.subList(1, list.size());
            int childCount = viewGroup.getChildCount();
            int alloc = this.mIndexStack.alloc();
            for (int i = 0; i < childCount; i++) {
                View findPrefixedMatch = findPrefixedMatch(pathElement, viewGroup.getChildAt(i), alloc);
                if (findPrefixedMatch != null) {
                    findTargetsInMatchedView(findPrefixedMatch, subList, accumulator);
                }
                if (pathElement.index >= 0 && this.mIndexStack.read(alloc) > pathElement.index) {
                    break;
                }
            }
            this.mIndexStack.free();
        }
    }

    public static boolean hasClassName(Object obj, String str) {
        Class<?> cls = obj.getClass();
        while (true) {
            Class<?> cls2 = cls;
            if (cls2.getCanonicalName() == null) {
                return false;
            }
            if (cls2.getCanonicalName().equals(str)) {
                return true;
            }
            if (cls2 == Object.class) {
                return false;
            }
            cls = cls2.getSuperclass();
        }
    }

    private boolean matches(PathElement pathElement, View view) {
        boolean z = false;
        if (pathElement.viewClassName != null && !hasClassName(view, pathElement.viewClassName)) {
            return false;
        }
        if (-1 == pathElement.viewId || view.getId() == pathElement.viewId) {
            z = true;
        }
        return z;
    }

    public void findTargetsInRoot(View view, List<PathElement> list, Accumulator accumulator) {
        if (list.isEmpty()) {
            return;
        }
        if (this.mIndexStack.full()) {
            SALog.m55i(TAG, "Path is too deep, there is no memory to perfrom the finding");
            return;
        }
        PathElement pathElement = list.get(0);
        List<PathElement> subList = list.subList(1, list.size());
        View findPrefixedMatch = findPrefixedMatch(pathElement, view, this.mIndexStack.alloc());
        this.mIndexStack.free();
        if (findPrefixedMatch != null) {
            findTargetsInMatchedView(findPrefixedMatch, subList, accumulator);
        }
    }
}
