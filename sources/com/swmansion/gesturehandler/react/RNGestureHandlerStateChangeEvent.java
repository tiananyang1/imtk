package com.swmansion.gesturehandler.react;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.swmansion.gesturehandler.GestureHandler;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerStateChangeEvent.class */
public class RNGestureHandlerStateChangeEvent extends Event<RNGestureHandlerStateChangeEvent> {
    private static final Pools.SynchronizedPool<RNGestureHandlerStateChangeEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(7);
    public static final String EVENT_NAME = "onGestureHandlerStateChange";
    private static final int TOUCH_EVENTS_POOL_SIZE = 7;
    private WritableMap mExtraData;

    private RNGestureHandlerStateChangeEvent() {
    }

    private void init(GestureHandler gestureHandler, int i, int i2, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        super.init(gestureHandler.getView().getId());
        this.mExtraData = Arguments.createMap();
        if (rNGestureHandlerEventDataExtractor != null) {
            rNGestureHandlerEventDataExtractor.extractEventData(gestureHandler, this.mExtraData);
        }
        this.mExtraData.putInt("handlerTag", gestureHandler.getTag());
        this.mExtraData.putInt("state", i);
        this.mExtraData.putInt("oldState", i2);
    }

    public static RNGestureHandlerStateChangeEvent obtain(GestureHandler gestureHandler, int i, int i2, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        RNGestureHandlerStateChangeEvent rNGestureHandlerStateChangeEvent = (RNGestureHandlerStateChangeEvent) EVENTS_POOL.acquire();
        RNGestureHandlerStateChangeEvent rNGestureHandlerStateChangeEvent2 = rNGestureHandlerStateChangeEvent;
        if (rNGestureHandlerStateChangeEvent == null) {
            rNGestureHandlerStateChangeEvent2 = new RNGestureHandlerStateChangeEvent();
        }
        rNGestureHandlerStateChangeEvent2.init(gestureHandler, i, i2, rNGestureHandlerEventDataExtractor);
        return rNGestureHandlerStateChangeEvent2;
    }

    public boolean canCoalesce() {
        return false;
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), EVENT_NAME, this.mExtraData);
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public void onDispose() {
        this.mExtraData = null;
        EVENTS_POOL.release(this);
    }
}
