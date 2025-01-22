package com.imagepicker.utils;

import android.content.Context;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.react.bridge.ReadableMap;
import com.imagepicker.ResponseHelper;
import com.imagepicker.media.ImageConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.UUID;

/* loaded from: classes08-dex2jar.jar:com/imagepicker/utils/MediaUtils.class */
public class MediaUtils {

    /* loaded from: classes08-dex2jar.jar:com/imagepicker/utils/MediaUtils$ReadExifResult.class */
    public static class ReadExifResult {
        public final int currentRotation;
        public final Throwable error;

        public ReadExifResult(int i, @Nullable Throwable th) {
            this.currentRotation = i;
            this.error = th;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/imagepicker/utils/MediaUtils$RolloutPhotoResult.class */
    public static class RolloutPhotoResult {
        public final Throwable error;
        public final ImageConfig imageConfig;

        public RolloutPhotoResult(@NonNull ImageConfig imageConfig, @Nullable Throwable th) {
            this.imageConfig = imageConfig;
            this.error = th;
        }
    }

    @Nullable
    public static File createNewFile(@NonNull Context context, @NonNull ReadableMap readableMap, @NonNull boolean z) {
        String str = "image-" + UUID.randomUUID().toString() + ".jpg";
        File externalFilesDir = (!ReadableMapUtils.hasAndNotNullReadableMap(readableMap, "storageOptions") || z) ? context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) : Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(externalFilesDir, str);
        try {
            externalFilesDir.mkdirs();
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void fileScan(@Nullable Context context, @NonNull String str) {
        if (context == null) {
            return;
        }
        MediaScannerConnection.scanFile(context, new String[]{str}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.imagepicker.utils.MediaUtils.1
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str2, Uri uri) {
                Log.i("TAG", "Finished scanning " + str2);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00b1, code lost:            if (r10.maxWidth > r22) goto L32;     */
    @android.support.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.imagepicker.media.ImageConfig getResizedImage(@android.support.annotation.NonNull android.content.Context r8, @android.support.annotation.NonNull com.facebook.react.bridge.ReadableMap r9, @android.support.annotation.NonNull com.imagepicker.media.ImageConfig r10, int r11, int r12, int r13) {
        /*
            Method dump skipped, instructions count: 571
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imagepicker.utils.MediaUtils.getResizedImage(android.content.Context, com.facebook.react.bridge.ReadableMap, com.imagepicker.media.ImageConfig, int, int, int):com.imagepicker.media.ImageConfig");
    }

    private static void moveFile(@NonNull File file, @NonNull File file2) throws IOException {
        FileChannel fileChannel;
        FileChannel fileChannel2;
        try {
            fileChannel = new FileInputStream(file).getChannel();
            try {
                fileChannel2 = new FileOutputStream(file2).getChannel();
            } catch (Throwable th) {
                th = th;
                fileChannel2 = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileChannel = null;
            fileChannel2 = null;
        }
        try {
            fileChannel.transferTo(0L, fileChannel.size(), fileChannel2);
            file.delete();
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    throw th;
                }
            }
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            throw th;
        }
    }

    public static ReadExifResult readExifInterface(@NonNull ResponseHelper responseHelper, @NonNull ImageConfig imageConfig) {
        int i = 0;
        int i2 = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(imageConfig.original.getAbsolutePath());
            float[] fArr = new float[2];
            exifInterface.getLatLong(fArr);
            float f = fArr[0];
            boolean z = true;
            float f2 = fArr[1];
            if (f != 0.0f || f2 != 0.0f) {
                responseHelper.putDouble("latitude", f);
                responseHelper.putDouble("longitude", f2);
            }
            String attribute = exifInterface.getAttribute("DateTime");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                StringBuilder sb = new StringBuilder(simpleDateFormat2.format(simpleDateFormat.parse(attribute)));
                sb.append("Z");
                responseHelper.putString("timestamp", sb.toString());
            } catch (Exception e) {
            }
            int attributeInt = exifInterface.getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt == 6) {
                    i = 90;
                } else if (attributeInt == 8) {
                    i = 270;
                }
                z = false;
            } else {
                i = 180;
            }
            responseHelper.putInt("originalRotation", i);
            int i3 = i;
            responseHelper.putBoolean("isVertical", z);
            i2 = i;
            return new ReadExifResult(i, null);
        } catch (IOException e2) {
            e2.printStackTrace();
            return new ReadExifResult(i2, e2);
        }
    }

    public static void removeUselessFiles(int i, @NonNull ImageConfig imageConfig) {
        if (i != 13001) {
            return;
        }
        if (imageConfig.original != null && imageConfig.original.exists()) {
            imageConfig.original.delete();
        }
        if (imageConfig.resized == null || !imageConfig.resized.exists()) {
            return;
        }
        imageConfig.resized.delete();
    }

    @Nullable
    public static RolloutPhotoResult rolloutPhotoFromCamera(@NonNull ImageConfig imageConfig) {
        File file = imageConfig.resized == null ? imageConfig.original : imageConfig.resized;
        File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath(), file.getName());
        try {
            moveFile(file, file2);
            return new RolloutPhotoResult(imageConfig.resized != null ? imageConfig.withResizedFile(file2) : imageConfig.withOriginalFile(file2), null);
        } catch (IOException e) {
            e.printStackTrace();
            return new RolloutPhotoResult(imageConfig, e);
        }
    }
}
