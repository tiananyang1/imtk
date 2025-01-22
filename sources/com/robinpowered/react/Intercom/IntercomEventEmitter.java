package com.robinpowered.react.Intercom;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.microsoft.codepush.react.CodePushConstants;
import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.UnreadConversationCountListener;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/robinpowered/react/Intercom/IntercomEventEmitter.class */
public class IntercomEventEmitter extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "IntercomEventEmitter";
    public static final String TAG = "Intercom Event";
    private static final String UNREAD_CHANGE_NOTIFICATION = "IntercomUnreadConversationCountDidChangeNotification";
    private final UnreadConversationCountListener unreadConversationCountListener;

    public IntercomEventEmitter(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.unreadConversationCountListener = new UnreadConversationCountListener() { // from class: com.robinpowered.react.Intercom.IntercomEventEmitter.1
            public void onCountUpdate(int i) {
                IntercomEventEmitter.this.handleUpdateUnreadCount();
            }
        };
        try {
            Intercom.client().addUnreadConversationCountListener(this.unreadConversationCountListener);
        } catch (Exception e) {
            Log.e(TAG, "client called before Intercom initialization");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUpdateUnreadCount() {
        try {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt(CodePushConstants.LATEST_ROLLBACK_COUNT_KEY, Intercom.client().getUnreadConversationCount());
            sendEvent(UNREAD_CHANGE_NOTIFICATION, createMap);
        } catch (Exception e) {
            Log.e(TAG, "client called before Intercom initialization");
        }
    }

    private void sendEvent(String str, @Nullable WritableMap writableMap) {
        if (getReactApplicationContext().hasActiveCatalystInstance()) {
            try {
                getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(str, writableMap);
            } catch (Exception e) {
                Log.e(TAG, "sendEvent called before bundle loaded");
            }
        }
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("UNREAD_CHANGE_NOTIFICATION", UNREAD_CHANGE_NOTIFICATION);
        return hashMap;
    }

    public String getName() {
        return MODULE_NAME;
    }
}
