package com.intercom.input.gallery;

import android.support.annotation.Nullable;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryInputDataSource.class */
public interface GalleryInputDataSource {

    /* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryInputDataSource$Listener.class */
    public interface Listener {
        void onError();

        void onSuccess(List<GalleryImage> list);
    }

    int getCount();

    void getImages(int i, @Nullable String str);

    int getPermissionStatus();

    boolean isLoading();

    void requestPermission();

    void setListener(Listener listener);
}
