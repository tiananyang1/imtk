package com.intercom.composer.keyboard;

import android.content.Context;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/keyboard/OrientationProvider.class */
public class OrientationProvider {
    private final Context context;

    public OrientationProvider(Context context) {
        this.context = context;
    }

    public int getOrientation() {
        return this.context.getResources().getConfiguration().orientation;
    }
}
