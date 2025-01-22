package com.intercom.input.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.stub.StubApp;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryLightBoxActivity.class */
public class GalleryLightBoxActivity extends AppCompatActivity {
    private static final String KEY_FRAGMENT_CLASS = "fragment_class";
    private Class<? extends GalleryLightBoxFragment> fragmentClass;

    @VisibleForTesting
    FragmentManager fragmentManager;

    @VisibleForTesting
    GalleryImage galleryImage;

    static {
        StubApp.interface11(10568);
    }

    public static Intent createIntent(Context context, GalleryImage galleryImage, Class<? extends GalleryLightBoxFragment> cls) {
        return new Intent(context, (Class<?>) GalleryLightBoxActivity.class).putExtra(GalleryInputFullScreenActivity.GALLERY_IMAGE, galleryImage).putExtra(KEY_FRAGMENT_CLASS, cls);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0134R.anim.intercom_composer_stay, C0134R.anim.intercom_composer_slide_down);
    }

    protected native void onCreate(Bundle bundle);
}
