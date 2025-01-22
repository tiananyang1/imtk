package com.reactnativecommunity.webview;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Toast;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.stub.StubApp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@ReactModule(name = RNCWebViewModule.MODULE_NAME)
/* loaded from: classes08-dex2jar.jar:com/reactnativecommunity/webview/RNCWebViewModule.class */
public class RNCWebViewModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final int FILE_DOWNLOAD_PERMISSION_REQUEST = 1;
    public static final String MODULE_NAME = "RNCWebView";
    private static final int PICKER = 1;
    private static final int PICKER_LEGACY = 3;
    final String DEFAULT_MIME_TYPES;
    private DownloadManager.Request downloadRequest;
    private ValueCallback<Uri[]> filePathCallback;
    private ValueCallback<Uri> filePathCallbackLegacy;
    private Uri outputFileUri;
    private PermissionListener webviewFileDownloaderPermissionListener;

    public RNCWebViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.DEFAULT_MIME_TYPES = "*/*";
        this.webviewFileDownloaderPermissionListener = new PermissionListener() { // from class: com.reactnativecommunity.webview.RNCWebViewModule.1
            public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                if (i != 1) {
                    return false;
                }
                if (iArr.length <= 0 || iArr[0] != 0) {
                    Toast.makeText(StubApp.getOrigApplicationContext(RNCWebViewModule.this.getCurrentActivity().getApplicationContext()), "Cannot download files as permission was denied. Please provide permission to write to storage, in order to download files.", 1).show();
                    return true;
                }
                if (RNCWebViewModule.this.downloadRequest == null) {
                    return true;
                }
                RNCWebViewModule.this.downloadFile();
                return true;
            }
        };
        reactApplicationContext.addActivityEventListener(this);
    }

    private Boolean acceptsImages(String str) {
        String str2 = str;
        if (str.matches("\\.\\w+")) {
            str2 = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str2.isEmpty() || str2.toLowerCase().contains("image"));
    }

    private Boolean acceptsImages(String[] strArr) {
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(isArrayEmpty(acceptedMimeType).booleanValue() || arrayContainsString(acceptedMimeType, "image").booleanValue());
    }

    private Boolean acceptsVideo(String str) {
        String str2 = str;
        if (str.matches("\\.\\w+")) {
            str2 = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str2.isEmpty() || str2.toLowerCase().contains("video"));
    }

    private Boolean acceptsVideo(String[] strArr) {
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(isArrayEmpty(acceptedMimeType).booleanValue() || arrayContainsString(acceptedMimeType, "video").booleanValue());
    }

    private Boolean arrayContainsString(String[] strArr, String str) {
        int length = strArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return false;
            }
            if (strArr[i2].contains(str)) {
                return true;
            }
            i = i2 + 1;
        }
    }

    private String[] getAcceptedMimeType(String[] strArr) {
        if (isArrayEmpty(strArr).booleanValue()) {
            return new String[]{"*/*"};
        }
        String[] strArr2 = new String[strArr.length];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= strArr.length) {
                return strArr2;
            }
            String str = strArr[i2];
            if (str.matches("\\.\\w+")) {
                strArr2[i2] = getMimeTypeFromExtension(str.replace(".", ""));
            } else {
                strArr2[i2] = str;
            }
            i = i2 + 1;
        }
    }

    private File getCapturedFile(String str) throws IOException {
        String str2;
        String str3;
        String str4 = "";
        if (str.equals("android.media.action.IMAGE_CAPTURE")) {
            str4 = Environment.DIRECTORY_PICTURES;
            str2 = "image-";
            str3 = ".jpg";
        } else if (str.equals("android.media.action.VIDEO_CAPTURE")) {
            str4 = Environment.DIRECTORY_MOVIES;
            str2 = "video-";
            str3 = ".mp4";
        } else {
            str2 = "";
            str3 = str2;
        }
        String str5 = str2 + String.valueOf(System.currentTimeMillis()) + str3;
        return Build.VERSION.SDK_INT < 23 ? new File(Environment.getExternalStoragePublicDirectory(str4), str5) : File.createTempFile(str5, str3, getReactApplicationContext().getExternalFilesDir((String) null));
    }

    private Intent getFileChooserIntent(String str) {
        String str2 = str.isEmpty() ? "*/*" : str;
        if (str.matches("\\.\\w+")) {
            str2 = getMimeTypeFromExtension(str.replace(".", ""));
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str2);
        return intent;
    }

    private Intent getFileChooserIntent(String[] strArr, boolean z) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", getAcceptedMimeType(strArr));
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", z);
        return intent;
    }

    private String getMimeTypeFromExtension(String str) {
        if (str != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        }
        return null;
    }

    private Uri getOutputUri(String str) {
        File file;
        try {
            file = getCapturedFile(str);
        } catch (IOException e) {
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            file = null;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return Uri.fromFile(file);
        }
        String packageName = getReactApplicationContext().getPackageName();
        return FileProvider.getUriForFile(getReactApplicationContext(), packageName + ".fileprovider", file);
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        PermissionAwareActivity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        }
        if (currentActivity instanceof PermissionAwareActivity) {
            return currentActivity;
        }
        throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
    }

    private Intent getPhotoIntent() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        this.outputFileUri = getOutputUri("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", this.outputFileUri);
        return intent;
    }

    private Uri[] getSelectedFiles(Intent intent, int i) {
        Uri[] uriArr = null;
        if (intent == null) {
            return null;
        }
        if (intent.getData() != null) {
            if (i != -1 || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return WebChromeClient.FileChooserParams.parseResult(i, intent);
        }
        if (intent.getClipData() != null) {
            int itemCount = intent.getClipData().getItemCount();
            Uri[] uriArr2 = new Uri[itemCount];
            int i2 = 0;
            while (true) {
                int i3 = i2;
                uriArr = uriArr2;
                if (i3 >= itemCount) {
                    break;
                }
                uriArr2[i3] = intent.getClipData().getItemAt(i3).getUri();
                i2 = i3 + 1;
            }
        }
        return uriArr;
    }

    private Intent getVideoIntent() {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        intent.putExtra("output", getOutputUri("android.media.action.VIDEO_CAPTURE"));
        return intent;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:            if (r4[0].length() == 0) goto L8;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Boolean isArrayEmpty(java.lang.String[] r4) {
        /*
            r3 = this;
            r0 = r4
            int r0 = r0.length
            r5 = r0
            r0 = 0
            r7 = r0
            r0 = r5
            if (r0 == 0) goto L1f
            r0 = r7
            r6 = r0
            r0 = r4
            int r0 = r0.length
            r1 = 1
            if (r0 != r1) goto L21
            r0 = r7
            r6 = r0
            r0 = r4
            r1 = 0
            r0 = r0[r1]
            int r0 = r0.length()
            if (r0 != 0) goto L21
        L1f:
            r0 = 1
            r6 = r0
        L21:
            r0 = r6
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.webview.RNCWebViewModule.isArrayEmpty(java.lang.String[]):java.lang.Boolean");
    }

    public void downloadFile() {
        ((DownloadManager) getCurrentActivity().getBaseContext().getSystemService("download")).enqueue(this.downloadRequest);
        Toast.makeText(StubApp.getOrigApplicationContext(getCurrentActivity().getApplicationContext()), "Downloading", 1).show();
    }

    public String getName() {
        return MODULE_NAME;
    }

    public boolean grantFileDownloaderPermissions() {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        boolean z = ContextCompat.checkSelfPermission(getCurrentActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        if (!z) {
            getPermissionAwareActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1, this.webviewFileDownloaderPermissionListener);
        }
        return z;
    }

    @ReactMethod
    public void isFileUploadSupported(Promise promise) {
        Boolean bool = false;
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            bool = true;
        }
        Boolean bool2 = bool;
        if (i >= 16) {
            bool2 = bool;
            if (i <= 18) {
                bool2 = true;
            }
        }
        promise.resolve(bool2);
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (this.filePathCallback == null && this.filePathCallbackLegacy == null) {
            return;
        }
        if (i != 1) {
            if (i == 3) {
                this.filePathCallbackLegacy.onReceiveValue(i2 != -1 ? null : intent == null ? this.outputFileUri : intent.getData());
            }
        } else if (i2 != -1) {
            ValueCallback<Uri[]> valueCallback = this.filePathCallback;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
        } else {
            Uri[] selectedFiles = getSelectedFiles(intent, i2);
            if (selectedFiles != null) {
                this.filePathCallback.onReceiveValue(selectedFiles);
            } else {
                this.filePathCallback.onReceiveValue(new Uri[]{this.outputFileUri});
            }
        }
        this.filePathCallback = null;
        this.filePathCallbackLegacy = null;
        this.outputFileUri = null;
    }

    public void onNewIntent(Intent intent) {
    }

    public void setDownloadRequest(DownloadManager.Request request) {
        this.downloadRequest = request;
    }

    public void startPhotoPickerIntent(ValueCallback<Uri> valueCallback, String str) {
        this.filePathCallbackLegacy = valueCallback;
        Intent createChooser = Intent.createChooser(getFileChooserIntent(str), "");
        ArrayList arrayList = new ArrayList();
        if (acceptsImages(str).booleanValue()) {
            arrayList.add(getPhotoIntent());
        }
        if (acceptsVideo(str).booleanValue()) {
            arrayList.add(getVideoIntent());
        }
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (createChooser.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(createChooser, 3);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
    }

    @RequiresApi(api = 21)
    public boolean startPhotoPickerIntent(ValueCallback<Uri[]> valueCallback, Intent intent, String[] strArr, boolean z) {
        this.filePathCallback = valueCallback;
        ArrayList arrayList = new ArrayList();
        if (acceptsImages(strArr).booleanValue()) {
            arrayList.add(getPhotoIntent());
        }
        if (acceptsVideo(strArr).booleanValue()) {
            arrayList.add(getVideoIntent());
        }
        Intent fileChooserIntent = getFileChooserIntent(strArr, z);
        Intent intent2 = new Intent("android.intent.action.CHOOSER");
        intent2.putExtra("android.intent.extra.INTENT", fileChooserIntent);
        intent2.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (intent2.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(intent2, 1);
            return true;
        }
        Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        return true;
    }
}
