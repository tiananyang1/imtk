package com.intercom.composer;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.intercom.input.gallery.GalleryImage;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/ImageLoader.class */
public interface ImageLoader {
    void clear(ImageView imageView);

    @Nullable
    Bitmap getBitmapFromView(ImageView imageView);

    void loadImageIntoView(GalleryImage galleryImage, ImageView imageView);
}
