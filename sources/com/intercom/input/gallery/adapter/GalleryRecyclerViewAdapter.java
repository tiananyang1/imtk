package com.intercom.input.gallery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.intercom.composer.ImageLoader;
import com.intercom.input.gallery.C0134R;
import com.intercom.input.gallery.GalleryImage;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/adapter/GalleryRecyclerViewAdapter.class */
public class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final boolean expanded;
    private final List<GalleryImage> galleryImages;
    private final ImageLoader imageLoader;
    private final LayoutInflater layoutInflater;
    private final OnImageClickListener onImageClickListener;

    public GalleryRecyclerViewAdapter(LayoutInflater layoutInflater, List<GalleryImage> list, boolean z, OnImageClickListener onImageClickListener, ImageLoader imageLoader) {
        this.layoutInflater = layoutInflater;
        this.galleryImages = list;
        this.expanded = z;
        this.onImageClickListener = onImageClickListener;
        this.imageLoader = imageLoader;
    }

    public GalleryImage getItem(int i) {
        return this.galleryImages.get(i);
    }

    public int getItemCount() {
        return this.galleryImages.size();
    }

    public void onBindViewHolder(ImageViewHolder imageViewHolder, int i) {
        this.imageLoader.loadImageIntoView(this.galleryImages.get(i), imageViewHolder.getImageView());
    }

    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ImageViewHolder(this.layoutInflater.inflate(this.expanded ? C0134R.layout.intercom_composer_expanded_image_list_item : C0134R.layout.intercom_composer_image_list_item, viewGroup, false), this.onImageClickListener);
    }

    public void onViewRecycled(ImageViewHolder imageViewHolder) {
        super.onViewRecycled(imageViewHolder);
        this.imageLoader.clear(imageViewHolder.getImageView());
    }
}
