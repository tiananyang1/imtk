package com.intercom.composer.input;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import com.intercom.composer.C0123R;
import com.intercom.composer.input.InputFragment;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/Input.class */
public abstract class Input<T extends InputFragment> {
    private String fragmentTag;
    private final IconProvider iconProvider;
    private final String uniqueIdentifier;

    /* JADX INFO: Access modifiers changed from: protected */
    public Input(String str, IconProvider iconProvider) {
        this.uniqueIdentifier = str;
        this.iconProvider = iconProvider;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Inputs must have a non-empty unique identifier.");
        }
    }

    public abstract T createFragment();

    @Nullable
    public T findFragment(FragmentManager fragmentManager) {
        return (T) fragmentManager.findFragmentByTag(this.fragmentTag);
    }

    @ColorRes
    public int getBackgroundColor() {
        return C0123R.color.intercom_composer_white;
    }

    @ColorRes
    public int getBorderColor() {
        return C0123R.color.intercom_composer_border;
    }

    public Drawable getIconDrawable(Context context) {
        return this.iconProvider.getIconDrawable(this.uniqueIdentifier, context);
    }

    public String getUniqueIdentifier() {
        return this.uniqueIdentifier;
    }

    public void setFragmentTag(String str) {
        this.fragmentTag = str;
    }
}
