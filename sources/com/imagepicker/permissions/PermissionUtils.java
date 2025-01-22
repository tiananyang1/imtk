package com.imagepicker.permissions;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.imagepicker.ImagePickerModule;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.lang.ref.WeakReference;

/* loaded from: classes08-dex2jar.jar:com/imagepicker/permissions/PermissionUtils.class */
public class PermissionUtils {

    /* loaded from: classes08-dex2jar.jar:com/imagepicker/permissions/PermissionUtils$OnExplainingPermissionCallback.class */
    public interface OnExplainingPermissionCallback {
        void onCancel(WeakReference<ImagePickerModule> weakReference, DialogInterface dialogInterface);

        void onReTry(WeakReference<ImagePickerModule> weakReference, DialogInterface dialogInterface);
    }

    @Nullable
    public static AlertDialog explainingDialog(@NonNull ImagePickerModule imagePickerModule, @NonNull ReadableMap readableMap, @NonNull final OnExplainingPermissionCallback onExplainingPermissionCallback) {
        if (imagePickerModule.getContext() == null || !readableMap.hasKey("permissionDenied")) {
            return null;
        }
        ReadableNativeMap map = readableMap.getMap("permissionDenied");
        if (map.toHashMap().size() == 0) {
            return null;
        }
        String string = map.getString(SettingsJsonConstants.PROMPT_TITLE_KEY);
        String string2 = map.getString("text");
        String string3 = map.getString("reTryTitle");
        String string4 = map.getString("okTitle");
        final WeakReference weakReference = new WeakReference(imagePickerModule);
        Activity activity = imagePickerModule.getActivity();
        if (activity == null) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, imagePickerModule.getDialogThemeId());
        builder.setTitle(string).setMessage(string2).setCancelable(false).setNegativeButton(string4, new DialogInterface.OnClickListener() { // from class: com.imagepicker.permissions.PermissionUtils.2
            @Override // android.content.DialogInterface.OnClickListener
            @SensorsDataInstrumented
            public void onClick(DialogInterface dialogInterface, int i) {
                OnExplainingPermissionCallback.this.onCancel(weakReference, dialogInterface);
                SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
            }
        }).setPositiveButton(string3, new DialogInterface.OnClickListener() { // from class: com.imagepicker.permissions.PermissionUtils.1
            @Override // android.content.DialogInterface.OnClickListener
            @SensorsDataInstrumented
            public void onClick(DialogInterface dialogInterface, int i) {
                OnExplainingPermissionCallback.this.onReTry(weakReference, dialogInterface);
                SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
            }
        });
        return builder.create();
    }
}
