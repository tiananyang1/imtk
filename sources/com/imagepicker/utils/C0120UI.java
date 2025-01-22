package com.imagepicker.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import com.facebook.react.bridge.ReadableMap;
import com.imagepicker.C0116R;
import com.imagepicker.ImagePickerModule;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: com.imagepicker.utils.UI */
/* loaded from: classes08-dex2jar.jar:com/imagepicker/utils/UI.class */
public class C0120UI {

    /* renamed from: com.imagepicker.utils.UI$OnAction */
    /* loaded from: classes08-dex2jar.jar:com/imagepicker/utils/UI$OnAction.class */
    public interface OnAction {
        void onCancel(@Nullable ImagePickerModule imagePickerModule);

        void onCustomButton(@Nullable ImagePickerModule imagePickerModule, String str);

        void onTakePhoto(@Nullable ImagePickerModule imagePickerModule);

        void onUseLibrary(@Nullable ImagePickerModule imagePickerModule);
    }

    @NonNull
    public static AlertDialog chooseDialog(@Nullable ImagePickerModule imagePickerModule, @NonNull ReadableMap readableMap, @Nullable final OnAction onAction) {
        Activity activity = imagePickerModule.getActivity();
        if (activity == null) {
            return null;
        }
        final WeakReference weakReference = new WeakReference(imagePickerModule);
        ButtonsHelper newInstance = ButtonsHelper.newInstance(readableMap);
        List<String> titles = newInstance.getTitles();
        final List<String> actions = newInstance.getActions();
        ArrayAdapter arrayAdapter = new ArrayAdapter(activity, C0116R.layout.list_item, titles);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, imagePickerModule.getDialogThemeId());
        if (ReadableMapUtils.hasAndNotEmptyString(readableMap, SettingsJsonConstants.PROMPT_TITLE_KEY)) {
            builder.setTitle(readableMap.getString(SettingsJsonConstants.PROMPT_TITLE_KEY));
        }
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() { // from class: com.imagepicker.utils.UI.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.content.DialogInterface.OnClickListener
            @SensorsDataInstrumented
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean z;
                String str = (String) actions.get(i);
                int hashCode = str.hashCode();
                if (hashCode == -1367724422) {
                    if (str.equals("cancel")) {
                        z = 2;
                    }
                    z = -1;
                } else if (hashCode != 106642994) {
                    if (hashCode == 166208699 && str.equals("library")) {
                        z = true;
                    }
                    z = -1;
                } else {
                    if (str.equals("photo")) {
                        z = false;
                    }
                    z = -1;
                }
                if (!z) {
                    onAction.onTakePhoto((ImagePickerModule) weakReference.get());
                } else if (z) {
                    onAction.onUseLibrary((ImagePickerModule) weakReference.get());
                } else if (z != 2) {
                    onAction.onCustomButton((ImagePickerModule) weakReference.get(), str);
                } else {
                    onAction.onCancel((ImagePickerModule) weakReference.get());
                }
                SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
            }
        });
        builder.setNegativeButton(newInstance.btnCancel.title, new DialogInterface.OnClickListener() { // from class: com.imagepicker.utils.UI.2
            @Override // android.content.DialogInterface.OnClickListener
            @SensorsDataInstrumented
            public void onClick(DialogInterface dialogInterface, int i) {
                OnAction.this.onCancel((ImagePickerModule) weakReference.get());
                dialogInterface.dismiss();
                SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
            }
        });
        AlertDialog create = builder.create();
        create.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.imagepicker.utils.UI.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(@NonNull DialogInterface dialogInterface) {
                OnAction.this.onCancel((ImagePickerModule) weakReference.get());
                dialogInterface.dismiss();
            }
        });
        return create;
    }
}
