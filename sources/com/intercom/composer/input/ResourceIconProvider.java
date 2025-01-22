package com.intercom.composer.input;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/ResourceIconProvider.class */
public class ResourceIconProvider implements IconProvider {

    @DrawableRes
    private final int resId;

    public ResourceIconProvider(@DrawableRes int i) {
        this.resId = i;
    }

    @Override // com.intercom.composer.input.IconProvider
    public Drawable getIconDrawable(String str, Context context) {
        return ContextCompat.getDrawable(context, this.resId);
    }
}
