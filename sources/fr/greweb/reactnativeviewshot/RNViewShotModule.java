package fr.greweb.reactnativeviewshot;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIManagerModule;
import fr.greweb.reactnativeviewshot.ViewShot;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:fr/greweb/reactnativeviewshot/RNViewShotModule.class */
public class RNViewShotModule extends ReactContextBaseJavaModule {
    public static final String RNVIEW_SHOT = "RNViewShot";
    private static final String TEMP_FILE_PREFIX = "ReactNative-snapshot-image";
    private final ReactApplicationContext reactContext;

    /* loaded from: classes08-dex2jar.jar:fr/greweb/reactnativeviewshot/RNViewShotModule$CleanTask.class */
    private static class CleanTask extends GuardedAsyncTask<Void, Void> implements FilenameFilter {
        private final File cacheDir;
        private final File externalCacheDir;

        private CleanTask(ReactContext reactContext) {
            super(reactContext);
            this.cacheDir = reactContext.getCacheDir();
            this.externalCacheDir = reactContext.getExternalCacheDir();
        }

        private void cleanDirectory(@NonNull File file) {
            File[] listFiles = file.listFiles(this);
            if (listFiles == null) {
                return;
            }
            int length = listFiles.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return;
                }
                File file2 = listFiles[i2];
                if (file2.delete()) {
                    Log.d(RNViewShotModule.RNVIEW_SHOT, "deleted file: " + file2.getAbsolutePath());
                }
                i = i2 + 1;
            }
        }

        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return str.startsWith(RNViewShotModule.TEMP_FILE_PREFIX);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... voidArr) {
            File file = this.cacheDir;
            if (file != null) {
                cleanDirectory(file);
            }
            File file2 = this.externalCacheDir;
            if (file2 != null) {
                cleanDirectory(file2);
            }
        }
    }

    public RNViewShotModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @NonNull
    private File createTempFile(@NonNull Context context, @NonNull String str) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File cacheDir = context.getCacheDir();
        if (externalCacheDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        }
        if (externalCacheDir != null) {
            if (cacheDir == null) {
                cacheDir = externalCacheDir;
            } else if (externalCacheDir.getFreeSpace() > cacheDir.getFreeSpace()) {
                cacheDir = externalCacheDir;
            }
        }
        return File.createTempFile(TEMP_FILE_PREFIX, "." + str, cacheDir);
    }

    @ReactMethod
    public void captureRef(int i, ReadableMap readableMap, Promise promise) {
        DisplayMetrics displayMetrics = getReactApplicationContext().getResources().getDisplayMetrics();
        String string = readableMap.getString("format");
        int i2 = "jpg".equals(string) ? 0 : "webm".equals(string) ? 2 : "raw".equals(string) ? -1 : 1;
        double d = readableMap.getDouble("quality");
        Integer valueOf = readableMap.hasKey(SettingsJsonConstants.ICON_WIDTH_KEY) ? Integer.valueOf((int) (displayMetrics.density * readableMap.getDouble(SettingsJsonConstants.ICON_WIDTH_KEY))) : null;
        Integer valueOf2 = readableMap.hasKey(SettingsJsonConstants.ICON_HEIGHT_KEY) ? Integer.valueOf((int) (displayMetrics.density * readableMap.getDouble(SettingsJsonConstants.ICON_HEIGHT_KEY))) : null;
        String string2 = readableMap.getString("result");
        try {
            this.reactContext.getNativeModule(UIManagerModule.class).addUIBlock(new ViewShot(i, string, i2, d, valueOf, valueOf2, ViewShot.Results.TEMP_FILE.equals(string2) ? createTempFile(getReactApplicationContext(), string) : null, string2, Boolean.valueOf(readableMap.getBoolean("snapshotContentContainer")), this.reactContext, getCurrentActivity(), promise));
        } catch (Throwable th) {
            Log.e(RNVIEW_SHOT, "Failed to snapshot view tag " + i, th);
            promise.reject(ViewShot.ERROR_UNABLE_TO_SNAPSHOT, "Failed to snapshot view tag " + i);
        }
    }

    @ReactMethod
    public void captureScreen(ReadableMap readableMap, Promise promise) {
        captureRef(-1, readableMap, promise);
    }

    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    public String getName() {
        return RNVIEW_SHOT;
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void releaseCapture(String str) {
        String path = Uri.parse(str).getPath();
        if (path == null) {
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile.equals(this.reactContext.getExternalCacheDir()) || parentFile.equals(this.reactContext.getCacheDir())) {
                file.delete();
            }
        }
    }
}
