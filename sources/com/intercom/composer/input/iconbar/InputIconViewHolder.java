package com.intercom.composer.input.iconbar;

import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.intercom.composer.C0123R;
import com.intercom.composer.input.Input;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/iconbar/InputIconViewHolder.class */
class InputIconViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @VisibleForTesting
    final ImageView imageView;

    @VisibleForTesting
    final InputClickedListener listener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputIconViewHolder(View view, InputClickedListener inputClickedListener) {
        super(view);
        this.listener = inputClickedListener;
        this.imageView = (ImageView) view.findViewById(C0123R.id.input_icon_image_view);
        this.imageView.setOnClickListener(this);
        view.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bind(Input input, boolean z) {
        ImageView imageView = this.imageView;
        imageView.setImageDrawable(input.getIconDrawable(imageView.getContext()));
        this.imageView.setSelected(z);
    }

    @Override // android.view.View.OnClickListener
    @SensorsDataInstrumented
    public void onClick(View view) {
        this.listener.onInputClicked(this);
        SensorsDataAutoTrackHelper.trackViewOnClick(view);
    }
}
