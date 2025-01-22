package com.imagepicker.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import com.stub.StubApp;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;

/* loaded from: classes08-dex2jar.jar:com/imagepicker/utils/RealPathUtil.class */
public class RealPathUtil {
    @Nullable
    public static Uri compatUriFromFile(@NonNull Context context, @NonNull File file) {
        if (Build.VERSION.SDK_INT < 21) {
            return Uri.fromFile(file);
        }
        try {
            return FileProvider.getUriForFile(context, StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName() + ".provider", file);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor == null) {
                return null;
            }
            cursor.close();
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    @Nullable
    public static String getFileProviderPath(@NonNull Context context, @NonNull Uri uri) {
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), uri.getLastPathSegment());
        if (file.exists()) {
            return file.toString();
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    @Nullable
    public static String getRealPathFromURI(@NonNull Context context, @NonNull Uri uri) {
        Uri uri2;
        if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                return isGooglePhotosUri(uri) ? uri.getLastPathSegment() : isFileProviderUri(context, uri) ? getFileProviderPath(context, uri) : getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        }
        if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(Constants.COLON_SEPARATOR);
            if (!"primary".equalsIgnoreCase(split[0])) {
                return null;
            }
            return Environment.getExternalStorageDirectory() + "/" + split[1];
        }
        if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        }
        if (!isMediaDocument(uri)) {
            return null;
        }
        String[] split2 = DocumentsContract.getDocumentId(uri).split(Constants.COLON_SEPARATOR);
        String str = split2[0];
        if ("image".equals(str)) {
            uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if ("video".equals(str)) {
            uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri2 = null;
            if ("audio".equals(str)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
        }
        return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isFileProviderUri(@NonNull Context context, @NonNull Uri uri) {
        return (context.getPackageName() + ".provider").equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(@NonNull Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
