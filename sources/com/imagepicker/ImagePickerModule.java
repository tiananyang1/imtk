package com.imagepicker;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.imagepicker.media.ImageConfig;
import com.imagepicker.permissions.OnImagePickerPermissionsCallback;
import com.imagepicker.permissions.PermissionUtils;
import com.imagepicker.utils.C0120UI;
import com.imagepicker.utils.MediaUtils;
import com.imagepicker.utils.RealPathUtil;
import com.lwansbrough.RCTCamera.RCTCameraModule;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* loaded from: classes08-dex2jar.jar:com/imagepicker/ImagePickerModule.class */
public class ImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    public static final int REQUEST_LAUNCH_IMAGE_CAPTURE = 13001;
    public static final int REQUEST_LAUNCH_IMAGE_LIBRARY = 13002;
    public static final int REQUEST_LAUNCH_VIDEO_CAPTURE = 13004;
    public static final int REQUEST_LAUNCH_VIDEO_LIBRARY = 13003;
    public static final int REQUEST_PERMISSIONS_FOR_CAMERA = 14001;
    public static final int REQUEST_PERMISSIONS_FOR_LIBRARY = 14002;
    protected Callback callback;
    protected Uri cameraCaptureURI;
    private final int dialogThemeId;
    private ImageConfig imageConfig;
    private PermissionListener listener;
    private Boolean noData;
    private ReadableMap options;
    private Boolean pickVideo;
    private final ReactApplicationContext reactContext;
    private ResponseHelper responseHelper;

    @Deprecated
    private int videoDurationLimit;

    @Deprecated
    private int videoQuality;

    public ImagePickerModule(ReactApplicationContext reactApplicationContext, @StyleRes int i) {
        super(reactApplicationContext);
        this.noData = false;
        this.pickVideo = false;
        this.imageConfig = new ImageConfig(null, null, 0, 0, 100, 0, false);
        this.videoQuality = 1;
        this.videoDurationLimit = 0;
        this.responseHelper = new ResponseHelper();
        this.listener = new PermissionListener() { // from class: com.imagepicker.ImagePickerModule.1
            public boolean onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
                boolean z = true;
                for (int i3 = 0; i3 < strArr.length; i3++) {
                    z = z && (iArr[i3] == 0);
                }
                if (ImagePickerModule.this.callback == null || ImagePickerModule.this.options == null) {
                    return false;
                }
                if (!z) {
                    ImagePickerModule.this.responseHelper.invokeError(ImagePickerModule.this.callback, "Permissions weren't granted");
                    return false;
                }
                if (i2 == 14001) {
                    ImagePickerModule imagePickerModule = ImagePickerModule.this;
                    imagePickerModule.launchCamera(imagePickerModule.options, ImagePickerModule.this.callback);
                    return true;
                }
                if (i2 != 14002) {
                    return true;
                }
                ImagePickerModule imagePickerModule2 = ImagePickerModule.this;
                imagePickerModule2.launchImageLibrary(imagePickerModule2.options, ImagePickerModule.this.callback);
                return true;
            }
        };
        this.dialogThemeId = i;
        this.reactContext = reactApplicationContext;
        this.reactContext.addActivityEventListener(this);
    }

    private File createFileFromURI(Uri uri) throws Exception {
        File file = new File(this.reactContext.getExternalCacheDir(), "photo-" + uri.getLastPathSegment());
        InputStream openInputStream = this.reactContext.getContentResolver().openInputStream(uri);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    fileOutputStream.flush();
                    return file;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } finally {
            fileOutputStream.close();
            openInputStream.close();
        }
    }

    private String getBase64StringFromFile(String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    @NonNull
    private String getRealPathFromURI(@NonNull Uri uri) {
        return RealPathUtil.getRealPathFromURI(this.reactContext, uri);
    }

    private boolean isCameraAvailable() {
        return this.reactContext.getPackageManager().hasSystemFeature("android.hardware.camera") || this.reactContext.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    private void parseOptions(ReadableMap readableMap) {
        this.noData = false;
        if (readableMap.hasKey("noData")) {
            this.noData = Boolean.valueOf(readableMap.getBoolean("noData"));
        }
        this.imageConfig = this.imageConfig.updateFromOptions(readableMap);
        this.pickVideo = false;
        if (readableMap.hasKey("mediaType") && readableMap.getString("mediaType").equals("video")) {
            this.pickVideo = true;
        }
        this.videoQuality = 1;
        if (readableMap.hasKey("videoQuality") && readableMap.getString("videoQuality").equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_LOW)) {
            this.videoQuality = 0;
        }
        this.videoDurationLimit = 0;
        if (readableMap.hasKey("durationLimit")) {
            this.videoDurationLimit = readableMap.getInt("durationLimit");
        }
    }

    private boolean passResult(int i) {
        if (this.callback == null) {
            return true;
        }
        if (this.cameraCaptureURI == null && i == 13001) {
            return true;
        }
        return (i == 13001 || i == 13002 || i == 13003 || i == 13004) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean permissionsCheck(@NonNull Activity activity, @NonNull Callback callback, @NonNull int i) {
        boolean z = true;
        if (ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ActivityCompat.checkSelfPermission(activity, "android.permission.CAMERA") == 0) {
            return true;
        }
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.WRITE_EXTERNAL_STORAGE") || !ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.CAMERA")) {
            z = false;
        }
        if (Boolean.valueOf(z).booleanValue()) {
            AlertDialog explainingDialog = PermissionUtils.explainingDialog(this, this.options, new PermissionUtils.OnExplainingPermissionCallback() { // from class: com.imagepicker.ImagePickerModule.3
                @Override // com.imagepicker.permissions.PermissionUtils.OnExplainingPermissionCallback
                public void onCancel(WeakReference<ImagePickerModule> weakReference, DialogInterface dialogInterface) {
                    ImagePickerModule imagePickerModule = weakReference.get();
                    if (imagePickerModule == null) {
                        return;
                    }
                    imagePickerModule.doOnCancel();
                }

                @Override // com.imagepicker.permissions.PermissionUtils.OnExplainingPermissionCallback
                public void onReTry(WeakReference<ImagePickerModule> weakReference, DialogInterface dialogInterface) {
                    ImagePickerModule imagePickerModule = weakReference.get();
                    if (imagePickerModule == null) {
                        return;
                    }
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", imagePickerModule.getContext().getPackageName(), null));
                    Activity activity2 = imagePickerModule.getActivity();
                    if (activity2 == null) {
                        return;
                    }
                    activity2.startActivityForResult(intent, 1);
                }
            });
            if (explainingDialog == null) {
                return false;
            }
            explainingDialog.show();
            return false;
        }
        String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
        if (activity instanceof PermissionAwareActivity) {
            ((PermissionAwareActivity) activity).requestPermissions(strArr, i, this.listener);
            return false;
        }
        if (activity instanceof OnImagePickerPermissionsCallback) {
            ((OnImagePickerPermissionsCallback) activity).setPermissionListener(this.listener);
            ActivityCompat.requestPermissions(activity, strArr, i);
            return false;
        }
        throw new UnsupportedOperationException(activity.getClass().getSimpleName() + " must implement " + OnImagePickerPermissionsCallback.class.getSimpleName());
    }

    private void putExtraFileInfo(@NonNull String str, @NonNull ResponseHelper responseHelper) {
        try {
            File file = new File(str);
            responseHelper.putDouble("fileSize", file.length());
            responseHelper.putString("fileName", file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            responseHelper.putString("type", MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl));
        }
    }

    private void updatedResultResponse(@Nullable Uri uri, @NonNull String str) {
        this.responseHelper.putString("uri", uri.toString());
        this.responseHelper.putString("path", str);
        if (!this.noData.booleanValue()) {
            this.responseHelper.putString("data", getBase64StringFromFile(str));
        }
        putExtraFileInfo(str, this.responseHelper);
    }

    public void doOnCancel() {
        this.responseHelper.invokeCancel(this.callback);
    }

    @NonNull
    public Activity getActivity() {
        return getCurrentActivity();
    }

    public Context getContext() {
        return getReactApplicationContext();
    }

    @StyleRes
    public int getDialogThemeId() {
        return this.dialogThemeId;
    }

    public String getName() {
        return "ImagePickerManager";
    }

    public void invokeCustomButton(@NonNull String str) {
        this.responseHelper.invokeCustomButton(this.callback, str);
    }

    public void launchCamera() {
        launchCamera(this.options, this.callback);
    }

    @ReactMethod
    public void launchCamera(ReadableMap readableMap, Callback callback) {
        int i;
        Intent intent;
        if (!isCameraAvailable()) {
            this.responseHelper.invokeError(callback, "Camera not available");
            return;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            this.responseHelper.invokeError(callback, "can't find current Activity");
            return;
        }
        this.options = readableMap;
        if (permissionsCheck(currentActivity, callback, REQUEST_PERMISSIONS_FOR_CAMERA)) {
            parseOptions(this.options);
            if (this.pickVideo.booleanValue()) {
                Intent intent2 = new Intent("android.media.action.VIDEO_CAPTURE");
                intent2.putExtra("android.intent.extra.videoQuality", this.videoQuality);
                int i2 = this.videoDurationLimit;
                intent = intent2;
                i = 13004;
                if (i2 > 0) {
                    intent2.putExtra("android.intent.extra.durationLimit", i2);
                    intent = intent2;
                    i = 13004;
                }
            } else {
                i = 13001;
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                this.imageConfig = this.imageConfig.withOriginalFile(MediaUtils.createNewFile(this.reactContext, this.options, false));
                if (this.imageConfig.original == null) {
                    this.responseHelper.invokeError(callback, "Couldn't get file path for photo");
                    return;
                }
                this.cameraCaptureURI = RealPathUtil.compatUriFromFile(this.reactContext, this.imageConfig.original);
                Uri uri = this.cameraCaptureURI;
                if (uri == null) {
                    this.responseHelper.invokeError(callback, "Couldn't get file path for photo");
                    return;
                }
                intent.putExtra("output", uri);
            }
            if (intent.resolveActivity(this.reactContext.getPackageManager()) == null) {
                this.responseHelper.invokeError(callback, "Cannot launch camera");
                return;
            }
            this.callback = callback;
            if (Build.VERSION.SDK_INT <= 19) {
                Iterator<ResolveInfo> it = this.reactContext.getPackageManager().queryIntentActivities(intent, 65536).iterator();
                while (it.hasNext()) {
                    this.reactContext.grantUriPermission(it.next().activityInfo.packageName, this.cameraCaptureURI, 3);
                }
            }
            try {
                currentActivity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                this.responseHelper.invokeError(callback, "Cannot launch camera");
            }
        }
    }

    public void launchImageLibrary() {
        launchImageLibrary(this.options, this.callback);
    }

    @ReactMethod
    public void launchImageLibrary(ReadableMap readableMap, Callback callback) {
        int i;
        Intent intent;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            this.responseHelper.invokeError(callback, "can't find current Activity");
            return;
        }
        this.options = readableMap;
        if (permissionsCheck(currentActivity, callback, REQUEST_PERMISSIONS_FOR_LIBRARY)) {
            parseOptions(this.options);
            if (this.pickVideo.booleanValue()) {
                i = 13003;
                intent = new Intent("android.intent.action.PICK");
                intent.setType("video/*");
            } else {
                i = 13002;
                intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            }
            if (intent.resolveActivity(this.reactContext.getPackageManager()) == null) {
                this.responseHelper.invokeError(callback, "Cannot launch photo library");
                return;
            }
            this.callback = callback;
            try {
                currentActivity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                this.responseHelper.invokeError(callback, "Cannot launch photo library");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00fc, code lost:            if (r11 != false) goto L56;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onActivityResult(android.app.Activity r9, int r10, int r11, android.content.Intent r12) {
        /*
            Method dump skipped, instructions count: 810
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imagepicker.ImagePickerModule.onActivityResult(android.app.Activity, int, int, android.content.Intent):void");
    }

    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void showImagePicker(ReadableMap readableMap, Callback callback) {
        if (getCurrentActivity() == null) {
            this.responseHelper.invokeError(callback, "can't find current Activity");
            return;
        }
        this.callback = callback;
        this.options = readableMap;
        this.imageConfig = new ImageConfig(null, null, 0, 0, 100, 0, false);
        C0120UI.chooseDialog(this, readableMap, new C0120UI.OnAction() { // from class: com.imagepicker.ImagePickerModule.2
            @Override // com.imagepicker.utils.C0120UI.OnAction
            public void onCancel(@NonNull ImagePickerModule imagePickerModule) {
                if (imagePickerModule == null) {
                    return;
                }
                imagePickerModule.doOnCancel();
            }

            @Override // com.imagepicker.utils.C0120UI.OnAction
            public void onCustomButton(@NonNull ImagePickerModule imagePickerModule, @NonNull String str) {
                if (imagePickerModule == null) {
                    return;
                }
                imagePickerModule.invokeCustomButton(str);
            }

            @Override // com.imagepicker.utils.C0120UI.OnAction
            public void onTakePhoto(@NonNull ImagePickerModule imagePickerModule) {
                if (imagePickerModule == null) {
                    return;
                }
                imagePickerModule.launchCamera();
            }

            @Override // com.imagepicker.utils.C0120UI.OnAction
            public void onUseLibrary(@NonNull ImagePickerModule imagePickerModule) {
                if (imagePickerModule == null) {
                    return;
                }
                imagePickerModule.launchImageLibrary();
            }
        }).show();
    }
}
