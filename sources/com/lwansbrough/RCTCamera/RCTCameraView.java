package com.lwansbrough.RCTCamera;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/RCTCameraView.class */
public class RCTCameraView extends ViewGroup {
    private int _actualDeviceOrientation;
    private int _aspect;
    private int _captureMode;
    private String _captureQuality;
    private boolean _clearWindowBackground;
    private final Context _context;
    private int _flashMode;
    private final OrientationEventListener _orientationListener;
    private int _torchMode;
    private RCTCameraViewFinder _viewFinder;
    private int _zoom;

    public RCTCameraView(Context context) {
        super(context);
        this._viewFinder = null;
        this._actualDeviceOrientation = -1;
        this._aspect = 1;
        this._captureMode = 0;
        this._captureQuality = RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH;
        this._torchMode = -1;
        this._flashMode = -1;
        this._zoom = 0;
        this._clearWindowBackground = false;
        this._context = context;
        RCTCamera.createInstance(getDeviceOrientation(context));
        this._orientationListener = new OrientationEventListener(context, 3) { // from class: com.lwansbrough.RCTCamera.RCTCameraView.1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                RCTCameraView rCTCameraView = RCTCameraView.this;
                if (rCTCameraView.setActualDeviceOrientation(rCTCameraView._context)) {
                    RCTCameraView.this.layoutViewFinder();
                }
            }
        };
        if (this._orientationListener.canDetectOrientation()) {
            this._orientationListener.enable();
        } else {
            this._orientationListener.disable();
        }
    }

    private int getDeviceOrientation(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getOrientation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void layoutViewFinder() {
        layoutViewFinder(getLeft(), getTop(), getRight(), getBottom());
    }

    private void layoutViewFinder(int i, int i2, int i3, int i4) {
        double ratio;
        double d;
        double d2;
        int i5;
        int i6;
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder == null) {
            return;
        }
        float f = i3 - i;
        float f2 = i4 - i2;
        int i7 = this._aspect;
        if (i7 == 0) {
            ratio = rCTCameraViewFinder.getRatio();
            double d3 = f2 * ratio;
            double d4 = f;
            d = d3;
            if (d3 < d4) {
                d2 = d4;
                i6 = (int) (d2 / ratio);
                i5 = (int) f;
                int i8 = (int) ((f - i5) / 2.0f);
                int i9 = (int) ((f2 - i6) / 2.0f);
                RCTCamera.getInstance().setPreviewVisibleSize(this._viewFinder.getCameraType(), (int) f, (int) f2);
                this._viewFinder.layout(i8, i9, i5 + i8, i6 + i9);
                postInvalidate(getLeft(), getTop(), getRight(), getBottom());
            }
            i5 = (int) d;
            i6 = (int) f2;
            int i82 = (int) ((f - i5) / 2.0f);
            int i92 = (int) ((f2 - i6) / 2.0f);
            RCTCamera.getInstance().setPreviewVisibleSize(this._viewFinder.getCameraType(), (int) f, (int) f2);
            this._viewFinder.layout(i82, i92, i5 + i82, i6 + i92);
            postInvalidate(getLeft(), getTop(), getRight(), getBottom());
        }
        if (i7 != 1) {
            i5 = (int) f;
            i6 = (int) f2;
            int i822 = (int) ((f - i5) / 2.0f);
            int i922 = (int) ((f2 - i6) / 2.0f);
            RCTCamera.getInstance().setPreviewVisibleSize(this._viewFinder.getCameraType(), (int) f, (int) f2);
            this._viewFinder.layout(i822, i922, i5 + i822, i6 + i922);
            postInvalidate(getLeft(), getTop(), getRight(), getBottom());
        }
        ratio = rCTCameraViewFinder.getRatio();
        double d5 = f2 * ratio;
        double d6 = f;
        d = d5;
        if (d5 > d6) {
            d2 = d6;
            i6 = (int) (d2 / ratio);
            i5 = (int) f;
            int i8222 = (int) ((f - i5) / 2.0f);
            int i9222 = (int) ((f2 - i6) / 2.0f);
            RCTCamera.getInstance().setPreviewVisibleSize(this._viewFinder.getCameraType(), (int) f, (int) f2);
            this._viewFinder.layout(i8222, i9222, i5 + i8222, i6 + i9222);
            postInvalidate(getLeft(), getTop(), getRight(), getBottom());
        }
        i5 = (int) d;
        i6 = (int) f2;
        int i82222 = (int) ((f - i5) / 2.0f);
        int i92222 = (int) ((f2 - i6) / 2.0f);
        RCTCamera.getInstance().setPreviewVisibleSize(this._viewFinder.getCameraType(), (int) f, (int) f2);
        this._viewFinder.layout(i82222, i92222, i5 + i82222, i6 + i92222);
        postInvalidate(getLeft(), getTop(), getRight(), getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setActualDeviceOrientation(Context context) {
        int deviceOrientation = getDeviceOrientation(context);
        if (this._actualDeviceOrientation == deviceOrientation) {
            return false;
        }
        this._actualDeviceOrientation = deviceOrientation;
        RCTCamera.getInstance().setActualDeviceOrientation(this._actualDeviceOrientation);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutViewFinder(i, i2, i3, i4);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder == view) {
            return;
        }
        removeView(rCTCameraViewFinder);
        addView(this._viewFinder, 0);
    }

    public void setAspect(int i) {
        this._aspect = i;
        layoutViewFinder();
    }

    public void setBarCodeTypes(List<String> list) {
        RCTCamera.getInstance().setBarCodeTypes(list);
    }

    public void setBarcodeScannerEnabled(boolean z) {
        RCTCamera.getInstance().setBarcodeScannerEnabled(z);
    }

    public void setCameraType(int i) {
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder != null) {
            rCTCameraViewFinder.setCameraType(i);
            RCTCamera.getInstance().adjustPreviewLayout(i);
            return;
        }
        this._viewFinder = new RCTCameraViewFinder(this._context, i);
        int i2 = this._flashMode;
        if (-1 != i2) {
            this._viewFinder.setFlashMode(i2);
        }
        int i3 = this._torchMode;
        if (-1 != i3) {
            this._viewFinder.setTorchMode(i3);
        }
        int i4 = this._zoom;
        if (i4 != 0) {
            this._viewFinder.setZoom(i4);
        }
        this._viewFinder.setClearWindowBackground(this._clearWindowBackground);
        addView(this._viewFinder);
    }

    public void setCaptureMode(int i) {
        this._captureMode = i;
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder != null) {
            rCTCameraViewFinder.setCaptureMode(i);
        }
    }

    public void setCaptureQuality(String str) {
        this._captureQuality = str;
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder != null) {
            rCTCameraViewFinder.setCaptureQuality(str);
        }
    }

    public void setClearWindowBackground(boolean z) {
        this._clearWindowBackground = z;
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder != null) {
            rCTCameraViewFinder.setClearWindowBackground(z);
        }
    }

    public void setFlashMode(int i) {
        this._flashMode = i;
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder != null) {
            rCTCameraViewFinder.setFlashMode(i);
        }
    }

    public void setOrientation(int i) {
        RCTCamera.getInstance().setOrientation(i);
        if (this._viewFinder != null) {
            layoutViewFinder();
        }
    }

    public void setTorchMode(int i) {
        this._torchMode = i;
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder != null) {
            rCTCameraViewFinder.setTorchMode(i);
        }
    }

    public void setZoom(int i) {
        this._zoom = i;
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder != null) {
            rCTCameraViewFinder.setZoom(i);
        }
    }

    public void startPreview() {
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder == null) {
            return;
        }
        rCTCameraViewFinder.startPreview();
    }

    public void stopPreview() {
        RCTCameraViewFinder rCTCameraViewFinder = this._viewFinder;
        if (rCTCameraViewFinder == null) {
            return;
        }
        rCTCameraViewFinder.stopPreview();
    }
}
