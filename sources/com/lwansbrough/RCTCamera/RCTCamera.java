package com.lwansbrough.RCTCamera;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/RCTCamera.class */
public class RCTCamera {
    private static RCTCamera ourInstance;
    private int _actualDeviceOrientation;
    private static final Resolution RESOLUTION_480P = new Resolution(853, 480);
    private static final Resolution RESOLUTION_720P = new Resolution(1280, 720);
    private static final Resolution RESOLUTION_1080P = new Resolution(1920, 1080);
    private boolean _barcodeScannerEnabled = false;
    private List<String> _barCodeTypes = null;
    private int _orientation = -1;
    private int _adjustedDeviceOrientation = 0;
    private final Map<Number, Camera> _cameras = new HashMap();
    private final HashMap<Integer, CameraInfoWrapper> _cameraInfos = new HashMap<>();
    private final HashMap<Integer, Integer> _cameraTypeToIndex = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/RCTCamera$CameraInfoWrapper.class */
    public class CameraInfoWrapper {
        public final Camera.CameraInfo info;
        public int rotation = 0;
        public int previewWidth = -1;
        public int previewHeight = -1;
        public int previewVisibleWidth = -1;
        public int previewVisibleHeight = -1;

        public CameraInfoWrapper(Camera.CameraInfo cameraInfo) {
            this.info = cameraInfo;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/RCTCamera$Resolution.class */
    private static class Resolution {
        public int height;
        public int width;

        public Resolution(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    private RCTCamera(int i) {
        this._actualDeviceOrientation = 0;
        this._actualDeviceOrientation = i;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= Camera.getNumberOfCameras()) {
                return;
            }
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i3, cameraInfo);
            if (cameraInfo.facing == 1 && this._cameraInfos.get(1) == null) {
                this._cameraInfos.put(1, new CameraInfoWrapper(cameraInfo));
                this._cameraTypeToIndex.put(1, Integer.valueOf(i3));
                acquireCameraInstance(1);
                releaseCameraInstance(1);
            } else if (cameraInfo.facing == 0 && this._cameraInfos.get(2) == null) {
                this._cameraInfos.put(2, new CameraInfoWrapper(cameraInfo));
                this._cameraTypeToIndex.put(2, Integer.valueOf(i3));
                acquireCameraInstance(2);
                releaseCameraInstance(2);
            }
            i2 = i3 + 1;
        }
    }

    public static void createInstance(int i) {
        ourInstance = new RCTCamera(i);
    }

    private Camera.Size getClosestSize(List<Camera.Size> list, int i, int i2) {
        Camera.Size size = null;
        for (Camera.Size size2 : list) {
            if (size != null) {
                if (Math.sqrt(Math.pow(size2.width - i, 2.0d) + Math.pow(size2.height - i2, 2.0d)) < Math.sqrt(Math.pow(size.width - i, 2.0d) + Math.pow(size.height - i2, 2.0d))) {
                }
            }
            size = size2;
        }
        return size;
    }

    public static RCTCamera getInstance() {
        return ourInstance;
    }

    private Camera.Size getSmallestSize(List<Camera.Size> list) {
        Camera.Size size = null;
        for (Camera.Size size2 : list) {
            if (size != null) {
                if (size2.width * size2.height < size.width * size.height) {
                }
            }
            size = size2;
        }
        return size;
    }

    public Camera acquireCameraInstance(int i) {
        Camera camera;
        synchronized (this) {
            if (this._cameras.get(Integer.valueOf(i)) == null && this._cameraTypeToIndex.get(Integer.valueOf(i)) != null) {
                try {
                    this._cameras.put(Integer.valueOf(i), Camera.open(this._cameraTypeToIndex.get(Integer.valueOf(i)).intValue()));
                    adjustPreviewLayout(i);
                } catch (Exception e) {
                    Log.e("RCTCamera", "acquireCameraInstance failed", e);
                }
            }
            camera = this._cameras.get(Integer.valueOf(i));
        }
        return camera;
    }

    public void adjustCameraRotationToDeviceOrientation(int i, int i2) {
        Camera camera = this._cameras.get(Integer.valueOf(i));
        if (camera == null) {
            return;
        }
        CameraInfoWrapper cameraInfoWrapper = this._cameraInfos.get(Integer.valueOf(i));
        int i3 = cameraInfoWrapper.info.orientation;
        cameraInfoWrapper.rotation = cameraInfoWrapper.info.facing == 1 ? (i3 + (i2 * 90)) % 360 : ((i3 - (i2 * 90)) + 360) % 360;
        Camera.Parameters parameters = camera.getParameters();
        parameters.setRotation(cameraInfoWrapper.rotation);
        try {
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adjustPreviewLayout(int i) {
        int i2;
        int i3;
        Camera camera = this._cameras.get(Integer.valueOf(i));
        if (camera == null) {
            return;
        }
        CameraInfoWrapper cameraInfoWrapper = this._cameraInfos.get(Integer.valueOf(i));
        int i4 = cameraInfoWrapper.info.orientation;
        if (cameraInfoWrapper.info.facing == 1) {
            int i5 = this._actualDeviceOrientation;
            i2 = ((i5 * 90) + i4) % 360;
            i3 = ((720 - i4) - (i5 * 90)) % 360;
        } else {
            i2 = ((i4 - (this._actualDeviceOrientation * 90)) + 360) % 360;
            i3 = i2;
        }
        cameraInfoWrapper.rotation = i2;
        setAdjustedDeviceOrientation(i2);
        camera.setDisplayOrientation(i3);
        Camera.Parameters parameters = camera.getParameters();
        parameters.setRotation(cameraInfoWrapper.rotation);
        Camera.Size bestSize = getBestSize(parameters.getSupportedPreviewSizes(), Integer.MAX_VALUE, Integer.MAX_VALUE);
        int i6 = bestSize.width;
        int i7 = bestSize.height;
        parameters.setPreviewSize(i6, i7);
        try {
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cameraInfoWrapper.rotation == 0 || cameraInfoWrapper.rotation == 180) {
            cameraInfoWrapper.previewWidth = i6;
            cameraInfoWrapper.previewHeight = i7;
        } else {
            cameraInfoWrapper.previewWidth = i7;
            cameraInfoWrapper.previewHeight = i6;
        }
    }

    public int getActualDeviceOrientation() {
        return this._actualDeviceOrientation;
    }

    public int getAdjustedDeviceOrientation() {
        return this._adjustedDeviceOrientation;
    }

    public List<String> getBarCodeTypes() {
        return this._barCodeTypes;
    }

    public Camera.Size getBestSize(List<Camera.Size> list, int i, int i2) {
        Camera.Size size = null;
        for (Camera.Size size2 : list) {
            if (size2.width <= i && size2.height <= i2) {
                if (size != null) {
                    if (size2.width * size2.height > size.width * size.height) {
                    }
                }
                size = size2;
            }
        }
        return size;
    }

    public int getOrientation() {
        return this._orientation;
    }

    public int getPreviewHeight(int i) {
        CameraInfoWrapper cameraInfoWrapper = this._cameraInfos.get(Integer.valueOf(i));
        if (cameraInfoWrapper == null) {
            return 0;
        }
        return cameraInfoWrapper.previewHeight;
    }

    public int getPreviewVisibleHeight(int i) {
        CameraInfoWrapper cameraInfoWrapper = this._cameraInfos.get(Integer.valueOf(i));
        if (cameraInfoWrapper == null) {
            return 0;
        }
        return cameraInfoWrapper.previewVisibleHeight;
    }

    public int getPreviewVisibleWidth(int i) {
        CameraInfoWrapper cameraInfoWrapper = this._cameraInfos.get(Integer.valueOf(i));
        if (cameraInfoWrapper == null) {
            return 0;
        }
        return cameraInfoWrapper.previewVisibleWidth;
    }

    public int getPreviewWidth(int i) {
        CameraInfoWrapper cameraInfoWrapper = this._cameraInfos.get(Integer.valueOf(i));
        if (cameraInfoWrapper == null) {
            return 0;
        }
        return cameraInfoWrapper.previewWidth;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<Camera.Size> getSupportedVideoSizes(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedVideoSizes = parameters.getSupportedVideoSizes();
        return supportedVideoSizes != null ? supportedVideoSizes : parameters.getSupportedPreviewSizes();
    }

    public boolean isBarcodeScannerEnabled() {
        return this._barcodeScannerEnabled;
    }

    public void releaseCameraInstance(int i) {
        Camera camera = this._cameras.get(Integer.valueOf(i));
        if (camera != null) {
            this._cameras.remove(Integer.valueOf(i));
            camera.release();
        }
    }

    public void setActualDeviceOrientation(int i) {
        this._actualDeviceOrientation = i;
        adjustPreviewLayout(1);
        adjustPreviewLayout(2);
    }

    public void setAdjustedDeviceOrientation(int i) {
        this._adjustedDeviceOrientation = i;
    }

    public void setBarCodeTypes(List<String> list) {
        this._barCodeTypes = list;
    }

    public void setBarcodeScannerEnabled(boolean z) {
        this._barcodeScannerEnabled = z;
    }

    public void setCaptureMode(int i, int i2) {
        Camera camera = this._cameras.get(Integer.valueOf(i));
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        boolean z = true;
        if (i2 != 1) {
            z = false;
        }
        parameters.setRecordingHint(z);
        camera.setParameters(parameters);
    }

    public void setCaptureQuality(int i, String str) {
        Camera.Size smallestSize;
        Camera acquireCameraInstance = acquireCameraInstance(i);
        if (acquireCameraInstance == null) {
            return;
        }
        Camera.Parameters parameters = acquireCameraInstance.getParameters();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        boolean z = -1;
        switch (str.hashCode()) {
            case -1078030475:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM)) {
                    z = true;
                    break;
                }
                break;
            case -318184504:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_PREVIEW)) {
                    z = 3;
                    break;
                }
                break;
            case 107348:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_LOW)) {
                    z = false;
                    break;
                }
                break;
            case 1604548:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_480P)) {
                    z = 4;
                    break;
                }
                break;
            case 1688155:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_720P)) {
                    z = 5;
                    break;
                }
                break;
            case 3202466:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH)) {
                    z = 2;
                    break;
                }
                break;
            case 46737913:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_1080P)) {
                    z = 6;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                smallestSize = getSmallestSize(supportedPictureSizes);
                break;
            case true:
                smallestSize = supportedPictureSizes.get(supportedPictureSizes.size() / 2);
                break;
            case true:
                smallestSize = getBestSize(parameters.getSupportedPictureSizes(), Integer.MAX_VALUE, Integer.MAX_VALUE);
                break;
            case true:
                Camera.Size bestSize = getBestSize(parameters.getSupportedPreviewSizes(), Integer.MAX_VALUE, Integer.MAX_VALUE);
                smallestSize = getClosestSize(parameters.getSupportedPictureSizes(), bestSize.width, bestSize.height);
                break;
            case true:
                smallestSize = getBestSize(supportedPictureSizes, RESOLUTION_480P.width, RESOLUTION_480P.height);
                break;
            case true:
                smallestSize = getBestSize(supportedPictureSizes, RESOLUTION_720P.width, RESOLUTION_720P.height);
                break;
            case true:
                smallestSize = getBestSize(supportedPictureSizes, RESOLUTION_1080P.width, RESOLUTION_1080P.height);
                break;
            default:
                smallestSize = null;
                break;
        }
        if (smallestSize != null) {
            parameters.setPictureSize(smallestSize.width, smallestSize.height);
            acquireCameraInstance.setParameters(parameters);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CamcorderProfile setCaptureVideoQuality(int i, String str) {
        Camera.Size smallestSize;
        CamcorderProfile camcorderProfile;
        Camera acquireCameraInstance = acquireCameraInstance(i);
        if (acquireCameraInstance == null) {
            return null;
        }
        List<Camera.Size> supportedVideoSizes = getSupportedVideoSizes(acquireCameraInstance);
        boolean z = -1;
        switch (str.hashCode()) {
            case -1078030475:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM)) {
                    z = true;
                    break;
                }
                break;
            case 107348:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_LOW)) {
                    z = false;
                    break;
                }
                break;
            case 1604548:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_480P)) {
                    z = 3;
                    break;
                }
                break;
            case 1688155:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_720P)) {
                    z = 4;
                    break;
                }
                break;
            case 3202466:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH)) {
                    z = 2;
                    break;
                }
                break;
            case 46737913:
                if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_1080P)) {
                    z = 5;
                    break;
                }
                break;
        }
        if (!z) {
            smallestSize = getSmallestSize(supportedVideoSizes);
            camcorderProfile = CamcorderProfile.get(this._cameraTypeToIndex.get(Integer.valueOf(i)).intValue(), 4);
        } else if (z) {
            smallestSize = supportedVideoSizes.get(supportedVideoSizes.size() / 2);
            camcorderProfile = CamcorderProfile.get(this._cameraTypeToIndex.get(Integer.valueOf(i)).intValue(), 5);
        } else if (z == 2) {
            smallestSize = getBestSize(supportedVideoSizes, Integer.MAX_VALUE, Integer.MAX_VALUE);
            camcorderProfile = CamcorderProfile.get(this._cameraTypeToIndex.get(Integer.valueOf(i)).intValue(), 1);
        } else if (z == 3) {
            smallestSize = getBestSize(supportedVideoSizes, RESOLUTION_480P.width, RESOLUTION_480P.height);
            camcorderProfile = CamcorderProfile.get(this._cameraTypeToIndex.get(Integer.valueOf(i)).intValue(), 4);
        } else if (z == 4) {
            smallestSize = getBestSize(supportedVideoSizes, RESOLUTION_720P.width, RESOLUTION_720P.height);
            camcorderProfile = CamcorderProfile.get(this._cameraTypeToIndex.get(Integer.valueOf(i)).intValue(), 5);
        } else if (z != 5) {
            camcorderProfile = null;
            smallestSize = null;
        } else {
            smallestSize = getBestSize(supportedVideoSizes, RESOLUTION_1080P.width, RESOLUTION_1080P.height);
            camcorderProfile = CamcorderProfile.get(this._cameraTypeToIndex.get(Integer.valueOf(i)).intValue(), 6);
        }
        if (camcorderProfile == null) {
            return null;
        }
        if (smallestSize != null) {
            camcorderProfile.videoFrameHeight = smallestSize.height;
            camcorderProfile.videoFrameWidth = smallestSize.width;
        }
        return camcorderProfile;
    }

    public void setFlashMode(int i, int i2) {
        Camera acquireCameraInstance = acquireCameraInstance(i);
        if (acquireCameraInstance == null) {
            return;
        }
        Camera.Parameters parameters = acquireCameraInstance.getParameters();
        String flashMode = parameters.getFlashMode();
        if (i2 == 0) {
            flashMode = "off";
        } else if (i2 == 1) {
            flashMode = "on";
        } else if (i2 == 2) {
            flashMode = "auto";
        }
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes == null || !supportedFlashModes.contains(flashMode)) {
            return;
        }
        parameters.setFlashMode(flashMode);
        acquireCameraInstance.setParameters(parameters);
    }

    public void setOrientation(int i) {
        if (this._orientation == i) {
            return;
        }
        this._orientation = i;
        adjustPreviewLayout(1);
        adjustPreviewLayout(2);
    }

    public void setPreviewVisibleSize(int i, int i2, int i3) {
        CameraInfoWrapper cameraInfoWrapper = this._cameraInfos.get(Integer.valueOf(i));
        if (cameraInfoWrapper == null) {
            return;
        }
        cameraInfoWrapper.previewVisibleWidth = i2;
        cameraInfoWrapper.previewVisibleHeight = i3;
    }

    public void setTorchMode(int i, int i2) {
        Camera acquireCameraInstance = acquireCameraInstance(i);
        if (acquireCameraInstance == null) {
            return;
        }
        Camera.Parameters parameters = acquireCameraInstance.getParameters();
        String flashMode = parameters.getFlashMode();
        if (i2 == 0) {
            flashMode = "off";
        } else if (i2 == 1) {
            flashMode = "torch";
        }
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes == null || !supportedFlashModes.contains(flashMode)) {
            return;
        }
        parameters.setFlashMode(flashMode);
        acquireCameraInstance.setParameters(parameters);
    }

    public void setZoom(int i, int i2) {
        Camera acquireCameraInstance = acquireCameraInstance(i);
        if (acquireCameraInstance == null) {
            return;
        }
        Camera.Parameters parameters = acquireCameraInstance.getParameters();
        int maxZoom = parameters.getMaxZoom();
        if (!parameters.isZoomSupported() || i2 < 0 || i2 >= maxZoom) {
            return;
        }
        parameters.setZoom(i2);
        acquireCameraInstance.setParameters(parameters);
    }
}
