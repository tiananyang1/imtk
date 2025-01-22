package com.intercom.input.gallery;

import android.support.annotation.Nullable;
import com.intercom.composer.Creator;
import com.intercom.composer.input.IconProvider;
import com.intercom.composer.input.Input;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryInput.class */
public class GalleryInput extends Input<GalleryInputFragment> {
    private final Creator<GalleryInputFragment> fragmentCreator;

    @Nullable
    private final GalleryInputExpandedListener galleryInputExpandedListener;

    @Nullable
    private final GalleryOutputListener galleryOutputListener;

    public GalleryInput(String str, IconProvider iconProvider, @Nullable GalleryOutputListener galleryOutputListener, @Nullable GalleryInputExpandedListener galleryInputExpandedListener, Creator<GalleryInputFragment> creator) {
        super(str, iconProvider);
        this.galleryOutputListener = galleryOutputListener;
        this.galleryInputExpandedListener = galleryInputExpandedListener;
        this.fragmentCreator = creator;
    }

    @Override // com.intercom.composer.input.Input
    public GalleryInputFragment createFragment() {
        GalleryInputFragment create = this.fragmentCreator.create();
        create.setArguments(GalleryInputFragment.createArguments(false));
        create.setGalleryListener(this.galleryOutputListener);
        create.setGalleryExpandedListener(this.galleryInputExpandedListener);
        return create;
    }
}
