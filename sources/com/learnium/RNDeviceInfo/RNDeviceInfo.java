package com.learnium.RNDeviceInfo;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/learnium/RNDeviceInfo/RNDeviceInfo.class */
public class RNDeviceInfo implements ReactPackage {
    private boolean mLoadConstantsAsynchronously;

    public RNDeviceInfo() {
        this(false);
    }

    public RNDeviceInfo(boolean z) {
        this.mLoadConstantsAsynchronously = z;
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RNDeviceModule(reactApplicationContext, this.mLoadConstantsAsynchronously));
        return arrayList;
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
