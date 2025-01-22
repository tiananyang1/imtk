package com.intercom.input.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;
import com.stub.StubApp;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryInputFullScreenActivity.class */
public class GalleryInputFullScreenActivity extends AppCompatActivity implements GalleryOutputListener {
    public static final String GALLERY_IMAGE = "gallery_image";
    private static final String KEY_FRAGMENT_ARGS = "fragment_args";
    private static final String KEY_FRAGMENT_CLASS = "fragment_class";
    private Bundle fragmentArgs;
    private Class<? extends GalleryInputFragment> fragmentClass;

    static {
        StubApp.interface11(10566);
    }

    public static Intent createIntent(Context context, Class<? extends GalleryInputFragment> cls, Bundle bundle) {
        return new Intent(context, (Class<?>) GalleryInputFullScreenActivity.class).putExtra(KEY_FRAGMENT_CLASS, cls).putExtra(KEY_FRAGMENT_ARGS, bundle);
    }

    public void onBackPressed() {
        super.onBackPressed();
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        overridePendingTransition(C0134R.anim.intercom_composer_stay, C0134R.anim.intercom_composer_slide_down);
    }

    protected native void onCreate(Bundle bundle);

    @Override // com.intercom.input.gallery.GalleryOutputListener
    public void onGalleryOutputReceived(GalleryImage galleryImage) {
        Intent intent = new Intent();
        intent.putExtra(GALLERY_IMAGE, galleryImage);
        setResult(-1, intent);
        onBackPressed();
    }

    @SensorsDataInstrumented
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            SensorsDataAutoTrackHelper.trackMenuItem(this, menuItem);
            return true;
        }
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        SensorsDataAutoTrackHelper.trackMenuItem(this, menuItem);
        return onOptionsItemSelected;
    }

    protected void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        String name = GalleryInputFragment.class.getName();
        if (supportFragmentManager.findFragmentByTag(name) == null) {
            GalleryInputFragment galleryInputFragment = (GalleryInputFragment) ClassUtils.instantiate(this.fragmentClass);
            Bundle bundle2 = this.fragmentArgs;
            Bundle bundle3 = bundle2 == null ? new Bundle() : new Bundle(bundle2);
            bundle3.putAll(GalleryInputFragment.createArguments(true));
            galleryInputFragment.setArguments(bundle3);
            galleryInputFragment.setGalleryListener(this);
            supportFragmentManager.beginTransaction().replace(C0134R.id.expanded_fragment, galleryInputFragment, name).commit();
        }
    }
}
