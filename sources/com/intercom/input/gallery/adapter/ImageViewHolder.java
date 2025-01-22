package com.intercom.input.gallery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.intercom.input.gallery.C0134R;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/adapter/ImageViewHolder.class */
class ImageViewHolder extends RecyclerView.ViewHolder {
    private final ImageView imageView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImageViewHolder(View view, final OnImageClickListener onImageClickListener) {
        super(view);
        this.imageView = (ImageView) view.findViewById(C0134R.id.thumbnail);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.intercom.input.gallery.adapter.ImageViewHolder.1
            @Override // android.view.View.OnClickListener
            @SensorsDataInstrumented
            public void onClick(View view2) {
                onImageClickListener.onImageClicked(ImageViewHolder.this);
                SensorsDataAutoTrackHelper.trackViewOnClick(view2);
            }
        });
    }

    public ImageView getImageView() {
        return this.imageView;
    }
}
