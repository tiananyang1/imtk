package com.intercom.input.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.intercom.composer.ImageLoader;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryLightBoxFragment.class */
public abstract class GalleryLightBoxFragment extends Fragment implements View.OnClickListener {

    @VisibleForTesting
    GalleryImage galleryImage;
    private ImageLoader imageLoader;

    /* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryLightBoxFragment$Injector.class */
    public interface Injector {
        ImageLoader getImageLoader(GalleryLightBoxFragment galleryLightBoxFragment);
    }

    public static Bundle createArgs(GalleryImage galleryImage) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(GalleryInputFullScreenActivity.GALLERY_IMAGE, galleryImage);
        return bundle;
    }

    protected abstract Injector getInjector(GalleryLightBoxFragment galleryLightBoxFragment);

    @Override // android.view.View.OnClickListener
    @SensorsDataInstrumented
    public void onClick(View view) {
        if (view.getId() == C0134R.id.lightbox_send_button) {
            Intent intent = new Intent();
            intent.putExtra(GalleryInputFullScreenActivity.GALLERY_IMAGE, this.galleryImage);
            getActivity().setResult(-1, intent);
        }
        getActivity().onBackPressed();
        SensorsDataAutoTrackHelper.trackViewOnClick(view);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.galleryImage = (GalleryImage) getArguments().getParcelable(GalleryInputFullScreenActivity.GALLERY_IMAGE);
        this.imageLoader = getInjector(this).getImageLoader(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(C0134R.layout.intercom_composer_gallery_lightbox_fragment, viewGroup, false);
    }

    @SensorsDataInstrumented
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        SensorsDataAutoTrackHelper.trackOnHiddenChanged(this, z);
    }

    @SensorsDataInstrumented
    public void onResume() {
        super.onResume();
        SensorsDataAutoTrackHelper.trackFragmentResume(this);
    }

    @SensorsDataInstrumented
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.imageLoader.loadImageIntoView(this.galleryImage, (ImageView) view.findViewById(C0134R.id.lightbox_image));
        view.findViewById(C0134R.id.lightbox_send_button).setOnClickListener(this);
        view.findViewById(C0134R.id.lightbox_close_button).setOnClickListener(this);
        SensorsDataAutoTrackHelper.onFragmentViewCreated(this, view, bundle);
    }

    @SensorsDataInstrumented
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        SensorsDataAutoTrackHelper.trackFragmentSetUserVisibleHint(this, z);
    }
}
