package com.swmansion.gesturehandler.react;

import android.util.SparseArray;
import android.view.View;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerRegistry;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerRegistry.class */
public class RNGestureHandlerRegistry implements GestureHandlerRegistry {
    private final SparseArray<GestureHandler> mHandlers = new SparseArray<>();
    private final SparseArray<Integer> mAttachedTo = new SparseArray<>();
    private final SparseArray<ArrayList<GestureHandler>> mHandlersForView = new SparseArray<>();

    private void detachHandler(GestureHandler gestureHandler) {
        synchronized (this) {
            Integer num = this.mAttachedTo.get(gestureHandler.getTag());
            if (num != null) {
                this.mAttachedTo.remove(gestureHandler.getTag());
                ArrayList<GestureHandler> arrayList = this.mHandlersForView.get(num.intValue());
                if (arrayList != null) {
                    arrayList.remove(gestureHandler);
                    if (arrayList.size() == 0) {
                        this.mHandlersForView.remove(num.intValue());
                    }
                }
            }
            if (gestureHandler.getView() != null) {
                gestureHandler.cancel();
            }
        }
    }

    private void registerHandlerForViewWithTag(int i, GestureHandler gestureHandler) {
        synchronized (this) {
            if (this.mAttachedTo.get(gestureHandler.getTag()) != null) {
                throw new IllegalStateException("Handler " + gestureHandler + " already attached");
            }
            this.mAttachedTo.put(gestureHandler.getTag(), Integer.valueOf(i));
            ArrayList<GestureHandler> arrayList = this.mHandlersForView.get(i);
            if (arrayList == null) {
                ArrayList<GestureHandler> arrayList2 = new ArrayList<>(1);
                arrayList2.add(gestureHandler);
                this.mHandlersForView.put(i, arrayList2);
            } else {
                arrayList.add(gestureHandler);
            }
        }
    }

    public boolean attachHandlerToView(int i, int i2) {
        synchronized (this) {
            GestureHandler gestureHandler = this.mHandlers.get(i);
            if (gestureHandler == null) {
                return false;
            }
            detachHandler(gestureHandler);
            registerHandlerForViewWithTag(i2, gestureHandler);
            return true;
        }
    }

    public void dropAllHandlers() {
        synchronized (this) {
            this.mHandlers.clear();
            this.mAttachedTo.clear();
            this.mHandlersForView.clear();
        }
    }

    public void dropHandler(int i) {
        synchronized (this) {
            GestureHandler gestureHandler = this.mHandlers.get(i);
            if (gestureHandler != null) {
                detachHandler(gestureHandler);
                this.mHandlers.remove(i);
            }
        }
    }

    @Nullable
    public GestureHandler getHandler(int i) {
        GestureHandler gestureHandler;
        synchronized (this) {
            gestureHandler = this.mHandlers.get(i);
        }
        return gestureHandler;
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerRegistry
    public ArrayList<GestureHandler> getHandlersForView(View view) {
        ArrayList<GestureHandler> handlersForViewWithTag;
        synchronized (this) {
            handlersForViewWithTag = getHandlersForViewWithTag(view.getId());
        }
        return handlersForViewWithTag;
    }

    public ArrayList<GestureHandler> getHandlersForViewWithTag(int i) {
        ArrayList<GestureHandler> arrayList;
        synchronized (this) {
            arrayList = this.mHandlersForView.get(i);
        }
        return arrayList;
    }

    public void registerHandler(GestureHandler gestureHandler) {
        synchronized (this) {
            this.mHandlers.put(gestureHandler.getTag(), gestureHandler);
        }
    }
}
