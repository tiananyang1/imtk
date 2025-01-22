package com.swmansion.gesturehandler.react;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = RNGestureHandlerRootViewManager.REACT_CLASS)
/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerRootViewManager.class */
public class RNGestureHandlerRootViewManager extends ViewGroupManager<RNGestureHandlerRootView> {
    public static final String REACT_CLASS = "GestureHandlerRootView";

    /* JADX INFO: Access modifiers changed from: protected */
    public RNGestureHandlerRootView createViewInstance(ThemedReactContext themedReactContext) {
        return new RNGestureHandlerRootView(themedReactContext);
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(RNGestureHandlerEvent.EVENT_NAME, MapBuilder.of("registrationName", RNGestureHandlerEvent.EVENT_NAME), RNGestureHandlerStateChangeEvent.EVENT_NAME, MapBuilder.of("registrationName", RNGestureHandlerStateChangeEvent.EVENT_NAME));
    }

    public String getName() {
        return REACT_CLASS;
    }

    public void onDropViewInstance(RNGestureHandlerRootView rNGestureHandlerRootView) {
        rNGestureHandlerRootView.tearDown();
    }
}
