package com.microsoft.codepush.react;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushDialog.class */
public class CodePushDialog extends ReactContextBaseJavaModule {
    public CodePushDialog(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialogInternal(String str, String str2, String str3, String str4, final Callback callback, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.microsoft.codepush.react.CodePushDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            @SensorsDataInstrumented
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    dialogInterface.cancel();
                    if (i == -2) {
                        callback.invoke(new Object[]{1});
                    } else {
                        if (i != -1) {
                            CodePushUnknownException codePushUnknownException = new CodePushUnknownException("Unknown button ID pressed.");
                            SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
                            throw codePushUnknownException;
                        }
                        callback.invoke(new Object[]{0});
                    }
                } catch (Throwable th) {
                    CodePushUtils.log(th);
                }
                SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
            }
        };
        if (str != null) {
            builder.setTitle(str);
        }
        if (str2 != null) {
            builder.setMessage(str2);
        }
        if (str3 != null) {
            builder.setPositiveButton(str3, onClickListener);
        }
        if (str4 != null) {
            builder.setNegativeButton(str4, onClickListener);
        }
        builder.create().show();
    }

    public String getName() {
        return "CodePushDialog";
    }

    @ReactMethod
    public void showDialog(final String str, final String str2, final String str3, final String str4, final Callback callback, Callback callback2) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            getReactApplicationContext().addLifecycleEventListener(new LifecycleEventListener() { // from class: com.microsoft.codepush.react.CodePushDialog.1
                public void onHostDestroy() {
                }

                public void onHostPause() {
                }

                public void onHostResume() {
                    Activity currentActivity2 = CodePushDialog.this.getCurrentActivity();
                    if (currentActivity2 != null) {
                        CodePushDialog.this.getReactApplicationContext().removeLifecycleEventListener(this);
                        CodePushDialog.this.showDialogInternal(str, str2, str3, str4, callback, currentActivity2);
                    }
                }
            });
        } else {
            showDialogInternal(str, str2, str3, str4, callback, currentActivity);
        }
    }
}
