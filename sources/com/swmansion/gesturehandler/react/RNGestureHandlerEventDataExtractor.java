package com.swmansion.gesturehandler.react;

import com.facebook.react.bridge.WritableMap;
import com.swmansion.gesturehandler.GestureHandler;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerEventDataExtractor.class */
public interface RNGestureHandlerEventDataExtractor<T extends GestureHandler> {
    void extractEventData(T t, WritableMap writableMap);
}
