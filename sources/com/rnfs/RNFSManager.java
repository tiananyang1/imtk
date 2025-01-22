package com.rnfs;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.rnfs.DownloadParams;
import com.rnfs.UploadParams;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/rnfs/RNFSManager.class */
public class RNFSManager extends ReactContextBaseJavaModule {
    private static final String RNFSCachesDirectoryPath = "RNFSCachesDirectoryPath";
    private static final String RNFSDocumentDirectory = "RNFSDocumentDirectory";
    private static final String RNFSDocumentDirectoryPath = "RNFSDocumentDirectoryPath";
    private static final String RNFSExternalCachesDirectoryPath = "RNFSExternalCachesDirectoryPath";
    private static final String RNFSExternalDirectoryPath = "RNFSExternalDirectoryPath";
    private static final String RNFSExternalStorageDirectoryPath = "RNFSExternalStorageDirectoryPath";
    private static final String RNFSFileTypeDirectory = "RNFSFileTypeDirectory";
    private static final String RNFSFileTypeRegular = "RNFSFileTypeRegular";
    private static final String RNFSPicturesDirectoryPath = "RNFSPicturesDirectoryPath";
    private static final String RNFSTemporaryDirectoryPath = "RNFSTemporaryDirectoryPath";
    private SparseArray<Downloader> downloaders;
    private ReactApplicationContext reactContext;
    private SparseArray<Uploader> uploaders;

    /* loaded from: classes08-dex2jar.jar:com/rnfs/RNFSManager$CopyFileTask.class */
    private class CopyFileTask extends AsyncTask<String, Void, Exception> {
        private CopyFileTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Exception doInBackground(String... strArr) {
            String str = strArr[0];
            String str2 = strArr[1];
            try {
                InputStream inputStream = RNFSManager.this.getInputStream(str);
                OutputStream outputStream = RNFSManager.this.getOutputStream(str2, false);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        inputStream.close();
                        outputStream.close();
                        return null;
                    }
                    outputStream.write(bArr, 0, read);
                    Thread.yield();
                }
            } catch (Exception e) {
                return e;
            }
        }
    }

    public RNFSManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.downloaders = new SparseArray<>();
        this.uploaders = new SparseArray<>();
        this.reactContext = reactApplicationContext;
    }

    private void DeleteRecursive(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                DeleteRecursive(listFiles[i2]);
                i = i2 + 1;
            }
        }
        file.delete();
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void copyInputStream(java.io.InputStream r12, java.lang.String r13, java.lang.String r14, com.facebook.react.bridge.Promise r15) {
        /*
            Method dump skipped, instructions count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.RNFSManager.copyInputStream(java.io.InputStream, java.lang.String, java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    private Uri getFileUri(String str, boolean z) throws IORejectionException {
        Uri parse = Uri.parse(str);
        Uri uri = parse;
        if (parse.getScheme() == null) {
            File file = new File(str);
            if (!z && file.isDirectory()) {
                throw new IORejectionException("EISDIR", "EISDIR: illegal operation on a directory, read '" + str + "'");
            }
            uri = Uri.parse("file://" + str);
        }
        return uri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream getInputStream(String str) throws IORejectionException {
        try {
            InputStream openInputStream = this.reactContext.getContentResolver().openInputStream(getFileUri(str, false));
            if (openInputStream != null) {
                return openInputStream;
            }
            throw new IORejectionException("ENOENT", "ENOENT: could not open an input stream for '" + str + "'");
        } catch (FileNotFoundException e) {
            throw new IORejectionException("ENOENT", "ENOENT: no such file or directory, open '" + str + "'");
        }
    }

    private static byte[] getInputStreamBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        return byteArray;
                    } catch (IOException e) {
                        return byteArray;
                    }
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    private String getOriginalFilepath(String str, boolean z) throws IORejectionException {
        Uri fileUri = getFileUri(str, z);
        String str2 = str;
        if (fileUri.getScheme().equals("content")) {
            try {
                Cursor query = this.reactContext.getContentResolver().query(fileUri, null, null, null, null);
                str2 = str;
                if (query.moveToFirst()) {
                    str2 = query.getString(query.getColumnIndexOrThrow("_data"));
                }
            } catch (IllegalArgumentException e) {
                return str;
            }
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OutputStream getOutputStream(String str, boolean z) throws IORejectionException {
        try {
            OutputStream openOutputStream = this.reactContext.getContentResolver().openOutputStream(getFileUri(str, false), z ? "wa" : "w");
            if (openOutputStream != null) {
                return openOutputStream;
            }
            throw new IORejectionException("ENOENT", "ENOENT: could not open an output stream for '" + str + "'");
        } catch (FileNotFoundException e) {
            throw new IORejectionException("ENOENT", "ENOENT: no such file or directory, open '" + str + "'");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reject(Promise promise, String str, Exception exc) {
        if (exc instanceof FileNotFoundException) {
            rejectFileNotFound(promise, str);
        } else if (!(exc instanceof IORejectionException)) {
            promise.reject((String) null, exc.getMessage());
        } else {
            IORejectionException iORejectionException = (IORejectionException) exc;
            promise.reject(iORejectionException.getCode(), iORejectionException.getMessage());
        }
    }

    private void rejectFileIsDirectory(Promise promise) {
        promise.reject("EISDIR", "EISDIR: illegal operation on a directory, read");
    }

    private void rejectFileNotFound(Promise promise, String str) {
        promise.reject("ENOENT", "ENOENT: no such file or directory, open '" + str + "'");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(ReactContext reactContext, String str, @Nullable WritableMap writableMap) {
        reactContext.getJSModule(RCTNativeAppEventEmitter.class).emit(str, writableMap);
    }

    @ReactMethod
    public void appendFile(String str, String str2, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            OutputStream outputStream = getOutputStream(str, true);
            outputStream.write(decode);
            outputStream.close();
            promise.resolve((Object) null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.rnfs.RNFSManager$2] */
    @ReactMethod
    public void copyFile(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        new CopyFileTask() { // from class: com.rnfs.RNFSManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Exception exc) {
                if (exc == null) {
                    promise.resolve((Object) null);
                } else {
                    exc.printStackTrace();
                    RNFSManager.this.reject(promise, str, exc);
                }
            }
        }.execute(new String[]{str, str2});
    }

    @ReactMethod
    public void copyFileAssets(String str, String str2, Promise promise) {
        try {
            copyInputStream(getReactApplicationContext().getAssets().open(str), str, str2, promise);
        } catch (IOException e) {
            reject(promise, str, new Exception(String.format("Asset '%s' could not be opened", str)));
        }
    }

    @ReactMethod
    public void downloadFile(final ReadableMap readableMap, final Promise promise) {
        try {
            File file = new File(readableMap.getString("toFile"));
            URL url = new URL(readableMap.getString("fromUrl"));
            final int i = readableMap.getInt("jobId");
            ReadableMap map = readableMap.getMap("headers");
            int i2 = readableMap.getInt("progressDivider");
            int i3 = readableMap.getInt("readTimeout");
            int i4 = readableMap.getInt("connectionTimeout");
            DownloadParams downloadParams = new DownloadParams();
            downloadParams.src = url;
            downloadParams.dest = file;
            downloadParams.headers = map;
            downloadParams.progressDivider = i2;
            downloadParams.readTimeout = i3;
            downloadParams.connectionTimeout = i4;
            downloadParams.onTaskCompleted = new DownloadParams.OnTaskCompleted() { // from class: com.rnfs.RNFSManager.3
                @Override // com.rnfs.DownloadParams.OnTaskCompleted
                public void onTaskCompleted(DownloadResult downloadResult) {
                    if (downloadResult.exception != null) {
                        RNFSManager.this.reject(promise, readableMap.getString("toFile"), downloadResult.exception);
                        return;
                    }
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("jobId", i);
                    createMap.putInt("statusCode", downloadResult.statusCode);
                    createMap.putDouble("bytesWritten", downloadResult.bytesWritten);
                    promise.resolve(createMap);
                }
            };
            downloadParams.onDownloadBegin = new DownloadParams.OnDownloadBegin() { // from class: com.rnfs.RNFSManager.4
                @Override // com.rnfs.DownloadParams.OnDownloadBegin
                public void onDownloadBegin(int i5, long j, Map<String, String> map2) {
                    WritableMap createMap = Arguments.createMap();
                    for (Map.Entry<String, String> entry : map2.entrySet()) {
                        createMap.putString(entry.getKey(), entry.getValue());
                    }
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putInt("jobId", i);
                    createMap2.putInt("statusCode", i5);
                    createMap2.putDouble("contentLength", j);
                    createMap2.putMap("headers", createMap);
                    RNFSManager rNFSManager = RNFSManager.this;
                    rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "DownloadBegin-" + i, createMap2);
                }
            };
            downloadParams.onDownloadProgress = new DownloadParams.OnDownloadProgress() { // from class: com.rnfs.RNFSManager.5
                @Override // com.rnfs.DownloadParams.OnDownloadProgress
                public void onDownloadProgress(long j, long j2) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("jobId", i);
                    createMap.putDouble("contentLength", j);
                    createMap.putDouble("bytesWritten", j2);
                    RNFSManager rNFSManager = RNFSManager.this;
                    rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "DownloadProgress-" + i, createMap);
                }
            };
            Downloader downloader = new Downloader();
            downloader.execute(downloadParams);
            this.downloaders.put(i, downloader);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, readableMap.getString("toFile"), e);
        }
    }

    @ReactMethod
    public void exists(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(new File(str).exists()));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void existsAssets(String str, Promise promise) {
        InputStream open;
        try {
            AssetManager assets = getReactApplicationContext().getAssets();
            try {
                String[] list = assets.list(str);
                if (list != null && list.length > 0) {
                    promise.resolve(true);
                    return;
                }
            } catch (Exception e) {
            }
            InputStream inputStream = null;
            InputStream inputStream2 = null;
            try {
                try {
                    open = assets.open(str);
                    inputStream2 = open;
                    inputStream = open;
                    promise.resolve(true);
                } catch (Throwable th) {
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Exception e2) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                inputStream2 = inputStream;
                promise.resolve(false);
                if (inputStream == null) {
                    return;
                }
            }
            if (open != null) {
                inputStream = open;
                try {
                    inputStream.close();
                } catch (Exception e4) {
                }
            }
        } catch (Exception e5) {
            e5.printStackTrace();
            reject(promise, str, e5);
        }
    }

    @ReactMethod
    public void getAllExternalFilesDirs(Promise promise) {
        File[] externalFilesDirs = getReactApplicationContext().getExternalFilesDirs((String) null);
        WritableArray createArray = Arguments.createArray();
        int length = externalFilesDirs.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                promise.resolve(createArray);
                return;
            } else {
                createArray.pushString(externalFilesDirs[i2].getAbsolutePath());
                i = i2 + 1;
            }
        }
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(RNFSDocumentDirectory, 0);
        hashMap.put(RNFSDocumentDirectoryPath, getReactApplicationContext().getFilesDir().getAbsolutePath());
        hashMap.put(RNFSTemporaryDirectoryPath, getReactApplicationContext().getCacheDir().getAbsolutePath());
        hashMap.put(RNFSPicturesDirectoryPath, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        hashMap.put(RNFSCachesDirectoryPath, getReactApplicationContext().getCacheDir().getAbsolutePath());
        hashMap.put(RNFSFileTypeRegular, 0);
        hashMap.put(RNFSFileTypeDirectory, 1);
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            hashMap.put(RNFSExternalStorageDirectoryPath, externalStorageDirectory.getAbsolutePath());
        } else {
            hashMap.put(RNFSExternalStorageDirectoryPath, null);
        }
        File externalFilesDir = getReactApplicationContext().getExternalFilesDir((String) null);
        if (externalFilesDir != null) {
            hashMap.put(RNFSExternalDirectoryPath, externalFilesDir.getAbsolutePath());
        } else {
            hashMap.put(RNFSExternalDirectoryPath, null);
        }
        File externalCacheDir = getReactApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            hashMap.put(RNFSExternalCachesDirectoryPath, externalCacheDir.getAbsolutePath());
            return hashMap;
        }
        hashMap.put(RNFSExternalCachesDirectoryPath, null);
        return hashMap;
    }

    @ReactMethod
    public void getFSInfo(Promise promise) {
        long availableBlocks;
        long blockCount;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            blockCount = statFs.getTotalBytes();
            availableBlocks = statFs.getFreeBytes();
        } else {
            long blockSize = statFs.getBlockSize();
            availableBlocks = statFs.getAvailableBlocks() * blockSize;
            blockCount = statFs.getBlockCount() * blockSize;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("totalSpace", blockCount);
        createMap.putDouble("freeSpace", availableBlocks);
        promise.resolve(createMap);
    }

    public String getName() {
        return "RNFSManager";
    }

    @ReactMethod
    public void hash(String str, String str2, Promise promise) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("md5", "MD5");
            hashMap.put("sha1", CommonUtils.SHA1_INSTANCE);
            hashMap.put("sha224", "SHA-224");
            hashMap.put("sha256", CommonUtils.SHA256_INSTANCE);
            hashMap.put("sha384", "SHA-384");
            hashMap.put("sha512", "SHA-512");
            if (!hashMap.containsKey(str2)) {
                throw new Exception("Invalid hash algorithm");
            }
            File file = new File(str);
            if (file.isDirectory()) {
                rejectFileIsDirectory(promise);
                return;
            }
            if (!file.exists()) {
                rejectFileNotFound(promise, str);
                return;
            }
            MessageDigest messageDigest = MessageDigest.getInstance((String) hashMap.get(str2));
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[10240];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                } else {
                    messageDigest.update(bArr, 0, read);
                }
            }
            StringBuilder sb = new StringBuilder();
            byte[] digest = messageDigest.digest();
            int length = digest.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    promise.resolve(sb.toString());
                    return;
                } else {
                    sb.append(String.format("%02x", Byte.valueOf(digest[i2])));
                    i = i2 + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void mkdir(String str, ReadableMap readableMap, Promise promise) {
        try {
            File file = new File(str);
            file.mkdirs();
            if (!file.exists()) {
                throw new Exception("Directory could not be created");
            }
            promise.resolve((Object) null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.rnfs.RNFSManager$1] */
    @ReactMethod
    public void moveFile(final String str, String str2, ReadableMap readableMap, final Promise promise) {
        try {
            final File file = new File(str);
            if (file.renameTo(new File(str2))) {
                promise.resolve(true);
            } else {
                new CopyFileTask() { // from class: com.rnfs.RNFSManager.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(Exception exc) {
                        if (exc == null) {
                            file.delete();
                            promise.resolve(true);
                        } else {
                            exc.printStackTrace();
                            RNFSManager.this.reject(promise, str, exc);
                        }
                    }
                }.execute(new String[]{str, str2});
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void pathForBundle(String str, Promise promise) {
    }

    @ReactMethod
    public void pathForGroup(String str, Promise promise) {
    }

    @ReactMethod
    public void read(String str, int i, int i2, Promise promise) {
        try {
            InputStream inputStream = getInputStream(str);
            byte[] bArr = new byte[i];
            inputStream.skip(i2);
            promise.resolve(Base64.encodeToString(bArr, 0, inputStream.read(bArr, 0, i), 2));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readDir(String str, Promise promise) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("Folder does not exist");
            }
            File[] listFiles = file.listFiles();
            WritableArray createArray = Arguments.createArray();
            int length = listFiles.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    promise.resolve(createArray);
                    return;
                }
                File file2 = listFiles[i2];
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("mtime", file2.lastModified() / 1000.0d);
                createMap.putString("name", file2.getName());
                createMap.putString("path", file2.getAbsolutePath());
                createMap.putInt("size", (int) file2.length());
                createMap.putInt("type", file2.isDirectory() ? 1 : 0);
                createArray.pushMap(createMap);
                i = i2 + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0108  */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void readDirAssets(java.lang.String r7, com.facebook.react.bridge.Promise r8) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.RNFSManager.readDirAssets(java.lang.String, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void readFile(String str, Promise promise) {
        try {
            promise.resolve(Base64.encodeToString(getInputStreamBytes(getInputStream(str)), 2));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void readFileAssets(String str, Promise promise) {
        InputStream open;
        InputStream inputStream = null;
        InputStream inputStream2 = null;
        try {
            try {
                open = getReactApplicationContext().getAssets().open(str, 0);
            } catch (Exception e) {
                e.printStackTrace();
                reject(promise, str, e);
                if (0 == 0) {
                    return;
                }
            }
            if (open == null) {
                reject(promise, str, new Exception("Failed to open file"));
                if (open != null) {
                    try {
                        open.close();
                        return;
                    } catch (IOException e2) {
                        return;
                    }
                }
                return;
            }
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            promise.resolve(Base64.encodeToString(bArr, 2));
            if (open != null) {
                inputStream = open;
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream2.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    @ReactMethod
    public void scanFile(String str, final Promise promise) {
        MediaScannerConnection.scanFile(getReactApplicationContext(), new String[]{str}, null, new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.rnfs.RNFSManager.9
            @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
            public void onMediaScannerConnected() {
            }

            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str2, Uri uri) {
                promise.resolve(str2);
            }
        });
    }

    @ReactMethod
    public void setReadable(String str, Boolean bool, Boolean bool2, Promise promise) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            file.setReadable(bool.booleanValue(), bool2.booleanValue());
            promise.resolve(true);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void stat(String str, Promise promise) {
        int i = 1;
        try {
            String originalFilepath = getOriginalFilepath(str, true);
            File file = new File(originalFilepath);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("ctime", (int) (file.lastModified() / 1000));
            createMap.putInt("mtime", (int) (file.lastModified() / 1000));
            createMap.putInt("size", (int) file.length());
            if (!file.isDirectory()) {
                i = 0;
            }
            createMap.putInt("type", i);
            createMap.putString("originalFilepath", originalFilepath);
            promise.resolve(createMap);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void stopDownload(int i) {
        Downloader downloader = this.downloaders.get(i);
        if (downloader != null) {
            downloader.stop();
        }
    }

    @ReactMethod
    public void stopUpload(int i) {
        Uploader uploader = this.uploaders.get(i);
        if (uploader != null) {
            uploader.stop();
        }
    }

    @ReactMethod
    public void touch(String str, double d, double d2, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(new File(str).setLastModified((long) d)));
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void unlink(String str, Promise promise) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                throw new Exception("File does not exist");
            }
            DeleteRecursive(file);
            promise.resolve((Object) null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void uploadFiles(final ReadableMap readableMap, final Promise promise) {
        try {
            ReadableArray array = readableMap.getArray("files");
            URL url = new URL(readableMap.getString("toUrl"));
            final int i = readableMap.getInt("jobId");
            ReadableMap map = readableMap.getMap("headers");
            ReadableMap map2 = readableMap.getMap("fields");
            String string = readableMap.getString("method");
            ArrayList<ReadableMap> arrayList = new ArrayList<>();
            UploadParams uploadParams = new UploadParams();
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= array.size()) {
                    uploadParams.src = url;
                    uploadParams.files = arrayList;
                    uploadParams.headers = map;
                    uploadParams.method = string;
                    uploadParams.fields = map2;
                    uploadParams.onUploadComplete = new UploadParams.onUploadComplete() { // from class: com.rnfs.RNFSManager.6
                        @Override // com.rnfs.UploadParams.onUploadComplete
                        public void onUploadComplete(UploadResult uploadResult) {
                            if (uploadResult.exception != null) {
                                RNFSManager.this.reject(promise, readableMap.getString("toUrl"), uploadResult.exception);
                                return;
                            }
                            WritableMap createMap = Arguments.createMap();
                            createMap.putInt("jobId", i);
                            createMap.putInt("statusCode", uploadResult.statusCode);
                            createMap.putMap("headers", uploadResult.headers);
                            createMap.putString("body", uploadResult.body);
                            promise.resolve(createMap);
                        }
                    };
                    uploadParams.onUploadBegin = new UploadParams.onUploadBegin() { // from class: com.rnfs.RNFSManager.7
                        @Override // com.rnfs.UploadParams.onUploadBegin
                        public void onUploadBegin() {
                            WritableMap createMap = Arguments.createMap();
                            createMap.putInt("jobId", i);
                            RNFSManager rNFSManager = RNFSManager.this;
                            rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "UploadBegin-" + i, createMap);
                        }
                    };
                    uploadParams.onUploadProgress = new UploadParams.onUploadProgress() { // from class: com.rnfs.RNFSManager.8
                        @Override // com.rnfs.UploadParams.onUploadProgress
                        public void onUploadProgress(int i4, int i5) {
                            WritableMap createMap = Arguments.createMap();
                            createMap.putInt("jobId", i);
                            createMap.putInt("totalBytesExpectedToSend", i4);
                            createMap.putInt("totalBytesSent", i5);
                            RNFSManager rNFSManager = RNFSManager.this;
                            rNFSManager.sendEvent(rNFSManager.getReactApplicationContext(), "UploadProgress-" + i, createMap);
                        }
                    };
                    Uploader uploader = new Uploader();
                    uploader.execute(uploadParams);
                    this.uploaders.put(i, uploader);
                    return;
                }
                arrayList.add(array.getMap(i3));
                i2 = i3 + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, readableMap.getString("toUrl"), e);
        }
    }

    @ReactMethod
    public void write(String str, String str2, int i, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            if (i < 0) {
                OutputStream outputStream = getOutputStream(str, true);
                outputStream.write(decode);
                outputStream.close();
            } else {
                RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
                randomAccessFile.seek(i);
                randomAccessFile.write(decode);
                randomAccessFile.close();
            }
            promise.resolve((Object) null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }

    @ReactMethod
    public void writeFile(String str, String str2, ReadableMap readableMap, Promise promise) {
        try {
            byte[] decode = Base64.decode(str2, 0);
            OutputStream outputStream = getOutputStream(str, false);
            outputStream.write(decode);
            outputStream.close();
            promise.resolve((Object) null);
        } catch (Exception e) {
            e.printStackTrace();
            reject(promise, str, e);
        }
    }
}
