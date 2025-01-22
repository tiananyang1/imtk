package com.intercom.input.gallery;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.intercom.input.gallery.GalleryInputDataSource;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.FileNotFoundException;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/LocalImagesDataSource.class */
public class LocalImagesDataSource implements GalleryInputDataSource {
    private static final int ITEM_COUNT_LIMIT = 50;
    private static final int READ_EXTERNAL_STORAGE_REQUEST = 1;
    private Context context;
    private GalleryInputScreen galleryInputScreen;
    private GalleryInputDataSource.Listener listener;
    private boolean loading;
    private PermissionHelper permissionHelper;

    LocalImagesDataSource(Context context, PermissionHelper permissionHelper, GalleryInputScreen galleryInputScreen) {
        this.context = context;
        this.galleryInputScreen = galleryInputScreen;
        this.permissionHelper = permissionHelper;
    }

    public static GalleryInputDataSource create(GalleryInputFragment galleryInputFragment) {
        FragmentActivity activity = galleryInputFragment.getActivity();
        return new LocalImagesDataSource(activity, PermissionHelper.create(activity), galleryInputFragment);
    }

    private Point getImageHeightAndWidth(Cursor cursor, Uri uri) {
        int i;
        int i2;
        if (Build.VERSION.SDK_INT >= 16) {
            i2 = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsJsonConstants.ICON_HEIGHT_KEY));
            i = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsJsonConstants.ICON_WIDTH_KEY));
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                BitmapFactory.decodeStream(this.context.getContentResolver().openInputStream(uri), null, options);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            i = options.outWidth;
            i2 = options.outHeight;
        }
        return new Point(i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x00b7, code lost:            r5.close();     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00bf, code lost:            return r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0015, code lost:            if (r5.moveToFirst() != false) goto L4;     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0018, code lost:            r0 = r5.getLong(r5.getColumnIndexOrThrow("_id"));        r0 = r5.getString(r5.getColumnIndexOrThrow("mime_type"));        r0 = r5.getString(r5.getColumnIndexOrThrow(io.fabric.sdk.android.services.settings.SettingsJsonConstants.PROMPT_TITLE_KEY));        r0 = r5.getInt(r5.getColumnIndexOrThrow("_size"));     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0058, code lost:            if (r0 == null) goto L9;     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x005d, code lost:            if (r0 == null) goto L9;     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0060, code lost:            r0 = android.net.Uri.withAppendedPath(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, java.lang.String.valueOf(r0));        r0 = getImageHeightAndWidth(r5, r0);        r0.add(new com.intercom.input.gallery.GalleryImage.Builder().withFileName(r0).withUri(r0).isGif(false).withMimeType(r0).withImageWidth(r0.x).withImageHeight(r0.y).withFileSize(r0).build());     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00b4, code lost:            if (r5.moveToNext() != false) goto L14;     */
    @android.support.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.util.List<com.intercom.input.gallery.GalleryImage> galleryImagesFromCursor(android.database.Cursor r5) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r2 = r5
            int r2 = r2.getCount()
            r1.<init>(r2)
            r9 = r0
            r0 = r5
            boolean r0 = r0.moveToFirst()
            if (r0 == 0) goto Lb7
        L18:
            r0 = r5
            r1 = r5
            java.lang.String r2 = "_id"
            int r1 = r1.getColumnIndexOrThrow(r2)
            long r0 = r0.getLong(r1)
            r7 = r0
            r0 = r5
            r1 = r5
            java.lang.String r2 = "mime_type"
            int r1 = r1.getColumnIndexOrThrow(r2)
            java.lang.String r0 = r0.getString(r1)
            r10 = r0
            r0 = r5
            r1 = r5
            java.lang.String r2 = "title"
            int r1 = r1.getColumnIndexOrThrow(r2)
            java.lang.String r0 = r0.getString(r1)
            r11 = r0
            r0 = r5
            r1 = r5
            java.lang.String r2 = "_size"
            int r1 = r1.getColumnIndexOrThrow(r2)
            int r0 = r0.getInt(r1)
            r6 = r0
            r0 = r11
            if (r0 == 0) goto Lae
            r0 = r10
            if (r0 == 0) goto Lae
            android.net.Uri r0 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            r1 = r7
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.net.Uri r0 = android.net.Uri.withAppendedPath(r0, r1)
            r12 = r0
            r0 = r4
            r1 = r5
            r2 = r12
            android.graphics.Point r0 = r0.getImageHeightAndWidth(r1, r2)
            r13 = r0
            r0 = r9
            com.intercom.input.gallery.GalleryImage$Builder r1 = new com.intercom.input.gallery.GalleryImage$Builder
            r2 = r1
            r2.<init>()
            r2 = r11
            com.intercom.input.gallery.GalleryImage$Builder r1 = r1.withFileName(r2)
            r2 = r12
            com.intercom.input.gallery.GalleryImage$Builder r1 = r1.withUri(r2)
            r2 = 0
            com.intercom.input.gallery.GalleryImage$Builder r1 = r1.isGif(r2)
            r2 = r10
            com.intercom.input.gallery.GalleryImage$Builder r1 = r1.withMimeType(r2)
            r2 = r13
            int r2 = r2.x
            com.intercom.input.gallery.GalleryImage$Builder r1 = r1.withImageWidth(r2)
            r2 = r13
            int r2 = r2.y
            com.intercom.input.gallery.GalleryImage$Builder r1 = r1.withImageHeight(r2)
            r2 = r6
            com.intercom.input.gallery.GalleryImage$Builder r1 = r1.withFileSize(r2)
            com.intercom.input.gallery.GalleryImage r1 = r1.build()
            boolean r0 = r0.add(r1)
        Lae:
            r0 = r5
            boolean r0 = r0.moveToNext()
            if (r0 != 0) goto L18
        Lb7:
            r0 = r5
            r0.close()
            r0 = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.intercom.input.gallery.LocalImagesDataSource.galleryImagesFromCursor(android.database.Cursor):java.util.List");
    }

    @Override // com.intercom.input.gallery.GalleryInputDataSource
    public int getCount() {
        int i = 0;
        if (getPermissionStatus() != 0) {
            return 0;
        }
        Cursor query = this.context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (query != null) {
            i = query.getCount();
            query.close();
        }
        return i;
    }

    @Override // com.intercom.input.gallery.GalleryInputDataSource
    public void getImages(int i, @Nullable String str) {
        this.loading = true;
        String[] strArr = Build.VERSION.SDK_INT >= 16 ? new String[]{"_id", "date_added", "mime_type", SettingsJsonConstants.PROMPT_TITLE_KEY, SettingsJsonConstants.ICON_HEIGHT_KEY, SettingsJsonConstants.ICON_WIDTH_KEY, "_size"} : new String[]{"_id", "date_added", "mime_type", SettingsJsonConstants.PROMPT_TITLE_KEY, "_size"};
        Cursor query = this.context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, null, null, "date_added DESC LIMIT 50 OFFSET " + i);
        this.loading = false;
        if (query == null) {
            this.listener.onError();
        } else {
            this.listener.onSuccess(galleryImagesFromCursor(query));
        }
    }

    @Override // com.intercom.input.gallery.GalleryInputDataSource
    public int getPermissionStatus() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.permissionHelper.getPermissionStatus("android.permission.READ_EXTERNAL_STORAGE");
        }
        return 0;
    }

    @Override // com.intercom.input.gallery.GalleryInputDataSource
    public boolean isLoading() {
        return this.loading;
    }

    @Override // com.intercom.input.gallery.GalleryInputDataSource
    public void requestPermission() {
        this.permissionHelper.setAskedForPermissionPref(true);
        if (Build.VERSION.SDK_INT >= 23) {
            this.galleryInputScreen.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
        }
    }

    @Override // com.intercom.input.gallery.GalleryInputDataSource
    public void setListener(GalleryInputDataSource.Listener listener) {
        this.listener = listener;
    }
}
