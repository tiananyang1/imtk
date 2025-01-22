package com.lwansbrough.RCTCamera;

import android.support.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/RCTCameraViewManager.class */
public class RCTCameraViewManager extends ViewGroupManager<RCTCameraView> {
    public static final int COMMAND_START_PREVIEW = 2;
    public static final int COMMAND_STOP_PREVIEW = 1;
    private static final String REACT_CLASS = "RCTCamera";

    public RCTCameraView createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTCameraView(themedReactContext);
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("stopPreview", 1, "startPreview", 2);
    }

    public String getName() {
        return REACT_CLASS;
    }

    public void receiveCommand(RCTCameraView rCTCameraView, int i, @Nullable ReadableArray readableArray) {
        Assertions.assertNotNull(rCTCameraView);
        if (i == 1) {
            rCTCameraView.stopPreview();
        } else {
            if (i != 2) {
                throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", Integer.valueOf(i), getClass().getSimpleName()));
            }
            rCTCameraView.startPreview();
        }
    }

    @ReactProp(name = "aspect")
    public void setAspect(RCTCameraView rCTCameraView, int i) {
        rCTCameraView.setAspect(i);
    }

    @ReactProp(name = "barCodeTypes")
    public void setBarCodeTypes(RCTCameraView rCTCameraView, ReadableArray readableArray) {
        if (readableArray == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(readableArray.size());
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= readableArray.size()) {
                rCTCameraView.setBarCodeTypes(arrayList);
                return;
            } else {
                arrayList.add(readableArray.getString(i2));
                i = i2 + 1;
            }
        }
    }

    @ReactProp(name = "barcodeScannerEnabled")
    public void setBarcodeScannerEnabled(RCTCameraView rCTCameraView, boolean z) {
        rCTCameraView.setBarcodeScannerEnabled(z);
    }

    @ReactProp(name = "captureAudio")
    public void setCaptureAudio(RCTCameraView rCTCameraView, boolean z) {
    }

    @ReactProp(name = "captureMode")
    public void setCaptureMode(RCTCameraView rCTCameraView, int i) {
        rCTCameraView.setCaptureMode(i);
    }

    @ReactProp(name = "captureQuality")
    public void setCaptureQuality(RCTCameraView rCTCameraView, String str) {
        rCTCameraView.setCaptureQuality(str);
    }

    @ReactProp(name = "captureTarget")
    public void setCaptureTarget(RCTCameraView rCTCameraView, int i) {
    }

    @ReactProp(name = "clearWindowBackground")
    public void setClearWindowBackground(RCTCameraView rCTCameraView, boolean z) {
        rCTCameraView.setClearWindowBackground(z);
    }

    @ReactProp(name = "flashMode")
    public void setFlashMode(RCTCameraView rCTCameraView, int i) {
        rCTCameraView.setFlashMode(i);
    }

    @ReactProp(name = "orientation")
    public void setOrientation(RCTCameraView rCTCameraView, int i) {
        rCTCameraView.setOrientation(i);
    }

    @ReactProp(name = "torchMode")
    public void setTorchMode(RCTCameraView rCTCameraView, int i) {
        rCTCameraView.setTorchMode(i);
    }

    @ReactProp(name = "type")
    public void setType(RCTCameraView rCTCameraView, int i) {
        rCTCameraView.setCameraType(i);
    }

    @ReactProp(name = "zoom")
    public void setZoom(RCTCameraView rCTCameraView, int i) {
        rCTCameraView.setZoom(i);
    }
}
