package com.reactnativecommunity.webview.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\n\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018�� \u00112\b\u0012\u0004\u0012\u00020��0\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"Lcom/reactnativecommunity/webview/events/TopLoadingStartEvent;", "Lcom/facebook/react/uimanager/events/Event;", "viewId", "", "mEventData", "Lcom/facebook/react/bridge/WritableMap;", "(ILcom/facebook/react/bridge/WritableMap;)V", "canCoalesce", "", "dispatch", "", "rctEventEmitter", "Lcom/facebook/react/uimanager/events/RCTEventEmitter;", "getCoalescingKey", "", "getEventName", "", "Companion", "react-native-webview_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes08-dex2jar.jar:com/reactnativecommunity/webview/events/TopLoadingStartEvent.class */
public final class TopLoadingStartEvent extends Event<TopLoadingStartEvent> {
    public static final Companion Companion = new Companion(null);

    @NotNull
    public static final String EVENT_NAME = "topLoadingStart";
    private final WritableMap mEventData;

    @Metadata(bv = {1, 0, 3}, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��¨\u0006\u0005"}, d2 = {"Lcom/reactnativecommunity/webview/events/TopLoadingStartEvent$Companion;", "", "()V", "EVENT_NAME", "", "react-native-webview_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes08-dex2jar.jar:com/reactnativecommunity/webview/events/TopLoadingStartEvent$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TopLoadingStartEvent(int i, @NotNull WritableMap writableMap) {
        super(i);
        Intrinsics.checkParameterIsNotNull(writableMap, "mEventData");
        this.mEventData = writableMap;
    }

    public boolean canCoalesce() {
        return false;
    }

    public void dispatch(@NotNull RCTEventEmitter rCTEventEmitter) {
        Intrinsics.checkParameterIsNotNull(rCTEventEmitter, "rctEventEmitter");
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), this.mEventData);
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    @NotNull
    public String getEventName() {
        return EVENT_NAME;
    }
}
