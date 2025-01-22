package com.imagepicker;

import android.support.annotation.StyleRes;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/imagepicker/ImagePickerPackage.class */
public class ImagePickerPackage implements ReactPackage {
    public static final int DEFAULT_EXPLAINING_PERMISSION_DIALIOG_THEME = C0116R.style.DefaultExplainingPermissionsTheme;

    @StyleRes
    private final int dialogThemeId;

    public ImagePickerPackage() {
        this.dialogThemeId = DEFAULT_EXPLAINING_PERMISSION_DIALIOG_THEME;
    }

    public ImagePickerPackage(@StyleRes int i) {
        this.dialogThemeId = i;
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new ImagePickerModule(reactApplicationContext, this.dialogThemeId));
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
