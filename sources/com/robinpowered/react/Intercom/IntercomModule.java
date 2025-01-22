package com.robinpowered.react.Intercom;

import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import io.intercom.android.sdk.Company;
import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.UserAttributes;
import io.intercom.android.sdk.identity.Registration;
import io.intercom.android.sdk.push.IntercomPushClient;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/robinpowered/react/Intercom/IntercomModule.class */
public class IntercomModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "IntercomWrapper";
    public static final String TAG = "Intercom";
    private final IntercomPushClient intercomPushClient;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.robinpowered.react.Intercom.IntercomModule$1 */
    /* loaded from: classes08-dex2jar.jar:com/robinpowered/react/Intercom/IntercomModule$1.class */
    public static /* synthetic */ class C01841 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:36:0x005d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.robinpowered.react.Intercom.IntercomModule.C01841.$SwitchMap$com$facebook$react$bridge$ReadableType = r0
                int[] r0 = com.robinpowered.react.Intercom.IntercomModule.C01841.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L4d
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L14:
                int[] r0 = com.robinpowered.react.Intercom.IntercomModule.C01841.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
            L1f:
                int[] r0 = com.robinpowered.react.Intercom.IntercomModule.C01841.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L51 java.lang.NoSuchFieldError -> L55
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch: java.lang.NoSuchFieldError -> L55
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L55
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L55
            L2a:
                int[] r0 = com.robinpowered.react.Intercom.IntercomModule.C01841.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L55 java.lang.NoSuchFieldError -> L59
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch: java.lang.NoSuchFieldError -> L59
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L59
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L59
            L35:
                int[] r0 = com.robinpowered.react.Intercom.IntercomModule.C01841.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L59 java.lang.NoSuchFieldError -> L5d
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch: java.lang.NoSuchFieldError -> L5d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L5d
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L5d
            L40:
                int[] r0 = com.robinpowered.react.Intercom.IntercomModule.C01841.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L5d java.lang.NoSuchFieldError -> L61
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch: java.lang.NoSuchFieldError -> L61
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L61
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L61
                return
            L4d:
                r4 = move-exception
                goto L14
            L51:
                r4 = move-exception
                goto L1f
            L55:
                r4 = move-exception
                goto L2a
            L59:
                r4 = move-exception
                goto L35
            L5d:
                r4 = move-exception
                goto L40
            L61:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.robinpowered.react.Intercom.IntercomModule.C01841.m3724clinit():void");
        }
    }

    public IntercomModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.intercomPushClient = new IntercomPushClient();
    }

    private UserAttributes convertToUserAttributes(ReadableMap readableMap) {
        Map<String, Object> recursivelyDeconstructReadableMap = recursivelyDeconstructReadableMap(readableMap);
        UserAttributes.Builder builder = new UserAttributes.Builder();
        for (Map.Entry<String, Object> entry : recursivelyDeconstructReadableMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.equals("email")) {
                builder.withEmail((String) value);
            } else if (key.equals("user_id")) {
                builder.withUserId((String) value);
            } else if (key.equals("name")) {
                builder.withName((String) value);
            } else if (key.equals("phone")) {
                builder.withPhone((String) value);
            } else if (key.equals("language_override")) {
                builder.withLanguageOverride((String) value);
            } else if (key.equals("signed_up_at")) {
                builder.withSignedUpAt(new Date(((Number) value).longValue() * 1000));
            } else if (key.equals("unsubscribed_from_emails")) {
                builder.withUnsubscribedFromEmails((Boolean) value);
            } else if (key.equals("custom_attributes")) {
                builder.withCustomAttributes((Map) value);
            } else if (key.equals("companies")) {
                Iterator it = ((ArrayList) value).iterator();
                while (it.hasNext()) {
                    Map map = (Map) it.next();
                    if (map.containsKey("company_id")) {
                        Company.Builder builder2 = new Company.Builder();
                        builder2.withCompanyId(map.get("company_id").toString());
                        if (map.containsKey("name")) {
                            builder2.withName(map.get("name").toString());
                        }
                        builder.withCompany(builder2.build());
                    }
                }
            }
        }
        return builder.build();
    }

    private List<Object> recursivelyDeconstructReadableArray(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= readableArray.size()) {
                return arrayList;
            }
            switch (C01841.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i2).ordinal()]) {
                case 1:
                    arrayList.add(i2, null);
                    break;
                case 2:
                    arrayList.add(i2, Boolean.valueOf(readableArray.getBoolean(i2)));
                    break;
                case 3:
                    arrayList.add(i2, Double.valueOf(readableArray.getDouble(i2)));
                    break;
                case 4:
                    arrayList.add(i2, readableArray.getString(i2));
                    break;
                case 5:
                    arrayList.add(i2, recursivelyDeconstructReadableMap(readableArray.getMap(i2)));
                    break;
                case 6:
                    arrayList.add(i2, recursivelyDeconstructReadableArray(readableArray.getArray(i2)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object at index " + i2 + ".");
            }
            i = i2 + 1;
        }
    }

    private Map<String, Object> recursivelyDeconstructReadableMap(ReadableMap readableMap) {
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        HashMap hashMap = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (C01841.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(nextKey).ordinal()]) {
                case 1:
                    hashMap.put(nextKey, null);
                    break;
                case 2:
                    hashMap.put(nextKey, Boolean.valueOf(readableMap.getBoolean(nextKey)));
                    break;
                case 3:
                    hashMap.put(nextKey, Double.valueOf(readableMap.getDouble(nextKey)));
                    break;
                case 4:
                    hashMap.put(nextKey, readableMap.getString(nextKey));
                    break;
                case 5:
                    hashMap.put(nextKey, recursivelyDeconstructReadableMap(readableMap.getMap(nextKey)));
                    break;
                case 6:
                    hashMap.put(nextKey, recursivelyDeconstructReadableArray(readableMap.getArray(nextKey)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + nextKey + ".");
            }
        }
        return hashMap;
    }

    private Intercom.Visibility visibilityStringToVisibility(String str) {
        return str.equalsIgnoreCase("VISIBLE") ? Intercom.Visibility.VISIBLE : Intercom.Visibility.GONE;
    }

    @ReactMethod
    public void displayConversationsList(Promise promise) {
        try {
            Intercom.client().displayConversationsList();
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void displayHelpCenter(Promise promise) {
        try {
            Intercom.client().displayHelpCenter();
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void displayMessageComposer(Promise promise) {
        try {
            Intercom.client().displayMessageComposer();
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void displayMessageComposerWithInitialMessage(String str, Promise promise) {
        try {
            Intercom.client().displayMessageComposer(str);
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void displayMessenger(Promise promise) {
        try {
            Intercom.client().displayMessenger();
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getUnreadConversationCount(Promise promise) {
        try {
            promise.resolve(Integer.valueOf(Intercom.client().getUnreadConversationCount()));
        } catch (Exception e) {
            Log.e(TAG, "logEvent - unable to get conversation count");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void handlePushMessage(Promise promise) {
        try {
            Intercom.client().handlePushMessage();
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void hideMessenger(Promise promise) {
        try {
            Intercom.client().hideMessenger();
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void logEvent(String str, @Nullable ReadableMap readableMap, Promise promise) {
        if (readableMap == null) {
            try {
                Intercom.client().logEvent(str);
            } catch (Exception e) {
                Log.e(TAG, "logEvent - unable to deconstruct metaData");
                promise.reject(e.toString());
                return;
            }
        }
        if (readableMap != null) {
            Intercom.client().logEvent(str, recursivelyDeconstructReadableMap(readableMap));
        }
        Log.i(TAG, "logEvent");
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void logout(Promise promise) {
        try {
            Intercom.client().logout();
            Log.i(TAG, "logout");
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void registerIdentifiedUser(ReadableMap readableMap, Promise promise) {
        try {
            if (readableMap.hasKey("email") && readableMap.getString("email").length() > 0) {
                Intercom.client().registerIdentifiedUser(new Registration().withEmail(readableMap.getString("email")));
                Log.i(TAG, "registerIdentifiedUser with userEmail");
                promise.resolve((Object) null);
            } else if (!readableMap.hasKey("userId") || readableMap.getString("userId").length() <= 0) {
                Log.e(TAG, "registerIdentifiedUser called with invalid userId or email");
                promise.reject("Invalid userId or email");
            } else {
                Intercom.client().registerIdentifiedUser(new Registration().withUserId(readableMap.getString("userId")));
                Log.i(TAG, "registerIdentifiedUser with userId");
                promise.resolve((Object) null);
            }
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void registerUnidentifiedUser(Promise promise) {
        try {
            Intercom.client().registerUnidentifiedUser();
            Log.i(TAG, "registerUnidentifiedUser");
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void sendTokenToIntercom(String str, Promise promise) {
        try {
            if (getCurrentActivity() == null) {
                Log.e(TAG, "sendTokenToIntercom; getCurrentActivity() is null");
                return;
            }
            this.intercomPushClient.sendTokenToIntercom(getCurrentActivity().getApplication(), str);
            Log.i(TAG, "sendTokenToIntercom");
            promise.resolve((Object) null);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void setBottomPadding(Integer num, Promise promise) {
        try {
            Intercom.client().setBottomPadding(num.intValue());
            Log.i(TAG, "setBottomPadding");
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void setInAppMessageVisibility(String str, Promise promise) {
        try {
            Intercom.client().setInAppMessageVisibility(visibilityStringToVisibility(str));
            promise.resolve((Object) null);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void setLauncherVisibility(String str, Promise promise) {
        try {
            Intercom.client().setLauncherVisibility(visibilityStringToVisibility(str));
            promise.resolve((Object) null);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void setUserHash(String str, Promise promise) {
        try {
            Intercom.client().setUserHash(str);
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "Intercom not initialized");
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void updateUser(ReadableMap readableMap, Promise promise) {
        try {
            Intercom.client().updateUser(convertToUserAttributes(readableMap));
            Log.i(TAG, "updateUser");
            promise.resolve((Object) null);
        } catch (Exception e) {
            Log.e(TAG, "updateUser - unable to deconstruct argument map");
            promise.reject(e.toString());
        }
    }
}
