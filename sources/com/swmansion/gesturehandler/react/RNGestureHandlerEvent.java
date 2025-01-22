package com.swmansion.gesturehandler.react;

import android.support.v4.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.swmansion.gesturehandler.GestureHandler;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerEvent.class */
public class RNGestureHandlerEvent extends Event<RNGestureHandlerEvent> {
    private static final Pools.SynchronizedPool<RNGestureHandlerEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(7);
    public static final String EVENT_NAME = "onGestureHandlerEvent";
    private static final int TOUCH_EVENTS_POOL_SIZE = 7;
    private WritableMap mExtraData;

    private RNGestureHandlerEvent() {
    }

    private void init(GestureHandler gestureHandler, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        super.init(gestureHandler.getView().getId());
        this.mExtraData = Arguments.createMap();
        if (rNGestureHandlerEventDataExtractor != null) {
            rNGestureHandlerEventDataExtractor.extractEventData(gestureHandler, this.mExtraData);
        }
        this.mExtraData.putInt("handlerTag", gestureHandler.getTag());
        this.mExtraData.putInt("state", gestureHandler.getState());
    }

    public static RNGestureHandlerEvent obtain(GestureHandler gestureHandler, @Nullable RNGestureHandlerEventDataExtractor rNGestureHandlerEventDataExtractor) {
        RNGestureHandlerEvent rNGestureHandlerEvent = (RNGestureHandlerEvent) EVENTS_POOL.acquire();
        RNGestureHandlerEvent rNGestureHandlerEvent2 = rNGestureHandlerEvent;
        if (rNGestureHandlerEvent == null) {
            rNGestureHandlerEvent2 = new RNGestureHandlerEvent();
        }
        rNGestureHandlerEvent2.init(gestureHandler, rNGestureHandlerEventDataExtractor);
        return rNGestureHandlerEvent2;
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
