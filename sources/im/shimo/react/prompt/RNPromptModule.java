package im.shimo.react.prompt;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = RNPromptModule.NAME)
/* loaded from: classes08-dex2jar.jar:im/shimo/react/prompt/RNPromptModule.class */
public class RNPromptModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    static final String FRAGMENT_TAG = "im.shimo.react.prompt.RNPromptModule";
    static final String KEY_CANCELABLE = "cancelable";
    static final String KEY_DEFAULT_VALUE = "defaultValue";
    static final String KEY_ITEMS = "items";
    static final String KEY_MESSAGE = "message";
    static final String KEY_PLACEHOLDER = "placeholder";
    static final String KEY_STYLE = "style";
    static final String KEY_TITLE = "title";
    static final String KEY_TYPE = "type";
    static final String NAME = "PromptAndroid";
    private boolean mIsInForeground;
    static final String ACTION_BUTTON_CLICKED = "buttonClicked";
    static final String ACTION_DISMISSED = "dismissed";
    static final String KEY_BUTTON_POSITIVE = "buttonPositive";
    static final String KEY_BUTTON_NEGATIVE = "buttonNegative";
    static final String KEY_BUTTON_NEUTRAL = "buttonNeutral";
    static final Map<String, Object> CONSTANTS = MapBuilder.of(ACTION_BUTTON_CLICKED, ACTION_BUTTON_CLICKED, ACTION_DISMISSED, ACTION_DISMISSED, KEY_BUTTON_POSITIVE, -1, KEY_BUTTON_NEGATIVE, -2, KEY_BUTTON_NEUTRAL, -3);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:im/shimo/react/prompt/RNPromptModule$FragmentManagerHelper.class */
    public class FragmentManagerHelper {

        @Nullable
        private final FragmentManager mFragmentManager;

        @Nullable
        private RNPromptFragment mFragmentToShow;

        public FragmentManagerHelper(FragmentManager fragmentManager) {
            this.mFragmentManager = fragmentManager;
        }

        private void dismissExisting() {
            RNPromptFragment rNPromptFragment;
            FragmentManager fragmentManager = this.mFragmentManager;
            if (fragmentManager == null || (rNPromptFragment = (RNPromptFragment) fragmentManager.findFragmentByTag(RNPromptModule.FRAGMENT_TAG)) == null) {
                return;
            }
            rNPromptFragment.dismiss();
        }

        public void showNewAlert(boolean z, Bundle bundle, Callback callback) {
            dismissExisting();
            PromptFragmentListener promptFragmentListener = callback != null ? new PromptFragmentListener(callback) : null;
            RNPromptFragment rNPromptFragment = new RNPromptFragment();
            rNPromptFragment.setListener(promptFragmentListener);
            rNPromptFragment.setArguments(bundle);
            if (!z) {
                this.mFragmentToShow = rNPromptFragment;
                return;
            }
            if (bundle.containsKey(RNPromptModule.KEY_CANCELABLE)) {
                rNPromptFragment.setCancelable(bundle.getBoolean(RNPromptModule.KEY_CANCELABLE));
            }
            rNPromptFragment.show(this.mFragmentManager, RNPromptModule.FRAGMENT_TAG);
        }

        public void showPendingAlert() {
            RNPromptFragment rNPromptFragment = this.mFragmentToShow;
            if (rNPromptFragment == null) {
                return;
            }
            rNPromptFragment.show(this.mFragmentManager, RNPromptModule.FRAGMENT_TAG);
            this.mFragmentToShow = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:im/shimo/react/prompt/RNPromptModule$PromptFragmentListener.class */
    public class PromptFragmentListener implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
        private final Callback mCallback;
        private boolean mCallbackConsumed = false;

        public PromptFragmentListener(Callback callback) {
            this.mCallback = callback;
        }

        @Override // android.content.DialogInterface.OnClickListener
        @SensorsDataInstrumented
        public void onClick(DialogInterface dialogInterface, int i) {
            onConfirm(i, "");
            SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
        }

        public void onConfirm(int i, String str) {
            if (this.mCallbackConsumed || !RNPromptModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                return;
            }
            this.mCallback.invoke(new Object[]{RNPromptModule.ACTION_BUTTON_CLICKED, Integer.valueOf(i), str});
            this.mCallbackConsumed = true;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (this.mCallbackConsumed || !RNPromptModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                return;
            }
            this.mCallback.invoke(new Object[]{RNPromptModule.ACTION_DISMISSED});
            this.mCallbackConsumed = true;
        }
    }

    public RNPromptModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    private FragmentManagerHelper getFragmentManagerHelper() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return null;
        }
        return new FragmentManagerHelper(currentActivity.getFragmentManager());
    }

    public Map<String, Object> getConstants() {
        return CONSTANTS;
    }

    public String getName() {
        return NAME;
    }

    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    public void onHostDestroy() {
    }

    public void onHostPause() {
        this.mIsInForeground = false;
    }

    public void onHostResume() {
        this.mIsInForeground = true;
        FragmentManagerHelper fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper != null) {
            fragmentManagerHelper.showPendingAlert();
        } else {
            FLog.w(DialogModule.class, "onHostResume called but no FragmentManager found");
        }
    }

    @ReactMethod
    public void promptWithArgs(ReadableMap readableMap, Callback callback) {
        FragmentManagerHelper fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper == null) {
            FLog.w(RNPromptModule.class, "Tried to show an alert while not attached to an Activity");
            return;
        }
        Bundle bundle = new Bundle();
        if (readableMap.hasKey("title")) {
            bundle.putString("title", readableMap.getString("title"));
        }
        if (readableMap.hasKey("message") && !readableMap.getString("message").isEmpty()) {
            bundle.putString("message", readableMap.getString("message"));
        }
        if (readableMap.hasKey(KEY_BUTTON_POSITIVE)) {
            bundle.putString("button_positive", readableMap.getString(KEY_BUTTON_POSITIVE));
        }
        if (readableMap.hasKey(KEY_BUTTON_NEGATIVE)) {
            bundle.putString("button_negative", readableMap.getString(KEY_BUTTON_NEGATIVE));
        }
        if (readableMap.hasKey(KEY_BUTTON_NEUTRAL)) {
            bundle.putString("button_neutral", readableMap.getString(KEY_BUTTON_NEUTRAL));
        }
        if (readableMap.hasKey(KEY_ITEMS)) {
            ReadableArray array = readableMap.getArray(KEY_ITEMS);
            CharSequence[] charSequenceArr = new CharSequence[array.size()];
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= array.size()) {
                    break;
                }
                charSequenceArr[i2] = array.getString(i2);
                i = i2 + 1;
            }
            bundle.putCharSequenceArray(KEY_ITEMS, charSequenceArr);
        }
        if (readableMap.hasKey(KEY_CANCELABLE)) {
            bundle.putBoolean(KEY_CANCELABLE, readableMap.getBoolean(KEY_CANCELABLE));
        }
        if (readableMap.hasKey(KEY_TYPE)) {
            bundle.putString(KEY_TYPE, readableMap.getString(KEY_TYPE));
        }
        if (readableMap.hasKey(KEY_STYLE)) {
            bundle.putString(KEY_STYLE, readableMap.getString(KEY_STYLE));
        }
        if (readableMap.hasKey(KEY_DEFAULT_VALUE)) {
            bundle.putString(KEY_DEFAULT_VALUE, readableMap.getString(KEY_DEFAULT_VALUE));
        }
        if (readableMap.hasKey(KEY_PLACEHOLDER)) {
            bundle.putString(KEY_PLACEHOLDER, readableMap.getString(KEY_PLACEHOLDER));
        }
        fragmentManagerHelper.showNewAlert(this.mIsInForeground, bundle, callback);
    }
}
