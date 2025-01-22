package com.imagepicker.media;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableMap;
import java.io.File;

/* loaded from: classes08-dex2jar.jar:com/imagepicker/media/ImageConfig.class */
public class ImageConfig {
    public final int maxHeight;
    public final int maxWidth;

    @Nullable
    public final File original;
    public final int quality;

    @Nullable
    public final File resized;
    public final int rotation;
    public final boolean saveToCameraRoll;

    public ImageConfig(@Nullable File file, @Nullable File file2, int i, int i2, int i3, int i4, boolean z) {
        this.original = file;
        this.resized = file2;
        this.maxWidth = i;
        this.maxHeight = i2;
        this.quality = i3;
        this.rotation = i4;
        this.saveToCameraRoll = z;
    }

    public File getActualFile() {
        File file = this.resized;
        return file != null ? file : this.original;
    }

    @NonNull
    public ImageConfig updateFromOptions(@NonNull ReadableMap readableMap) {
        boolean z;
        int i = readableMap.hasKey("maxWidth") ? readableMap.getInt("maxWidth") : 0;
        int i2 = readableMap.hasKey("maxHeight") ? readableMap.getInt("maxHeight") : 0;
        int i3 = readableMap.hasKey("quality") ? (int) (readableMap.getDouble("quality") * 100.0d) : 100;
        int i4 = readableMap.hasKey("rotation") ? readableMap.getInt("rotation") : 0;
        if (readableMap.hasKey("storageOptions")) {
            ReadableMap map = readableMap.getMap("storageOptions");
            if (map.hasKey("cameraRoll")) {
                z = map.getBoolean("cameraRoll");
                return new ImageConfig(this.original, this.resized, i, i2, i3, i4, z);
            }
        }
        z = false;
        return new ImageConfig(this.original, this.resized, i, i2, i3, i4, z);
    }

    public boolean useOriginal(int i, int i2, int i3) {
        int i4 = this.maxWidth;
        if ((i >= i4 || i4 <= 0) && this.maxWidth != 0) {
            return false;
        }
        int i5 = this.maxHeight;
        if (((i2 >= i5 || i5 <= 0) && this.maxHeight != 0) || this.quality != 100) {
            return false;
        }
        int i6 = this.rotation;
        return i6 == 0 || i3 == i6;
    }

    @NonNull
    public ImageConfig withMaxHeight(int i) {
        return new ImageConfig(this.original, this.resized, this.maxWidth, i, this.quality, this.rotation, this.saveToCameraRoll);
    }

    @NonNull
    public ImageConfig withMaxWidth(int i) {
        return new ImageConfig(this.original, this.resized, i, this.maxHeight, this.quality, this.rotation, this.saveToCameraRoll);
    }

    @NonNull
    public ImageConfig withOriginalFile(@Nullable File file) {
        return new ImageConfig(file, this.resized, this.maxWidth, this.maxHeight, this.quality, this.rotation, this.saveToCameraRoll);
    }

    @NonNull
    public ImageConfig withQuality(int i) {
        return new ImageConfig(this.original, this.resized, this.maxWidth, this.maxHeight, i, this.rotation, this.saveToCameraRoll);
    }

    @NonNull
    public ImageConfig withResizedFile(@Nullable File file) {
        return new ImageConfig(this.original, file, this.maxWidth, this.maxHeight, this.quality, this.rotation, this.saveToCameraRoll);
    }

    @NonNull
    public ImageConfig withRotation(int i) {
        return new ImageConfig(this.original, this.resized, this.maxWidth, this.maxHeight, this.quality, i, this.saveToCameraRoll);
    }

    @NonNull
    public ImageConfig withSaveToCameraRoll(@Nullable boolean z) {
        return new ImageConfig(this.original, this.resized, this.maxWidth, this.maxHeight, this.quality, this.rotation, z);
    }
}
